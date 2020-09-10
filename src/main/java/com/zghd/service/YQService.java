package com.zghd.service;

import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.YongQi.*;
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
 * 甬祺激励视频
 */
@Service(value="yqService")
public class YQService {

    /**
     * 向后台发请求
     */
    public GetAdsResp YQSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        //String url = "http://52.81.7.231:8226/yongqi_rwdvedio.cgi?media=zghd&zone_id=rwd_0001";
        //正式
        String url = "http://52.81.7.231:8226/yongqi_rwdvedio.cgi?media=zghd";
        //String url = "http://54.222.193.214:8110/yongqi_rwdvedio.cgi?media=zghd";
        //参数转换
        String data = formatData(gaReq,gu);
        CloseableHttpResponse resp = null;
        CloseableHttpClient httpclient = null;
        httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        resp = httpclient.execute(httpPost);
        int code = resp.getStatusLine().getStatusCode();
        if(code == 200){
            HttpEntity result = resp.getEntity();
            String str = EntityUtils.toString(result, "utf-8");
            //格式化返回数据,放到流里面返回给下游
            gar = formatBackData(str, gaReq, gu);
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
        YQRequest yqr = new YQRequest();
        yqr.setId(gaReq.getRequestId());
        yqr.setAt(2);
        yqr.setIs_native_app(true);
        yqr.setPaymode(1);

        List<Impression> impList = new ArrayList<>();
        Impression imp = new Impression();
        imp.setId(gu.getUpstreamId());
        imp.setBidfloor(300);
        imp.setTagid(gu.getUpstreamId());//广告位id
        //imp.setTagid("D1000019");//测试广告位id
        impList.add(imp);

        Video video = new Video();
        video.setW(gaReq.getSlot().getSlotwidth());
        video.setH(gaReq.getSlot().getSlotheight());
        List<String> mimes = new ArrayList<>();
        mimes.add("video");
        mimes.add("mp4");
        video.setMimes(mimes);
        video.setMinduration(1);
        video.setMaxduration(60);
        video.setLinearity(1);
        video.setStartdelay(1);
        video.setPos(1);
        video.setVideo_type(8);
        imp.setVideo(video);
        yqr.setImp(impList);

        App app = new App();
        app.setId(gu.getUpstreamId());//media=zghd&zone_id=rwd_0001
        app.setBundle(gaReq.getApp().getAppPackage());
        app.setName(gaReq.getApp().getAppName());
        app.setVer(gaReq.getApp().getAppVersion());
        app.setPaid(0);
        yqr.setApp(app);

        Device device = new Device();
        device.setDnt("0");
        device.setUa(gaReq.getDevice().getUa());
        device.setIp(gaReq.getNetwork().getIp());
        device.setDidsmd5(MD5.md5(gaReq.getDevice().getImei()));
        device.setMacmd5(MD5.md5(gaReq.getDevice().getMac()));
        device.setMake(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setConnectiontype(0);
        device.setCarrier("中国移动");
        device.setS_density(gaReq.getDevice().getPpi());
        device.setSw(gaReq.getDevice().getScreenWidth());
        device.setSh(gaReq.getDevice().getScreenHeight());

        Geo geo = new Geo();
        geo.setLat(0.0f);
        geo.setLon(0.0f);
        device.setGeo(geo);

        Extension ext = new Extension();
        if(gaReq.getDevice().getOsType() == 1){
            ext.setAid(gaReq.getDevice().getAndroidId());
            device.setDpid(gaReq.getDevice().getAndroidId());
            device.setDpidmd5(MD5.md5(gaReq.getDevice().getAndroidId()));
            device.setOs("android");
        }else if(gaReq.getDevice().getOsType() == 2){
            ext.setAid(gaReq.getDevice().getIdfa());
            device.setDpid(gaReq.getDevice().getIdfa());
            device.setDidsmd5(MD5.md5(gaReq.getDevice().getIdfa()));
            device.setOs("iOS");
        }
        ext.setImei(gaReq.getDevice().getImei());
        ext.setMac(gaReq.getDevice().getMac());
        device.setExt(ext);
        yqr.setDevice(device);

        return JSONObject.fromObject(yqr).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(backData);
        JSONObject seatbid = JSONObject.fromObject(json.getJSONArray("seatbid").get(0));
        JSONObject adJson = JSONObject.fromObject(seatbid.getJSONArray("bid").get(0));
        JSONObject ext = adJson.getJSONObject("ext");


        //requestId
        gar.setRequestId(gaReq.getRequestId());

        //广告主体
        Ad ya = new Ad();
        if (adJson.has("crid")){
            ya.setAdKey(adJson.getString("crid"));
        }
        if (ext.has("html_snippet")){
            ya.setHtmlSnippet(ext.getString("html_snippet"));
        }
        if (ext.has("ad_icon_url")){
            ya.setAdtext(ext.getString("ad_icon_url"));
        }
        if (ext.has("ad_logo_url")){
            ya.setAdlogo(ext.getString("ad_logo_url"));
        }

        //视频内容
        MaterialMeta ym = new MaterialMeta();
        List<String> iconUrls = new ArrayList<>();
        if (ext.has("app_icon_url")){
            iconUrls.add(ext.getString("app_icon_url"));
        }
        ym.setIconUrls(iconUrls);
        List<String> descs = new ArrayList<>();
        descs.add(ext.getString("description"));
        ym.setDescs(descs);
        ym.setPackageName(ext.getString("packagename"));
        ym.setAdTitle(ext.getString("title"));
        ym.setAppSize(ext.getInt("appsize"));
        ym.setBrandName(ext.getString("appname"));
        ym.setMaterialHeight(adJson.getInt("adh"));
        ym.setMaterialWidth(adJson.getInt("adw"));
        ym.setVideoUrl(adJson.getString("adurl"));
        ym.setClickUrl(adJson.getString("curl"));
        ym.setVideoDuration(adJson.getInt("duration"));
        int interactionType = adJson.getInt("inventory_type");
        if(interactionType == 1){
            ym.setCreativeType(2);
        } else if (interactionType == 2) {
            ym.setCreativeType(3);
        } else if (interactionType == 3) {
            ym.setCreativeType(5);
        } else if (interactionType == 4) {
            ym.setCreativeType(4);
        }
        int action = ext.getInt("action");
        if(action == 1){
            ym.setInteractionType(1);
        }else if(action == 5){
            ym.setInteractionType(2);
        }else{
            ym.setInteractionType(0);
        }
        List<String> imageUrl = new ArrayList<>();
        ////尾帧
        VerifyService vs = new VerifyService();
        if (adJson.has("videoCompanion")){
            JSONObject videoCompanion = adJson.getJSONObject("videoCompanion");
            if(vs.isJson(adJson.getString("videoCompanion")) && videoCompanion.has("type")){
                String snippet_type = videoCompanion.getString("snippet_type");
                if ("img".equals(snippet_type)){
                    imageUrl.add(videoCompanion.getString("img_snippet"));
                }
                if ("html".equals(snippet_type)){
                    ya.setHtmlSnippet(videoCompanion.getString("html_snippet"));
                }
            }
        }
        ym.setImageUrl(imageUrl);
        //下载类特殊处理字段
        if (ext.has("protocolType")){
            ym.setProtocolType(ext.getInt("protocolType"));
        }

        //曝光展现
        List<String> nL = ext.getJSONArray("imptrackers");
        String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&8&3");
        nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
        ym.setWinNoticeUrls(nL);

        //点击
        List<String> cL = ext.getJSONArray("clktrackers");
        String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&8&4");
        cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
        ym.setWinCNoticeUrls(cL);

        //下载
        ym.setWinDownloadUrls(ext.getJSONArray("download_starts"));

        //下载完成
        ym.setWinDownloadEndUrls(ext.getJSONArray("download_ends"));

        //安装
        ym.setWinInstallUrls(ext.getJSONArray("install_end_tracks"));

        //安装完成
        ym.setWinInstallEndUrls(ext.getJSONArray("install_tracks"));

        //激活
        ym.setWinActiveUrls(ext.getJSONArray("download_actives"));

        //视频监控和播放上报
        List<Track> ydtTrackList = new ArrayList<>();
        boolean tracksKey = ext.has("videotrackers");
        if(tracksKey){
            JSONArray tracks = ext.getJSONArray("videotrackers");
            for(int i=0;i<tracks.size();i++){
                JSONObject track = JSONObject.fromObject(tracks.get(i));
                String type = track.getString("tracking_event_key");
                //开始
                if ("start".equals(type)) {
                    Track yt = new Track();
                    yt.setType(0);
                    yt.setUrls(track.getJSONArray("tracking_url"));
                    ydtTrackList.add(0,yt);
                }
                int ydtTrackListSize =  ydtTrackList.size();
                //1/4
                if ("firstQuartile".equals(type)) {
                    Track yt = new Track();
                    yt.setType(1);
                    yt.setUrls(track.getJSONArray("tracking_url"));
                    ydtTrackList.add(ydtTrackListSize ,yt);
                }
                //1/2
                if ("midpoint".equals(type)) {
                    Track yt = new Track();
                    yt.setType(2);
                    yt.setUrls(track.getJSONArray("tracking_url"));
                    ydtTrackList.add(ydtTrackListSize ,yt);
                }
                //3/4
                if ("thirdQuartile".equals(type)) {
                    Track yt = new Track();
                    yt.setType(3);
                    yt.setUrls(track.getJSONArray("tracking_url"));
                    ydtTrackList.add(ydtTrackListSize ,yt);
                }
                //结束
                if ("complete".equals(type)) {
                    Track yt = new Track();
                    yt.setType(4);
                    yt.setUrls(track.getJSONArray("tracking_url"));
                    ydtTrackList.add(ydtTrackListSize ,yt);
                }
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
    }

}
