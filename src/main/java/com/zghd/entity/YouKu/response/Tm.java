package com.zghd.entity.YouKu.response;

/**
 * 视频素材播中曝光监测URL
 */
public class Tm {

    //在播放器触发检测链接url的时间点，单位为秒。
    private int t;
    //展示监测链接，在指定时间点t发送，包括投放引擎、客户与第三方监测链接。
    private String url;

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
