package com.zghd.entity.JuKanDian.response;

/**
 * 文章详情返回参数
 */
public class ArticleDetailResp {

    private String errorCode;
    //状态码，成功或失败
    private int ret_code;
    //文章页面URL
    private String openUrl;
    //埋点数据
    private String ctxData;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public String getCtxData() {
        return ctxData;
    }

    public void setCtxData(String ctxData) {
        this.ctxData = ctxData;
    }
}
