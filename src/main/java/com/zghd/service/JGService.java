package com.zghd.service;

import com.util.http.HeaderEntity;
import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.zghd.entity.JiGuang.JGProto;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 极光激励视频
 */
@Service(value="jgService")
public class JGService {

    /**
     * 向极光后台发请求
     */
    public GetAdsResp JGSend(GetAdsReq gaReq, GetUpstream gu) throws IOException {
        //dev_key:4ed9775acc4ae3936f3011a6
        //dev_secret:ff81f3eeed163aee240368d1
        //NGVkOTc3NWFjYzRhZTM5MzZmMzAxMWE2JTNBZmY4MWYzZWVlZDE2M2FlZTI0MDM2OGQx
        //将下游参数都转为极光的参数
        byte[] bytes = formatData(gaReq, gu);
        String uri = "https://ssp.ad.jiguang.cn/v1/market/ads";

        List<HeaderEntity> heList = new ArrayList();
        HeaderEntity he = new HeaderEntity();
        he.setJian("Authorization");
        he.setZhi("Basic NGVkOTc3NWFjYzRhZTM5MzZmMzAxMWE2OmZmODFmM2VlZWQxNjNhZWUyNDAzNjhkMQ==");
        HttpEntity result = TestConnectionPool.postProtobuf(uri, bytes, heList);
        byte[] str = EntityUtils.toByteArray(result);

        //格式化出参数据
        JGProto.AdResponse adResponse = JGProto.AdResponse.parseFrom(str);
        return formatBackData(adResponse, gaReq, gu);

    }

    /**
     * 格式化前端数据
     */
    public byte[] formatData(GetAdsReq gaReq, GetUpstream gu){
        //序列化
        JGProto.AdRequest.Builder builder = JGProto.AdRequest.newBuilder();
        //requestId
        builder.setReqId(gaReq.getRequestId());
        builder.setApiVersion("1.2.0");
        builder.setProtocol("http");

        //APP信息
        JGProto.AdRequest.App.Builder appBuilder = JGProto.AdRequest.App.newBuilder();
        appBuilder.setVersion(gaReq.getApp().getAppVersion());
        appBuilder.setBundle(gaReq.getApp().getAppPackage());
        builder.setApp(appBuilder);

        //Adslot信息
        JGProto.AdRequest.Adslot.Builder adslotBuilder = JGProto.AdRequest.Adslot.newBuilder();
        adslotBuilder.setIndex((int)(Math.random()*1000000));
        adslotBuilder.setId(gu.getUpstreamId());
        adslotBuilder.setHeight(gaReq.getSlot().getSlotheight());
        adslotBuilder.setWidth(gaReq.getSlot().getSlotwidth());
        adslotBuilder.setVideoPreload(false);
        adslotBuilder.addSupportInteraction("DOWNLOAD");
        builder.addAdslot(adslotBuilder);

        //设备信息
        JGProto.AdRequest.Device.Builder deviceBuilder = JGProto.AdRequest.Device.newBuilder();
        int deviceType = gaReq.getDevice().getDeviceType();
        if(deviceType == 1){
            deviceBuilder.setType("PHONE");
        }else if(deviceType == 2){
            deviceBuilder.setType("PAD");
        }
        int osType = gaReq.getDevice().getOsType();
        if(osType == 1){
            deviceBuilder.setOsType("Android");
        }else if(osType == 2){
            deviceBuilder.setOsType("iOS");
        }
        deviceBuilder.setOsVersion(gaReq.getDevice().getOsVersion());
        deviceBuilder.setModel(gaReq.getDevice().getModel());
        deviceBuilder.setVendor(gaReq.getDevice().getVendor());
        deviceBuilder.setScreenHeight(gaReq.getDevice().getScreenHeight());
        deviceBuilder.setScreenWidth(gaReq.getDevice().getScreenWidth());
        deviceBuilder.setUa(gaReq.getDevice().getUa());
        builder.setDevice(deviceBuilder);

        //Udid信息
        JGProto.AdRequest.Udid.Builder udBuilder = JGProto.AdRequest.Udid.newBuilder();
        udBuilder.setIdfa(gaReq.getDevice().getIdfa());
        udBuilder.setImei(gaReq.getDevice().getImei());
        udBuilder.setAndroidId(gaReq.getDevice().getAndroidId());
        udBuilder.setMac(gaReq.getDevice().getMac());
        builder.setUdid(udBuilder);

        //network信息
        JGProto.AdRequest.Network.Builder nwBuilder = JGProto.AdRequest.Network.newBuilder();
        nwBuilder.setIpv4(gaReq.getNetwork().getIp());
        nwBuilder.setConnectionType("UNKNOWN");
        nwBuilder.setOperator("UNKNOWN");
        builder.setNetwork(nwBuilder);

        //wifi信息
        JGProto.AdRequest.Wifi.Builder wifiBuilder = JGProto.AdRequest.Wifi.newBuilder();
        builder.addWifi(wifiBuilder);

        //gps信息
        JGProto.AdRequest.Gps.Builder gpsBuilder = JGProto.AdRequest.Gps.newBuilder();
        builder.setGps(gpsBuilder);

        //返回数据
        JGProto.AdRequest rj = builder.build();
        return rj.toByteArray();
    }

