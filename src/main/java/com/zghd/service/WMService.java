package com.zghd.service;

import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.WangMai.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 旺脉激励视频
 */
@Service(value="wmService")
public class WMService {

    /**
     * 向后台发请求
     */
    public GetAdsResp WMSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String url = "http://api.mssp.adwangmai.com/v1.api";
        //参数转换
        String data = formatData(gaReq, gu);
        CloseableHttpResponse response;
        CloseableHttpClient httpclient;
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
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){
        String key = "cac8839448ab2f9b";
        String apptoken = gu.getUpstreamAppId();
        //请求参数
        WMRequest request = new WMRequest();
        request.setSign(MD5.md5(key+apptoken));
        request.setApptoken(apptoken);
        //参数集
        Data data = new Data();

        //APP信息
        App app = new App();
        String [] version = gaReq.getApp().getAppVersion().split("\\.");
        AppVersion appVersion = new AppVersion();
        appVersion.setMajor(Integer.valueOf(version[0]));
        appVersion.setMinor(Integer.valueOf(version[1]));
        appVersion.setMicro(Integer.valueOf(version[2]));
        app.setApp_version(appVersion);
        app.setPkgName(gu.getUpstreamPackageName());
        app.setAppName(gaReq.getApp().getAppName());
        //app.setStoreurl("");
        data.setApp(app);

        //代码位信息
        Adslot adslot = new Adslot();
        adslot.setAdslot_id(gu.getUpstreamId());
        AdslotSize adslotSize = new AdslotSize();
        adslotSize.setHeight(gaReq.getSlot().getSlotheight());
        adslotSize.setWidth(gaReq.getSlot().getSlotwidth());
        adslot.setAdslot_size(adslotSize);
        adslot.setSupport_deeplink(0);
        adslot.setSecure(0);
        data.setAdslot(adslot);

        //设备信息
        Device device = new Device();
        device.setDevice_type(1);
        //1安卓 2ios
        device.setOs_type(gaReq.getDevice().getOsType());
        OsVersion osVersion = new OsVersion();
        String [] ov = gaReq.getApp().getAppVersion().split("\\.");
        osVersion.setMajor(Integer.valueOf(ov[0]));
        osVersion.setMinor(Integer.valueOf(ov[1]));
        osVersion.setMicro(Integer.valueOf(ov[2]));
        device.setOs_version(osVersion);
        device.setVendor(gaReq.getDevice().getVendor());
        device.setManufacturer(gaReq.getDevice().getBrand());
        device.setModel(gaReq.getDevice().getModel());
        ScreenSize screenSize = new ScreenSize();
        screenSize.setHeight(gaReq.getDevice().getScreenHeight());
        screenSize.setWidth(gaReq.getDevice().getScreenWidth());
        device.setScreen_size(screenSize);
        device.setPpi(gaReq.getDevice().getPpi());
        Udid udid = new Udid();
        udid.setIdfa(gaReq.getDevice().getIdfa());
        udid.setIdfa_md5(MD5.md5(gaReq.getDevice().getIdfa()));
        udid.setImei(gaReq.getDevice().getImei());
        udid.setImei_md5(MD5.md5(gaReq.getDevice().getImei()));
        udid.setAndroid_id(gaReq.getDevice().getAndroidId());
        udid.setAndroid_id_md5(MD5.md5(gaReq.getDevice().getAndroidId()));
        udid.setMac(gaReq.getDevice().getMac());
        //udid.setOaid();
        device.setUdid(udid);
        device.setUser_agent(gaReq.getDevice().getUa());
        device.setOrientation(gaReq.getDevice().getScreenOrientation());
        data.setDevice(device);

        //网络信息
        Network network = new Network();
        network.setIpv4(gaReq.getNetwork().getIp());
        network.setConnection_type(0);
        network.setOperator_type(0);
        network.setImsi(gaReq.getDevice().getImsi());
        data.setNetwork(network);

        //定位信息
        Gps gps = new Gps();
        data.setGps(gps);
        request.setData(data);
        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(backData);
        String code = json.getString("error_code");
        if ("0".equals(code)){
            JSONObject wxad = json.getJSONObject("wxad");
            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(json.getString("request_id"));
            //ya.setHtmlSnippet();
            if (wxad.has("source")){
                ya.setAdtext(wxad.getString("source"));
            }
            if (wxad.has("adLogo")){
                ya.setAdlogo(wxad.getString("adLogo"));
            }

            //物料元
            MaterialMeta ym = new MaterialMeta();
            ym.setMaterialWidth(wxad.getInt("material_width"));
            ym.setMaterialHeight(wxad.getInt("material_height"));
            int creative_type = wxad.getInt("creative_type");
            if (creative_type == 1){//文字
                ym.setCreativeType(1);
            }else if (creative_type == 2){//图片
                ym.setCreativeType(2);
            }else if (creative_type == 3){//图文
                ym.setCreativeType(3);
            }else if (creative_type == 6){//视频
                ym.setCreativeType(5);
            }else{//其他
                ym.setCreativeType(4);
            }
            int interactionType = wxad.getInt("interaction_type");
            if (interactionType == 1){
                ym.setInteractionType(1);
            }else if(interactionType == 2){
                ym.setInteractionType(2);
                ym.setPackageName(wxad.getString("app_package"));
                ym.setAppSize(wxad.getInt("app_size"));
            }else{
                ym.setInteractionType(0);
            }
            ym.setClickUrl(wxad.getString("landing_page_url"));
            if (wxad.has("brand_name")){
                ym.setBrandName(wxad.getString("brand_name"));
            }
            JSONObject video = wxad.getJSONObject("video");
            ym.setVideoDuration(video.getInt("duration"));
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            ym.setVideoUrl(video.getString("v_url"));
            JSONObject ext = video.getJSONObject("ext");
            List<String> imageUrls = new ArrayList<>();
            String image_src;
            if (ext.has("endimgurl")){
                image_src = ext.getString("endimgurl");
            }else{
                image_src = "";
            }
            imageUrls.add(image_src);
            ym.setImageUrl(imageUrls);
            String icon_src;
            if (ext.has("endiconurl")){
                icon_src = ext.getString("endiconurl");
            }else{
                icon_src = "";
            }
            List<String> iconUrls = new ArrayList<>();
            iconUrls.add(icon_src);
            ym.setIconUrls(iconUrls);
            List<String> desc = new ArrayList<>();
            if (ext.has("enddesc")){
                desc.add(ext.getString("enddesc"));
            }
            ym.setDescs(desc);
            ym.setAdTitle(ext.getString("endtitle"));

            //上报
            //视频加载
            //ym.setWinLoadUrls();
            //点击
            ym.setWinCNoticeUrls(wxad.getJSONArray("click_url"));
            //关闭
            ym.setWinCloseUrls(wxad.getJSONArray("close_track_urls"));
            //下载
            ym.setWinDownloadUrls(wxad.getJSONArray("download_track_urls"));
            //下载完成
            ym.setWinDownloadEndUrls(wxad.getJSONArray("downloaded_track_urls"));
            //安装
            //ym.setWinInstallUrls();
            //安装完成
            ym.setWinInstallEndUrls(wxad.getJSONArray("installed_track_urls"));
            //安装后打开
            ym.setWinInstallOpenUrls(wxad.getJSONArray("open_track_urls"));
            //激活
            if (wxad.has("dp_success_track_urls")){
                ym.setWinActiveUrls(wxad.getJSONArray("dp_success_track_urls"));
            }

            JSONObject v_tracking = video.getJSONObject("v_tracking");

            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            //静音
            if (v_tracking.has("v_mute_tracking_event")){
                Track track103 = new Track();
                track103.setType(103);
                track103.setUrls(v_tracking.getJSONArray("v_mute_tracking_event"));
                ydtTrackList.add(track103);
            }

            //关闭静音
            if (v_tracking.has("v_unmute_tracking_event")){
                Track track104 = new Track();
                track104.setType(104);
                track104.setUrls(v_tracking.getJSONArray("v_unmute_tracking_event"));
                ydtTrackList.add(track104);
            }

            //暂停
            if (v_tracking.has("v_pause_tracking_event")){
                Track track107 = new Track();
                track107.setType(107);
                track107.setUrls(v_tracking.getJSONArray("v_pause_tracking_event"));
                ydtTrackList.add(track107);
            }

            //继续播放
            if (v_tracking.has("v_resume_tracking_event")){
                Track track108 = new Track();
                track108.setType(108);
                track108.setUrls(v_tracking.getJSONArray("v_resume_tracking_event"));
                ydtTrackList.add(track108);
            }

            //全屏播放
            if (v_tracking.has("v_fullscreen_tracking_event")){
                Track track101 = new Track();
                track101.setType(101);
                track101.setUrls(v_tracking.getJSONArray("v_fullscreen_tracking_event"));
                ydtTrackList.add(track101);
            }


            //退出全屏
            if (v_tracking.has("v_exitfullscreen_tracking_event")){
                Track track102 = new Track();
                track102.setType(102);
                track102.setUrls(v_tracking.getJSONArray("v_exitfullscreen_tracking_event"));
                ydtTrackList.add(track102);
            }

            Track track4 = new Track();
            track4.setType(4);
            //曝光放在这
            List<String> imptracker = wxad.getJSONArray("win_notice_url");
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&7&3");
            imptracker.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            JSONArray jsonArray = v_tracking.getJSONArray("v_progress_tracking_event");
            for (int i = 0; i < jsonArray.size(); i++){
                JSONObject jb = jsonArray.getJSONObject(i);
                int millisec = jb.getInt("millisec");
                List<String> urls = jb.getJSONArray("url");
                if (millisec == 0){
                    Track track0 = new Track();
                    track0.setType(0);
                    track0.setUrls(urls);
                    ydtTrackList.add(track0);
                }else if(millisec == video.getInt("duration")){

                    for (int j = 0; j < urls.size(); j++){
                        imptracker.add(urls.get(j));
                    }
                }
            }
            track4.setUrls(imptracker);
            ydtTrackList.add(track4);
            ya.setTracks(ydtTrackList);

            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);

            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);
            gar.setRequestId(gaReq.getRequestId());

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
        }else if ("200000".equals(code)){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

}
