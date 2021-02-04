package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.DianKai.response.Ads;
import com.zghd.entity.DianKai.response.DKResponse;
import com.zghd.entity.DianKai.response.VideoPlay;
import com.zghd.entity.XiaoZhi.*;
import com.zghd.entity.YouKu.request.YKRequest;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 点开--云告
 */
@Service(value="dkService")
public class DKService {

    /**
     * 向云告发送请求
     */
    public GetAdsResp DKSend(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        //入参参数
        String data = formatData(gaReq, gu);
        String url = "http://api.yungao.mobi/ssp"+data;

        //请求
        String str = TestConnectionPool.get(url, null);

        //回参参数
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }


    /**
     * 格式化参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{

        StringBuffer sb = new StringBuffer();
        //基础参数
        sb.append("?request_id="+gaReq.getRequestId());
        sb.append("&api_version=3.3.1");
        sb.append("&media_type=1");
        sb.append("&request_protocol_type=1");
        sb.append("&request_time="+new Date().getTime());
        //app参数
        sb.append("&app_id="+gu.getUpstreamAppId());
        sb.append("&app_version="+gaReq.getApp().getAppVersion());
        sb.append("&app_package="+gu.getUpstreamPackageName());
        //广告位参数
        sb.append("&adslot_id="+gu.getUpstreamId());
        //设备参数
        int deviceType = gaReq.getDevice().getDeviceType();
        if (deviceType == 1){
            sb.append("&device_type=1");
        }else{
            sb.append("&device_type=2");
        }
        sb.append("&os_version="+gaReq.getDevice().getOsVersion());
        sb.append("&oaid="+gaReq.getDevice().getOaid());
        sb.append("&vendor="+ URLEncoder.encode(gaReq.getDevice().getVendor(), "UTF-8"));
        sb.append("&model="+ URLEncoder.encode(gaReq.getDevice().getModel(), "UTF-8"));
        sb.append("&screen_height="+gaReq.getDevice().getScreenHeight());
        sb.append("&screen_width="+gaReq.getDevice().getScreenWidth());
        sb.append("&ua="+URLEncoder.encode(gaReq.getDevice().getUa(), "UTF-8"));
        sb.append("&ppi="+gaReq.getDevice().getPpi());
        sb.append("&dpi=3");
        //设备唯一识别码
        sb.append("&android_id="+gaReq.getDevice().getAndroidId());
        sb.append("&serial_number=00000000");
        sb.append("&imei="+gaReq.getDevice().getImei());
        sb.append("&mac="+gaReq.getDevice().getMac());
        sb.append("&imsi="+gaReq.getDevice().getImsi());
        sb.append("&idfa="+gaReq.getDevice().getIdfa());
        //移动网络参数
        sb.append("&ipv4="+gaReq.getNetwork().getIp());
        sb.append("&connection_type="+gaReq.getNetwork().getConnectionType());
        sb.append("&operator_type="+gaReq.getNetwork().getOperatorType());
        //GPS
        sb.append("&longitude="+gaReq.getNetwork().getLon());
        sb.append("&latitude="+gaReq.getNetwork().getLat());

        return sb.toString();
    }


    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        //返回数据
        GetAdsResp gar = new GetAdsResp();
        gar.setRequestId(gaReq.getRequestId());

        DKResponse dkResponse = JSON.parseObject(backData,DKResponse.class);

        int code = dkResponse.getError_code();

        if (code == 0){
            Ads data = dkResponse.getAds().get(0);
            //广告信息
            Ad ya = new Ad();
            ya.setSlotId(gaReq.getSlot().getSlotId());
            ya.setAdKey(data.getAd_key());
            ya.setHtmlSnippet(data.getHtml_snippet());
            ya.setAdtext(data.getMob_adtext());
            ya.setAdlogo(data.getMob_adlogo());

            //广告详情
            MaterialMeta ym = new MaterialMeta();
            //标题
            ym.setAdTitle(data.getTitle());
            ym.setBrandName(data.getBrand_name());
            //广告描述
            List<String> descs = new ArrayList<>();
            if(null != data.getDescription()){
                descs.add(data.getDescription());
            }
            ym.setDescs(descs);
            //图片
            ym.setImageUrl(data.getImage_src());
            //图标
            List<String> iconUrls = new ArrayList<>();
            if(null != data.getIcon_src()){
                iconUrls.add(data.getIcon_src());
            }
            ym.setIconUrls(iconUrls);
            ym.setClickUrl(data.getClick_url());
            //交互类型 1浏览 2下载 3deeplink
            int interaction_type = data.getInteraction_type();

            if(1 == interaction_type){
                ym.setInteractionType(1);
            }else if(2 == interaction_type){
                ym.setInteractionType(2);

                //(广点通)是否特殊下载类；如果是特殊下载类，需要请求click_url，获取最终的下载地址
                boolean special_download = data.isSpecial_download();
                if (special_download){
                    ym.setProtocolType(1);
                }
                //当广告类型为下载类型时，并且appName 不为空时，会返回此字段。
                ym.setBrandName(data.getApp_name());
                ym.setPackageName(data.getApp_package());
                /**大小*/
                //ym.setAppSize(data.getApp_size());
                /**是否下载中间页*/
                data.isIs_download_middle_page();

                //下载
                ym.setWinDownloadUrls(data.getReport_startdown());
                //下载完成
                ym.setWinDownloadEndUrls(data.getReport_downsucc());
                //安装
                ym.setWinInstallUrls(data.getReport_startdown());
                //安装完成
                ym.setWinInstallEndUrls(data.getReport_installsucc());
                //安装后打开
                ym.setWinInstallOpenUrls(data.getReport_appactive());


            }else{
                ym.setInteractionType(0);
                ym.setDeepLink(true);
                ym.setDeepLinkUrl(data.getDeeplink_url());
                ym.setBrandName(data.getApp_name());
                ym.setPackageName(data.getApp_package());
                //安装完成
                ym.setWinInstallEndUrls(data.getReport_deeplink_install());


            }

            //视频类素材
            if (null != data.getVideo_url() && !"".equals(data.getVideo_url())){
                ym.setCreativeType(5);
                ym.setVideoUrl(data.getVideo_url());
                ym.setVideoDuration(data.getVideo_duration());
                //视频进度部分
                List<VideoPlay> vpList = data.getReport_video_play_percentage();
                for (VideoPlay vp : vpList){
                    if (vp.getSecond() == 0){

                    }else if (vp.getSecond() == data.getVideo_duration()){

                    }else{

                    }
                }


            }else{
                ym.setCreativeType(3);
            }
            //曝光
            List<String> winNoticeUrls = data.getReport_impress();
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&31&3");
            winNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(winNoticeUrls);
            //点击
            List<String> winCNoticeUrls = data.getReport_click();
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&31&4");
            winCNoticeUrls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(winCNoticeUrls);

            //综合封装返回
            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);
            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");

        } else if (code == 20100) {
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        } else if (code == 20000) {
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        } else {
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

}
