package rainclassv3.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CorsConfig
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午1:35
 * @Version 1.0
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${disPic.file-path}")
    private String FILE_PATH;

    @Value("${disPic.web-path}")
    private String WEB_PATH;


    private static final Logger LOG= LoggerFactory.getLogger(CorsConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOG.info("外部尝试访问磁盘图片");
        LOG.info("拦截器中设置的路径为:{}","file:"+FILE_PATH);
        registry.addResourceHandler("/disPic/**").addResourceLocations("file:"+FILE_PATH+"/");

        // http://127.0.0.1:9000/disPic/1.jpg
    }
}
