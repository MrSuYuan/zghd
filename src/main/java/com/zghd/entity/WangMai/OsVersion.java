package com.zghd.entity.WangMai;

/**
 * 操作系统版本
 */
public class OsVersion {

    /**
     * 操作系统主版本号
     */
    private int major;
    /**
     * 操作系统副版本号
     */
    private int minor;
    /**
     * 操作系统小版本号
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
