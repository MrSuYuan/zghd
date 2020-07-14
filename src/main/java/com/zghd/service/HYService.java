package com.zghd.service;

import com.util.md5.JiaMi;
import com.zghd.entity.HongYi.*;
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
 * 虹益
 */
@Service(value="hyService")
public class HYService {

    /**
     * 向后台发请求
     */
    public GetAdsResp  HYSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String url = "http://api.hyrainbow.com/api/v1";
        //参数转换
        String data = formatData(gaReq,gu);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpclient.execute(httpPost);
        int code = resp.getStatusLine().getStatusCode();
        if(code == 200){
            HttpEntity result = resp.getEntity();
            String str = EntityUtils.toString(result, "utf-8");
            //格式化返回数据,放到流里面返回给下游
            gar = formatBackData(str, gaReq, gu);

        }else if (code == 204){
            gar = new GetAdsResp();
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");

        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) {
        HYRequest request = new HYRequest();

        Media media = new Media();
        media.setType(1);
        App app = new App();
        app.setPackage_name(gaReq.getApp().getAppPackage());
        app.setApp_version(gaReq.getApp().getAppVersion());
        media.setApp(app);
        Browser browser = new Browser();
        browser.setUser_agent(gaReq.getDevice().getUa());
        media.setBrowser(browser);
        request.setMedia(media);

        Device device = new Device();
        device.setId_idfa(gaReq.getDevice().getIdfa());
        device.setId_imei(gaReq.getDevice().getImei());
        device.setId_imsi(gaReq.getDevice().getImsi());
        device.setId_androidid(gaReq.getDevice().getAndroidId());
        device.setId_mac(gaReq.getDevice().getMac());
        device.setHeight(gaReq.getDevice().getScreenHeight());
        device.setWidth(gaReq.getDevice().getScreenWidth());
        device.setBrand(gaReq.getDevice().getBrand());
        device.setModel(gaReq.getDevice().getModel());
        //这个需要特殊处理 a.b.b
        device.setOs_version(gaReq.getDevice().getOsVersion());
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setOs_type(1);
        }else if (osType == 2){
            device.setOs_type(2);
        }
        request.setDevice(device);

        Network network = new Network();
        network.setIp(gaReq.getNetwork().getIp());
        network.setType(2);
        request.setNetwork(network);

        Client client = new Client();
        client.setType(3);
        client.setVersion("1.6.11");
        request.setClient(client);

        Adslot adslot = new Adslot();
        adslot.setId(gu.getUpstreamId());
        adslot.setType(1);
        adslot.setHeight(gaReq.getSlot().getSlotheight());
        adslot.setWidth(gaReq.getSlot().getSlotwidth());
        request.setAdslot(adslot);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(backData);
        JSONObject ads = json.getJSONObject("ads");
        //0html 1原生广告物料
        ads.getInt("material_type");

        //原生物料
        JSONObject material = ads.getJSONObject("native_material");
        material.getString("image_snippet");//type=267广告物料取这个字段
        material.getString("text_icon_snippet");//type=135广告物料取这个字段
        //包含下载类信息
        JSONObject ext = material.getJSONObject("ext");
        JSONObject video = material.getJSONObject("video_snippet");//type=4广告物料取这个字段

         //广告主体
        Ad ya = new Ad();
        ya.setSlotId(gaReq.getSlot().getSlotId());
        ya.setAdKey(json.getString("search_id"));
        ya.setHtmlSnippet(ads.getString("html_snippet"));

        //物料元
        MaterialMeta ym = new MaterialMeta();
        //1、图文  2、图片  3、组图  4、视频  5、互动  6、开屏  7、横幅
        int type = material.getInt("type");
        if (type == 1){
            ym.setCreativeType(3);
        }else if (type == 2 || type == 3){
            ym.setCreativeType(2);
        }else{
            ym.setCreativeType(4);
        }
        //广告交互类型  0. NO_INTERACT 1. SURFING(打开网页) 2. DOWNLOAD(下载)
        int interaction_type = material.getInt("interaction_type");
        if (interaction_type == 1){
            ym.setInteractionType(1);
        }else if (interaction_type == 2){
            ym.setInteractionType(2);
        }else{
            ym.setInteractionType(0);
        }
        ym.setAdTitle(video.getString("title"));
        List image = new ArrayList();
        image.add(video.getString("logo_url"));
        ym.setImageUrl(image);
        ym.setVideoUrl(video.getString("url"));
        List icon = new ArrayList();
        icon.add(video.getString("video_logo"));
        ym.setIconUrls(icon);
        List desc = new ArrayList();
        desc.add(video.getString("desc"));
        ym.setDescs(desc);
        ym.setClickUrl(video.getString("c_url"));
        ym.setMaterialWidth(video.getInt("width"));
        ym.setMaterialHeight(video.getInt("height"));
        ym.setVideoDuration(video.getInt("duration"));

        //下载类app信息
        if (null != ext && !"".equals(ext)){
            if (ext.has("apppackage")){
                ym.setPackageName(ext.getString("apppackage"));
            }
            if (ext.has("appname")){
                ym.setBrandName(ext.getString("appname"));
            }
        }

        //曝光展现
        List<String> nL = video.getJSONArray("imp");
        String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-13-3");
        nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
        ym.setWinNoticeUrls(nL);

        //点击
        List<String> cL = video.getJSONArray("clk");
        String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-13-4");
        cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
        ym.setWinCNoticeUrls(cL);

        //下载
        if (video.has("download_begin_monitor")){
            ym.setWinDownloadUrls(video.getJSONArray("download_begin_monitor"));
        }
        //下载完成
        if (video.has("download_end_monitor")){
            ym.setWinDownloadEndUrls(video.getJSONArray("download_end_monitor"));
        }
        //安装
        if (video.has("installation_begin_monitor")){
            ym.setWinInstallUrls(video.getJSONArray("installation_begin_monitor"));
        }
        //安装完成
        if (video.has("installation_end_monitor")){
            ym.setWinInstallEndUrls(video.getJSONArray("installation_end_monitor"));
        }

        //视频进度监控
        List<Track> ydtTrackList = new ArrayList<>();
        int duration = video.getInt("duration");
        List<JSONObject> videoImps = video.getJSONArray("video_imp");//循环
        for (int i = 0; i < videoImps.size(); i++){
            JSONObject videoImp = videoImps.get(i);
            int time = videoImp.getInt("time");
            //开始
            if (time == 1){
                Track track = new Track();
                track.setType(0);
                track.setUrls(videoImp.getJSONArray("imp_url"));
                ydtTrackList.add(track);
            }
            //结束
            if (time == duration){
                Track track = new Track();
                track.setType(4);
                track.setUrls(videoImp.getJSONArray("imp_url"));
                ydtTrackList.add(track);
            }
        }
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

        return gar;
    }

}
