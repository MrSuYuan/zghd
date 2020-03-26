package com.zghd.entity.WanKa;

/**
 * 设备信息
 */
public class Device {

    /**
     * 设备类型 1：移动设备，2：个人计算机，3：连接电视 4：手机，5：平板，6：连通装置，7：机顶盒(所有手机端全部使用类型 4)
     * @required true
     */
    private int device_type;
    /**
     * 操作系统类型全小写 ：android | ios
     * @required true
     */
    private String os_type;
    /**
     * 操作系统版本
     * @required true
     */
    private String os_version;
    /**
     * 设备厂商名称
     * @required true
     */
    private String vendor;
    /**
     * 设备型号
     * @required true
     */
    private String model;
    /**
     * android设备系统id
     * @required true
     */
    private String android_id;
    /**
     * android系统必填，android设备系统id的md5值
     * @required true
     */
    private String android_id_md5;
    /**
     * android系统必填，android手机设备的imei或meid号
     * @required true
     */
    private String imei;
    /**
     * android系统必填，android手机设备的imei或meid号的md5值
     * @required true
     */
    private String imei_md5;
    /**
     * ios系统必填，ios系统广告标志符
     * @required true
     */
    private String idfa;
    /**
     * ios系统必填
     * @required true
     */
    private String idfv;
    /**
     * 设备屏幕宽度
     * @required true
     */
    private int w;
    /**
     * 设备屏幕高度
     * @required true
     */
    private int h;
    /**
     * 系统user-agent
     * @required true
     */
    private String ua;
    /**
     * 国际移动用户识别码
     * @required true
     */
    private String imsi;
    /**
     * 设备mac号
     * @required true
     */
    private String mac;
    /**
     * 像素密度，此数据将影响返回广告的清晰度
     * @required true
     */
    private int dpi;
    /**
     * ios设备唯一识别号
     * @required false
     */
    private String openudid;
    /**
     * android 设备的 Android Advertising ID，在保证取值正确有效的前提下填写，用于定向优化
     * @required false
     */
    private String android_advertising_id;
    /**
     * 设备的语言设置,使用 alpha-2/ISO 639-1
     * @required false
     */
    private String language;
    /**
     * 国家，使用 ISO-3166-1 Alpha-3
     * @required false
     */
    private String country;
    /**
     * 手机分辨率，例：1024*768
     * @required false
     */
    private String resolution;
    /**
     * 横屏竖屏。0：竖屏，1：横屏
     * @required false
     */
    private int orientation;
    /**
     * 用户设备HTTP请求头中的Referer字段
     * @required false
     */
    private String referer;
    /**
     * 设备电量百分比，取整数，数值区间0~100。例：75%的电量，则传值75
     * @required false
     */
    private int battery;
    /**
     * android设备是否root，ios设备是否越狱。 0：否，1：是
     * @required false
     */
    private int isroot;
    /**
     * 蓝牙mac号。有蓝牙功能的设备存在该参数
     * @required false
     */
    private String btmac;
    /**
     * 安卓设备pseudo-unique id
     * @required false
     */
    private String pdunid;
    /**
     * cpu类型
     * @required false
     */
    private String cputy;
    /**
     * cpu序列号
     * @required false
     */
    private String cpusn;
    /**
     * android 设备的 iccid 值
     * @required false
     */
    private String iccid;

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
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

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getAndroid_id_md5() {
        return android_id_md5;
    }

    public void setAndroid_id_md5(String android_id_md5) {
        this.android_id_md5 = android_id_md5;
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

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getOpenudid() {
        return openudid;
    }

    public void setOpenudid(String openudid) {
        this.openudid = openudid;
    }

    public String getAndroid_advertising_id() {
        return android_advertising_id;
    }

    public void setAndroid_advertising_id(String android_advertising_id) {
        this.android_advertising_id = android_advertising_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getIsroot() {
        return isroot;
    }

    public void setIsroot(int isroot) {
        this.isroot = isroot;
    }

    public String getBtmac() {
        return btmac;
    }

    public void setBtmac(String btmac) {
        this.btmac = btmac;
    }

    public String getPdunid() {
        return pdunid;
    }

    public void setPdunid(String pdunid) {
        this.pdunid = pdunid;
    }

    public String getCputy() {
        return cputy;
    }

    public void setCputy(String cputy) {
        this.cputy = cputy;
    }

    public String getCpusn() {
        return cpusn;
    }

    public void setCpusn(String cpusn) {
        this.cpusn = cpusn;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
}
