package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.util.md5.EncryptUtil;
import com.zghd.entity.ZGHDNewsResponse.AdNewsResp;
import com.zghd.entity.ZGHDNewsResponse.AdNewsTitleResp;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.news.PlatformNewsService;
import com.zghd.service.*;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("ssp")
@Api(value = "/ssp", tags = "广告请求")
public class SspController extends BaseController{

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PlatformService platformService;
    @Autowired
    private PlatformNewsService platformNewsService;
    @Autowired
    private DongfangtoutiaoAdaptor dongfangtoutiaoAdaptor;
    @Autowired
    private BaiDuService baiDuService;
    @Autowired
    private VerifyService verifyParam;


    /**
     * 激励视频(调度接口)
     */
    @RequestMapping(value = "/dspAdVideo", method = {RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void dspAdVideo(@RequestBody String data,HttpServletResponse response) throws Exception{

        GetAdsResp gar;
        GetAdsReq gaReq = JSON.parseObject(data,GetAdsReq.class);
        String slotId = gaReq.getSlot().getSlotId();
        //将原来的测试接口数据全部屏蔽
        if("3753".equals(slotId)){
            gar = new GetAdsResp();
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
        }else{
            //验参
            gar = verifyParam.verifyParam(data);
            if("200".equals(gar.getErrorCode())){
                //百度广告
                if ("4a52bc7b".equals(gaReq.getApp().getAppId()) || "38147bdf".equals(gaReq.getApp().getAppId())){
                    //时间
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String dateStr = sdf.format(date);
                    platformService.upStreamReport(dateStr, gaReq.getApp().getAppId(),gaReq.getSlot().getSlotId(),gaReq.getApp().getAppId(),9, 1);
                    gar = baiDuService.getAds(gaReq, 2, gaReq.getApp().getAppId(), gaReq.getSlot().getSlotId());
                    //返回統計
                    if ("200".equals(gar.getErrorCode())){
                        platformService.upStreamReport(dateStr, gaReq.getApp().getAppId(), slotId, gaReq.getApp().getAppId(), 9, 2);
                    }

                //平台广告
                }else{
                    gar = platformService.adVideo(gaReq);
                }
            }
        }
        String jsonData = JSON.toJSONString(gar);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 测试接口
     */
    @RequestMapping(value = "/dspAdTest", method = {RequestMethod.GET, RequestMethod.POST })
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
    @RequestMapping(value = "/backNotice", method = {RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void backNotice(HttpServletRequest request, HttpServletResponse response) throws Exception{
        EncryptUtil eu = new EncryptUtil();
        String param = request.getParameter("param");
        if (null != param && !"".equals(param)){
            String r = eu.AESdecode(param, "zghd");
            String [] params = r.split("&");
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


    /**
     * 新闻信息流标题
     */
    @RequestMapping(value = "/dspNewsTitle", method = {RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void dspNewsTitle(@RequestBody String data,HttpServletResponse response) throws Exception{
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
    @RequestMapping(value = "/dspNews", method = {RequestMethod.GET, RequestMethod.POST })
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


    /**
     * 激励视频(旧版)
     */
    @RequestMapping(value = "/dspvideo", method = {RequestMethod.GET, RequestMethod.POST })
    public void dspvideo(@RequestBody String data,HttpServletResponse response) throws UnsupportedEncodingException, IOException{
        if(StringUtils.isNotEmpty(data)){
            GetAdsReq req = JSON.parseObject(data, GetAdsReq.class);
            if("4a52bc7b".equals(req.getApp().getAppId())){
                String url = "http://47.95.31.238/adx/ad/getAds";
                CloseableHttpClient httpClient=HttpClients.createDefault();
                HttpPost method = new HttpPost(url);
                method.setHeader("Content-Type","application/json");
                StringEntity entity = new StringEntity(data);
                method.setEntity(entity);
                HttpResponse result = httpClient.execute(method);
                String str = EntityUtils.toString(result.getEntity(), "utf-8");
                IOUtils.write(str.getBytes("utf-8"), response.getOutputStream());
                IOUtils.closeQuietly(response.getOutputStream());

            }else{
                GetAdsResp rsp;
                if(StringUtils.isNotEmpty(data)){
                    try {
                        rsp =dongfangtoutiaoAdaptor.getAds(req);
                        rsp.setRequestId(req.getRequestId());
                    }catch (Exception e) {
                        e.printStackTrace();
                        rsp = new GetAdsResp();
                        rsp.setErrorCode("200000");
                    }
                }else{
                    rsp = new GetAdsResp();
                    rsp.setErrorCode("400");
                }
                String jsonData = JSON.toJSONString(rsp);
                IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
                IOUtils.closeQuietly(response.getOutputStream());
            }
        }
    }

}
