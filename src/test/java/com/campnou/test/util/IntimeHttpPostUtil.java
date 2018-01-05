package com.campnou.test.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 上午10:43 2018/1/5
 */
public class IntimeHttpPostUtil {
    /**
     * 调用银泰接口
     *
     * @param urlStr
     * @param from
     * @param data
     * @return
     */
    public static String send(String privateKey, String urlStr, String from, JSONObject data) {
        JSONObject postJson = new JSONObject();
        int nonce = new Random().nextInt(1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        postJson.put("from", from);
        postJson.put("nonce", String.valueOf(nonce));
        postJson.put("timestamp", now);
        postJson.put("data", data);
        String sign = "";
        try {
            sign = genSign(postJson, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        postJson.put("sign", sign);
        return sendRequest(urlStr, postJson.toString(), "POST");
    }

    /**
     * 发送http请求
     *
     * @param urlStr
     * @param paramsStr
     * @param sendType
     * @return
     */
    public static String sendRequest(String urlStr, String paramsStr, String sendType) {
        String result = "";
        HttpURLConnection connection = null;
        System.out.println("====================================入参====================================");
        System.out.println(FormatUtil.formatJson(paramsStr));
        try {
            if (sendType == null || sendType.isEmpty()) {
                sendType = "POST";
            }
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(sendType);
            connection.setConnectTimeout(1000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.getOutputStream().write(paramsStr.getBytes("UTF-8"));
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String tempLine = reader.readLine();
            StringBuilder buider = new StringBuilder();
            while (tempLine != null) {
                buider.append(tempLine);
                tempLine = reader.readLine();
            }
            result = buider.toString();
            reader.close();
            in.close();
        } catch (SocketTimeoutException e) {
            System.out.println("对应接口连接失败");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("接口地址不存在，请检查");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    /**
     * 生成密钥
     *
     * @param jsonObject
     * @return
     */
    public static String genSign(JSONObject jsonObject, String privateKey) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("pk", jsonObject.getString("from"));
        map.put("utc", jsonObject.getString("timestamp"));
        map.put("ran", jsonObject.getString("nonce"));
        String[] array = map.values().toArray(new String[0]);
        Arrays.sort(array);
        StringBuilder builder = new StringBuilder();
        for (String string : array) {
            builder.append(string);
        }
        String str = builder.toString();
        str += jsonObject.getString("data");
        return encode(str, privateKey);
    }

    /**
     * 加密
     *
     * @param content
     * @param seed
     * @return
     * @throws Exception
     */
    public static String encode(String content, Object seed) throws Exception {
        String macKey = seed.toString();
        String macData = content;
//        System.out.println("MACDATA:" + macData);

        Mac mac = Mac.getInstance("HMACSHA1");
        byte[] secretByte = macKey.getBytes("UTF-8");
        byte[] dataBytes = macData.getBytes("UTF-8");
        SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA1");

        mac.init(secret);
        byte[] doFinal = mac.doFinal(dataBytes);
        String checksum = Base64.encodeBase64String(doFinal);
        return checksum;
    }
}
