package com.zghd.entity.HongYi;

/**
 * 广告位信息
 */
public class Adslot {

    /**
     * 广告位id
     */
    private String id;
    /**
     * 广告位类型
     * 1.NATIVE(信息流 ) 2.BANNER(横幅 )
     */
    private int type;
    /**
     * 广告位的高度
     */
    private int height;
    /**
     * 广告位的宽度
     */
    private int width;
    /**
     * 第几页
     */
    private int page_num;
    /**
     * 标记符
     */
    private String book_id;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
