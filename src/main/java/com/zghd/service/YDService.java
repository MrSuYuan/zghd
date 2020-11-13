package com.zghd.service;

import com.util.md5.JiaMi;
import com.util.md5.MD5;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 有道
 */
@Service(value="ydService")
public class YDService {

    /**
     * 向后台发请求
     */
    public GetAdsResp YDSend(GetAdsReq gaReq, GetUpstream gu) throws Exception {
        GetAdsResp gar;

        /*//临时版
        if("c0xn8tm2".equals(gaReq.getSlot().getSlotId())){
            double x = Math.random();
            String s ;
            if (x>0.5){
                s = "{\"ads\":[{\"adKey\":\"de4d1334ff40082df130f02c900443d6\",\"metaGroup\":[{\"adTitle\":\"零钱理财，门槛低，注册还能领388元红包\",\"appSize\":0,\"brandName\":\"创意投\",\"clickUrl\":\"http://ap.51cytou.com/andriod-cytou.apk\",\"creativeType\":5,\"currentIndex\":1,\"descs\":[],\"iconUrls\":[\"http://oimagea6.ydstatic.com/image?id=3586906694633027006&product=adpublish&w=300&h=300&sc=0&rm=2&gsb=0&gsbd=60\"],\"imageUrl\":[\"\"],\"interactionType\":2,\"materialHeight\":1280,\"materialWidth\":960,\"packageName\":\"com.cytou51.app\",\"protocolType\":0,\"totalNum\":1,\"videoDuration\":30,\"videoUrl\":\"https://download.ydstatic.com/ead/200211chuangyitou30sZY318a.mp4\",\"winCNoticeUrls\":[\"http://p.clkservice.youdao.com/clk/request.s?slot=3774bb3839b4aef8face76d1e6e7b03b&k=Yto0%2BjTS9m7T2gfrokn%2BD9sbR58qRPrM6v8WTjZ%2BYw3Xxo%2BoRxcJpjjAgKuViCqvQvVhB3sMLlYMOTVW6q%2BFyQZn%2FbdNNp%2B4sYddn8UpUFr0%2Fb%2FTzqZouO2zpWcebib1EKGACA7QweQBJMTWuRtS7vVUOlG56PXMxpC2qzqqEaissOVAl6voFwV9pTpbrO%2F6i2faePbJWLeZCuIhNg1nXWjP5J0X9ZpwOah4Q%2BM5laYei0DCI9OYGF3vePOhm6z5sjQV7aS%2F7PJEttqohugvHbRn9bkHJqibZHrNgIaDHD%2BFBUEHgRtu0vtNhy9pOT7yS4xzKWvVkG689iTg%2BcHWYFeLw2qo8vwBe2fUnG9XzS2gTyXmtmyDBC5TFvM%2FYBbb1tbKEtxyFyIXmLuPn3UM38jJE2Mh1CvxKISQ0Ll1VfFzExHb5gSd7qFLSwAIew6annAudlP775vGzCeuHaWuxNhUeTUjRkssanzIPl4lG3iHjD39g5stBseO8JsiOrTWXLd1GH9APTRaNbDvzeMLnBcbQqPdSvV2wayZczAC45tr3Hez3DWFd2PqqJDtiq5weZw6wHVSlBMk5OHbfHRV%2FE5KdaHQAqmkHMc6LEBF184v%2F7MiI0lcGnte6N8qe9OH3gnW8h9FYdEKkK5ttCHA25Ngkik3rMPghoaUaxttF4pfabWkRBgZWEoj%2B%2BGx6cENIThKXzHwZ1%2BP9S2ohbDpL0OqZ496CgEOprsSiFlxjmxiKYRqDQYrMYypTlT2lFxfPhg4nq4nXouCSvu2zf%2FhiiFahViLQL87fPigLmbmDZVb21z0iB5BqpRnoGPyaDMRttueXhsUV8HBVuTOrCJ4M95%2BDoWOFCZzKXPZMhxlHjbzfHrNtr7TtpCzDJMtUCC%2FGddLuBCaqr5WOggXy43RC%2FjE1nimuXq5AHBHjTpkpesqdmh8kiouJ0whvuqGCqXSWxO3NstLm8dnyRhV6f%2FXKlx88hs59QYK3vzQmN0c3UcfPMj0OniIdHnJ%2Bsfln%2FcwCN9y4H6jIbGw8E%2B17Calpq%2BBVhG6lRuNBy1Ie3LCFVzqSqUh1s%2FhBFU9rTsk6l48T5ZyHHOiuRu%2Bvphiz2rcUHmXDqIyroYPFOXmNcnY3ivSD%2FyTMJmu%2FyhUhdH61XuhbTTdxkevsseW7UVzh7Nf1%2F5ycIt%2B35yTbBpbfojPNvaIeWPdwp67eMY7XZkux692XKUhFzlUKKZG%2FuFQ2WdHoAdAW8apyXPo1zaBU%2Bq0K4MMVTzNucKrn%2FbRSIB4L5Dj8yYNr4lurMLvL5M7nvx%2FpIvMpqdriLo7j7hMdYt%2BV6DXxo%2BoRxcJpjjAgKuViCqv18aPqEcXCaY4wICrlYgqr0LH2t18Z6Kla%2BQLkO3p44s%3D&isrd=0&youdao_bid=5a94802d-1b19-4c7f-885c-a86dd1b6d411&youdao_deviceId=490E51C3256C1D48D208DFE257371963&iid=%7B%22-5019977113036504890%22%3A1%2C%223586906694633027006%22%3A4%2C%226091600025074095208%22%3A1%2C%226563407705052515054%22%3A1%7D&sid=21452&tid=40\",\"https://cn.bj.adx.adwangmai.com/clickNotice.api?reqId=de4d1334ff40082df130f02c900443d6&v=NewqVhRzKGBuN2J392Ncb3lwqUqNiMFWSBJLdehjRa7QsfnP82_BRzcO7oCSG2fs7xzAlAdZMOOFkPwUhiiuJxYFOxLlem1mKEb6KHCpCMu6evg-Nz1F_sncXinw6LhqJ0PU55cKep4qajERwZV-iNjJtjdUqL4B5jnYi2sK-LdpLbdhRr4UwrKEENAMAU-L8bCT98REhb2wlRWAxHDQAOSjc_sZhuZYU0byzFQ7oq8T33M2f9ocsK5fiOGt77WS0KfcNLWlVXm7L_nADcw2sZy3_xzwwRzViBBvSM9kKBXLe5FHhU-kNO0zJpCkkVWSP1TEmV074DWLGivPBQmsbIx9OsYlkxTOlOFgBrKB_TO_nqTczlov5WM8wMuMaNhvmzJXVGEA-i7leDJpQkg9eAFzz2YgPm6lj7VqlngifID5dBF9R__WmTnBtUMTVQ1q8fnnI3O-A27X4wW4Jkp22U-2N5fFOEP7ElubJ4X-4_fgQNqOTbC4meP-93zxdLcfz7nI3Ibkt1oreF3Cjk5ici1KI4cHeOAm_kp9Tr1MAcb4Pxvy7bvyre9j6f9COJKKpguYwIz1-2MqZw&ts=1582859064878\",\"http://47.95.31.238/adx/ssp/backNotice?param=2A84AD9A56273B26F0E376C6535B0CA433A0D5811211D1E52EB5EB9AB461AB900F00A86725972E26D4669C054C23655308E9C5E132DA2D8BDBB3EEE4D4DF4A90\"],\"winCloseUrls\":[],\"winDownloadEndUrls\":[],\"winDownloadUrls\":[],\"winInstallEndUrls\":[],\"winInstallOpenUrls\":[],\"winNoticeUrls\":[\"http://47.95.31.238/adx/ssp/backNotice?param=\"]}],\"tracks\":[{\"type\":103,\"urls\":[\"http://dsp-impr2.youdao.com/impplay.s?ext=CiQ1YTk0ODAyZC0xYjE5LTRjN2YtODg1Yy1hODZkZDFiNmQ0MTEQou0SGPzzPyDi6%2FcCKP7u0gswbjoPMTE3LjE1MC4xODkuMTMzQICsgs6ILkgBUgQ4NDM0WiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYmIgNDkwRTUxQzMyNTZDMUQ0OEQyMDhERkUyNTczNzE5NjNqAHIAeACCARA0RkQ4NkM4MUZDOEUyREFBigEPODY2MzMzMDI2OTM5NDk0kAEUmAG2AqIBBU9USEVSqgEOWGlhb21pLE1JIDRMVEW4AfYHwgEkNjhiMWQxZDYtZjA1YS00OGVlLWE4OWMtNjc3NzBmZTgyMzhh0gEFMS4zLjHaAQUxLjMuMQ%3D%3D&event_type=203\"]},{\"type\":104,\"urls\":[\"http://dsp-impr2.youdao.com/impplay.s?ext=CiQ1YTk0ODAyZC0xYjE5LTRjN2YtODg1Yy1hODZkZDFiNmQ0MTEQou0SGPzzPyDi6%2FcCKP7u0gswbjoPMTE3LjE1MC4xODkuMTMzQICsgs6ILkgBUgQ4NDM0WiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYmIgNDkwRTUxQzMyNTZDMUQ0OEQyMDhERkUyNTczNzE5NjNqAHIAeACCARA0RkQ4NkM4MUZDOEUyREFBigEPODY2MzMzMDI2OTM5NDk0kAEUmAG2AqIBBU9USEVSqgEOWGlhb21pLE1JIDRMVEW4AfYHwgEkNjhiMWQxZDYtZjA1YS00OGVlLWE4OWMtNjc3NzBmZTgyMzhh0gEFMS4zLjHaAQUxLjMuMQ%3D%3D&event_type=204\"]},{\"type\":0,\"urls\":[\"http://dsp-impr2.youdao.com/impplay.s?ext=CiQ1YTk0ODAyZC0xYjE5LTRjN2YtODg1Yy1hODZkZDFiNmQ0MTEQou0SGPzzPyDi6%2FcCKP7u0gswbjoPMTE3LjE1MC4xODkuMTMzQICsgs6ILkgBUgQ4NDM0WiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYmIgNDkwRTUxQzMyNTZDMUQ0OEQyMDhERkUyNTczNzE5NjNqAHIAeACCARA0RkQ4NkM4MUZDOEUyREFBigEPODY2MzMzMDI2OTM5NDk0kAEUmAG2AqIBBU9USEVSqgEOWGlhb21pLE1JIDRMVEW4AfYHwgEkNjhiMWQxZDYtZjA1YS00OGVlLWE4OWMtNjc3NzBmZTgyMzhh0gEFMS4zLjHaAQUxLjMuMQ%3D%3D&event_type=205&play_percent=0.0\"]},{\"type\":4,\"urls\":[\"http://dsp-impr2.youdao.com/k.gif?yd_ewp=15316&yd_ext=ErsBCgEwEiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYiKRAQj-7tILEOLr9wIY_PM_IKLtEiik3wIwSzhLZQBMb0ZwAHgAgAEAmAEBogELVHJhZGl0aW9uYWy6ARJ7Ik9SREVSRURfSUQiOiIxIn3gAdAGgAIAigIgZTkyYTUyYzhkMTY2ZDRmMzNlYjAxMDBhNGI0Yzg0MDaSAgIwMJgCG6ACAKgCALgCAMACAMgCANACtAQwAyIkNWE5NDgwMmQtMWIxOS00YzdmLTg4NWMtYTg2ZGQxYjZkNDExKG4wADoAQgBSDzExNy4xNTAuMTg5LjEzM2oNMTU4Mjg1OTA2NDgyNngAggEAiAHUd5AB-quCzoguqAEBsAEBuAEBwgEEODQzNNABAtoBIDQ5MEU1MUMzMjU2QzFENDhEMjA4REZFMjU3MzcxOTYz4gFAEg5YaWFvbWksTUkgNExURRoPODY2MzMzMDI2OTM5NDk0IgAqADIQNEZEODZDODFGQzhFMkRBQToFMS4zLjFCAOgB9gf6AQUxLjMuMYACoQaKAgp0aDA4Ny05MDEzwgISCQAAAAAAAAAAEQAAAAAAAPC_&iid=%7B%22-5019977113036504890%22%3A1%2C%223586906694633027006%22%3A4%2C%226091600025074095208%22%3A1%2C%226563407705052515054%22%3A1%7D&sid=21452&tid=40\",\"https://cn.bj.adx.adwangmai.com/displayNotice.api?reqId=de4d1334ff40082df130f02c900443d6&v=NewqVhRzKGBuN2J392Ncb3lwqUqNiMFWSBJLdehjRa7QsfnP82_BRzcO7oCSG2fs7xzAlAdZMOOFkPwUhiiuJxYFOxLlem1mKEb6KHCpCMu6evg-Nz1F_sncXinw6LhqJ0PU55cKep4qajERwZV-iNjJtjdUqL4B5jnYi2sK-LdpLbdhRr4UwrKEENAMAU-L8bCT98REhb2wlRWAxHDQAOSjc_sZhuZYU0byzFQ7oq8T33M2f9ocsK5fiOGt77WS0KfcNLWlVXm7L_nADcw2sZy3_xzwwRzViBBvSM9kKBXLe5FHhU-kNO0zJpCkkVWSP1TEmV074DWLGivPBQmsbIx9OsYlkxTOlOFgBrKB_TO_nqTczlov5WM8wMuMaNhvmzJXVGEA-i7leDJpQkg9eAFzz2YgPm6lj7VqlngifID5dBF9R__WmTnBtUMTVQ1q8fnnI3O-A27X4wW4Jkp22U-2N5fFOEP7ElubJ4X-4_fgQNqOTbC4meP-93zxdLcfz7nI3Ibkt1oreF3Cjk5ici1KI4cHeOAm_kp9Tr1MAcb4Pxvy7bvyre9j6f9COJKKpguYwIz1-2MqZw&ts=1582859064878\",\"http://47.95.31.238/adx/ssp/backNotice?param=2A84AD9A56273B26F0E376C6535B0CA433A0D5811211D1E52EB5EB9AB461AB900F00A86725972E26D4669C054C236553988723EBE2DBD0B981722642698DFD8C\"]}]}],\"errorCode\":\"200\",\"msg\":\"SUCCESS\",\"requestId\":\"1119092016594257588754\"}\n";
            }else{
                s = "{\"ads\":[{\"adKey\":\"96ae664d9f014f9353a36dd7bd7a94d1\",\"metaGroup\":[{\"adTitle\":\"存款还放在银行？不如在这稳健理财！\",\"appSize\":0,\"brandName\":\"正川国际\",\"clickUrl\":\"http://app.zczben51.com/123.apk\",\"creativeType\":5,\"currentIndex\":1,\"descs\":[],\"iconUrls\":[\"http://oimagec2.ydstatic.com/image?id=5126483666218420970&product=adpublish&w=300&h=300&sc=0&rm=2&gsb=0&gsbd=60\"],\"imageUrl\":[\"http://oimagec3.ydstatic.com/image?id=-8277771976911226161&product=adpublish&w=720&h=1280&sc=0&rm=0\"],\"interactionType\":2,\"materialHeight\":1280,\"materialWidth\":960,\"packageName\":\"com.zczben51.app\",\"protocolType\":0,\"totalNum\":1,\"videoDuration\":27,\"videoUrl\":\"https://download.ydstatic.com/ead/200218zhengchuanguoji27sWX61.mp4\",\"winCNoticeUrls\":[\"http://p.clkservice.youdao.com/clk/request.s?slot=3774bb3839b4aef8face76d1e6e7b03b&k=bCz8gA1fqu6%2BhHZvk%2FdBNPk5DfhLCIEjk%2BSkJvdT513Xxo%2BoRxcJpjjAgKuViCqvQvVhB3sMLlYMOTVW6q%2BFyQZn%2FbdNNp%2B4sYddn8UpUFq5KDPpyWxBnZB135C0yAtpEKGACA7QweQBJMTWuRtS7uTEHWbjmMBNU8jtlzF2%2FYyssOVAl6voFwV9pTpbrO%2F6i2faePbJWLeZCuIhNg1nXWjP5J0X9ZpwOah4Q%2BM5laYei0DCI9OYGF3vePOhm6z5sjQV7aS%2F7PJEttqohugvHbRn9bkHJqibZHrNgIaDHD%2BFBUEHgRtu0vtNhy9pOT7yS4xzKWvVkG689iTg%2BcHWYFeLw2qo8vwBe2fUnG9XzS2gTyXmtmyDBC5TFvM%2FYBbb1tbKEtxyFyIXmLuPn3UM38jJE2Mh1CvxKISQ0Ll1VfGb5gsEAyBPwlwMWFBYjnavnnAudlP775vGzCeuHaWuxNhUeTUjRkssanzIPl4lG3iHjD39g5stBseO8JsiOrTWXLd1GH9APTRaNbDvzeMLnBcbQqPdSvV2wayZczAC45tr3Hez3DWFd2PqqJDtiq5weZw6wHVSlBMk5OHbfHRV%2FE5KdaHQAqmkHMc6LEBF184v%2F7MiI0lcGnte6N8qe9OH3gnW8h9FYdEKkK5ttCHA20EBQ1niT4EGzfptvPw1alnQJeqB8Kug7ID7t1vfBTPArcLPeYnCvKXnAIJ4wlvCZEOqZ496CgEOprsSiFlxjmxiKYRqDQYrMYypTlT2lFxfPhg4nq4nXouCSvu2zf%2FhisrYcNDZjhxZ3%2FpjMLKqOcxrrt%2BUI%2Fj%2FhShADtzSckKOorVj%2BTVvEQHXx1BYKywtgNbXxzUfENDsMfgqHQAX82iMiZrXMPTwhiCyFzbq3JPPcKmuK1yVmNOu0oDiUEGMIqkD3%2FOD%2BKS4Sh7mzG%2FUBgkuyGKDP7PRHZMHz3fPYYaxHvqVeAVkX%2BYFqQjztZy1tNn6K6ymrZma6wl2T2tp6kzetvlqqR74Xiv4AU3g9sGZx8ocN18bd%2BeDtJyCMW9UQJ5Iu3MaI0l8HqvKDqJFnYsf559IofsrEbIvYAwe9Bbx17kzc%2B7yyt4GYA%2FZa505hT6loG4xT6duYy5GywOnvvz1%2BvSbha4h1VFtAxaX9%2BZpRab9BqbKCIjQAGNzbjwJUEJVgG%2Fdm8J%2Fq7jVnuXLCe%2FOoamwmLhiL2md6OanLVljojHKsDlXK6nyo25P0hrNrn6hA402DlvM85Boco0QiYioQwGBO9w%2FvcdvI4PD7RHOpjV59%2FGP%2BcyLgMuEdSOFQtfGj6hHFwmmOMCAq5WIKq%2FXxo%2BoRxcJpjjAgKuViCqvNJRG5tbjAFed%2BN9BhR7PLM3idoGAZsrc6o86YvwEqk4%3D&isrd=0&youdao_bid=b1027deb-715c-4d62-b19f-2306469ca37b&youdao_deviceId=490E51C3256C1D48D208DFE257371963&iid=%7B%225126483666218420970%22%3A4%2C%22-8277771976911226161%22%3A1%7D&sid=21454&tid=38\",\"https://cn.bj.adx.adwangmai.com/clickNotice.api?reqId=96ae664d9f014f9353a36dd7bd7a94d1&v=GwTRPFdQbXtYbkFZygs_RCxvnvN4SXawfDzBH-jUwci02MzNtLJNt2WAMkZ8WkruBJK32l1oUKCmVVQiyHOGeiLVJmRTfOshIwyTR9g73Umrb5HMVsSKK0w0H96x82_blrMWB4frMB0a9FZKdhylrdI1BBR3dSIo0C7kWIEYnSS2MTn2VFdS_ArzB7Lm45um6ZH72Lmn8D8hNpPgXOqe9d90mmiPTlFXXzxGSmxB-BocUAcMnBbsWn37suPi6ZVOYl_Dw7VlOqanllepumDmu-VzM1acMb5JXZfMT2VtPST1u43sAtiI8kEchkg6nxwHpnHkMflEKTZrhJGQuk5wbhi7TYi-jcrZvXsk5YTU5nGz0KzTtw1VeyiRYpHnn2dOkEHAt7dw8SFbKY1tTGSkD5-aC2PSWJQCzEsydn2fXsQXZDtDTAb8jQBwXcUrGlyQGvafga9hDRkGd2oIRcrytfVuiN8t_6Vqj6YuizNWf6ZM3-HXnd6q0EfdarnohAl4RpQYKxRjSqTzHDAVkUEb3O3EDyGqNVgSBmoAbR6kSaJv3M4AHHlndOWP_UW6v-uiG7EtkJKUb0EE&ts=1582859524479\",\"http://47.95.31.238/adx/ssp/backNotice?param=2A84AD9A56273B26F0E376C6535B0CA433A0D5811211D1E52EB5EB9AB461AB900F00A86725972E26D4669C054C23655308E9C5E132DA2D8BDBB3EEE4D4DF4A90\"],\"winCloseUrls\":[],\"winDownloadEndUrls\":[],\"winDownloadUrls\":[],\"winInstallEndUrls\":[],\"winInstallOpenUrls\":[],\"winNoticeUrls\":[\"http://47.95.31.238/adx/ssp/backNotice?param=\"]}],\"tracks\":[{\"type\":103,\"urls\":[\"http://dsp-impr2.youdao.com/impplay.s?ext=CiRiMTAyN2RlYi03MTVjLTRkNjItYjE5Zi0yMzA2NDY5Y2EzN2IQvu0SGMrzPyD%2B6vcCKKjq0gswbjoPMTE3LjE1MC4xODkuMTMzQNOyns6ILkgBUgQ4NDM0WiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYmIgNDkwRTUxQzMyNTZDMUQ0OEQyMDhERkUyNTczNzE5NjNqAHIAeACCARA0RkQ4NkM4MUZDOEUyREFBigEPODY2MzMzMDI2OTM5NDk0kAEUmAG2AqIBBU9USEVSqgEOWGlhb21pLE1JIDRMVEW4AfYHwgEkMWM3YWZmNTUtNTA1ZC00YWVmLThjMmQtOTU3Y2U0MzU3MTA00gEFMS4zLjHaAQUxLjMuMQ%3D%3D&event_type=203\"]},{\"type\":104,\"urls\":[\"http://dsp-impr2.youdao.com/impplay.s?ext=CiRiMTAyN2RlYi03MTVjLTRkNjItYjE5Zi0yMzA2NDY5Y2EzN2IQvu0SGMrzPyD%2B6vcCKKjq0gswbjoPMTE3LjE1MC4xODkuMTMzQNOyns6ILkgBUgQ4NDM0WiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYmIgNDkwRTUxQzMyNTZDMUQ0OEQyMDhERkUyNTczNzE5NjNqAHIAeACCARA0RkQ4NkM4MUZDOEUyREFBigEPODY2MzMzMDI2OTM5NDk0kAEUmAG2AqIBBU9USEVSqgEOWGlhb21pLE1JIDRMVEW4AfYHwgEkMWM3YWZmNTUtNTA1ZC00YWVmLThjMmQtOTU3Y2U0MzU3MTA00gEFMS4zLjHaAQUxLjMuMQ%3D%3D&event_type=204\"]},{\"type\":0,\"urls\":[\"http://dsp-impr2.youdao.com/impplay.s?ext=CiRiMTAyN2RlYi03MTVjLTRkNjItYjE5Zi0yMzA2NDY5Y2EzN2IQvu0SGMrzPyD%2B6vcCKKjq0gswbjoPMTE3LjE1MC4xODkuMTMzQNOyns6ILkgBUgQ4NDM0WiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYmIgNDkwRTUxQzMyNTZDMUQ0OEQyMDhERkUyNTczNzE5NjNqAHIAeACCARA0RkQ4NkM4MUZDOEUyREFBigEPODY2MzMzMDI2OTM5NDk0kAEUmAG2AqIBBU9USEVSqgEOWGlhb21pLE1JIDRMVEW4AfYHwgEkMWM3YWZmNTUtNTA1ZC00YWVmLThjMmQtOTU3Y2U0MzU3MTA00gEFMS4zLjHaAQUxLjMuMQ%3D%3D&event_type=205&play_percent=0.0\"]},{\"type\":4,\"urls\":[\"http://dsp-impr2.youdao.com/k.gif?yd_ewp=16620&yd_ext=ErsBCgEwEiAzNzc0YmIzODM5YjRhZWY4ZmFjZTc2ZDFlNmU3YjAzYiKRAQio6tILEP7q9wIYyvM_IL7tEiik3wIwSzhLZQDWgUZwAHgAgAEAmAEBogELVHJhZGl0aW9uYWy6ARJ7Ik9SREVSRURfSUQiOiIxIn3gAdAGgAIAigIgOWUzY2I0YWQzY2M0NjUzYThkYzJjZmQyZGM0NzEyOGaSAgIwMJgCG6ACAKgCALgCAMACAMgCANACtAQwAyIkYjEwMjdkZWItNzE1Yy00ZDYyLWIxOWYtMjMwNjQ2OWNhMzdiKG4wADoAQgBSDzExNy4xNTAuMTg5LjEzM2oNMTU4Mjg1OTUyNDQyOHgAggEAiAHsgQGQAcyyns6ILqgBAbABAbgBAcIBBDg0MzTQAQLaASA0OTBFNTFDMzI1NkMxRDQ4RDIwOERGRTI1NzM3MTk2M-IBQBIOWGlhb21pLE1JIDRMVEUaDzg2NjMzMzAyNjkzOTQ5NCIAKgAyEDRGRDg2QzgxRkM4RTJEQUE6BTEuMy4xQgDoAfYH-gEFMS4zLjGAAqEGigIKdGgwMjAtOTAxM8ICEgkAAAAAAAAAABEAAAAAAADwvw&iid=%7B%225126483666218420970%22%3A4%2C%22-8277771976911226161%22%3A1%7D&sid=21454&tid=38\",\"https://cn.bj.adx.adwangmai.com/displayNotice.api?reqId=96ae664d9f014f9353a36dd7bd7a94d1&v=GwTRPFdQbXtYbkFZygs_RCxvnvN4SXawfDzBH-jUwci02MzNtLJNt2WAMkZ8WkruBJK32l1oUKCmVVQiyHOGeiLVJmRTfOshIwyTR9g73Umrb5HMVsSKK0w0H96x82_blrMWB4frMB0a9FZKdhylrdI1BBR3dSIo0C7kWIEYnSS2MTn2VFdS_ArzB7Lm45um6ZH72Lmn8D8hNpPgXOqe9d90mmiPTlFXXzxGSmxB-BocUAcMnBbsWn37suPi6ZVOYl_Dw7VlOqanllepumDmu-VzM1acMb5JXZfMT2VtPST1u43sAtiI8kEchkg6nxwHpnHkMflEKTZrhJGQuk5wbhi7TYi-jcrZvXsk5YTU5nGz0KzTtw1VeyiRYpHnn2dOkEHAt7dw8SFbKY1tTGSkD5-aC2PSWJQCzEsydn2fXsQXZDtDTAb8jQBwXcUrGlyQGvafga9hDRkGd2oIRcrytfVuiN8t_6Vqj6YuizNWf6ZM3-HXnd6q0EfdarnohAl4RpQYKxRjSqTzHDAVkUEb3O3EDyGqNVgSBmoAbR6kSaJv3M4AHHlndOWP_UW6v-uiG7EtkJKUb0EE&ts=1582859524479\",\"http://47.95.31.238/adx/ssp/backNotice?param=2A84AD9A56273B26F0E376C6535B0CA433A0D5811211D1E52EB5EB9AB461AB900F00A86725972E26D4669C054C236553988723EBE2DBD0B981722642698DFD8C\"]}]}],\"errorCode\":\"200\",\"msg\":\"SUCCESS\",\"requestId\":\"1119092016594257588754\"}\n";
            }
            gar = JSON.parseObject(s,GetAdsResp.class);
            return gar;
        }*/

        String data = formatData(gaReq, gu);
        String url = "http://gorgon.youdao.com/gorgon/request.s?"+data;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        if (code == 200){
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity,"utf-8");
            gar = formatBackData(str, gaReq, gu);
        }else{
            gar = new GetAdsResp();
            gar.setErrorCode("500");
            gar.setMsg("SERVER_ERROR");
        }
        response.close();
        return gar;
    }

    /**
     * 封装入参参数
     */
    public String formatData(GetAdsReq gaReq, GetUpstream gu)throws Exception{
        StringBuffer requestStr = new StringBuffer();
        requestStr.append("id="+gu.getUpstreamId());
        requestStr.append("&av="+gaReq.getApp().getAppVersion());
        int operatorType = gaReq.getNetwork().getOperatorType();
        //网络连接类型
        if (operatorType == 0){
            requestStr.append("&ct="+"0");
        } else if (operatorType == 1 || operatorType == 2 || operatorType == 3){
            requestStr.append("&ct="+"3");
        } else {
            requestStr.append("&ct="+"2");
        }
        //自网络连接类型
        int connectionType = gaReq.getNetwork().getConnectionType();
        if (connectionType == 2){
            requestStr.append("&dct="+"11");
        } else if (connectionType == 3){
            requestStr.append("&dct="+"12");
        } else if (connectionType == 4){
            requestStr.append("&dct="+"13");
        } else {
            requestStr.append("&dct="+"0");
        }
        //1安卓 2ios
        int osType = gaReq.getDevice().getOsType();
        if (osType == 1){
            requestStr.append("&udid="+gaReq.getDevice().getAndroidId());
            requestStr.append("&auidmd5="+MD5.md5(gaReq.getDevice().getAndroidId()));
        }else{
            requestStr.append("&udid="+gaReq.getDevice().getIdfa());
            requestStr.append("&auidmd5="+MD5.md5(gaReq.getDevice().getIdfa()));
        }
        requestStr.append("&imei="+gaReq.getDevice().getImei());
        requestStr.append("&imeimd5="+MD5.md5(gaReq.getDevice().getImei()));
        requestStr.append("&aaid="+"12345");
        requestStr.append("&oaid="+gaReq.getRequestId());
        requestStr.append("&rip="+gaReq.getNetwork().getIp());
        requestStr.append("&ll="+gaReq.getNetwork().getLat()+","+gaReq.getNetwork().getLon());
        requestStr.append("&lla="+"100米/10米");
        requestStr.append("&llt="+"0.001");
        requestStr.append("&llp="+"g");
        requestStr.append("&wifi="+gaReq.getDevice().getMac());
        requestStr.append("&wifi="+gaReq.getDevice().getMac());
        requestStr.append("&dn="+URLEncoder.encode(gaReq.getDevice().getVendor(), "UTF-8")+","+URLEncoder.encode(gaReq.getDevice().getModel(), "UTF-8")+","+URLEncoder.encode(gaReq.getDevice().getBrand(), "UTF-8"));
        //return URLEncoder.encode(requestStr.toString(),"utf-8");
        return requestStr.toString();
    }

    /**
     * 封装回参参数
     */
    public GetAdsResp formatBackData(String backData, GetAdsReq gaReq, GetUpstream gu){
        GetAdsResp gar = new GetAdsResp();
        if (null != backData && !"".equals(backData)){
            JSONObject json = JSONObject.fromObject(backData);

            //广告主体
            Ad ya = new Ad();
            ya.setAdKey(json.getString("creativeid"));
            ya.setHtmlSnippet(json.getString("endcardhtml"));
            //ya.setAdtext();
            //ya.setAdlogo();

            //物料元
            MaterialMeta ym = new MaterialMeta();
            ym.setMaterialWidth(json.getInt("videowidth"));
            ym.setMaterialHeight(json.getInt("videoheight"));
            ym.setAppSize(json.getInt("videosize")/1000000);//(单位byte)
            ym.setVideoUrl(json.getString("videourl"));
            ym.setTotalNum(1);
            ym.setCurrentIndex(1);
            if (json.has("title")){
                ym.setAdTitle(json.getString("title"));
            }
            if (json.has("text")){
                List<String> descs = new ArrayList<>();
                descs.add(json.getString("text"));
                ym.setDescs(descs);
            }
            if (json.has("iconimage")){
                List<String> icon = new ArrayList<>();
                icon.add(json.getString("iconimage"));
                ym.setIconUrls(icon);
            }
            if (json.has("mainimage")){
                List<String> image = new ArrayList<>();
                image.add(json.getString("mainimage"));
                ym.setImageUrl(image);
            }
            ym.setCreativeType(5);
            //广告类型 0落地页  1下载类
            int ydAdType = json.getInt("ydAdType");
            if (ydAdType == 0){
                ym.setInteractionType(1);
            }else if(ydAdType == 1){
                ym.setInteractionType(2);
                ym.setPackageName(json.getString("packageName"));
                ym.setBrandName(json.getString("appName"));
            }else{
                ym.setInteractionType(0);
            }
            ym.setClickUrl(json.getString("clk"));
            String videoduration = json.getString("videoduration");//00:00:13.760
            String [] duration = videoduration.split(":");
            double d = Double.valueOf(duration[2]);
            ym.setVideoDuration(new Double(d).intValue());

            //上报
            //视频加载
            ym.setWinLoadUrls(json.getJSONArray("videoloaded"));


            //曝光展现
            List<String> nL = new ArrayList<>();
            nL.add("http://47.95.31.238/adx/ssp/backNotice?param=");
            ym.setWinNoticeUrls(nL);
            //点击
            List<String> cL = json.getJSONArray("clktrackers");
            String param2 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&16&4");
            cL.add("http://47.95.31.238/adx/ssp/backNotice?param="+param2);
            ym.setWinCNoticeUrls(cL);

            //下载完成
            ym.setWinDownloadEndUrls(json.getJSONArray("apkDownloadTrackers"));
            JSONObject tracks = json.getJSONObject("playtrackers");
            //关闭
            ym.setWinCloseUrls(tracks.getJSONArray("videoclose"));

            //视频进度监控
            List<Track> ydtTrackList = new ArrayList<>();
            //静音
            Track track103 = new Track();
            track103.setType(103);
            track103.setUrls(tracks.getJSONArray("mute"));
            ydtTrackList.add(track103);
            //关闭静音
            Track track104 = new Track();
            track104.setType(104);
            track104.setUrls(tracks.getJSONArray("unmute"));
            ydtTrackList.add(track104);
            //视频进度上报
            JSONArray trackList = tracks.getJSONArray("playpercentage");
            for (int i = 0; i < trackList.size(); i++){
                JSONObject jo = JSONObject.fromObject(trackList.get(i));
                double checkpoint = jo.getDouble("checkpoint");
                if (checkpoint == 0.0){
                    Track track0 = new Track();
                    track0.setType(0);
                    track0.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track0);
                }else if (checkpoint == 0.25){
                    Track track1 = new Track();
                    track1.setType(1);
                    track1.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track1);
                }else if (checkpoint == 0.5){
                    Track track2 = new Track();
                    track2.setType(2);
                    track2.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track2);
                }else if (checkpoint == 0.75){
                    Track track3 = new Track();
                    track3.setType(3);
                    track3.setUrls(jo.getJSONArray("urls"));
                    ydtTrackList.add(track3);
                }else if (checkpoint == 1.0){
                    List<String> urls = jo.getJSONArray("urls");
                    List<String> imptracker = json.getJSONArray("imptracker");
                    for (int j = 0; j < imptracker.size(); j++){
                        urls.add(imptracker.get(j));
                    }
                    String param1 = JiaMi.encrypt(gaReq.getApp().getAppId()+"&"+gaReq.getSlot().getSlotId()+"&"+gu.getUpstreamId()+"&16&3");
                    urls.add("http://47.95.31.238/adx/ssp/backNotice?param="+param1);
                    Track track4 = new Track();
                    track4.setType(4);
                    track4.setUrls(urls);
                    ydtTrackList.add(track4);
                }
            }
            ya.setTracks(ydtTrackList);

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

}
