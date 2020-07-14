package com.zghd.service;

import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.XiaoZhi.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 小知
 */
@Service(value="xzService")
public class XZService {

    /**
     * 向小知发送请求
     */
    public GetAdsResp XZSend(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        //分配上游
        String data = formatData(gaReq, gu);
        String uri;
        //133
        if ("56".equals(gu.getUpstreamId())){
            uri = "http://121.201.120.145:18080/malacca/sdkPullAds.do";
        }else{
            uri = "https://malacca.inveno.com/malacca/sdkPullAds.do";
        }

        String str = TestConnectionPool.post(uri,data);
        /**
         * 第一种方法
         */
        /*CloseableHttpResponse resp = null;
        CloseableHttpClient httpclient = null;
        httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Accept-Encoding", "utf-8");
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept", "application/json");
        Long t1 = new Date().getTime();
        HttpEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        resp = httpclient.execute(httpPost);
        HttpEntity result = resp.getEntity();
        String str = EntityUtils.toString(result, "utf-8");
        Long t2 = new Date().getTime();
        System.out.println("第一种方法时长"+(t2 - t1));*/

        /*Long t3 = new Date().getTime();
        String res = RestTemplateUtil.postForEntity(uri,data, String.class);
        String str = new String(res.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("第二种方法返回"+str);
        Long t4 = new Date().getTime();
        System.out.println("第二种方法时长"+(t4 - t3));*/

        //String str = new String(res.getBytes("ISO-8859-1"),"UTF-8");

        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }


    /**
     * 格式化参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){

        //请求信息
        AdReq ar = new AdReq();
        ar.setBid(gaReq.getRequestId());
        ar.setApi_version("2.2.0");
        ar.setUa(gaReq.getDevice().getUa());

        //App信息
        App app = new App();
        app.setApp_id(gu.getUpstreamAppId());
        app.setChannel_id(gaReq.getDevice().getVendor());
        app.setApp_name(gaReq.getApp().getAppName());
        app.setPackage_name(gaReq.getApp().getAppPackage());
        app.setApp_version(gaReq.getApp().getAppVersion());
        app.setReport_pv_method(0);
        ar.setApp(app);

        List<DeviceId> diList = new ArrayList<>();

        //imei
        String imei = gaReq.getDevice().getImei();
        if (null != imei && !"".equals(imei)){
            DeviceId d1 = new DeviceId();
            d1.setDevice_id_type(1);
            d1.setDevice_id(imei);
            d1.setHash_type(0);
            diList.add(d1);
        }
        //idfa
        String idfa = gaReq.getDevice().getIdfa();
        if (null != idfa && !"".equals(idfa)){
            DeviceId d2 = new DeviceId();
            d2.setDevice_id_type(2);
            d2.setDevice_id(idfa);
            d2.setHash_type(0);
            diList.add(d2);
        }
        //androidId
        String androidId = gaReq.getDevice().getAndroidId();
        if (null != androidId && !"".equals(androidId)){
            DeviceId d3 = new DeviceId();
            d3.setDevice_id_type(3);
            d3.setDevice_id(androidId);
            d3.setHash_type(0);
            diList.add(d3);
        }
        //mac
        String mac = gaReq.getDevice().getMac();
        if (null != mac && !"".equals(mac)){
            DeviceId d4 = new DeviceId();
            d4.setDevice_id_type(4);
            d4.setDevice_id(mac);
            d4.setHash_type(0);
            diList.add(d4);
        }
        //idfv
        /*String idfv = deviceJson.getString("idfv");
        if (null != idfv && !"".equals(idfv)){
            DeviceId d5 = new DeviceId();
            d5.setDevice_id_type(5);
            d5.setDevice_id(idfv);
            d5.setHash_type(0);
            diList.add(d5);
        }*/
        //imsi
        String imsi = gaReq.getDevice().getImsi();
        if (null != imsi && !"".equals(imsi)){
            DeviceId d6 = new DeviceId();
            d6.setDevice_id_type(8);
            d6.setDevice_id(imsi);
            d6.setHash_type(0);
            diList.add(d6);
        }
        //oaid
        String oaid = gaReq.getDevice().getOaid();
        if (null != oaid && !"".equals(oaid)){
            DeviceId d7 = new DeviceId();
            d7.setDevice_id_type(9);
            d7.setDevice_id(oaid);
            d7.setHash_type(0);
            diList.add(d7);
        }

