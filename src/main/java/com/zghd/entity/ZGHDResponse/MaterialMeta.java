package com.zghd.entity.ZGHDResponse;

import java.util.List;

/**
 * 物料信息
 */
public class MaterialMeta {

    /**
     * 广告推广标题
     */
    private String adTitle;
    /**
     * 广告描述
     */
    private List<String> descs;
    /**
     * 广告图片地址
     */
    private List<String> imageUrl;
    /**
     * 物料的宽度
     */
    private int materialWidth;
    /**
     * 物料的高度
     */
    private int materialHeight;
    /**
     * 广告图标地址
     */
    private List<String> iconUrls;
    /**
     * 点击行为地址
     */
    private String clickUrl;
    /**
     * 创意类型
     * 1-纯文字  2-纯图片  3-图文混合  4-其他
     */
    private int creativeType;
    /**
     * 广告类型
     * 0-其他  1-浏览  2-下载
     */
    private int interactionType;
    /**
     * 下载类特殊处理类型
     */
    private int protocolType;
    /**
     * 下载类广告应用包名
     */
    private String packageName;
    /**
     * 下载类广告应用大小
     */
    private int appSize;
    /**
     * 广告视频物料地址
     */
    private String videoUrl;
    /**
     * 下载类为app名
     * 非下载类为推广的品牌名
     */
    private String brandName;
    /**
     * 广告视频物料时长
     */
    private int videoDuration;
    /**
     * 每条广告对应元素组中元数据总数
     */
    private int totalNum;
    /**
     * 当前元数据所在索引
     */
    private int currentIndex;
    /**
     * deepLink
     */
    private boolean deepLink;
    /**
     * deepLinkUrl
     */
    private String deepLinkUrl;

    private Float rating;
    private Integer comments;
    private List<String> arrDownloadTrackUrl;//开始下载上报地址（一维数组）
    private List<String> arrDownloadedTrakUrl;//下载完成上报地址（一维数组）
    private List<String> arrIntallTrackUrl;//开始安装上报地址（一维数组）
    private List<String> arrIntalledTrackUrl;//安装完成上报地址（一维数组）

    //视频加载完成
    private List<String> winLoadUrls;
    //广告跳过
    private List<String> winIgnoreUrls;
    //曝光展现
    private List<String> winNoticeUrls;
    //点击
    private List<String> winCNoticeUrls;
    //关闭
    private List<String> winCloseUrls;
    //下载
    private List<String> winDownloadUrls;
    //下载完成
    private List<String> winDownloadEndUrls;
    //安装
    private List<String> winInstallUrls;
    //安装完成
    private List<String> winInstallEndUrls;
    //安装完成打开
    private List<String> winInstallOpenUrls;
    //激活
    private List<String> winActiveUrls;
    //用户完成激励
    private List<String> winCompleteUrls;

    public List<String> getWinIgnoreUrls() {
        return winIgnoreUrls;
    }

    public void setWinIgnoreUrls(List<String> winIgnoreUrls) {
        this.winIgnoreUrls = winIgnoreUrls;
    }

