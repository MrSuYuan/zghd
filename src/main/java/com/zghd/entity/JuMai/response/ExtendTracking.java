package com.zghd.entity.JuMai.response;

import java.util.List;

/**
 * ExtendTracking 对象
 */
public class ExtendTracking {

    //deeplink 唤起成功上报地址
    private List<String> dktracking;
    //下载开始上报地址
    private List<String> dlstart;
    //下载完成上报地址
    private List<String> dlcomplete;
    //安装开始上报地址
    private List<String> istart;
    //安装完成上报地址
    private List<String> icomplete;
    //激活上报地址
    private List<String> activation;

    public List<String> getDktracking() {
        return dktracking;
    }

    public void setDktracking(List<String> dktracking) {
        this.dktracking = dktracking;
    }

    public List<String> getDlstart() {
        return dlstart;
    }

    public void setDlstart(List<String> dlstart) {
        this.dlstart = dlstart;
    }

    public List<String> getDlcomplete() {
        return dlcomplete;
    }

    public void setDlcomplete(List<String> dlcomplete) {
        this.dlcomplete = dlcomplete;
    }

    public List<String> getIstart() {
        return istart;
    }

    public void setIstart(List<String> istart) {
        this.istart = istart;
    }

    public List<String> getIcomplete() {
        return icomplete;
    }

    public void setIcomplete(List<String> icomplete) {
        this.icomplete = icomplete;
    }

    public List<String> getActivation() {
        return activation;
    }

    public void setActivation(List<String> activation) {
        this.activation = activation;
    }
}