        User user = new User();
       //设备信息
        Device device = new Device();
        device.setDevice_id(diList);
        //1— ANDRIOD 2— IOS
        int osType = gaReq.getDevice().getOsType();
        if(osType == 1){
            device.setOs_type(2);
            if (null != oaid && !"".equals(oaid)){
                user.setUser_id(MD5.md5(gaReq.getDevice().getOaid()));
            }else{
                user.setUser_id(MD5.md5(gaReq.getDevice().getImei()));
            }
        }else if(osType == 2){
            device.setOs_type(1);
            user.setUser_id(MD5.md5(gaReq.getDevice().getIdfa()));
        }else{
            device.setOs_type(0);
        }
        ar.setUser(user);
        device.setOs_version(gaReq.getDevice().getOsVersion());
        device.setBrand(gaReq.getDevice().getBrand());
        device.setModel(gaReq.getDevice().getModel());
        //我们 1手机 2平板
        //上游 设备类型 0未知 1平板 2手机
        int deviceType = gaReq.getDevice().getDeviceType();
        if(deviceType == 1){
            device.setDevice_type(2);
        }else if(deviceType == 2){
            device.setDevice_type(1);
        }else{
            device.setDevice_type(0);
        }
        device.setLanguage("zh_cn");
        device.setScreen_width(gaReq.getDevice().getScreenWidth());
        device.setScreen_height(gaReq.getDevice().getScreenHeight());
        device.setScreen_density(gaReq.getDevice().getPpi());
        device.setScreen_orientation(0);
        ar.setDevice(device);

        //网络信息
        Network nw = new Network();
        nw.setIp(gaReq.getNetwork().getIp());
        nw.setNetwork_type(0);
        nw.setCarrier_id("0");
        ar.setNetwork(nw);

        //广告位类型
        List<Integer> adTypeList = new ArrayList<>();
        adTypeList.add(2);
        //广告位交互类型
        List<Integer> iTypeList = new ArrayList<>();
        iTypeList.add(0,2);
        /*iTypeList.add(1,3);
        iTypeList.add(2,2);*/
        //物料要求
        List<Integer> assets = new ArrayList<>();
        assets.add(0,1);
        assets.add(1,2);
        assets.add(2,3);
        assets.add(3,4);

        //广告信息
        Adspaces as = new Adspaces();
        as.setAdspace_id(gu.getUpstreamId());
        as.setAdspace_type(gaReq.getSlot().getAdtype());
        as.setAllowed_html(false);
        as.setWidth(gaReq.getSlot().getSlotwidth());
        as.setHeight(gaReq.getSlot().getSlotheight());
        as.setImpression_num(1);
        as.setOpen_type(0);
        as.setInteraction_type(iTypeList);
        as.setAssets(assets);
        ar.setAdspaces(as);

