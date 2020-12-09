package com.zghd.service;

import com.util.md5.JiaMi;
import com.zghd.entity.HuiChuan.*;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 汇川信息流
 */
@Service(value="hcService")
public class HCService {

    public final static String PARAM_SIG = "sig";

    public final static String[] NOT_ENCRYPTED_PARAMS = { PARAM_SIG };

    private final static Integer connTimeOut = 500;
    private final static Integer readTimeOut = 500;

    /**
     * 向后台发请求
     */
    public GetAdsResp HCSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;

        //正式地址:
        String u = "http://huichuan.sm.cn/nativead";
        //测试地址
        //String u = "https://test.huichuan.sm.cn/nativead";
        //参数转换
        String data = formatData(gaReq, gu);

        URL url = new URL(u);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");//设置请求方式为POST
        connection.setDoOutput(true);//允许写出
        connection.setDoInput(true);//允许读入
        connection.setUseCaches(false);//不使用缓存

        connection.setRequestProperty("ENV","0");
        connection.setRequestProperty("Data_type","2");
        connection.setRequestProperty("VER","1");
        connection.setRequestProperty("SDK_VER","1");

        connection.connect();//连接

        byte[] datbaty = data.getBytes();
        byte[] newData = new byte[16+datbaty.length];
        byte[] header_s = new byte[]{
                0x00,0x02,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
        };
        System.arraycopy(header_s,0,newData,0,header_s.length);
        System.arraycopy(datbaty,0,newData,header_s.length,datbaty.length);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(newData);
        outputStream.flush();

        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){

            InputStream inputStream = connection.getInputStream();
            String result = getStreamToStr(inputStream);//将流转换为字符串。
            gar = formatBackData(result, gaReq, gu);
        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }

        return gar;
    }


    /**
     * 将一个输入流转化为字符串
     *
     * @param tInputStream
     * @return
     */
    public static String getStreamToStr(InputStream tInputStream) {
        if (tInputStream != null) {
            try {
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine;
                while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }




    /**
     * 转换入参参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu){

        AdDeviceInfo device = new AdDeviceInfo();
        //1android 2ios
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            device.setAndroid_id(gaReq.getDevice().getAndroidId());
            device.setImei(gaReq.getDevice().getImei());
            device.setOaid(gaReq.getDevice().getOaid());
            device.setOs("android");
        }else{
            device.setIdfa(gaReq.getDevice().getIdfa());
            device.setOs("ios");
        }
        device.setDevice(gaReq.getDevice().getModel());
        device.setOsv(gaReq.getDevice().getOsVersion());
        device.setMac(gaReq.getDevice().getMac());
        device.setSw(gaReq.getDevice().getScreenWidth()+"");
        device.setSh(gaReq.getDevice().getScreenHeight()+"");
        device.setIs_jb(2+"");
        int operator = gaReq.getNetwork().getOperatorType();
        if (operator == 0){
            device.setCarrier("Unknown");
        }else if (operator == 1){
            device.setCarrier("ChinaMobil");
        }else if (operator == 2){
            device.setCarrier("ChinaTelecom");
        }else if (operator == 3){
            device.setCarrier("ChinaUnicom");
        }else if (operator == 99){
            device.setCarrier("Unknown");
        }
        int connection = gaReq.getNetwork().getConnectionType();
        if (connection == 100){
            device.setAccess("Wi-Fi");
        }else if (connection == 2){
            device.setAccess("2G");
        }else if (connection == 3){
            device.setAccess("3G");
        }else if (connection == 4){
            device.setAccess("4G");
        }else if (connection == 5){
            device.setAccess("5G");
        }else{
            device.setAccess("Unknown");
        }
        device.setClient_ip(gaReq.getNetwork().getIp());

        AdAppInfo app = new AdAppInfo();
        //1android 2ios
        if (osType == 1){
            app.setFr("android");
        }else if(osType == 2){
            app.setFr("iphone");
        }else{
            app.setFr("other");
        }
        app.setIs_ssl("1");
        app.setPkg_name(gu.getUpstreamPackageName());
        app.setPkg_ver(gaReq.getApp().getAppVersion());
        //lm_zhongguan-iflow(中关)
        //lm_dianxing-iflow(王悦)
        //lm_xiongmaorss-iflow(洋溢)
        app.setApp_name(gu.getUpstreamPackageName());
        app.setUa(gaReq.getDevice().getUa());

        AdGpsInfo gps = new AdGpsInfo();

        AdPosInfo pos = new AdPosInfo();
        pos.setSlot_id(1000132+"");
        pos.setWid(gu.getUpstreamId());
        pos.setSlot_type(0+"");
        pos.setReq_cnt(1+"");
        pos.setAw(gaReq.getSlot().getSlotwidth()+"");
        pos.setAh(gaReq.getSlot().getSlotheight()+"");

        HCRequest request = new HCRequest();
        request.setAd_app_info(app);
        request.setAd_device_info(device);
        request.setAd_gps_info(gps);
        List<AdPosInfo> list = new ArrayList<>();
        list.add(pos);
        request.setAd_pos_info(list);

        return JSONObject.fromObject(request).toString();
    }

    /**
     * 转换出参参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();

        JSONObject json = JSONObject.fromObject(backData);
        String code = json.getString("code");
        if ("0".equals(code)){
            gar.setRequestId(gaReq.getRequestId());

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(json.getString("sid"));
            JSONArray jsonArray = json.getJSONArray("slot_ad");
            JSONObject dsIsYes = JSONObject.fromObject(jsonArray.get(0));

            if (dsIsYes.has("ad")){

                //第一次广告数组
                JSONArray ads = JSONObject.fromObject(jsonArray.get(0)).getJSONArray("ad");
                //第二层广告数组
                JSONObject ad = JSONObject.fromObject(ads.get(0));
                //判断下载类 还是 浏览类
                JSONObject ad_action = ad.getJSONObject("ad_action");
                String action = ad_action.getString("action");
                //物料元内容
                JSONObject content = ad.getJSONObject("ad_content");
                MaterialMeta ym = new MaterialMeta();
                ym.setAdTitle(content.getString("title"));
                List<String> descs = new ArrayList<>();
                List<String> image = new ArrayList<>();
                image.add(content.getString("img_1"));
                ym.setImageUrl(image);
                List<String> icon = new ArrayList<>();
                if (content.has("logo_url")){
                    icon.add(content.getString("logo_url"));
                }
                ym.setIconUrls(icon);
                //点击地址 浏览类里面只有一条地址,即点击地址  下载类有两条地址,第一条是跳转的页面,第二条是包地址
                JSONArray clickUrl = ad.getJSONArray("turl");
                //浏览类
                if ("tab".equals(action)){
                    ym.setInteractionType(1);
                    ym.setClickUrl(clickUrl.get(0).toString());
                    //下载类
                }else if ("download".equals(action)){
                    ym.setInteractionType(2);
                    descs.add(content.getString("sub_title"));
                    ym.setPackageName(content.getString("package_name"));
                    ym.setClickUrl(clickUrl.get(1).toString());
                }
                ym.setDescs(descs);
                ym.setMaterialWidth(Integer.valueOf(content.getString("img_1_w")));
                ym.setMaterialHeight(Integer.valueOf(content.getString("img_1_h")));
                //2图片
                ym.setCreativeType(2);
                ym.setTotalNum(1);
                ym.setCurrentIndex(1);

                //展现曝光
                List<String> winNotice = macroParam(ad.getJSONArray("vurl"));
                String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&18&3");
                winNotice.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                ym.setWinNoticeUrls(winNotice);
                //点击
                List<String> clk = macroParam(ad.getJSONArray("curl"));
                String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&18&4");
                clk.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
                ym.setWinCNoticeUrls(clk);

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

        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }

        return gar;
    }

    //宏替换
    public List<String> macroParam(List<String> list){
        List returnList = new ArrayList();
        for (int i = 0; i < list.size(); i++){
            String s = list.get(i);

            //替换__AZCX__
            String hong = "\\{TS\\}";
            Pattern pazcx = Pattern.compile(hong);
            Matcher mazcx = pazcx.matcher(s);
            s = mazcx.replaceAll("__EVENT_TIME_START__");

            returnList.add(i, s);
        }
        return returnList;
    }

}
