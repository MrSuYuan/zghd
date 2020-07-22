package com.zghd.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.http.HTTPUtils;
import com.util.md5.MD5;
import com.zghd.entity.ZGHDRequest.GetAdsReq;
import com.zghd.entity.ZGHDResponse.Ad;
import com.zghd.entity.ZGHDResponse.GetAdsResp;
import com.zghd.entity.ZGHDResponse.MaterialMeta;
import com.zghd.entity.ZGHDResponse.Track;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxj
 */

@Service(value = "dongfangtoutiaoAdaptor")
public class DongfangtoutiaoAdaptor {
    private static final Logger logger = Logger.getLogger(DongfangtoutiaoAdaptor.class);
    private static final String URL = "http://rellatice.tt.cn/export/";
    
    public GetAdsResp getAds(GetAdsReq ydtReq) throws Exception {
        //String reqParams = convertReq(ydtReq);
    	//String reqParams = convertReqV2(ydtReq);
    	String url1 =  URL+ydtReq.getApp().getAppId()+".api";
    	//reqParams= URL+ydtReq.getApp().getAppId()+".api?apid="+ydtReq.getSlot().getSlotId()+reqParams;
    	Map<String, Object> params = convertReqV3(ydtReq);
        String result = HTTPUtils.getResponseAsStringByGetMethodEEE(url1, 700,params);
		GetAdsResp rsp = convertRsp( ydtReq,result);
        return rsp;
    }
    
  

    private Map<String, Object> convertReqV3(GetAdsReq req) {
    	Map<String, Object> params  = new HashMap<String, Object>();
    	params.put("appVer", req.getApp().getAppVersion());
    	params.put("clientIp", req.getNetwork().getIp());
    	params.put("deviceId", req.getDevice().getAndroidId());
    	params.put("lat",req.getNetwork().getLat());
    	params.put("lng",req.getNetwork().getLon());
    	params.put("position","");
    	params.put("mCount","1");
    	params.put("mHeight",req.getSlot().getSlotheight());
    	params.put("mWidth",req.getSlot().getSlotwidth());
    	params.put("mStyle","018");
    	params.put("apid",req.getSlot().getSlotId());
    	params.put("mac",req.getDevice().getMac());
    	params.put("model",req.getDevice().getModel());
    	int net = req.getNetwork().getConnectionType()==100?1:req.getNetwork().getConnectionType();
    	params.put("network",net);
    	params.put("operater",buildNetwor(req.getNetwork().getOperatorType()));
    	
    	if(req.getDevice().getOsType()==2) {
    		params.put("os","Ios");
    		params.put("imei",MD5.md5(req.getDevice().getIdfa()));
    	}else {
    		params.put("os","Android");
    		params.put("imei",MD5.md5(req.getDevice().getImei()));
    	}
    	params.put("osVersion",req.getDevice().getOsVersion());
    	params.put("packageName",req.getApp().getAppPackage());
    	params.put("platform",req.getDevice().getOsType());
    	params.put("screenHeight",req.getDevice().getScreenHeight());
    	params.put("screenWidth",req.getDevice().getScreenWidth());
    	params.put("userAgent",req.getDevice().getUa());
    	params.put("vendor",req.getDevice().getVendor());
		return params;
	}

    


    private String convertReqV2(GetAdsReq req) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("&appVer="+req.getApp().getAppVersion());
    	sb.append("&clientIp="+req.getNetwork().getIp());
    	sb.append("&deviceId="+req.getDevice().getAndroidId());
    	
