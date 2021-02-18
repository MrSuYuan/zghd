package com.zghd.service;

import com.util.http.HeaderEntity;
import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.zghd.entity.GuangDianTong.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * 广点通
 */
@Service(value="gdtService")
public class GDTService {

    /**
     * 向广点通后台发请求
     */
    public GetAdsResp GDTSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {

        GetAdsResp gar;
        //格式化入参数据
        String data = formatData(gaReq, gu);
        String url;
        //测试环境
        //url = "http://test.mi.gdt.qq.com/api/v3";
        //正式环境
        url = "http://mi.gdt.qq.com/api/v3";

        List<HeaderEntity> heList = new ArrayList<>();
        HeaderEntity h1 = new HeaderEntity();
        h1.setJian("X-Forwarded-For");
        h1.setZhi("47.93.46.24");
        heList.add(h1);
        HeaderEntity h2 = new HeaderEntity();
        h2.setJian("User-Agent");
        h2.setZhi(gaReq.getDevice().getUa());
        heList.add(h2);

        String reqParams = url+data;
        String backData = TestConnectionPool.get(reqParams, heList);
        gar = formatBackData(backData, gaReq, gu);

        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{

        StringBuffer sb = new StringBuffer();
        sb.append("?api_version=3.2");
        sb.append("&support_https=1");

        Pos pos = new Pos();
        pos.setId(Long.parseLong(gu.getUpstreamId()));
        pos.setWidth(gaReq.getSlot().getSlotwidth());
        pos.setHeight(gaReq.getSlot().getSlotheight());
        pos.setWidth(gu.getUpstreamWidth());
        pos.setHeight(gu.getUpstreamHeight());
        pos.setAd_count(1);
        pos.setDeep_link_version(1);
        sb.append("&pos="+URLEncoder.encode(JSONObject.fromObject(pos).toString()));

        Media media = new Media();
        media.setApp_id(gu.getUpstreamAppId());
        media.setApp_bundle_id(gu.getUpstreamPackageName());
        sb.append("&media="+URLEncoder.encode(JSONObject.fromObject(media).toString()));

        Device device = new Device();
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setOs("android");
            device.setImei(gaReq.getDevice().getImei());
            device.setImei_md5(gaReq.getDevice().getImei_md5());
            device.setAndroid_id(gaReq.getDevice().getAndroidId());
            device.setAndroid_id_md5(gaReq.getDevice().getAndroidId_md5());
            device.setOaid(gaReq.getDevice().getOaid());
        }else{
            device.setOs("ios");
            device.setIdfa(gaReq.getDevice().getIdfa());
            device.setIdfa_md5(gaReq.getDevice().getIdfa_md5());
        }
        device.setOs_version(gaReq.getDevice().getOsVersion());
        device.setModel(gaReq.getDevice().getModel());
        device.setManufacturer(gaReq.getDevice().getVendor());
        device.setDevice_type(gaReq.getDevice().getDeviceType());
        device.setScreen_width(gaReq.getDevice().getScreenWidth());
        device.setScreen_height(gaReq.getDevice().getScreenHeight());
        int screenOrientation = gaReq.getDevice().getScreenOrientation();
        if (screenOrientation == 1){
            device.setOrientation(0);
        }else{
            device.setOrientation(90);
        }
        sb.append("&device="+URLEncoder.encode(JSONObject.fromObject(device).toString()));

        Network net = new Network();
        int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 1){
            net.setCarrier(1);
        }else if (operatorType == 2){
            net.setCarrier(3);
        }else if (operatorType == 3){
            net.setCarrier(2);
        }else{
            net.setCarrier(0);
        }
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            net.setConnect_type(2);
        }else if (connectionType == 3){
            net.setConnect_type(3);
        }else if (connectionType == 4){
            net.setConnect_type(4);
        }else if (connectionType == 100){
            net.setConnect_type(1);
        }else{
            net.setConnect_type(0);
        }
        sb.append("&network="+URLEncoder.encode(JSONObject.fromObject(net).toString()));

        Geo geo = new Geo();
        geo.setLat((int)(gaReq.getNetwork().getLat()*1000000));
        geo.setLng((int)(gaReq.getNetwork().getLon()*1000000));
        sb.append("&geo="+URLEncoder.encode(JSONObject.fromObject(geo).toString()));

        return sb.toString();
    }

    /**
     * 格式化返回数据
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu) {
        GetAdsResp gar = new GetAdsResp();
        JSONObject data = JSONObject.fromObject(backData);
        //requestId
        gar.setRequestId(gaReq.getRequestId());

        long ret = data.getLong("ret");
        if (ret == 0){

            JSONObject d = data.getJSONObject("data");
            JSONObject adid = d.getJSONObject(gu.getUpstreamId());
            JSONObject ad = JSONObject.fromObject(adid.getJSONArray("list").get(0));

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(ad.getString("ad_id"));

            //内容
            MaterialMeta ym = new MaterialMeta();

            //广告类型 0浏览类 1下载类
            int interact_type = ad.getInt("interact_type");
            if (interact_type == 0){
                ym.setInteractionType(1);
            }else{
                //转化上报地址  仅当返回APP下载类广告（interact_type=1）或当返回应用直达广告（customized_invoke_url参数存在）时，该参数有效
                String conversion_link = ad.getString("conversion_link");
                ym.setInteractionType(2);
                ym.setProtocolType(1);
                if (ad.has("package_name")){
                    ym.setBrandName(ad.getString("package_name"));
                }
                //开始下载
                List<String> winDownloadUrls = new ArrayList<>();
                winDownloadUrls.add(conversion_link.replace( "__ACTION_ID__","5"));
                ym.setWinDownloadUrls(winDownloadUrls);
                //下载完成
                List<String> winDownloadEndUrls = new ArrayList<>();
                winDownloadEndUrls.add(conversion_link.replace( "__ACTION_ID__","7"));
                ym.setWinDownloadEndUrls(winDownloadEndUrls);
                //安装完成
                List<String> winInstallEndUrls = new ArrayList<>();
                winInstallEndUrls.add(conversion_link.replace( "__ACTION_ID__","6"));
                ym.setWinInstallEndUrls(winInstallEndUrls);
            }
            //点击  需要宏替换
            String click_link = ad.getString("click_link");
            click_link = click_link.replaceAll("__REQ_WIDTH__",gu.getUpstreamWidth()+"");
            click_link = click_link.replaceAll("__REQ_HEIGHT__",gu.getUpstreamHeight()+"");
            ym.setClickUrl(click_link);
            //创意类型
            int crt_type = ad.getInt("crt_type");
            if (crt_type == 2){
                ym.setCreativeType(2);
            }else if (crt_type == 20){
                ym.setCreativeType(5);
                ym.setVideoUrl(ad.getString("video_url"));
                ym.setVideoDuration(ad.getInt("video_duration"));
                //视频进度监控
                if (ad.has("video_view_link")){
                    String video_view_link = ad.getString("video_view_link");
                    List<String> urls = new ArrayList<>();
                    urls.add(video_view_link);
                    Track track4 = new Track();
                    track4.setType(4);
                    track4.setUrls(urls);
                    List<Track> ydtTrackList = new ArrayList<>();
                    ydtTrackList.add(track4);
                    ya.setTracks(ydtTrackList);
                }

            }else{
                ym.setCreativeType(3);
            }
            //图片
            List<String> image = new ArrayList<>();
            if (ad.has("img_url")){
                if (!"".equals(ad.getString("img_url")) && null != ad.getString("img_url")){
                    String imageUrl = ad.getString("img_url");
                    image.add(imageUrl);
                }else{
                    if (ad.has("img_list")){
                        if (!"".equals(ad.getString("img_list")) && null != ad.getString("img_list")){
                            image = ad.getJSONArray("img_list");
                        }
                    }
                }
            }else{
                if (ad.has("img_list")){
                    if (!"".equals(ad.getString("img_list")) && null != ad.getString("img_list")){
                        image = ad.getJSONArray("img_list");
                    }
                }
            }
            ym.setImageUrl(image);
            //图标
            List<String> icon = new ArrayList<>();
            if (ad.has("img2_url")){
                if (!"".equals(ad.getString("img2_url")) && null != ad.getString("img2_url")){
                    String iconUrl = ad.getString("img2_url");
                    icon.add(iconUrl);
                }
            }
            ym.setIconUrls(icon);
            //宽高
            if (ad.has("img_width")){
                ym.setMaterialWidth(ad.getInt("img_width"));
            }
            if (ad.has("img_height")){
                ym.setMaterialHeight(ad.getInt("img_height"));
            }
            //标题描述
            if (ad.has("title")){
                ym.setAdTitle(ad.getString("title"));
            }
            List<String> desc = new ArrayList<>();
            if (ad.has("description")){
                desc.add(ad.getString("description"));
            }
            ym.setDescs(desc);

            //宏参数
            String h = "&event=width:__WIDTH__height:__HEIGHT__dx:__DOWN_X__dy:__DOWN_Y__ux:__UP_X__uy:__UP_Y__";
            //展现曝光
            String impression_link = ad.getString("impression_link");
            List<String> winNotice = new ArrayList<>();
            winNotice.add(impression_link);
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&24&3");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(winNotice);

            //点击
            List<String> cL = new ArrayList<>();
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&24&4");
            cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2+h);
            ym.setWinCNoticeUrls(cL);

            //应用直达url deeplink
            if (ad.has("customized_invoke_url")){
                //转化上报地址  仅当返回APP下载类广告（interact_type=1）或当返回应用直达广告（customized_invoke_url参数存在）时，该参数有效
                String conversion_link = ad.getString("conversion_link");
                String customized_invoke_url = ad.getString("customized_invoke_url");
                ym.setDeepLink(true);
                ym.setDeepLinkUrl(customized_invoke_url);
                //吊起app
                List<String> deepLinkUrls = new ArrayList<>();
                deepLinkUrls.add(conversion_link.replace( "__ACTION_ID__","137"));
                ym.setWinDeepLinkUrls(deepLinkUrls);
                //吊起成功
                List<String> deepLinkSuccessUrls = new ArrayList<>();
                deepLinkSuccessUrls.add(conversion_link.replace( "__ACTION_ID__","138"));
                String param3 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&24&5");
                deepLinkSuccessUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param3);
                ym.setWinDeepLinkSuccessUrls(deepLinkSuccessUrls);
            }else{
                ym.setDeepLink(false);
            }

            //综合封装返回
            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);
            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");

        }else if(ret == 102006 || ret == 109506 || ret == 109507){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }


}
