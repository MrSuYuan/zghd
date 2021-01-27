package com.zghd.entity.YouKu.response;

import java.util.List;

/**
 * 扩展字段
 */
public class Ext {

    //点击目标URL。这个字段是新的用来替代clickm的，所以请保证clickm和ldp两个字段只有一个填写。如果两个都填写，我们会优先取ldp。
    private String ldp;
    //deeplink地址。目前只移动APP，PC及移动WAP暂不支持。dp跳转失败会使用ldp进行跳转
    private String dp;
    //0：普通链接；1：iOS下载地址；2：安卓apk下载地址。3：deeplink跳转（dp参数必须返回）；4：universallink跳转（仅IOS支持），universallink地址对应ldp参数。注：目前仅支持淘宝换端，dp的白名单规则为：tbopen://m.taobao.com开头，Uniervallink以https://b.mashort.cn开头。返回其他默认为0
    private int ldptype;
    //曝光监测URL，可以有多条，建议最多3条，数量太多可能被审核拒审。注意格式为json数组
    private List<String> pm;
    //可选字段，曝光监测URL，广告播放结束后触发，可以有多个。注意是json数组 。1.3.0.3 版本新增
    private List<String> em;
    //可选字段，视频素材播中曝光监测URL，指定时间点发送的一系列检测地址，可以有多个注意是json数组 。1.3.0.3 版本新增
    private List<Tm> tm;
    //点击监测URL，可以有多条，建议最多3条，数量太多可能被审核拒审。注意是json数组
    private List<String> cm;
    //素材的类型。注意：只有当素材为动态HTML snippet时，需要指定"type":"c"; 当素材为先投后审的静态或native素材时，需要指定"type":"x"。其他类型的素材均不需要提供type字段。
    private String type;
    //PDB送审的监测地址中宏替换值，格式为K/V格式，K格式只能使用字母、数字和下划线并且宏由双下划线开始和双下划线结束，例如__MAC__
    private String macro;
    //应用相关信息，应用下载推荐回传，该字段会关联终端显示，有利于提高应用下载投放效率。
    private Apk apk;

    public String getLdp() {
        return ldp;
    }

    public void setLdp(String ldp) {
        this.ldp = ldp;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public int getLdptype() {
        return ldptype;
    }

    public void setLdptype(int ldptype) {
        this.ldptype = ldptype;
    }

    public List<String> getPm() {
        return pm;
    }

    public void setPm(List<String> pm) {
        this.pm = pm;
    }

    public List<String> getEm() {
        return em;
    }

    public void setEm(List<String> em) {
        this.em = em;
    }

    public List<Tm> getTm() {
        return tm;
    }

    public void setTm(List<Tm> tm) {
        this.tm = tm;
    }

    public List<String> getCm() {
        return cm;
    }

    public void setCm(List<String> cm) {
        this.cm = cm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMacro() {
        return macro;
    }

    public void setMacro(String macro) {
        this.macro = macro;
    }

    public Apk getApk() {
        return apk;
    }

    public void setApk(Apk apk) {
        this.apk = apk;
    }
}
