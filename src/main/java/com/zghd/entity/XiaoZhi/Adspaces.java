package com.zghd.entity.XiaoZhi;

import java.util.List;

/**
 * 广告位信息
 */
public class Adspaces {

    /**
     * 广告位id，唯一标识一个广告位，由INVENO提供
     * @request true
     */
    private String adspace_id;
    /**
     * 广告位类型
     * 1;  //横幅广告
     * 2;  //开屏广告
     * 3; //插屏广告
     * 4;  //信息流广告
     * 5;  //文字链广告
     * @request true
     */
    private int adspace_type;
    /**
     * 由INVENO渲染还是由媒体渲染，
     * INVENO渲染（HTML素材）：true；
     * 媒体渲染（图片素材或者原生素材）：false
     * true和false字母须全部小写
     * 现在只能写false
     * @request true
     */
    private boolean allowed_html;
    /**
     * 广告位宽度
     * @request true
     */
    private int width;
    /**
     * 广告位高度
     * @request true
     */
    private int height;
    /**
     * 当前广告位一次请求返回的创意个数，最多支持5个
     * 现在只支持１
     * @request true
     */
    private int impression_num;
    /**
     * 广告位允许的落地页打开类型
     * 0;//内开、外开都支持
     * 1;//内开,由应用webview打开
     * 2;  //外开，由系统浏览器打开传数值
     * 默认 0
     * @request true
     */
    private int open_type;
    /**
     * 广告位允许的交互类型
     * NO_INTERACTION = 1;//不交互
     * BROWSE= 2;//跳链接
     * DOWNLOAD = 3;//下载
     * DIALING = 4;//电话
     * MESSAGE = 5;//短信
     * MAIL = 6;//邮件
     * VIDEO=7；//视频播放
     * AUDIO=8；//音频播放
     * GIF=9；//GIF图播放
     * 取值数字数组
     * @request true
     */
    private List<Integer> interaction_type;
    /**
     * 文档没注释,但是写的默认为0
     * @request true
     */
    private int support_deeplink;
    /**
     * 对当前广告位所需物料有明确要求时，可以通过该字段指定物料被必备字段。
     * enum Asset {
     * TITLE = 1; //推广标题
     * TEXT = 2; //推广摘要
     * ICON_IMAGE = 3; //广告ICON 图标
     * MAIN_IMAGE = 4; //广告图片
     * }
     * 取值数字数组
     * @request true
     */
    private List<Integer> assets;

    public String getAdspace_id() {
        return adspace_id;
    }

    public void setAdspace_id(String adspace_id) {
        this.adspace_id = adspace_id;
    }

    public int getAdspace_type() {
        return adspace_type;
    }

    public void setAdspace_type(int adspace_type) {
        this.adspace_type = adspace_type;
    }

    public boolean isAllowed_html() {
        return allowed_html;
    }

    public void setAllowed_html(boolean allowed_html) {
        this.allowed_html = allowed_html;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getImpression_num() {
        return impression_num;
    }

    public void setImpression_num(int impression_num) {
        this.impression_num = impression_num;
    }

    public int getOpen_type() {
        return open_type;
    }

    public void setOpen_type(int open_type) {
        this.open_type = open_type;
    }

    public List<Integer> getInteraction_type() {
        return interaction_type;
    }

    public void setInteraction_type(List<Integer> interaction_type) {
        this.interaction_type = interaction_type;
    }

    public int getSupport_deeplink() {
        return support_deeplink;
    }

    public void setSupport_deeplink(int support_deeplink) {
        this.support_deeplink = support_deeplink;
    }

    public List<Integer> getAssets() {
        return assets;
    }

    public void setAssets(List<Integer> assets) {
        this.assets = assets;
    }
}
