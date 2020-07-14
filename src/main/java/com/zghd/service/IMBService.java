package com.zghd.service;

import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.InMoBi.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InMoBi广告请求
 */
@Service(value="imbService")
public class IMBService {

    /**
     * InMoBi广告请求
     */
    public GetAdsResp IMBSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        //入参封装
        String data = formatData(gaReq, gu);
        String url = "http://api.w.inmobi.cn/showad/v3.1";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpclient.execute(httpPost);

        HttpEntity result = resp.getEntity();
        String str = EntityUtils.toString(result, "utf-8");
        //出参封装
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }

    /**
     * 参数转换-将下游参数转换为inmobi参数类型
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) {
        IMBRequest request = new IMBRequest();
        //app信息
        App app = new App();
        app.setId(gu.getUpstreamId());
        app.setBundle(gu.getUpstreamPackageName());
        request.setApp(app);
        //imp信息,都是固定值
        Map<String,Object> imp = new HashMap<>();
        Native n = new Native();
        n.setLayout(0);
        imp.put("native",n);
        imp.put("secure",0);
        imp.put("trackertype","url_ping");
        ImpExt impExt = new ImpExt();
        impExt.setAds(1);
        imp.put("ext",impExt);
        request.setImp(imp);
        //device
        Device device = new Device();
        //1android 2ios
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setOs("android");
            device.setMd5_imei(MD5.md5(gaReq.getDevice().getImei()));
            device.setSha1_imei(MD5.sha1(gaReq.getDevice().getImei()));
            device.setO1(MD5.sha1(gaReq.getDevice().getAndroidId()));
            device.setUm5(MD5.md5(gaReq.getDevice().getAndroidId().toLowerCase()));
            device.setOaid(gaReq.getDevice().getOaid());

        }else{
            device.setOs("iOS");
            device.setIfa(gaReq.getDevice().getIdfa().toUpperCase());

        }
        device.setUa(gaReq.getDevice().getUa());
        device.setIp(gaReq.getNetwork().getIp());
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setModel(gaReq.getDevice().getModel());
        int connection = gaReq.getNetwork().getConnectionType();
        if (connection == 100){
            device.setConnectiontype(2);
        }else if (connection == 2){
            device.setConnectiontype(4);
        }else if (connection == 3){
            device.setConnectiontype(5);
        }else if (connection == 4){
            device.setConnectiontype(6);
        }else{
            device.setConnectiontype(3);
        }
        request.setDevice(device);
        //Ext
        Ext ext = new Ext();
        ext.setResponseformat("json");
        ext.setExternalSupported(true);
        request.setExt(ext);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回数据
     */
    public GetAdsResp formatBackData(String str, GetAdsReq gaReq, GetUpstream gu){
        //返回整体
        GetAdsResp gar = new GetAdsResp();
        if (null != str && !"".equals(str)){
            JSONObject json = JSONObject.fromObject(str);
            JSONArray ads = json.getJSONArray("ads");
            if (ads.size() >= 1){
                JSONObject ad = JSONObject.fromObject(ads.get(0));

                //广告主体
                Ad ya = new Ad();

                //广告物料
                JSONObject data = ad.getJSONObject("pubContent");
                //物料元
                MaterialMeta ym = new MaterialMeta();
                ym.setAdTitle(data.getString("title"));
                List<String> descs = new ArrayList();
                descs.add(data.getString("description"));
                ym.setDescs(descs);
                //图标
                JSONObject icon = data.getJSONObject("icon");
                List<String> iconUrls = new ArrayList<>();
                iconUrls.add(icon.getString("url"));
                ym.setIconUrls(iconUrls);
                //图片
                JSONObject image = data.getJSONObject("screenshots");
                List<String> imageUrls = new ArrayList<>();
                imageUrls.add(image.getString("url"));
                ym.setImageUrl(imageUrls);
                ym.setMaterialWidth((int)image.getDouble("width"));
                ym.setMaterialHeight((int)image.getDouble("height"));
                ym.setClickUrl(ad.getString("landingPage"));
                ym.setCreativeType(2);
                //true下载类  false非下载类
                boolean isApp = ad.getBoolean("isApp");
                if (isApp){
                    ym.setInteractionType(2);
                }else{
                    ym.setInteractionType(1);
                }
                //true是deeplink  false是非deeplink
                boolean deepStatus = ad.getBoolean("openExternal");
                ym.setDeepLink(deepStatus);
                //deepLink落地页
                ym.setDeepLinkUrl(data.getString("landingURL"));

                //上报信息
                JSONObject event = ad.getJSONObject("eventTracking");
                //展现曝光 18
                List<String> winNotice = event.getJSONObject("18").getJSONArray("urls");
                String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-19-3");
                winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                ym.setWinNoticeUrls(winNotice);
                //点击 8
                List<String> clk = event.getJSONObject("8").getJSONArray("urls");
                String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-19-4");
                clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                ym.setWinCNoticeUrls(clk);

                List ymList = new ArrayList();
                ymList.add(ym);
                ya.setMetaGroup(ymList);

                List yaList = new ArrayList();
                yaList.add(ya);
                gar.setAds(yaList);
                gar.setRequestId(gaReq.getRequestId());
                gar.setErrorCode("200");
                gar.setMsg("SUCCESS");

            }else{
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");

            }

        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }

    /**
     * 技术推荐使用的sha1加密方式
     * 但是我测试和自己的md5加密后结果是一样的
     */
    public static String getDigested(String id, String digest) {
        int decimal16 = 16;
        int hexFf = 0xff;
        final int hex100 = 0x100;
        try {
            MessageDigest md = MessageDigest.getInstance(digest);
            md.update(id.getBytes());
            byte byteData[] = md.digest();

            // convert the byte to hex format
            StringBuffer sb = new StringBuffer();
            for (byte by : byteData) {
                sb.append(Integer.toString((by & hexFf) + hex100, decimal16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            System.out.println("错误");
        }

        return null;
    }


}
