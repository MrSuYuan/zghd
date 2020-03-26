package com.zghd.service;

import com.zghd.entity.ZGHDResponse.GetAdsResp;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 验参
 */
@Service("verifyService")
public class VerifyService {

    /**
     * 验证参数
     */
    public GetAdsResp verifyParam(String data){
        GetAdsResp gar = new GetAdsResp();

        //验证data
        boolean dataKey = isJson(data);
        if(false == dataKey){
            gar.setErrorCode("300");
            gar.setMsg("PARAM_ERROR");
            return gar;
        }else{
            JSONObject json = JSONObject.fromObject(data);

            //验证app
            boolean appKey = json.has("app");
            if(false == appKey){
                gar.setErrorCode("31001");
                gar.setMsg("APP_MISSING");
                return gar;
            }
            boolean appIsJson = isJson(json.getString("app"));
            if(appIsJson == false){
                gar.setErrorCode("31002");
                gar.setMsg("APP_ERROR");
                return gar;
            }

            //验证slot
            boolean slotKey = json.has("slot");
            if(false == slotKey){
                gar.setErrorCode("32001");
                gar.setMsg("SLOT_MISSING");
                return gar;
            }
            boolean slotIsJson = isJson(json.getString("slot"));
            if(slotIsJson == false){
                gar.setErrorCode("32002");
                gar.setMsg("SLOT_ERROR");
                return gar;
            }

            //验证network
            boolean networkKey = json.has("network");
            if(false == networkKey){
                gar.setErrorCode("33001");
                gar.setMsg("NETWORK_MISSING");
                return gar;
            }
            boolean networkIsJson = isJson(json.getString("network"));
            if(networkIsJson == false){
                gar.setErrorCode("33002");
                gar.setMsg("NETWORK_ERROR");
                return gar;
            }

            //验证device
            boolean deviceKey = json.has("device");
            if(false == deviceKey){
                gar.setErrorCode("34001");
                gar.setMsg("DEVICE_MISSING");
                return gar;
            }
            boolean deviceIsJson = isJson(json.getString("device"));
            if(deviceIsJson == false){
                gar.setErrorCode("34002");
                gar.setMsg("DEVICE_ERROR");
                return gar;
            }

            JSONObject app = json.getJSONObject("app");
            List<String> appKeyList = new ArrayList<>();
            appKeyList.add("appId");
            appKeyList.add("appPackage");
            appKeyList.add("appVersion");
            appKeyList.add("appName");

            JSONObject slot = json.getJSONObject("slot");
            List<String> slotKeyList = new ArrayList<>();
            slotKeyList.add("slotId");
            slotKeyList.add("slotwidth");
            slotKeyList.add("slotheight");

            JSONObject network = json.getJSONObject("network");
            List<String> networkKeyList = new ArrayList<>();
            networkKeyList.add("ip");
            networkKeyList.add("connectionType");
            networkKeyList.add("operatorType");

            JSONObject device = json.getJSONObject("device");
            List<String> deviceKeyList = new ArrayList<>();
            deviceKeyList.add("idfa");
            deviceKeyList.add("imei");
            deviceKeyList.add("mac");
            deviceKeyList.add("androidId");
            deviceKeyList.add("model");
            deviceKeyList.add("vendor");
            deviceKeyList.add("screenWidth");
            deviceKeyList.add("screenHeight");
            deviceKeyList.add("osType");
            deviceKeyList.add("osVersion");
            deviceKeyList.add("deviceType");
            deviceKeyList.add("ua");
            deviceKeyList.add("ppi");
            deviceKeyList.add("screenOrientation");
            deviceKeyList.add("brand");
            deviceKeyList.add("imsi");

            //先验证键
            gar = checkKey(app,appKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }
            gar = checkKey(slot,slotKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }
            gar = checkKey(network,networkKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }
            gar = checkKey(device,deviceKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }

            //再验证值
            gar = checkValue(app,appKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }
            gar = checkValue(slot,slotKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }
            gar = checkValue(network,networkKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }
            //先不验证device的值
            /*gar = volidateValue(device,deviceKeyList);
            if("200" != gar.getErrorCode()){
                return gar;
            }*/
        }

        /**
         * 个别上游要求
         * 1.app版本号 a.b.c
         * 2.系统版本号
         * 3.16位的imei?androidId?
         */

        gar.setErrorCode("200");
        return gar;
    }

