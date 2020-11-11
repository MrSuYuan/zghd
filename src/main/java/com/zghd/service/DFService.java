package com.zghd.service;

import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
 * 东方激励视频
 */
@Service(value="dfService")
public class DFService {

    /**
     * 东方请求
     */
    public GetAdsResp DFSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        GetAdsResp gar;
        String URL;
        //测试
        if("TPXjOu".equals(gu.getUpstreamAppId())){
            URL = "http://62.234.201.244/export/";
            //正式
        }else{
            URL = "http://rellatice.tt.cn/export/";
        }
        //参数转换
        String data = formatData(gaReq);
        CloseableHttpResponse response;
        CloseableHttpClient httpclient;
        //String reqParams = URL+"nIDBdd.api?apid=6p3EQ8Ca2lTb"+data;
        String reqParams = URL+gu.getUpstreamAppId()+".api?apid="+gu.getUpstreamId()+data;
        httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqParams);
        response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        entity.writeTo(buf);
        String backData = new String(buf.toByteArray(),"utf-8");
        if (null != backData && !"".equals(backData)){
            //整理返回参数
            JSONObject json = JSONObject.fromObject(backData);
            //状态码（1000:无广告返回,1001:正常返回）
            String code = json.getString("code");
            if("1000".equals(code)) {
                gar = new GetAdsResp();
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");
            }else{
                //格式化返回数据,放到流里面返回给下游
                gar = formatBackData(json, gaReq, gu);
                gar.setErrorCode("200");
                gar.setMsg("SUCCESS");
            }
        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

    /**
     * 格式化前端传来的参数
     */
    public String formatData(GetAdsReq gaReq){
        StringBuffer sb = new StringBuffer();
        sb.append("&appVer="+gaReq.getApp().getAppVersion());
        sb.append("&clientIp="+gaReq.getNetwork().getIp());
        sb.append("&deviceId="+gaReq.getDevice().getAndroidId());

        sb.append("&lat="+gaReq.getNetwork().getLat());
        sb.append("&lng="+gaReq.getNetwork().getLon());
        sb.append("&mCount=1");
        sb.append("&mHeight="+gaReq.getSlot().getSlotheight());
        sb.append("&mWidth="+gaReq.getSlot().getSlotwidth());
        sb.append("&mStyle=018");
        sb.append("&mac="+gaReq.getDevice().getMac());
        sb.append("&model="+URLEncoder.encode(gaReq.getDevice().getModel()));
        int net =gaReq.getNetwork().getConnectionType()==100?1:gaReq.getNetwork().getConnectionType();
        sb.append("&network="+net);
        sb.append("&operater="+gaReq.getNetwork().getOperatorType());
        sb.append("&os=");
        if(gaReq.getDevice().getOsType()==2) {
            sb.append("Ios");
            sb.append("&imei="+gaReq.getDevice().getIdfa());
        }else {
            sb.append("Android");
            sb.append("&imei="+gaReq.getDevice().getImei());
        }
        sb.append("&osVersion="+URLEncoder.encode(gaReq.getDevice().getOsVersion()));
        sb.append("&packageName="+URLEncoder.encode(gaReq.getApp().getAppPackage()));
        sb.append("&platform="+gaReq.getDevice().getOsType());
        sb.append("&screenHeight="+gaReq.getDevice().getScreenHeight());
        sb.append("&screenWidth="+gaReq.getDevice().getScreenWidth());
        sb.append("&userAgent="+URLEncoder.encode(gaReq.getDevice().getUa()));
        sb.append("&vendor="+URLEncoder.encode(gaReq.getDevice().getVendor()));
        return sb.toString();
    }

    /**
     * 格式化东方返回数据
     */
    public GetAdsResp formatBackData(JSONObject json, GetAdsReq gaReq, GetUpstream gu) {
        GetAdsResp gar = new GetAdsResp();
        JSONObject data = JSONObject.fromObject(JSONArray.fromObject(json.getString("data")).get(0));
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        //广告主体
        Ad ya = new Ad();
        ya.setAdKey("");

        //视频内容
        MaterialMeta ym = new MaterialMeta();
        ym.setAdTitle(data.getString("title"));
        List<String> descs = new ArrayList<>();
        descs.add(data.getString("subTitle"));
        ym.setDescs(descs);
        int mType = data.getInt("mType");
        if(mType == 0){
            ym.setInteractionType(1);
            ym.setClickUrl(data.getString("landingLink"));
        }else if(mType == 1){
            ym.setInteractionType(2);
            ym.setClickUrl(data.getString("downLink"));
        }else if(mType == 2){
            ym.setInteractionType(0);
            ym.setClickUrl(data.getString("deepLink"));
        }
        List<String> icon = new ArrayList<>();
        icon.add(data.getString("iconUrl"));
        ym.setIconUrls(icon);
        //图片素材集合
        JSONArray imageList = data.getJSONArray("imageMaterial");
        List<String> image = new ArrayList<>();
        for(int i=0;i<imageList.size();i++){
            String src = JSONObject.fromObject(imageList.get(i)).getString("src");
            image.add(i,src);
        }
        ym.setImageUrl(image);
        //视频素材集合
        JSONObject video = JSONObject.fromObject(data.getJSONArray("videoMaterial").get(0));
        ym.setVideoUrl(video.getString("videoLink"));
        ym.setVideoDuration(video.getInt("videoTime")/1000);

        //曝光展现
        ym.setWinNoticeUrls(data.getJSONArray("inViewReport"));
        //点击
        ym.setWinCNoticeUrls(data.getJSONArray("clickReport"));
        //开始下载
        ym.setWinDownloadUrls(data.getJSONArray("startDownReport"));
        //下载完成
        ym.setWinDownloadEndUrls(data.getJSONArray("endDownReport"));

        //视频播放监控
        List<Track> TrackList = new ArrayList<>();
        Track yt = new Track();
        yt.setType(4);
        yt.setUrls(data.getJSONArray("finishReport"));
        TrackList.add(yt);
        ya.setTracks(TrackList);

        //综合封装返回
        List ymList = new ArrayList();
        ymList.add(ym);
        ya.setMetaGroup(ymList);
        List yaList = new ArrayList();
        yaList.add(ya);
        gar.setAds(yaList);

        return gar;
    }
}
