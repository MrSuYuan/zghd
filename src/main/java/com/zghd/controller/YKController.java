package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zghd.entity.YouKu.request.Banner;
import com.zghd.entity.YouKu.request.Native;
import com.zghd.entity.YouKu.request.Video;
import com.zghd.entity.YouKu.request.YKRequest;
import com.zghd.entity.YouKu.response.Bid;
import com.zghd.entity.YouKu.response.Ext;
import com.zghd.entity.YouKu.response.Seatbid;
import com.zghd.entity.YouKu.response.YKResponse;
import com.zghd.entity.ZGHDRequest.*;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.service.PlatformService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//Bid URL : http://47.95.31.238/adx/yk/ykAds
//Win Notice Url : http://47.95.31.238/adx/yk/ykNotice
//QPS : 500
//姓名 : 杨凌云
//邮箱 : lyyang1990@126.com
//电话 : 13651027550
@Controller
@RequestMapping("yk")
@Api(value = "/yk", tags = "优酷广告请求")
public class YKController extends BaseController{


    @Autowired
    private PlatformService platformService;

    /**
     * 优酷
     */
    @RequestMapping(value = "/ykAds", method = {RequestMethod.POST })
    @ResponseBody
    public void ykAds(@RequestBody String data, HttpServletResponse response) throws Exception{
        GetAdsResp resp = new GetAdsResp();

        long startTime = System.currentTimeMillis();
        //统计使用时间参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//年月日
        String dateStr = sdf.format(startTime);
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);

        try{
            data = data.replaceAll("native","aNative");
            //接收优酷参数
            YKRequest ykData = JSON.parseObject(data,YKRequest.class);
            //转化为平台参数
            GetAdsReq gaReq = formatRequestData(ykData);
            String appId = gaReq.getApp().getAppId();
            String slotId = gaReq.getSlot().getSlotId();

            //广告调度实现
            resp = platformService.adVideo(gaReq, dateStr, hour, appId, slotId);

            //将返回参数转化为优酷返回参数

            //下游请求统计 + 计时统计
            long endTime = System.currentTimeMillis();
            platformService.downStreamReport(appId, slotId, dateStr, hour, endTime - startTime);

            //如果是json解析错误,报300
        }catch (JSONException j){
            resp.setErrorCode("300");
            resp.setMsg("PARAM_ERROR");

            //其他程序错误,报500
        }catch (Exception e){
            resp.setErrorCode("500");
            resp.setMsg("SERVER_ERROR");
            //logger.info("报错参数:..."+data);
        }

        //返回数据
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 封装入参参数
     */
    private GetAdsReq formatRequestData(YKRequest ykData){
        GetAdsReq gaReq = new GetAdsReq();
        gaReq.setRequestId(ykData.getId());

        String id = ykData.getImp().get(0).getTagid();
        String appId = id.substring(0,6);
        String slotId = id.substring(6,14);

        App app = new App();
        app.setAppId(appId);
        //app和页面
        if (null != ykData.getApp()){
            app.setAppName(ykData.getApp().getName());
        }else{
            app.setAppName(ykData.getSite().getName());
        }
        app.setAppPackage("com.youku.app");
        app.setAppVersion("8.0.8");
        gaReq.setApp(app);

        Slot slot = new Slot();
        slot.setSlotId(slotId);
        //底价
        float f = ykData.getImp().get(0).getBidfloor();
        slot.setPrice(f);
        //信息流
        if (null != ykData.getImp().get(0).getaNative()){
            Native n = ykData.getImp().get(0).getaNative();
            slot.setAdtype(4);
            slot.setSlotwidth(n.getAssets().get(0).getImage_url().getW());
            slot.setSlotheight(n.getAssets().get(0).getImage_url().getH());
        }
        //Banner
        if (null != ykData.getImp().get(0).getBanner()){
            Banner b = ykData.getImp().get(0).getBanner();
            slot.setAdtype(1);
            slot.setSlotwidth(b.getW());
            slot.setSlotheight(b.getH());
        }
        //视频
        if (null != ykData.getImp().get(0).getVideo()){
            Video v = ykData.getImp().get(0).getVideo();
            slot.setAdtype(5);
            slot.setSlotwidth(v.getW());
            slot.setSlotheight(v.getH());
        }
        gaReq.setSlot(slot);

        Device device = new Device();
        if (null != ykData.getDevice()){
            device.setIdfa(ykData.getDevice().getIdfa());
            String os = ykData.getDevice().getOs();
            if (null != os && os.equals("ios")){
                device.setOsType(2);
            }else{
                device.setOsType(1);
            }
            device.setOsVersion(ykData.getDevice().getOsv());
            int devicetype = ykData.getDevice().getDevicetype();
            if (devicetype == 0){
                device.setDeviceType(1);
            }else{
                device.setDeviceType(2);
            }
            device.setImei_md5(ykData.getDevice().getImei_md5());
            device.setMac(ykData.getDevice().getMac());
            device.setAndroidId(ykData.getDevice().getAndroidid());
            device.setOaid(ykData.getDevice().getOaid());
            device.setVendor(ykData.getDevice().getMake());
            device.setBrand(ykData.getDevice().getMake());
            device.setModel(ykData.getDevice().getModel());
            if (null != ykData.getDevice().getScreenwidth()){
                device.setScreenWidth(Integer.valueOf(ykData.getDevice().getScreenwidth()));
                device.setScreenHeight(Integer.valueOf(ykData.getDevice().getScreenhight()));
            }
            device.setUa(ykData.getDevice().getUa());
            device.setPpi(1);
            device.setScreenOrientation(1);
            device.setImsi("");
            gaReq.setDevice(device);

            Network network = new Network();
            network.setIp(ykData.getDevice().getIp());
            if (null != ykData.getDevice().getCarrier()){
                network.setOperatorType(Integer.valueOf(ykData.getDevice().getCarrier()));
            }
            network.setConnectionType(0);
            network.setLat(0);
            network.setLon(0);
            network.setCellular_id("");
            gaReq.setNetwork(network);
        }

        return gaReq;
    }


