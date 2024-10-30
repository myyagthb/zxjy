package edu.hut.aiassistant.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class WechatService {

    private String appid;
    private String appsecret;

    public WechatService(String appid, String appsecret) {
        this.appid = appid;
        this.appsecret = appsecret;
    }

    // 获取access_token的方法
    private JSONObject getAccessToken() {
        String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
                + "appid=" + appid
                + "&secret=" + appsecret;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return JSONObject.parseObject(response.getBody());
        } else {
            throw new RuntimeException("Failed to get access token: " + response.getStatusCodeValue());
        }
    }

    // 获取ticket的方法
    public JSONObject getTicket(JSONObject json) {
        JSONObject accessTokenObj = getAccessToken(); // 调用获取access_token的方法
        String access_token = accessTokenObj.getString("access_token");

        String ticketUri = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + access_token;
        System.out.println("ticket-uri:" + ticketUri);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(ticketUri, request, String.class);

        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());

        JSONObject obj = JSONObject.parseObject(response.getBody());
        return obj;
    }

    // 用于测试的方法
    public static void main(String[] args) {
//        WechatService wechatService = new WechatService("wx0a971a93b324d491", "61d64c6c7e246aedf9f31d54ae0fc929");
        WechatService wechatService = new WechatService("wx171dd84859e0dbf4", "0ad4d19e34cac62f38a93d73dfcbcfc2");
        JSONObject json = new JSONObject();
        json.put("expire_seconds", 604800); // 一周有效期
        json.put("action_name", "QR_SCENE");
        JSONObject scene = new JSONObject();
        scene.put("scene_id", 123);
        json.put("action_info", new JSONObject().put("scene", scene));

        try {
            JSONObject ticketResponse = wechatService.getTicket(json);
            System.out.println("Ticket Response: " + ticketResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
