package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.zghd.entity.MoJi.MoJiResp;
import com.zghd.entity.ZGHDRequest.*;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.service.MJService;
import com.zghd.service.PlatformService;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("ad")
@Api(value = "/ad", tags = "其他广告请求")
public class AdController extends BaseController{

    @Autowired
    private MJService mjService;
    @Autowired
    private PlatformService platformService;

    /**
     * 墨迹天气
     */
    @RequestMapping(value = "/mjAds", method = {RequestMethod.GET})
    public void mjAds(HttpServletResponse response) throws Exception {

        //封装入参参数
        GetAdsReq req = mjService.getParams(request);
        //请求广告
        GetAdsResp ads = platformService.adVideo(req);
        //封装回参参数
        MoJiResp resp = mjService.putParams(ads, 1 , 1);
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());

    }


}
