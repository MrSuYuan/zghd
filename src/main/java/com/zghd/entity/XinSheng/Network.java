package com.zghd.entity.XinSheng;

/**
 * 当前设备的网络信息
 */
public class Network {

    /**
     * ip地址
     */
    private String ip;
    /**
     * 系统webview的user‒agent
     */
    private String ua;
    /**
     * ip地址类型 0:ipv4 1:ipv6 默认0
     */
    private int ip_type;
    /**
     * 是否https环境 0:否 1:是 默认0
     */
    private int http_type;
    /**
     * 用户请求的来源页面url，用于转发给dsp
     */
    private String ref;
    /**
     * 广告所在页面url
     */
    private String url;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public int getIp_type() {
        return ip_type;
    }

    public void setIp_type(int ip_type) {
        this.ip_type = ip_type;
    }

    public int getHttp_type() {
        return http_type;
    }

    public void setHttp_type(int http_type) {
        this.http_type = http_type;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
