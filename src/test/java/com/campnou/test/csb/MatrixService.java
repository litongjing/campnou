package com.campnou.test.csb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.campnou.test.util.CsbPostUtil;
import com.campnou.test.util.FormatUtil;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 上午10:51 2018/1/4
 */
public class MatrixService extends BaseService{
    @Test
    public void recordApportion(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("storeNo","HZ01");
        jsonObject.put("orderNo","201711111111111111");
        jsonObject.put("seqNo","10000000000");
        jsonObject.put("operDate",new Date().getTime());
        jsonObject.put("comCode","7777777");
        jsonObject.put("counterCode","666666");
        jsonObject.put("saleAmount","20");
        jsonObject.put("couponAmount","10");
        jsonObject.put("flag","2");
        jsonObject.put("requestId","123456");
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("storeNo","HZ01");
        jsonObject1.put("orderNo","201711111111111111");
        jsonObject1.put("seqNo","10000000000");
        jsonObject1.put("operDate",new Date().getTime());
        jsonObject1.put("comCode","7777777");
        jsonObject1.put("counterCode","666666");
        jsonObject1.put("saleAmount","20");
        jsonObject1.put("couponAmount","10");
        jsonObject1.put("flag","2");
        jsonObject1.put("requestId","122456");
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);
        Map<String, String> params = Maps.newHashMap();
        params.put("data", jsonArray.toString());
        String apiName = "com.intime.mps.client.service.calculateAmountCenter.InvCouponService.recordApportion";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName,params,product)));
    }
}
