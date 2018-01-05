package com.campnou.test.csb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.campnou.test.util.CsbPostUtil;
import com.campnou.test.util.FormatUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSON;

public class MpsService extends BaseService {
    /**
     * 券列表查询
     */
    @Test
    public void queryByAcount() {
        Map<String, String> params = Maps.newHashMap();
        Map<String, Object> condition = Maps.newHashMap();
        params.put("pageNum", "1");
        params.put("pageSize", "1000");
//      List<String> list = Lists.newArrayList("Y", "X");
        List<String> list = Lists.newArrayList();
        condition.put("account", "17245211");
        condition.put("bizStatus", list);
        condition.put("isContainMj", "false");
        JSON conditionJson = (JSON) toJSON(condition);
        params.put("GetListConditionDO", conditionJson.toString());
        System.out.println(params);
        String apiName = "com.intime.mps.client.service.CouponService.getListByAccount";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, test)));
    }

    /**
     * 券详情查询
     */
    @Test
    public void queryByCouponCode() {
        Map<String, String> params = new HashMap<String, String>();
        JSONObject json = new JSONObject();
        json.put("couponCode", "65788603634852097");//券号
        params.put("CouponDO", json.toString());
        String apiName = "com.intime.mps.client.service.getOne";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, product)));
    }

    /**
     * 发券
     *
     * @throws ParseException
     */
    @Test
    public void insertCoupon() throws ParseException {
        Map<String, String> params = Maps.newHashMap();
        Map<String, Object> coupon = Maps.newHashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate = String.valueOf(String.valueOf(sdf.parse("2017-04-02 09:09:09").getTime()));
        String endDate = String.valueOf(String.valueOf(sdf.parse("2017-04-02 10:10:10").getTime()));
        coupon.put("payRule", "16889");
        coupon.put("couponName", "xavi test");
        coupon.put("couponDesc", "xavi test");
        coupon.put("amount", "1");
        coupon.put("startDate", startDate);
        coupon.put("endDate", endDate);
        coupon.put("account", "1");
        coupon.put("channel", "8");
        params.put("couponDO", toJSON(coupon).toString());
        String apiName = "com.intime.mps.client.service.CouponService.insert";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.csbPost(apiName, params, product)));
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(1515272400000L)));
    }
}
