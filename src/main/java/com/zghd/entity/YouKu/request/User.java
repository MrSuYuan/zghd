package com.zghd.entity.YouKu.request;

/**
 * 用户id
 */
public class User {

    //用户ID。在PC流量中，这个ID是youku.com的cookie中的用户ID字段；在移动流量中，该字段具体取值方式，参见附录UserID取值规则
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
