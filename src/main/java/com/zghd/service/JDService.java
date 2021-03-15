package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.zghd.entity.JingDong.request.*;
import com.zghd.entity.JingDong.response.Bid;
import com.zghd.entity.JingDong.response.Item;
import com.zghd.entity.JingDong.response.JDResponse;
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
 * 京东
 */
@Service(value="jdService")
public class JDService {

    /**
     * 向京东后台发请求
     */
    public GetAdsResp JDSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String data = formatData(gaReq, gu);
        data = data.replaceAll("aNative","native");
        String url = "https://dsp-x.jd.com/adx/"+gu.getUpstreamAppId();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpClient.execute(httpPost);
        int code = resp.getStatusLine().getStatusCode();
        if (code == 200){
            HttpEntity result = resp.getEntity();
            String str = EntityUtils.toString(result, "utf-8");
            System.out.println("京东返回..."+str);
            gar = formatBackData(str,gaReq,gu);
            System.out.println("平台返回..."+JSONObject.fromObject(gar).toString());
        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("400");
        }
        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        JDRequest request = new JDRequest();
        request.setId(gaReq.getRequestId());
        request.setVersion("3.3");
        /**
         * 广告位信息
         */
        List<Imp> impList = new ArrayList<>();
        Imp imp = new Imp();
        imp.setId(gaReq.getRequestId());
        imp.setTagid(gu.getUpstreamId());
        imp.setBidfloor(1);
        Native aNative = new Native();
        aNative.setW(gu.getUpstreamWidth());
        aNative.setH(gu.getUpstreamHeight());
        aNative.setCount(1);
        aNative.setImgnum(1);
        imp.setaNative(aNative);
        imp.setIsdeeplink(true);
        impList.add(imp);
        request.setImp(impList);
        /**
         * APP信息
         */
        App app = new App();
        app.setBundle(gu.getUpstreamPackageName());
        request.setApp(app);
        /**
         * 设备信息
         */
        Device device = new Device();
        int ost = gaReq.getDevice().getOsType();
        if (ost == 1){
            device.setOs("android");
            device.setOsv(gaReq.getDevice().getOsVersion());
            device.setDid(gaReq.getDevice().getImei());
            device.setDidmd5(gaReq.getDevice().getImei_md5().toUpperCase());
            device.setOid(gaReq.getDevice().getOaid());
        }else{
            device.setOs("ios");
            device.setIfa(gaReq.getDevice().getIdfa());
            device.setIfamd5(gaReq.getDevice().getIdfa_md5());
        }
        device.setIp(gaReq.getNetwork().getIp());
        device.setUa(gaReq.getDevice().getUa());
        device.setMake(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        device.setHwv(gaReq.getDevice().getBrand());
        device.setScreenheight(gu.getUpstreamHeight());
        device.setScreenwidth(gu.getUpstreamWidth());
        int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 1){
            device.setCarrier("mobile");
        }else if (operatorType == 2){
            device.setCarrier("telecom");
        }else if (operatorType == 3){
            device.setCarrier("unicom");
        }else{
            device.setCarrier("mobile");
        }
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            device.setConnectiontype(4);
        }else if (connectionType == 3){
            device.setConnectiontype(5);
        }else if (connectionType == 4){
            device.setConnectiontype(6);
        }else if (connectionType == 100){
            device.setConnectiontype(2);
        }else if (connectionType == 101){
            device.setConnectiontype(1);
        }else{
            device.setConnectiontype(0);
        }
        device.setMacidmd5(gaReq.getDevice().getMac_md5());
        device.setPpi(gaReq.getDevice().getPpi());
        /**
         * 地理位置
         */
        Geo geo = new Geo();
        geo.setLat(gaReq.getNetwork().getLat());
        geo.setLon(gaReq.getNetwork().getLon());
        device.setGeo(geo);
        request.setDevice(device);
        /**
         * 用户信息--非必填
         */
        User user = new User();
        request.setUser(user);
        /**
         * 内容页上下文--非必填
         */
        List<Content> contentList = new ArrayList<>();
        request.setContent(contentList);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu) {
        GetAdsResp gar = new GetAdsResp();
        gar.setRequestId(gaReq.getRequestId());

        JDResponse resp = JSON.parseObject(backData,JDResponse.class);

        //native对象
        Bid bid = resp.getSeatbid().getBid().get(0);
        //素材对象
        Item item = bid.getAdm().getItems().get(0);

        //广告主体
        Ad ya = new Ad();
        ya.setAdKey(bid.getAdid());

        //材料内容
        MaterialMeta ym = new MaterialMeta();

        ym.setAdTitle(item.getTitle());
        List<String> descs = new ArrayList<>();
        descs.add(item.getDesc());
        ym.setDescs(descs);

        String media_style = item.getMedia_style();
        //浏览类或者deeplink
        if ("1".equals(media_style)){
            ym.setInteractionType(1);
            ym.setClickUrl(item.getClick_url());
            String dpl_url = item.getDpl_url();
            if (null != dpl_url && !"".equals(dpl_url)){
                ym.setDeepLink(true);
                ym.setDeepLinkUrl(dpl_url);
            }

        //下载类
        }else{
            ym.setInteractionType(2);
            ym.setBrandName(item.getAd_resource());
            ym.setClickUrl(item.getDownload_url());
        }
        //图片
        String img = item.getImg();
        if (null != img && !"".equals(img)){
            List<String> image_url = new ArrayList<>();
            image_url.add(img);
            ym.setImageUrl(image_url);
        }else{
            ym.setImageUrl(item.getImgs());
        }
        //创意类型
        String video = item.getVideo();
        if (null != video && !"".equals(video)){
            ym.setCreativeType(5);
            ym.setVideoUrl(video);
            //视频进度上报信息
            List<Track> ydtTrackList = new ArrayList<>();
            Track yt0 = new Track();
            yt0.setType(0);
            yt0.getUrls().add(item.getVideo_start_url());
            ydtTrackList.add(yt0);

            Track yt2 = new Track();
            yt2.setType(2);
            yt2.getUrls().add(item.getVideo_valid_url());
            ydtTrackList.add(yt2);

            Track yt4 = new Track();
            yt4.setType(4);
            yt4.getUrls().add(item.getVideo_finish_url());
            ydtTrackList.add(yt4);

            ya.setTracks(ydtTrackList);

        }else{
            ym.setCreativeType(2);
        }

        //曝光
        List<String> nL = item.getExposal_urls();
        String param1 = JiaMi.encrypt(gaReq.getApp().getAppId() + "&" + gaReq.getSlot().getSlotId() + "&" + gu.getUpstreamId() + "&33&3");
        nL.add("http://47.95.31.238/adx/ssp/backNotice?param=" + param1);
        ym.setWinNoticeUrls(nL);
        //点击
        List<String> cL = item.getClick_monitor_urls();
        String param2 = JiaMi.encrypt(gaReq.getApp().getAppId() + "&" + gaReq.getSlot().getSlotId() + "&" + gu.getUpstreamId() + "&33&4");
        cL.add("http://47.95.31.238/adx/ssp/backNotice?param=" + param2);
        ym.setWinCNoticeUrls(cL);

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