    /**
     * 封装出参参数
     */
    private GetAdsReq formatResponseData(GetAdsReq req, GetAdsResp resp, YKRequest ykData){
        GetAdsReq gaReq = new GetAdsReq();

        resp.getErrorCode();
        Ad ad = resp.getAds().get(0);

        ad.getHtmlSnippet();
        ad.getAdtext();
        ad.getAdlogo();
        ad.getTracks();
        MaterialMeta meta = ad.getMetaGroup().get(0);
        meta.getAdTitle();
        meta.getDescs();
        meta.getImageUrl();
        meta.getIconUrls();
        meta.getMaterialWidth();
        meta.getMaterialHeight();
        meta.getClickUrl();
        meta.getCreativeType();
        meta.getInteractionType();
        meta.isDeepLink();
        meta.getDeepLinkUrl();
        meta.getProtocolType();
        meta.getPackageName();
        meta.getBrandName();
        meta.getAppSize();
        meta.getVideoUrl();
        meta.getVideoDuration();
        meta.getWinLoadUrls();
        meta.getWinNoticeUrls();
        meta.getWinCNoticeUrls();
        meta.getWinDownloadUrls();
        meta.getWinDownloadEndUrls();
        meta.getWinInstallUrls();
        meta.getWinInstallEndUrls();

        YKResponse response = new YKResponse();
        response.setId(resp.getRequestId());
        //DSP给出的该次竞价的ID
        response.setBidid(ad.getAdKey());
        List<Seatbid> seatbidList = new ArrayList<>();
        Seatbid seatbid = new Seatbid();
        List<Bid> bidList = new ArrayList<>();
        Bid bid = new Bid();
        //DSP对该次出价分配的ID
        bid.setId(ad.getAdKey());
        //Bid Request中对应的曝光ID
        bid.setImpid(ykData.getImp().get(0).getId());
        //DSP出价，单位是分/千次曝光，即CPM
        bid.setPrice(resp.getAds().get(0).getP());
        //广告主ID，对应广告审核的广告主ID
        bid.setAdvertiser("");
        //广告主行业ID，需要使用YOUKU系统的行业
        bid.setIndustry("");
        //DSP参加的deal id
        bid.setDealid("");
        int adType = req.getSlot().getAdtype();
        //4信息流  5激励视频  其他都按banner处理
        if (adType == 4){
            //参与竞价的原生广告创意信息
            bid.setaNative(null);
            //DSP系统中的创意ID。信息流，焦点图资源必填。保证在DSP侧的ID唯一性，作为竞价时的唯一创意ID，否则会引起素材投放混乱。
            bid.setCrid("");
        }else if (adType == 5){

        }else{
            //广告素材URL。banner，贴片，暂停，角标，开机图资源必填。注意：PDB2.0该字段无效，统一接收crid。
            bid.setAdm("");
        }


        bidList.add(bid);
        seatbid.setBid(bidList);
        seatbidList.add(seatbid);
        response.setSeatbid(seatbidList);

        com.zghd.entity.YouKu.response.Native aNative = new com.zghd.entity.YouKu.response.Native();
        aNative.setNative_template_id("");
        aNative.setTitle("");
        aNative.setImage(null);
        aNative.setLogo(null);
        aNative.setVideo(null);
        aNative.setBrand("");

        Ext ext = new Ext();
        ext.setLdp("");
        ext.setDp("");
        ext.setLdptype(0);
        ext.setPm(null);
        ext.setEm(null);
        ext.setTm(null);

        return gaReq;
    }

}
