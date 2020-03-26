package com.zghd.news;

import com.zghd.entity.ZGHDNewsResponse.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 瑞狮新闻
 */
@Service(value="rsNewsService")
public class RSNewsService {

    /**
     * 标题请求
     */
    public AdNewsTitleResp titleSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        AdNewsTitleResp atr = new AdNewsTitleResp();

        //入参参数
        StringBuffer sb = new StringBuffer();
        sb.append("&media="+URLEncoder.encode(gu.getUpstreamAppId(),"utf-8"));
        sb.append("&submedia="+URLEncoder.encode(gu.getUpstreamId(),"utf-8"));
        sb.append("&appname="+URLEncoder.encode(gaReq.getApp().getAppName(),"utf-8"));
        sb.append("&pkgname="+URLEncoder.encode(gaReq.getApp().getAppPackage(),"utf-8"));
        sb.append("&app_version="+URLEncoder.encode(gaReq.getApp().getAppVersion(),"utf-8"));
        sb.append("&os="+URLEncoder.encode(gaReq.getDevice().getOsType()+"","utf-8"));
        sb.append("&osv="+URLEncoder.encode(gaReq.getDevice().getOsVersion(),"utf-8"));
        sb.append("&idfa="+URLEncoder.encode(gaReq.getDevice().getIdfa(),"utf-8"));
        sb.append("&imei="+URLEncoder.encode(gaReq.getDevice().getImei(),"utf-8"));
        sb.append("&anid="+URLEncoder.encode(gaReq.getDevice().getAndroidId(),"utf-8"));
        sb.append("&mac="+URLEncoder.encode(gaReq.getDevice().getMac(),"utf-8"));
        sb.append("&make="+URLEncoder.encode(gaReq.getDevice().getVendor(),"utf-8"));
        sb.append("&model="+URLEncoder.encode(gaReq.getDevice().getModel(),"utf-8"));
        sb.append("&sw="+URLEncoder.encode(gaReq.getDevice().getScreenWidth()+"","utf-8"));
        sb.append("&sh="+URLEncoder.encode(gaReq.getDevice().getScreenHeight()+"","utf-8"));
        sb.append("&devicetype="+URLEncoder.encode(gaReq.getDevice().getDeviceType()+"","utf-8"));
        sb.append("&ip="+URLEncoder.encode(gaReq.getNetwork().getIp(),"utf-8"));
        sb.append("&ua="+URLEncoder.encode(gaReq.getDevice().getUa(),"utf-8"));
        sb.append("&conn="+URLEncoder.encode("0","utf-8"));
        sb.append("&carrier="+URLEncoder.encode("0","utf-8"));
        sb.append("&lon="+URLEncoder.encode(gaReq.getNetwork().getLon()+"","utf-8"));
        sb.append("&lat="+URLEncoder.encode(gaReq.getNetwork().getLat()+"","utf-8"));

