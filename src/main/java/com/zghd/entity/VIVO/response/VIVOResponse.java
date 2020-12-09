package com.zghd.entity.VIVO.response;

import java.util.List;

/**
 * VIVO返回数据
 */
public class VIVOResponse {

    /**
     * 错误码
     * 1成功 3/4/2006无填充 5/1000系统错误 其他参数错误
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 广告物料
     */
    private List<AdDTO> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AdDTO> getData() {
        return data;
    }

    public void setData(List<AdDTO> data) {
        this.data = data;
    }
}
