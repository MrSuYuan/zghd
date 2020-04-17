package com.zghd.service;

import com.zghd.entity.MoJi.MoJiData;
import com.zghd.entity.MoJi.MoJiResp;
import com.zghd.entity.ZGHDRequest.*;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 墨迹天气
 */
@Service(value="mjService")
public class MJService {

    /**
     * 入参封装
     */
    public GetAdsReq getParams(HttpServletRequest request){
        GetAdsReq req = new GetAdsReq();
        //最低出价
        int price = Integer.valueOf(request.getParameter("basic_price"));
        //每次请求的唯一标识（墨迹平台生成）
        String sessionid = request.getParameter("sessionid");
        req.setRequestId(sessionid);
        /**
         * 封装app信息
         */
        App app = new App();
        app.setAppId("FUN1SW");
        //APP 名称 固定值 mojiWeather
        String appname = request.getParameter("appname");
        app.setAppName(appname);
        //应用的包名
        String pkgname = request.getParameter("pkgname");
        app.setAppPackage(pkgname);
        app.setAppVersion("1.1.1");
        req.setApp(app);
        /**
         * 封装网络信息
         */
        Network network = new Network();
        //用户来源 IP
        String ip = request.getParameter("ip");
        network.setIp(ip);
        //运营商： 0：unknown  1：移动  2：联通  3：电信
        int carrier = Integer.valueOf(request.getParameter("carrier"));
        if (carrier == 1){
            network.setOperatorType(1);
        }else if (carrier == 2){
            network.setOperatorType(3);
        }else if (carrier == 3){
            network.setOperatorType(2);
        }else{
            network.setOperatorType(0);
        }
        //连网方式：0：unknown 1：WiFi  2：2G  3：3G  4：4G
        int net = Integer.valueOf(request.getParameter("net"));
        if (net == 1){
            network.setConnectionType(100);
        }else{
            network.setConnectionType(net);
        }
        //地理位置精度
        String lon = request.getParameter("lon");
        if (null != lon){
            network.setLon(Double.parseDouble(lon));
        }
        //地理位置纬度
        String lat = request.getParameter("lat");
        if (null != lat){
            network.setLat(Double.parseDouble(lat));
        }
        req.setNetwork(network);
        /**
         * 封装广告位信息
         */
        Slot slot = new Slot();
        //广告位标识，为第三方广告投放系统唯一位置标识（第三方平台提供）
        String adid = request.getParameter("adid");
        slot.setSlotId(adid);
        /*slot.setSlotwidth();
        slot.setSlotheight();*/
        slot.setAdtype(4);
        req.setSlot(slot);
        /**
         * 封装设备信息
         */
        Device device = new Device();
        //操作系统类型： 0：Android  1：iOS  2：Windows Phone  3：Others
        int os = Integer.valueOf(request.getParameter("os"));
        if (os == 1){
            device.setOsType(2);
            //idfa
            String idfa = request.getParameter("idfa");
            device.setIdfa(idfa);
        }else{
            device.setOsType(1);
            //用户终端的 Android ID
            String andid = request.getParameter("andid");
            device.setAndroidId(andid);
        }
        //用户浏览器标识 user-agent
        String ua = request.getParameter("ua");
        device.setUa(ua);
        //Android 系统的设备号
        String imei = request.getParameter("imei");
        device.setImei(imei);
        //操作系统版本，如 iPhone 8.1.2 的参数填写 8.1.2 注：安卓系统传值为sdk版本号，如Android 10.0（Q） 的参数填写为 29
        String osv = request.getParameter("osv");
        device.setOsVersion(osv);
        //设备品牌和型号，如 iphone6s、Sumsang S6
        String model = request.getParameter("device");
        device.setBrand(model);
        device.setModel(model);
        device.setVendor(model);
        //终端网卡的 MAC 地址，去除冒号分隔符保持大写
        String mac = request.getParameter("wma");
        if (null != mac && !"".equals(mac)){
            String m = "";
            String[] strs = new String[mac.length()-1];
            for(int i=0;i<mac.length()-1;i+=2){
                strs[i] = mac.substring(i, i+2);
                if (i != 0){
                    m = m + ":" + strs[i];
                }else{
                    m = m + strs[i];
                }
            }
            device.setMac(m);
        }
        //屏幕宽度，广告请求时，应用界面的方向的全屏宽
        int scrwidth = Integer.valueOf(request.getParameter("scrwidth"));
        device.setScreenWidth(scrwidth);
        //屏幕高度，广告请求时，应用界面的方向的全屏高
        int scrheight = Integer.valueOf(request.getParameter("scrheight"));
        device.setScreenHeight(scrheight);
        //广告类型： 1：文字链（已废弃） 2：Banner 3：开机全屏(二级splash)
        int adtype = Integer.valueOf(request.getParameter("adtype"));
        req.setDevice(device);

        //除2048,4096,8192广告位支持的样式 如果只支持一种样式则为0 1：纯图  2：左图右文本 3：右图左文本 4：上标题下图和描述 5：上文本下图 6：上文本下三图 9：双icon 10：双纯图
        request.getParameter("adstyle_request");
        //该广告位支持的 Feed 类型, 仅 feed 广告位使用 3：右图左文  5：上文下图  6：上文下 3 图
        String feed_support_types = request.getParameter("feed_support_types");
        //广告样式
        int adstyle = Integer.valueOf(request.getParameter("adstyle"));
        //当为 pdb 投放模式使用，值可定义
        request.getParameter("comment");
        //屏幕方向，广告请求时用户屏幕的方向  1：水平  2：垂直
        request.getParameter("scrro");
        //Android系统的匿名设备标识符oaid
        request.getParameter("oaid");
        //iOS 终端设备的 OpenUDID
        request.getParameter("openudid");
        //非 Andorid、iOS 操作系统设备的唯一标示码
        request.getParameter("unqid");
        //debug 模式： 1：开启 debug 模式  2：非 debug 模式
        request.getParameter("debug");
        //广告的展示日期，一般用于视频广告预加载
        request.getParameter("show_date");
        //用户标签（墨迹）
        request.getParameter("user_tags");
        //天气参数，具有唯一性，即不存在传多个值的情况
        request.getParameter("weather_code");

        return req;
    }

