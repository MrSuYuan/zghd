package com.zghd.entity.TuiA.request;

/**
 * 推啊需要加密和压缩的参数
 */
public class Md {

    //安卓设备信息
    private String imei;
    //ios设备信息
    private String idfa;
    //安卓Q设备信息
    private String oaid;
    //用户唯一标识
    private String device_id;
    //版本号,固定1.0.0
    private String api_version;
    //用户偏好广告(DMP 标签， ec ： 电商类， loan ： 贷款类，game：传奇游戏...)
    private String advert_like_type;
    //经度
    private String longitude;
    //维度
    private String latitude;
    //操作系统 Android，iOS
    private String os;
    //页面标题
    private String page_title;
    //网络类型 wif i/3G/4G/2G
    private String nt;
    //APP安装列表,逗号分隔包名
    private String apps;
    //活跃列表,包名、应⽤使⽤时长、启动次数{"packageName ":"com.ss.android.ugc.live","totalTimeInForeground":67616,"useTimes":3}
    private String apps_active_list;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getAdvert_like_type() {
        return advert_like_type;
    }

    public void setAdvert_like_type(String advert_like_type) {
        this.advert_like_type = advert_like_type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getApps() {
        return apps;
    }

    public void setApps(String apps) {
        this.apps = apps;
    }

    public String getApps_active_list() {
        return apps_active_list;
    }

    public void setApps_active_list(String apps_active_list) {
        this.apps_active_list = apps_active_list;
    }
}
