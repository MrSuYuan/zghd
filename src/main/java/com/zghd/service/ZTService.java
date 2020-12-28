package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.ZhongTi.request.Adslot;
import com.zghd.entity.ZhongTi.request.App;
import com.zghd.entity.ZhongTi.request.Device;
import com.zghd.entity.ZhongTi.request.ZTRequest;
import com.zghd.entity.ZhongTi.response.*;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service(value="ztService")
public class ZTService {

    /**
     * 向中体互联发送请求
     */
    public GetAdsResp ZTSend(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        //入参参数
        String data = formatData(gaReq, gu);
        //请求
        String url = "http://ssp.kid8.com/api/useful";
        String str = TestConnectionPool.post(url, data,null);
        //回参参数
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }


    /**
     * 格式化参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){
        //请求信息
        ZTRequest request = new ZTRequest();
        request.setToken(gu.getUpstreamAppId());

        App app = new App();
        app.setApp_package_name(gu.getUpstreamPackageName());
        app.setApp_name(gu.getUpstreamAppName());
        app.setApp_version(gaReq.getApp().getAppVersion());
        request.setApp(app);

        Adslot adslot = new Adslot();
        adslot.setAdslot_id(gu.getUpstreamId());
        adslot.setSize_width(gaReq.getSlot().getSlotwidth()+"");
        adslot.setSize_height(gaReq.getSlot().getSlotheight()+"");
        request.setAdslot(adslot);

        Device device = new Device();
        device.setDevice_type(1);
        device.setOs(gaReq.getDevice().getOsType());
        device.setOs_version(gaReq.getDevice().getOsVersion());
        device.setScreen_size_width(gaReq.getDevice().getScreenWidth());
        device.setScreen_size_height(gaReq.getDevice().getScreenHeight());
        device.setIdfa(gaReq.getDevice().getIdfa());
        device.setImei(gaReq.getDevice().getImei());
        device.setAndroid_id(gaReq.getDevice().getAndroidId());
        device.setUa(gaReq.getDevice().getUa());
        device.setMac(gaReq.getDevice().getMac());
        device.setVendor(gaReq.getDevice().getVendor());
        device.setImsi(gaReq.getDevice().getImsi());
        device.setIp(gaReq.getNetwork().getIp());
        int connection_type = gaReq.getNetwork().getConnectionType();
        if (connection_type == 98){
            device.setConnection_type(100);
        }else{
            device.setConnection_type(gaReq.getNetwork().getConnectionType());
        }
        device.setOperator_type(gaReq.getNetwork().getOperatorType());
        device.setCoordinate_type(1);
        device.setLongitude(gaReq.getNetwork().getLon());
        device.setLatitude(gaReq.getNetwork().getLat());
        device.setOaid(gaReq.getDevice().getOaid());
        device.setScreen_orientation(0);
        device.setRomVersion("");
        request.setDevice(device);

        return JSONObject.fromObject(request).toString();
    }


    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        ZTResponse resp = JSON.parseObject(backData,ZTResponse.class);
        int code = resp.getError_code();
        //成功
        if (code == 0){
            ZTAds ztAds = resp.getAds().get(0);
            //广告主体
            Ad ya = new Ad();
            ya.setHtmlSnippet(ztAds.getHtml_snippet());

            //物料元
            MaterialMeta ym = new MaterialMeta();
            MetaGroup mg = ztAds.getMeta_group().get(0);
            String interactionType = mg.getInteraction_type();
            if ("WB".equals(interactionType)){
                ym.setInteractionType(1);
            }else if ("DOWNLOAD".equals(interactionType)){
                ym.setInteractionType(2);
            }else{
                ym.setInteractionType(0);
            }
            if (null != mg.getDeep_link() && !"".equals(mg.getDeep_link())){
                ym.setDeepLink(true);
                ym.setDeepLinkUrl(mg.getDeep_link());
            }
            ym.setClickUrl(mg.getClk_url());
            Size size = mg.getSize();
            ym.setMaterialWidth(size.getWidth());
            ym.setMaterialHeight(size.getHeight());
            ym.setAdTitle(mg.getTitle());
            List<String> desc = new ArrayList<>();
            desc.add(mg.getAdvertisement());
            ym.setDescs(desc);
            ym.setImageUrl(mg.getImages());
            List<String> icon = new ArrayList<>();
            icon.add(mg.getIcon());
            ym.setIconUrls(icon);
            ym.setPackageName(mg.getApp_package_name());
            ym.setVideoUrl(mg.getVideo());
            ym.setVideoDuration(mg.getVideo_duration());
            if (mg.getClick_position() == 1){
                ym.setProtocolType(1);
            }
            if (null != mg.getVideo() && !"".equals(mg.getVideo())){
                ym.setCreativeType(5);
            }else{
                ym.setCreativeType(2);
            }

            //上报
            List<AdTracking> trackList = ztAds.getAd_tracking();
            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            for (AdTracking t : trackList){
                String event = t.getTracking_event();
                List<String> urls = t.getTracking_url();
                //展现曝光
                if ("AD_IMPRESSION".equals(event)){
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&30&3");
                    urls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    ym.setWinNoticeUrls(report(urls));
                    //点击
                }else if ("AD_CLICK".equals(event)){
                    String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&30&4");
                    urls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    ym.setWinCNoticeUrls(report(urls));
                    //下载开始上报
                }else if ("DOWN_LOAD_START".equals(event)){
                    ym.setWinDownloadUrls(urls);
                    //下载完成上报
                }else if ("DOWN_LOAD_END".equals(event)){
                    ym.setWinDownloadEndUrls(urls);
                    //安装开始上报
                }else if ("INSTALL_START".equals(event)){
                    ym.setWinInstallUrls(urls);
                    //安装完成上报
                }else if ("INSTALL_END".equals(event)){
                    ym.setWinInstallEndUrls(urls);
                    //视频开始播放上报
                }else if ("VIDEO_AD_START".equals(event)){
                    Track track = new Track();
                    track.setType(0);
                    track.setUrls(urls);
                    ydtTrackList.add(track);
                    //视频正常播放结束上报
                }else if ("VIDEO_AD_END".equals(event)){
                    Track track = new Track();
                    track.setType(4);
                    track.setUrls(urls);
                    ydtTrackList.add(track);
                    //视频播放中上报
                }else if ("VIDEO_AD_PLAY".equals(event)){
                    Track track = new Track();
                    track.setType(2);
                    track.setUrls(urls);
                    ydtTrackList.add(track);
                    //视频关闭上报
                }else if ("VIDEO_AD_CLOSE".equals(event)){
                    Track track = new Track();
                    track.setType(104);
                    track.setUrls(urls);
                    ydtTrackList.add(track);
                    //应用激活上报信息
                }else if ("ACTIVE_END".equals(event)){
                    ym.setWinActiveUrls(urls);
                    //静音监控链接
                }else if ("VIDEO_MUTE".equals(event)){
                    Track track = new Track();
                    track.setType(103);
                    track.setUrls(urls);
                    ydtTrackList.add(track);
                    //取消静音监控链接
                }else if ("VIDEO_UN_MUTE".equals(event)){
                    Track track = new Track();
                    track.setType(104);
                    track.setUrls(urls);
                    ydtTrackList.add(track);
                    //广告被关闭
                }else if ("AD_CLOSE".equals(event)){
                    ym.setWinCloseUrls(urls);
                    //视频被暂停
                }else if ("VIDEO_PAUSE".equals(event)){
                    Track track = new Track();
                    track.setType(107);
                    track.setUrls(urls);
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
            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
        }
        //无填充
        else if (code == 1404){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        //参数错误
        else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        gar.setRequestId(gaReq.getRequestId());
        return gar;
    }


    //格式化上报地址
    public List<String> report(List<String> list){
        List<String> report = new ArrayList<>();
        if (list == null){
            return null;
        }
        for (int i = 0; i < list.size(); i++){
            String l = list.get(i)
                    .replace("__ABS_DOWN_X__","__DOWN_X__")
                    .replace("__ABS_DOWN_Y__","__DOWN_Y__")
                    .replace("__ABS_UP_X__","__UP_X__")
                    .replace("__ABS_UP_Y__","__UP_Y__")
                    .replace("__TS__","__EVENT_TIME_START__")
                    .replace("__NET_TYPE__","UNKNOWN")
                    .replace("__CARRIER__","0")
                    ;
            report.add(l);
        }
        return report;
    }

}
