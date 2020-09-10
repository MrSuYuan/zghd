package com.zghd.entity.AZhe.response;

import java.util.List;

/**
 * 返回参数
 */
public class AZResponse {

    //0:正常 其他:异常
    private int result;
    //图片链接
    private String image_url;
    //上报检测使用的ua
    private String user_agent;
    //浏览器访问落地页时使用的ua
    private String browser_ua;
    //视频播放链接
    private String video_url;
    //target
    private String target;
    //接口信息
    private String msg;
    //检测链接集合
    private List<TargetAddition> target_addition;
    //下一次曝光请求的停留时间
    private int duration;
    //落地页对应的行为处理脚本的订单id ,注意行为脚本请求一定要带上这个
    private int js_order_id;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getBrowser_ua() {
        return browser_ua;
    }

    public void setBrowser_ua(String browser_ua) {
        this.browser_ua = browser_ua;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TargetAddition> getTarget_addition() {
        return target_addition;
    }

    public void setTarget_addition(List<TargetAddition> target_addition) {
        this.target_addition = target_addition;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getJs_order_id() {
        return js_order_id;
    }

    public void setJs_order_id(int js_order_id) {
        this.js_order_id = js_order_id;
    }
}
