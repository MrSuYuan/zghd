package com.zghd.entity.TuiA.response;

/**
 * 推啊返回素材
 */
public class TAResponse {

    //0正确
    private String code;
    //参数
    private TAData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TAData getData() {
        return data;
    }

    public void setData(TAData data) {
        this.data = data;
    }
}
