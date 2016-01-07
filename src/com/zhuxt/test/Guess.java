package com.zhuxt.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by zxt on 2016/1/8.
 */
public class Guess {

    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = null;
        HttpResponse result = null;
        HttpEntity httpEntity = null;
        String body = "";
        boolean flag = false;
        String s1 = "d";
        String s2 = "476691c573d13";
        String[] a1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        String value = "";
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1.length; j++) {
                value = s1 + a1[i] + s2 + a1[j];
                System.out.println("邀请码：" + value);
                StringBuffer url = new StringBuffer("http://cl.yucl.pw/register.php?");
                url.append("reginvcode=").append(value).append("&");
                url.append("action=reginvcodeck");
                System.out.println(url);
                httpPost = new HttpPost(url.toString());
//                httpPost.setHeader("Connection", "close");
                result = httpclient.execute(httpPost);
                httpEntity = result.getEntity();
                body = EntityUtils.toString(httpEntity);
                System.out.println(body);
                if (!body.contains("parent.retmsg_invcode('1')")) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        httpclient.getConnectionManager().shutdown();
    }
}
