package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.zghd.entity.ZGHDNewsResponse.AdNewsResp;
import com.zghd.entity.ZGHDNewsResponse.AdNewsTitleResp;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.news.PlatformNewsService;
import com.zghd.service.VerifyService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("news")
@Api(value = "/news", tags = "新闻请求")
public class NewsController {

    @Autowired
    private PlatformNewsService platformNewsService;
    @Autowired
    private VerifyService verifyParam;

    /**
     * 新闻信息流标题
     */
    @RequestMapping(value = "/dspNewsTitle", method = {RequestMethod.POST })
    @ResponseBody
    public void dspNewsTitle(@RequestBody String data, HttpServletResponse response) throws Exception{
        GetAdsResp gar;
        AdNewsTitleResp atr;
        GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
        //验参
        gar = verifyParam.verifyParam(data);
        if("200".equals(gar.getErrorCode())){
            atr = platformNewsService.newsTitles(gaReq);
        }else{
            atr = new AdNewsTitleResp();
            atr.setErrorCode("300");
            atr.setMsg("PARAM_ERROR");
        }
        String jsonData = JSON.toJSONString(atr);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 新闻信息流
     */
    @RequestMapping(value = "/dspNews", method = {RequestMethod.POST })
    @ResponseBody
    public void dspNews(@RequestBody String data,HttpServletResponse response) throws Exception{
        GetAdsResp gar;
        AdNewsResp anr;
        GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
        //验参
        gar = verifyParam.verifyParam(data);
        if("200".equals(gar.getErrorCode())){
            anr = platformNewsService.newsList(gaReq);
        }else{
            anr = new AdNewsResp();
            anr.setErrorCode("300");
            anr.setMsg("PARAM_ERROR");
        }
        String jsonData = JSON.toJSONString(anr);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }

}
