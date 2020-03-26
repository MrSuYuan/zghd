package com.zghd.entity.JuLiang;

/**
 * 位置信息
 */
public class Geo {

    /**
     * 经度
     */
    private String lgd;
    /**
     * 纬度
     */
    private String ltd;
    /**
     * 详细地址
     */
    private String addr;

    public String getLgd() {
        return lgd;
    }

    public void setLgd(String lgd) {
        this.lgd = lgd;
    }

    public String getLtd() {
        return ltd;
    }

    public void setLtd(String ltd) {
        this.ltd = ltd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
