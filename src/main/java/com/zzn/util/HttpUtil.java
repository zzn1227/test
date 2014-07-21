/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.zzn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 类HttpUtil.java的实现描述：TODO 类实现描述
 * 
 * @author wb_zhineng.zhaozn 2014-1-26 下午7:57:46
 */

class TrustAnyTrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[] {};
    }
}

class TrustAnyHostnameVerifier implements HostnameVerifier {

    public boolean verify(String hostname, SSLSession session) {
        // 直接Pass
        return true;
    }
}

public class HttpUtil {

    public static String postHttp(String urlString) {
        HttpURLConnection url_con = null;
        StringBuilder tempStr = new StringBuilder();
        try {
            URL url = new URL(urlString);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            url_con.setDoOutput(true);

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));

            while (rd.read() != -1) {
                tempStr.append(rd.readLine());
            }
            System.out.println(tempStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (url_con != null) url_con.disconnect();
        }
        return tempStr.toString();
    }

    // 发送https请求
    public static String postHttps(String urlString) {
        HttpsURLConnection connection = null;
        StringBuilder tempStr = new StringBuilder();
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new SecureRandom());
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(context.getSocketFactory());
            connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            InputStream in = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));

            while (rd.read() != -1) {
                tempStr.append(rd.readLine());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }
        return tempStr.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // String url = "https://10.20.151.25/icbuAutoBidding/postKeyword.htm?usr=jcbutest&pwd=hello1234&jsonData=";
        // String jsonData =
        // "[{kw:'zhaozn',aid :'2156408976',cid:'51208255',did:'1479388555',kid:'28949932756',e1:'1',e2:'1',e3:'1',nt:'Broad',nm:'1',ns:'paused',ot:'borad',om:'1',os:'paused'}]";
        // url += URLEncoder.encode(jsonData, "UTF-8");
        //
        // //postHttp("http://127.0.0.1:8080/icbuAutoBidding/postKeyword.htm?usr=jcbut");
        // postHttps(url);

        // String url =
        // "http://127.0.0.1/autoOptimizeService/postKeywords.json?user=ptsdev&pwd=123456&advertiserId=2&ignoreManual=true&postKeywords=";
        // String jsonData =
        // "[{ptsid:'200010388966',aid:'2156408976',cid:'77097655',did:'2864863615',kid:'11996501',ns:'2',os:'1',nm:'0.13',om:'0.13'}]";
        // url += URLEncoder.encode(jsonData, "UTF-8");

        // String url =
        // "http://10.20.147.101:7001/autoOptimizeService/resetAccount.json?user=ICBU-Admin&pwd=123456&aoId=2&accountIds=3502507725,2156408976";
        // postHttp(url);

        // String url =
        // "http://pts.data.alibaba-inc.com/superadmin.dox?action=superadmin/PullDataAction&event_submit_do_performance=true&accountId=8417680229&advertiserId=2&date=2012-08-03&reportType=SEARCH_QUERY_PERFORMANCE_REPORT";
        // postHttp(url);
        // System.out.println(new Date());

    }

}
