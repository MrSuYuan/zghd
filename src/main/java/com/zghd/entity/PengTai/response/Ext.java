package com.zghd.entity.PengTai.response;

import java.util.List;

public class Ext {

    private List<String> downloadtrackers;
    private List<String> installtrackers;

    public List<String> getDownloadtrackers() {
        return downloadtrackers;
    }

    public void setDownloadtrackers(List<String> downloadtrackers) {
        this.downloadtrackers = downloadtrackers;
    }

    public List<String> getInstalltrackers() {
        return installtrackers;
    }

    public void setInstalltrackers(List<String> installtrackers) {
        this.installtrackers = installtrackers;
    }
}
