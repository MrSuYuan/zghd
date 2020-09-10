package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.zghd.entity.AZhe.response.AZResponse;
import com.zghd.entity.AZhe.response.TargetAddition;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 阿哲
 */
@Service(value="azService")
public class AZService {

    /**
     * 向阿哲发送请求
     */
    public GetAdsResp AZSend(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        //入参参数
        String data = formatData(gaReq, gu);
        String url = "http://test-ad.api.cloudmob.xyz/ads";//测试
        //String url = "http://ad.api.cloudmob.xyz/ads";//正式
        String reqParams = url + "?" + data;
        System.out.println("入参:"+reqParams);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqParams);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        entity.writeTo(buf);
        String str = new String(buf.toByteArray(),"utf-8");
        System.out.println("入参:"+str);
        //回参参数
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }


    /**
     * 格式化参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){

        StringBuffer sb = new StringBuffer();
        sb.append("req_source="+gu.getUpstreamId());
        sb.append("&ua="+URLEncoder.encode(gaReq.getDevice().getUa()));
        sb.append("&mac="+gaReq.getDevice().getMac());
        sb.append("&os_version="+gaReq.getDevice().getOsVersion());
        sb.append("&ip="+gaReq.getNetwork().getIp());
        sb.append("&model="+URLEncoder.encode(gaReq.getDevice().getModel()));
        sb.append("&brand="+URLEncoder.encode(gaReq.getDevice().getBrand()));
        sb.append("&android_id="+gaReq.getDevice().getAndroidId());
        if(gaReq.getDevice().getOsType()==2) {
            sb.append("&platform="+2);
            sb.append("&imei="+gaReq.getDevice().getIdfa());
        }else {
            sb.append("&platform="+1);
            sb.append("&imei="+gaReq.getDevice().getImei());
        }
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 100 || connectionType == 5 || connectionType == 0){
            sb.append("&network_type="+ 9);
        }else{
            sb.append("&network_type="+ connectionType);
        }
        sb.append("&screen_size="+gaReq.getDevice().getScreenWidth()+"x"+gaReq.getDevice().getScreenHeight());
        sb.append("&script_order="+3);

        return sb.toString();
    }


    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        AZResponse resp = JSON.parseObject(backData,AZResponse.class);

        int result = resp.getResult();
        if (result == 0){
            //广告主体
            Ad ya = new Ad();

            //物料元
            MaterialMeta ym = new MaterialMeta();
            List<String> images = new ArrayList<>();
            images.add(resp.getImage_url());
            ym.setImageUrl(images);
            ym.setCreativeType(5);
            ym.setInteractionType(3);
            ym.setClickUrl(resp.getTarget());
            ym.setVideoUrl(resp.getVideo_url());
            ym.setVideoDuration(resp.getDuration());
            ym.setUser_agent(resp.getUser_agent());
            ym.setBrowser_ua(resp.getBrowser_ua());
            ym.setJs_order_id(resp.getJs_order_id());
            ym.setReq_source(gu.getUpstreamId());

            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();

            List<TargetAddition> targetAdditions = resp.getTarget_addition();
            for (TargetAddition tar : targetAdditions){
                String type = tar.getType();
                //VIEW(曝光)
                if (type.equals("VIEW")){
                    List<String> winNotice = tar.getUrls();
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&27&3");
                    winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    ym.setWinNoticeUrls(winNotice);
                }
                //CLICK（点击）
                if (type.equals("CLICK")){
                    //点击
                    List<String> cL = tar.getUrls();
                    String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&27&4");
                    cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    ym.setWinCNoticeUrls(cL);
                }
                //VIDEO_FINISH（播放完成）
                if (type.equals("VIDEO_FINISH")){
                    Track track = new Track();
                    track.setType(4);
                    track.setUrls(tar.getUrls());
                    ydtTrackList.add(track);
                }
                //CLOSE（关闭）
                if (type.equals("CLOSE")){
                    ym.setWinCloseUrls(tar.getUrls());
                }
                //VIDEO_TIMER（播放定时打点）
                if (type.equals("VIDEO_TIMER")){

                }
            }

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
            gar.setRequestId(gaReq.getRequestId());
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }


}
