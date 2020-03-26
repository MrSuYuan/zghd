package com.zghd.entity.BaiDu;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @author tc.wang
 * @date 18/10/23
 */
public final class BuildLogListUtils {
    private static final Logger logger = Logger.getLogger(BuildLogListUtils.class);
    private static final EncryptUtil DES = new EncryptUtil("", "utf-8");
    private static final String SHOWLOG = "showlog";
    private static final String CLICKLOG = "clicklog";
    private static final String SD = "sd";
    private static final String FD = "fd";
    private static final String SI = "si";
    private static final String FI = "fi";
    private static final String SKIPAD = "skipad";
    private static final String CAD_LOG = "{\"source\":\"%s\", \"title\":\"%s\", \"url\":\"%s\"}";
    private BuildLogListUtils(){}

    /**
     * 曝光上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildShowLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SHOWLOG,  req);
    }

    /**
     * 曝光上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildShowLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SHOWLOG,  req);
    }

    /**
     * 曝光上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildShowLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, SHOWLOG, req);
    }

    /**
     * 点击上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildClickLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, CLICKLOG, req);
    }

    /**
     * 点击上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildClickLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, CLICKLOG,  req);
    }

    /**
     * 点击上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildClickLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, CLICKLOG,  req);
    }

    /**
     * 开始下载上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSdLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SD,  req);
    }

    /**
     * 开始下载上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSdLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SD,  req);
    }

    /**
     * 开始下载上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSdLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, SD,  req);
    }

    /**
     * 下载完成上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildFdLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, FD,  req);
    }

    /**
     * 下载完成上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildFdLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, FD,  req);
    }

    /**
     * 下载完成上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildFdLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, FD,  req);
    }

    /**
     * 开始安装上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSiLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SI,  req);
    }

    /**
     * 开始安装上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSiLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SI,  req);
    }

    /**
     * 开始安装上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSiLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, SI,  req);
    }

    /**
     * 安装完成上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildFiLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, FI,  req);
    }

    /**
     * 安装完成上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildFiLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, FI,  req);
    }

    /**
     * 安装完成上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildFiLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, FI,  req);
    }

    /**
     * 跳过广告上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSkipAdLinks(JSONArray links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SKIPAD,  req);
    }

    /**
     * 跳过广告上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSkipAdLinks(String[] links, String slotId, String type, GetAdsReq req){
        return common(toList(links), slotId, type, SKIPAD,  req);
    }

    /**
     * 跳过广告上报地址
     * @param links 上游上报地址
     * @param slotId 代码位ID
     * @param type 上游名称
     * @return
     */
    public static List<String> buildSkipAdLinks(List<String> links, String slotId, String type, GetAdsReq req){
        return common(links, slotId, type, SKIPAD,  req);
    }

    /**
     * 公共
     * @param links 源地址
     * @param slotId 代码为ID
     * @param type 上游名称
     * @param path 上报 action url
     * @return
     */
    private static List<String> common(List<String> links, String uuid, String type, String path,  GetAdsReq req){
    	return links;
//        List<String> urlList = Lists.newArrayList();
//        String link = null;
//        if (CollectionUtils.isNotEmpty(links)){
//            for (int i = 0; i < links.size(); i++) {
//                link = links.get(i);
//                if (StringUtils.isEmpty(link)) continue;
//                urlList.add(link);
//            }
//        }
//        
//        
//        StringBuilder url = new StringBuilder(GlobalToken.url);
//        url.append("ad/");
//        url.append(path);
//        url.append("?c=");
//        url.append(uuid);
    	
    	
    	
//        try {
//            //url.append(DES.encode(paramsBuilder.toString()));
//            urlList.add(url.toString());
//        } catch (Exception e) {
//            logger.error(String.format(type + " "+path+" encode error %s",uuid+"&type="+type+"&time="+ DateUtils.getYYMMDD(new Date())), e);
//        }
//        return urlList;
    }

    /**
     * 广告主广告点击
     * @param links 点击上报地址
     * @param title 广告标题
     * @param adUrl 广告点击url
     * @param source 广告源
     * @return
     */
    public static List<String> buildCad(List<String> links, GetAdsReq req, String adUrl, String source){
    	
        List<String> urlList = Lists.newArrayList();
        String link = null;
        if (CollectionUtils.isNotEmpty(links)){
            for (int i = 0; i < links.size(); i++) {
                link = links.get(i);
                if (StringUtils.isEmpty(link)) continue;
                urlList.add(link);
            }
        }
        StringBuilder url = new StringBuilder(GlobalToken.url);
        url.append("ad/cad");
        url.append("?c=");
        try {
             url.append(adUrl);
           // url.append(DES.encode(String.format(CAD_LOG, source, title, adUrl)));
             urlList.add(url.toString());
        } catch (Exception e) {
            logger.error(String.format(source + " cad encode error %s","source="+source+"&adUrl="+ adUrl), e);
        }
        return links;
    }

    private static List<String> toList(String[] links){
        List<String> urlList = Lists.newArrayList();
        if (links != null && links.length > 0){
            int length = links.length;
            for (int i = 0; i < length; i++) {
                String link = links[i];
                if (StringUtils.isEmpty(link)) continue;
                urlList.add(link);
            }
        }
        return urlList;
    }

    public static List<String> toList(JSONArray links){
        List<String> urlList = Lists.newArrayList();
        if (links != null && !links.isEmpty()){
            for (int i = 0; i < links.size(); i++) {
                String link = links.getString(i);
                if (StringUtils.isEmpty(link)) continue;
                urlList.add(link);
            }
        }
        return urlList;
    }

    private static Map<String,Object> buildParams(GetAdsReq req){
        if (req == null) return null;
        //YdtDevice ydtDevice = req.getDevice();
        //YdtApp ydtApp = req.getApp();
        Map<String, Object> params = Maps.newHashMap();
//        if (ydtApp != null){
//            params.put("appId", ydtApp.getAppId());
//        } else {
//            params.put("appId", null);
//        }
//
//        if (ydtDevice != null){
//            params.put("androidId", ydtDevice.getAndroidId());
//            params.put("idfa", ydtDevice.getIdfa());
//            params.put("imei", ydtDevice.getImei());
//            params.put("mac", ydtDevice.getMac());
//            params.put("androidId", ydtDevice.getAndroidId());
//            params.put("model", ydtDevice.getModel());
//            params.put("vendor", ydtDevice.getVendor());
//            params.put("osVersion", ydtDevice.getOsVersion());
//            //params.put("ua", ydtDevice.getUa());
//            params.put("brand", ydtDevice.getBrand());
//        }

        return params;
    }
}
