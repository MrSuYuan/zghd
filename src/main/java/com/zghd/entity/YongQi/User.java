package com.zghd.entity.YongQi;

/**
 * 用户信息
 */
public class User {

    /**
     * 用户唯一标识
     */
    private String id;
    /**
     * 出生年,4位数字
     */
    private String yob;
    /**
     * 年龄范围,最小年龄
     */
    private int age_low;
    /**
     * 年龄范围,最大年龄
     */
    private int age_hight;
    /**
     * 性别
     * M    F    O    Null
     */
    private String gender;
    /**
     * 用户兴趣爱好 以,分隔
     */
    private String keywords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public int getAge_low() {
        return age_low;
    }

    public void setAge_low(int age_low) {
        this.age_low = age_low;
    }

    public int getAge_hight() {
        return age_hight;
    }

    public void setAge_hight(int age_hight) {
        this.age_hight = age_hight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
