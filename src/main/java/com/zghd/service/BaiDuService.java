package com.zghd.service;

import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.util.md5.EncryptUtil;
import com.zghd.entity.BaiDu.BaiduMobadsApi5;
import com.zghd.entity.BaiDu.BuildLogListUtils;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service("baiDuService")
public class BaiDuService {

    private static final String DOT = "\\.";
    private static final String BAIDU_API_URL = "http://mobads.baidu.com/api_5";
    //private static final String BAIDU_API_URL = "http://debug.mobads.baidu.com/api_5";
    private static final String YDT = "YDT";
    private static final String SOURCE = "baidu";
    private Random rand = new Random();

    /**
     * 请求百度
     */
    public GetAdsResp getAds(GetAdsReq ydtReq, GetUpstream gu)throws Exception {
        GetAdsResp resp;

        HttpURLConnection conn = null;
        BaiduMobadsApi5.MobadsRequest baiduReq = formatData(ydtReq, gu);
        URL mURL = new URL(BAIDU_API_URL);
        conn = (HttpURLConnection) mURL.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-protobuf");
        conn.setRequestProperty("Accept", "application/x-protobuf");
        conn.setReadTimeout(500);
        conn.setConnectTimeout(500);
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        out.write(baiduReq.toByteArray());
        out.flush();
        out.close();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int responseCode = conn.getResponseCode();// 调用此方法就不必再使用conn.connect()方法
        if (responseCode == 200) {
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            is.close();
            os.close();
        }
        BaiduMobadsApi5.MobadsResponse baiduRsp = BaiduMobadsApi5.MobadsResponse.parseFrom(os.toByteArray());
        resp = formatBackData(baiduRsp, ydtReq, gu);
        if (conn != null) {
            conn.disconnect();// 关闭连接
        }
        return resp;
    }


