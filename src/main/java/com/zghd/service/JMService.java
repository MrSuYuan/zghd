package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.http.TestConnectionPool;
import com.util.md5.MD5;
import com.zghd.entity.JuMai.request.App;
import com.zghd.entity.JuMai.request.Device;
import com.zghd.entity.JuMai.request.JMRequest;
import com.zghd.entity.JuMai.response.ExtendTracking;
import com.zghd.entity.JuMai.response.JMAd;
import com.zghd.entity.JuMai.response.JMResponse;
import com.zghd.entity.JuMai.response.Videos;
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
 * 俱脉
 */
@Service(value="jmService")
public class JMService {

    /**
     * 向俱脉发送请求
     */
    public GetAdsResp JMSend(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        //入参参数
        String data = formatData(gaReq, gu);
        String url = "http://api.adserver.jvmai.com/api/r";

        //请求
        String str = TestConnectionPool.post(url, data,null);

        //回参参数
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }


    /**
     * 格式化参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){

        //key就是appId  pid就是广告位id
        long time = System.currentTimeMillis();
        JMRequest jmr = new JMRequest();
        jmr.setRid(gaReq.getRequestId());
        jmr.setPid(gu.getUpstreamId());
        jmr.setTime(time);
        jmr.setToken((MD5.md5(gu.getUpstreamId()+gu.getUpstreamAppId()+time).toUpperCase()));

        App app = new App();
        app.setAppver(gaReq.getApp().getAppVersion());
        app.setAppname(gu.getUpstreamAppName());
        app.setPkgname(gu.getUpstreamPackageName());
        app.setW(gaReq.getSlot().getSlotwidth());
        app.setH(gaReq.getSlot().getSlotheight());
        jmr.setAppinfo(app);

        Device device = new Device();
        device.setIp(gaReq.getNetwork().getIp());
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 100){
            device.setNet(1);
        }else{
            device.setNet(connectionType);
        }
        int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 1){
            device.setCarrier(46000);
        }else if (operatorType == 2){
            device.setCarrier(46003);
        }else if (operatorType == 3){
            device.setCarrier(46002);
        }else{
            device.setCarrier(46000);
        }
        device.setUa(gaReq.getDevice().getUa());
        int deviceType = gaReq.getDevice().getDeviceType();
        if (deviceType == 1){
            device.setType(1);
        }else if (deviceType == 2){
            device.setType(2);
        }else{
            device.setType(0);
        }
        device.setOs(gaReq.getDevice().getOsType());
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setImsi(gaReq.getDevice().getImsi());
        device.setImei(gaReq.getDevice().getImei());
        device.setAid(gaReq.getDevice().getAndroidId());
        device.setMac(gaReq.getDevice().getMac());
        device.setIdfv("");
        device.setIdfa(gaReq.getDevice().getIdfa());
        device.setOid("");
        device.setBrand(gaReq.getDevice().getBrand());
        device.setModel(gaReq.getDevice().getModel());
        device.setDensity(gaReq.getDevice().getPpi());
        device.setSw(gaReq.getDevice().getScreenWidth());
        device.setSh(gaReq.getDevice().getScreenHeight());
        device.setSo(gaReq.getDevice().getScreenOrientation());
        device.setLon(gaReq.getNetwork().getLon());
        device.setLat(gaReq.getNetwork().getLat());
        device.setOaid(gaReq.getDevice().getOaid());
        jmr.setDeviceinfo(device);

        return JSONObject.fromObject(jmr).toString();
    }


    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        JMResponse resp = JSON.parseObject(backData,JMResponse.class);
        boolean status = resp.isStatus();
        if (status){
            List<JMAd> ads = resp.getAds();
            if (null != ads){
                JMAd ad = ads.get(0);
                //广告主体
                Ad ya = new Ad();
                ya.setHtmlSnippet(ad.getHtml());

                //物料元
                MaterialMeta ym = new MaterialMeta();
                ym.setAdTitle(ad.getTitle());
                List<String> descs = new ArrayList<>();
                descs.add(ad.getDesc());
                ym.setDescs(descs);
                ym.setImageUrl(ad.getImgs());
                List<String> icons = new ArrayList<>();
                icons.add(ad.getIcon());
                ym.setIconUrls(icons);
                ym.setMaterialWidth(ad.getW());
                ym.setMaterialHeight(ad.getH());
                //点击行为 1:页面跳转，2 :下载，3：广点通下载
                int action = ad.getAction();
                if (action == 1){
                    ym.setInteractionType(1);
                }else if (action == 2){
                    ym.setInteractionType(2);
                }else if (action == 3){
                    ym.setInteractionType(2);
                    ym.setProtocolType(1);
                }else{
                    ym.setInteractionType(0);
                }
                ym.setClickUrl(ad.getUrl());
                if (null != ad.getDurl() && !"".equals(ad.getDurl())){
                    ym.setDeepLink(true);
                    ym.setDeepLinkUrl(ad.getDurl());
                }else{
                    ym.setDeepLink(false);
                }
                ym.setPackageName(ad.getPkgname());

                Videos v = ad.getVideos();
                ym.setVideoUrl(v.getVideo_url());
                ym.setVideoDuration(v.getVideo_duration());

                String html = v.getHtml();
                if (null != html && !"".equals(html)){
                    ya.setHtmlSnippet(html);

                    //展现曝光
                    List<String> winNotice = report(v.getHtml_exposure_tracking());
                    if (winNotice != null){
                        ym.setWinNoticeUrls(winNotice);
                    }else{
                        //展现曝光
                        winNotice = report(ad.getExlist());
                        ym.setWinNoticeUrls(winNotice);
                    }
                }else{
                    //展现曝光
                    List<String> winNotice = report(ad.getExlist());
                    ym.setWinNoticeUrls(winNotice);
                }

                //点击
                List<String> cL = report(ad.getCklist());
                ym.setWinCNoticeUrls(cL);

                ExtendTracking e = ad.getExttracking();
                //视频加载
                ym.setWinLoadUrls(report(v.getVideo_loaded_trackers()));
                //关闭
                ym.setWinCloseUrls(report(v.getVideo_close()));
                //用户完成激励
                ym.setWinCompleteUrls(report(v.getCallback_trackers()));
                //下载
                ym.setWinDownloadUrls(report(e.getDlstart()));
                //下载完成
                ym.setWinDownloadEndUrls(report(e.getDlcomplete()));
                //安装
                ym.setWinInstallUrls(report(e.getIstart()));
                //安装完成
                ym.setWinInstallEndUrls(report(e.getIcomplete()));
                //激活
                ym.setWinActiveUrls(report(e.getActivation()));
                //唤醒
                ym.setWinDeepLinkSuccessUrls(report(e.getDktracking()));
                //跳过
                ym.setWinIgnoreUrls(report(v.getVideo_skip()));

                //视频进度监控
                List<Track> ydtTrackList = new ArrayList<>();

                //视频开始播放
                Track track0 = new Track();
                track0.setType(0);
                track0.setUrls(report(v.getStart_play_trackers()));
                ydtTrackList.add(track0);

                //视频结束播放
                Track track4 = new Track();
                track4.setType(4);
                track4.setUrls(report(v.getEnd_play_trackers()));
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

            }else{
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");
            }

        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
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
                    .replace("$$DOWN_X$$","__DOWN_X__")
                    .replace("$$DOWN_Y$$","__DOWN_Y__")
                    .replace("$$UP_X$$","__UP_X__")
                    .replace("$$UP_Y$$","__UP_Y__")
                    .replace("$$CLICK_ID$$","__CLICK_ID__")
                    ;
            report.add(l);
        }
        return report;
    }


}
