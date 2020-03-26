package com.zghd.entity.YuLiang;

import java.util.List;

/**
 * 广告
 */
public class Imp {

    /**
     * 推广位曝光ID
     * @request true
     */
    private int impJd;
    /**
     * 推广位类型标识
     * @request true
     */
    private String slot_id;
    /**
     * 交互类型
     * @request true
     */
    private List<String> interaction_types;

    public int getImpJd() {
        return impJd;
    }

    public void setImpJd(int impJd) {
        this.impJd = impJd;
    }

    public String getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(String slot_id) {
        this.slot_id = slot_id;
    }

    public List<String> getInteraction_types() {
        return interaction_types;
    }

    public void setInteraction_types(List<String> interaction_types) {
        this.interaction_types = interaction_types;
    }

}
