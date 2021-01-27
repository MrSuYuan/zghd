package com.zghd.entity.YouKu.request;

/**
 * 网站来源信息
 */
public class Site {

    //媒体网站名称
    private String name;
    //当前页面URL
    private String page;
    //Referrer URL
    private String ref;
    //视频的内容相关信息。只有视频贴片类型的广告位才会有这个字段，参见site.content对象描述
    private Content content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
