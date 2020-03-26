package com.zghd.entity.WanKa;

/**
 * 基站信息
 */
public class BaseStation {

    /**
     * 基站编号 小区识别，为了唯一地表示 GSMPLMN 中的每个小区，网络运营者需分配给网络中所有的小区一个代码，取值范围：0~65535
     * @required false
     */
    private String cid;
    /**
     * 基站信号强度
     * @required false
     */
    private String bsss;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBsss() {
        return bsss;
    }

    public void setBsss(String bsss) {
        this.bsss = bsss;
    }
}