    public BaiduMobadsApi5.MobadsRequest formatData(GetAdsReq ydtReq, GetUpstream gu) throws UnsupportedEncodingException {
        String appId = StringUtils.reverse(gu.getUpstreamAppId());
        String slotId = gu.getUpstreamId();

        BaiduMobadsApi5.MobadsRequest.Builder requestBuilder = BaiduMobadsApi5.MobadsRequest.newBuilder();
        requestBuilder.setRequestId(YDT + appId + slotId + System.currentTimeMillis() + rand.nextInt(9));
        BaiduMobadsApi5.Version.Builder apiversionBuilder = BaiduMobadsApi5.Version.newBuilder();
        apiversionBuilder.setMajor(5);
        apiversionBuilder.setMicro(6);
        apiversionBuilder.setMinor(0);
        requestBuilder.setApiVersion(apiversionBuilder.build());
        //支持http
        BaiduMobadsApi5.MobadsRequest.RequestProtocolType protocolType = BaiduMobadsApi5.MobadsRequest.RequestProtocolType.HTTP_PROTOCOL_TYPE;
        requestBuilder.setRequestProtocolType(protocolType);

        BaiduMobadsApi5.App.Builder appBuilder = BaiduMobadsApi5.App.newBuilder();
        appBuilder.setAppId(appId);
        BaiduMobadsApi5.Version.Builder appversionBuilder = BaiduMobadsApi5.Version.newBuilder();
        String appVersion = ydtReq.getApp().getAppVersion();
        if (StringUtils.isNotEmpty(appVersion)) {
            String[] appVersionArray = appVersion.split(DOT);
            if (appVersionArray.length == 3) {
                appversionBuilder.setMajor(Integer.parseInt(appVersionArray[0]));
                appversionBuilder.setMicro(Integer.parseInt(appVersionArray[1]));
                appversionBuilder.setMinor(Integer.parseInt(appVersionArray[2]));
            }
        }
        appBuilder.setAppVersion(appversionBuilder.build());
        appBuilder.setAppPackage(gu.getUpstreamPackageName());
        requestBuilder.setApp(appBuilder.build());

        BaiduMobadsApi5.AdSlot.Builder adSlotBuilder = BaiduMobadsApi5.AdSlot.newBuilder();
        adSlotBuilder.setAdslotId(slotId);
        BaiduMobadsApi5.Size.Builder sizeBuilder = BaiduMobadsApi5.Size.newBuilder();
        sizeBuilder.setHeight(ydtReq.getSlot().getSlotheight());
        sizeBuilder.setWidth(ydtReq.getSlot().getSlotwidth());
        adSlotBuilder.setAdslotSize(sizeBuilder.build());
        requestBuilder.setAdslot(adSlotBuilder.build());

        BaiduMobadsApi5.Device.Builder deviceBuilder = BaiduMobadsApi5.Device.newBuilder();
        deviceBuilder.setDeviceType(BaiduMobadsApi5.Device.DeviceType.forNumber(ydtReq.getDevice().getDeviceType()));
        deviceBuilder.setOsType(BaiduMobadsApi5.Device.OsType.forNumber(ydtReq.getDevice().getOsType()));
        BaiduMobadsApi5.Version.Builder osVersionBuilder = BaiduMobadsApi5.Version.newBuilder();
        String osVersion = "9.1.1";
        if (StringUtils.isNotEmpty(osVersion)) {
            String[] osVersionArray = osVersion.trim().split(DOT);
            if (osVersionArray.length == 3) {
                if(osVersionArray[0]!=null&&osVersionArray[0].length()>0){
                    osVersionBuilder.setMajor(Integer.parseInt(osVersionArray[0]));
                }
                if(osVersionArray[1]!=null&&osVersionArray[1].length()>0){
                    osVersionBuilder.setMicro(Integer.parseInt(osVersionArray[1]));
                }
                if(osVersionArray[2]!=null&&osVersionArray[2].length()>0){
                    osVersionBuilder.setMinor(Integer.parseInt(osVersionArray[2]));
                }
            }
        }
        deviceBuilder.setOsVersion(osVersionBuilder.build());
        deviceBuilder.setVendor(ByteString.copyFrom(ydtReq.getDevice().getVendor(), "utf-8"));
        deviceBuilder.setModel(ByteString.copyFrom(ydtReq.getDevice().getModel(), "utf-8"));
        BaiduMobadsApi5.Size.Builder screeSizeBuilder = BaiduMobadsApi5.Size.newBuilder();
        screeSizeBuilder.setHeight(ydtReq.getDevice().getScreenHeight());
        screeSizeBuilder.setWidth(ydtReq.getDevice().getScreenWidth());
        deviceBuilder.setScreenSize(screeSizeBuilder.build());
        BaiduMobadsApi5.UdId.Builder udidBuilder = BaiduMobadsApi5.UdId.newBuilder();
        if(ydtReq.getDevice().getImei() != null && !"".equals(ydtReq.getDevice().getImei())) {
            udidBuilder.setImei(ydtReq.getDevice().getImei());
        }else {
            udidBuilder.setImeiMd5(ydtReq.getDevice().getImei_md5());
        }
        if (null != ydtReq.getDevice().getOaid() && !"".equals(ydtReq.getDevice().getOaid())){
            udidBuilder.setOaid(ydtReq.getDevice().getOaid());
        }
        udidBuilder.setIdfa(ydtReq.getDevice().getIdfa());
        udidBuilder.setAndroidId(ydtReq.getDevice().getAndroidId());

        udidBuilder.setMac(ydtReq.getDevice().getMac());
        deviceBuilder.setUdid(udidBuilder.build());
        requestBuilder.setDevice(deviceBuilder.build());

        BaiduMobadsApi5.Network.Builder networkBuilder = BaiduMobadsApi5.Network.newBuilder();
        networkBuilder.setIpv4(ydtReq.getNetwork().getIp());
        networkBuilder.setConnectionType(BaiduMobadsApi5.Network.ConnectionType
                .forNumber(ydtReq.getNetwork().getConnectionType()));
        networkBuilder.setOperatorType(BaiduMobadsApi5.Network.OperatorType.forNumber(ydtReq.getNetwork().getOperatorType()));
        networkBuilder.setCellularId("");
        requestBuilder.setNetwork(networkBuilder.build());

        return requestBuilder.build();
    }


