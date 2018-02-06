package com.campnou.test.csb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.campnou.test.util.CsbPostUtil;
import com.campnou.test.util.FormatUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 上午10:51 2018/1/4
 */
public class MatrixService extends BaseService {
    /**
     * 查询适用储值卡余额的商品
     */
    @Test
    public void getCardYeCommod() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        json1.put("comCode", "020714761219");
        json1.put("storeNo", "HZ01");
        json2.put("comCode", "123456789000");
        json2.put("storeNo", "HZ01");
        jsonArray.add(json1);
        jsonArray.add(json2);
        Map<String, String> params = Maps.newHashMap();
        params.put("data", jsonArray.toString());
        String apiName = "com.intime.matrix.client.service.CommodService.getCardYeVipCommodList";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, pre)));
    }

    /**
     * 查询参与365折扣的专柜列表
     */
    @Test
    public void getVipCommodCounterList() {
        Map<String, String> params = Maps.newHashMap();
        params.put("storeNo", "HZ01");
        String apiName = "com.intime.matrix.client.service.CommodService.getVIPPlusCommodityCounterList";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, pre)));
    }

    /**
     * 得到所有商品位置信息
     */
    @Test
    public void getCommodAllLocation() {
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("code", "080711");
        Map<String, String> map2 = Maps.newHashMap();
        map2.put("code", "200000011129");
        List<Map<String, String>> commodDOList = Lists.newArrayList(map1, map2);
        JSON conditionJson = (JSON) toJSON(commodDOList);
        Map<String, String> params = Maps.newHashMap();
        params.put("comCodeList", conditionJson.toString());
        String apiName = "com.intime.matrix.client.service.CommodService.getCommodityAllLocations";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, product)));
    }

    /**
     * 所有参加365门店专柜数
     */
    @Test
    public void getVipPlusStoreAndCounters() {
        Map<String, String> params = Maps.newHashMap();
        String apiName = "com.intime.matrix.client.service.CommodService.getVIPPlusStoreAndCounters";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, pre)));
    }

    /**
     * 查询商品位置信息列表
     */
    @Test
    public void getCommodityLocationList() {
        String data = "[{\"code\":\"020714000318\",\"storeno\":\"HZ01\"},{\"code\":\"207773\",\"storeno\":\"E01\"}," +
                "{\"code\":\"200000011129\",\"storeno\":\"HZ01\"}]\n";
        Map<String, String> params = Maps.newHashMap();
        params.put("commodDOList", data);
        String apiName = "com.intime.matrix.client.service.CommodService.getCommodityLocationList";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, product)));
    }

    /**
     * 查询商品折扣信息
     */
    @Test
    public void getVipPlusDiscount() {
        Map<String, String> params = Maps.newHashMap();
        Map<String, Object> vipPlusDiscount = Maps.newHashMap();
        Map<String, Object> commodity1 = Maps.newHashMap();
        commodity1.put("code", "200000127971");
        commodity1.put("storeNo", "HZ01");
        commodity1.put("rowNo", "01");
        commodity1.put("actualAmount", "645");
        Map<String, Object> commodity2 = Maps.newHashMap();
        commodity2.put("code", "200000500925");
        commodity2.put("storeNo", "HZ01");
        commodity2.put("rowNo", "01");
        commodity2.put("actualAmount", "133");
        Map<String, Object> commodity3 = Maps.newHashMap();
        commodity3.put("code", "3097");
        commodity3.put("storeNo", "HZ10");
        commodity3.put("rowNo", "01");
        commodity3.put("actualAmount", "415");
        Map<String, Object> commodity4 = Maps.newHashMap();
        commodity4.put("code", "30971");
        commodity4.put("storeNo", "HZ10");
        commodity4.put("rowNo", "01");
        commodity4.put("actualAmount", "321");
        List<Map<String, Object>> commodityList = Lists.newArrayList(commodity1, commodity2, commodity3, commodity4);
        vipPlusDiscount.put("commodityList", commodityList);
        vipPlusDiscount.put("couponAmount", "0");
        vipPlusDiscount.put("salesType", "0");
        vipPlusDiscount.put("vipType", "3");
        vipPlusDiscount.put("paySite", "MJ");
        vipPlusDiscount.put("account", "12");
        params.put("vipPlusDiscount", toJSON(vipPlusDiscount).toString());
        String apiName = "com.intime.matrix.client.service.CommodService.getVIPPlusDiscount";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, product)));
    }

    /**
     * 查询商品参加365折扣信息
     */
    @Test
    public void getVIPPlusCommodityList() {
        String data = "[{\"actualAmount\":160,\"code\":\"020714000318\",\"storeNo\":\"HZ01\"}," +
                "{\"actualAmount\":-40,\"code\":\"207773\",\"storeNo\":\"E01\"}," +
                "{\"actualAmount\":-120,\"code\":\"200000011129\",\"storeNo\":\"HZ01\"}]\n";
        Map<String, String> params = Maps.newHashMap();
        params.put("commodityDOS", data);
        System.out.println(params);
        String apiName = "com.intime.matrix.client.service.CommodService.getVIPPlusCommodityList";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, product)));
    }
}
