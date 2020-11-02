package com.zghd.entity.PengTai.request;

public class Imp {
    private String id;
    private Native nativeN;
    private String tagid;
    private String displaymanager;
    private ImpExt ext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Native getNativeN() {
        return nativeN;
    }

    public void setNativeN(Native nativeN) {
        this.nativeN = nativeN;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getDisplaymanager() {
        return displaymanager;
    }

    public void setDisplaymanager(String displaymanager) {
        this.displaymanager = displaymanager;
    }

    public ImpExt getExt() {
        return ext;
    }

    public void setExt(ImpExt ext) {
        this.ext = ext;
    }
}
