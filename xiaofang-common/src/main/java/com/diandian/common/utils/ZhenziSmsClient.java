package com.diandian.common.utils;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2020-03-29 21:31
 * @last modify by [shengxiaohua 2020-03-29 21:31]
 **/
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class ZhenziSmsClient {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private String apiUrl = "";
    private static final int connectTimeout = 20000;
    private static final int readTimeout = 20000;
    private String appId;
    private String appSecret;

    public ZhenziSmsClient(String apiUrl, String appId, String appSecret) {
        this.apiUrl = apiUrl;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String send(String number, String message) throws Exception {
        return this.send(number, message, "");
    }

    public String send(String number, String message, String messageId) throws Exception {
        Map<String, String> params = new HashMap();
        params.put("appId", this.appId);
        params.put("appSecret", this.appSecret);
        params.put("message", message);
        params.put("number", number);
        params.put("messageId", messageId);
        String result = this.doPost(this.apiUrl + "/sms/send.do", params, "UTF-8", 20000, 20000);
        return result;
    }

    public String balance() throws Exception {
        Map<String, String> params = new HashMap();
        params.put("appId", this.appId);
        params.put("appSecret", this.appSecret);
        String result = this.doPost(this.apiUrl + "/account/balance.do", params, "UTF-8", 20000, 20000);
        return result;
    }

    public String findSmsByMessageId(String messageId) throws Exception {
        Map<String, String> params = new HashMap();
        params.put("appId", this.appId);
        params.put("appSecret", this.appSecret);
        params.put("messageId", messageId);
        String result = this.doPost(this.apiUrl + "/smslog/findSmsByMessageId.do", params, "UTF-8", 20000, 20000);
        return result;
    }

    private String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws Exception {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = this.buildQuery(params, charset);
        byte[] content = new byte[0];
        if (query != null) {
            content = query.getBytes(charset);
        }

        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;

        try {
            conn = this.getConnection(new URL(url), "POST", ctype, (Map)null);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = StreamUtil.read(conn.getInputStream(), "UTF-8");
        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        return rsp;
    }

    public String buildQuery(Map<String, String> params, String charset) throws Exception {
        if (params != null && !params.isEmpty()) {
            StringBuilder query = new StringBuilder();
            Set<Entry<String, String>> entries = params.entrySet();
            boolean hasParam = false;
            Iterator var6 = entries.iterator();

            while(var6.hasNext()) {
                Entry<String, String> entry = (Entry)var6.next();
                String name = (String)entry.getKey();
                String value = (String)entry.getValue();
                if (name != null && !name.trim().equals("")) {
                    if (hasParam) {
                        query.append("&");
                    } else {
                        hasParam = true;
                    }

                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                }
            }

            return query.toString();
        } else {
            return null;
        }
    }

    private HttpURLConnection getConnection(final URL url, String method, String ctype, Map<String, String> headerMap) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String protocol = url.getProtocol();
        if (protocol.toLowerCase().equals("https")) {
            SmsX509TrustManager xtm = new SmsX509TrustManager();
            SSLContext sslContext = null;
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[]{xtm};
            sslContext.init((KeyManager[])null, xtmArray, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession sslSession) {
                    hostname = trim(hostname);
                    return !hostname.trim().equals("") && url.getHost().equals(hostname);
                }
            });
        }

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Host", url.getHost());
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("Content-Type", ctype);
        if (headerMap != null) {
            Iterator var10 = headerMap.entrySet().iterator();

            while(var10.hasNext()) {
                Entry<String, String> entry = (Entry)var10.next();
                conn.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            }
        }

        return conn;
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }

    public static class SmsX509TrustManager implements X509TrustManager {
        public SmsX509TrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
