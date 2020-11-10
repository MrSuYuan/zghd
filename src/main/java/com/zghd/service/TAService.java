package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.util.http.TestConnectionPool;
import com.zghd.entity.TuiA.response.TAData;
import com.zghd.entity.TuiA.response.TAResponse;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.platform.GetUpstream;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 推啊
 */
@Service(value="taService")
public class TAService {

    /**
     *
     * 向推啊发送请求
     */
    public GetAdsResp TASend(GetAdsReq gaReq, GetUpstream gu) throws Exception{
        //入参参数
        String url = "https://engine.lvehaisen.com/index/serving?appKey="+gu.getUpstreamAppId()+"&adslotId="+gu.getUpstreamId();

        //请求
        String str = TestConnectionPool.get(url,null);

        //回参参数
        GetAdsResp gar = formatBackData(str, gaReq, gu);
        return gar;
    }


    /**
     * 格式化返回参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        TAResponse resp = JSON.parseObject(backData,TAResponse.class);

        String code = resp.getCode();
        if ("0".equals(code)){
            String device_id;
            String userId;
            //1安卓 2ios
            int osType = gaReq.getDevice().getOsType();
            if (osType == 1){
                String imei = gaReq.getDevice().getImei();
                if (null != imei && !"".equals(imei)){
                    device_id = gaReq.getDevice().getImei();
                    userId = gaReq.getDevice().getImei();
                }else{
                    device_id = gaReq.getDevice().getOaid();
                    userId = gaReq.getDevice().getOaid();
                }
            }else{
                device_id = gaReq.getDevice().getIdfa();
                userId = gaReq.getDevice().getIdfa();
            }

            //广告主体
            Ad ya = new Ad();

            TAData data = resp.getData();

            //物料元
            ya.setAdKey(data.getSckId()+"");
            MaterialMeta ym = new MaterialMeta();
            ym.setAdTitle(data.getExtTitle());
            List<String> descs = new ArrayList<>();
            descs.add(data.getExtDesc());
            ym.setDescs(descs);
            List<String> images = new ArrayList<>();
            images.add(data.getImageUrl());
            ym.setImageUrl(images);
            String size = data.getSize();
            if (null!=size && !"".equals(size)){
                String []s = size.split("\\*");
                ym.setMaterialWidth(Integer.valueOf(s[0]));
                ym.setMaterialHeight(Integer.valueOf(s[1]));
            }
            //点击行为 1:页面跳转
            ym.setInteractionType(1);
            ym.setClickUrl(data.getActivityUrl()+"&device_id="+device_id+"&userId="+userId);

            //展现曝光
            List<String> winNotice = new ArrayList<>();
            winNotice.add(data.getReportExposureUrl()+"&device_id="+device_id);
            ym.setWinNoticeUrls(winNotice);

            //点击
            List<String> cL = new ArrayList<>();
            cL.add(data.getReportClickUrl()+"&device_id="+device_id);
            ym.setWinCNoticeUrls(cL);

            List ymList = new ArrayList();
            ymList.add(ym);
            ya.setMetaGroup(ymList);

            List yaList = new ArrayList();
            yaList.add(ya);
            gar.setAds(yaList);
            gar.setRequestId(gaReq.getRequestId());
            gar.setErrorCode("200");
            gar.setMsg("SUCCESS");
        }else{
            gar.setErrorCode("400");
            gar.setMsg("NO_AD");
        }
        return gar;
    }

    /*public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("apps","android,cn.coupon.kfc,cn.coupon.mac,cn.wps.moffice_eng,com.MobileTicket,com.UCMobile,com.alipay.security.mobile.authenticator,com.android.BBKClock,com.android.BBKCrontab,com.android.BBKPhoneInstructions,com.android.BBKTools,com.android.VideoPlayer,com.android.attachcamera,com.android.backupconfirm,com.android.bbk.lockscreen3");
        json.put("imei","355065053311001");
        json.put("latitude","104.07642");
        json.put("longitude","38.6518");
        json.put("nt","wifi");
        System.out.println(json.toString());
        try {
            ByteArrayOutputStream str = formatGzip(json.toString());
            String msg=new BASE64Encoder().encodeBuffer(str.toByteArray()).replaceAll("[\\s*\t\n\r]", "");;
            msg = Base64.encodeBase64String(str.toByteArray());
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ByteArrayOutputStream formatGzip(String reqjson) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gos = null;
        try {
            gos = new GZIPOutputStream(baos);
            gos.write(reqjson.getBytes("UTF-8"));
            gos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(gos !=null) {
                gos.close();
            }
            if(baos !=null) {
                baos.close();
            }
        }
        return baos;
    }*/
}
