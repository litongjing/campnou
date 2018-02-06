package com.campnou.test.http;

import com.alibaba.fastjson.JSONObject;
import com.campnou.test.util.FormatUtil;
import com.campnou.test.util.IntimeHttpPostUtil;
import org.junit.Test;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 下午4:05 2018/1/9
 */
public class IntimeNetRsService {
    private String from = "xavi";

    /**
     * 查询卡余额  出参金额为元
     */
    @Test
    public void queryCardYe() {
//        String urlStr="http://122.224.218.141:8075/intimenet-rs/api/net/queryCurAmt";
        String urlStr = "http://122.224.218.140:9090/intimenet-rs/api/net/queryCurAmt";
//        String urlStr="http://122.224.218.139:1810/intimenet-rs/api/net/queryCurAmt";
        String privateKey = "intime996";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pathb", "9000001983308=20754");//二轨码
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 消费
     */
    @Test
    public void consume() {
        String urlStr = "http://122.224.218.140:9090/intimenet-rs/api/net/posConsume";
//        String urlStr = "http://localhost:8081/intimenet-rs/api/net/posConsume";
//        String urlStr="http://192.168.123.76:8080/intimenet-rs/api/net/posConsume";
        String privateKey = "intime996";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "01");//01 消费
        jsonObject.put("pathb", "9000100102003773489=99122013410000000");//二轨码
        jsonObject.put("jycode", "3489");//校验码 等号前4位
        jsonObject.put("je", "100");//消费金额 单位：分
        jsonObject.put("authnum", "1234");//授权码  必填 只存
        jsonObject.put("xph", "1234567");//小票号 varchar2(12)
        jsonObject.put("syyh", "007");//收银员号 只存
        jsonObject.put("seqno", "xavi6565656565663");//交易序号 需要唯一
        //jsonObject.put("jylsh", "121212123231");//交易流水号，退货时必传 消费时的seqno
        jsonObject.put("zdh", "112");//终端号 和 商户号匹配
        jsonObject.put("shh", "HZ01");//商户号 和 终端号要匹配
        jsonObject.put("shname", "武林商户");//商户名称 无用
        jsonObject.put("rqsj", "20180109131313");//交易日期时间
        // jsonObject.put("memo", "xavi 测试");//备注 无用
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }

    /**
     * 储值卡退货
     */
    @Test
    public void refund() {
        String urlStr = "http://122.224.218.140:9090/intimenet-rs/api/net/posConsume";
//        String urlStr = "http://localhost:8081/intimenet-rs/api/net/posConsume";
//        String urlStr="http://192.168.123.76:8080/intimenet-rs/api/net/posConsume";
        String privateKey = "intime996";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "02");//02 退货
        jsonObject.put("pathb", "9000100102003773489=99122013410000000");//二轨码
        jsonObject.put("jycode", "3489");//校验码 等号前4位
//        jsonObject.put("je", "10");//消费金额 单位：分
        jsonObject.put("authnum", "1234");//授权码  不能为空 只存
        jsonObject.put("xph", "1234567");//小票号 varchar2(12)
        jsonObject.put("syyh", "007");//收银员号 只存
        jsonObject.put("seqno", "xavi6565656565661");//交易序号 需要唯一
        jsonObject.put("jylsh", "xavi6565656565659");//交易流水号，退货时必传 消费时的seqno
        jsonObject.put("zdh", "112");//终端号 和 商户号匹配
        jsonObject.put("shh", "HZ01");//商户号 和 终端号要匹配
        jsonObject.put("shname", "武林商户");//商户名称 无用
        jsonObject.put("rqsj", "20180109131313");//交易日期时间
        // jsonObject.put("memo", "xavi 测试");//备注 无用
        String result = IntimeHttpPostUtil.send(privateKey, urlStr, from, jsonObject);
        System.out.println("====================================出参====================================");
        System.out.println(FormatUtil.formatJson(result));
    }
}
