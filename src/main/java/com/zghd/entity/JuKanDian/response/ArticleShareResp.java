package com.zghd.entity.JuKanDian.response;

/**
 * 文章分享返回
 */
public class ArticleShareResp {

    private String errorCode;
    //状态码，成功或失败
    private int ret_code;
    //分享内容
    private String shareContent;
    //分享图片URL
    private String shareImageUrl;
    //分享目标 timegorup:好友群 timeline:朋友圈
    private String shareTarget;
    //分享标题
    private String shareTitle;
    //链接：0，图片:1，文字:2 视频:3 文章列表返回的 itemType
    private String shareType;
    //分享链接
    private String shareUrl;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public String getShareTarget() {
        return shareTarget;
    }

    public void setShareTarget(String shareTarget) {
        this.shareTarget = shareTarget;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }
}
