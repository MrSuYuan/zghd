package com.zghd.entity.JuKanDian.response;

import java.util.List;

public class ArticleTypeResp {

    private String errorCode;
    //状态码，成功或失败
    private int ret_code;
    //文章列表数组
    private List<ArticleTypeDetail> types;

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

    public List<ArticleTypeDetail> getTypes() {
        return types;
    }

    public void setTypes(List<ArticleTypeDetail> types) {
        this.types = types;
    }
}
