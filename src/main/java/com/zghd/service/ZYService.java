package com.zghd.service;

import com.util.md5.EncryptUtil;
import com.util.md5.MD5;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.ZhiYou.*;
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
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微播智友广告
 */
@Service(value="zyService")
public class ZYService {

    /**
     * 向后台发请求
     */
    public GetAdsResp  ZYSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String url;
        //测试服
        if ("test1030".equals(gaReq.getSlot().getSlotId())){
            url = "http://stage-adx.8bcd9.com/bid/v6/rf9mfi5";
        }else{
            url = "http://adx.8bcd9.com/bid/v6/rrtyagh";
        }
        //参数转换
        String data = formatData(gaReq,gu);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept-Encoding","gzip");
        httpPost.addHeader("X-Forwarded-For",gaReq.getNetwork().getIp());
        httpPost.addHeader("User-Agent",gaReq.getDevice().getUa());
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpclient.execute(httpPost);
        int code = resp.getStatusLine().getStatusCode();
        if(code == 200) {
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
        BidRequest request = new BidRequest();
        request.setId(MD5.md5("1234"));
        request.setVersion("1.1.6");
        request.setAt(2);
        request.setTest(0);

        List<Imp> impList = new ArrayList<>();
        Imp imp = new Imp();
        imp.setId("1");
        imp.setAw(gaReq.getSlot().getSlotwidth());
        imp.setAh(gaReq.getSlot().getSlotheight());
        if ("test1030".equals(gaReq.getSlot().getSlotId())){
            imp.setTagid("rgrtdho");
        }else{
            imp.setTagid(gu.getUpstreamId());
        }
        imp.setBidfloor(0f);
        Video video = new Video();
        video.setW(gaReq.getDevice().getScreenWidth());
        video.setH(gaReq.getDevice().getScreenHeight());
        //1原生视频 2激励视频
        video.setType(2);
        video.setMinduration(10);
        video.setMaxduration(30);
        //0前贴 1中贴 2后贴
        video.setStartdelay(2);
        //1在视频流中展示 2在视频内容上悬浮展示
        video.setLinearity(2);
        video.setPos(0);
        List<String> mimes = new ArrayList<>();
        mimes.add("video/mp4");
        video.setMimes(mimes);
        imp.setVideo(video);
        List mts = new ArrayList();
        mts.add("MVB");
        imp.setMst(mts);
        impList.add(imp);
        request.setImp(impList);

        App app = new App();
        if ("test1030".equals(gaReq.getSlot().getSlotId())){
            app.setId("rf9ml8f");
        }else{
            app.setId(gu.getUpstreamAppId());
        }
        app.setName(gaReq.getApp().getAppName());
        app.setBundle(gu.getUpstreamPackageName());
        app.setVer(gaReq.getApp().getAppVersion());
        //是否是付费应用 1是 0否(默认0)
        app.setPaid(0);
        request.setApp(app);

        Device device = new Device();
        device.setUa(gaReq.getDevice().getUa());
        Geo geo = new Geo();
        geo.setLat((float)gaReq.getNetwork().getLat());
        geo.setLon((float)gaReq.getNetwork().getLon());
        device.setGeo(geo);
        device.setIp(gaReq.getNetwork().getIp());
        device.setIpv6(null);
        int deviceType = gaReq.getDevice().getDeviceType();
        if (deviceType == 1){
            device.setDevicetype(4);
        }else if (deviceType == 2){
            device.setDevicetype(5);
        }else{
            device.setDevicetype(0);
        }
        device.setMake(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        device.setH(gaReq.getDevice().getScreenHeight());
        device.setW(gaReq.getDevice().getScreenWidth());
        device.setSw(gaReq.getDevice().getScreenWidth());
        device.setSh(gaReq.getDevice().getScreenHeight());
        device.setPpi(gaReq.getDevice().getPpi());
        device.setDpi(gaReq.getDevice().getPpi());
        int os = gaReq.getDevice().getOsType();
        //安卓
        if (os == 1){
            device.setOs("android");
            device.setDpid(gaReq.getDevice().getAndroidId());
            device.setDpidmd5(gaReq.getDevice().getAndroidId());
            device.setDpidsha1(gaReq.getDevice().getAndroidId());
            device.setDid(gaReq.getDevice().getImei());
            device.setDidmd5(MD5.md5(gaReq.getDevice().getImei()));
            device.setDidsha1(MD5.sha1(gaReq.getDevice().getImei()));
            device.setOaId(gaReq.getDevice().getAndroidId());
            device.setOaIdmd5(MD5.md5(gaReq.getDevice().getAndroidId()));
            device.setOaIdsha1(MD5.sha1(gaReq.getDevice().getAndroidId()));
        //ios
        }else{
            device.setOs("ios");
            device.setIfa("");
            device.setIfamd5("");
            device.setIfasha1("");
            device.setIfv("");
            device.setIfvmd5("");
            device.setIfvsha1("");
            device.setUdid("");
            device.setUdidmd5("");
            device.setUdidsha1("");
        }
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setMac(gaReq.getDevice().getMac());
        device.setMacmd5(MD5.md5(gaReq.getDevice().getMac()));
        device.setMacsha1(MD5.sha1(gaReq.getDevice().getMac()));
        int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 1){
            device.setCarrier("46000");
        }else if (operatorType == 2){
            device.setCarrier("46003");
        }else if (operatorType == 3){
            device.setCarrier("46001");
        }

        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            device.setConnectiontype(4);
        }else if (connectionType == 3){
            device.setConnectiontype(5);
        }else if (connectionType == 4){
            device.setConnectiontype(6);
        }else if (connectionType == 5){
            device.setConnectiontype(7);
        }else if (connectionType == 100){
            device.setConnectiontype(2);
        }else{
            device.setConnectiontype(0);
        }
        device.setImsi(gaReq.getDevice().getImsi());
        int screenOrientation = gaReq.getDevice().getScreenOrientation();
        if (screenOrientation == 1){
            device.setOrientation(1);
        }else{
            device.setOrientation(0);
        }
        List<String> apps = new ArrayList<>();
        device.setApps(apps);
        request.setDevice(device);

