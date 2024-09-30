package edu.hut.aiassistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @BelongsProject: foodie-dev
 * @BelongsPackage: com.imooc.config
 * @Author: 王海鑫
 * @CreateTime: 2024-04-03  21:18
 * @Description: 跨域类配置
 * @Version: 1.0
 */
@Configuration
public class CorsConfig {


    public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        //1.添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");

        //设置是否发送cookie信息
        config.setAllowCredentials(true);

        //设置允许请求方式
        config.addAllowedMethod("*");
        //设置允许的Header
        config.addAllowedHeader("*");

        //2.为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);//  /**代表所有的路由

        //3.返回从新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
