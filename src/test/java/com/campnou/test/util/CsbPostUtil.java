package com.campnou.test.util;

import com.alibaba.csb.sdk.HttpCaller;
import com.alibaba.csb.sdk.HttpCallerException;

import java.util.Map;

public class CsbPostUtil {
    private static String ak = "";
    private static String sk = "";
    private static String requestUrl = "";
    private static String version = "";

    public static String csbPost(String apiName, Map<String, String> params, String env) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~当前env：" + env + "~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (env.equals("test")) {
            ak = "d826991603d649cba00bdb759d9c6015";
            sk = "kqrp02df0hGSNaGW8+F7OOAv0m0=";
            requestUrl = "http://101.37.127.104:80/csb";
            version = "1.0.0";
        }
        if (env.equals("pre")) {
            ak = "5b3e81ae312a442f8a0accd128b84051";
            sk = "mUNqJBXzB+b62oMlj3Dv1S/rM0g=";
            requestUrl = "http://101.37.178.69/csb";
            version = "1.0.0";
        }
        if (env.equals("product")) {
            ak = "5ac45539552146e5831924ce23f30157";
            sk = "QlBHjIwnK0kAf5oee67E/cjAq2U=";
            requestUrl = "http://csb.yintai.com/csb";
            version = "1.0.0";
        }
        try {
            return doCsbPost(apiName, params);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    private static String doCsbPost(String requestUrl, String apiName, String version, Map<String, String> params,
                                    String ak, String sk) throws HttpCallerException {
        String result = HttpCaller.doPost(requestUrl, apiName, version, params, ak, sk);
        return HttpCaller.changeCharset(result);
    }

    protected static String doCsbPost(String apiName, Map<String, String> params) throws HttpCallerException {
        return doCsbPost(requestUrl, apiName, version, params, ak, sk);
    }
}
