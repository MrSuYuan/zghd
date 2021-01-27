package com.zghd.entity.YouKu.request;

/**
 * 扩展字段
 */
public class Ext {

    //本次竞价请求支持返回的最大广告素材数量（信息流和贴片资源需要处理）。贴片资源中，以15s为一个单位，累计repeat*15s的最长播放时长素材（单个素材满足15s的倍数）。例如：repeat=3，支持所有素材时长累计3*15s=45s，可返回素材序列 【3个15s，1个30s+1个15s，1个45s素材】。多贴广告竞价的规则，具体参见附录竞价规则说明
    private int repeat;
    //订单预投起始时间戳（单位：秒）目前仅PDB开屏广告使用该字段
    private int campaign_start;
    //订单预投终止时间戳（单位：秒）目前仅PDB开屏广告使用该字段
    private int campaign_end;

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getCampaign_start() {
        return campaign_start;
    }

    public void setCampaign_start(int campaign_start) {
        this.campaign_start = campaign_start;
    }

    public int getCampaign_end() {
        return campaign_end;
    }

    public void setCampaign_end(int campaign_end) {
        this.campaign_end = campaign_end;
    }
}
