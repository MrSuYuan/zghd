package com.zghd.service;

import com.util.md5.JiaMi;
import com.zghd.entity.YiDianTong.Device;
import com.zghd.entity.YiDianTong.Network;
import com.zghd.entity.YiDianTong.RequestJson;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 一点通激励视频
 */
@Service(value="ydtService")
public class YDTService {

    /**
     * 一点通请求
     */
    public GetAdsResp YDTSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{

        GetAdsResp gar;
        //将下游参数都转为一点通的参数
        String data = formatData(gaReq, gu);
        String uri = "";
        //http://api.ydtad.com:8100/api/ads

        if("TV00001".equals(gu.getUpstreamId())){
            //测试版
            uri = "http://39.97.28.133:8898/api/test/ads";
        }else{
            //正式版
            uri = "http://39.97.28.133:8898/api/ads";
        }

        CloseableHttpResponse resp = null;
        CloseableHttpClient httpclient = null;
        httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        resp = httpclient.execute(httpPost);

        HttpEntity result = resp.getEntity();
        String str = EntityUtils.toString(result, "utf-8");
        if(null != str && !"".equals(str)){
            //格式化返回数据,放到流里面返回给下游
            gar = formatBackData(str, gaReq, gu);

        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

    /**
     * 参数转换-将下游参数转换为一点通参数类型
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) {
        //返回数据
        RequestJson rj = new RequestJson();

        //requestId
        rj.setRequestId(gaReq.getRequestId());
        rj.setChannelId(gu.getUpstreamId());

        //设备信息
        Device device = new Device();
        device.setIdfa(gaReq.getDevice().getIdfa());
        device.setImei(gaReq.getDevice().getImei());
        device.setMac(gaReq.getDevice().getMac());
        device.setAndroidId(gaReq.getDevice().getAndroidId());
        device.setModel(gaReq.getDevice().getModel());
        device.setVendor(gaReq.getDevice().getVendor());
        device.setScreenWidth(gaReq.getDevice().getScreenWidth());
        device.setScreenHeight(gaReq.getDevice().getScreenHeight());
        device.setOsType(gaReq.getDevice().getOsType());
        device.setOsVersion(gaReq.getDevice().getOsVersion());
        device.setDeviceType(gaReq.getDevice().getDeviceType());
        device.setUa(gaReq.getDevice().getUa());
        device.setPpi(gaReq.getDevice().getPpi());
        device.setScreenOrientation(gaReq.getDevice().getScreenOrientation());
        device.setBrand(gaReq.getDevice().getBrand());
        device.setImsi(gaReq.getDevice().getImsi());
        rj.setDevice(device);

        //网络信息
        Network nw = new Network();
        nw.setIp(gaReq.getNetwork().getIp());
        nw.setConnectionType(gaReq.getNetwork().getConnectionType());
        nw.setOperatorType(gaReq.getNetwork().getOperatorType());
        nw.setLat(0f);
        nw.setLon(0f);
        rj.setNetwork(nw);

        return JSONObject.fromObject(rj).toString();
    }

    /**
     * 格式化一点通返回数据
     */
    public GetAdsResp formatBackData(String entityStr, GetAdsReq gaReq, GetUpstream gu){
        //返回整体
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(entityStr);
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        if("1".equals(json.getString("errorCode"))){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
            return gar;
        }else if("0".equals(json.getString("errorCode"))){
            String ads = json.getString("ads");
            if (null != ads) {
                String ad = JSONArray.fromObject(ads).get(0).toString();
                JSONObject adJson = JSONObject.fromObject(ad);

                //广告主体
                Ad ya = new Ad();
                ya.setSlotId(gaReq.getSlot().getSlotId());
                ya.setAdKey(adJson.getString("adKey"));
                ya.setHtmlSnippet(adJson.getString("htmlSnippet"));
                ya.setAdtext(adJson.getString("adtext"));
                ya.setAdlogo(adJson.getString("adlogo"));

                //视频内容
                MaterialMeta ym = new MaterialMeta();
                JSONObject video = JSONObject.fromObject(JSONArray.fromObject(adJson.getString("metaGroup")).get(0));
                if(null != video.getString("descs") && !"null".equals(video.getString("descs"))){
                    ym.setDescs(video.getJSONArray("descs"));
                }
                if(null != video.getString("imageUrl")){
                    ym.setImageUrl(video.getJSONArray("imageUrl"));
                }
                ym.setMaterialHeight(video.getInt("materialHeight"));
                ym.setMaterialWidth(video.getInt("materialWidth"));
                if(null != video.getString("iconUrls") && !"null".equals(video.getString("iconUrls"))){
                    ym.setIconUrls(video.getJSONArray("iconUrls"));
                }
                ym.setClickUrl(video.getString("clickUrl"));
                ym.setCreativeType(video.getInt("creativeType"));
                ym.setInteractionType(video.getInt("interactionType"));
                ym.setPackageName(video.getString("packageName"));
                ym.setAppSize(video.getInt("appSize"));
                ym.setAdTitle(video.getString("adTitle"));
                ym.setVideoDuration(video.getInt("videoDuration"));
                ym.setVideoUrl(video.getString("videoUrl"));
                ym.setBrandName(video.getString("brandName"));
                //下载类特殊处理字段
                if (adJson.has("protocolType")){
                    ym.setProtocolType(adJson.getInt("protocolType"));
                }

                //曝光展现
                if(null != video.getString("winNoticeUrls") && "null"!= video.getString("winNoticeUrls")){
                    List<String> nL = video.getJSONArray("winNoticeUrls");
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-5-3");
                    nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    ym.setWinNoticeUrls(nL);
                }
                //点击
                if(null != video.getString("winCNoticeUrls") && "null"!= video.getString("winCNoticeUrls")){
                    List<String> cL = video.getJSONArray("winCNoticeUrls");
                    String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-5-4");
                    cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    ym.setWinCNoticeUrls(cL);
                }else{
                    List<String> cL = new ArrayList<>();
                    String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-5-4");
                    cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    ym.setWinCNoticeUrls(cL);
                }
                //跳过
                if(null != video.getString("arrSkipTrackUrl") && "null"!= video.getString("arrSkipTrackUrl")){
                    ym.setWinIgnoreUrls(video.getJSONArray("arrSkipTrackUrl"));
                }
                //下载
                if(null != video.getString("arrDownloadTrackUrl") && "null"!= video.getString("arrDownloadTrackUrl")){
                    ym.setWinDownloadUrls(video.getJSONArray("arrDownloadTrackUrl"));
                }
                //下载完成
                if(null != video.getString("arrDownloadedTrakUrl") && "null"!= video.getString("arrDownloadedTrakUrl")){
                    ym.setWinDownloadEndUrls(video.getJSONArray("arrDownloadedTrakUrl"));
                }
                //安装
                if(null != video.getString("arrIntallTrackUrl") && "null"!= video.getString("arrIntallTrackUrl")){
                    ym.setWinInstallUrls(video.getJSONArray("arrIntallTrackUrl"));
                }
                //安装完成
                if(null != video.getString("arrIntalledTrackUrl") && "null"!= video.getString("arrIntalledTrackUrl")){
                    ym.setWinInstallEndUrls(video.getJSONArray("arrIntalledTrackUrl"));
                }
                //视频监控和播放上报
                List<Track> ydtTrackList = new ArrayList<>();
                boolean tracksKey = adJson.has("tracks");
                if(tracksKey){
                    JSONArray tracks = adJson.getJSONArray("tracks");
                    if(null != adJson.getString("tracks") && "null"!= adJson.getString("tracks")){
                        for(int i=0;i<tracks.size();i++){
                            JSONObject track = JSONObject.fromObject(tracks.get(i));
                            int type = track.getInt("type");
                            //视频加载完成
                            if(type == 101019){
                                ym.setWinLoadUrls(track.getJSONArray("urls"));
                                continue;
                            }
                            //完成激励上报
                            if(type == 101017){
                                ym.setWinCompleteUrls(track.getJSONArray("urls"));
                                continue;
                            }
                            //关闭
                            if(type == 2){
                                ym.setWinCloseUrls(track.getJSONArray("urls"));
                                continue;
                            }

                            // 视频类广告展示
                            //开始 0%
                            if (type == 101000) {
                                Track yt = new Track();
                                yt.setType(0);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //结束 100%
                            if (type == 101004) {
                                Track yt = new Track();
                                yt.setType(4);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //全屏
                            if (type == 101001) {
                                Track yt = new Track();
                                yt.setType(101);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //退出全屏
                            if (type == 101013) {
                                Track yt = new Track();
                                yt.setType(102);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //静音
                            if (type == 101005) {
                                Track yt = new Track();
                                yt.setType(103);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //关闭静音
                            if (type == 101006) {
                                Track yt = new Track();
                                yt.setType(104);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //上滑
                            if (type == 101014) {
                                Track yt = new Track();
                                yt.setType(105);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //下滑
                            if (type == 101015) {
                                Track yt = new Track();
                                yt.setType(106);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //暂停
                            if (type == 101007) {
                                Track yt = new Track();
                                yt.setType(107);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //暂停后继续播放
                            if (type == 101009) {
                                Track yt = new Track();
                                yt.setType(108);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //重播
                            if (type == 101010) {
                                Track yt = new Track();
                                yt.setType(109);
                                yt.setUrls(track.getJSONArray("urls"));
                                ydtTrackList.add(yt);
                            }
                            //异常停止
                            if (type == 101004) {
                                Track yt = new Track();
                                yt.setType(110);
                                yt.setUrls(track.getJSONArray("urls"));
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
            }else{
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");
            }
        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
            return gar;
        }
        return gar;
    }
}