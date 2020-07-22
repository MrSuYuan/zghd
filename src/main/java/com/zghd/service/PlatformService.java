package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.dao.PlatformDao;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.platform.GetUpstream;
import com.zghd.entity.platform.ReportDownstream;
import com.zghd.entity.platform.ReportUpstream;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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

    private Logger logger = Logger.getLogger(this.getClass());


    /**
     * 请求调度
     */
    public GetAdsResp adVideo(GetAdsReq gaReq)throws Exception{
        GetAdsResp gar = null;

        try{
            //获取下游上传的广告位id
            String appId = gaReq.getApp().getAppId();
            String slotId = gaReq.getSlot().getSlotId();
            //时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            //下游请求统计
            downStreamReport(appId, slotId, dateStr);

            //查看所有有分配流量的上游
            List<GetUpstream> guList = platformDao.getUpstream(slotId);

            //未绑定上游id 或者 上游id概率为0 的情况
            if(guList.size() == 0){
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

            //上游請求統計
            upStreamReport(dateStr, appId, slotId, gu.getUpstreamId(), upstreamType, 1);

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

            }else{

            }
            //返回統計
            if ("200".equals(gar.getErrorCode())){
                upStreamReport(dateStr, appId, slotId, gu.getUpstreamId(), upstreamType, 2);
            }

        }catch(Exception e){
            logger.info("-------------[錯誤請求參數]"+JSONObject.fromObject(gaReq));
            gar = new GetAdsResp();
            gar.setErrorCode("500");
            gar.setMsg("SERVER_ERROR");
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
    public void downStreamReport(String appId, String slotId, String date){
        String downKey = date + appId + slotId;
        String downStatus = redisClient.get(downKey);
        //新增
        if (downStatus == null){
            redisClient.set(downKey,"1");
            ReportDownstream downstream = new ReportDownstream();
            downstream.setDownstreamReportId(downKey);
            downstream.setAppId(appId);
            downstream.setSlotId(slotId);
            downstream.setDownstreamRequest(1);
            platformDao.insertDownStreamReport(downstream);
        //修改
        }else{
            platformDao.updateDownStreamReport(downKey);
        }
    }


    /**
     * 上游请求统计
     * type 1请求 2返回 3曝光 4点击
     */
    public void upStreamReport(String date, String appId, String slotId, String upstreamId, int upstreamType, int type){
        String upKey = MD5.md5(date + appId + slotId + upstreamId);
        ReportUpstream upstream = new ReportUpstream();
        upstream.setId(upKey);
        upstream.setType(type);
        //请求
        if (type == 1){
            String upStatus = redisClient.get(upKey);
            if (upStatus == null){
                redisClient.set(upKey, "1");
                String downstreamReportId = date + appId + slotId;
                upstream.setDownstreamReportId(downstreamReportId);
                upstream.setUpstreamId(upstreamId);
                upstream.setUpstreamType(upstreamType);
                upstream.setAppId(appId);
                upstream.setSlotId(slotId);
                platformDao.insertUpstreamReport(upstream);
            }else{
                platformDao.updateUpstreamReport(upstream);
            }
        //返回 曝光 点击
        }else{
            platformDao.updateUpstreamReport(upstream);
        }
    }


    /**
     * 测试接口
     */
    public GetAdsResp adTest(GetAdsReq gaReq, String dateStr, String appId, String slotId, int adtype){
        GetAdsResp gar;
        //下游请求统计
        downStreamReport(appId, slotId, dateStr);

        //上游请求统计
        upStreamReport(dateStr, appId, slotId, slotId, 0, 1);

        Map<String, Object> map = new HashMap<>();
        map.put("adType",adtype);
        GetUpstream guContent = platformDao.getAdTest(map);

        if (guContent != null){
            String content = guContent.getContent();
            gar = JSON.parseObject(content,GetAdsResp.class);

            //展现曝光
            List<String> winNotice = new ArrayList<>();
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gaReq.getSlot().getSlotId()+"-0-3");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            gar.getAds().get(0).getMetaGroup().get(0).setWinNoticeUrls(winNotice);

            //点击
            List<String> clk  = new ArrayList<>();
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gaReq.getSlot().getSlotId()+"-0-4");
            clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            gar.getAds().get(0).getMetaGroup().get(0).setWinCNoticeUrls(clk);
            gar.setRequestId(gaReq.getRequestId());

            //上游返回统计
            upStreamReport(dateStr, appId, slotId, slotId, 0, 2);

        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }

}
