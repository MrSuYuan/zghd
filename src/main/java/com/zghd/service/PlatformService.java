package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.dao.PlatformDao;
import com.zghd.entity.ZGHDReport.EventLog;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.platform.GetUpstream;
import com.zghd.entity.platform.ReportDownstream;
import com.zghd.entity.platform.ReportUpstream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

@Service("platformService")
public class PlatformService {

    @Resource
    private PlatformDao platformDao;
    @Autowired
    com.util.redis.JedisClient redisClient;
    @Autowired
    private WKService wkService;
    @Autowired
    private YLService ylService;
    @Autowired
    private YDTService ydtService;
    @Autowired
    private DFService dfService;
    @Autowired
    private XZService xzService;
    @Autowired
    private JGService jgService;
    @Autowired
    private MJKService mjkService;
    @Autowired
    private WMService wmService;
    @Autowired
    private YQService yqService;
    @Autowired
    private JLService jlService;
    @Autowired
    private ZMService zmService;
    @Autowired
    private BaiDuService baiDuService;
    @Autowired
    private HYService hyService;
    @Autowired
    private XSService xsService;
    @Autowired
    private RSService rsService;
    @Autowired
    private YDService ydService;
    @Autowired
    private ZYService zyService;
    @Autowired
    private HCService hcService;
    @Autowired
    private IMBService imbService;
    @Autowired
    private RXService rxService;
    @Autowired
    private OPPOService oppoService;
    @Autowired
    private DMService dmService;
    @Autowired
    private GDTService gdtService;
    @Autowired
    private XJService xjService;
    @Autowired
    private JMService jmService;
    @Autowired
    private AZService azService;
    @Autowired
    private TAService taService;
    @Autowired
    private PTService ptService;
    @Autowired
    private ZTService ztService;


