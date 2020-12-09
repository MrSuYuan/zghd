package com.zghd.service;

import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.OPPO.*;
import com.zghd.entity.VIVO.request.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * vivo广告
 */
@Service(value="vivoService")
public class VIVOService {

    /**
     * 向vivo后台发请求
     */
    public GetAdsResp VIVOSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;
        String data = formatData(gaReq, gu);
        String url = "https://uapi-ads.vivo.com.cn/u/api/v1/reqAd";
        String str = TestConnectionPool.post(url, data,null);
        System.out.println(str);
        gar = formatBackData(str,gaReq,gu);
        return gar;
    }

    /**
     * 解析前端参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){

        VIVORequest request = new VIVORequest();
        request.setApiVersion("1.0");
        request.setSysVersion("unknow");
        //这个也需要确认
        request.setAppstoreVersion(1);

        Media media = new Media();
        media.setMediaId(gu.getUpstreamAppId());
        media.setAppPackage(gu.getUpstreamPackageName());
        String v = gaReq.getApp().getAppVersion().split(".")[0];
        media.setAppVersion(Integer.valueOf(v));
        request.setMedia(media);

        Postion postion = new Postion();
        postion.setPositionId(gu.getUpstreamId());
        int adType = gaReq.getSlot().getAdtype();
        if (adType == 2){
            postion.setDisplayType(2);
        }else if (adType == 3){
            postion.setDisplayType(4);
        }else if (adType == 4){
            postion.setDisplayType(5);
        }else if (adType == 5){
            postion.setDisplayType(9);
        }
        postion.setWidth(gaReq.getSlot().getSlotwidth());
        postion.setHeight(gaReq.getSlot().getSlotheight());
        request.setPostion(postion);

        Device device = new Device();
        device.setMac(gaReq.getDevice().getMac());
        device.setImei(gaReq.getDevice().getImei());
        device.setOaid(gaReq.getDevice().getOaid());
        device.setAndroidId(gaReq.getDevice().getAndroidId());
        device.setAn(gaReq.getDevice().getOsVersion());
        int osv = Integer.valueOf(gaReq.getDevice().getOsVersion().split(".")[0]);
        if (osv == 4){
            device.setAv(20);
        }else if (osv == 5){
            device.setAv(22);
        }else if (osv == 6){
            device.setAv(23);
        }else if (osv == 7){
            device.setAv(25);
        }else if (osv == 8){
            device.setAv(27);
        }else if (osv == 9){
            device.setAv(28);
        }else if (osv == 10){
            device.setAv(29);
        }else{
            device.setAv(30);
        }
        device.setUa(gaReq.getDevice().getUa());
        device.setIp(gaReq.getNetwork().getIp());
        device.setMake(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        device.setLanguage("zh-CN");
        device.setScreenWidth(gaReq.getDevice().getScreenWidth());
        device.setScreenHeight(gaReq.getDevice().getScreenHeight());
        device.setPpi(gaReq.getDevice().getPpi());
        //这个得问问
        device.setElapseTime(0);
        request.setDevice(device);

        Network network = new Network();
        network.setConnectType(gaReq.getNetwork().getConnectionType());
        int ca = gaReq.getNetwork().getOperatorType();
        if (ca == 2){
            network.setCarrier(3);
        }else if (ca == 3){
            network.setCarrier(2);
        }else{
            network.setCarrier(ca);
        }
        request.setNetwork(network);

        Geo geo = new Geo();
        geo.setLat(gaReq.getNetwork().getLat());
        geo.setLng(gaReq.getNetwork().getLon());
        request.setGeo(geo);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu) {

        return null;
    }

}
