package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.zghd.entity.YouKu.request.YKRequest;
import com.zghd.entity.ZGHDRequest.*;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
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
import java.util.Calendar;
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

        App app = new App();
        app.setAppId("");
        app.setAppName("");
        app.setAppPackage("");
        app.setAppVersion("");
        gaReq.setApp(app);

        Slot slot = new Slot();
        slot.setSlotId("");
        slot.setAdtype(1);
        slot.setSlotwidth(1);
        slot.setSlotheight(1);
        gaReq.setSlot(slot);

        Device device = new Device();
        device.setIdfa("");
        device.setImei("");
        device.setMac("");
        device.setAndroidId("");
        device.setOaid("");
        device.setOsType(1);
        device.setOsVersion("");
        device.setDeviceType(1);
        device.setVendor("");
        device.setBrand("");
        device.setModel("");
        device.setScreenWidth(1);
        device.setScreenHeight(1);
        device.setUa("");
        device.setPpi(1);
        device.setScreenOrientation(1);
        device.setImsi("");
        gaReq.setDevice(device);

        Network network = new Network();
        network.setIp("");
        network.setOperatorType(0);
        network.setConnectionType(0);
        network.setLat(0);
        network.setLon(0);
        network.setCellular_id("");
        gaReq.setNetwork(network);

        return gaReq;
    }


    /**
     * 封装出参参数
     */
    private GetAdsReq formatResponseData(){
        GetAdsReq gaReq = new GetAdsReq();
        return gaReq;
    }

}
