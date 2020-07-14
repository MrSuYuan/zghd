package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.WanKa.*;
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
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * 万咖智星激励视频
 */
@Service(value="wkService")
public class WKService {

    /**
     * 万咖请求
     */
    public GetAdsResp WKSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        String channel_id;
        String token;
        String uri;
        String host;
        if("1093".equals(gu.getUpstreamAppId())){
            //测试版
            channel_id = "1";//渠道id
            token = "945471662e93b6a62222a794a080a378";
            uri = "http://test.sspapi.gm825.net/api";//访问地址
            host = "test.sspapi.gm825.net";//host
        }else{
            //正式版
            channel_id = "71";//渠道id
            token = "4qSUSSEQQXx2EZwSyR5mJE36eO9LNk36";
            uri = "http://sspapi.gm825.com/api";//访问地址
            host = "sspapi.gm825.com";//host
        }

        GetAdsResp gar;
        //将下游参数都转为万咖的参数
        String data = formatData(gaReq, gu);
        WanKa req = new WanKa();
        //转换参数
        Reqjson rj = JSON.parseObject(data, Reqjson.class);
        Date date = new Date();
        JSONObject json = JSONObject.fromObject(rj);
        //生成签名
        String signature = generateSignature(json.toString(),date.getTime()+"",token, host);
        //封装参数
        req.setChannel_id(channel_id);
        req.setTimestamp(date.getTime());
        req.setSignature(signature);
        req.setReqjson(json.toString());
        //准备发送请求
        HttpPost post = new HttpPost(uri);
        post.addHeader("Content-Type", "application/json;utf-8");
        post.addHeader("Accept-Encoding", "gzip");
        //格式化参数
        JSONObject reqjson = JSONObject.fromObject(req);
        ByteArrayOutputStream baos = formatGzip(reqjson.toString());
        post.setEntity(new ByteArrayEntity(baos.toByteArray()));
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = closeableHttpClient.execute(post);
        HttpEntity responseEntity = httpResponse.getEntity();

