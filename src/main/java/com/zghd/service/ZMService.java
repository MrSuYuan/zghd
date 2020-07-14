package com.zghd.service;

import com.util.md5.JiaMi;
import com.zghd.entity.ZhongMeng.*;
import com.util.md5.MD5;
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
import java.util.Date;
import java.util.List;

/**
 * 众盟
 */
@Service(value="zmService")
public class ZMService {

    /**
     * 众盟请求
     */
    public GetAdsResp ZMSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        GetAdsResp gar;
        String url = "http://adalliance.zmeng123.com/zmtmobads/v5/getAd.do";
        //参数转换
        String data = formatData(gaReq, gu);
        CloseableHttpResponse response;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("charset", "utf-8");
        StringEntity stringEntity = new StringEntity(data,"utf-8");
        httpPost.setEntity(stringEntity);
        response = httpclient.execute(httpPost);
        HttpEntity result = response.getEntity();
        String str = EntityUtils.toString(result);
        gar = formatBackData(str, gaReq, gu);
        return gar;
    }

    /**
     * 封装入参
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){
        RequestJson requestJson = new RequestJson();

        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setAccessToken("dHlwZTphY2Nlc3NfdG9rZW4gYWxnOkFFUyA=.aXNfd2ViOiBhcHBfcGFja2FnZTpjb20uanltLmxpbnlhbmdkYXIgYXBwX2lkOnptX2FwcF8xMTg2MjE3IA==.3dj1iAlb0nnCmxIv3Opj41etWfzSY2Bnd4ICsBCgt6GnM2w0eFdv25ZQe5xALA9LWAdHm0S_lZXETknT3e7AMlHGrxGCx97r8AEqlzO_6VnEERV8OZ5MSrM73AT_8LF0psUvSjeHMUqObGE5HCrShGcDSb6Qje3BMI1GXRHTZFk");
        int adtype = gaReq.getSlot().getAdtype();
        if(adtype == 1){
            reqInfo.setAdSlotId("multi_02");
        }else if (adtype == 2){
            reqInfo.setAdSlotId("multi_03");
        }else if (adtype == 3){
            reqInfo.setAdSlotId("multi_04");
        }else if (adtype == 4){
            reqInfo.setAdSlotId("multi_05");
        }
        reqInfo.setAdSlotId(gu.getUpstreamId());
        requestJson.setReqInfo(reqInfo);

        NetWorkInfo networkInfo = new NetWorkInfo();
        networkInfo.setUa(gaReq.getDevice().getUa());
        networkInfo.setIp(gaReq.getNetwork().getIp());
        networkInfo.setIpType(0);
        networkInfo.setHttpType(0);
        requestJson.setNetworkInfo(networkInfo);

        AdSlotInfo adSlotInfo = new AdSlotInfo();
        adSlotInfo.setMimes("img,icon");
        adSlotInfo.setSlotWidth(gaReq.getSlot().getSlotwidth());
        adSlotInfo.setSlotHeight(gaReq.getSlot().getSlotheight());
        adSlotInfo.setMinDuration(0);
        adSlotInfo.setMaxDuration(90);
        requestJson.setAdSlotInfo(adSlotInfo);

        MobileInfo mobileInfo = new MobileInfo();
        mobileInfo.setOsVersion(gaReq.getDevice().getOsVersion());
        mobileInfo.setAppVersion(gaReq.getApp().getAppVersion());
        mobileInfo.setMobileModel(gaReq.getDevice().getModel());
        mobileInfo.setVendor(gaReq.getDevice().getVendor());
        mobileInfo.setConnectionType(100);
        mobileInfo.setOperatorType(0);
        mobileInfo.setImsi("");
        mobileInfo.setImei(gaReq.getDevice().getImei());
        mobileInfo.setImei_md5(MD5.md5(gaReq.getDevice().getImei()));
        mobileInfo.setAndroidId(gaReq.getDevice().getAndroidId());
        mobileInfo.setAndroidId_md5(MD5.md5(gaReq.getDevice().getAndroidId()));
        mobileInfo.setIdfa(gaReq.getDevice().getIdfa());
        mobileInfo.setIdfa_md5(MD5.md5(gaReq.getDevice().getIdfa()));
        mobileInfo.setIdfv("");
        mobileInfo.setOpenUdid("");
        mobileInfo.setMac(gaReq.getDevice().getMac());
        mobileInfo.setDeviceType(1);
        int os_type = gaReq.getDevice().getOsType();
        if(os_type == 1){
            mobileInfo.setOsType(0);
        }else{
            mobileInfo.setOsType(1);
        }
        mobileInfo.setScreenWidth(gaReq.getDevice().getScreenWidth());
        mobileInfo.setScreenHeight(gaReq.getDevice().getScreenHeight());
        mobileInfo.setDeny(gaReq.getDevice().getPpi());
        requestJson.setMobileInfo(mobileInfo);

        CoordinateInfo coordinateInfo = new CoordinateInfo();
        coordinateInfo.setCoordinateType(100);
        coordinateInfo.setTimestamp(new Date().getTime());
        requestJson.setCoordinateInfo(coordinateInfo);

        return JSONObject.fromObject(requestJson).toString();
    }

    /**
     * 封装出参
     */
    public GetAdsResp formatBackData(String entityStr, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        JSONObject json = JSONObject.fromObject(entityStr);
        int errorCode= json.getInt("errorCode");
        if(0 == errorCode){
            JSONObject ad = (JSONObject) json.getJSONArray("ads").get(0);
            JSONObject material = (JSONObject) ad.getJSONArray("materialMetas").get(0);
            List<JSONObject> adTrack = ad.getJSONArray("adTracking");
            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(ad.getString("adKey"));
            if (ad.has("adtext")){
                ya.setAdtext(ad.getString("adtext"));
            }
            if (ad.has("adlogo")){
                ya.setAdlogo(ad.getString("adlogo"));
            }
            if (material.has("htmlSnippet")){
                ya.setHtmlSnippet(material.getString("htmlSnippet"));
            }

            //物料元
            MaterialMeta ym = new MaterialMeta();
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            ym.setCreativeType(material.getInt("creativeType"));
            ym.setInteractionType(material.getInt("interactionType"));
            ym.setClickUrl(material.getString("landingUrl"));
            if (material.has("title")){
                ym.setAdTitle(material.getString("title"));
            }
            List<String> descs = new ArrayList<>();
            if (material.has("desc")){
                descs.add(material.getString("desc"));
            }
            ym.setDescs(descs);
            if (material.has("imageSrcs")){
                ym.setImageUrl(material.getJSONArray("imageSrcs"));
            }
            if (material.has("iconSrcs")){
                ym.setIconUrls(material.getJSONArray("iconSrcs"));
            }
            if (material.has("materialWidth")){
                ym.setMaterialWidth(material.getInt("materialWidth"));
            }
            if (material.has("materialHeight")){
                ym.setMaterialHeight(material.getInt("materialHeight"));
            }
            if (material.has("packageName")){
                ym.setPackageName(material.getString("packageName"));
            }
            if (material.has("materialSize")){
                ym.setAppSize(material.getInt("materialSize"));
            }
            if (material.has("videoUrl")){
                ym.setVideoUrl(material.getString("videoUrl"));
            }
            if (material.has("appName")){
                ym.setBrandName(material.getString("appName"));
            }
            if (material.has("videoDuration")){
                ym.setVideoDuration(material.getInt("videoDuration"));
            }

            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();

            for(int i=0;i<adTrack.size();i++){
                JSONObject track = adTrack.get(i);
                int type = track.getInt("trackingEventType");
                List<String> urls = track.getJSONArray("trackingUrls");
                if (type == 0){
                    //点击
                    String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-12-4");
                    urls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    ym.setWinCNoticeUrls(urls);
                }else if (type == 1){
                    //展现曝光
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-12-3");
                    urls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    ym.setWinNoticeUrls(urls);
                }else if (type == 2){
                    //关闭
                    ym.setWinCloseUrls(urls);
                }else if (type == 3){
                    //视频加载
                    ym.setWinLoadUrls(urls);
                }else if (type == 1000){
                    //下载
                    ym.setWinDownloadUrls(urls);
                }else if (type == 1001){
                    //下载完成
                    ym.setWinDownloadEndUrls(urls);
                }else if (type == 1002){
                    //安装
                    ym.setWinInstallUrls(urls);
                }else if (type == 1003){
                    //安装完成
                    ym.setWinInstallEndUrls(urls);
                }else if (type == 1004){
                    //激活
                    ym.setWinActiveUrls(urls);
                }else if (type == 108){
                    Track tracking = new Track();
                    tracking.setType(0);
                    tracking.setUrls(urls);
                    ydtTrackList.add(tracking);
                    ya.setTracks(ydtTrackList);
                }else if (type == 104){
                    Track tracking = new Track();
                    tracking.setType(1);
                    tracking.setUrls(urls);
                    ydtTrackList.add(tracking);
                    ya.setTracks(ydtTrackList);
                }else if (type == 105){
                    Track tracking = new Track();
                    tracking.setType(2);
                    tracking.setUrls(urls);
                    ydtTrackList.add(tracking);
                    ya.setTracks(ydtTrackList);
                }else if (type == 106){
                    Track tracking = new Track();
                    tracking.setType(3);
                    tracking.setUrls(urls);
                    ydtTrackList.add(tracking);
                    ya.setTracks(ydtTrackList);
                }else if (type == 109){
                    Track tracking = new Track();
                    tracking.setType(4);
                    tracking.setUrls(urls);
                    ydtTrackList.add(tracking);
                    ya.setTracks(ydtTrackList);

                }
            }
            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);

            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);
            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
            return gar;
        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
            return gar;
        }

    }
}
