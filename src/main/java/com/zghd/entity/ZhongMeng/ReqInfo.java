package com.zghd.entity.ZhongMeng;

/**
 * 广告位请求配置数据
 */
public class ReqInfo {

    /**
     * 应⽤用分配的token(需众盟运营同学分配)
     */
    private String accessToken;
    /**
     * 分配的⼴广告位ID(需众盟同学分配)
     */
    private String adSlotId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAdSlotId() {
        return adSlotId;
    }

    public void setAdSlotId(String adSlotId) {
        this.adSlotId = adSlotId;
    }
}
