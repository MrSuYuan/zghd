package com.zghd.entity.PengTai.response;

import java.util.List;

public class PTResponse {
    private String id;
    private String cur;
    private List<Seatbid> seatbid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public List<Seatbid> getSeatbid() {
        return seatbid;
    }

    public void setSeatbid(List<Seatbid> seatbid) {
        this.seatbid = seatbid;
    }
}
