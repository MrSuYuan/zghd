package com.zghd.service;

import com.zghd.entity.ZGHDRequest.App;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 广点通
 */
@Service(value="gdtService")
public class GDTService {

    /**
     * 向广点通后台发请求
     */
    public GetAdsResp GDTSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar = null;
        String data = formatData(gaReq, gu);
        String url = "http://mi.gdt.qq.com/api/v3";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("X-Forwarded-For","47.93.46.24");
        httpPost.addHeader("User-Agent",gaReq.getDevice().getUa());
        httpPost.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(data,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse resp = httpClient.execute(httpPost);
        HttpEntity result = resp.getEntity();
        String str = EntityUtils.toString(result, "utf-8");

        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu) throws Exception{

        StringBuffer sb = new StringBuffer();
        sb.append("?api_version=3.2");
        sb.append("&support_https=1");
        sb.append("&pos="+gaReq.getApp().getAppVersion());
        sb.append("&media="+gaReq.getApp().getAppVersion());
        sb.append("&device="+gaReq.getApp().getAppVersion());
        sb.append("&network="+gaReq.getApp().getAppVersion());
        sb.append("&geo="+gaReq.getApp().getAppVersion());
        return null;
    }

    public static void main(String[] args) {
        App app = new App();
        app.setAppId("id");
        app.setAppName("名字");
        app.setAppPackage("com.zghd");
        app.setAppVersion("1.1.1");
        System.out.println(URLEncoder.encode(JSONObject.fromObject(app).toString()));


    }


}