    /**
     * 请求调度
     */
    public GetAdsResp adVideo(GetAdsReq gaReq, String dateStr, int hour, String appId, String slotId)throws Exception{
        GetAdsResp gar = null;

        //查看所有有分配流量的上游
        List<GetUpstream> guList = platformDao.getUpstream(slotId);

        //appId和广告位id不匹配
        if (guList.size() > 0 && !guList.get(0).getAppId().equals(appId)){
            gar = new GetAdsResp();
            gar.setRequestId(gaReq.getRequestId());
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
            return gar;
        }

        //未绑定上游id 或者 所有上游id概率为0  ||  广告位停量
        if(guList.size() == 0 || guList.get(0).getFlowStatus() == 0){
            gar = new GetAdsResp();
            gar.setRequestId(gaReq.getRequestId());
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
            return gar;
        }

        //分配上游
        GetUpstream gu = getAssign(guList);

        //包名
        if("".equals(gu.getUpstreamPackageName()) || null==gu.getUpstreamPackageName()){
            gu.setUpstreamPackageName(gaReq.getApp().getAppPackage());
        }
        //app名称
        if("".equals(gu.getUpstreamAppName()) || null==gu.getUpstreamAppName()){
            gu.setUpstreamAppName(gaReq.getApp().getAppName());
        }
        int upstreamType = gu.getUpstreamType();

        //上游请求統計
        upStreamReport(dateStr, hour, appId, slotId, gu.getUpstreamId(), upstreamType, 1, 0, null);
        long startTime = System.currentTimeMillis();

        if(upstreamType == 1){
            //logger.info("-东方-");
            gar = dfService.DFSend(gaReq, gu);
        }else if(upstreamType == 2){
            //logger.info("-万咖-");
            gar = wkService.WKSend(gaReq, gu);
        }else if(upstreamType == 3){
            //logger.info("-极光-");
            gar = jgService.JGSend(gaReq, gu);
        }else if(upstreamType == 4){
            //logger.info("-余梁-");
            gar = ylService.YLSend(gaReq, gu);
        }else if(upstreamType == 5){
            //logger.info("-一点通-");
            gar = ydtService.YDTSend(gaReq, gu);
        }else if(upstreamType == 6){                    //-------在跑-已优化
            //logger.info("-小知-");
            gar = xzService.XZSend(gaReq, gu);
        }else if(upstreamType == 7){
            //logger.info("-旺脉-");
            gar = wmService.WMSend(gaReq, gu);
        }else if(upstreamType == 8){
            //logger.info("-甬祺-");
            gar = yqService.YQSend(gaReq, gu);
        }else if(upstreamType == 9){                   //-------在跑-已优化
            //logger.info("-百度-");
            gar = baiDuService.getAds(gaReq, gu);
        }else if(upstreamType == 10){
            //logger.info("-迈吉客-");
            gar = mjkService.MJKSend(gaReq, gu);
        }else if(upstreamType == 11){
            //logger.info("-聚量-");
            gar = jlService.JLSend(gaReq, gu);
        }else if(upstreamType == 12){
            //logger.info("-众盟-");
            gar = zmService.ZMSend(gaReq, gu);
        }else if(upstreamType == 13){
            //logger.info("-虹益-");
            gar = hyService.HYSend(gaReq, gu);
        }else if(upstreamType == 14){
            //logger.info("-新笙-");
            gar = xsService.XSSend(gaReq, gu);
        }else if(upstreamType == 15){
            //logger.info("-瑞狮-");
            gar = rsService.RSSend(gaReq, gu);
        }else if(upstreamType == 16){                    //-------在跑
            //logger.info("-有道-");
            gar = ydService.YDSend(gaReq, gu);
        }else if(upstreamType == 17){
            //logger.info("-智友-");
            gar = zyService.ZYSend(gaReq, gu);
        }else if(upstreamType == 18){                    //-------在跑
            //logger.info("-汇川-");
            gar = hcService.HCSend(gaReq, gu);
        }else if(upstreamType == 19){                    //-------在跑
            //logger.info("-InMoBi-");
            gar = imbService.IMBSend(gaReq, gu);
        }else if(upstreamType == 20){
            //logger.info("-瑞郗-");
            gar = rxService.RXSend(gaReq, gu);
        }else if(upstreamType == 21){                    //-------在跑
            //logger.info("-OPPO-");
            gar = oppoService.OPPOSend(gaReq, gu);
        }else if(upstreamType == 22){
            //logger.info("-豆盟-");
            gar = dmService.DMSend(gaReq, gu);
        }else if(upstreamType == 24){                    //-------在跑-已优化
            //logger.info("-广点通-");
            gar = gdtService.GDTSend(gaReq, gu);
        }else if(upstreamType == 25){                    //-------在跑-已优化
            //logger.info("-先荐-");
            gar = xjService.XJSend(gaReq, gu);
        }else if(upstreamType == 26){                    //-------在跑-已优化
            //logger.info("-俱脉-");
            gar = jmService.JMSend(gaReq, gu);
        }else if(upstreamType == 27){
            //logger.info("-阿哲-");
            gar = azService.AZSend(gaReq, gu);
        }else if(upstreamType == 28){
            //logger.info("-推啊-");
            gar = taService.TASend(gaReq, gu);
        }else if(upstreamType == 29){
            //logger.info("-三星鹏泰-");
            gar = ptService.PTSend(gaReq, gu);
        }else if(upstreamType == 30){
            //logger.info("-中体互联-");
            gar = ztService.ZTSend(gaReq, gu);
        }else{

        }

        //上游返回統計
        long endTime = System.currentTimeMillis();
        if ("200".equals(gar.getErrorCode())){


            //7和16的曝光封装在了视频播放结束里面
            if (upstreamType != 7 && upstreamType != 16){
                //封装上报信息-曝光
                String param1 = "http://47.95.31.238/adx/ssp/backNotice?param=" + JiaMi.encrypt(gaReq.getApp().getAppId() + "&" + gaReq.getSlot().getSlotId() + "&" + gu.getUpstreamId() + "&"+ upstreamType +"&3");
                gar.getAds().get(0).getMetaGroup().get(0).getWinNoticeUrls().add(param1);
            }
            //封装上报信息-点击-带宏参数
            String h = "&event=width:__WIDTH__height:__HEIGHT__dx:__DOWN_X__dy:__DOWN_Y__ux:__UP_X__uy:__UP_Y__";
            String param2 = "http://47.95.31.238/adx/ssp/backNotice?param=" + JiaMi.encrypt(gaReq.getApp().getAppId() + "&" + gaReq.getSlot().getSlotId() + "&" + gu.getUpstreamId() + "&"+ upstreamType +"&4");
            gar.getAds().get(0).getMetaGroup().get(0).getWinCNoticeUrls().add(param2+h);
            //吊起类
            if (gar.getAds().get(0).getMetaGroup().get(0).isDeepLink()){
                String param3 = "http://47.95.31.238/adx/ssp/backNotice?param=" + JiaMi.encrypt(gaReq.getApp().getAppId() + "&" + gaReq.getSlot().getSlotId() + "&" + gu.getUpstreamId() + "&" + upstreamType + "&5");
                gar.getAds().get(0).getMetaGroup().get(0).getWinDeepLinkSuccessUrls().add("http://47.95.31.238/adx/ssp/backNotice?param="+param3);
            }

            //统计
            upStreamReport(dateStr, hour, appId, slotId, gu.getUpstreamId(), upstreamType, 2, (endTime-startTime), null);
        }

        return gar;
    }

