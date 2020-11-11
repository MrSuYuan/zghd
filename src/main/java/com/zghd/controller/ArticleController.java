package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.util.http.TestConnectionPool;
import com.zghd.entity.JuKanDian.request.*;
import com.zghd.entity.JuKanDian.response.*;
import com.zghd.service.JKDService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;

/**
 * 文章接口
 */
@Controller
@RequestMapping("article")
@Api(value = "/article", tags = "文章广告请求")
public class ArticleController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JKDService jkdService;

    //private static String url = "http://api.wenlv-kd.com";
    //appkey = zhongguanhudong
    //appid =  108

    /**
     * 用户初始化
     */
    @RequestMapping(value = "/user", method = {RequestMethod.POST })
    @ResponseBody
    public void user(@RequestBody String data, HttpServletResponse response) throws Exception{
        UserResp resp = new UserResp();
        if (null == data || "".equals(data)){
            resp.setErrorCode("300");
        }else{
            String url = "http://api.wenlv-kd.com/outread/init";
            User user = JSON.parseObject(data,User.class);
            String appId = user.getAppId();
            String slotId = user.getSlotId();
            //获取上游参数
            Upstream u = jkdService.selectUpstream(appId, slotId);
            user.setAppkey(u.getUpstreamAppkey());
            user.setAppid(u.getUpstreamAppid());
            //平台统计
            jkdService.articleReport(appId, slotId, u.getUpstreamAppkey(), u.getUpstreamAppid(), 1);
            //向上游发起请求
            String str = TestConnectionPool.post(url, JSON.toJSONString(user),null);
            //转成下游参数
            resp = JSON.parseObject(str,UserResp.class);
            if (resp.getRet_code() == 1){
                resp.setErrorCode("200");
            }else{
                resp.setErrorCode("400");
            }
        }
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 文章列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST })
    @ResponseBody
    public void list(@RequestBody String data, HttpServletResponse response) throws Exception{
        ArticleListResp resp = new ArticleListResp();
        if (null == data || "".equals(data)){
            resp.setErrorCode("300");
        }else{
            String url = "http://api.wenlv-kd.com/article/list";
            ArticleList al = JSON.parseObject(data,ArticleList.class);
            String appId = al.getAppId();
            String slotId = al.getSlotId();
            //获取上游参数
            Upstream u = jkdService.selectUpstream(appId, slotId);
            al.setAppkey(u.getUpstreamAppkey());
            al.setAppid(u.getUpstreamAppid());
            //平台统计
            jkdService.articleReport(appId, slotId, u.getUpstreamAppkey(), u.getUpstreamAppid(), 2);
            //向上游发起请求
            String str = TestConnectionPool.post(url, JSON.toJSONString(al),null);
            //转成下游参数
            resp = JSON.parseObject(str,ArticleListResp.class);
            if (resp.getRet_code() == 1){
                resp.setErrorCode("200");
            }else{
                resp.setErrorCode("400");
            }
        }
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 文章详情
     */
    @RequestMapping(value = "/detail", method = {RequestMethod.POST })
    @ResponseBody
    public void detail(@RequestBody String data, HttpServletResponse response) throws Exception{
        ArticleDetailResp resp = new ArticleDetailResp();
        if (null == data || "".equals(data)){
            resp.setErrorCode("300");
        }else{
            String url = "http://api.wenlv-kd.com/article/detail";
            ArticleDetail ad = JSON.parseObject(data,ArticleDetail.class);
            String appId = ad.getAppId();
            String slotId = ad.getSlotId();
            //获取上游参数
            Upstream u = jkdService.selectUpstream(appId, slotId);
            ad.setAppkey(u.getUpstreamAppkey());
            ad.setAppid(u.getUpstreamAppid());
            //平台统计
            jkdService.articleReport(appId, slotId, u.getUpstreamAppkey(), u.getUpstreamAppid(), 3);
            //向上游发起请求
            String str = TestConnectionPool.post(url, JSON.toJSONString(ad),null);
            System.out.println(str);
            //转成下游参数
            resp = JSON.parseObject(str,ArticleDetailResp.class);
            if (resp.getRet_code() == 1){
                resp.setErrorCode("200");
            }else{
                resp.setErrorCode("400");
            }
        }
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 文章分享
     */
    @RequestMapping(value = "/share", method = {RequestMethod.POST })
    @ResponseBody
    public void share(@RequestBody String data, HttpServletResponse response) throws Exception{
        ArticleShareResp resp = new ArticleShareResp();
        if (null == data || "".equals(data)){
            resp.setErrorCode("300");
        }else{
            String url = "http://api.wenlv-kd.com/share/info";
            ArticleShare as = JSON.parseObject(data,ArticleShare.class);
            String appId = as.getAppId();
            String slotId = as.getSlotId();
            //获取上游参数
            Upstream u = jkdService.selectUpstream(appId, slotId);
            as.setAppkey(u.getUpstreamAppkey());
            as.setAppid(u.getUpstreamAppid());
            //平台统计
            jkdService.articleReport(appId, slotId, u.getUpstreamAppkey(), u.getUpstreamAppid(), 4);
            //向上游发起请求
            String str = TestConnectionPool.post(url, JSON.toJSONString(as),null);
            System.out.println(str);
            //转成下游参数
            resp = JSON.parseObject(str,ArticleShareResp.class);
            if (resp.getRet_code() == 1){
                resp.setErrorCode("200");
            }else{
                resp.setErrorCode("400");
            }
        }
        String jsonData = JSON.toJSONString(resp);
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 接口回调
     */
    @RequestMapping(value = "/back", method = {RequestMethod.POST })
    @ResponseBody
    public void back(@RequestBody String data, HttpServletResponse response) throws Exception{
        System.out.println(data);
        ArticleBack ab = JSON.parseObject(data,ArticleBack.class);
        String upstreamAppkey = ab.getAppkey();
        String upstreamAppid = ab.getAppid();
        //获取上游参数
        Upstream u = jkdService.selectAppMsg(upstreamAppkey, upstreamAppid);
        ArticleBackResp abr = JSON.parseObject(data,ArticleBackResp.class);
        abr.setAppId(u.getAppId());
        abr.setSlotId(u.getSlotId());
        //平台统计
        jkdService.articleReport(u.getAppId(), u.getSlotId(), upstreamAppkey, upstreamAppid, 5);
        //向下游发起请求
        TestConnectionPool.post(u.getBackUrl(), JSON.toJSONString(abr),null);
    }

}
