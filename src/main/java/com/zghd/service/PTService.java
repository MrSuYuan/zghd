package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.http.TestConnectionPool;
import com.util.md5.JiaMi;
import com.util.md5.MD5;
import com.zghd.entity.PengTai.request.*;
import com.zghd.entity.PengTai.response.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 鹏泰广告
 */
@Service(value="ptService")
public class PTService {

    /**
     * 向三星鹏泰发送广告请求
     * CPD:  三星 763   非三星 758
     * CPM: 734
     */
    public GetAdsResp PTSend(GetAdsReq gaReq, GetUpstream gu)throws Exception{

        //测试1096
        //String url = "http://stg-adx.ad-survey.com/api/v0?slot="+gu.getUpstreamId();
        String url;
        //正式
        String brand = gaReq.getDevice().getBrand().toLowerCase();
        //cpm的
        if ("734".equals(gu.getUpstreamId())){
            url = "http://adx.ad-survey.com/api/v0?slot=734";
        }
        //cpd的
        else{
            int result = brand.indexOf("samsung");
            if(result == -1){
                url = "http://adx.ad-survey.com/api/v0?slot=758";
            }else{
                url = "http://adx.ad-survey.com/api/v0?slot=763";
            }
        }

        String data = formatData(gaReq, gu).replaceAll("nativeN","native");
        //data = "{\"id\":\"0b4db478-79d6-4a8e-97b4-1af31d700fbf\",\"imp\":[{\"id\":\"1\",\"native\":{\"request\":\"{\\\"assets\\\":[{\\\"id\\\":1,\\\"required\\\":1,\\\"title\\\":{\\\"len\\\":25}},{\\\"id\\\":2,\\\"required\\\":1,\\\"img\\\":{\\\"type\\\":3,\\\"w\\\":256,\\\"h\\\":256}}]}\",\"ver\":\"1.0\"},\"tagid\":\"7bd9fbf623f8499faeajkoe37529723f7\",\"displaymanager\":\"union\",\"ext\":{\"pkgs\":\"com.psyone.brainmusic,com.ifeng.news2,com.youku.phone,com.ss.android.ugc.aweme\"}}],\"app\":{\"bundle\":\"unionmedia\",\"ver\":\"1.2\",\"name\":\"Game Launcher\",\"domain\":\"www.samsung.com\"},\"device\":{\"ip\":\"14.142.149.66\",\"ipv6\":\"2001:0db8:85a3:0000:0000:8a2e:0370:7334\",\"devicetype\":1,\"make\":\"xiaomi\",\"model\":\"S8\",\"os\":\"Android\",\"osv\":\"6.0\",\"connectiontype\":1,\"ext\":{\"imei\":\"YWRhc2RmYUhLRklFV0ZIRUlXUkZFV1JSVw==\",\"db\":\"xiaomi\",\"mcc\":\"460\",\"mnc\":\"01\",\"csc\":\"CHC\",\"sdkVerAndroid\":\"22\",\"abiType\":\"64\"},\"ifa\":\"this is an IFA.\",\"ua\":\"Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-G9650 Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/9.4 Chrome/67.0.3396.87 Mobile Safari/537.36\"},\"user\":{\"id\":\"03166f5c-2750-4a37-af03-cfc1c021dc19\",\"yob\":1983,\"gender\":\"M\"},\"cur\":[\"CNY\"]}\n";
        String backData = TestConnectionPool.post(url, data,null);
        GetAdsResp resp = formatBackData(backData, gaReq, gu);
        return resp;
    }

