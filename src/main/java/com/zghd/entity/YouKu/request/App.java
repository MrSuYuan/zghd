package com.zghd.entity.YouKu.request;

/**
 * app信息
 */
public class App {

    //App的名称，一般是"“优酷客户端"，或者"土豆客户端"。
    private String name;
    //App的包名，一般是com.youku.app。
    private String pck;
    //App bundlid。
    private String bundlid;
    //视频的内容相关信息。只有视频贴片类型的广告位才会有这个字段，内容与site.content对象相同
    private Content content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPck() {
        return pck;
    }

    public void setPck(String pck) {
        this.pck = pck;
    }

    public String getBundlid() {
        return bundlid;
    }

    public void setBundlid(String bundlid) {
        this.bundlid = bundlid;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
