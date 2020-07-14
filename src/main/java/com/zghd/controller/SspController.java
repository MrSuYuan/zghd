package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.service.*;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("ssp")
@Api(value = "/ssp", tags = "平台广告请求")
public class SspController extends BaseController{

    @Autowired
    private PlatformService platformService;
    @Autowired
    private VerifyService verifyParam;

    /**
     * 激励视频(调度接口)
     */
    @RequestMapping(value = "/dspAdVideo", method = {RequestMethod.POST })
    @ResponseBody
    public void dspAdVideo(@RequestBody String data,HttpServletResponse response) throws Exception{

        //格式化前端传入的json参数
        GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
        //请求广告
        GetAdsResp gar = platformService.adVideo(gaReq);

        //验参
        /*gar = verifyParam.verifyParam(data);
        if("200".equals(gar.getErrorCode())){
            gar = platformService.adVideo(gaReq);
        }*/


        //返回数据
        String jsonData = JSON.toJSONString(gar);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 测试接口
     */
    @RequestMapping(value = "/dspAdTest", method = {RequestMethod.POST })
    @ResponseBody
    public void dspAdTest(@RequestBody String data,HttpServletResponse response) throws Exception{
        GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
        //验参
        GetAdsResp gar = verifyParam.verifyParam(data);
        if("200".equals(gar.getErrorCode())){
            //时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            //获取下游上传的主要参数
            String appId = gaReq.getApp().getAppId();
            String slotId = gaReq.getSlot().getSlotId();
            int adType = gaReq.getSlot().getAdtype();
            //验证id和类型匹配
            boolean adType4 = "test1031".equals(slotId) && adType == 4;
            boolean adType5 = "test1030".equals(slotId) && adType == 5;
            boolean adType2 = "test1032".equals(slotId) && adType == 2;
            if ( (adType2 || adType4 || adType5) && "200".equals(gar.getErrorCode()) ){
                //请求广告
                gar = platformService.adTest(gaReq, dateStr, appId, slotId, adType);
            }else{
                gar = new GetAdsResp();
                gar.setErrorCode("300");
                gar.setMsg("PARAM_ERROR");
            }
        }
        String jsonData = JSON.toJSONString(gar);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 上报接口接收
     * 参数需要解密,固定顺序排列
     */
    @RequestMapping(value = "/backNotice", method = {RequestMethod.GET})
    @ResponseBody
    public void backNotice(HttpServletRequest request) {
        String param = request.getParameter("param");
        if (null != param && !"".equals(param)){
            String r = JiaMi.decrypt(param);
            String [] params = r.split("-");
            String appId = params[0];
            String slotId = params[1];
            String upstreamId = params[2];
            String upstreamType = params[3];
            String type = params[4];
            //时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            platformService.upStreamReport(dateStr, appId, slotId, upstreamId, Integer.valueOf(upstreamType), Integer.valueOf(type));
        }
    }


}
