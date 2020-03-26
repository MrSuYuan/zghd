package com.zghd.entity.ZGHDNewsResponse;

import java.util.List;

/**
 * 瑞狮新闻内容
 */
public class AdNewsResp {

    /**
     * 对应请求id
     */
    private String requestId;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 内容
     */
    private List<Material> materials;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
