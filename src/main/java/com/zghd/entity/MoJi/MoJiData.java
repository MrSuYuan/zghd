package com.zghd.entity.MoJi;

import java.util.List;

/**
 * 墨迹天气广告信息
 */
public class MoJiData {

    //广告位唯一标识
    private String adid;
    //每次请求的唯一标识
    private String sessionid;
    //广告类型 1 图片 2 视频
    private int type;
    //出价单位 (分)
    private int price;
    //广告计费类型： 1. CPM（品牌） 2. CPC（效果） 3. CPA（App：iOS 点击/Android 下载）
    private int chargingtype;
    //广告图片链接（信息流多图用分号隔开）
    private String imgurl;
    //小图标图片链接（扩展字段，请优先使用 imgurl）
    private String iconurl;
    //广告跳转链接
    private String clickurl;
    //点击上报（可以是多个 url，以;分隔支持宏替换,宏替换字符 ${winprice}）
    private String clktrack;
    //展示上报（可以是多个 url，以;分隔支持宏替换，宏替换字符 ${winprice}）
    private String imptrack;
    //展示上报，点击上报 多个 url 之间的分隔符，默认分号
    private String urlSeparator;
    //竞价成功通知的 URL（可以是多个 url，以;分隔支持宏替换，宏替换字符 ${winprice}）
    private String winnoticeurl;
    //图片或视频广告素材宽度
    private int adwidth;
    //图片或视频广告素材高度
    private int adheight;
    //广告标题(如果不填写由墨迹自动填充)
    private String adtitle;
    //广告内容
    private String adtext;
    //图片广告类型： 1：文字链（已废弃） 2：Banner 3：开屏
    private int adtype;
    //广告样式
    private int adstyle;
    //2048 , 4096, 8192 信息流位置的 Feed 样式，由客户控制，必须按广告位支持的 feed 类型进行随机返回。且仅支持 3，5，6 。必须按照每个类型的尺寸要求返回。
    private int feed_type;
    //广告的展示日期
    private String show_date;
    //响应的用户画像标签（对接方）
    private String user_tags;
    //广点通广告点击转化地址
    private String transformUrl;
    //除了2048 , 4096, 8192广告样式控制,需要返回此字段列表如下: 1纯图 2 左图右文本 3右图左文本 4 上标题下图和描述 5上文本下图 6上文本下三图 9 双icon 10 双纯图 必须按照每个类型的尺寸要求返回 如不需控制则返回0即可样式
    private int adstyle_control;
    //开屏类型 0 图片 2 视频
    private int splashtype;
    //deeplink (目前只支持开屏)
    private String deeplinkurl;
    //视频播放进度上报当需要进行视频进度打点时，需返回的参数格式："视频进度百分比" : "上报打点链接"（进度百分比限制为25、50、75、100，不必全部返回，返回需要上报打点的即可）（上报打点链接可以是多个 url，以;分隔）
    private List<Object> videoprogressurl;
    //区域标识
    private String region;

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getChargingtype() {
        return chargingtype;
    }

    public void setChargingtype(int chargingtype) {
        this.chargingtype = chargingtype;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getClickurl() {
        return clickurl;
    }

    public void setClickurl(String clickurl) {
        this.clickurl = clickurl;
    }

    public String getClktrack() {
        return clktrack;
    }

    public void setClktrack(String clktrack) {
        this.clktrack = clktrack;
    }

    public String getImptrack() {
        return imptrack;
    }

    public void setImptrack(String imptrack) {
        this.imptrack = imptrack;
    }

    public String getUrlSeparator() {
        return urlSeparator;
    }

    public void setUrlSeparator(String urlSeparator) {
        this.urlSeparator = urlSeparator;
    }

    public String getWinnoticeurl() {
        return winnoticeurl;
    }

    public void setWinnoticeurl(String winnoticeurl) {
        this.winnoticeurl = winnoticeurl;
    }

    public int getAdwidth() {
        return adwidth;
    }

    public void setAdwidth(int adwidth) {
        this.adwidth = adwidth;
    }

    public int getAdheight() {
        return adheight;
    }

    public void setAdheight(int adheight) {
        this.adheight = adheight;
    }

    public String getAdtitle() {
        return adtitle;
    }

    public void setAdtitle(String adtitle) {
        this.adtitle = adtitle;
    }

    public String getAdtext() {
        return adtext;
    }

    public void setAdtext(String adtext) {
        this.adtext = adtext;
    }

    public int getAdtype() {
        return adtype;
    }

    public void setAdtype(int adtype) {
        this.adtype = adtype;
    }

    public int getAdstyle() {
        return adstyle;
    }

    public void setAdstyle(int adstyle) {
        this.adstyle = adstyle;
    }

    public int getFeed_type() {
        return feed_type;
    }

    public void setFeed_type(int feed_type) {
        this.feed_type = feed_type;
    }

    public String getShow_date() {
        return show_date;
    }

    public void setShow_date(String show_date) {
        this.show_date = show_date;
    }

    public String getUser_tags() {
        return user_tags;
    }

    public void setUser_tags(String user_tags) {
        this.user_tags = user_tags;
    }

    public String getTransformUrl() {
        return transformUrl;
    }

    public void setTransformUrl(String transformUrl) {
        this.transformUrl = transformUrl;
    }

    public int getAdstyle_control() {
        return adstyle_control;
    }

    public void setAdstyle_control(int adstyle_control) {
        this.adstyle_control = adstyle_control;
    }

    public int getSplashtype() {
        return splashtype;
    }

    public void setSplashtype(int splashtype) {
        this.splashtype = splashtype;
    }

    public String getDeeplinkurl() {
        return deeplinkurl;
    }

    public void setDeeplinkurl(String deeplinkurl) {
        this.deeplinkurl = deeplinkurl;
    }

    public List<Object> getVideoprogressurl() {
        return videoprogressurl;
    }

    public void setVideoprogressurl(List<Object> videoprogressurl) {
        this.videoprogressurl = videoprogressurl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
