package com.imc.test;

import com.alibaba.fastjson.JSONObject;
import com.imc.util.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

public class TestHttpClient {
    public static void main(String[] args) {
        websitejson();

    }

    public static void websitejson() {

        JSONObject obj = new JSONObject();

        JSONObject device = new JSONObject();
        device.put("deviceId", "1349873D434");
        device.put("deviceMake", "Samsung");
        device.put("deviceModel", "I9500");
        device.put("sysType", "Android");
        device.put("sysVersion", "6.0");

        obj.put("appVersion", "1");
        obj.put("device", device);
        obj.put("lang", "en");
        obj.put("sign", "C7A7");
        obj.put("time", "1232435435");
        obj.put("token", "C7A7");

        JSONObject jsonStr = new JSONObject();
        jsonStr.put("orderId", "CRM34976329445447120");
        jsonStr.put("exhibitionId", 2759);
        jsonStr.put("companyId", 50156);
        jsonStr.put("companyName", "内蒙古鼎业食品有限公司");
        jsonStr.put("companyNameEn", "INNER MONGOLIA DING YE FOODSTUFF CO., LTD.");
        jsonStr.put("lintelInfo", "12123");
        jsonStr.put("contactPersionId", 10577);
        jsonStr.put("contactPersionName", "陈建伟");
        jsonStr.put("contactPosition", "外贸经理");
        jsonStr.put("tel", "15804782835");
        jsonStr.put("landline", "04787866113");
        jsonStr.put("qq", "1376137718");
        jsonStr.put("wechat", null);
        jsonStr.put("taxNumber", null);
        jsonStr.put("note", null);
        jsonStr.put("isFreight", null);

        List arr = new ArrayList();
        JSONObject arrObj1 = new JSONObject();
        arrObj1.put("companyId", 1231);
        arrObj1.put("contactPersionName", "name1");
        arr.add(arrObj1);

        JSONObject arrObj2 = new JSONObject();
        arrObj2.put("companyId", 1232);
        arrObj2.put("contactPersionName", "name2");
        arr.add(arrObj2);
        jsonStr.put("companyContactInfos", arr);

        JSONObject data = new JSONObject();
        data.put("jsonStr", jsonStr);
        data.put("url", "");

        obj.put("data", data);

        try {
//            jsonPost("http://10.21.64.225:8086/meorient/getData/contacterEnterpriseInfo",obj, null);
            JSONObject result = HttpClientUtils.jsonPost("http://10.21.64.225/meorientb2b-apigateway-supplier/esm/exhibitorWebsite.json", obj, null, false);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
