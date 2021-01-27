package com.zghd.entity.YouKu.request;

import java.util.List;

/**
 * Native
 */
public class Native {

    //原生广告模板ID数组
    private List<String> native_template_ids;
    //原生广告模板规则要求
    private List<Assets> assets;

    public List<String> getNative_template_ids() {
        return native_template_ids;
    }

    public void setNative_template_ids(List<String> native_template_ids) {
        this.native_template_ids = native_template_ids;
    }

    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }
}
