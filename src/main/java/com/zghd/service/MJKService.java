package com.zghd.service;

import com.util.md5.EncryptUtil;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 迈吉客激励视频
 */
@Service(value="mjkService")
public class MJKService {

    /**
     * 向极光后台发请求
     */
    public GetAdsResp MJKSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar = null;
        String data = formatData(gaReq, gu);
        String uri = "http://adx3.milatu.cn:8188/v/showad?key1=value1&key2=value2"+data;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("User-Agent", gaReq.getDevice().getUa());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            gar = formatBackData(EntityUtils.toString(entity,"utf-8"), gaReq, gu);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append("&aid="+gu.getUpstreamAppId());
        sb.append("&sid="+gu.getUpstreamId());
        sb.append("&devicetype="+gaReq.getDevice().getDeviceType());
        sb.append("&ostype="+gaReq.getDevice().getOsType());
        sb.append("&imei="+gaReq.getDevice().getImei());
        sb.append("&idfa="+gaReq.getDevice().getIdfa());
        sb.append("&idfv=");
        sb.append("&imsi=-1");
        sb.append("&mac="+gaReq.getDevice().getMac());
        sb.append("&adrid="+gaReq.getDevice().getAndroidId());
        sb.append("&opid=");
        sb.append("&osv="+gaReq.getDevice().getOsVersion());
        sb.append("&dv="+gaReq.getDevice().getVendor());
        sb.append("&dm="+gaReq.getDevice().getModel());
        sb.append("&nt=0");
        sb.append("&sw="+gaReq.getDevice().getScreenWidth());
        sb.append("&sh="+gaReq.getDevice().getScreenHeight());
        sb.append("&w="+gaReq.getSlot().getSlotwidth());
        sb.append("&h="+gaReq.getSlot().getSlotheight());
        sb.append("&ip="+gaReq.getNetwork().getIp());
        sb.append("&useragent="+URLEncoder.encode(gaReq.getDevice().getUa(),"UTF-8"));
        sb.append("&sn=-1");
        sb.append("&son=1");
        sb.append("&lac=");
        sb.append("&cellularid=");
        return sb.toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        JSONObject json = JSONObject.fromObject(backData);
        String res = json.getString("res");
        //0无广告 1正常返回
        if("1".equals(res)){
            List<JSONObject> ads = json.getJSONArray("ads");
            JSONObject ad = ads.get(0);

            //广告主体
            Ad ya = new Ad();
            ya.setHtmlSnippet(ad.getString("renderhtml"));

            //视频内容
            MaterialMeta ym = new MaterialMeta();
            ym.setVideoUrl(ad.getString("videourl"));
            ym.setVideoDuration(ad.getInt("videoduration")/1000);
            List<String> image_url = new ArrayList<>();
            image_url.add(ad.getString("image_url"));
            ym.setImageUrl(image_url);
            List<String> icon_url = new ArrayList<>();
            icon_url.add(ad.getString("icon_url"));
            ym.setIconUrls(icon_url);
            ym.setAdTitle(ad.getString("title"));
            List<String> descs = new ArrayList<>();
            descs.add(ad.getString("description"));
            ym.setDescs(descs);
            ym.setMaterialHeight(ad.getInt("height"));
            ym.setMaterialWidth(ad.getInt("width"));
            ym.setAppSize(ad.getInt("appsize"));
            boolean isdown = ad.getBoolean("isdown");
            if(isdown == true){
                ym.setInteractionType(2);
                ym.setClickUrl(ad.getString("fileurl"));
            }else{
                ym.setInteractionType(0);
                ym.setClickUrl(ad.getString("landingurl"));
            }
            ym.setCreativeType(4);
            ym.setPackageName(ad.getString("pkgname"));

            EncryptUtil eu = new EncryptUtil();
            //视频加载成功
            ym.setWinLoadUrls(ad.getJSONArray("ReportVideoLoadSuccessUrl"));

            //曝光展现
            List<String> nL = ad.getJSONArray("ReportVideoShowUrl");
            String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&10&3","zghd");
            nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(nL);

            //点击
            List<String> cL = ad.getJSONArray("ReportVideoClickUrl");
            String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&10&4","zghd");
            cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(cL);

            //关闭
            ym.setWinCloseUrls(ad.getJSONArray("ReportVideoCloseUrl"));

            //下载
            ym.setWinDownloadUrls(ad.getJSONArray("ReportDownBeginLoadUrl"));

            //下载完成
            ym.setWinDownloadEndUrls(ad.getJSONArray("ReportDownloadCompleteUrl"));

            //安装
            ym.setWinInstallUrls(ad.getJSONArray("ReportInstallBeginLoadUrl"));

            //激活
            ym.setWinActiveUrls(ad.getJSONArray("ReportActiveCompleteUrl"));

            //视频播放进度
            List<JSONObject> trackList = ad.getJSONArray("VideoCheckPointList");
            List<Track> ydtTrackList = new ArrayList<>();
            for(int i = 0; i < trackList.size(); i++){
                JSONObject track = trackList.get(i);
                double CheckPoint = track.getDouble("CheckPoint");
                if(CheckPoint == 0.25){
                    Track yt = new Track();
                    yt.setType(1);
                    yt.setUrls(track.getJSONArray("Urls"));
                    ydtTrackList.add(yt);
                }else if(CheckPoint == 0.5){
                    Track yt = new Track();
                    yt.setType(2);
                    yt.setUrls(track.getJSONArray("Urls"));
                    ydtTrackList.add(yt);
                }else if(CheckPoint == 0.75){
                    Track yt = new Track();
                    yt.setType(3);
                    yt.setUrls(track.getJSONArray("Urls"));
                    ydtTrackList.add(yt);
                }else if(CheckPoint == 1){
                    Track yt = new Track();
                    yt.setType(4);
                    yt.setUrls(track.getJSONArray("Urls"));
                    ydtTrackList.add(yt);
                }else if(CheckPoint == 0){
                    Track yt = new Track();
                    yt.setType(0);
                    yt.setUrls(track.getJSONArray("Urls"));
                    ydtTrackList.add(yt);
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
            return gar;
        }else if("0".equals(res)){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
            return gar;
        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
            return gar;
        }
    }

}
