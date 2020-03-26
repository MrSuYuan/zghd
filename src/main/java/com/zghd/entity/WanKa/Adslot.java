package com.zghd.entity.WanKa;

/**
 * 广告位信息
 */
public class Adslot {

    /**
     * 广告位id
     * @required false
     */
    private String adslot_id;
    /**
     * 素材渲染形式：0-任意形式，1-json格式（开发者需要自己渲染），2-html格式（已经渲染好的代码段，目前较少）。 任意形式时，会选择收益较高的广告形式返回
     * @required false
     */
    private int render_type;
    /**
     * 是否支持deeplink，0-不支持，1-支持
     * @required false
     */
    private int deeplink;

    public String getAdslot_id() {
        return adslot_id;
    }

    public void setAdslot_id(String adslot_id) {
        this.adslot_id = adslot_id;
    }

    public int getRender_type() {
        return render_type;
    }

    public void setRender_type(int render_type) {
        this.render_type = render_type;
    }

    public int getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(int deeplink) {
        this.deeplink = deeplink;
    }
}
