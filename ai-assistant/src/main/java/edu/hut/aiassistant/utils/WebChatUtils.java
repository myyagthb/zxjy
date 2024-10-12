package edu.hut.aiassistant.utils;



import edu.hut.aiassistant.custom.dto.WebChatRequestDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WebChatUtils {
    /**
     * 微信公众平台-测试公众号-接口配置信息-token
     */
    private static String webChatToken = "123456";

    /**
     * 核实微信请求
     * @param requestModel
     * @return
     */
    public static boolean checkWebChatToken(WebChatRequestDTO requestModel) throws NoSuchAlgorithmException {
        String[] array = {webChatToken,requestModel.getTimestamp(),requestModel.getNonce()};

        Arrays.sort(array);

        String str = String.join("",array);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

        byte[] digest = messageDigest.digest(str.getBytes());


        StringBuffer hexString = new StringBuffer();
        for (byte b : digest) {
            String s = Integer.toHexString(b & 0xff);

            if (s.length() == 1){
                hexString.append(0);
            }

            hexString.append(s);
        }

        return requestModel.getSignature().equals(hexString.toString());
    }
}
