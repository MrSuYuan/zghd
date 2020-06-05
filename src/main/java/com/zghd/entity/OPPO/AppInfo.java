package com.zghd.entity.OPPO;

public class AppInfo {

    //联盟应用ID.
    private String appId;
    //应用唯一标示（即包名）
    private String pkgname;
    //应用版本名称(接入应用的版本名称)
    private String verName;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }
}
