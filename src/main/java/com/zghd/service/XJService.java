package com.zghd.service;

import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.zghd.entity.XianJian.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 第四范式 - 先荐
 */
@Service(value="xjService")
public class XJService {

    /**
     * 发送httppost请求
     * 1337   455908   儿歌多多  com.duoduo.child.story
     */
    public GetAdsResp XJSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String data = formatData(gaReq, gu);
        String url = "http://recsys-sspapi.4paradigm.com/common/zghd2020";
        String str = TestConnectionPool.post(url, data, null);
        gar = formatBackData(str,gaReq,gu);
        return gar;
    }


    /**
     * 封装入参参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        XJRequest request = new XJRequest();
        request.setId(gaReq.getRequestId());

        List<Imp> impList = new ArrayList<>();
        Imp imp = new Imp();
        imp.setId("0");
        imp.setTagid(gu.getUpstreamId());
        impList.add(imp);
        request.setImp(impList);

        App app = new App();
        app.setId(gu.getUpstreamAppId());
        app.setName(gu.getUpstreamAppName());
        app.setBundle(gu.getUpstreamPackageName());
        app.setPaid(false);
        app.setVer("1.1.1");
        request.setApp(app);

        Device device = new Device();
        device.setUa(gaReq.getDevice().getUa());
        device.setDnt(false);
        device.setIp(gaReq.getNetwork().getIp());
        int deviceType = gaReq.getDevice().getDeviceType();
        if (deviceType == 1){
            device.setDevicetype(4);
        }else{
            device.setDevicetype(5);
        }
        device.setMake(gaReq.getDevice().getVendor());
        device.setBrand(gaReq.getDevice().getBrand());
        device.setModel(gaReq.getDevice().getModel());
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setOs("Android");
        }else if (osType == 2){
            device.setOs("iOS");
        }else{
            device.setOs("unknown");
        }
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setH(gaReq.getDevice().getScreenHeight());
        device.setW(gaReq.getDevice().getScreenWidth());
        int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 2){
            device.setCarrier(3);
        }else if (operatorType == 3){
            device.setCarrier(2);
        }else{
            device.setCarrier(0);
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
        device.setImei(gaReq.getDevice().getImei());
        device.setOaid(gaReq.getDevice().getOaid());
        device.setAndroidid(gaReq.getDevice().getAndroidId());
        device.setMac(gaReq.getDevice().getMac());
        device.setIdfa(gaReq.getDevice().getIdfa());
        device.setImsi(gaReq.getDevice().getImsi());

        Geo geo = new Geo();
        geo.setLat(gaReq.getNetwork().getLat());
        geo.setLon(gaReq.getNetwork().getLon());
        geo.setType(1);
        device.setGeo(geo);
        request.setDevice(device);

        return JSONObject.fromObject(request).toString();
    }


    /**
     * 封装出参参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu) {
        GetAdsResp gar = new GetAdsResp();
        gar.setRequestId(gaReq.getRequestId());
        JSONObject seat = JSONObject.fromObject(JSONObject.fromObject(backData).getJSONArray("seatbid").get(0));
        if (seat.size() > 0){
            JSONObject data = JSONObject.fromObject(seat.getJSONArray("bid").get(0));

            //广告主体
            Ad ya = new Ad();

            //物料元
            MaterialMeta ym = new MaterialMeta();

            int action = data.getInt("action");

            /**
             * 开屏 插屏 banner
             */
            if (data.has("adm")){
                ym.setCreativeType(3);
                JSONObject ad = data.getJSONObject("adm");
                ym.setMaterialWidth(ad.getInt("w"));
                ym.setMaterialHeight(ad.getInt("h"));
                List<String> image = new ArrayList<>();
                image.add(ad.getString("asseturl"));
                ym.setImageUrl(image);
                if (ad.has("title")){
                    ym.setAdTitle(ad.getString("title"));
                }
                List<String> desc = new ArrayList<>();
                if (ad.has("desc")){
                    desc.add(ad.getString("desc"));
                }
                ym.setDescs(desc);

                //浏览类
                if (action == 1 || action == 2){
                    ym.setInteractionType(1);
                    ym.setClickUrl(ad.getString("landingpage"));

                //下载类
                }else if(action == 3 || action == 4){
                    ym.setInteractionType(2);
                    ym.setClickUrl(ad.getString("downloadurl"));
                    if (data.has("bundle")){
                        ym.setPackageName(data.getString("bundle"));
                    }
                    //下载类上报
                    JSONObject r = ad.getJSONObject("downloadtrackers");
                    //下载
                    ym.setWinDownloadUrls(report(r.getJSONArray("startdownload")));
                    //下载完成
                    ym.setWinDownloadEndUrls(report(r.getJSONArray("finishdownload")));
                    //安装
                    ym.setWinInstallUrls(report(r.getJSONArray("startinstall")));
                    //安装完成
                    ym.setWinInstallEndUrls(report(r.getJSONArray("finishinstall")));

                //deeplink
                }else if(action == 5){
                    ym.setInteractionType(0);
                    ym.setDeepLink(true);
                    ym.setDeepLinkUrl(ad.getString("deeplink"));
                    //deeplink上报
                    List deepLinkSuccessUrls = report(ad.getJSONArray("deeplinktrackers"));
                    String param3 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&5");
                    deepLinkSuccessUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param3);
                    ym.setWinDeepLinkSuccessUrls(deepLinkSuccessUrls);

                //下载二次跳转
                }else{
                    ym.setInteractionType(2);
                }

                //展现曝光
                List<String> winNotice = report(ad.getJSONArray("imptrackers"));
                String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&3");
                winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                ym.setWinNoticeUrls(winNotice);

                //点击
                List<String> cL = report(ad.getJSONArray("clicktrackers"));
                String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&4");
                cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                ym.setWinCNoticeUrls(cL);
            }

            /**
             * 信息流
             */
            if (data.has("admnative")){
                ym.setCreativeType(3);
                JSONObject admnative = data.getJSONObject("admnative");
                JSONObject ass = JSONObject.fromObject(admnative.getJSONArray("assets").get(0));
                if (ass.has("title")){
                    ym.setAdTitle(ass.getJSONObject("title").getString("text"));
                }
                if (ass.has("data")){
                    List<String> desc = new ArrayList<>();
                    desc.add(ass.getJSONObject("data").getString("value"));
                    ym.setDescs(desc);
                }
                if (ass.has("img")){
                    JSONObject img = ass.getJSONObject("img");
                    List<String> image = new ArrayList<>();
                    image.add(img.getString("url"));
                    ym.setImageUrl(image);
                    ym.setMaterialWidth(img.getInt("w"));
                    ym.setMaterialHeight(img.getInt("h"));

                }

                //展现曝光
                List<String> winNotice = report(admnative.getJSONArray("imptrackers"));
                String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&3");
                winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                ym.setWinNoticeUrls(winNotice);

                JSONObject link = admnative.getJSONObject("link");
                //点击
                List<String> cL = report(link.getJSONArray("clicktrackers"));
                String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&4");
                cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                ym.setWinCNoticeUrls(cL);

                int a = link.getInt("action");
                //浏览类
                if (a == 1 || a == 2){
                    ym.setInteractionType(1);
                    ym.setClickUrl(link.getString("url"));

                    //下载类
                }else if(a == 3 || a == 4){
                    ym.setInteractionType(2);
                    ym.setClickUrl(link.getString("downloadurl"));
                    if (data.has("bundle")){
                        ym.setPackageName(data.getString("bundle"));
                    }
                    //下载类上报
                    JSONObject r = link.getJSONObject("downloadtrackers");
                    //下载
                    ym.setWinDownloadUrls(report(r.getJSONArray("startdownload")));
                    //下载完成
                    ym.setWinDownloadEndUrls(report(r.getJSONArray("finishdownload")));
                    //安装
                    ym.setWinInstallUrls(report(r.getJSONArray("startinstall")));
                    //安装完成
                    ym.setWinInstallEndUrls(report(r.getJSONArray("finishinstall")));

                    //deeplink
                }else if(a == 5){
                    ym.setInteractionType(0);
                    ym.setDeepLink(true);
                    ym.setDeepLinkUrl(link.getString("deeplink"));
                    //deeplink上报
                    List deepLinkSuccessUrls = report(link.getJSONArray("deeplinktrackers"));
                    String param3 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&5");
                    deepLinkSuccessUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param3);
                    ym.setWinDeepLinkSuccessUrls(deepLinkSuccessUrls);

                    //下载二次跳转
                }else{
                    ym.setInteractionType(2);
                }
            }


            /**
             * 视频
             */
            if (data.has("admvideo")){
                ym.setCreativeType(5);
                JSONObject video = data.getJSONObject("admvideo");
                ym.setVideoUrl(video.getString("url"));
                ym.setMaterialWidth(video.getInt("w"));
                ym.setMaterialHeight(video.getInt("h"));
                ym.setVideoDuration(video.getInt("duration"));
                ym.setAdTitle(video.getString("title"));
                List<String> desc = new ArrayList<>();
                desc.add(video.getString("desc"));
                ym.setDescs(desc);

                List<String> image = new ArrayList<>();
                image.add(video.getString("icon_src"));
                ym.setImageUrl(image);
                List<String> icon = new ArrayList<>();
                icon.add(video.getString("image_src"));
                ym.setIconUrls(icon);

                //展现曝光
                List<String> winNotice = report(video.getJSONArray("imptrackers"));
                String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&3");
                winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                ym.setWinNoticeUrls(winNotice);

                //点击
                List<String> cL = report(video.getJSONArray("clicktrackers"));
                String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&4");
                cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                ym.setWinCNoticeUrls(cL);

                //浏览类
                if (action == 1 || action == 2){
                    ym.setInteractionType(1);
                    ym.setClickUrl(video.getString("landingpage"));

                    //下载类
                }else if(action == 3 || action == 4){
                    ym.setInteractionType(2);
                    ym.setClickUrl(video.getString("downloadurl"));
                    if (data.has("bundle")){
                        ym.setPackageName(data.getString("bundle"));
                    }
                    //下载类上报
                    JSONObject r = video.getJSONObject("downloadtrackers");
                    //下载
                    ym.setWinDownloadUrls(report(r.getJSONArray("startdownload")));
                    //下载完成
                    ym.setWinDownloadEndUrls(report(r.getJSONArray("finishdownload")));
                    //安装
                    ym.setWinInstallUrls(report(r.getJSONArray("startinstall")));
                    //安装完成
                    ym.setWinInstallEndUrls(report(r.getJSONArray("finishinstall")));

                    //deeplink
                }else if(action == 5){
                    ym.setInteractionType(0);
                    ym.setDeepLink(true);
                    ym.setDeepLinkUrl(video.getString("deeplink"));
                    //deeplink上报
                    List deepLinkSuccessUrls = report(video.getJSONArray("deeplinktrackers"));
                    String param3 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&25&5");
                    deepLinkSuccessUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param3);
                    ym.setWinDeepLinkSuccessUrls(deepLinkSuccessUrls);

                    //下载二次跳转
                }else{
                    ym.setInteractionType(2);
                }

                //播放事件监控
                JSONObject event = video.getJSONObject("eventTrackers");

                List<Track> ydtTrackList = new ArrayList<>();
                if (event.has("start")){
                    Track track0 = new Track();
                    track0.setType(0);
                    track0.setUrls(report(event.getJSONArray("start")));
                    ydtTrackList.add(track0);
                }

                if (event.has("firstQuartile")){
                    Track track1 = new Track();
                    track1.setType(1);
                    track1.setUrls(report(event.getJSONArray("firstQuartile")));
                    ydtTrackList.add(track1);
                }


                if (event.has("midPoint")){
                    Track track2 = new Track();
                    track2.setType(2);
                    track2.setUrls(report(event.getJSONArray("midPoint")));
                    ydtTrackList.add(track2);
                }


                if (event.has("thirdQuartile")){
                    Track track3 = new Track();
                    track3.setType(3);
                    track3.setUrls(report(event.getJSONArray("thirdQuartile")));
                    ydtTrackList.add(track3);
                }


                if (event.has("complete")){
                    Track track4 = new Track();
                    track4.setType(4);
                    track4.setUrls(report(event.getJSONArray("complete")));
                    ydtTrackList.add(track4);
                }

                ya.setTracks(ydtTrackList);

            }
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

    //格式化上报地址
    public List<String> report(List<JSONObject> list){
        List<String> report = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            JSONObject l = list.get(i);
            report.add(l.getString("url"));
        }
        return report;
    }
}
