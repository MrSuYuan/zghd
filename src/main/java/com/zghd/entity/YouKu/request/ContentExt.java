package com.zghd.entity.YouKu.request;

/**
 * 视频的频道信息
 */
public class ContentExt {

    //视频的频道ID，例如"a"。具体的频道列表，参见字典文件Youku ADX频道列表
    private String channel;
    //二级频道ID。具体的二级频道列表信息，参见字典文件Youku ADX二级频道列表
    private String cs;
    //视频上传者id，“优酷”里上传视频的用户ID
    private String usr;
    //节目id
    private String s;
    //视频id
    private String vid;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
