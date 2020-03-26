package com.zghd.entity.DongFang;

/**
 * 东方数据
 */
public class RequestJson {

    /**
     * IP地址，务必传客户端真实ip
     * @request true
     */
    private String clientIp;
    /**
     * 用户id
     * @request false
     */
    private String accId;
    /**
     * 素材个数
     * @request true
     */
    private int mCount;
    /**
     * 素材样式: 大图:001，单图:002，三图:003，开屏:004，激励视频:018 支持多个素材
     * @request true
     */
    private String mStyle;
    /**
     * 广告位id(正式id请找商务申请)
     * @request true
     */
    private String apid;
    /**
     * 应用包名
     * @request true
     */
    private String packageName;
    /**
     * 应用版本
     * @request true
     */
    private String appVer;
    /**
     * 回传参数,上一次返回参数值
     * @request false
     */
    private String passBack;
    /**
     * Android 传IMEI号  IOS传IDFA 不可为空，数据使用MD5加密，格式32位小写
     * @request true
     */
    private String imei;
    /**
     * 第二个 IMEI，数据使用MD5加密，格式32位小写
     * @request false
     */
    private String imei2;
    /**
     * 设备系统ID IOS传idfv，android传唯一id
     * @request true
     */
    private String deviceId;
    /**
     * 网卡MAC地址
     * @request true
     */
    private String mac;
    /**
     * 设备系统
     * @request true
     */
    private String os;
    /**
     * 设备系统版本
     * @request true
     */
    private String osVersion;
    /**
     * 网络连接类型: Unknown:0，WIIF:1 ，2G:2，3G:3，4G:4，5G:5)
     * @request true
     */
    private int network;
    /**
     * 设备厂商名称
     * @request true
     */
    private String vendor;
    /**
     * 设备型号
     * @request true
     */
    private String model;
    /**
     * 运营商信息： 46000 中国移动 46001 中国联通 46002 中国移动 46003 中国电信
     * @request true
     */
    private String operater;
    /**
     * 设备User_agent
     * @request true
     */
    private String userAgent;
    /**
     * 设备屏幕宽
     * @request true
     */
    private int screenWidth;
    /**
     * 设备屏幕高
     * @request true
     */
    private int screenHeight;
    /**
     * 地域(单位:省)
     * @request true
     */
    private String position;
    /**
     * 纬度
     * @request true
     */
    private String lat;
    /**
     * 经度
     * @request true
     */
    private String lng;
    /**
     * 平台: Android:1，iOS:2，PC:3，WAP:4
     * @request true
     */
    private int platform;
    /**
     * 广告宽
     * @request true
     */
    private int mWidth;
    /**
     * 广告高
     * @request true
     */
    private int mHeight;
    /**
     * 是否支持https 支持 ：1 不支持：0
     * @request false
     */
    private int isHttps;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public String getmStyle() {
        return mStyle;
    }

    public void setmStyle(String mStyle) {
        this.mStyle = mStyle;
    }

    public String getApid() {
        return apid;
    }

    public void setApid(String apid) {
        this.apid = apid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getPassBack() {
        return passBack;
    }

    public void setPassBack(String passBack) {
        this.passBack = passBack;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public int getIsHttps() {
        return isHttps;
    }

    public void setIsHttps(int isHttps) {
        this.isHttps = isHttps;
    }
}
