package com.zghd.entity.InMoBiDown.request;

public class ReqAssets {

    private int id;
    private ReqImg img;
    private int required;
    private ReqData data;
    private ReqTitle title;

    public ReqData getData() {
        return data;
    }

    public void setData(ReqData data) {
        this.data = data;
    }

    public ReqTitle getTitle() {
        return title;
    }

    public void setTitle(ReqTitle title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReqImg getImg() {
        return img;
    }

    public void setImg(ReqImg img) {
        this.img = img;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }
}
