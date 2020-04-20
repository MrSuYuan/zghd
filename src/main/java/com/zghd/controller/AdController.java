package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.zghd.entity.MoJi.MoJiResp;
import com.zghd.entity.ZGHDRequest.*;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.service.BaiDuService;
import com.zghd.service.MJService;
import com.zghd.service.PlatformService;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("ad")
@Api(value = "/ad", tags = "百度广告请求")
public class AdController extends BaseController{

    @Autowired
    private BaiDuService baiDuService;
    @Autowired
    private MJService mjService;
    @Autowired
    private PlatformService platformService;

    /**
     * 点开广告(百度广告) 已经过期
     */
    @RequestMapping(value = "/getAds", method = {RequestMethod.GET, RequestMethod.POST })
    public void getAds(@RequestBody String data, HttpServletResponse response) throws Exception {
        GetAdsResp rsp;
        GetAdsReq req = JSON.parseObject(data, GetAdsReq.class);
        String appId = req.getApp().getAppId();
        String slotId = req.getSlot().getSlotId();
        if(StringUtils.isNotEmpty(data)){
            rsp = baiDuService.getAds(req, 1, appId, slotId);

        }else{
            rsp = new GetAdsResp();
            rsp.setErrorCode("");
        }
        String jsonData = JSON.toJSONString(rsp);
//        if("0".equals(rsp.getErrorCode())){
//            platformService.otherStatistics(appId, slotId, 2);
//            rsp.setErrorCode("200");
//        }else{
//            rsp.setErrorCode("300");
//        }
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 墨迹天气
     */
    @RequestMapping(value = "/mjAds", method = {RequestMethod.GET})
    public void mjAds(HttpServletResponse response) throws Exception {

        //封装入参参数
        GetAdsReq req = mjService.getParams(request);
        System.out.println("入参:"+JSONObject.fromObject(req).toString());
        //请求广告
        GetAdsResp ads = platformService.adVideo(req);
        System.out.println("回参"+JSONObject.fromObject(ads).toString());
        //封装回参参数
        MoJiResp resp = mjService.putParams(ads, 1 , 1);
        System.out.println("下游"+JSONObject.fromObject(resp).toString());
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());

    }


}
