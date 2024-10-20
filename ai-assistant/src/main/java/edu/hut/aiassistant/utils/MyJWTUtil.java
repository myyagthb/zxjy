package edu.hut.aiassistant.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyJWTUtil {

    // 定义签名密钥，这里使用随机生成的密钥，生产环境中应妥善保管
//    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final String SECRET_KEY = "fslkdfasdjfsoiffslkdjfiunsnfaksdhfiuweowejrowirjwwlrjlksmfsafmldjfasfsadflkajflkjfaoiufoiwejrlksmfksadfaksjflaksjflaksjfasjfoijdfvlkxt4535retetsfdsfsrwresf";

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; ; // 24小时

    /**
     * 将对象转换为JWT字符串
     * @param object 要转换的对象，必须是可以被序列化的
     * @return JWT字符串
     */
    public static String objectToJwtString(Object object) {
        // 注意：jjwt默认使用JsonSerializer进行序列化，因此对象需可序列化
        Map<String, Object> claims = new HashMap<>();
        claims.put("payload", object);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 设置签发时间
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME)) // 设置过期时间为1小时后
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 将JWT字符串转换为对象
     * @param jwtStr JWT字符串
     * @param clazz 目标对象的Class类型
     * @param <T> 泛型标记
     * @return 目标对象实例
     */
    public static <T> T jwtStringToObject(String jwtStr, Class<T> clazz) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwtStr)
                    .getBody();
            return mapToObject((Map<String, Object>) claims.get("payload"), clazz);
        } catch (Exception e) {
            throw new RuntimeException("JWT解析失败", e);
        }
    }

    /**
     * 辅助方法：将Map转换为目标类型的对象
     * @param map 数据源Map
     * @param clazz 目标对象的Class类型
     * @param <T> 泛型标记
     * @return 目标对象实例
     */
    private static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        // 此处可以根据实际使用的序列化框架（如Jackson、Gson等）进行转换
        // 以下代码仅为示例，具体实现需根据所使用的库调整
        // 假设使用Jackson
         ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper.convertValue(map, clazz);
    }

    /**
     * 验证JWT有效性
     * @param jwtStr JWT字符串
     * @return 是否有效
     */
    public static boolean validateJwt(String jwtStr) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwtStr);
            return true;
        } catch (Exception e) {
            return false; // 解析异常则认为无效
        }
    }

}

