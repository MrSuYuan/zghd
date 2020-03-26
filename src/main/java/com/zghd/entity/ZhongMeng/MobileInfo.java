package com.zghd.entity.ZhongMeng;

/**
 * 手机的相关配置
 */
public class MobileInfo {

    /**
     * 操作系统版本 e.g. 6.0.2
     */
    private String osVersion;
    /**
     * 软件应用版本 e.g. 3.2.9
     */
    private String appVersion;
    /**
     * 手机设备型号 e.g. xiaomi5
     */
    private String mobileModel;
    /**
     * 手机设备⼚厂商 e.g. XiaoMi
     */
    private String vendor;
    /**
     * 网络连接类型
     * 1:wifi 2:2g 3:3g 4:4g 5:5g 100:未知
     */
    private int connectionType;
    /**
     * 网络运营商类型
     * 0:未知 1:中国移动 2:电信 3:联通 99:其他
     */
    private int operatorType;
    /**
     * 安卓设备唯一标识(获取不到传空字符串)
     */
    private String imsi;
    /**
     * 安卓移动设备号
     */
    private String imei;
    /**
     * imei替补值(imei获取不到)
     */
    private String imei_md5;
    /**
     * 安卓设备唯一标识(获取不到传空字符串)
     */
    private String androidId;
    /**
     * androidId替补值
     */
    private String androidId_md5;
    /**
     * 苹果设备唯一标识
     */
    private String idfa;
    /**
     * idfa替补值
     */
    private String idfa_md5;
    /**
     * 苹果唯一标识(获取不不到传空字符串)
     */
    private String idfv;
    /**
     * 苹果唯一标识(获取不不到传空字符串)
     */
    private String openUdid;
    /**
     * 手机唯一标识
     */
    private String mac;
    /**
     * 设备类型:
     * 1:手机 2:平板 3:智能电视 4:户外屏
     */
    private int deviceType;
    /**
     * 系统类型:
     * 0:Android 1:IOS
     */
    private int osType;
    /**
     * 手机物理理屏幕⻓长度(建议设置)
     */
    private int screenWidth;
    /**
     * 手机物理理屏幕宽度(建议设置)
     */
    private int screenHeight;
    /**
     * 移动国家码(建议设置)
     */
    private String mcc;
    /**
     * 移动网络码(建议设置)
     */
    private String mnc;
    /**
     * 屏幕密度(建议设置) e.g.2.0
     */
    private float deny;
    /**
     * 是否⽀支持https⼴广告,默认为false(不⽀支持)
     */
    private boolean supportHttps;

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImei_md5() {
        return imei_md5;
    }

    public void setImei_md5(String imei_md5) {
        this.imei_md5 = imei_md5;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidId_md5() {
        return androidId_md5;
    }

    public void setAndroidId_md5(String androidId_md5) {
        this.androidId_md5 = androidId_md5;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfa_md5() {
        return idfa_md5;
    }

    public void setIdfa_md5(String idfa_md5) {
        this.idfa_md5 = idfa_md5;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
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

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public float getDeny() {
        return deny;
    }

    public void setDeny(float deny) {
        this.deny = deny;
    }

    public boolean isSupportHttps() {
        return supportHttps;
    }

    public void setSupportHttps(boolean supportHttps) {
        this.supportHttps = supportHttps;
    }
}
