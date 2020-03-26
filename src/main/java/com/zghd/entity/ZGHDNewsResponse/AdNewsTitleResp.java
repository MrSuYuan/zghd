package com.zghd.entity.ZGHDNewsResponse;

import java.util.List;

/**
 * 新闻类标题
 */
public class AdNewsTitleResp {

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
     * 标题对象
     */
    private List<Title> titles;

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

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }
}
