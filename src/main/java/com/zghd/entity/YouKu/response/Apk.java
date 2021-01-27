package com.zghd.entity.YouKu.response;

/**
 * 应用相关信息
 */
public class Apk {

    //应用下载链接，iOS为appstore地址，Android为apk包下载地址。回传后，“阿里妈妈全域媒体”视频信息流支持前置下载
    private String download_url;
    //应用包名，iOS为应用市场ID，即：appid；Android为包名
    private String packagename;
    //应用版本号
    private int versioncode;
    //应用版本名称
    private String versionname;
    //包大小，单位：字节
    private long size;
    //包签名
    private String sign;
    //包MD5
    private String md5;
    //系统最低版本要求
    private int minsdklevel;

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getMinsdklevel() {
        return minsdklevel;
    }

    public void setMinsdklevel(int minsdklevel) {
        this.minsdklevel = minsdklevel;
    }
}
