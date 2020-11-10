package com.zghd.service;

import com.util.md5.JiaMi;
import com.util.md5.MD5;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 有道
 */
@Service(value="ydService")
public class YDService {

    /**
     * 向后台发请求
     */
    public GetAdsResp YDSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;

        String data = formatData(gaReq, gu);
        String url = "http://gorgon.youdao.com/gorgon/request.s?"+data;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        if (code == 200){
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity,"utf-8");
            gar = formatBackData(str, gaReq, gu);
        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("500");
            gar.setMsg("SERVER_ERROR");
        }
        response.close();
        return gar;
    }

    /**
     * 封装入参参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        StringBuffer requestStr = new StringBuffer();
        requestStr.append("id="+gu.getUpstreamId());
        requestStr.append("&av="+gaReq.getApp().getAppVersion());
        int operatorType = gaReq.getNetwork().getOperatorType();
        //网络连接类型
        if (operatorType == 0){
            requestStr.append("&ct="+"0");
        } else if (operatorType == 1 || operatorType == 2 || operatorType == 3){
            requestStr.append("&ct="+"3");
        } else {
            requestStr.append("&ct="+"2");
        }
        //自网络连接类型
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            requestStr.append("&dct="+"11");
        } else if (connectionType == 3){
            requestStr.append("&dct="+"12");
        } else if (connectionType == 4){
            requestStr.append("&dct="+"13");
        } else {
            requestStr.append("&dct="+"0");
        }
        //1安卓 2ios
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            requestStr.append("&udid="+gaReq.getDevice().getAndroidId());
            requestStr.append("&auidmd5="+MD5.md5(gaReq.getDevice().getAndroidId()));
        }else{
            requestStr.append("&udid="+gaReq.getDevice().getIdfa());
            requestStr.append("&auidmd5="+MD5.md5(gaReq.getDevice().getIdfa()));
        }
        requestStr.append("&imei="+gaReq.getDevice().getImei());
        requestStr.append("&imeimd5="+MD5.md5(gaReq.getDevice().getImei()));
        requestStr.append("&aaid="+"12345");
        requestStr.append("&oaid="+gaReq.getRequestId());
        requestStr.append("&rip="+gaReq.getNetwork().getIp());
        requestStr.append("&ll="+gaReq.getNetwork().getLat()+","+gaReq.getNetwork().getLon());
        requestStr.append("&lla="+"100米/10米");
        requestStr.append("&llt="+"0.001");
        requestStr.append("&llp="+"g");
        requestStr.append("&wifi="+gaReq.getDevice().getMac());
        requestStr.append("&wifi="+gaReq.getDevice().getMac());
        requestStr.append("&dn="+URLEncoder.encode(gaReq.getDevice().getVendor(), "UTF-8")+","+URLEncoder.encode(gaReq.getDevice().getModel(), "UTF-8")+","+URLEncoder.encode(gaReq.getDevice().getBrand(), "UTF-8"));
        //return URLEncoder.encode(requestStr.toString(),"utf-8");
        return requestStr.toString();
    }

    /**
     * 封装回参参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        if (null != backData && !"".equals(backData)){
            JSONObject json = JSONObject.fromObject(backData);

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(json.getString("creativeid"));
            ya.setHtmlSnippet(json.getString("endcardhtml"));
            //ya.setAdtext();
            //ya.setAdlogo();

            //物料元
            MaterialMeta ym = new MaterialMeta();
            ym.setMaterialWidth(json.getInt("videowidth"));
            ym.setMaterialHeight(json.getInt("videoheight"));
            ym.setAppSize(json.getInt("videosize")/1000000);//(单位byte)
            ym.setVideoUrl(json.getString("videourl"));
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            if (json.has("title")){
                ym.setAdTitle(json.getString("title"));
            }
            if (json.has("text")){
                List<String> descs = new ArrayList<>();
                descs.add(json.getString("text"));
                ym.setDescs(descs);
            }
            if (json.has("iconimage")){
                List<String> icon = new ArrayList<>();
                icon.add(json.getString("iconimage"));
                ym.setIconUrls(icon);
            }
            if (json.has("mainimage")){
                List<String> image = new ArrayList<>();
                image.add(json.getString("mainimage"));
                ym.setImageUrl(image);
            }
            ym.setCreativeType(5);
            //广告类型 0落地页  1下载类
            int ydAdType = json.getInt("ydAdType");
            if (ydAdType == 0){
                ym.setInteractionType(1);
            }else if(ydAdType == 1){
                ym.setInteractionType(2);
                ym.setPackageName(json.getString("packageName"));
                ym.setBrandName(json.getString("appName"));
            }else{
                ym.setInteractionType(0);
            }
            ym.setClickUrl(json.getString("clk"));
            String videoduration = json.getString("videoduration");//00:00:13.760
            String [] duration = videoduration.split(":");
            double d = Double.valueOf(duration[2]);
            ym.setVideoDuration(new Double(d).intValue());

            //上报
            //视频加载
            ym.setWinLoadUrls(json.getJSONArray("videoloaded"));
            //点击
            ym.setWinCNoticeUrls(json.getJSONArray("clktrackers"));
            //下载完成
            ym.setWinDownloadEndUrls(json.getJSONArray("apkDownloadTrackers"));
            JSONObject tracks = json.getJSONObject("playtrackers");
            //关闭
            ym.setWinCloseUrls(tracks.getJSONArray("videoclose"));

            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            //静音
            Track track103 = new Track();
            track103.setType(103);
            track103.setUrls(tracks.getJSONArray("mute"));
            ydtTrackList.add(track103);
            //关闭静音
            Track track104 = new Track();
            track104.setType(104);
            track104.setUrls(tracks.getJSONArray("unmute"));
            ydtTrackList.add(track104);
            //视频进度上报
            JSONArray trackList = tracks.getJSONArray("playpercentage");
            for (int i = 0; i < trackList.size(); i++){
                JSONObject jo = JSONObject.fromObject(trackList.get(i));
                double checkpoint = jo.getDouble("checkpoint");
                if (checkpoint == 0.0){
                    Track track0 = new Track();
                    track0.setType(0);
                    track0.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track0);
                }else if (checkpoint == 0.25){
                    Track track1 = new Track();
                    track1.setType(1);
                    track1.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track1);
                }else if (checkpoint == 0.5){
                    Track track2 = new Track();
                    track2.setType(2);
                    track2.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track2);
                }else if (checkpoint == 0.75){
                    Track track3 = new Track();
                    track3.setType(3);
                    track3.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track3);
                }else if (checkpoint == 1.0){
                    List<String> urls = jo.getJSONArray("urls");
                    List<String> imptracker = json.getJSONArray("imptracker");
                    for (int j = 0; j < imptracker.size(); j++){
                        urls.add(imptracker.get(j));
                    }
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&16&3");
                    urls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    Track track4 = new Track();
                    track4.setType(4);
                    track4.setUrls(urls);
                    ydtTrackList.add(track4);
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
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }

}
