package com.zghd.entity.platform;

/**
 * 调度
 */
public class AppAssign {

    /**
     * id
     */
    private Long id;
    /**
     * id
     */
    private int upstreamType;
    /**
     * id
     */
    private int probability;
    /**
     * id
     */
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUpstreamType() {
        return upstreamType;
    }

    public void setUpstreamType(int upstreamType) {
        this.upstreamType = upstreamType;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
