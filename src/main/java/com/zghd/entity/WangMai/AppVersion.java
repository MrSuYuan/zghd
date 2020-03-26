package com.zghd.entity.WangMai;

/**
 * app版本信息
 */
public class AppVersion {

    /**
     * 应用主版本号
     */
    private int major;
    /**
     * 应用副版本号
     */
    private int minor;
    /**
     * 应用小版本号
     */
    private int micro;

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getMicro() {
        return micro;
    }

    public void setMicro(int micro) {
        this.micro = micro;
    }
}