    /**
     * 格式化入参参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){
        PTRequest request = new PTRequest();

        request.setId(gaReq.getRequestId());

        List<String> cur = new ArrayList<>();
        cur.add("CNY");
        request.setCur(cur);

        App app = new App();
        app.setBundle(gu.getUpstreamPackageName());
        app.setVer(gaReq.getApp().getAppVersion());
        app.setName(gaReq.getApp().getAppName());
        request.setApp(app);

        Device device = new Device();
        device.setIp(gaReq.getNetwork().getIp());
        int deviceType = gaReq.getDevice().getDeviceType();
        if (deviceType == 1){
            device.setDevicetype(4);
        }else{
            device.setDevicetype(5);
        }
        device.setMake(gaReq.getDevice().getVendor());
        device.setModel(gaReq.getDevice().getModel());
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setOs("Android");
        }else{
            device.setOs("iOS");
        }
        device.setOsv(gaReq.getDevice().getOsVersion());
        //device.setLanguage("cn");
        /*int operatorType = gaReq.getNetwork().getOperatorType();
        if (operatorType == 1){
            device.setCarrier("中国移动");
        }else if (operatorType == 2){
            device.setCarrier("中国电信");
        }else if (operatorType == 3){
            device.setCarrier("中国联通");
        }else{
            device.setCarrier("其他");
        }*/
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            device.setConnectiontype(4);
        }else if (connectionType == 3){
            device.setConnectiontype(5);
        }else if (connectionType == 4){
            device.setConnectiontype(6);
        }else if (connectionType == 5){
            device.setConnectiontype(7);
        }else if (connectionType == 100){
            device.setConnectiontype(2);
        }else{
            device.setConnectiontype(3);
        }
        device.setIfa(gaReq.getDevice().getIdfa());
        device.setUa(gaReq.getDevice().getUa());

        DeviceExt de = new DeviceExt();
        de.setImei(gaReq.getDevice().getImei());
        de.setOaid(gaReq.getDevice().getOaid());
        de.setAndroidid(gaReq.getDevice().getAndroidId());
        de.setDb(gaReq.getDevice().getBrand());
        de.setMcc("460");
        de.setMnc("01");
        de.setCsc("CHC");
        de.setSdkVerAndroid("22");
        de.setAbiType("64");
        device.setExt(de);

        /*Geo geo = new Geo();
        geo.setLat(Float.parseFloat(gaReq.getNetwork().getLat()+""));
        geo.setLon(Float.parseFloat(gaReq.getNetwork().getLon()+""));
        geo.setType(1);
        device.setGeo(geo);*/
        request.setDevice(device);

        List<Imp> impList = new ArrayList<>();
        Imp imp = new Imp();
        imp.setId("1");
        imp.setDisplaymanager("union");
        imp.setTagid("7bd9fbf623f8499faeajkoe37529723f7");
        Native nativeN = new Native();
        RequestN r = new RequestN();
        /*Map<String,Object> title = new HashMap<>();
        title.put("len",25);
        Map<String,Object> a1 = new HashMap<>();
        a1.put("id",1);
        a1.put("required",1);
        a1.put("title",title);
        Map<String,Object> img = new HashMap<>();
        img.put("type",3);
        img.put("w",256);
        img.put("h",256);
        Map<String,Object> a2 = new HashMap<>();
        a2.put("id",2);
        a2.put("required",1);
        a2.put("img",img);
        String stra1 = JSONObject.fromObject(a1).toString();
        String stra2 = JSONObject.fromObject(a2).toString();
        List<String> assets = new ArrayList<>();
        assets.add(stra1);
        assets.add(stra2);
        r.setAssets(assets);*/

        String s = "{\"assets\":[{\"id\":1,\"required\":1,\"title\":{\"len\":25}},{\"id\":2,\"required\":1,\"img\":{\"type\":3,\"w\":256,\"h\":256}}]}";
        r.setAssets(s);

        nativeN.setRequest(s);
        nativeN.setVer("1.0");
        imp.setNativeN(nativeN);

        ImpExt impExt = new ImpExt();
        impExt.setPkgs(gu.getUpstreamAppName());
        imp.setExt(impExt);
        impList.add(imp);
        request.setImp(impList);

        User user = new User();
        user.setId(gaReq.getRequestId());
        user.setYob(1994);
        user.setGender("男");
        request.setUser(user);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 格式化出参参数
     */
    public GetAdsResp formatBackData(String str, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        PTResponse resp = JSON.parseObject(str,PTResponse.class);
        List<Seatbid> seatbid = resp.getSeatbid();
        if (seatbid.size() > 0){
            //多个广告实体容器
            List<Ad> ads = new ArrayList<>();
            List<Bid> bid = seatbid.get(0).getBid();
            if (bid.size() > 0){
                //多条广告
                for (Bid b : bid){
                    //广告主体
                    Ad ya = new Ad();
                    //物料元
                    MaterialMeta ym = new MaterialMeta();
                    ym.setPackageName(b.getBundle());
                    ym.setCreativeType(2);
                    ym.setInteractionType(2);
                    //广告
                    String admS = b.getAdm();
                    if (admS==null || "null".equals(admS)){
                        continue;
                    }
                    Adm adm = JSON.parseObject(admS,Adm.class);
                    //点击上报检测  应用下载地址
                    Link link = adm.getLink();
                    ym.setClickUrl(link.getFallback());
                    //图(宽高)  标题
                    List<String> assets = adm.getAssets();
                    for (String s : assets){
                        JSONObject adContent = JSONObject.fromObject(s);
                        if (adContent.has("title")){
                            ym.setAdTitle(adContent.getJSONObject("title").getString("text"));
                        }
                        if (adContent.has("img")){
                            List<String> images = new ArrayList<>();
                            images.add(adContent.getJSONObject("img").getString("url"));
                            ym.setImageUrl(images);
                            ym.setMaterialWidth(adContent.getJSONObject("img").getInt("w"));
                            ym.setMaterialHeight(adContent.getJSONObject("img").getInt("h"));
                        }
                    }

                    //上报
                    //下载完成监测地址,安装完成监测地址
                    Ext ext = adm.getExt();
                    //展现曝光
                    List<String> imptrackers = adm.getImptrackers();
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&29&3");
                    imptrackers.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    ym.setWinNoticeUrls(imptrackers);
                    //点击
                    List<String> cL = link.getClicktrackers();
                    String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&29&4");
                    cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                    ym.setWinCNoticeUrls(cL);
                    //下载完成
                    ym.setWinDownloadEndUrls(ext.getDownloadtrackers());
                    //安装完成
                    ym.setWinInstallEndUrls(ext.getInstalltrackers());

                    List ymList = new ArrayList();
                    ymList.add(ym);
                    ya.setMetaGroup(ymList);
                    ads.add(ya);
                }

                if (ads.size() > 0){
                    gar.setAds(ads);
                    gar.setRequestId(gaReq.getRequestId());
                    gar.setErrorCode("200");
                    gar.setMsg("SUCCESS");
                }else{
                    gar.setRequestId(gaReq.getRequestId());
                    gar.setErrorCode("400");
                    gar.setMsg("NO_AD");
                }

                //没有广告
            }else{
                gar.setRequestId(gaReq.getRequestId());
                gar.setErrorCode("400");
                gar.setMsg("NO_AD");
            }

            //没有广告
        }else{
            gar.setRequestId(gaReq.getRequestId());
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }

}
