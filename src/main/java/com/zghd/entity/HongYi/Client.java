package com.zghd.entity.HongYi;

/**
 * 客户端信息
 */
public class Client {

    /**
     * 广告客户端类型 (固定值 3)
     */
    private int type;
    /**
     * 请求 HYTECH的版本号， 当前版本“ 1.6.11”
     */
    private String version;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
