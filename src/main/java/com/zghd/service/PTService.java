package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.md5.JiaMi;
import com.zghd.dao.PlatformDao;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.platform.GetUpstream;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平台测试广告
 */
@Service(value="ptService")
public class PTService {

    @Resource
    private PlatformDao platformDao;

    public GetAdsResp PTSend(GetAdsReq gaReq, GetUpstream gu){
        //获取下游上传的广告位id
        int adtype = gaReq.getSlot().getAdtype();
        Map<String, Object> map = new HashMap<>();
        map.put("adType",adtype);
        GetUpstream guContent = platformDao.getAdTest(map);
        String content = guContent.getContent();
        GetAdsResp gar = JSON.parseObject(content,GetAdsResp.class);

        //展现曝光
        List<String> winNotice = new ArrayList<>();
        String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-0-3");
        winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
        gar.getAds().get(0).getMetaGroup().get(0).setWinNoticeUrls(winNotice);

        //点击
        List<String> clk  = new ArrayList<>();
        String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-0-4");
        clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
        gar.getAds().get(0).getMetaGroup().get(0).setWinCNoticeUrls(clk);

        return gar;
    }

}