    /**
     * 新接口封装方式
     */
    public GetAdsResp formatBackData(BaiduMobadsApi5.MobadsResponse baiduRsp, GetAdsReq ydtReq, GetUpstream gu) {
        GetAdsResp ydtRsp = new GetAdsResp();
        ydtRsp.setErrorCode(String.valueOf(baiduRsp.getErrorCode()));
        List<Ad> adList = Lists.newArrayList();
        if (baiduRsp.getAdsList().size() == 0){
            ydtRsp.setRequestId(ydtReq.getRequestId());
            ydtRsp.setErrorCode("400");
            ydtRsp.setMsg("NO_AD");
            return ydtRsp;
        }
        for (BaiduMobadsApi5.Ad ad : baiduRsp.getAdsList()) {
            Ad Ad = new Ad();
            Ad.setSlotId(ad.getAdslotId());
            Ad.setHtmlSnippet(ad.getHtmlSnippet().toStringUtf8());
            Ad.setAdKey(ad.getAdKey());
            Ad.setAdtext(ad.getMobAdtext());
            Ad.setAdlogo(ad.getMobAdlogo());

            List<MaterialMeta> metaGroup = Lists.newArrayList();
            for (BaiduMobadsApi5.MaterialMeta meta : ad.getMetaGroupList()) {
                MaterialMeta Meta = new MaterialMeta();
                Meta.setDescs(bsList2sList(meta.getDescriptionList()));
                Meta.setImageUrl(bsList2sList(meta.getImageSrcList().asByteStringList()));
                Meta.setMaterialHeight(meta.getMaterialHeight());
                Meta.setMaterialWidth(meta.getMaterialWidth());
                Meta.setIconUrls(bsList2sList(meta.getIconSrcList().asByteStringList()));
                Meta.setClickUrl(meta.getClickUrl());
                Meta.setCreativeType(meta.getCreativeType().getNumber());
                Meta.setInteractionType(meta.getInteractionType().getNumber());
                Meta.setPackageName(meta.getAppPackage());
                Meta.setAppSize(meta.getAppSize());
                Meta.setVideoUrl(meta.getVideoUrl());
                Meta.setVideoDuration(meta.getVideoDuration());
                Meta.setTotalNum(meta.getMetaIndex().getTotalNum());
                Meta.setCurrentIndex(meta.getMetaIndex().getCurrentIndex());
                Meta.setBrandName(meta.getBrandName());
                if (StringUtils.isNotEmpty(meta.getAdTitle())) {
                    Meta.setAdTitle(meta.getAdTitle());
                } else if (meta.getTitle() != null) {
                    Meta.setAdTitle(meta.getTitle().toStringUtf8());
                } else {
                    Meta.setAdTitle(meta.getAdTitle());
                }

                //曝光
                List<String> nL = bsList2sList(meta.getWinNoticeUrlList().asByteStringList());
                EncryptUtil eu = new EncryptUtil();
                String param1 = eu.AESencode(ydtReq.getApp().getAppId()+"&"+ydtReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&9&3","zghd");
                nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                Meta.setWinNoticeUrls(nL);
                List<String> url=Meta.getWinNoticeUrls();
                if (CollectionUtils.isEmpty(url)){
                    url = Lists.newArrayList();
                }
                String uuid = UUID.randomUUID().toString();
                List<String> emptyList = Lists.newArrayList();
                url.addAll(BuildLogListUtils.buildShowLinks(emptyList, uuid, SOURCE, ydtReq));
                Meta.setWinNoticeUrls(url);

                //点击
                List<String> clickUrls = BuildLogListUtils.buildClickLinks(emptyList, uuid, SOURCE, ydtReq);
                List<String> cL = BuildLogListUtils.buildCad(clickUrls, ydtReq, uuid, SOURCE);
                String param2 = eu.AESencode(ydtReq.getApp().getAppId()+"&"+ydtReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&9&4","zghd");
                cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                Meta.setWinCNoticeUrls(cL);

                if (Meta.getInteractionType() == 2){
                    Meta.setWinDownloadUrls(BuildLogListUtils.buildSdLinks(emptyList, uuid, SOURCE, ydtReq));;
                    Meta.setWinDownloadEndUrls(BuildLogListUtils.buildFdLinks(emptyList,uuid, SOURCE, ydtReq));;
                    Meta.setWinInstallUrls(BuildLogListUtils.buildSiLinks(emptyList, uuid, SOURCE, ydtReq));;
                    Meta.setWinInstallEndUrls(BuildLogListUtils.buildFiLinks(emptyList,uuid, SOURCE, ydtReq));;
                }

                metaGroup.add(Meta);
            }
            Ad.setMetaGroup(metaGroup);

            List<Track> tracks = Lists.newArrayList();
            for (BaiduMobadsApi5.Tracking track : ad.getAdTrackingList()) {
                int num = track.getTrackingEvent().getNumber();
                //视频开始
                if(10002 == num){
                    Track ydtTrack = new Track();
                    ydtTrack.setType(0);
                    ydtTrack.setUrls(bsList2sList(track.getTrackingUrlList().asByteStringList()));
                    tracks.add(ydtTrack);
                }
                //视频结束
                if(10001 == num){
                    Track ydtTrack = new Track();
                    ydtTrack.setType(4);
                    ydtTrack.setUrls(bsList2sList(track.getTrackingUrlList().asByteStringList()));
                    tracks.add(ydtTrack);
                }
            }
            Ad.setTracks(tracks);
            adList.add(Ad);
        }
        ydtRsp.setAds(adList);
        ydtRsp.setRequestId(ydtReq.getRequestId());
        ydtRsp.setErrorCode("200");
        return ydtRsp;
    }

    private List<String> bsList2sList(List<ByteString> bsList) {
        List<String> strList = Lists.newArrayList();
        for (ByteString bs : bsList) {
            strList.add(bs.toStringUtf8());
        }
        return strList;
    }

}
