package com.zghd.entity.HuiChuan;

/**
 * app信息
 */
public class AdAppInfo {
    //平台分类枚举值 android/iphone/other现在基本都是用 android 和iphone，用户平台定向投放
    private String fr;
    //(淘内)安装序列号（外部）
    private String dn;
    //(淘内)安装序列号（内部）
    private String sn;
    //(淘内)阿里统一用户ID，淘内强烈建议填充，用于用户识别和定向投放
    private String utdid;
    //为1则支持https，为0则不支持https
    private String is_ssl;
    //包名，ios 取bundle id（小写归一化）（对于ios直接取appstore上的包名），用于渠道识别和广告效果分析
    private String pkg_name;
    //版本号，用于渠道识别和广告效果分析
    private String pkg_ver;
    //从UC开放平台获取（-iflow结尾，渠道结算标识）
    private String app_name;
    //User Agent，无法填充可以填空串
    private String ua;
    //App发行国家
    private String app_country;
    //App发行语言
    private String lang;
    //App发行时区
    private String timezone;

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUtdid() {
        return utdid;
    }

    public void setUtdid(String utdid) {
        this.utdid = utdid;
    }

    public String getIs_ssl() {
        return is_ssl;
    }

    public void setIs_ssl(String is_ssl) {
        this.is_ssl = is_ssl;
    }

    public String getPkg_name() {
        return pkg_name;
    }

    public void setPkg_name(String pkg_name) {
        this.pkg_name = pkg_name;
    }

    public String getPkg_ver() {
        return pkg_ver;
    }

    public void setPkg_ver(String pkg_ver) {
        this.pkg_ver = pkg_ver;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getApp_country() {
        return app_country;
    }

    public void setApp_country(String app_country) {
        this.app_country = app_country;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
