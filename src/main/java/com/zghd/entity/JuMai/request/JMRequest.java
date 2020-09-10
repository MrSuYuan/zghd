package com.zghd.entity.JuMai.request;

/**
 * 俱脉
 */
public class JMRequest {

    //请求唯一 ID
    private String rid;
    //媒体 ID，请联系商务/渠道人员获取
    private String pid;
    //请求接口的时间戳，精确到毫秒
    private long time;
    //加密 签名，规则 ：md5(pid+key+time) 大写, 媒体 KEY 请联系商务/渠道人员获取
    private String token;
    //App 相关信息
    private App appinfo;
    //设备信息
    private Device deviceinfo;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public App getAppinfo() {
        return appinfo;
    }

    public void setAppinfo(App appinfo) {
        this.appinfo = appinfo;
    }

    public Device getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(Device deviceinfo) {
        this.deviceinfo = deviceinfo;
    }
}