    public Float getRating() {
        return rating;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public List<String> getArrDownloadTrackUrl() {
        return arrDownloadTrackUrl;
    }

    public void setArrDownloadTrackUrl(List<String> arrDownloadTrackUrl) {
        this.arrDownloadTrackUrl = arrDownloadTrackUrl;
    }

    public List<String> getArrDownloadedTrakUrl() {
        return arrDownloadedTrakUrl;
    }

    public void setArrDownloadedTrakUrl(List<String> arrDownloadedTrakUrl) {
        this.arrDownloadedTrakUrl = arrDownloadedTrakUrl;
    }

    public List<String> getArrIntallTrackUrl() {
        return arrIntallTrackUrl;
    }

    public void setArrIntallTrackUrl(List<String> arrIntallTrackUrl) {
        this.arrIntallTrackUrl = arrIntallTrackUrl;
    }

    public List<String> getArrIntalledTrackUrl() {
        return arrIntalledTrackUrl;
    }

    public void setArrIntalledTrackUrl(List<String> arrIntalledTrackUrl) {
        this.arrIntalledTrackUrl = arrIntalledTrackUrl;
    }

    public boolean getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(boolean deepLink) {
        this.deepLink = deepLink;
    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public void setDeepLinkUrl(String deepLinkUrl) {
        this.deepLinkUrl = deepLinkUrl;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public List<String> getDescs() {
        return descs;
    }

    public void setDescs(List<String> descs) {
        this.descs = descs;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMaterialWidth() {
        return materialWidth;
    }

    public void setMaterialWidth(int materialWidth) {
        this.materialWidth = materialWidth;
    }

    public int getMaterialHeight() {
        return materialHeight;
    }

    public void setMaterialHeight(int materialHeight) {
        this.materialHeight = materialHeight;
    }

    public List<String> getIconUrls() {
        return iconUrls;
    }

    public void setIconUrls(List<String> iconUrls) {
        this.iconUrls = iconUrls;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public int getCreativeType() {
        return creativeType;
    }

    public void setCreativeType(int creativeType) {
        this.creativeType = creativeType;
    }

    public int getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(int interactionType) {
        this.interactionType = interactionType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getAppSize() {
        return appSize;
    }

    public void setAppSize(int appSize) {
        this.appSize = appSize;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public List<String> getWinLoadUrls() {
        return winLoadUrls;
    }

    public void setWinLoadUrls(List<String> winLoadUrls) {
        this.winLoadUrls = winLoadUrls;
    }

    public List<String> getWinNoticeUrls() {
        return winNoticeUrls;
    }

    public void setWinNoticeUrls(List<String> winNoticeUrls) {
        this.winNoticeUrls = winNoticeUrls;
    }

    public List<String> getWinCNoticeUrls() {
        return winCNoticeUrls;
    }

    public void setWinCNoticeUrls(List<String> winCNoticeUrls) {
        this.winCNoticeUrls = winCNoticeUrls;
    }

    public List<String> getWinCloseUrls() {
        return winCloseUrls;
    }

    public void setWinCloseUrls(List<String> winCloseUrls) {
        this.winCloseUrls = winCloseUrls;
    }

    public List<String> getWinDownloadUrls() {
        return winDownloadUrls;
    }

    public void setWinDownloadUrls(List<String> winDownloadUrls) {
        this.winDownloadUrls = winDownloadUrls;
    }

    public List<String> getWinDownloadEndUrls() {
        return winDownloadEndUrls;
    }

    public void setWinDownloadEndUrls(List<String> winDownloadEndUrls) {
        this.winDownloadEndUrls = winDownloadEndUrls;
    }

    public List<String> getWinInstallUrls() {
        return winInstallUrls;
    }

    public void setWinInstallUrls(List<String> winInstallUrls) {
        this.winInstallUrls = winInstallUrls;
    }

    public List<String> getWinInstallEndUrls() {
        return winInstallEndUrls;
    }

    public void setWinInstallEndUrls(List<String> winInstallEndUrls) {
        this.winInstallEndUrls = winInstallEndUrls;
    }

    public List<String> getWinInstallOpenUrls() {
        return winInstallOpenUrls;
    }

    public void setWinInstallOpenUrls(List<String> winInstallOpenUrls) {
        this.winInstallOpenUrls = winInstallOpenUrls;
    }

    public List<String> getWinActiveUrls() {
        return winActiveUrls;
    }

    public void setWinActiveUrls(List<String> winActiveUrls) {
        this.winActiveUrls = winActiveUrls;
    }

    public List<String> getWinCompleteUrls() {
        return winCompleteUrls;
    }

    public void setWinCompleteUrls(List<String> winCompleteUrls) {
        this.winCompleteUrls = winCompleteUrls;
    }
}
