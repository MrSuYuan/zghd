package com.zghd.entity.InMoBiDown.request;

public class ReqExt {

    private boolean dpl;
    private boolean dplRecommended;
    private int supportDplType;

    public boolean isDpl() {
        return dpl;
    }

    public void setDpl(boolean dpl) {
        this.dpl = dpl;
    }

    public boolean isDplRecommended() {
        return dplRecommended;
    }

    public void setDplRecommended(boolean dplRecommended) {
        this.dplRecommended = dplRecommended;
    }

    public int getSupportDplType() {
        return supportDplType;
    }

    public void setSupportDplType(int supportDplType) {
        this.supportDplType = supportDplType;
    }
}
