package com.zghd.entity.WanKa;

/**
 * 万咖智星请求参数
 */
public class WanKa {

    /**
     * 广告请求字段，值为json格式
     * @required true
     */
    private String reqjson;
    /**
     * 渠道ID
     * @required true
     */
    private String channel_id;
    /**
     * 请求密钥，用于用户验证，生成规则详见获取广告的签名
     * @required true
     */
    private String signature;
    /**
     * 时间戳(手机当前时间的时间戳)，生成广告签名时使用此时间戳
     * @required true
     */
    private Long timestamp;

    public String getReqjson() {
        return reqjson;
    }

    public void setReqjson(String reqjson) {
        this.reqjson = reqjson;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
