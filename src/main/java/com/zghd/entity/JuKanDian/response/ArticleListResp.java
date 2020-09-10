package com.zghd.entity.JuKanDian.response;

import java.util.List;

/**
 * 文章列表返回参数
 */
public class ArticleListResp {

    private String errorCode;
    //状态码，成功或失败
    private int ret_code;
    //文章列表数组
    private List<ArticleListDetail> artlist;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<ArticleListDetail> getArtlist() {
        return artlist;
    }

    public void setArtlist(List<ArticleListDetail> artlist) {
        this.artlist = artlist;
    }
}
