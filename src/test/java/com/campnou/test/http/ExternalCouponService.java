package com.campnou.test.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.campnou.test.util.FormatUtil;
import com.campnou.test.util.IntimeHttpPostUtil;
import org.junit.Test;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 上午11:15 2018/1/5
 */
public class ExternalCouponService {
    private String from = "xavi";

    /**
     * 新增活动期
     */
    @Test
    public void addAction() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/coupon/genaction";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actionname", "2017-12-07 xavi test");
        jsonObject.put("startdate", "2017-01-01");
        jsonObject.put("enddate", "2017-12-31");
        jsonObject.put("actiontype", "INTIME");
        jsonObject.put("storeno", "MJ01");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~出参~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 修改活动期
     */
    @Test
    public void modAction() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/coupon/modaction";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actionno", "INTIME16768");
        jsonObject.put("actionname", "2017-12-071 xavi test");
        jsonObject.put("startdate", "2017-01-01 10:10:10");
        jsonObject.put("enddate", "2017-12-31 11:11:11");
        jsonObject.put("actiontype", "INTIME");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 新增规则
     */
    @Test
    public void addPayRule() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/coupon/genpayrule";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actionno", "INTIME16768");
        jsonObject.put("categoryid", "11");
        jsonObject.put("paysite", "ON");
        jsonObject.put("paytype", "ALL");
        jsonObject.put("startdate", "2017-07-07 10:10:10");
        jsonObject.put("enddate", "2017-08-08 12:12:12");
        jsonObject.put("startamount", "200");
        jsonObject.put("endamount", "1000");
        jsonObject.put("rulememo", "测试");
        jsonObject.put("paymenta", "EO");
        jsonObject.put("paymentb", "E04");
        jsonObject.put("yhstoreno", "MJ01,MJ02");
        jsonObject.put("mutex", "Y");
        jsonObject.put("storeno", "MJ01");
        jsonObject.put("orderlimit", "10");
        jsonObject.put("oper", "beckham");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 修改规则
     */
    @Test
    public void modPayRule() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/coupon/modpayrule";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payrule", "16889");
        jsonObject.put("actionno", "INTIME16768");
        jsonObject.put("categoryid", "11");
        jsonObject.put("paysite", "ON");
        jsonObject.put("paytype", "ALL");
        jsonObject.put("startdate", "2017-07-07 10:10:10");
        jsonObject.put("enddate", "2017-08-09 12:12:12");
        jsonObject.put("startamount", "200");
        jsonObject.put("endamount", "1000");
        jsonObject.put("rulememo", "测试");
        jsonObject.put("paymenta", "EO");
        jsonObject.put("paymentb", "E04");
        jsonObject.put("yhstoreno", "MJ03,MJ02");
        jsonObject.put("mutex", "Y");
        jsonObject.put("storeno", "MJ01");
        jsonObject.put("orderlimit", "10");
        jsonObject.put("oper", "beckham");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 设置券范围
     */
    @Test
    public void updateUsableRange() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/external/updateusablerange";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payrule", "16889");
        jsonObject.put("actionno", "INTIME16768");
        jsonObject.put("storeno", "MJ01");
        jsonObject.put("degree", "4");


        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("storeno", "HZ01");
        jsonObject1.put("deptcode", "11");
        jsonObject1.put("countercode", "00005");
        jsonObject1.put("floorcode", "001");
        jsonObject1.put("comcode", "690396655032");


        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("storeno", "HZ01");
        jsonObject2.put("deptcode", "11");
        jsonObject2.put("countercode", "00012");
        jsonObject2.put("floorcode", "001");
        jsonObject2.put("comcode", "200000116211");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonObject.put("commod", jsonArray);

        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }
}
