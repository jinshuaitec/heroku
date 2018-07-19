package com.jinshuaitec.hello.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;*/

/**
 * Created by fonlin on 2017/8/4.
 * http工具类
 */
public class HttpUtil {

    /**
     * httpUtil请求超时时间
     */
    private static final int HTTP_TIME_OUT = 100000;

    public static JSONObject get(String url, Map<String, String> params) throws IOException {
        JSONObject json = new JSONObject();
        HttpClient client = new HttpClient();
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nameValuePairs.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        GetMethod method = new GetMethod(url);
        //设置超时时长
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, HTTP_TIME_OUT);

        method.setQueryString(nameValuePairs.toArray(new NameValuePair[params.size()]));
        HttpMethodParams param = method.getParams();
        param.setContentCharset("utf-8");
        json.put("code", client.executeMethod(method));
        json.put("result", method.getResponseBodyAsString());
        return json;
    }

    public static JSONObject post(String url, String jsonValue) throws IOException {
        JSONObject json = new JSONObject();
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        //设置超时时长
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, HTTP_TIME_OUT);
        postMethod.setRequestHeader("Content-Type", "application/json");
        RequestEntity requestEntity = new StringRequestEntity(jsonValue, null, "utf-8");
        postMethod.setRequestEntity(requestEntity);
        json.put("code", client.executeMethod(postMethod));
        json.put("result", postMethod.getResponseBodyAsString());
        return json;
    }

    public static JSONObject get(String url) throws IOException {
        JSONObject json = new JSONObject();
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        //设置超时时长
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, HTTP_TIME_OUT);
        HttpMethodParams param = method.getParams();
        param.setContentCharset("utf-8");
        json.put("code", client.executeMethod(method));
        json.put("result", method.getResponseBodyAsString());
        return json;
    }
}
