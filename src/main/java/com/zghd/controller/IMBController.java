package com.zghd.controller;

import com.alibaba.fastjson.JSON;
import com.zghd.entity.InMoBiDown.request.*;
import com.zghd.entity.InMoBiDown.response.*;
import com.zghd.entity.ZGHDRequest.*;
import com.zghd.entity.ZGHDResponse.*;
import com.zghd.service.PlatformService;
import io.swagger.annotations.Api;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("imb")
@Api(value = "/imb", tags = "InMoBi广告请求")
public class IMBController {


    @Autowired
    private PlatformService platformService;

    /**
     * InMoBi
     */
    @RequestMapping(value = "/getAds", method = {RequestMethod.POST })
    @ResponseBody
    public void getAds(@RequestBody String data, HttpServletResponse response) throws Exception{
        BidResponse bidResponse = new BidResponse();

        //统计使用时间参数
        long startTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//年月日
        String dateStr = sdf.format(startTime);
        Calendar c = Calendar.getInstance();//时
        int hour = c.get(c.HOUR_OF_DAY);

        //try{
            //拿到inmobi的参数
            data = data.replaceAll("native","aNative");
            BidRequest bidData = JSON.parseObject(data, BidRequest.class);
            //转换为平台参数
            GetAdsReq gaReq = formatRequestData(bidData);
            String appId = gaReq.getApp().getAppId();
            String slotId = gaReq.getSlot().getSlotId();

            //广告调度实现
            GetAdsResp resp = platformService.adVideo(gaReq, dateStr, hour, appId, slotId);

            //将返回参数转化为InMoBi返回参数
            bidResponse = formatResponseData(gaReq, resp, bidData);

            //下游请求统计 + 计时统计
            long endTime = System.currentTimeMillis();
            platformService.downStreamReport(appId, slotId, dateStr, hour, endTime - startTime);

            //如果是json解析错误,报300
        /*}catch (JSONException j){
            System.out.println("CONTROLLER300错误");
            //其他程序错误,报500
        }catch (Exception e){
            System.out.println("CONTROLLER500错误");
        }*/
        response.setStatus(200);
        //返回数据
        String jsonData = JSON.toJSONString(bidResponse).replaceAll("aNative","native");
        IOUtils.write(jsonData.getBytes("utf-8"), response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }


    /**
     * 封装入参参数
     */
    private GetAdsReq formatRequestData(BidRequest inMoBiData){
        GetAdsReq gaReq = new GetAdsReq();
        gaReq.setRequestId(inMoBiData.getId());

        String slotId = inMoBiData.getApp().getId();

        App app = new App();
        Device device = new Device();
        Network network = new Network();

        /**
         * APP信息
         */
        ReqApp a = inMoBiData.getApp();
        app.setAppName(a.getName());
        app.setAppPackage(a.getBundle());
        //这个id可能需要自己处理
        app.setAppId("ZG08HD");
        app.setAppVersion("5.1.1");
        gaReq.setApp(app);

        /**
         * 广告位信息
         */
        Slot slot = new Slot();
        ReqImp reqImp = inMoBiData.getImp().get(0);
        //广告位id
        ReqPmp reqPmp = reqImp.getPmp();
        slot.setSlotId(slotId);
        //原生--先看native里面有没有数据
        if (null != reqImp.getaNative()){
            ReqNativeRequest reqNativeRequest = reqImp.getaNative().getRequest();
            List<ReqAssets> assetsList = reqNativeRequest.getAssets();
            for (ReqAssets ar : assetsList){
                //图尺寸
                if (ar.getId() == 6){
                    slot.setSlotwidth(ar.getImg().getW());
                    slot.setSlotheight(ar.getImg().getH());
                    break;
                }
            }
            //这个广告类型处理一下
            int layout = reqNativeRequest.getLayout();
            if (layout == 3){
                slot.setAdtype(4);
            }else if (layout == 501){
                slot.setAdtype(2);
            }else if (layout == 502){
                slot.setAdtype(1);
            }else if (layout == 503){
                slot.setAdtype(3);
            }else if (layout == 504){
                slot.setAdtype(4);
            }else if (layout == 505){
                slot.setAdtype(5);
            }

        //ReqBanner
        }else{
            reqImp.getBanner();
        }
        gaReq.setSlot(slot);


        /**
         * 设备信息
         */
        ReqDevice d = inMoBiData.getDevice();
        //ios设备参数
        ReqDeviceExt ext = d.getExt();
        //deeplink相关
        reqImp.getExt();
        device.setDeviceType(1);
        String os = d.getOs();
        if ("Android".equals(os)){
            device.setOsType(1);
            device.setImei("");
            device.setImei_md5(d.getDidmd5());
            device.setAndroidId("");
            device.setIdfa("");
            device.setIdfa_md5("");
            device.setAndroidId_md5(d.getDpidmd5());
            device.setOaid(d.getOaid());
        }else{
            device.setOsType(2);
            device.setIdfa(d.getIfa());
        }
        device.setMac("");
        device.setUa(d.getUa());
        device.setOsVersion(d.getOsv());
        device.setVendor(d.getMake());
        device.setModel(d.getModel());
        device.setBrand(d.getModel());
        gaReq.setDevice(device);

        network.setIp(d.getIp());
        ReqGeo g = d.getGeo();
        network.setLat(g.getLat());
        network.setLon(g.getLon());
        String ct = d.getConnectiontype();//连接方式
        if ("1".equals(ct)){
            network.setConnectionType(101);
        }else if ("2".equals(ct)){
            network.setConnectionType(100);
        }else if ("3".equals(ct)){
            network.setConnectionType(0);
        }else if ("4".equals(ct)){
            network.setConnectionType(2);
        }else if ("5".equals(ct)){
            network.setConnectionType(3);
        }else if ("6".equals(ct)){
            network.setConnectionType(4);
        }else if ("7".equals(ct)){
            network.setConnectionType(5);
        }else{
            network.setConnectionType(0);
        }
        gaReq.setNetwork(network);

        return gaReq;
    }


    /**
     * 封装出参参数
     */
    private BidResponse formatResponseData(GetAdsReq req, GetAdsResp resp, BidRequest imbData){
        //String back = "{\"ads\":[{\"adKey\":\"3038408029\",\"adlogo\":\"\",\"adtext\":\"\",\"htmlSnippet\":\"\",\"metaGroup\":[{\"adTitle\":\"看美女，来快手！\",\"appSize\":0,\"brandName\":\"com.kuaishou.nebula\",\"browser_ua\":\"\",\"clickUrl\":\"https://c2.gdt.qq.com/gdt_mclick.fcg?viewid=4acmlwNQaAzlPGl6k1E5z0ioB5YzM6QCtoPl!ou8JDgORWtBRZDhvCwUgCte9LltjmEOCl1k6OmwVbrbExT20FsfvpKa6kgs41AQqVoe3wH4Tt_SP5XJe8kPmPe1GVkJL3HSp2LFQlYBt2i7RI!F_stDwBjm_H2M!vNPTSONqGRycVpbbS!Q3d61ACIkrtZnsiZ9y1K716QaVZLgIsL0RJ8BI_BV2f59HZN!epTPG!Qjekb5zhVyX0UyG0afo7LnE7mdsA82VigKZVuwK7UVvRULzcGFQCpmJYoXDgKzLMLOL5mxcMzaG_6Elheeocw!&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22OPPO%22%7D&acttype=1&s=%7B%22req_width%22%3A%221280%22%2C%22req_height%22%3A%22720%22%2C%22width%22%3A%22__WIDTH__%22%2C%22height%22%3A%22__HEIGHT__%22%2C%22down_x%22%3A%22__DOWN_X__%22%2C%22down_y%22%3A%22__DOWN_Y__%22%2C%22up_x%22%3A%22__UP_X__%22%2C%22up_y%22%3A%22__UP_Y__%22%7D&clklpp=__CLICK_LPP__&nxjp=1&xp=0&tl=1\",\"creativeType\":3,\"currentIndex\":0,\"deepLink\":false,\"deepLinkUrl\":\"\",\"descs\":[\"美女实拍视频，看18遍都不腻，好看！\"],\"iconUrls\":[\"https://pgdt.ugdtimg.com/gdt/0/EABBYeOAEsAEsAAAEn_BfhBj0AtBuI_Vh.png/0?ck=f276c3d593b55a65270e9dbe81e81507\"],\"imageUrl\":[\"https://pgdt.ugdtimg.com/gdt/0/DABBYeOAUAALQABnBf-sGIBr3k--Gy.jpg/0?ck=36254a30f3080e1920816a96317b3fb7\"],\"interactionType\":2,\"js_order_id\":0,\"materialHeight\":720,\"materialWidth\":1280,\"packageName\":\"\",\"protocolType\":1,\"req_source\":\"\",\"totalNum\":0,\"user_agent\":\"\",\"videoDuration\":0,\"videoUrl\":\"\",\"winActiveUrls\":[],\"winCNoticeUrls\":[\"http://47.95.31.238/adx/ssp/backNotice?param=C75EA9C9A9053D59696658D183A4E1AD1854AF3844B3D15CAE4E53015BA0307732C3F110A0099E09&event=width:__WIDTH__height:__HEIGHT__dx:__DOWN_X__dy:__DOWN_Y__ux:__UP_X__uy:__UP_Y__\"],\"winCloseUrls\":[],\"winCompleteUrls\":[],\"winDeepLinkFailUrls\":[],\"winDeepLinkSuccessUrls\":[],\"winDeepLinkUrls\":[],\"winDownloadEndUrls\":[\"https://t.gdt.qq.com/conv/alliance/api/conv?client=6&action_id=7&click_id=__CLICK_ID__&product_id=1107805332\"],\"winDownloadUrls\":[\"https://t.gdt.qq.com/conv/alliance/api/conv?client=6&action_id=5&click_id=__CLICK_ID__&product_id=1107805332\"],\"winIgnoreUrls\":[],\"winInstallEndUrls\":[\"https://t.gdt.qq.com/conv/alliance/api/conv?client=6&action_id=6&click_id=__CLICK_ID__&product_id=1107805332\"],\"winInstallOpenUrls\":[],\"winInstallUrls\":[],\"winLoadUrls\":[],\"winNoticeUrls\":[\"https://v2.gdt.qq.com/gdt_stats.fcg?viewid=4acmlwNQaAzlPGl6k1E5z0ioB5YzM6QCtoPl!ou8JDgORWtBRZDhvCwUgCte9LltjmEOCl1k6OmwVbrbExT20FsfvpKa6kgs41AQqVoe3wH4Tt_SP5XJe8kPmPe1GVkJL3HSp2LFQlYBt2i7RI!F_stDwBjm_H2M!vNPTSONqGRycVpbbS!Q3d61ACIkrtZnsiZ9y1K716QaVZLgIsL0RJ8BI_BV2f59HZN!epTPG!Qjekb5zhVyX0UyG0afo7LnE7mdsA82VigKZVuwK7UVvRULzcGFQCpmJYoXDgKzLMLOL5mxcMzaG_6Elheeocw!&i=1&os=2&datatype=json&xp=0&pre_as=1&tl=1\",\"http://47.95.31.238/adx/ssp/backNotice?param=C75EA9C9A9053D59696658D183A4E1AD1854AF3844B3D15CAE4E53015BA03077689682D92840E805\"]}],\"p\":0,\"slotId\":\"\",\"tracks\":[]}],\"errorCode\":\"200\",\"msg\":\"SUCCESS\",\"requestId\":\"1d234d6892a3464969f74b860c1021448618\"}\n";
        //GetAdsResp resp = JSON.parseObject(back, GetAdsResp.class);
        System.out.println(JSONObject.fromObject(resp).toString());
        BidResponse imbResp = new BidResponse();
        String code = resp.getErrorCode();
        if ("200".equals(code)){
            List<Ad> ads = resp.getAds();
            Ad ad = ads.get(0);
            //物料
            MaterialMeta meta = ad.getMetaGroup().get(0);
            //1浏览类  2下载类
            int interactionType = meta.getInteractionType();

            imbResp.setId(resp.getRequestId());//请求id
            imbResp.setBidid("");//Bidder id，由DSP 生成
            imbResp.setCur("");//默认人民币，"CNY"
            List<Seatbid> seatbids = new ArrayList<>();
            Seatbid seatbid = new Seatbid();
            /**
             * +++++++++++++++++
             */
            seatbid.setSeat("");//回传DSP ID，ID由InMobi 分配
            List<Bid> bids = new ArrayList<>();
            Bid bid = new Bid();
            bid.setAdid(ad.getAdKey());//预加载广告ID，如果不支持可以取crid同值
            List brandName = new ArrayList();
            brandName.add(meta.getBrandName());
            bid.setAdomain(brandName);//广告主domain，如"xxx.xxx.com"
            /**
             * +++++++++++++++++
             */
            bid.setCid(0);//Campaign ID
            bid.setCrid(0);//素材ID
            /**
             * +++++++++++++++++
             */
            bid.setId("1");//Bidder id，由DSP 生成
            bid.setImpid(1);//默认为"1"
            bid.setIurl("");//图片地址
            bid.setNurl("");//win notice，仅表示竞价获胜，不代表最终展示
            bid.setBurl("");//billing notice，最终计费通知，表示已获得展示
            bid.setPrice(0);//CPM 出价，单位人民币元
            AdmObject adm = new AdmObject();
            Native n = new Native();
            List<Assets> assetsList = new ArrayList<>();
            /**
             * 图片
             */
            if (null != meta.getImageUrl()){
                Assets assets = new Assets();
                assets.setId(6);//不同物料的id，详情参看 附件3 Assets ID对照表
                assets.setRequired(1);//是否必传，与请求一致即可
                Image img = new Image();
                img.setH(meta.getMaterialHeight());
                img.setW(meta.getMaterialWidth());
                img.setUrl(meta.getImageUrl().get(0));
                assets.setImg(img);
                assetsList.add(assets);
            }

            /**
             * 图标
             */
            if (null != meta.getIconUrls()){
                Assets assets = new Assets();
                assets.setId(3);//不同物料的id，详情参看 附件3 Assets ID对照表
                assets.setRequired(1);//是否必传，与请求一致即可
                Image icon = new Image();
                if (null != meta.getIconUrls() && meta.getIconUrls().size() > 0){
                    icon.setUrl(meta.getIconUrls().get(0));
                }
                assets.setImg(icon);
                assetsList.add(assets);
            }

            /**
             * 标题
             */
            if (null != meta.getAdTitle() && !"".equals(meta.getAdTitle())){
                Assets assets = new Assets();
                assets.setId(4);//不同物料的id，详情参看 附件3 Assets ID对照表
                assets.setRequired(1);//是否必传，与请求一致即可
                Title title = new Title();
                title.setText(meta.getAdTitle());
                assets.setTitle(title);
                assetsList.add(assets);
            }

            /**
             * 描述
             */
            if (null != meta.getDescs()){
                Assets assets = new Assets();
                assets.setId(5);//不同物料的id，详情参看 附件3 Assets ID对照表
                assets.setRequired(1);//是否必传，与请求一致即可
                Data data = new Data();
                data.setValue(meta.getDescs().get(0));
                assets.setData(data);
                assetsList.add(assets);
            }

            /**
             * 链接
             */
            n.setImptrackers(meta.getWinNoticeUrls());//展示监测链接，也可以直接放置参数${AUCTION_PRICE}，接收成功竞得价格
            Link link = new Link();
            link.setClicktrackers(meta.getWinCNoticeUrls());//点击监测链接
            if (meta.isDeepLink()){
                link.setUrl(meta.getDeepLinkUrl());//落地链接
                link.setFallback(meta.getClickUrl());//如果url链接是deeplink链接，则fallback 放置备用H5 落地链接，以应对deeplink url 无法吊起的情况
            }else{
                link.setUrl(meta.getClickUrl());//落地链接
            }
            n.setLink(link);
            n.setAssets(assetsList);
            n.setVer(1);//默认为"1"
            adm.setaNative(n);
            bid.setAdmobject(adm);
            bids.add(bid);
            seatbid.setBid(bids);
            seatbids.add(seatbid);
            imbResp.setSeatbid(seatbids);

        }else{

        }

        return imbResp;
    }



}
