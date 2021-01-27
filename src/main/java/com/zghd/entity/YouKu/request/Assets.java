package com.zghd.entity.YouKu.request;

/**
 * 原生广告模板
 */
public class Assets {

    //原生广告模板ID
    private String native_template_id;
    //广告标题要求
    private Title title;
    //广告图片素材要求
    private ImageUrl image_url;
    //广告视频素材要求
    private VideoUrl video_url;
    //广告素材LOGO规格
    private LogoUrl logo_url;
    //广告素材品牌要求
    private Brand brand;

    public String getNative_template_id() {
        return native_template_id;
    }

    public void setNative_template_id(String native_template_id) {
        this.native_template_id = native_template_id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public ImageUrl getImage_url() {
        return image_url;
    }

    public void setImage_url(ImageUrl image_url) {
        this.image_url = image_url;
    }

    public VideoUrl getVideo_url() {
        return video_url;
    }

    public void setVideo_url(VideoUrl video_url) {
        this.video_url = video_url;
    }

    public LogoUrl getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(LogoUrl logo_url) {
        this.logo_url = logo_url;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
