package com.zghd.entity.platform;

public class GetUpstream {

    private String appId;
    private String upstreamId;
    private String upstreamAppId;
    private String upstreamAppName;
    private String upstreamPackageName;
    private int upstreamWidth;
    private int upstreamHeight;
    private int upstreamType;
    private int probability;
    private int flowStatus;
    private int requestStatus;
    private int responseStatus;
    private int vendorStatus;
    private String vendorDivision;
    //测试id的时候返回的素材内容
    private String content;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(int flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getUpstreamAppName() {
        return upstreamAppName;
    }

    public void setUpstreamAppName(String upstreamAppName) {
        this.upstreamAppName = upstreamAppName;
    }

    public int getUpstreamWidth() {
        return upstreamWidth;
    }

    public void setUpstreamWidth(int upstreamWidth) {
        this.upstreamWidth = upstreamWidth;
    }

    public int getUpstreamHeight() {
        return upstreamHeight;
    }

    public void setUpstreamHeight(int upstreamHeight) {
        this.upstreamHeight = upstreamHeight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpstreamPackageName() {
        return upstreamPackageName;
    }

    public void setUpstreamPackageName(String upstreamPackageName) {
        this.upstreamPackageName = upstreamPackageName;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public String getUpstreamAppId() {
        return upstreamAppId;
    }

    public void setUpstreamAppId(String upstreamAppId) {
        this.upstreamAppId = upstreamAppId;
    }

    public String getUpstreamId() {
        return upstreamId;
    }

    public void setUpstreamId(String upstreamId) {
        this.upstreamId = upstreamId;
    }

    public int getUpstreamType() {
        return upstreamType;
    }

    public void setUpstreamType(int upstreamType) {
        this.upstreamType = upstreamType;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public int getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(int vendorStatus) {
        this.vendorStatus = vendorStatus;
    }

    public String getVendorDivision() {
        return vendorDivision;
    }

    public void setVendorDivision(String vendorDivision) {
        this.vendorDivision = vendorDivision;
    }
}