        Ext ext = new Ext();
        ext.setRdt(-1);
        ext.setHttps(-1);
        ext.setDownload(1);
        ext.setDeepLink(1);
        ext.setAdmt(2);
        ext.setVech(2);
        ext.setVecv(2);
        request.setExt(ext);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        JSONObject back = JSONObject.fromObject(backData);
        String code = back.getString("code");
        if ("10000".equals(code)){
            JSONArray jsonArray = back.getJSONArray("seatbid");
            JSONObject seatbid = JSONObject.fromObject(jsonArray.get(0));
            JSONObject ad = JSONObject.fromObject(seatbid.getJSONArray("bid").get(0));

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(back.getString("bidid"));
            //ya.setAdtext();
            //ya.setAdlogo();
            //deeplink
            ad.getString("deepLink");
            //包名
            ad.getString("bundle");

            //下载类app信息
            JSONObject app = ad.getJSONObject("app");
            //视频信息
            JSONObject video = ad.getJSONObject("video");
            //尾帧信息
            JSONObject card = video.getJSONObject("card");
            //1图片链接 2网页 4代码
            int cardType = card.getInt("type");
            if (cardType == 4){
                ya.setHtmlSnippet(card.getString("html"));
            }

            //物料元
            MaterialMeta ym = new MaterialMeta();
            List<String> icon = new ArrayList<>();
            //广告类型 1浏览类 2下载类
            String actionType = ad.getString("actionType");
            //浏览类
            if ("1".equals(actionType)){
                ym.setInteractionType(1);
                icon.add(card.getString("icon"));
                ya.setHtmlSnippet(ad.getString("adm"));
            //下载类
            }else if ("2".equals(actionType)){
                ym.setInteractionType(2);
                ym.setBrandName(app.getString("name"));
                ym.setPackageName(app.getString("pack"));
                ym.setAppSize(app.getInt("size")/1000);
                icon.add(app.getString("icon"));
                //下载类特殊处理字段
                String demand = ad.getString("demand");
                if ("GDT".equals(demand)){
                    ym.setProtocolType(1);
                }
            }
            ym.setIconUrls(icon);
            ym.setAdTitle(card.getString("title"));
            List<String> descs = new ArrayList<>();
            descs.add(card.getString("content"));
            ym.setDescs(descs);
            List<String> image = new ArrayList<>();
            image.add(card.getString("url"));
            ym.setImageUrl(image);
            ym.setMaterialWidth(video.getInt("w"));
            ym.setMaterialHeight(video.getInt("h"));
            ym.setClickUrl(ad.getString("target"));
            ym.setVideoUrl(video.getString("iurl"));
            ym.setVideoDuration(video.getInt("duration"));
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            ym.setCreativeType(5);

            //上报监控
            JSONObject events = ad.getJSONObject("events");
            EncryptUtil eu = new EncryptUtil();
            //展现曝光
            List<String> winNotice = macroParam(events.getJSONArray("els"));
            String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&17&3","zghd");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(winNotice);
            //点击
            List<String> clk = macroParam(events.getJSONArray("cls"));
            String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&17&4","zghd");
            clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(clk);
            //下载
            ym.setWinDownloadUrls(macroParam(events.getJSONArray("sdls")));
            //下载完成
            ym.setWinDownloadEndUrls(macroParam(events.getJSONArray("edls")));
            //安装
            ym.setWinInstallUrls(macroParam(events.getJSONArray("sils")));
            //安装完成
            ym.setWinInstallEndUrls(macroParam(events.getJSONArray("eils")));
            //激活
            ym.setWinActiveUrls(macroParam(events.getJSONArray("ials")));
            //关闭
            ym.setWinCloseUrls(macroParam(events.getJSONArray("cpls")));
            //跳过
            ym.setWinIgnoreUrls(macroParam(events.getJSONArray("skls")));

             //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            //103-静音播放
            Track track103 = new Track();
            track103.setType(103);
            track103.setUrls(macroParam(events.getJSONArray("mpls")));
            ydtTrackList.add(track103);
            //107-视频暂停播放
            Track track107 = new Track();
            track107.setType(107);
            track107.setUrls(macroParam(events.getJSONArray("ppls")));
            ydtTrackList.add(track107);
            //108-视频暂停后继续播放
            Track track108 = new Track();
            track108.setType(108);
            track108.setUrls(macroParam(events.getJSONArray("gpls")));
            ydtTrackList.add(track108);
            //0—播放进度0%(开始播放)
            Track track0 = new Track();
            track0.setType(0);
            track0.setUrls(macroParam(events.getJSONArray("spls")));
            ydtTrackList.add(track0);
            //4—播放进度100%(播放结束)
            Track track4 = new Track();
            track4.setType(4);
            track4.setUrls(macroParam(events.getJSONArray("epls")));
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
        }else if ("10001".equals(code)){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

    //宏替换
    public List<String> macroParam(List<String> list){
        List returnList = new ArrayList();
        for (int i = 0; i < list.size(); i++){
            String s = list.get(i);

            //替换__AZCX__
            Pattern pazcx = Pattern.compile("__AZCX__");
            Matcher mazcx = pazcx.matcher(s);
            s = mazcx.replaceAll("__UP_X__");

            //替换__AZCY__
            Pattern pazcy = Pattern.compile("__AZCY__");
            Matcher mazcy = pazcy.matcher(s);
            s = mazcy.replaceAll("__UP_Y__");

            //替换__AZMX__
            Pattern pazmx = Pattern.compile("__AZMX__");
            Matcher mazmx = pazmx.matcher(s);
            s = mazmx.replaceAll("__DOWN_X__");

            //替换__AZMY__
            Pattern pazmy = Pattern.compile("__AZMY__");
            Matcher mazmy = pazmy.matcher(s);
            s = mazmy.replaceAll("__DOWN_Y__");

            //替换__DSMX__
            Pattern pdsmx = Pattern.compile("__DSMX__");
            Matcher mdsmx = pdsmx.matcher(s);
            s = mdsmx.replaceAll("__DOWN_X__");

            //替换__DSMY__
            Pattern pdsmy = Pattern.compile("__DSMY__");
            Matcher mdsmy = pdsmy.matcher(s);
            s = mdsmy.replaceAll("__DOWN_Y__");

            //替换__DSCX__
            Pattern pdscx = Pattern.compile("__DSCX__");
            Matcher mdscx = pdscx.matcher(s);
            s = mdscx.replaceAll("__UP_X__");

            //替换__DSCY__
            Pattern pdscy = Pattern.compile("__DSCY__");
            Matcher mdscy = pdscy.matcher(s);
            s = mazcx.replaceAll("__UP_Y__");

            //替换__AMSW__
            Pattern pamsw = Pattern.compile("__AMSW__");
            Matcher mamsw = pamsw.matcher(s);
            s = mamsw.replaceAll("__WIDTH__");

            //替换__AMSH__
            Pattern pamsh = Pattern.compile("__AMSH__");
            Matcher mamsh = pamsh.matcher(s);
            s = mamsh.replaceAll("__HEIGHT__");

            //替换__STS__
            Pattern psts = Pattern.compile("__STS__");
            Matcher msts = psts.matcher(s);
            s = msts.replaceAll("__EVENT_TIME_START__");

            //替换__ETS__
            Pattern pets = Pattern.compile("__ETS__");
            Matcher mets = pets.matcher(s);
            s = mets.replaceAll("__EVENT_TIME_END__");

            //替换__TS__
            Pattern pts = Pattern.compile("__TS__");
            Matcher mts = pts.matcher(s);
            s = mts.replaceAll("__EVENT_TIME_START__");

            //替换__VD__
            Pattern pvd = Pattern.compile("__VD__");
            Matcher mvd = pvd.matcher(s);
            s = mvd.replaceAll("__VIDEO_TIME__");

            //替换__AZCTS__
            //替换__AZMTS__
            //替换__AZCTS__

            returnList.add(i, s);
        }
        return returnList;
    }

}
