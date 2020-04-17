package com.zghd.entity.MoJi;

/**
 * 墨迹天气返回参数
 */
public class MoJiResp {

    /**
     * code
     * 200成功  400无广告 402参数错误 501服务器错误
     */
    private int code;
    /**
     * 广告信息
     */
    private MoJiData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MoJiData getData() {
        return data;
    }

    public void setData(MoJiData data) {
        this.data = data;
    }
}
