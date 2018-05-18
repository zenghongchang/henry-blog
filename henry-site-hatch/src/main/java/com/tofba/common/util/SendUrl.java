package com.tofba.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendUrl {
    public static String sendGet(String url) {
        return sendGet(url, null);
    }
    
    public static String sendGet(String url, String param) {
        String result = processSendGet(url, param, null);
        
        return result;
    }
    
    public static String processSendGet(String url, String param, Map<String, String> paramMap) {
        String result = "";
        try {            
            String urlName = url;
            if (null != param) {
                urlName += "?" + param;
            }
            URL U = new URL(urlName);
            URLConnection connection = U.openConnection();
            if (null != paramMap) {
                for (String key : paramMap.keySet()) {
                    connection.addRequestProperty(key, paramMap.get(key));
                }
            }            
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return result;
    }
    
    public static InputStream sendGetReturnInputStream(String url, String param, Map<String, String> paramMap) {
        InputStream inputStream = null;
        try {
            String urlName = url;
            if (null != param) {
                urlName += "?" + param;
            }
            URL U = new URL(urlName);
            URLConnection connection = U.openConnection();
            if (null != paramMap) {
                for (String key : paramMap.keySet()) {
                    connection.addRequestProperty(key, paramMap.get(key));
                }
            }
            connection.connect();
            inputStream = connection.getInputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
        return inputStream;
    }
    
    public static String sendPost(String url, Map<String, String> paramMap) {
        String result = processSendPost(url, null, paramMap);
        return result;
    }
    
    public static String sendPost(String url, String param) {
        String result = processSendPost(url, param, null);
        return result;
    }
    
    public static String processSendPost(String url, String param, Map<String, String> paramMap) {
        String result = "";
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }            
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }            
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        try {
            // Post请求的url，与get不同的是不需要带参数
            URL postUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection)postUrl.openConnection();
            if (null != paramMap) {
                for (String key : paramMap.keySet()) {
                    connection.addRequestProperty(key, paramMap.get(key));
                }
            }
            connection.setConnectTimeout(1200000);
            connection.setReadTimeout(1200000);
            // Output to the connection. Default is
            // false, set to true because post
            // method must write something to the
            // connection
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // Set the post method. Default is GET
            connection.setRequestMethod("POST");
            // Post cannot use caches
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // Set the content type to urlencoded,
            // because we will write
            // some URL-encoded content to the
            // connection. Settings above must be set before connect!
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            String contentType = "application/x-www-form-urlencoded";
            if (null != paramMap) {
                String _contentType = paramMap.get("Content-Type");
                if (StringUtils.isNotEmpty(_contentType)) {
                    contentType = _contentType;
                }
            }
            connection.setRequestProperty("Content-Type", contentType);
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            if (null != param) {
                outputStreamWriter.write(param);
            }
            outputStreamWriter.flush();
            outputStreamWriter.close();
            String encoding = "utf-8";
            if (null != paramMap && StringUtils.isNotEmpty(paramMap.get("encoding"))) {
                encoding = paramMap.get("encoding");
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 设置编码,否则中文乱码
            String line = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }
    
    /**
     * 模拟form表单请求
     * 
     * @param requestUrl
     * @param params
     * @return
     * @author  Jeff(fba01)
     * @version  [版本号, 2018年3月30日]
     * @see [类、类#方法、类#成员]
     */
    public static String mockFormPost(String requestUrl, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();       
        HttpPost httpPost = new HttpPost(requestUrl);       
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            if(params!=null){  
                for (Entry<String, String> entry : params.entrySet()) {  
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
                }  
            }  
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8")); 
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded"); 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                System.out.println("Method is wrong " + httpResponse.getStatusLine());
                return null;
            }
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpPost.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 获取request中body的值
     * <一句话功能简述>
     * <功能详细描述>
     * @param request
     * @return
     * @author  Jeff(fba01)
     * @version  [版本号, 2018年3月30日]
     * @see [类、类#方法、类#成员]
     */
    public static String gainRequestBodyConstent(HttpServletRequest request) {
        String content = "";
        if(null != request) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String str = "";
                while ((str = reader.readLine()) != null) {
                    content += str;
                }
            }catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return content;
    }
}