    /**
     * 权重分配
     * @param upstreams
     * @return
     */
    public GetUpstream getAssign(List<GetUpstream> upstreams) {
        GetUpstream gu  = new GetUpstream();

        Integer probabilitySum = 100;
        Random random = new Random();
        Integer n = random.nextInt(probabilitySum); // n in [0, weightSum)

        Integer m = 0;
        for (GetUpstream u : upstreams) {
            if (m <= n && n < m + u.getProbability()) {
                gu = u;
                break;
            }
            m += u.getProbability();
        }
        return gu;
    }


    /**
     * 下游请求统计
     * type 1新增 2修改
     */
    public void downStreamReport(String appId, String slotId, String date, int hour, long time){
        String downKey = date + hour + appId + slotId;
        String downStatus = redisClient.get(downKey);
        ReportDownstream downstream = new ReportDownstream();
        downstream.setDownstreamReportId(downKey);
        if (time < 300){
            downstream.setT300(1);
        }else if(time >= 300 && time < 400){
            downstream.setT400(1);
        }else if(time >= 400 && time < 500){
            downstream.setT500(1);
        }else{
            downstream.setT1000(1);
        }
        //新增
        if (downStatus == null){
            redisClient.set(downKey,"1");
            redisClient.expire(downKey,5000);
            downstream.setAppId(appId);
            downstream.setSlotId(slotId);
            downstream.setDownstreamRequest(1);
            downstream.setHour(hour);
            platformDao.insertDownStreamReport(downstream);
        //修改
        }else{
            platformDao.updateDownStreamReport(downstream);
        }
    }


    /**
     * 上游请求统计
     * type 1请求 2返回 3曝光 4点击
     */
    public void upStreamReport(String date, int hour, String appId, String slotId, String upstreamId, int upstreamType, int type, long time, String log){
        String upKey = MD5.md5(date + hour + appId + slotId + upstreamId);
        ReportUpstream upstream = new ReportUpstream();
        upstream.setId(upKey);
        upstream.setType(type);
        //请求
        if (type == 1) {
            String upStatus = redisClient.get(upKey);
            if (upStatus == null){
                redisClient.set(upKey, "1");
                redisClient.expire(upKey,5000);
                String downstreamReportId = date + hour + appId + slotId;
                upstream.setDownstreamReportId(downstreamReportId);
                upstream.setUpstreamId(upstreamId);
                upstream.setUpstreamType(upstreamType);
                upstream.setAppId(appId);
                upstream.setSlotId(slotId);
                upstream.setHour(hour);
                platformDao.insertUpstreamReport(upstream);
            }else{
                platformDao.updateUpstreamReport(upstream);
            }
        //返回 + 统计时间
        }else if(type == 2){
            if (time < 300){
                upstream.setT300(1);
            }else if(time >= 300 && time < 400){
                upstream.setT400(1);
            }else if(time >= 400 && time < 500){
                upstream.setT500(1);
            }else{
                upstream.setT1000(1);
            }
            platformDao.updateUpstreamReport(upstream);
        //曝光 点击
        }else{
            platformDao.updateUpstreamReport(upstream);
            //上报日志
            int eventStatus = platformDao.eventStatus(slotId);
            if (eventStatus == 1){
                //保存日志
                EventLog el = new EventLog();
                el.setAppId(appId);
                el.setSpaceId(slotId);
                el.setUpstreamId(upstreamId);
                el.setLogContent(log);
                el.setEventType(type);
                platformDao.insertEventLog(el);

            }
        }
    }


    /**
     * 测试接口
     */
    public GetAdsResp adTest(GetAdsReq gaReq, String dateStr, int hour, String appId, String slotId, int adtype){
        GetAdsResp gar;
        long startTime = System.currentTimeMillis();

        //上游統計
        upStreamReport(dateStr, hour, appId, slotId, slotId, 0, 1, 0, null);

        Map<String, Object> map = new HashMap<>();
        map.put("adType",adtype);
        GetUpstream guContent = platformDao.getAdTest(map);

        if (guContent != null){
            String content = guContent.getContent();
            gar = JSON.parseObject(content,GetAdsResp.class);

            //展现曝光
            List<String> winNotice = new ArrayList<>();
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gaReq.getSlot().getSlotId()+"&0&3");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1+"&event="+123456);
            gar.getAds().get(0).getMetaGroup().get(0).setWinNoticeUrls(winNotice);

            //点击
            List<String> clk  = new ArrayList<>();
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gaReq.getSlot().getSlotId()+"&0&4");
            clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            gar.getAds().get(0).getMetaGroup().get(0).setWinCNoticeUrls(clk);
            gar.setRequestId(gaReq.getRequestId());

            //上游統計
            long endTime = System.currentTimeMillis();
            upStreamReport(dateStr, hour, appId, slotId, slotId, 0, 2, (endTime-startTime), null);

        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }


}
