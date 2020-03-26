package com.zghd.service;

import com.util.md5.EncryptUtil;
import com.zghd.entity.JuLiang.App;
import com.zghd.entity.JuLiang.Device;
import com.zghd.entity.JuLiang.Geo;
import com.zghd.entity.JuLiang.RequestJson;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 聚量
 */
@Service(value="jlService")
public class JLService {

    /**
     * 聚量请求
     */
    public GetAdsResp JLSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        GetAdsResp gar;
        String url = "http://api.joomob.com/phone/agent.php";
        //参数转换
        String data = formatData(gaReq, gu);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("charset", "utf-8");
        StringEntity stringEntity = new StringEntity(data,"utf-8");
        httpPost.setEntity(stringEntity);
        response = httpclient.execute(httpPost);
        HttpEntity result = response.getEntity();
        String str = EntityUtils.toString(result);
        gar = formatBackData(str, gaReq, gu);
        return gar;
    }

    /**
     * 封装入参
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){
        RequestJson requestJson = new RequestJson();

        App app = new App();
        app.setAn(gaReq.getApp().getAppName());
        app.setPkg(gaReq.getApp().getAppPackage());
        app.setVer(gaReq.getApp().getAppVersion());
        requestJson.setApp(app);

        Device device = new Device();
        device.setIdfa(gaReq.getDevice().getIdfa());
        device.setIme(gaReq.getDevice().getImei());
        device.setIcc(gaReq.getDevice().getImsi());
        int os_type = gaReq.getDevice().getOsType();
        if(os_type == 1){
            device.setPlt(1);
        }else{
            device.setPlt(2);
        }
        device.setDvt(gaReq.getDevice().getDeviceType()+"");
        device.setOv(gaReq.getDevice().getOsVersion());
        device.setDpi(gaReq.getDevice().getPpi()+"");
        device.setSwidth(gaReq.getDevice().getScreenWidth()+"");
        device.setSheight(gaReq.getDevice().getScreenHeight()+"");
        device.setMdl(gaReq.getDevice().getModel());
        device.setBrd(gaReq.getDevice().getBrand());
        device.setAid(gaReq.getDevice().getAndroidId());
        device.setLg("zh");
        device.setNet(0);
        device.setOpt("46000");
        device.setMac(gaReq.getDevice().getMac());
        device.setUa(gaReq.getDevice().getUa());
        requestJson.setDevice(device);

        Geo geo = new Geo();
        geo.setLgd(gaReq.getNetwork().getLon()+"");
        geo.setLtd(gaReq.getNetwork().getLon()+"");
        requestJson.setGeo(geo);
        requestJson.setAdw(gaReq.getSlot().getSlotwidth());
        requestJson.setAdh(gaReq.getSlot().getSlotheight());
        requestJson.setAppid(gu.getUpstreamAppId());
        requestJson.setSlotid("video");
        requestJson.setIp(gaReq.getNetwork().getIp());

        return JSONObject.fromObject(requestJson).toString();
    }

    /**
     * 封装出参
     */
    public GetAdsResp formatBackData(String entityStr, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        JSONObject json = JSONObject.fromObject(entityStr);
        int resCode= json.getInt("res");
        if(0 == resCode){
            JSONObject ad = json.getJSONObject("ad");
            //广告主体
            Ad ya = new Ad();
            ya.setAdKey("");
            if (ad.has("adlogo")){
                ya.setAdtext(ad.getString("adlogo"));
            }
            ya.setAdlogo("");

            //物料元
            JSONObject video = ad.getJSONObject("videoext");
            JSONObject adext = ad.getJSONObject("adext");
            JSONObject vt = video.getJSONObject("vt");

            MaterialMeta ym = new MaterialMeta();
            ym.setAdTitle(ad.getString("title"));
            List<String> descs = new ArrayList<>();
            String txt = ad.getString("txt");
            if (null != txt && !"".equals(txt)){
                descs.add(txt);
            }
            ym.setDescs(descs);
            List<String> imageUrl = new ArrayList<>();
            String img = ad.getString("img");
            if (null != img && !"".equals(img)){
                imageUrl.add(img);
            }
            String img2 = ad.getString("img2");
            if (null != img2 && !"".equals(img2)){
                imageUrl.add(img2);
            }
            String img3 = ad.getString("img3");
            if (null != img3 && !"".equals(img3)){
                imageUrl.add(img3);
            }
            ym.setImageUrl(imageUrl);
            List<String> iconUrls = new ArrayList<>();
            iconUrls.add(ad.getString("icon"));
            ym.setIconUrls(iconUrls);
            ym.setMaterialWidth(ad.getInt("adw"));
            ym.setMaterialHeight(ad.getInt("adh"));
            ym.setClickUrl(ad.getString("lpg"));
            int act = ad.getInt("act");
            if (act == 1){//广告页
                ym.setInteractionType(1);
            }else{//下载类
                ym.setInteractionType(2);
            }
            ym.setCreativeType(4);
            ym.setPackageName(adext.getString("pkg"));
            //ym.setAppSize();
            ym.setVideoUrl(video.getString("vurl"));
            if (adext.has("appname")){
                ym.setBrandName(adext.getString("appname"));
            }
            ym.setVideoDuration(video.getInt("duation"));
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            ya.setHtmlSnippet(video.getString("vhtml"));

            EncryptUtil eu = new EncryptUtil();
            //上报
            //展现曝光
            List<String> winNoticeUrls = ad.getJSONArray("imp");
            String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&11&3","zghd");
            winNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(winNoticeUrls);
            //点击
            List<String> winCNoticeUrls = ad.getJSONArray("click");
            String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&11&4","zghd");
            winCNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(winCNoticeUrls);
            //关闭
            ym.setWinCloseUrls(vt.getJSONArray("vi"));
            //下载完成
            ym.setWinDownloadEndUrls(adext.getJSONArray("downsucc"));
            //安装完成
            ym.setWinInstallEndUrls(adext.getJSONArray("installsucc"));
            //激活
            ym.setWinActiveUrls(adext.getJSONArray("appactive"));

            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            Track track0 = new Track();
            track0.setType(0);
            track0.setUrls(vt.getJSONArray("vs"));
            ydtTrackList.add(track0);

            Track track4 = new Track();
            track4.setType(4);
            track4.setUrls(vt.getJSONArray("vc"));
            ydtTrackList.add(track4);
            ya.setTracks(ydtTrackList);

            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);


            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
            return gar;
        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
            return gar;
        }
    }
}
