package edu.hut.aiassistant.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.hut.aiassistant.custom.entity.WeiXinAccountConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 微信回复用户模板
 */
@Component
public class WeiXinTemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinTemplateUtils.class);


    @Autowired
    private WeiXinAccountConfig accountConfig;


    public void sendTemplateMessageToWxUser(String openid,String accessToken) {
        try {
            //String accessToken = getAccessToken();
            JsonObject message = createTemplateMessage(
                    openid,
                    accountConfig.getTemplateId(),
                    "http://weixin.qq.com/download"
            );
            sendTemplateMessage(accessToken, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTemplateMessage(String accessToken, JsonObject message) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        Gson gson = new Gson();
        String jsonInputString = gson.toJson(message);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            LOGGER.info("Message sent successfully.");
        } else {
            LOGGER.info("Failed to send message. Response Code: {}",responseCode);
        }
    }



    /**
     * 生成访问token字符串
     * @return
     * @throws Exception
     */
    public String getAccessToken() throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + accountConfig.getAppId() + "&secret=" + accountConfig.getAppSecret();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response.toString()).getAsJsonObject();
        return jsonObject.get("access_token").getAsString();
    }


    /**
     * 创建消息模板带阿米
     * @param openId 接受者的Id
     * @param templateId 微信消息模板的templateId
     * @param url 固定下载地址：http://weixin.qq.com/download
     * @return
     */
    public JsonObject createTemplateMessage(String openId, String templateId, String url) {
        JsonObject message = new JsonObject();
        //设置接收者的微信号
        message.addProperty("touser", openId);
        //设置微信回复的消息模板
        message.addProperty("template_id", templateId);
        //设置地址
        message.addProperty("url", url);
        //设置颜色
        message.addProperty("topcolor", "#FF0000");

        //如果模板里面有要替换的变量，这里可以替换,如下是模板和替换的实例代码
        /**
         * 	尊敬的{{User.DATA}}，您于{{Date.DATA}}
         * 	在{{CardNumber.DATA}}卡进行了{{Type.DATA}}操作，
         * 	金额为{{Money.DATA}}。本次操作后余额为{{Left.DATA}}。请知悉！
         */
        JsonObject data = new JsonObject();
        addDataField(data, "User", "黄先生");
        addDataField(data, "Date", "06月07日 19时24分");
        addDataField(data, "CardNumber", "0426");
        addDataField(data, "Type", "消费");
        addDataField(data, "Money", "人民币260.00元");
        addDataField(data, "DeadTime", "06月07日19时24分");
        addDataField(data, "Left", "6504.09");
        message.add("data", data);

        return message;
    }

    private void addDataField(JsonObject data, String key, String value) {
        JsonObject field = new JsonObject();
        field.addProperty("value", value);
        field.addProperty("color", "#173177");
        data.add(key, field);
    }



}