    /**
     * 出参封装
     */
    public MoJiResp putParams(GetAdsResp ads, int adtype, int adstyle){
        MoJiResp resp = new MoJiResp();
        String code = ads.getErrorCode();
        if ("200".equals(code)){
            MoJiData data = new MoJiData();
            Ad ad = ads.getAds().get(0);
            data.setAdid(ad.getSlotId());
            data.setSessionid(ads.getRequestId());
            MaterialMeta materialMeta = ad.getMetaGroup().get(0);
            data.setType(1);
            String imgurl = "";
            List<String> imgurls = materialMeta.getImageUrl();
            if (imgurls.size()>0){
                for (int i = 0; i < imgurls.size(); i++){
                    imgurl = imgurl + imgurls.get(i) + ";";
                }
            }
            data.setImgurl(imgurl);
            String iconurl = "";
            List<String> iconurls = materialMeta.getImageUrl();
            if (iconurls.size()>0){
                for (int i = 0; i < iconurls.size(); i++){
                    iconurl = iconurl + iconurls.get(i) + ";";
                }
            }
            data.setIconurl(iconurl);
            data.setAdtitle(materialMeta.getAdTitle());
            data.setAdwidth(materialMeta.getMaterialWidth());
            data.setAdheight(materialMeta.getMaterialHeight());
            data.setClickurl(materialMeta.getClickUrl());
            if (materialMeta.getDescs().size()>0){
                data.setAdtext(materialMeta.getDescs().get(0));
            }
            data.setAdtype(adtype);
            data.setAdstyle(adstyle);
            String imptrack = "";
            List<String> winNoticeUrls = materialMeta.getWinNoticeUrls();
            if (winNoticeUrls.size()>0){
                for (int i = 0; i < winNoticeUrls.size(); i++){
                    imptrack = imptrack + winNoticeUrls.get(i) + ";";
                }
            }
            data.setImptrack(imptrack);
            String clktrack = "";
            List<String> winCNoticeUrls = materialMeta.getWinCNoticeUrls();
            if (winCNoticeUrls.size()>0){
                for (int i = 0; i < winCNoticeUrls.size(); i++){
                    clktrack = clktrack + winCNoticeUrls.get(i) + ";";
                }
            }
            data.setClktrack(clktrack);

            /*//广点通广告点击转化地址
            private String transformUrl;
            //视频播放进度上报当需要进行视频进度打点时，需返回的参数格式："视频进度百分比" : "上报打点链接"（进度百分比限制为25、50、75、100，不必全部返回，返回需要上报打点的即可）（上报打点链接可以是多个 url，以;分隔）
            private List<Object> videoprogressurl;
            //出价单位 (分)
            private int price;
            //广告计费类型： 1. CPM（品牌） 2. CPC（效果） 3. CPA（App：iOS 点击/Android 下载）
            private int chargingtype;*/
            resp.setData(data);
            resp.setCode(200);
        }else if ("400".equals(code)){
            resp.setCode(400);
        }else{
            resp.setCode(501);
        }
        return resp;
    }

}
