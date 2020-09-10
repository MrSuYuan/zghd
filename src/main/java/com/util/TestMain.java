package com.util;

import com.zghd.entity.JuKanDian.response.ArticleBack;
import net.sf.json.JSONObject;

public class TestMain {

    public static void main(String[] args) {
/*        User user = new User();
        user.setAppkey("zhongguanhudong");
        user.setAppid("108");
        user.setUserid("user001");
        user.setReward(200);
        user.setUnit("金币");
        user.setOs("Android");
        user.setOsversion("8.1.1");
        user.setMobileboard("huawei");
        user.setMobilemodel("huawei");
        System.out.println(JSONObject.fromObject(user).toString());

        ArticleList al = new ArticleList();
        al.setAppkey("zhongguanhudong");
        al.setAppid("108");
        al.setToken("bc07ca1282404ce8925117156371c6e0");
        al.setPageno(0);
        System.out.println(JSONObject.fromObject(al).toString());

        ArticleDetail ad = new ArticleDetail();
        ad.setAppkey("zhongguanhudong");
        ad.setAppid("108");
        ad.setToken("bc07ca1282404ce8925117156371c6e0");
        ad.setArtClassify("0");
        ad.setArtId("7");
        ad.setCtxData("%7B%22classify%22%3A%220%22%2C%22artimg%22%3A%22https%3A%2F%2Fstatic.1sapp.com%2Fqupost%2Fimages%2F2020%2F09%2F02%2F1599041769229143220.jpg%22%2C%22itemtype%22%3A%220%22%2C%22artsource%22%3A%22zc%22%2C%22typeid%22%3A%227%22%2C%22title%22%3A%22%E5%93%A5%E5%93%A5%E4%B8%8B%E7%8F%AD%E5%9B%9E%E5%AE%B6%EF%BC%8C%E5%A6%B9%E5%A6%B9%E2%80%9C%E5%B0%AC%E8%88%9E%E2%80%9D%E7%9B%B8%E8%BF%8E%EF%BC%8C%E5%93%A5%E5%93%A5%E6%89%AD%E5%8A%A8%E8%BA%AB%E5%AD%90%E9%82%A3%E4%B8%80%E5%88%BB%E7%BD%91%E5%8F%8B%E7%AC%91%E7%BF%BB%22%2C%22scene%22%3A%22list%22%7D");
        System.out.println(JSONObject.fromObject(ad).toString());

        ArticleShare as = new ArticleShare();
        as.setAppkey("zhongguanhudong");
        as.setAppid("108");
        as.setToken("bc07ca1282404ce8925117156371c6e0");
        as.setShareArtId("7");
        as.setShareExtra("%7B%22classify%22%3A%220%22%2C%22artimg%22%3A%22https%3A%2F%2Fstatic.1sapp.com%2Fqupost%2Fimages%2F2020%2F09%2F02%2F1599041769229143220.jpg%22%2C%22itemtype%22%3A%220%22%2C%22artsource%22%3A%22zc%22%2C%22typeid%22%3A%227%22%2C%22title%22%3A%22%E5%93%A5%E5%93%A5%E4%B8%8B%E7%8F%AD%E5%9B%9E%E5%AE%B6%EF%BC%8C%E5%A6%B9%E5%A6%B9%E2%80%9C%E5%B0%AC%E8%88%9E%E2%80%9D%E7%9B%B8%E8%BF%8E%EF%BC%8C%E5%93%A5%E5%93%A5%E6%89%AD%E5%8A%A8%E8%BA%AB%E5%AD%90%E9%82%A3%E4%B8%80%E5%88%BB%E7%BD%91%E5%8F%8B%E7%AC%91%E7%BF%BB%22%2C%22scene%22%3A%22list%22%7D");
        as.setShareTarget("timegroup");
        as.setShareType("0");
        as.setCtxData("%7B%22classify%22%3A%220%22%2C%22artimg%22%3A%22https%3A%2F%2Fstatic.1sapp.com%2Fqupost%2Fimages%2F2020%2F09%2F02%2F1599041769229143220.jpg%22%2C%22itemtype%22%3A%220%22%2C%22artsource%22%3A%22zc%22%2C%22typeid%22%3A%227%22%2C%22title%22%3A%22%E5%93%A5%E5%93%A5%E4%B8%8B%E7%8F%AD%E5%9B%9E%E5%AE%B6%EF%BC%8C%E5%A6%B9%E5%A6%B9%E2%80%9C%E5%B0%AC%E8%88%9E%E2%80%9D%E7%9B%B8%E8%BF%8E%EF%BC%8C%E5%93%A5%E5%93%A5%E6%89%AD%E5%8A%A8%E8%BA%AB%E5%AD%90%E9%82%A3%E4%B8%80%E5%88%BB%E7%BD%91%E5%8F%8B%E7%AC%91%E7%BF%BB%22%2C%22scene%22%3A%22list%22%7D");
        System.out.println(JSONObject.fromObject(as).toString());*/
        ArticleBack ab = new ArticleBack();
        ab.setAppkey("zhongguanhudong");
        ab.setAppid("108");
        ab.setAid("aid1");
        ab.setUserid("user001");
        ab.setTitle("这是标题");
        ab.setSharekey("分享key123");
        ab.setReward(200);
        ab.setCreatetime("时间啊");
        System.out.println(JSONObject.fromObject(ab).toString());

    }
}
