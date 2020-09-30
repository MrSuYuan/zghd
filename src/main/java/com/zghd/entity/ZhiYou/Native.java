package com.zghd.entity.ZhiYou;

import java.util.List;

/**
 * 智友信息流
 */
public class Native {

    private String version;
    private List<Assets> assets;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }
}
