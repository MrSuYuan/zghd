package com.zghd.service;

import com.util.md5.EncryptUtil;
import com.util.md5.MD5;
import com.zghd.entity.YuLiang.*;
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
import java.util.UUID;

/**
 * 余梁激励视频
 */
@Service(value="ylService")
public class YLService {

    /**
     * 余梁请求
     */
    public GetAdsResp YLSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        GetAdsResp gar;
        //将下游参数都转为余梁的参数
        String data = formatData(gaReq, gu);
        String uri = "http://api.ad.scjcgj.top/api/video/get?app_id=10002&version=1.0.3";
        CloseableHttpResponse resp = null;
        CloseableHttpClient httpclient = null;
        httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        resp = httpclient.execute(httpPost);
        HttpEntity result = resp.getEntity();

        if(null != result && !"".equals(result)){
            String entityStr = EntityUtils.toString(result, "utf-8");
            //格式化返回数据,放到流里面返回给下游
            gar = formatBackData(entityStr, gaReq, gu);

        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) {
        RequestJson rj = new RequestJson();

        String requestId = UUID.randomUUID().toString();
        rj.setRequest_id(requestId);

        //App
        App app = new App();
        app.setApp_id(gu.getUpstreamAppId());
        app.setApp_version(gaReq.getApp().getAppVersion());
        app.setApp_name(gaReq.getApp().getAppName());
        app.setApp_package(gaReq.getApp().getAppPackage());
        rj.setApp(app);

        //Device
        Device device = new Device();
        int os_type = gaReq.getDevice().getOsType();
        if (os_type == 1) {
            device.setOs("android");
        } else {
            device.setOs("iOS");
        }
        device.setVendor(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setIdfa(gaReq.getDevice().getIdfa());
        device.setImei(gaReq.getDevice().getImei());
        device.setIdfa_md5(MD5.md5(gaReq.getDevice().getIdfa()));
        device.setImei_md5(MD5.md5(gaReq.getDevice().getImei()));
        device.setAndroid_id(gaReq.getDevice().getAndroidId());
        device.setMac(gaReq.getDevice().getMac());
        device.setScreen_width(gaReq.getDevice().getScreenWidth());
        device.setScreen_height(gaReq.getDevice().getScreenHeight());
        device.setOri(-1);
        rj.setDevice(device);

        //Imp
        Imp imp = new Imp();
        List<Imp> list = new ArrayList();
        imp.setImpJd(Integer.valueOf(gu.getUpstreamId()));
        imp.setSlot_id("spty-mas-video");
        list.add(0, imp);
        rj.setImps(list);

        //基站信息
        Network nw = new Network();
        nw.setIp(gaReq.getNetwork().getIp());
        nw.setConnection_type(0);
        nw.setCarrier("46000");
        //余梁类型 0:未知  1:以太网  2:Wifi  3:蜂窝网络,未知代  4:2G   5:3G  6:4G
        //下游类型 0--CONNECTION_UNKNOWN 1--CELL_UNKNOWN 2--CELL_2G 3--CELL_3G 4--CELL_4G 5--CELL_5G 100—WIFI 101—ETHERNET 999--NEW_TYPE
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2) {
            nw.setConnection_type(4);
        } else if (connectionType == 3) {
            nw.setConnection_type(5);
        } else if (connectionType == 4) {
            nw.setConnection_type(6);
        } else if (connectionType == 100) {
            nw.setConnection_type(2);
        } else {
            nw.setConnection_type(0);
        }
        //46000:中国移动   46001:中国致通   46003:中国电信   46020:中国铁通
        //0--UNKNOWN_OPERATOR 1--CHINA_MOBILE 2--CHINA_TELECOM 3--CHINA_UNICOM 99--OTHER_OPERATOR
        int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 0) {
            nw.setCarrier("46000");
        } else if (operatorType == 1) {
            nw.setCarrier("46003");
        } else if (operatorType == 2) {
            nw.setCarrier("46001");
        } else if (operatorType == 3) {
            nw.setCarrier("46000");
        } else {
            nw.setCarrier("46000");
        }
        rj.setNetwork(nw);

