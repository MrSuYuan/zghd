package com.zghd.service;

import com.util.md5.MD5;
import com.zghd.dao.PlatformDao;
import com.zghd.entity.JuKanDian.request.ArticleReport;
import com.zghd.entity.JuKanDian.request.Upstream;
import com.zghd.entity.platform.ReportDownstream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 聚看点文章接口
 */
@Service(value="jkdService")
public class JKDService {

    @Resource
    private PlatformDao platformDao;
    @Autowired
    com.util.redis.JedisClient redisClient;

    /**
     * 查询上游appkey 和 appid
     */
    public Upstream selectUpstream(String appId, String slotId){
        Upstream u = new Upstream();
        u.setAppId(appId);
        u.setSlotId(slotId);
        u = platformDao.getArticleUpstream(u);
        return u;
    }


    /**
     * 查询平台APP信息
     */
    public Upstream selectAppMsg(String upstreamAppkey, String upstreamAppid){
        Upstream u = new Upstream();
        u.setUpstreamAppkey(upstreamAppkey);
        u.setUpstreamAppid(upstreamAppid);
        u = platformDao.getArticleApp(u);
        return u;
    }


    /**
     * 统计信息
     * type 1用户 2列表 3详情 4分享 5回调
     */
    public void articleReport(String appId, String slotId, String upstreamAppkey, String upstreamAppid, int type){
        long startTime = System.currentTimeMillis();
        //统计使用时间参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//年月日
        String date= sdf.format(startTime);
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);
        String redisKey = MD5.md5(date + hour + appId + slotId + upstreamAppkey + upstreamAppid);
        String downStatus = redisClient.get(redisKey);
        ArticleReport ar = new ArticleReport();
        ar.setType(type);
        if (type == 1){
            ar.setUserTimes(1);
        }else if (type == 2){
            ar.setListTimes(1);
        }else if (type == 3){
            ar.setDetailTimes(1);
        }else if (type == 4){
            ar.setShareTimes(1);
        }else{
            ar.setBackTimes(1);
        }
        ar.setCreate_Time(date);
        ar.setAppId(appId);
        ar.setSlotId(slotId);
        ar.setUpstreamAppkey(upstreamAppkey);
        ar.setUpstreamAppid(upstreamAppid);
        ar.setHour(hour);
        //新增
        if (downStatus == null){
            System.out.println("曾");
            redisClient.set(redisKey,"1");
            redisClient.expire(redisKey,5000);
            platformDao.insertArticleReport(ar);
            //修改
        }else{
            System.out.println("改");
            platformDao.updateArticleReport(ar);
        }
    }

}
