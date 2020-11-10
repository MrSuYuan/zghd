package com.zghd.service;

import com.util.md5.MD5;
import com.zghd.entity.OPPO.*;
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
import java.util.Date;
import java.util.List;

/**
 * OPPO广告
 */
@Service(value="oppoService")
public class OPPOService {

    /**
     * 向OPPO后台发请求
     */
    public GetAdsResp OPPOSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String data = formatData(gaReq, gu);
        //String url = "http://uapi.ads-test.wanyol.com/union/ads/v1/api/adReq";
        String url = "https://uapi.ads.heytapmobi.com/union/ads/v1/api/adReq";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpClient.execute(httpPost);
        HttpEntity result = resp.getEntity();
        String str = EntityUtils.toString(result, "utf-8");
        gar = formatBackData(str,gaReq,gu);

        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        OPPORequest request = new OPPORequest();
        //oppo固定参数
        request.setApiVersion(1);
        request.setApiVc(108);
        request.setAppStoreVc(5500);

        AppInfo app = new AppInfo();
        app.setAppId(gu.getUpstreamAppId());
        app.setPkgname(gu.getUpstreamPackageName());
        app.setVerName(gaReq.getApp().getAppVersion());
        request.setAppInfo(app);

        PosInfo pos = new PosInfo();
        pos.setId(gu.getUpstreamId());
        //oppo广告类型 1banner 横幅  2插屏  4开屏  8native原生  64激励视频
        int adtype = gaReq.getSlot().getAdtype();
        if (adtype == 1){
            pos.setPosType(1);
        }else if (adtype == 2){
            pos.setPosType(4);
        }else if (adtype == 3){
            pos.setPosType(2);
        }else if (adtype == 4){
            pos.setPosType(8);
        }else if (adtype == 5){
            pos.setPosType(64);
        }
        pos.setW(gaReq.getSlot().getSlotwidth());
        pos.setH(gaReq.getSlot().getSlotheight());
        request.setPosInfo(pos);

