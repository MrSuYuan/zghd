package com.zghd.service;

import com.util.md5.EncryptUtil;
import com.util.md5.MD5;
import com.zghd.entity.RuiXi.*;
import com.zghd.entity.RuiXi.Device;
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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 瑞郗广告请求
 */
@Service(value="rxService")
public class RXService {
    /**
     * 瑞郗广告请求
     */
    public GetAdsResp RXSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        //将下游参数都转为瑞郗的参数
        String data = formatData(gaReq, gu);
        String url = "http://ssp.wruixi.com/ssp/v1";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpclient.execute(httpPost);

        HttpEntity result = resp.getEntity();
        String str = EntityUtils.toString(result, "utf-8");
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }

    /**
     * 参数转换
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) {

        //参数组合
        RXData data = new RXData();
        String key = "eae552d85ef698cd";
        String sign = MD5.md5(key+gu.getUpstreamAppId());
        data.setToken(gu.getUpstreamAppId());
        data.setSign(sign);

        //request参数
        RXRequest request = new RXRequest();
        request.setAppName(gaReq.getApp().getAppName());
        request.setPackageName(gaReq.getApp().getAppPackage());
        request.setAppVersion(gaReq.getApp().getAppVersion());

        //广告位参数
        AdSlot adSlot = new AdSlot();
        adSlot.setAdslotId(gu.getUpstreamId());
        adSlot.setWidth(gaReq.getSlot().getSlotwidth());
        adSlot.setHeight(gaReq.getSlot().getSlotheight());
        //是否支持deeplink  0默认不支持  1支持
        adSlot.setDeeplink(1);
        request.setAdslot(adSlot);

        //设备参数
        Device device = new Device();
        device.setDeviceType(1);
        //1android 2ios
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setOsType(1);
            device.setAndroidId(gaReq.getDevice().getAndroidId());
        }else{
            device.setOsType(2);
            device.setIdfa(gaReq.getDevice().getIdfa());
        }
        device.setOsVersion(gaReq.getDevice().getOsVersion());
        device.setScreenWidth(gaReq.getDevice().getScreenWidth());
        device.setScreenHeight(gaReq.getDevice().getScreenHeight());
        device.setImei(gaReq.getDevice().getImei());
        device.setImsi(gaReq.getDevice().getImsi());
        device.setOaid(gaReq.getDevice().getOaid());
        device.setMac(gaReq.getDevice().getMac());
        device.setVendor(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        device.setUa(gaReq.getDevice().getUa());
        device.setOrientation(0);
        device.setDensity(gaReq.getDevice().getPpi()+"");
        device.setIpv4(gaReq.getNetwork().getIp());
        device.setIpv6(null);
        int connection = gaReq.getNetwork().getConnectionType();
        if (connection == 100){
            device.setConnectionType(98);
        }else if (connection == 2){
            device.setConnectionType(2);
        }else if (connection == 3){
            device.setConnectionType(3);
        }else if (connection == 4){
            device.setConnectionType(4);
        }else if (connection == 5){
            device.setConnectionType(5);
        }else{
            device.setConnectionType(0);
        }
        int operator = gaReq.getNetwork().getOperatorType();
        if (operator == 0){
            device.setOperatorType(0);
        }else if (operator == 1){
            device.setOperatorType(1);
        }else if (operator == 2){
            device.setOperatorType(2);
        }else if (operator == 3){
            device.setOperatorType(3);
        }else if (operator == 99){
            device.setOperatorType(99);
        }

        //gps参数
        Geo geo = new Geo();
        geo.setLongitude(gaReq.getNetwork().getLon());
        geo.setLatitude(gaReq.getNetwork().getLat());
        geo.setTimestamp(new Date().getTime());
        device.setGeo(geo);
        request.setDevice(device);
        data.setRequest(request);

        return JSONObject.fromObject(data).toString();
    }

    /**
     * 格式化一点通返回数据
     */
    public GetAdsResp formatBackData(String str, GetAdsReq gaReq, GetUpstream gu){
        //返回整体
        GetAdsResp gar = new GetAdsResp();
        JSONObject json = JSONObject.fromObject(str);
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        String code = json.getString("code");
        if ("0".equals(code)){
            JSONObject data = json.getJSONObject("data");

            //广告主体
            Ad ya = new Ad();
            if (data.has("source")){
                ya.setAdtext(data.getString("source"));
            }
            if (data.has("adLogo")){
                ya.setAdlogo(data.getString("adLogo"));
            }

            //物料元
            MaterialMeta ym = new MaterialMeta();
            ym.setMaterialWidth(data.getInt("imgWidth"));
            ym.setMaterialHeight(data.getInt("imgHeight"));
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            /**
             * 视频和其他广告类型公用参数
             * 视频有自己的独有参数,视频没参数的时候,使用共有的
             */
            if (data.has("title")){
                ym.setAdTitle(data.getString("title"));
            }
            List<String> descs = new ArrayList<>();
            if (data.has("description")){
                descs.add(data.getString("description"));
            }
            ym.setDescs(descs);
            if (data.has("imgUrl")){
                ym.setImageUrl(data.getJSONArray("imgUrl"));
            }
            List<String> iconUrls = new ArrayList<>();
            if (data.has("iconUrl")){
                String [] icon = data.getString("iconUrl").split(";");
                for (int i = 0; i < icon.length; i++){
                    iconUrls.add(i, icon[i]);
                }
            }
            ym.setIconUrls(iconUrls);
            if (data.has("landingPageUrl")){
                ym.setClickUrl(data.getString("landingPageUrl"));
            }

            //1=纯文 2=纯图 3=图文 5=非原生(H5) 6=视频
            int adType = data.getInt("adType");
            if (adType == 1){
                ym.setCreativeType(1);
            }else if (adType == 2){
                ym.setCreativeType(2);
            }else if (adType == 3){
                ym.setCreativeType(3);
            }else if (adType == 5){
                ym.setCreativeType(4);
                ya.setHtmlSnippet(data.getString("html"));

            //视频类
            }else if (adType == 6){
                ym.setCreativeType(5);
                JSONObject video = data.getJSONObject("video");
                if (video.has("endHtml") && null != video.getString("endHtml") && !"".equals(video.getString("endHtml"))){
                    ya.setHtmlSnippet(video.getString("endHtml"));
                }
                ym.setVideoUrl(video.getString("videoUrl"));
                ym.setVideoDuration(video.getInt("duration"));

                /**
                 * 视频类专有参数
                 */
                if (video.has("endTitle") && null != video.getString("endTitle") && !"".equals(video.getString("endTitle"))){
                    ym.setAdTitle(video.getString("endTitle"));
                }
                if (video.has("endDesc") && null != video.getString("endDesc") && !"".equals(video.getString("endDesc"))){
                    descs.add(video.getString("endDesc"));
                    ym.setDescs(descs);
                }
                if (video.has("endImgUrl") && null != video.getString("endImgUrl") && !"".equals(video.getString("endImgUrl"))){
                    List<String> imageUrl = new ArrayList<>();
                    imageUrl.add(video.getString("endImgUrl"));
                    ym.setImageUrl(imageUrl);
                }
                if (video.has("endIconUrl") && null != video.getString("endIconUrl") && !"".equals(video.getString("endIconUrl"))){
                    iconUrls.add(video.getString("endIconUrl"));
                    ym.setIconUrls(iconUrls);
                }
                if (video.has("endLandingUrl") && null != video.getString("endLandingUrl") && !"".equals(video.getString("endLandingUrl"))){
                    ym.setClickUrl(video.getString("endLandingUrl"));
                }

                /**
                 * 视频播放进度集合
                 */
                List<Track> ydtTrackList = new ArrayList<>();
                //播放进度100%(播放结束)
                if (video.has("incentiveCallbackReportUrl")){
                    Track track4 = new Track();
                    track4.setType(4);
                    track4.setUrls(macroParam(video.getJSONArray("incentiveCallbackReportUrl")));
                    ydtTrackList.add(track4);
                }
                if (video.has("videoReport")){
                    JSONObject report = video.getJSONObject("videoReport");
                    //静音
                    if (report.has("muteTrackingEvent")){
                        Track track103 = new Track();
                        track103.setType(103);
                        track103.setUrls(macroParam(report.getJSONArray("muteTrackingEvent")));
                        ydtTrackList.add(track103);
                    }
                    //取消静音
                    if (report.has("unmuteTrackingEvent")){
                        Track track104 = new Track();
                        track104.setType(104);
                        track104.setUrls(macroParam(report.getJSONArray("unmuteTrackingEvent")));
                        ydtTrackList.add(track104);
                    }
                }
                ya.setTracks(ydtTrackList);

            }
            //1=打开网页 2=点击下载 3=GDT
            int interactionType = data.getInt("interactionType");
            if (interactionType == 1){
                ym.setInteractionType(1);

            }else if (interactionType == 2){
                ym.setInteractionType(2);
                if (data.has("sourcePackage")){
                    ym.setPackageName(data.getString("sourcePackage"));
                }
                if (data.has("appSize")){
                    ym.setAppSize(data.getInt("appSize")/1000);
                }

            }else if (interactionType == 3){
                ym.setInteractionType(2);
                ym.setProtocolType(1);
            }

            //上报
            EncryptUtil eu = new EncryptUtil();
            //展现曝光
            List<String> winNotice = new ArrayList<>();
            if (data.has("impUrl")){
                winNotice = data.getJSONArray("impUrl");
            }
            String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&20&3","zghd");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(macroParam(winNotice));
            //点击
            List<String> clk = new ArrayList<>();
            if (data.has("clkUrl")){
                clk = data.getJSONArray("clkUrl");
            }
            String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&20&4","zghd");
            clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(macroParam(clk));
            //下载
            if (data.has("downloadReportUrls")){
                ym.setWinDownloadUrls(data.getJSONArray("downloadReportUrls"));
            }
            //下载完成
            if (data.has("downloadedReportUrls")){
                ym.setWinDownloadEndUrls(data.getJSONArray("downloadedReportUrls"));
            }
            //安装
            if (data.has("installReportUrls")){
                ym.setWinInstallUrls(data.getJSONArray("installReportUrls"));
            }
            //安装完成
            if (data.has("installedReportUrls")){
                ym.setWinInstallEndUrls(data.getJSONArray("installedReportUrls"));
            }

            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);

            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
        }else if ("666666".equals(code)){
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }else{
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }
        return gar;
    }

    //宏替换
    public List<String> macroParam(List<String> list){
        List returnList = new ArrayList();
        for (int i = 0; i < list.size(); i++){
            String s = list.get(i);

            //替换__AZCX__
            Pattern pazcx = Pattern.compile("__up_x__");
            Matcher mazcx = pazcx.matcher(s);
            s = mazcx.replaceAll("__UP_X__");

            //替换__AZCY__
            Pattern pazcy = Pattern.compile("__up_y__");
            Matcher mazcy = pazcy.matcher(s);
            s = mazcy.replaceAll("__UP_Y__");

            //替换__AZMX__
            Pattern pazmx = Pattern.compile("__down_x__");
            Matcher mazmx = pazmx.matcher(s);
            s = mazmx.replaceAll("__DOWN_X__");

            //替换__AZMY__
            Pattern pazmy = Pattern.compile("__down_y__");
            Matcher mazmy = pazmy.matcher(s);
            s = mazmy.replaceAll("__DOWN_Y__");

            //替换__DSMX__
            Pattern pdsmx = Pattern.compile("__re_down_x__");
            Matcher mdsmx = pdsmx.matcher(s);
            s = mdsmx.replaceAll("__DOWN_X__");

            //替换__DSMY__
            Pattern pdsmy = Pattern.compile("__re_down_y__");
            Matcher mdsmy = pdsmy.matcher(s);
            s = mdsmy.replaceAll("__DOWN_Y__");

            //替换__DSCX__
            Pattern pdscx = Pattern.compile("__re_up_x__");
            Matcher mdscx = pdscx.matcher(s);
            s = mdscx.replaceAll("__UP_X__");

            //替换__DSCY__
            Pattern pdscy = Pattern.compile("__re_up_y__");
            Matcher mdscy = pdscy.matcher(s);
            s = mazcx.replaceAll("__UP_Y__");

            //替换__STS__
            Pattern psts = Pattern.compile("__utc_ts__");
            Matcher msts = psts.matcher(s);
            s = msts.replaceAll("__EVENT_TIME_START__");

            //替换__ETS__
            Pattern pets = Pattern.compile("__utc_end_ts__");
            Matcher mets = pets.matcher(s);
            s = mets.replaceAll("__EVENT_TIME_END__");

            //替换__VD__
            Pattern pvd = Pattern.compile("__video_duration__");
            Matcher mvd = pvd.matcher(s);
            s = mvd.replaceAll("__VIDEO_TIME__");

            returnList.add(i, s);
        }
        return returnList;
    }


}
