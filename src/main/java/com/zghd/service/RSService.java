package com.zghd.service;

import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.platform.GetUpstream;
import org.springframework.stereotype.Service;

/**
 * 瑞狮
 */
@Service(value="rsService")
public class RSService {

    /**
     * 瑞狮请求
     */
    public GetAdsResp RSSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        GetAdsResp gar = new GetAdsResp();
        return gar;
    }

}
