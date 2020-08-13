package com.zghd.news;

import com.zghd.dao.PlatformDao;
import com.zghd.entity.ZGHDNewsResponse.AdNewsResp;
import com.zghd.entity.ZGHDNewsResponse.AdNewsTitleResp;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.platform.GetUpstream;
import com.zghd.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 新闻类内容广告
 */
@Service("platformNewsService")
public class PlatformNewsService {

    @Autowired
    private RSNewsService rsNewsService;
    @Autowired
    private PlatformService platformService;
    @Resource
    private PlatformDao platformDao;

    /**
     * 标题
     */
    public AdNewsTitleResp newsTitles(GetAdsReq gaReq)throws Exception{
        GetUpstream gu = platformDao.getNewsUpstream(gaReq.getSlot().getSlotId());
        AdNewsTitleResp ar = rsNewsService.titleSend(gaReq, gu);
        return ar;
    }

    /**
     * 内容
     */
    public AdNewsResp newsList(GetAdsReq gaReq)throws Exception{
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);
        GetUpstream gu = platformDao.getNewsUpstream(gaReq.getSlot().getSlotId());
        //时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(date);
        //請求統計
        //platformService.upStreamReport(dateStr, hour, gaReq.getApp().getAppId(), gaReq.getSlot().getSlotId(), gu.getUpstreamId(), 15, 1);
        AdNewsResp ar = rsNewsService.newsList(gaReq, gu);
        //返回統計
        if ("200".equals(ar.getErrorCode())){
            //platformService.upStreamReport(dateStr, hour, gaReq.getApp().getAppId(), gaReq.getSlot().getSlotId(), gu.getUpstreamId(), 15, 2);
        }
        return ar;
    }
}
