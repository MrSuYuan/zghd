package com.zghd.entity.YouKu.response;

/**
 * 参与竞价的原生广告创意信息
 */
public class Native {

    //参与竞价的原生广告模板ID
    private String native_template_id;
    //信息流素材Title信息（仅适用于先投后审的情况）
    private String title;
    //信息流图片素材信息（仅适用于先投后审的情况）
    private Image image;
    //信息流素材LOGO信息（仅适用于先投后审的情况）
    private Logo logo;
    //信息流素材VIDEO信息（仅适用于先投后审的情况）
    private Video video;
    //信息流素材品牌信息（仅适用于先投后审的情况）
    private String brand;

    public String getNative_template_id() {
        return native_template_id;
    }

    public void setNative_template_id(String native_template_id) {
        this.native_template_id = native_template_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