    /**
     * 格式化返回数据
     */
    public GetAdsResp formatBackData(JGProto.AdResponse adResponse, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        gar.setRequestId(gaReq.getRequestId());
        int errorCode = adResponse.getErrCode();//错误码
        if(errorCode == 0){
            //广告信息
            JGProto.AdResponse.Ad ad =adResponse.getAd(0);
            //物料信息
            JGProto.AdResponse.Ad.MaterialMeta mm = ad.getMaterialMeta();

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(ad.getIndex()+"");
            ya.setAdlogo(mm.getLogo());

            //视频内容
            MaterialMeta ym = new MaterialMeta();
            List<String> descs = new ArrayList<>();
            descs.add(mm.getDescription());
            ym.setDescs(descs);
            ym.setImageUrl(mm.getImgList());
            ym.setMaterialWidth(ad.getWidth());
            ym.setMaterialHeight(ad.getHeight());
            List<String> icon = new ArrayList<>();
            icon.add(mm.getAppIcon());
            ym.setIconUrls(icon);
            ym.setPackageName(mm.getAppPackage());
            ym.setVideoUrl(mm.getVideoUrl());
            ym.setVideoDuration(mm.getVideoDuration());
            ym.setAdTitle(mm.getTitle());
            ym.setAppSize(mm.getAppSize());
            ym.setBrandName(mm.getAppName());
            //交互类型(详情见文档)
            //SURFING 普通链接（H5） DOWNLOAD 应用下载
            String interaction = ad.getInteraction();
            if("DOWNLOAD".equals(interaction)){
                ym.setClickUrl(mm.getDownloadLink());
                ym.setInteractionType(2);
            }else if("SURFING".equals(interaction)){
                ym.setInteractionType(1);
                ym.setClickUrl(mm.getDeepLink());
            }else{
                ym.setInteractionType(0);
                ym.setClickUrl(mm.getDeepLink());
            }

            //曝光展现
            List<String> nL = ad.getAdTracking().getExposureUrlList();
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-3-3");
            nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(nL);

            //点击
            List<String> cL = ad.getAdTracking().getClickUrlList();
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-3-4");
            cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(cL);

            List<JGProto.AdResponse.Ad.TrackingUrl> tuList = ad.getTrackingUrlList();
            for(JGProto.AdResponse.Ad.TrackingUrl tu:tuList){
                int eventType = tu.getEventTypeValue();
                //REWARD_SUCCESS = 0;      // 当对用户完成激励时上报
                if(eventType == 0){
                    ym.setWinCompleteUrls(tu.getUrlList());

                //VIDEO_LOADED = 2;        // 广告视频被加载成功依次上报
                }else if (eventType == 1){
                    ym.setWinLoadUrls(tu.getUrlList());

                //CLOSE_AD = 20;               // 点击关闭广告
                }else if (eventType == 20){
                    ym.setWinCloseUrls(tu.getUrlList());

                //DOWNLOAD_APP = 21;           // 确认下载应用
                }else if (eventType == 21){
                    ym.setWinDownloadUrls(tu.getUrlList());

                //DOWNLOAD_APP_SUCCEEDED = 27; // 应用下载成功
                }else if (eventType == 27){
                    ym.setWinDownloadEndUrls(tu.getUrlList());

                //INSTALL_APP = 23;            // 应用安装
                }else if (eventType == 23){
                    ym.setWinInstallUrls(tu.getUrlList());

                //INSTALL_APP_SUCCEEDED = 24;  // 应用安装成功
                }else if (eventType == 24){
                    ym.setWinInstallEndUrls(tu.getUrlList());

                //DEEPLINK_SUCCEEDED = 25;     // 成功拉起应用
                }else if (eventType == 25){
                    ym.setWinInstallOpenUrls(tu.getUrlList());

                }
            }

            //播放进度
            List<Track> ydtTrackList = new ArrayList<>();
            for(JGProto.AdResponse.Ad.TrackingUrl tu:tuList){
                int eventType = tu.getEventTypeValue();
                if(eventType == 3){
                    Track yt = new Track();
                    yt.setType(0);
                    yt.setUrls(tu.getUrlList());
                    ydtTrackList.add(yt);

                }else if(eventType == 4){
                    Track yt = new Track();
                    yt.setType(4);
                    yt.setUrls(tu.getUrlList());
                    ydtTrackList.add(yt);

                }else if(eventType == 16){
                    List<JGProto.AdResponse.Ad.TrackingUrl.VideoPlayPercentage> ppList = tu.getPlayPercentageList();
                    for(int i=0;i<ppList.size();i++){
                        double point = ppList.get(i).getCheckPoint();
                        if(0.25 == point){
                            Track yt = new Track();
                            yt.setType(1);
                            yt.setUrls(ppList.get(i).getUrlList());
                            ydtTrackList.add(yt);

                        }else if(1.0 == point){
                            Track yt = new Track();
                            yt.setType(4);
                            yt.setUrls(ppList.get(i).getUrlList());
                            ydtTrackList.add(yt);
                        }
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
        }else if(errorCode == 2001){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }else{
            gar.setErrorCode("500");
            gar.setMsg("SERVER_ERROR");
        }
        return gar;
    }

}