    	sb.append("&lat="+req.getNetwork().getLat());
    	sb.append("&lng="+req.getNetwork().getLon());
    	sb.append("&position=null");
    	sb.append("&mCount=1");
    	sb.append("&mHeight="+req.getSlot().getSlotheight());
    	sb.append("&mWidth="+req.getSlot().getSlotwidth());
    	sb.append("&mStyle=018");
    	sb.append("&mac="+req.getDevice().getMac());
    	sb.append("&model="+req.getDevice().getModel());
    	int net = req.getNetwork().getConnectionType()==100?1:req.getNetwork().getConnectionType();
    	sb.append("&network="+buildNetwor(req.getNetwork().getOperatorType()));
    	sb.append("&operater="+buildNetwor(req.getNetwork().getOperatorType()));
    	sb.append("&os=");
    	if(req.getDevice().getOsType()==2) {
    		sb.append("Ios");
    		sb.append("&imei="+MD5.md5(req.getDevice().getIdfa()));
    	}else {
    		sb.append("Android");
    		sb.append("&imei="+MD5.md5(req.getDevice().getImei()));
    	}
    	sb.append("&osVersion="+req.getDevice().getOsVersion());
    	sb.append("&packageName="+req.getApp().getAppPackage());
    	sb.append("&platform="+req.getDevice().getOsType());
    	sb.append("&screenHeight="+req.getDevice().getScreenHeight());
    	sb.append("&screenWidth="+req.getDevice().getScreenWidth());
    	sb.append("&userAgent="+req.getDevice().getUa());
    	sb.append("&vendor="+req.getDevice().getVendor());
		return sb.toString();
	}


	private String buildNetwor(int net) {
		if(net==1) {
			return "46002";
		}else if(net==2) {
			return "46003";
		}else if(net==3) {
			return "46001";
		}
		return "460000";
	}



	private GetAdsResp convertRsp(GetAdsReq ydtReq, String result) {
		GetAdsResp rsp = new GetAdsResp();
    	JSONObject job = JSONObject.parseObject(result);
    	if(job.containsKey("code")) {
    		if(job.getIntValue("code")!=1001) {
        		rsp.setErrorCode(job.getInteger("code")+"");
        		rsp.setMsg("无广告返回");
        		return rsp;
        	}	
    	}
    	
    	
    	if(job.containsKey("data")) {
    		rsp.setErrorCode("0");
    		JSONArray ja =job.getJSONArray("data");
    		List<Ad> ydtads = new ArrayList<Ad>();
    		for(int i=0;i<ja.size();i++) {
    			JSONObject item =ja.getJSONObject(i);
    			Ad ad = new Ad();
    			ad.setSlotId(ydtReq.getSlot().getSlotId());
    			List<MaterialMeta> metas = new ArrayList<MaterialMeta>();
    			MaterialMeta meta = new MaterialMeta();
    			if(item.containsKey("iconUrl")) {
    				List<String> iconUrls = new ArrayList<String>();
    				iconUrls.add(item.getString("iconUrl"));
    				meta.setIconUrls(iconUrls);
    			}
    			if(item.containsKey("imageMaterial")) {
    				JSONArray imageMaterials = item.getJSONArray("imageMaterial");
    				List<String> imageUrls = new ArrayList<String>();
    				for(int m=0;m<imageMaterials.size();m++) {
    					JSONObject img=imageMaterials.getJSONObject(m);
    					if(img.containsKey("src")) {
    						imageUrls.add(img.getString("src"));
    					}
    					if(img.containsKey("height")) {
    						meta.setMaterialHeight(img.getIntValue("height"));
    					}
    					if(img.containsKey("width")) {
    						meta.setMaterialWidth(img.getIntValue("width"));
    					}
    				}
    				meta.setImageUrl(imageUrls);
    			}
    			if(item.containsKey("subTitle")) {
    				List<String> descs = new ArrayList<String>();
    				descs.add(item.getString("subTitle"));
    				meta.setDescs(descs);
    			}
    			if(item.containsKey("title")) {
    				meta.setAdTitle(item.getString("title"));
    			}
    			if(item.containsKey("videoMaterial")) {
    				JSONObject videoMaterial =item.getJSONArray("videoMaterial").getJSONObject(0);
    				if(videoMaterial.containsKey("videoLink")) {
    					meta.setVideoUrl(videoMaterial.getString("videoLink"));
    				}
    				if(videoMaterial.containsKey("videoTime")) {
    					meta.setVideoDuration(videoMaterial.getIntValue("videoTime"));
    				}
    			}
    			
    			if(item.containsKey("mType")) {
    				if(item.getInteger("mType")==0) {
    					meta.setClickUrl(item.getString("landingLink"));
    					meta.setInteractionType(1);
    				}else if(item.getInteger("mType")==1) {
    					meta.setClickUrl(item.getString("downLink"));
    	    			if(item.containsKey("downLink")) {
    	    				meta.setClickUrl(item.getString("downLink"));
    	    			}
    					meta.setInteractionType(2);
    				}else if(item.getInteger("mType")==3){
    					if(item.containsKey("deepLink")) {
    						meta.setInteractionType(3);
    	    				//meta.setDeepLink(item.getString("deepLink"));
    	    			}
    				}
    			}
    			if(item.containsKey("showReport")) {
    				JSONArray show=item.getJSONArray("showReport");
    				List<String> winNoticeUrls = new ArrayList<String>();
    				for(int m=0;m<show.size();m++) {
    					winNoticeUrls.add(show.getString(m));
    				}
    				meta.setWinNoticeUrls(winNoticeUrls);
    			}
    			if(item.containsKey("clickReport")) {
    				JSONArray click=item.getJSONArray("clickReport");
    				List<String> winCNoticeUrls = new ArrayList<String>();
    				for(int m=0;m<click.size();m++) {
    					winCNoticeUrls.add(click.getString(m));
    				}
    				meta.setWinCNoticeUrls(winCNoticeUrls);
    			}
    			if(item.containsKey("endDownReport")) {
    				JSONArray enddown=item.getJSONArray("endDownReport");
    				List<String> arrDownloadedTrakUrl = new ArrayList<String>();
    				for(int m=0;m<enddown.size();m++) {
    					arrDownloadedTrakUrl.add(enddown.getString(m));
    				}
    				meta.setWinDownloadEndUrls(arrDownloadedTrakUrl);
    			}
    			
    			
    			metas.add(meta);
    			ad.setMetaGroup(metas);
    			if(item.containsKey("videoMaterial")) {
    				List<Track> tracks = new ArrayList<Track>();
    				
    				if(item.containsKey("finishReport")) {
        				JSONArray finishReport=item.getJSONArray("finishReport");
        				Track ydt = new Track();
        				ydt.setType(10001);//视频播放完成
        				List<String> urls = new ArrayList<String>();
        				for(int m=0;m<finishReport.size();m++) {
        					urls.add(finishReport.getString(m));
        				}
        				ydt.setUrls(urls);
        				tracks.add(ydt);
        			}
    				
    				if(item.containsKey("inViewReport")) {
        				JSONArray inViewReport=item.getJSONArray("inViewReport");
        				Track ydt = new Track();
        				ydt.setType(10002);//广告进入屏幕展现
        				List<String> urls = new ArrayList<String>();
        				for(int m=0;m<inViewReport.size();m++) {
        					urls.add(inViewReport.getString(m));
        				}
        				ydt.setUrls(urls);
        				tracks.add(ydt);
        			}
    				
        			ad.setTracks(tracks);
    			}
    			
    			
    			
    			ydtads.add(ad);
    		}
    		rsp.setAds(ydtads);
    	} 
    	
		return rsp;
	}






	private String buildOperater(int operatorType) {
		// TODO Auto-generated method stub
		return operatorType+"";
	}
 
}
