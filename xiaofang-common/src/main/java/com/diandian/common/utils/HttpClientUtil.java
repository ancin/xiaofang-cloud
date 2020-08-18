package com.diandian.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.diandian.common.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2019-11-23 16:49
 * @last modify by [shengxiaohua 2019-11-23 16:49]
 **/
@Slf4j
@Component
public class HttpClientUtil {
    @Autowired
    private WxConfig wxConfig;

    /**
     * 发起主动调用 Post方式
     * @param url
     * @param context
     * @return
     */
    public JSONObject createPostMsg(String url, String context) {
        log.info("remote-http-post,url:{},context:{}",url,context);
        String jsonContext = context;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity myEntity = new StringEntity(jsonContext, ContentType.create("text/plain", "UTF-8"));
            httpPost.setEntity(myEntity);
            JSONObject responseBody = httpClient.execute(httpPost,getResponseHandler());
            log.info("remote-http-post,result:{}",responseBody.toJSONString());
            return responseBody;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发起主动调用 Get方式
     * @param url
     * @return
     */
    public JSONObject createGetMsg(String url) {
        log.info("remote-http-get,url:{}",url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            JSONObject responseBody = httpClient.execute(httpGet,getResponseHandler());
            log.info("remote-http-get,result:{}",responseBody.toJSONString());
            return responseBody;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private ResponseHandler<JSONObject> getResponseHandler(){
        return new ResponseHandler<JSONObject>() {
            @Override
            public JSONObject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if(status>=200 && status<300){
                    HttpEntity entity = response.getEntity();
                    if(entity!=null){
                        String result = EntityUtils.toString(entity);
                        JSONObject resultObj = JSONObject.parseObject(result);
                        return resultObj;
                    }else{
                        return null;
                    }
                }else{
                    throw new ClientProtocolException("unexpected response status: "+status);
                }
            }
        };
    }
}
