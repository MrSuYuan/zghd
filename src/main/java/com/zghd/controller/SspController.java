package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.util.md5.JiaMi;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.service.*;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("ssp")
@Api(value = "/ssp", tags = "平台广告请求")
public class SspController extends BaseController{

    private Logger logger = Logger.getLogger(this.getClass());

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
        GetAdsResp resp = new GetAdsResp();

        long startTime = System.currentTimeMillis();
        //统计使用时间参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//年月日
        String dateStr = sdf.format(startTime);
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);

        //验参
        /*gar = verifyParam.verifyParam(data);
        if("200".equals(gar.getErrorCode())){
            gar = platformService.adVideo(gaReq);
        }*/
        try{
            //验证入参是json参数,并将参数转换为平台参数对象
            GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
            String appId = gaReq.getApp().getAppId();
            String slotId = gaReq.getSlot().getSlotId();

            //广告调度实现
            resp = platformService.adVideo(gaReq, dateStr, hour, appId, slotId);

            //下游请求统计 + 计时统计
            long endTime = System.currentTimeMillis();
            platformService.downStreamReport(appId, slotId, dateStr, hour, endTime - startTime);
        }catch (JSONException j){
            resp.setErrorCode("300");
            resp.setMsg("PARAM_ERROR");
        }catch (Exception e){
            resp.setErrorCode("500");
            resp.setMsg("SERVER_ERROR");
            logger.info("报错参数:..."+data);
        }

        //返回数据
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 测试接口
     */
    @RequestMapping(value = "/dspAdTest", method = {RequestMethod.POST })
    @ResponseBody
    public void dspAdTest(@RequestBody String data,HttpServletResponse response) throws Exception{
        GetAdsResp resp = new GetAdsResp();

        //下游请求时间
        long startTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//年月日
        String dateStr = sdf.format(startTime);
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);

        try{
            //验证入参是json参数,并将参数转换为平台参数对象
            GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
            //获取下游上传的主要参数
            String appId = gaReq.getApp().getAppId();
            String slotId = gaReq.getSlot().getSlotId();
            int adType = gaReq.getSlot().getAdtype();

            //验参-字段和值
            resp = verifyParam.verifyParam(data);

            if("200".equals(resp.getErrorCode())){
                //验证id和类型匹配
                boolean adType4 = "test1031".equals(slotId) && adType == 4;
                boolean adType5 = "test1030".equals(slotId) && adType == 5;
                boolean adType2 = "test1032".equals(slotId) && adType == 2;
                if ( (adType2 || adType4 || adType5) && "200".equals(resp.getErrorCode()) ){
                    //请求广告
                    resp = platformService.adTest(gaReq, dateStr, hour, appId, slotId, adType);
                }else{
                    resp = new GetAdsResp();
                    resp.setErrorCode("300");
                    resp.setMsg("PARAM_ERROR");
                }
            }

            //下游请求统计 + 计时统计
            long endTime = System.currentTimeMillis();
            platformService.downStreamReport(appId, slotId, dateStr, hour, endTime - startTime);
        }catch (JSONException j){
            resp.setErrorCode("300");
            resp.setMsg("PARAM_ERROR");
        }catch (Exception e){
            resp.setErrorCode("500");
            resp.setMsg("SERVER_ERROR");
            logger.info("报错参数:..."+data);
        }

        String jsonData = JSON.toJSONString(resp);
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
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);
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
            platformService.upStreamReport(dateStr, hour, appId, slotId, upstreamId, Integer.valueOf(upstreamType), Integer.valueOf(type), 0);
        }
    }


}
