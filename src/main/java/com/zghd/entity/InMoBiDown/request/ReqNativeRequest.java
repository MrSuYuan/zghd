package com.zghd.entity.InMoBiDown.request;

import java.util.List;

public class ReqNativeRequest {

    private List<ReqAssets> assets;
    private int layout;
    private int plcmtcnt;
    private int seq;

    public List<ReqAssets> getAssets() {
        return assets;
    }

    public void setAssets(List<ReqAssets> assets) {
        this.assets = assets;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getPlcmtcnt() {
        return plcmtcnt;
    }

    public void setPlcmtcnt(int plcmtcnt) {
        this.plcmtcnt = plcmtcnt;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
