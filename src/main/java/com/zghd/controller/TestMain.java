package com.zghd.controller;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * 测试
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        long st = System.currentTimeMillis();
        TestMain t = new TestMain();
        t.test();
        long et = System.currentTimeMillis();
        System.out.println(et - st);
    }

    String xzUrl = "http://47.95.31.238/adx/ssp/dspAdTest";
    String hcUrl = "http://47.95.31.238/adx/ssp/dspAdTest";
    String ptUrl = "http://47.95.31.238/adx/ssp/dspAdTest";
    String data = "{\"requestId\":\"e3d91fc8-51af-4895-bbbd-c076fb6266d3\",\"app\":{\"appId\":\"ZG08HD\",\"appPackage\":\"com.test.test\",\"appVersion\":\"1.7.1\",\"appName\":\"Go手机助手\"},\"slot\":{\"slotId\":\"test1030\",\"slotwidth\":740,\"slotheight\":340,\"adtype\":5},\"device\":{\"idfa\":\"\",\"imei\":\"866341036297480\",\"mac\":\"02:00:00:00:00:00\",\"androidId\":\"5c5ce60126e0e9a9\",\"oaid\":\"\",\"osType\":1,\"osVersion\":\"7.1.1\",\"deviceType\":1,\"vendor\":\"smartisan\",\"brand\":\"SMARTISAN\",\"model\":\"OS105\",\"screenWidth\":1080,\"screenHeight\":2070,\"ua\":\"Mozilla/5.0 (Linux; Android 7.1.1; OS105 Build/NGI77B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36\",\"ppi\":400,\"screenOrientation\":1,\"imsi\":\"460007140377626\"},\"network\":{\"ip\":\"117.136.40.169\",\"operatorType\":1,\"connectionType\":4,\"lat\":0,\"lon\":0,\"cellular_id\":\"225200194\"}}";

    public LinkedList<JSONObject> test() throws JSONException, IOException, InterruptedException, ExecutionException {
        //组合线程请求参数
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> m1 = new HashMap<>();
        m1.put("url", xzUrl);
        m1.put("data", data);
        list.add(m1);

        /*Map<String, String> m2 = new HashMap<>();
        m2.put("url", hcUrl);
        m2.put("data", data);
        list.add(m2);

        Map<String, String> m3 = new HashMap<>();
        m3.put("url", ptUrl);
        m3.put("data", data);
        list.add(m3);*/

        //申明线程池
        ExecutorService exc = Executors.newFixedThreadPool(list.size());
        //申明数据回调处理类List<Future<JSONObject>>
        List<Future<Map<String, String>>> futures = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);
            //申请单个线程执行类
            HttpUtilRequest call = new HttpUtilRequest(map);
            //提交单个线程
            Future<Map<String, String>> future = exc.submit(call);
            //将每个线程放入线程集合， 这里如果任何一个线程的执行结果没有回调，线程都会自动堵塞
            futures.add(future);

        }


        //所有线程执行完毕之后会执行下面的循环，然后通过循环每个个线程后执行线程的get()方法每个线程执行的结果
        for (Future<Map<String, String>> future : futures) {
            Map<String, String> json = future.get();
            if (json.get("back") != null){
                System.out.println(json.get("back"));
                /*for (Future<Map<String, String>> f : futures) {
                    f.cancel(true);
                }*/
                //关闭线程池
                exc.shutdown();
            }
        }
        return null;
    }

    public class HttpUtilRequest implements Callable<Map<String, String>> {

        Map<String, String> map;

        public HttpUtilRequest(Map<String, String> map) throws IOException {
            this.map = map;
            //发送http请求
            String back = httpPostRequest(this.map);
            this.map.put("back",back);
        }

        //数据回调
        public Map<String, String> call() throws Exception {
            return this.map;
        }
    }

    //http请求工具
    public String httpPostRequest(Map<String, String> map) throws IOException {
        String url = map.get("url");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        method.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(map.get("data"));
        method.setEntity(entity);
        HttpResponse result = httpClient.execute(method);
        String str = EntityUtils.toString(result.getEntity(), "utf-8");
        return str;
    }
}