        String url = "http://api.viaweb.cn/bd/news/cat_list?"+sb.toString();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", gaReq.getDevice().getUa());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200){
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity,"utf-8");
                JSONArray array = JSONArray.fromObject(str);
                List<Title> list = new ArrayList<>();
                for (int i = 0; i < array.size(); i++){
                    JSONObject json = JSONObject.fromObject(array.get(i));
                    Title t = new Title();
                    t.setTitle(json.getString("title"));
                    t.setUrl(json.getString("url"));
                    list.add(t);
                }
                atr.setTitles(list);
                atr.setErrorCode("200");
                atr.setMsg("SUCCESS");
            }else{
                atr.setErrorCode("500");
                atr.setMsg("SERVER_ERROR");
            }
        } catch (IOException e) {
            e.printStackTrace();
            atr.setErrorCode("500");
            atr.setMsg("SERVER_ERROR");
        } finally {
            response.close();
        }
        return atr;
    }

    /**
     * 内容请求
     */
    public AdNewsResp newsList(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        AdNewsResp anr = new AdNewsResp();

        //入参参数
        StringBuffer sb = new StringBuffer();
        sb.append("&media="+URLEncoder.encode(gu.getUpstreamAppId(),"utf-8"));
        sb.append("&submedia="+URLEncoder.encode(gu.getUpstreamId(),"utf-8"));
        sb.append("&appname="+URLEncoder.encode(gaReq.getApp().getAppName(),"utf-8"));
        sb.append("&pkgname="+URLEncoder.encode(gaReq.getApp().getAppPackage(),"utf-8"));
        sb.append("&app_version="+URLEncoder.encode(gaReq.getApp().getAppVersion(),"utf-8"));
        sb.append("&os="+URLEncoder.encode(gaReq.getDevice().getOsType()+"","utf-8"));
        sb.append("&osv="+URLEncoder.encode(gaReq.getDevice().getOsVersion(),"utf-8"));
        sb.append("&idfa="+URLEncoder.encode(gaReq.getDevice().getIdfa(),"utf-8"));
        sb.append("&imei="+URLEncoder.encode(gaReq.getDevice().getImei(),"utf-8"));
        sb.append("&anid="+URLEncoder.encode(gaReq.getDevice().getAndroidId(),"utf-8"));
        sb.append("&mac="+URLEncoder.encode(gaReq.getDevice().getMac(),"utf-8"));
        sb.append("&make="+URLEncoder.encode(gaReq.getDevice().getVendor(),"utf-8"));
        sb.append("&model="+URLEncoder.encode(gaReq.getDevice().getModel(),"utf-8"));
        sb.append("&sw="+URLEncoder.encode(gaReq.getDevice().getScreenWidth()+"","utf-8"));
        sb.append("&sh="+URLEncoder.encode(gaReq.getDevice().getScreenHeight()+"","utf-8"));
        sb.append("&devicetype="+URLEncoder.encode(gaReq.getDevice().getDeviceType()+"","utf-8"));
        sb.append("&ip="+URLEncoder.encode(gaReq.getNetwork().getIp(),"utf-8"));
        sb.append("&ua="+URLEncoder.encode(gaReq.getDevice().getUa(),"utf-8"));
        sb.append("&conn="+URLEncoder.encode("0","utf-8"));
        sb.append("&carrier="+URLEncoder.encode("0","utf-8"));
        sb.append("&lon="+URLEncoder.encode(gaReq.getNetwork().getLon()+"","utf-8"));
        sb.append("&lat="+URLEncoder.encode(gaReq.getNetwork().getLat()+"","utf-8"));
        sb.append("&page="+URLEncoder.encode(gaReq.getNews().getPage()+"","utf-8"));
        sb.append("&category="+URLEncoder.encode(gaReq.getNews().getTitle(),"utf-8"));

        String url = "http://api.viaweb.cn/bd/news/list?"+sb.toString();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", gaReq.getDevice().getUa());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200){
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity,"utf-8");
                //封装出参参数
                anr = formatBackData(str);
                anr.setErrorCode("200");
                anr.setMsg("SUCCESS");
            }else{
                anr.setErrorCode("500");
                anr.setMsg("SERVER_ERROR");
            }
        } catch (IOException e) {
            e.printStackTrace();
            anr.setErrorCode("500");
            anr.setMsg("SERVER_ERROR");
        } finally {
            response.close();
        }
        return anr;
    }

    private static AdNewsResp formatBackData(String str){
        AdNewsResp anr = new AdNewsResp();
        List<Material> materialList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(str);
        for (int i = 0; i < jsonArray.size(); i++){
            Material material = new Material();
            JSONObject json = JSONObject.fromObject(jsonArray.get(i));
            boolean materialType = json.getBoolean("is_ad");
            //广告
            if (materialType){
                material.setMaterialType(2);
                JSONObject adJson = json.getJSONObject("ad");
                Ad ad = new Ad();
                ad.setId(adJson.getString("cid"));
                JSONObject nativeAd = adJson.getJSONObject("nativead");
                ad.setTitle(nativeAd.getString("title"));
                ad.setDesc(nativeAd.getString("desc"));
                //大图
                JSONArray img = nativeAd.getJSONArray("img");
                List<Image> imgList = new ArrayList<>();
                for (int j=0;j<img.size();j++){
                    Image im = new Image();
                    JSONObject image = JSONObject.fromObject(img.get(j));
                    if (image.has("url")){
                        im.setImageUrl(image.getString("url"));
                    }
                    if (image.has("w")){
                        im.setImageWidth(image.getInt("w"));
                    }
                    if (image.has("h")){
                        im.setImageHeight(image.getInt("h"));
                    }
                    imgList.add(im);
                }
                ad.setImages(imgList);
                //小图
                JSONArray icon = nativeAd.getJSONArray("icon");
                List<Image> iconList = new ArrayList<>();
                for (int j=0;j<img.size();j++){
                    Image im = new Image();
                    JSONObject image = JSONObject.fromObject(icon.get(j));
                    if (image.has("url")){
                        im.setImageUrl(image.getString("url"));
                    }
                    if (image.has("w")){
                        im.setImageWidth(image.getInt("w"));
                    }
                    if (image.has("h")){
                        im.setImageHeight(image.getInt("h"));
                    }
                    iconList.add(im);
                }
                ad.setIcons(iconList);
                ad.setLandPage(nativeAd.getString("ldp"));
                ad.setDeeplink(nativeAd.getString("deeplink"));
                //1打开网页 2下载
                if(adJson.has("interact_type")){
                    if (adJson.getInt("interact_type") == 0){
                        ad.setInteractionType(1);
                    }else{
                        ad.setInteractionType(2);
                    }
                }else{
                    ad.setInteractionType(1);
                }
                //上报监控
                ad.setShowUrl(adJson.getJSONArray("imp_tracking"));
                ad.setClickUrl(adJson.getJSONArray("clk_tracking"));
                if(adJson.has("ds_t")){
                    ad.setDownLoadUrl(adJson.getJSONArray("ds_t"));
                }
                if(adJson.has("dc_t")){
                    ad.setDownLoadEndUrl(adJson.getJSONArray("dc_t"));
                }
                if(adJson.has("is_t")){
                    ad.setInstallUrl(adJson.getJSONArray("is_t"));
                }
                if(adJson.has("ic_t")){
                    ad.setInstallEndUrl(adJson.getJSONArray("ic_t"));
                }
                if(adJson.has("op_t")){
                    ad.setOpenUrl(adJson.getJSONArray("op_t"));
                }
                material.setAd(ad);

            //新闻
            }else{
                material.setMaterialType(1);
                News news = new News();
                news.setId(json.getString("id"));
                news.setTitle(json.getString("title"));
                news.setSource(json.getString("source"));
                news.setUpdateTime(json.getString("update_time"));
                news.setTimestamp(json.getLong("timestamp"));
                boolean isVideo = json.getBoolean("is_video");
                if (isVideo){
                    news.setIsVideo(1);
                }else{
                    news.setIsVideo(2);
                }
                JSONArray images = json.getJSONArray("images");
                List<Image> imageList = new ArrayList<>();
                for (int j=0;j<images.size();j++){
                    Image im = new Image();
                    JSONObject image = JSONObject.fromObject(images.get(j));
                    if (image.has("url")){
                        im.setImageUrl(image.getString("url"));
                    }
                    if (image.has("w")){
                        im.setImageWidth(image.getInt("w"));
                    }
                    if (image.has("h")){
                        im.setImageHeight(image.getInt("h"));
                    }
                    imageList.add(im);
                }
                if (json.has("clk_url")){
                    news.setUrl(json.getString("clk_url"));
                }
                if (json.has("cat")){
                    news.setCat(json.getString("cat"));
                }
                if (json.has("imp_tracking")){
                    news.setShowUrl(json.getJSONArray("imp_tracking"));
                }
                material.setNews(news);

            }
            materialList.add(material);
        }
        anr.setMaterials(materialList);
        return anr;
    }
}
