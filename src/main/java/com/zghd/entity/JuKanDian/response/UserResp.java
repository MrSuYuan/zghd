package com.zghd.entity.JuKanDian.response;

/**
 * 用户初始化返回参数
 */
public class UserResp {

    private String errorCode;
    //状态码，成功或失败
    private int ret_code;
    //初始化成功后生成的令牌
    private String token;
    //生成的用户编号
    private String usercode;
    //初始化传入的用户ID
    private String userid;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
