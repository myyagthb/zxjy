package edu.hut.aiassistant.utils;




import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpRequestUtil {
    public static HttpResponse doGet(String url){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return httpResponse;
    }
}