        DevInfo dev = new DevInfo();
        dev.setImei(gaReq.getDevice().getImei());
        dev.setImeiMd5(MD5.md5(gaReq.getDevice().getImei()));
        dev.setOaId(gaReq.getDevice().getOaid());
        dev.setVaId("");
        dev.setIp(gaReq.getNetwork().getIp());
        dev.setUa(gaReq.getDevice().getUa());
        dev.setMac(gaReq.getDevice().getMac());
        dev.setMacMd5(MD5.md5(gaReq.getDevice().getMac()));
        dev.setAnId(gaReq.getDevice().getAndroidId());
        dev.setColorOsv("");
        dev.setRomv("");
        dev.setAnVer(gaReq.getDevice().getOsVersion());
        dev.setH(gaReq.getDevice().getScreenHeight());
        dev.setW(gaReq.getDevice().getScreenWidth());
        dev.setDensity(gaReq.getDevice().getPpi());
        //oppo网络类型 字符串类型  UNKNOWN 2G  3G  4G  5G  WIFI
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            dev.setConnectionType("2G");
        }else if (connectionType == 3){
            dev.setConnectionType("3G");
        }else if (connectionType == 4){
            dev.setConnectionType("4G");
        }else if (connectionType == 5){
            dev.setConnectionType("4G");
        }else if (connectionType == 100){
            dev.setConnectionType("WIFI");
        }else{
            dev.setConnectionType("UNKNOWN");
        }
        //oppo运营商 0未知  1移动  2联通  3电信
        int operatorType = gaReq.getNetwork().getOperatorType();
        dev.setCarrier(operatorType);
        dev.setOri(0);
        dev.setLinkSpeed(0);
        dev.setBrand(gaReq.getDevice().getBrand());
        dev.setModel(gaReq.getDevice().getModel());

        GpsInfo gps = new GpsInfo();
        gps.setLat(gaReq.getNetwork().getLat());
        gps.setLon(gaReq.getNetwork().getLon());
        gps.setTimestamp(new Date().getTime());
        dev.setGpsInfo(gps);
        request.setDevInfo(dev);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu) {
        GetAdsResp gar = new GetAdsResp();
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        JSONObject json = JSONObject.fromObject(backData);
        int code = json.getInt("code");
        if (code == 0) {
            //取第一条广告
            JSONObject ad = JSONObject.fromObject(json.getJSONArray("adList").get(0));

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(ad.getString("adId"));
            if (null!=ad.getJSONObject("logoFile") && !"null".equals(ad.getString("logoFile"))){
                ya.setAdlogo(ad.getJSONObject("logoFile").getString("url"));
            }

            //视频内容
            MaterialMeta ym = new MaterialMeta();
            //创意类型
            int creativeType = ad.getInt("creativeType");
            JSONArray files = ad.getJSONArray("fileList");
            List<String> image_url = new ArrayList<>();
            //视频类
            if (creativeType == 10){
                ym.setCreativeType(5);
                ym.setVideoDuration(ad.getInt("videoDuration") / 1000);
                for (int i = 0; i < files.size(); i++){
                    JSONObject f = JSONObject.fromObject(files.get(i));
                    int fileType = f.getInt("fileType");
                    //图片路径
                    if (fileType == 1){
                        image_url.add(ad.getString("url"));
                    }
                    //视频路径
                    if (fileType == 2){
                        ym.setVideoUrl(f.getString("url"));
                    }
                }
                ym.setImageUrl(image_url);


            //图片类
            }else{
                if (creativeType == 2){
                    ym.setCreativeType(2);
                }else if (creativeType == 6 || creativeType == 7 || creativeType == 8){
                    ym.setCreativeType(3);
                }else{
                    ym.setCreativeType(4);
                }
                for (int i = 0; i < files.size(); i++){
                    JSONObject f = JSONObject.fromObject(files.get(i));
                    int fileType = f.getInt("fileType");
                    //图片路径
                    if (fileType == 1){
                        image_url.add(f.getString("url"));
                    }
                }
                ym.setImageUrl(image_url);
            }

            ym.setAdTitle(ad.getString("title"));
            List<String> descs = new ArrayList<>();
            descs.add(ad.getString("desc"));
            ym.setDescs(descs);
            String deepLink = ad.getString("deepLink");
            if (!"".equals(deepLink)){
                ym.setDeepLink(true);
                ym.setDeepLinkUrl(deepLink);
            }
            ym.setClickUrl(ad.getString("targetUrl"));
            //推广类型 1链接 2应用
            int contentType = ad.getInt("contentType");
            if (contentType == 1){
                ym.setInteractionType(1);
            }else{
                ym.setInteractionType(2);
            }
            ym.setPackageName(ad.getString("appPackage"));
            ym.setAppSize(ad.getInt("apkSize")/1000/1000);

            //上报信息
            List<Track> ydtTrackList = new ArrayList<>();
            JSONArray trackingList = ad.getJSONArray("trackingList");
            for (int i = 0; i < trackingList.size(); i++){
                JSONObject track = JSONObject.fromObject(trackingList.get(i));
                int trackingEvent = track.getInt("trackingEvent");
                //点击
                if (trackingEvent == 1){
                    ym.setWinCNoticeUrls(macroParam(track.getJSONArray("trackUrls")));
                //曝光
                }else if (trackingEvent == 2){
                    ym.setWinNoticeUrls(macroParam(track.getJSONArray("trackUrls")));
                //关闭
                }else if (trackingEvent == 3){
                    ym.setWinCloseUrls(macroParam(track.getJSONArray("trackUrls")));
                //视频开始播放
                }else if (trackingEvent == 2){
                    Track yt = new Track();
                    yt.setType(0);
                    yt.setUrls(macroParam(track.getJSONArray("trackUrls")));
                    ydtTrackList.add(yt);
                //视频播放完成
                }else if (trackingEvent == 2){
                    Track yt = new Track();
                    yt.setType(4);
                    yt.setUrls(macroParam(track.getJSONArray("trackUrls")));
                    ydtTrackList.add(yt);
                //视频播放25%
                }else if (trackingEvent == 2){
                    Track yt = new Track();
                    yt.setType(1);
                    yt.setUrls(macroParam(track.getJSONArray("trackUrls")));
                    ydtTrackList.add(yt);
                //视频播放50%
                }else if (trackingEvent == 2){
                    Track yt = new Track();
                    yt.setType(2);
                    yt.setUrls(macroParam(track.getJSONArray("trackUrls")));
                    ydtTrackList.add(yt);
                //视频播放75%
                }else if (trackingEvent == 2){
                    Track yt = new Track();
                    yt.setType(3);
                    yt.setUrls(macroParam(track.getJSONArray("trackUrls")));
                    ydtTrackList.add(yt);
                }
            }

            ya.setTracks(ydtTrackList);
            //综合封装返回
            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);
            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
            return gar;
        } else if (code == 1001) {
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
            return gar;
        } else {
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
            return gar;
        }
    }

    //宏替换
    public List<String> macroParam(List<String> list){
        List returnList = new ArrayList();
        for (int i = 0; i < list.size(); i++){
            String s = list.get(i);

            //替换__AZCX__
            s = s.replace( "$ux$","__UP_X__");

            //替换__AZCY__
            s = s.replace( "$uy$","__UP_Y__");

            //替换__AZMX__
            s = s.replace( "$dx$","__DOWN_X__");

            //替换__AZMY__
            s = s.replace( "$dy$","__DOWN_Y__");

            //运营商
            s = s.replace( "$nt$","UNKNOWN");
            //网络
            s = s.replace( "$ca$","0");

            returnList.add(i, s);
        }
        return returnList;
    }

}