    /**
     * 判断key存不存在,并且是不是json
     */
    public boolean isJson(String key){
        if(null == key || "null".equals(key)){
            return false;
        }
        try {
            JSONObject.fromObject(key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证key存在
     */
    public GetAdsResp checkKey(JSONObject volidateMap, List<String> keys){
        GetAdsResp gar = new GetAdsResp();
        for(String key : keys){
            //验证键
            if (volidateMap.has(key)){
                continue;
            }else{
                gar.setMsg(key.toUpperCase()+"_MISSING");
                if("appId".equals(key)){
                    gar.setErrorCode("31003");
                    return gar;
                }else if("appPackage".equals(key)){
                    gar.setErrorCode("31005");
                    return gar;
                }else if("appVersion".equals(key)){
                    gar.setErrorCode("31007");
                    return gar;
                }else if("appName".equals(key)){
                    gar.setErrorCode("31009");
                    return gar;
                }else if("slotId".equals(key)){
                    gar.setErrorCode("32003");
                    return gar;
                }else if("slotwidth".equals(key)){
                    gar.setErrorCode("32005");
                    return gar;
                }else if("slotheight".equals(key)){
                    gar.setErrorCode("32007");
                    return gar;
                }else if("ip".equals(key)){
                    gar.setErrorCode("33003");
                    return gar;
                }else if("connectionType".equals(key)){
                    gar.setErrorCode("33005");
                    return gar;
                }else if("operatorType".equals(key)){
                    gar.setErrorCode("33007");
                    return gar;
                }else if("idfa".equals(key)){
                    gar.setErrorCode("34003");
                    return gar;
                }else if("imei".equals(key)){
                    gar.setErrorCode("34005");
                    return gar;
                }else if("mac".equals(key)){
                    gar.setErrorCode("34007");
                    return gar;
                }else if("androidId".equals(key)){
                    gar.setErrorCode("34009");
                    return gar;
                }else if("model".equals(key)){
                    gar.setErrorCode("34011");
                    return gar;
                }else if("vendor".equals(key)){
                    gar.setErrorCode("34013");
                    return gar;
                }else if("screenWidth".equals(key)){
                    gar.setErrorCode("34015");
                    return gar;
                }else if("screenHeight".equals(key)){
                    gar.setErrorCode("34017");
                    return gar;
                }else if("osType".equals(key)){
                    gar.setErrorCode("34019");
                    return gar;
                }else if("osVersion".equals(key)){
                    gar.setErrorCode("34021");
                    return gar;
                }else if("deviceType".equals(key)){
                    gar.setErrorCode("34023");
                    return gar;
                }else if("ua".equals(key)){
                    gar.setErrorCode("34025");
                    return gar;
                }else if("ppi".equals(key)){
                    gar.setErrorCode("34027");
                    return gar;
                }else if("screenOrientation".equals(key)){
                    gar.setErrorCode("34029");
                    return gar;
                }else if("brand".equals(key)){
                    gar.setErrorCode("34031");
                    return gar;
                }else if("imsi".equals(key)){
                    gar.setErrorCode("34033");
                    return gar;
                }
            }
        }
        gar.setErrorCode("200");
        gar.setMsg("SUCCESS");
        return gar;
    }

    /**
     * 验证value存在
     */
    public GetAdsResp checkValue(JSONObject volidateMap, List<String> keys){
        GetAdsResp gar = new GetAdsResp();
        for(String key : keys){
            String value = volidateMap.getString(key);
            if(null != value && !"".equals(value) && !"null".equals(value)) {
                continue;
            }else{
                gar.setMsg(key.toUpperCase()+"_ERROR");
                if("appId".equals(key)){
                    gar.setErrorCode("31004");
                    return gar;
                }else if("appPackage".equals(key)){
                    gar.setErrorCode("31006");
                    return gar;
                }else if("appVersion".equals(key)){
                    gar.setErrorCode("31008");
                    return gar;
                }else if("appName".equals(key)){
                    gar.setErrorCode("31010");
                    return gar;
                }else if("slotId".equals(key)){
                    gar.setErrorCode("32004");
                    return gar;
                }else if("slotwidth".equals(key)){
                    gar.setErrorCode("32006");
                    return gar;
                }else if("slotheight".equals(key)){
                    gar.setErrorCode("32008");
                    return gar;
                }else if("ip".equals(key)){
                    gar.setErrorCode("33004");
                    return gar;
                }else if("connectionType".equals(key)){
                    gar.setErrorCode("33006");
                    return gar;
                }else if("operatorType".equals(key)){
                    gar.setErrorCode("33008");
                    return gar;
                }else if("idfa".equals(key)){
                    gar.setErrorCode("34004");
                    return gar;
                }else if("imei".equals(key)){
                    gar.setErrorCode("34006");
                    return gar;
                }else if("mac".equals(key)){
                    gar.setErrorCode("34008");
                    return gar;
                }else if("androidId".equals(key)){
                    gar.setErrorCode("34010");
                    return gar;
                }else if("model".equals(key)){
                    gar.setErrorCode("34012");
                    return gar;
                }else if("vendor".equals(key)){
                    gar.setErrorCode("34014");
                    return gar;
                }else if("screenWidth".equals(key)){
                    gar.setErrorCode("34016");
                    return gar;
                }else if("screenHeight".equals(key)){
                    gar.setErrorCode("34018");
                    return gar;
                }else if("osType".equals(key)){
                    gar.setErrorCode("34020");
                    return gar;
                }else if("osVersion".equals(key)){
                    gar.setErrorCode("34022");
                    return gar;
                }else if("deviceType".equals(key)){
                    gar.setErrorCode("34024");
                    return gar;
                }else if("ua".equals(key)){
                    gar.setErrorCode("34026");
                    return gar;
                }else if("ppi".equals(key)){
                    gar.setErrorCode("34028");
                    return gar;
                }else if("screenOrientation".equals(key)){
                    gar.setErrorCode("34030");
                    return gar;
                }else if("brand".equals(key)){
                    gar.setErrorCode("34032");
                    return gar;
                }else if("imsi".equals(key)){
                    gar.setErrorCode("34034");
                    return gar;
                }
            }
        }
        gar.setErrorCode("200");
        gar.setMsg("SUCCESS");
        return gar;
    }

}