       return JSONObject.fromObject(ar).toString();
    }


    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        //返回数据
        GetAdsResp gar = new GetAdsResp();
        gar.setRequestId(gaReq.getRequestId());
        //广告信息
        Ad ya = new Ad();
        ya.setSlotId(gaReq.getSlot().getSlotId());
        //广告详情
        MaterialMeta ym = new MaterialMeta();

        JSONObject json = JSONObject.fromObject(backData);
        //错误码
        int error_code = json.getInt("error_code");
        //请求id
        //gar.setRequestId(json.getString("bid"));
        if(error_code == 0){
            if (json.getJSONArray("ads").size() > 0){
                //广告实例
                JSONObject adJson = JSONObject.fromObject(json.getJSONArray("ads").get(0));
                //广告位id
                JSONObject creativeJson = JSONObject.fromObject(adJson.getJSONArray("creative").get(0));
                //广告id
                String ad_id = creativeJson.getString("ad_id");
                ya.setAdKey(ad_id);
                //交互类型 2浏览 3下载
                int interaction_type = creativeJson.getInt("interaction_type");
                if(2==interaction_type){
                    ym.setInteractionType(1);
                }else if(3==interaction_type){
                    ym.setInteractionType(2);
                }else{
                    ym.setInteractionType(0);
                }
                //交互动作
                String interaction_object = creativeJson.getString("interaction_object");
                ym.setClickUrl(JSONObject.fromObject(interaction_object).getString("url"));
                //广告素材类型 1html素材  3原生
                String adm_type = creativeJson.getString("adm_type");
                if("1".equals(adm_type)){
                    ym.setCreativeType(1);
                }else if("3".equals(adm_type)){
                    ym.setCreativeType(2);
                }else{
                    ym.setCreativeType(3);
                }
                //过期时长 秒
                //ym.setExpiredTime(creativeJson.getInt("expiration_duration"));

                //广告素材对象
                JSONObject adm = JSONObject.fromObject(creativeJson.getString("adm"));
                JSONObject adm_native = adm.getJSONObject("adm_native");
                //标题
                ym.setAdTitle(adm_native.getString("ad_title"));
                //广告小图标
                ya.setAdtext(adm_native.getString("adtag"));
                //广告描述
                List<String> descs = new ArrayList<>();
                if(null != adm_native.get("description")){
                    descs.add(adm_native.getString("description"));
                }
                ym.setDescs(descs);
                //图片
                List<String> urls = new ArrayList<>();
                JSONArray imgs = adm_native.getJSONArray("imgs");
                for(int i=0;i<imgs.size();i++){
                    JSONObject u = JSONObject.fromObject(imgs.get(i));
                    urls.add(u.getString("url"));
                }
                ym.setImageUrl(urls);
                //监控信息
                JSONArray event_track = creativeJson.getJSONArray("event_track");
                List<String> winNoticeUrls = new ArrayList<>();
                List<String> winCNoticeUrls = new ArrayList<>();
                List<String> winDownloadEndUrls = new ArrayList<>();
                List<String> winInstallEndUrls = new ArrayList<>();
                List<String> winInstallOpenUrls = new ArrayList<>();
                List<String> winActiveUrls = new ArrayList<>();
                for(int i = 0; i<event_track.size();i++){
                    JSONObject event = JSONObject.fromObject(event_track.get(i).toString());
                    String event_type = event.getString("event_type");
                    String notify_url = event.getString("notify_url");
                    //1曝光
                    if("1".equals(event_type)){
                        winNoticeUrls.add(notify_url);
                        //2点击
                    }else if("2".equals(event_type)){
                        winCNoticeUrls.add(notify_url);
                        //3安装完成打开
                    }else if("3".equals(event_type)){
                        winInstallOpenUrls.add(notify_url);
                        //4下载完成
                    }else if("4".equals(event_type)){
                        winDownloadEndUrls.add(notify_url);
                        //5安装完成
                    }else if("5".equals(event_type)){
                        winInstallEndUrls.add(notify_url);
                        //6激活
                    }else if("6".equals(event_type)){
                        winActiveUrls.add(notify_url);
                    }else{

                    }
                }
                ym.setWinInstallOpenUrls(winInstallOpenUrls);
                ym.setWinDownloadEndUrls(winDownloadEndUrls);
                ym.setWinInstallEndUrls(winInstallEndUrls);
                ym.setWinActiveUrls(winActiveUrls);
                //曝光-平台
                String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-6-3");
                winNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                ym.setWinNoticeUrls(winNoticeUrls);
                //点击-平台
                String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-6-4");
                winCNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                ym.setWinCNoticeUrls(winCNoticeUrls);


                //综合封装返回
                List ymList = new ArrayList();
                ymList.add(ym);
                ya.setMetaGroup(ymList);
                List yaList = new ArrayList();
                yaList.add(ya);
                gar.setAds(yaList);

                gar.setErrorCode("200");
                gar.setMsg("SUCCESS");
            }else{
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");
            }

        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }
}