        return JSONObject.fromObject(rj).toString();
    }


    /**
     * 格式化余梁返回数据
     */
    public GetAdsResp formatBackData(String entityStr, GetAdsReq gaReq, GetUpstream gu) {
        //返回整体
        GetAdsResp gar = new GetAdsResp();
        gar.setRequestId(gaReq.getRequestId());
        JSONObject json = JSONObject.fromObject(entityStr);
        String units = json.getString("units");

        if (null != units) {
            String unit = JSONArray.fromObject(units).get(0).toString();
            JSONObject unitJson = JSONObject.fromObject(unit);

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(unitJson.getString("unit_id"));
            String interaction_type = unitJson.getString("interaction_type");

            //视频内容
            MaterialMeta ym = new MaterialMeta();
            JSONObject video = JSONObject.fromObject(unitJson.getString("video"));
            ym.setAdTitle(video.getString("title"));
            List<String> desc = new ArrayList<>();
            desc.add(video.getString("desc"));
            ym.setDescs(desc);
            //图标
            String icon = JSONObject.fromObject(video.getString("icon")).getString("url");
            List<String> iconUrl = new ArrayList<>();
            iconUrl.add(0, icon);
            ym.setIconUrls(iconUrl);
            //封面图
            String image = JSONObject.fromObject(video.getString("image")).getString("url");
            List<String> imageUrl = new ArrayList<>();
            imageUrl.add(0, image);
            ym.setImageUrl(imageUrl);
            //视频
            JSONObject video_material = JSONObject.fromObject(video.getString("video_material"));
            ym.setVideoUrl(video_material.getString("url"));
            ym.setMaterialWidth(Integer.valueOf(video_material.getString("width")));
            ym.setMaterialHeight(Integer.valueOf(video_material.getString("height")));
            ym.setVideoDuration(Integer.valueOf(video_material.getString("duration")));
            if ("SURFING".equals(interaction_type)) {
                ym.setInteractionType(1);
            } else if ("DOWNLOAD".equals(interaction_type)) {
                ym.setInteractionType(2);
            } else {
                ym.setInteractionType(0);
            }

            //app信息
            JSONObject app_info = JSONObject.fromObject(video.getString("app_info"));
            if(null != app_info.get("app_package")){
                ym.setPackageName(app_info.getString("app_package"));
            }
            if(null != app_info.get("app_size")){
                ym.setAppSize(Integer.valueOf(app_info.getString("app_size")));
            }
            if(null != app_info.get("landing_url")){
                ym.setClickUrl(app_info.getString("landing_url"));
            }

            //监控信息 array of AdTracking
            JSONArray ad_trackings = JSONArray.fromObject(unitJson.getString("ad_trackings"));
            //视频播放监控
            List<Track> ydtTrackList = new ArrayList<>();
            List<String> winNoticeUrls = new ArrayList<>();
            List<String> winCNoticeUrls = new ArrayList<>();
            List<String> winCloseUrls = new ArrayList<>();
            List<String> urls0 = new ArrayList<>();
            Track yt0 = new Track();
            List<String> urls1 = new ArrayList<>();
            Track yt1 = new Track();
            List<String> urls2 = new ArrayList<>();
            Track yt2 = new Track();
            List<String> urls3 = new ArrayList<>();
            Track yt3 = new Track();
            List<String> urls4 = new ArrayList<>();
            Track yt4 = new Track();
            for (int i = 0; i < ad_trackings.size(); i++) {
                JSONObject tracking = JSONObject.fromObject(ad_trackings.get(i));
                int tracking_event = Integer.valueOf(tracking.getString("tracking_event"));
                String tracking_url = tracking.getString("tracking_url");

                EncryptUtil eu = new EncryptUtil();
                //展示曝光
                if (tracking_event == 1) {
                    String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&4&3","zghd");
                    winNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    winNoticeUrls.add(tracking_url);
                }
                //点击
                if (tracking_event == 2) {
                    String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&4&4","zghd");
                    winCNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    winCNoticeUrls.add(tracking_url);
                }
                //关闭
                if (tracking_event == 3) {
                    winCloseUrls.add(0, tracking_url);
                }

                //视频播放进度:开始
                if (tracking_event == 10101) {
                    yt0.setType(0);
                    urls0.add(tracking_url);
                }
                //视频播放进度:结束
                if (tracking_event == 10102) {
                    yt4.setType(4);
                    urls4.add(tracking_url);
                }
                //视频播放进度25%：10103
                if (tracking_event == 10103) {
                    yt1.setType(1);
                    urls1.add(tracking_url);
                }
                //视频播放进度50%：10104
                if (tracking_event == 10104) {
                    yt2.setType(2);
                    urls2.add(tracking_url);
                }
                //视频播放进度75%：10105
                if (tracking_event == 10105) {
                    yt3.setType(3);
                    urls3.add(tracking_url);
                }


            }
            yt0.setUrls(urls0);
            yt1.setUrls(urls1);
            yt2.setUrls(urls2);
            yt3.setUrls(urls3);
            yt4.setUrls(urls4);
            ydtTrackList.add(yt0);
            ydtTrackList.add(yt1);
            ydtTrackList.add(yt2);
            ydtTrackList.add(yt3);
            ydtTrackList.add(yt4);
            ya.setTracks(ydtTrackList);
            ym.setWinNoticeUrls(winNoticeUrls);
            ym.setWinCNoticeUrls(winCNoticeUrls);
            ym.setWinCloseUrls(winCloseUrls);
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
        return gar;
    }

}