package com.imc.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Http访问工具类。
 */
public class HttpClientUtils {

    static {
        System.setProperty("sun.net.client.defaultConnectTimeout", "50000");
        System.setProperty("sun.net.client.defaultReadTimeout", "50000");
    }

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * jsonPost
     *
     * @param url    路径
     * @param params 参数
     * @return
     */
    public static JSONObject jsonPost(String url, Map<String, String> params, Map<String, String> headersMap) {
        Map<String, Object> dataMap = new HashMap<>();
        if(null!=params && params.size()>0){
            for (String key : params.keySet()) {
                dataMap.put(key, params.get(key));
            }
        }
        return jsonPost(url, new JSONObject(dataMap), headersMap, false);
    }

    /**
     * jsonPost
     *
     * @param url    路径
     * @param params 参数
     * @return JSONArray
     */
    public static JSONArray jsonPostArray(String url, Map<String, String> params) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : params.keySet()) {
            dataMap.put(key, params.get(key));
        }

        return jsonPostArray(url, new JSONObject(dataMap), false);
    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @param noNeedResponse 不需要返回结果
     * @return
     */
    public static JSONArray jsonPostArray(String url, JSONObject jsonParam, boolean noNeedResponse) {
        //post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            return buildResultArray(url, result, noNeedResponse);
        } catch (IOException e) {
            logger.error("通信异常:" + url, e);
        }
        return null;
    }

    private static JSONArray buildResultArray(String url, HttpResponse result, boolean noNeedResponse) {
        int statusCode = result.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            logger.error("通信异常:code=" + statusCode + "," + url + "," + result.getStatusLine());
            throw new RuntimeException("通信异常");
        }

        /**读取服务器返回过来的json字符串数据**/
        String str;
        try {
            str = EntityUtils.toString(result.getEntity());
        } catch (IOException e) {
            logger.error("获取返回数据异常", e);
            throw new RuntimeException(e);
        }

        if (noNeedResponse) {
            return null;
        }

        /**把json字符串转换成json对象**/
        try {
            return JSONArray.parseArray(str);
        } catch (Exception e) {
            logger.error("JSON转换异常,无法将字符串转换成JSON对象\n" + str, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @param noNeedResponse 不需要返回结果
     * @return
     */
    public static JSONObject jsonPost(String url, JSONObject jsonParam, Map<String, String> headersMap, boolean noNeedResponse) {
        //post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost method = new HttpPost(url);
        //设置header参数
        if (null != headersMap && headersMap.size() > 0) {
            for (String key : headersMap.keySet()) {
                String value = headersMap.get(key);
                Header header = new BasicHeader(key, value);
                method.addHeader(header);
            }
        }
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            return buildResult(url, result, noNeedResponse);
        } catch (IOException e) {
            logger.error("通信异常:" + url, e);
        }
        return null;
    }

    private static JSONObject buildResult(String url, HttpResponse result, boolean noNeedResponse) {
        int statusCode = result.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            logger.error("通信异常:code=" + statusCode + "," + url + "," + result.getStatusLine());
            throw new RuntimeException("通信异常");
        }

        /**读取服务器返回过来的json字符串数据**/
        String str;
        try {
            str = EntityUtils.toString(result.getEntity());
        } catch (IOException e) {
            logger.error("获取返回数据异常", e);
            throw new RuntimeException(e);
        }

        if (noNeedResponse) {
            return null;
        }

        /**把json字符串转换成json对象**/
        try {
            return JSONObject.parseObject(str);
        } catch (Exception e) {
            logger.error("JSON转换异常,无法将字符串转换成JSON对象\n" + str, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * get请求
     *
     * @param url        请求路径
     * @param headersMap 请求头参数
     * @return
     */

    public static JSONObject requestGet(String url, Map<String, String> headersMap) {
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            if (null != headersMap && headersMap.size() > 0) {
                for (String key : headersMap.keySet()) {
                    String value = headersMap.get(key);
                    Header header = new BasicHeader(key, value);
                    request.addHeader(header);
                }
            }
            HttpResponse response = httpClient.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("通信异常:" + url);
            }
        } catch (IOException e) {
            logger.error("通信异常:" + url, e);
        }
        return jsonResult;
    }
}