        String entityStr = EntityUtils.toString(responseEntity, "utf-8");
        String errorCode = JSONObject.fromObject(entityStr).getString("error_code");
        if("0".equals(errorCode)){
            //格式化返回数据,放到流里面返回给下游
            gar = formatBackData(entityStr, gaReq, gu);
            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");

        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

    /**
     * 参数转换--将下游参数全部转为万咖参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){

        Reqjson rj = new Reqjson();
		rj.setApi_version("3.0.0");//固定

		//App
		App app = new App();
		app.setApp_id(gu.getUpstreamAppId());
		//万咖格式a.b.c
		app.setApp_version("1.1.1");
		app.setPackage_name(gaReq.getApp().getAppPackage());
		rj.setApp(app);

		//Adslot
        Adslot ad = new Adslot();
		ad.setAdslot_id(gu.getUpstreamId());
		ad.setRender_type(0);//0任意形式,设默认
		ad.setDeeplink(1);//支持下载,设默认
		rj.setAdslot(ad);

		//Device
        Device device = new Device();
		device.setDevice_type(gaReq.getDevice().getDeviceType());
		int os_type = gaReq.getDevice().getOsType();
		if(os_type == 1){
            device.setOs_type("android");
        }else{
            device.setOs_type("ios");
        }
		device.setOs_version(gaReq.getDevice().getOsVersion());
		device.setVendor(gaReq.getDevice().getVendor());
		device.setModel(gaReq.getDevice().getModel());
		device.setAndroid_id(gaReq.getDevice().getAndroidId());
		device.setAndroid_id_md5(MD5.md5(gaReq.getDevice().getAndroidId()));
		device.setImei(gaReq.getDevice().getImei());
		device.setImei_md5(MD5.md5(gaReq.getDevice().getImei()));
		device.setW(gaReq.getDevice().getScreenWidth());
		device.setH(gaReq.getDevice().getScreenHeight());
		device.setMac(gaReq.getDevice().getMac());
		device.setIdfa(gaReq.getDevice().getIdfa());
		device.setUa(gaReq.getDevice().getUa());
		device.setDpi(gaReq.getDevice().getPpi());
		device.setImsi(gaReq.getDevice().getImsi());
		device.setIdfv("");
        //非必填
        device.setResolution("1920*1080");
		rj.setDevice(device);

		//基站信息
        Network nw = new Network();
		nw.setIp(gaReq.getNetwork().getIp());
		//万咖类型 网络连接类型 0：未知连接，1：以太网，2：WiFi， 3：未知蜂窝网络，4:2G，5:3G，6:4G
		//下游类型 0--CONNECTION_UNKNOWN 1--CELL_UNKNOWN 2--CELL_2G 3--CELL_3G 4--CELL_4G 5--CELL_5G 100—WIFI 101—ETHERNET 999--NEW_TYPE
        int connectionType = gaReq.getNetwork().getConnectionType();
        if(connectionType == 2){
            nw.setConnect_type(4);
        }else if(connectionType == 3){
            nw.setConnect_type(5);
        }else if(connectionType == 4){
            nw.setConnect_type(6);
        }else if(connectionType == 100){
            nw.setConnect_type(2);
        }else{
            nw.setConnect_type(0);
        }
        //运营商类型 0：未知运营商，1：中国移动，2：中国电信，3：中国联通，4：其他运营商
        //0--UNKNOWN_OPERATOR 1--CHINA_MOBILE 2--CHINA_TELECOM 3--CHINA_UNICOM 99--OTHER_OPERATOR
        int operatorType = gaReq.getNetwork().getOperatorType();
        if(operatorType == 0){
            nw.setCarrier(0);
        }else if(operatorType == 1){
            nw.setCarrier(1);
        }else if(operatorType == 2){
            nw.setCarrier(2);
        }else if(operatorType == 3){
            nw.setCarrier(3);
        }else{
            nw.setCarrier(0);
        }
		nw.setCellular_id(gaReq.getNetwork().getCellular_id());
		rj.setNetwork(nw);

		//Gps
		Gps gps = new Gps();
		gps.setCoordinate_type(4);
		gps.setLon(0);
		gps.setLat(0);
		gps.setLocation_accuracy(0);
		gps.setCoord_time(new Date().getTime());
		rj.setGps(gps);
        return JSONObject.fromObject(rj).toString();
    }

    /**
     * 万咖生成签名
     */
    public String generateSignature(String reqjson, String timestamp, String token, String host) throws Exception {
        String method = "POST";
        String path = "/api";
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(method).append("\n");
        tokenBuilder.append(host).append("\n");
        tokenBuilder.append(path).append("\n");
        tokenBuilder.append("reqjson=").append(java.net.URLEncoder.encode(reqjson,"utf-8")).append("&");
        tokenBuilder.append("token=").append(token);
        tokenBuilder.append("&").append("timestamp=").append(timestamp);
        java.security.MessageDigest mDigest = java.security.MessageDigest.getInstance("MD5");
        String signature = org.apache.commons.codec.binary.Hex.encodeHexString(mDigest.digest(tokenBuilder.toString().getBytes("utf-8")));
        return signature;
    }

    /**
     * 万咖转换参数格式 gzip
     */
    public ByteArrayOutputStream formatGzip(String reqjson) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gos = null;
        try {
            gos = new GZIPOutputStream(baos);
            gos.write(reqjson.getBytes("UTF-8"));
            gos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(gos !=null) {
                gos.close();
            }
            if(baos !=null) {
                baos.close();
            }
        }
        return baos;
    }

