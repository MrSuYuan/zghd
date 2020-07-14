package com.zghd.service;

import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 豆盟
 */
@Service(value="dmService")
public class DMService {

    /**
     * 豆盟请求
     */
    public GetAdsResp DMSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        String url = "https://openapi.clotfun.online/openApi/advertisementAccess";
        //参数转换
        String data = formatData(gu);
        String reqParams = url+"?"+data;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(reqParams);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        entity.writeTo(buf);
        String backData = new String(buf.toByteArray(),"utf-8");
        return formatBackData(backData, gaReq, gu);
    }

    /**
     * 格式化前端传来的参数
     */
    public String formatData(GetUpstream gu){
        StringBuffer sb = new StringBuffer();
        String accountId = "2a33e7916636c2f32b8c7a341a15330a";
        String secret = "6636c2f32b8c7a34";
        //String adSpaceKey = "ea9de32b450183ff4dc925f387763b4f";
        String adSpaceKey = gu.getUpstreamId();
        String sign = MD5.md5(accountId + adSpaceKey + secret);
        sb.append("accountId="+accountId);
        sb.append("&secret="+secret);
        sb.append("&adSpaceKey="+adSpaceKey);
        sb.append("&sign="+sign);
        return sb.toString();
    }

    /**
     * 格式化返回数据
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu) {
        GetAdsResp gar = new GetAdsResp();
        JSONObject data = JSONObject.fromObject(backData);
        //requestId
        gar.setRequestId(gaReq.getRequestId());
        String code = data.getString("code");
        if ("200".equals(code)){
            //广告主体
            Ad ya = new Ad();

            //内容
            MaterialMeta ym = new MaterialMeta();
            JSONObject ad = JSONObject.fromObject(data.getJSONObject("result"));
            //图片
            String imageUrl = ad.getString("imageUrl");
            List<String> image = new ArrayList<>();
            image.add(imageUrl);
            ym.setImageUrl(image);
            //跳转链接
            ym.setClickUrl(ad.getString("adUrl"));
            ym.setCreativeType(2);
            ym.setInteractionType(1);
            ym.setDeepLink(false);

            //曝光展现
            List<String> nL = new ArrayList<>();
            String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-22-3");
            nL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
            ym.setWinNoticeUrls(nL);
            //点击
            List<String> cL = new ArrayList<>();
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"-"+gaReq.getSlot().getSlotId()+"-"+gu.getUpstreamId()+"-22-4");
            cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(cL);

            //综合封装返回
            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);
            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);

            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }

        return gar;
    }

}
