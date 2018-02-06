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
    private String from = "4033639";

    /**
     * 新增活动期
     */
    @Test
    public void addAction() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/coupon/genaction";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actionname", "2018-02-06 xavi test");
        jsonObject.put("startdate", "2018-01-01");
        jsonObject.put("enddate", "2018-12-31");
        jsonObject.put("actiontype", "INTIME");
        jsonObject.put("storeno", "MJ01");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
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
        jsonObject.put("actionno", "INTIME16916");
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
        jsonObject.put("actionno", "INTIME16916");
        jsonObject.put("categoryid", "11");
        jsonObject.put("paysite", "ON");
        jsonObject.put("paytype", "ALL");
        jsonObject.put("startdate", "2018-01-01 00:00:00");
        jsonObject.put("enddate", "2018-12-30 23:59:59");
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
        jsonObject.put("useType", "0");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 修改规则
     */
    @Test
    public void modPayRule() {
        String urlStr = "http://localhost:8081/externalcoupon/api/coupon/modpayrule";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payrule", "17042");
        jsonObject.put("actionno", "INTIME16809");
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
        jsonObject.put("payrule", "16926");
        jsonObject.put("actionno", "INTIME16809");
        jsonObject.put("storeno", "GROUP");
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

    /**
     * 线下批量生成白条券
     */
    @Test
    public void batchCreateBlankCoupon() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/offline/batchCreateBlankCoupon";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("count", "1000");
        jsonObject.put("count", 100000);
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 原单退反核销
     */
    @Test
    public void reverseConsume() {
        String urlStr = "http://192.168.130.7:9090/externalcoupon/api/coupon/reverseconsume";
        String privateKey = "intime996";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", "50.00");
        jsonObject.put("reversedate", "2018-01-25 14:59:31");
        jsonObject.put("posno", "666");
        jsonObject.put("posseq", "65996660003");
        jsonObject.put("paystore", "HZ10");
        jsonObject.put("couponcode", "6424550671018415");
        jsonObject.put("operator", "0000");
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    @Test
    public void consume() {
        //17045
        String urlStr = "http://192.168.130.7:9090/couponcenter/api/coupon/consume";
        String privateKey = "intime$4r#sE";
        JSONObject jsonObject = new JSONObject();
        //type
        JSONObject typeJson = new JSONObject();
        typeJson.put("HDCODE", "WD-WMAMAC244425");
        typeJson.put("POSNO", "995");
        typeJson.put("OPERTYPE", "COUPONCODE");
        typeJson.put("OPERATOR", "0000");
        typeJson.put("SYSDATE", "2014-05-21");
        typeJson.put("COUPONCODE", "123456");
        typeJson.put("PAYTYPE", "0");
        typeJson.put("PAYTIME", "18:16:25");
        typeJson.put("PAYMENT", "COUPON");
        typeJson.put("PAYDATE", "2014-05-21");
        typeJson.put("AMOUNT", "");
        typeJson.put("PASSWORD", "");
        typeJson.put("SEQNO", "52549950001");
        typeJson.put("MACADDR", "00-11-25-C1-C5-EC");
        typeJson.put("PAYSTORE", "HZ06");
        typeJson.put("IPADDR", "192.168.121.30");
        typeJson.put("MSGCODE", "");
        //operdata
        JSONArray operdataJSONArray = new JSONArray();

        JSONObject data1 = new JSONObject();
        data1.put("PRICE", "1000");
        data1.put("MUTEXSTR", "");
        data1.put("COMCODE", "1021");
        data1.put("ACTUALAMOUNT", "1000");
        data1.put("AMOUNT", "1000");
        data1.put("DISCOUNT", "0");
        data1.put("COUNTERCODE", "00160");
        data1.put("COUNTS", "1");
        data1.put("ROWNO", "1");

        JSONObject data2 = new JSONObject();
        data2.put("PRICE", "1000");
        data2.put("MUTEXSTR", "");
        data2.put("COMCODE", "1021");
        data2.put("ACTUALAMOUNT", "1000");
        data2.put("AMOUNT", "1000");
        data2.put("DISCOUNT", "0");
        data2.put("COUNTERCODE", "00160");
        data2.put("COUNTS", "1");
        data2.put("ROWNO", "1");

        operdataJSONArray.add(data2);
        operdataJSONArray.add(data1);
        jsonObject.put("operdata", operdataJSONArray);
        jsonObject.put("type", typeJson);


        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }
}
