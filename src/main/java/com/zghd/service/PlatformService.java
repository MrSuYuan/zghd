package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.EncryptUtil;
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

            //分配上游
            List<GetUpstream> guList = getUpstream(slotId);

            //未绑定上游id 或者 上游id概率为0 的情况
            if(guList.size() == 0){
                gar = new GetAdsResp();
                gar.setRequestId(gaReq.getRequestId());
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");
                return gar;
            }

            //多上游循环请求
            for (GetUpstream gu : guList){
                //包名
                if("".equals(gu.getUpstreamPackageName()) || null==gu.getUpstreamPackageName()){
                    gu.setUpstreamPackageName(gaReq.getApp().getAppPackage());
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
                }else if(upstreamType == 6){
                    //logger.info("-小知-");
                    gar = xzService.XZSend(gaReq, gu);
                }else if(upstreamType == 7){
                    //logger.info("-旺脉-");
                    gar = wmService.WMSend(gaReq, gu);
                }else if(upstreamType == 8){
                    //logger.info("-甬祺-");
                    gar = yqService.YQSend(gaReq, gu);
                }else if(upstreamType == 9){
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
                }else if(upstreamType == 16){
                    //logger.info("-有道-");
                    gar = ydService.YDSend(gaReq, gu);
                }else if(upstreamType == 17){
                    //logger.info("-智友-");
                    gar = zyService.ZYSend(gaReq, gu);
                }else if(upstreamType == 18){
                    //logger.info("-汇川-");
                    gar = hcService.HCSend(gaReq, gu);
                }else if(upstreamType == 19){
                    //logger.info("-InMoBi-");
                    gar = imbService.IMBSend(gaReq, gu);
                }else if(upstreamType == 20){
                    //logger.info("-瑞郗-");
                    gar = rxService.RXSend(gaReq, gu);
                }else if(upstreamType == 21){
                    //logger.info("-OPPO-");
                    gar = oppoService.OPPOSend(gaReq, gu);
                }else if(upstreamType == 22){
                    //logger.info("-豆盟-");
                    gar = dmService.DMSend(gaReq, gu);

                }else{

                }
                //返回統計
                if ("200".equals(gar.getErrorCode())){
                    upStreamReport(dateStr, appId, slotId, gu.getUpstreamId(), upstreamType, 2);
                    break;
                }

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
     * 查询上游信息
     * @param spaceId
     * @return
     */
    public List<GetUpstream> getUpstream(String spaceId){
        //查看所有有分配流量的上游
        List<GetUpstream> gu = platformDao.getUpstream(spaceId);
        int df = 0;// 1东方
        int wk = 0;// 2万咖
        int jg = 0;// 3极光
        int yl = 0;// 4余梁
        int ydt = 0;// 5一点通
        int xz = 0; //6小知
        int wm = 0; //7旺脉
        int yq = 0; //8甬祺
        int dk = 0; //9点开
        int mjk = 0; //10迈吉客
        int jl = 0;  //11聚量
        int zm = 0;  //12众盟
        int hy = 0;  //13虹益
        int xs = 0;  //14新笙
        int rs = 0;  //15新笙
        int yd = 0;  //16有道
        int zy = 0;  //17智友
        int hc = 0;  //18汇川
        int imb = 0;  //19InMoBi
        int rx = 0;  //20瑞郗
        int oppo = 0;  //21oppo
        int dm = 0;  //22豆盟
        for(int i=0;i<gu.size();i++){
            if(gu.get(i).getUpstreamType() == 1){
                df = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 2){
                wk = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 3){
                jg = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 4){
                yl = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 5){
                ydt = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 7){
                wm = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 6){
                xz = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 8){
                yq = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 9){
                dk = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 10){
                mjk = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 11){
                jl = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 12){
                zm = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 13){
                hy = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 14){
                xs = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 15){
                rs = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 16){
                yd = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 17){
                zy = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 18){
                hc = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 19){
                imb = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 20){
                rx = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 21){
                oppo = gu.get(i).getProbability();
                continue;
            }else if(gu.get(i).getUpstreamType() == 22){
                dm = gu.get(i).getProbability();
                continue;
            }else{
                continue;
            }
        }
        //分配请求上游信息
        int upstreamType = 0;
        int d = (int)(Math.random()*100);
        if (d>=0 && d<df){
            upstreamType = 1;
        }else if(d>=df && d<(df+wk)){
            upstreamType = 2;
        }else if(d>=(df+wk) && d<(df+wk+jg)){
            upstreamType = 3;
        }else if(d>=(df+wk+jg) && d<(df+wk+jg+yl)){
            upstreamType = 4;
        }else if(d>=(df+wk+jg+yl) && d<(df+wk+jg+yl+ydt)){
            upstreamType = 5;
        }else if(d>=(df+wk+jg+yl+ydt) && d<(df+wk+jg+yl+ydt+xz)){
            upstreamType = 6;
        }else if(d>=(df+wk+jg+yl+ydt+xz) && d<(df+wk+jg+yl+ydt+xz+wm)){
            upstreamType = 7;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm) && d<(df+wk+jg+yl+ydt+xz+wm+yq)){
            upstreamType = 8;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk)){
            upstreamType = 9;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk)){
            upstreamType = 10;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl)){
            upstreamType = 11;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm)){
            upstreamType = 12;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy)){
            upstreamType = 13;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs)){
            upstreamType = 14;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs)){
            upstreamType = 15;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd)){
            upstreamType = 16;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy)){
            upstreamType = 17;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc)){
            upstreamType = 18;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb)){
            upstreamType = 19;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb+rx)){
            upstreamType = 20;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb+rx) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb+rx+oppo)){
            upstreamType = 21;
        }else if(d>=(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb+rx+oppo) && d<(df+wk+jg+yl+ydt+xz+wm+yq+dk+mjk+jl+zm+hy+xs+rs+yd+zy+hc+imb+rx+oppo+dm)){
            upstreamType = 22;
        }
        for(int i = 0; i < gu.size(); i++){
            if(upstreamType == gu.get(i).getUpstreamType()){
                GetUpstream getUpstream = gu.get(0);
                gu.set(0,gu.get(i));
                gu.set(i,getUpstream);
                break;
            }
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

            EncryptUtil eu = new EncryptUtil();
            //展现曝光
            List<String> winNotice = new ArrayList<>();
            String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gaReq.getSlot().getSlotId()+"&0&3","zghd");
            winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            gar.getAds().get(0).getMetaGroup().get(0).setWinNoticeUrls(winNotice);

            //点击
            List<String> clk  = new ArrayList<>();
            String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gaReq.getSlot().getSlotId()+"&0&4","zghd");
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


    public static void main(String[] args) {
        //模板
        /**
         EncryptUtil eu = new EncryptUtil();

         String param1 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&8&3","zghd");
         nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);

         String param2 = eu.AESencode(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&8&4","zghd");
         cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);

         */
        /*String s = "20200326ZG08HDtest1030319d75b2a20c7d36b801a262210a6dac";
        System.out.println(MD5.md5(s));
        System.out.println(MD5.md5(s));
        System.out.println(MD5.md5(s));*/


    }

}
