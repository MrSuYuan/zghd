package com.zghd.entity.JuMai.response;

import java.util.List;

/**
 * ad对象
 */
public class JMAd {

    //	html 信息
    private String html;
    //图片宽度
    private int w;
    //图片高度
    private int h;
    //广告标题
    private String title;
    //广告描述
    private String desc;
    //图片地址
    private List<String> imgs;
    //图片 icon
    private String icon;
    //点击行为 1:页面跳转，2 :下载，3：广点通下载
    private int action;
    //链接地址，如果有宏替换的标识，也需要宏替换
    private String url;
    //deeplink 地址
    private String durl;
    //app 包名
    private String pkgname;
    //展示上报地址
    private List<String> exlist;
    //点击上报地址
    private List<String> cklist;
    //扩展跟踪地址
    private ExtendTracking exttracking;
    //激励视频相关
    private Videos videos;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDurl() {
        return durl;
    }

    public void setDurl(String durl) {
        this.durl = durl;
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public List<String> getExlist() {
        return exlist;
    }

    public void setExlist(List<String> exlist) {
        this.exlist = exlist;
    }

    public List<String> getCklist() {
        return cklist;
    }

    public void setCklist(List<String> cklist) {
        this.cklist = cklist;
    }

    public ExtendTracking getExttracking() {
        return exttracking;
    }

    public void setExttracking(ExtendTracking exttracking) {
        this.exttracking = exttracking;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
}
