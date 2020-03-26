package com.zghd.entity.XinSheng;

/**
 * 广告位请求配置信息
 */
public class Req {

    /**
     * 合作方流量的请求ID，接口会在响应中返回
     */
    private String reqid;
    /**
     * api版本号 目前固定填: 1.4.1
     */
    private String version;

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
