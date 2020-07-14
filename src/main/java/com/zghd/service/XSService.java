package com.zghd.service;

import com.util.md5.JiaMi;
import com.zghd.entity.XinSheng.*;
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

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 新笙激励视频
 */
@Service(value="xsService")
public class XSService {

    /**
     * 请求
     */
    public GetAdsResp XSSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        GetAdsResp gar = null;
        String data = formatData(gaReq, gu);
        //正式
        String url = "http://g.jssdk.net/getad?"+data;
        //测试
        //String url = "http://sx.g.jssdk.net/getad?"+data;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", gaReq.getDevice().getUa());
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        if (code == 200){
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity,"utf-8");
            gar = formatBackData(str, gaReq, gu);
        }else{
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

        Req req = new Req();
        req.setReqid(gaReq.getRequestId());
        req.setVersion("1.4.1");
        String reqStr  = JSONObject.fromObject(req).toString();
        requestStr.append("req_info="+URLEncoder.encode(reqStr,"utf-8"));

        Media media = new Media();
        media.setBf("100");
        media.setSlotid("appId");
        media.setUid("100001:e0e38550");
        media.setApp_version(gaReq.getApp().getAppVersion());
        media.setPackage_name(gu.getUpstreamPackageName());
        String mediaStr  = JSONObject.fromObject(media).toString();
        requestStr.append("&media="+URLEncoder.encode(mediaStr,"utf-8"));

        AdSlot adSlot = new AdSlot();
        adSlot.setSlotid(gu.getUpstreamId());
        adSlot.setMin(0);
        adSlot.setMax(60);
        String adSlotStr  = JSONObject.fromObject(adSlot).toString();
        requestStr.append("&adslot="+URLEncoder.encode(adSlotStr,"utf-8"));

        Mobile mobile = new Mobile();
        mobile.setAndroid_id(gaReq.getDevice().getAndroidId());
        mobile.setDensity(gaReq.getDevice().getPpi()+"");
        mobile.setOperator(0);
        mobile.setNetwork(0);
        mobile.setWidth(gaReq.getDevice().getScreenWidth());
        mobile.setHeight(gaReq.getDevice().getScreenHeight());
        mobile.setOs(gaReq.getDevice().getOsType());
        mobile.setOs_version(gaReq.getDevice().getOsVersion());
        mobile.setVendor(gaReq.getDevice().getVendor());
        mobile.setModel(gaReq.getDevice().getModel());
        mobile.setMac(gaReq.getDevice().getMac());
        mobile.setUdid(gaReq.getDevice().getImei());
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            mobile.setIdentify_type("imei");
        }else{
            mobile.setIdentify_type("idfa");
        }
        String mobileStr  = JSONObject.fromObject(mobile).toString();
        requestStr.append("&mobile="+URLEncoder.encode(mobileStr,"utf-8"));

        Network network = new Network();
        network.setIp(gaReq.getNetwork().getIp());
        network.setUa(gaReq.getDevice().getUa());
        network.setHttp_type(0);
        network.setIp_type(0);
        String networkStr  = JSONObject.fromObject(network).toString();
        requestStr.append("&network="+URLEncoder.encode(networkStr,"utf-8"));

        Coordinate coordinate = new Coordinate();
        coordinate.setCtype(1);
        coordinate.setLng(0);
        coordinate.setLat(0);
        coordinate.setTimestamp(new Date().getTime());
        String coordinateStr  = JSONObject.fromObject(coordinate).toString();
        requestStr.append("&coordinate="+URLEncoder.encode(coordinateStr,"utf-8"));

        return requestStr.toString();
    }

    /**
     * 封装入参参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(backData);
        if (json.has("ad")){
            JSONObject ad = JSONObject.fromObject(json.getJSONArray("ad").get(0));

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(ad.getString("aid"));

            //物料元
            MaterialMeta ym = new MaterialMeta();
            ym.setAdTitle(ad.getString("title"));
            List<String> desc = new ArrayList<>();
            desc.add(ad.getString("desc"));
            ym.setDescs(desc);
            if (ad.has("ext_urls")){
                ym.setImageUrl(ad.getJSONArray("ext_urls"));
            }
            ym.setMaterialWidth(ad.getInt("width"));
            ym.setMaterialHeight(ad.getInt("height"));
            ym.setClickUrl(ad.getString("url"));
            //0下载 1浏览器打开 2通知栏推送
            int action = ad.getInt("action");
            if (action == 0){
                ym.setInteractionType(2);
                JSONObject app = ad.getJSONObject("app");
                if (app.has("icon_url")){
                    List<String> icons = new ArrayList<>();
                    icons.add(app.getString("icon_url"));
                    ym.setIconUrls(icons);
                }
                ym.setPackageName(app.getString("package_name"));
                ym.setBrandName(app.getString("name"));

                //判断广点通广告download_urls是否有__CLICK_ID__这个宏参数
                int gdt = gdt(ad.getJSONArray("download_urls"));
                if (gdt == 1){
                    ym.setProtocolType(1);
                }

                //下载
                ym.setWinDownloadUrls(macroParam(ad.getJSONArray("download_urls")));
                //下载完成
                ym.setWinDownloadEndUrls(macroParam(ad.getJSONArray("downloaded_urls")));
                //安装
                ym.setWinInstallUrls(macroParam(ad.getJSONArray("install_urls")));
                //安装完成
                ym.setWinInstallEndUrls(macroParam(ad.getJSONArray("installed_urls")));
            }else if (action == 1){
                ym.setInteractionType(1);
            }else{
                ym.setInteractionType(0);
            }
            //物料创意类型 txt:文字链 icon:图文 c:富媒体 mp4:视频
            String mime = ad.getString("mime");
            if(mime.equals("c")){
                ya.setHtmlSnippet(ad.getString("html"));
                ym.setCreativeType(4);
            }else if (mime.equals("txt")){
                ym.setCreativeType(1);
            }else if (mime.equals("icon")){
                ym.setCreativeType(3);
            }else if (mime.equals("mp4")){
                ym.setCreativeType(5);
            }
            ym.setVideoUrl(ad.getString("src"));
            ym.setVideoDuration(ad.getInt("duration"));

            //上报
            //展现曝光
            List<String> winNotice = macroParam(ad.getJSONObject("imp").getJSONArray("0"));
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-14-3");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(winNotice);
            //点击
            List<String> clk = macroParam(ad.getJSONArray("clk"));
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-14-4");
            clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(clk);

            JSONObject tracking = ad.getJSONObject("video_tracking");
            if (tracking.has("images")){
                ym.setImageUrl(tracking.getJSONArray("images"));
            }
            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            //关闭
            ym.setWinCloseUrls(macroParam(tracking.getJSONArray("close")));
            //开始
            Track track0 = new Track();
            track0.setType(0);
            track0.setUrls(macroParam(tracking.getJSONArray("start")));
            ydtTrackList.add(track0);
            ya.setTracks(ydtTrackList);
            //结束
            Track track4 = new Track();
            track4.setType(4);
            track4.setUrls(macroParam(tracking.getJSONArray("end")));
            ydtTrackList.add(track4);
            ya.setTracks(ydtTrackList);
            //暂停
            Track track107 = new Track();
            track107.setType(107);
            track107.setUrls(macroParam(tracking.getJSONArray("pause")));
            ydtTrackList.add(track107);
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

    //判断是不是广点通的广告
    public int gdt(List<String> urls){
        String url = urls.get(0);
        int index = url.indexOf("__CLICK_ID__");
        if (index != -1){
            return 1;
        }else{
            return 0;
        }

    }

    //宏替换
    public List<String> macroParam(List<String> list){
        List returnList = new ArrayList();
        for (int i = 0; i < list.size(); i++){
            String s = list.get(i);
            //替换__AZMX__
            Pattern pazmx = Pattern.compile("__AZMX__");
            Matcher mazmx = pazmx.matcher(s);
            s = mazmx.replaceAll("__DOWN_X__");

            //替换__AZMY__
            Pattern pazmy = Pattern.compile("__AZMY__");
            Matcher mazmy = pazmy.matcher(s);
            s = mazmy.replaceAll("__DOWN_Y__");

            //替换__AZCX__
            Pattern pazcx = Pattern.compile("__AZCX__");
            Matcher mazcx = pazcx.matcher(s);
            s = mazcx.replaceAll("__UP_X__");

            //替换__AZCY__
            Pattern pazcy = Pattern.compile("__AZCY__");
            Matcher mazcy = pazcy.matcher(s);
            s = mazcy.replaceAll("__UP_Y__");
            returnList.add(i, s);
        }
        return returnList;
    }

    //__AZMX__   __AZMY__   __AZCX__   __AZCY__
    public static void main(String[] args) {
        /*String a = "aaa1__AZMX__a2a__AZMY__a3a";
        String b = "bbb2__AZCX__b2b__AZCY__b5b";
        List<String> list = new ArrayList<>();
        list.add(a);

        list.add(b);
        list = macroParam(list);
        for (String s : list){
            System.out.println(s);
        }*/
        Pattern p = Pattern.compile("__clik_id__");
        String instring = "asd_ss____click_id__kj_sss";
        Matcher m = p.matcher(instring);
        String tmp = m.replaceAll("这是新的");

    }


}
