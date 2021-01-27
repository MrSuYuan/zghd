package com.zghd.entity.YouKu.request;

/**
 * 视频的内容相关信息
 */
public class Content {

    //视频标题名称
    private String title;
    //视频标签关键字，如果是多个关键字，则使用英文竖线分隔
    private String keywords;
    //参见site.content.ext描述
    private ContentExt ext;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public ContentExt getExt() {
        return ext;
    }

    public void setExt(ContentExt ext) {
        this.ext = ext;
    }
}