    /**
     * 格式化万咖返回数据
     */
    public GetAdsResp formatBackData(String entityStr, GetAdsReq gaReq, GetUpstream gu){
        //返回整体
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(entityStr);
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        //广告主体
        Ad ya = new Ad();
        String adms = JSONArray.fromObject(json.getString("adms")).get(0).toString();
        JSONObject adm = JSONObject.fromObject(adms);

        //视频内容
        MaterialMeta ym = new MaterialMeta();
        JSONObject video = JSONObject.fromObject(adm.getString("rewardVideoVO"));
        ym.setAdTitle(video.getString("title"));
        ym.setVideoUrl(video.getString("videoUrl"));
        ym.setMaterialWidth(Integer.valueOf(video.getString("videoWidth")));
        ym.setMaterialHeight(Integer.valueOf(video.getString("videoHeight")));
        ym.setPackageName(adm.getString("bundle"));
        ym.setClickUrl(adm.getString("clkurl"));
        String interaction_type = adm.getString("interaction_type");

        if ("1".equals(interaction_type)){
            ym.setInteractionType(1);
        }else if ("2".equals(interaction_type)){
            ym.setInteractionType(2);
        }else{
            ym.setInteractionType(0);
        }
        ym.setVideoDuration(Integer.valueOf(video.getString("videoDuration")));
        List<String> endCardUrl = new ArrayList<>();
        endCardUrl.add(video.getString("endCardUrl"));
        ym.setImageUrl(endCardUrl);
        List<String> subtitle = new ArrayList<>();
        subtitle.add(video.getString("subtitle"));
        ym.setDescs(subtitle);

        //监控
        JSONObject reportVO = JSONObject.fromObject(adm.getString("reportVO"));

        //视频加载完成
        List<String> winLoadUrls = JSONObject.fromObject(video.getString("playTrackers")).getJSONArray("loadedTracker");
        ym.setWinLoadUrls(winLoadUrls);

        //曝光展现
        List<String> winNoticeUrls = new ArrayList<>();
        Object imptrackers = reportVO.get("imptrackers");
        if("null".equals(imptrackers.toString())) {
        }else{
            JSONArray imptrackersList = reportVO.getJSONArray("imptrackers");
            for(int i=0;i<imptrackersList.size();i++){
                JSONObject j = JSONObject.fromObject(imptrackersList.get(i));
                winNoticeUrls.add(i,j.getString("url"));
            }
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-2-3");
            winNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(winNoticeUrls);
        }

        //点击
        Object clktrackers = reportVO.get("clktrackers");
        if("null".equals(clktrackers.toString())) {
        }else{
            JSONArray clktrackersList = reportVO.getJSONArray("clktrackers");
            List<String> winCNoticeUrls = new ArrayList<>();
            for(int i=0;i<clktrackersList.size();i++){
                JSONObject j = JSONObject.fromObject(clktrackersList.get(i));
                winCNoticeUrls.add(i,j.getString("url"));
            }
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-2-4");
            winCNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(winCNoticeUrls);
        }

        //下载
        Object dwnlsts = reportVO.get("dwnlsts");
        if("null".equals(dwnlsts.toString())) {
        }else{
            JSONArray dwnlstsList = reportVO.getJSONArray("dwnlsts");
            List<String> winDownloadUrls = new ArrayList<>();
            for(int i=0;i<dwnlstsList.size();i++){
                JSONObject j = JSONObject.fromObject(dwnlstsList.get(i));
                winDownloadUrls.add(i,j.getString("url"));
            }
            ym.setWinDownloadUrls(winDownloadUrls);
        }

        //下载完成
        Object dwnltrackers = reportVO.get("dwnltrackers");
        if("null".equals(dwnltrackers.toString())) {
        }else{
            JSONArray dwnltrackersList = reportVO.getJSONArray("dwnltrackers");
            List<String> winDownloadEndUrls = new ArrayList<>();
            for(int i=0;i<dwnltrackersList.size();i++){
                JSONObject j = JSONObject.fromObject(dwnltrackersList.get(i));
                winDownloadEndUrls.add(i,j.getString("url"));
            }
            ym.setWinDownloadEndUrls(winDownloadEndUrls);
        }

        //安装

        Object intltrackers = reportVO.get("intltrackers");
        if("null".equals(intltrackers.toString())) {
        }else{
            JSONArray intltrackersList = reportVO.getJSONArray("intltrackers");
            List<String> winInstallUrls = new ArrayList<>();
            for(int i=0;i<intltrackersList.size();i++){
                JSONObject j = JSONObject.fromObject(intltrackersList.get(i));
                winInstallUrls.add(i,j.getString("url"));
            }
            ym.setWinInstallUrls(winInstallUrls);
        }

        //激活

        Object actvtrackers = reportVO.get("actvtrackers");
        if("null".equals(actvtrackers.toString())) {
        }else{
            JSONArray actvtrackersList = reportVO.getJSONArray("actvtrackers");
            List<String> winActiveUrls = new ArrayList<>();
            for(int i=0;i<actvtrackersList.size();i++){
                JSONObject j = JSONObject.fromObject(actvtrackersList.get(i));
                winActiveUrls.add(i,j.getString("url"));
            }
            ym.setWinActiveUrls(winActiveUrls);
        }

        //视频播放上报
        List<Track> ydtTrackList = new ArrayList<>();
        JSONObject pt = JSONObject.fromObject(video.getString("playTrackers"));
        JSONArray playTrackersList = JSONArray.fromObject(pt.getString("playCentageTrackers"));
        for(int i=0;i<playTrackersList.size();i++){
            JSONObject jb = (JSONObject) playTrackersList.get(i);
            Track yt = new Track();
            double d = jb.getDouble("checkpoint");
            if(d == 0){
                yt.setType(0);
            }else if(d == 0.25){
                yt.setType(1);
            }else if(d == 0.5){
                yt.setType(2);
            }else if(d == 0.75){
                yt.setType(3);
            }else if(d == 1){
                yt.setType(4);
            }
            yt.setUrls(JSONArray.fromObject(jb.getString("urls")));
            ydtTrackList.add(i,yt);
        }
        ya.setTracks(ydtTrackList);

        //综合封装返回
        List ymList = new ArrayList();
        ymList.add(ym);
        ya.setMetaGroup(ymList);
        List yaList = new ArrayList();
        yaList.add(ya);
        gar.setAds(yaList);
        return gar;
    }



}
