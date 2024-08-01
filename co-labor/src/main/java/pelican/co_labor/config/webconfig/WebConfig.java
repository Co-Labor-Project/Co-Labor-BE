package pelican.co_labor.config.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:3000", "http://localhost:8081", "http://colaborapp.site", "http://colabor-en.site", "http://colabor.site", "http://colabor-en.site.s3-website.ap-northeast-2.amazonaws.com", "http://colabor.site.s3-website.ap-northeast-2.amazonaws.com", "http://colaborapp.site.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);


    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setSameSite("None");
        serializer.setUseSecureCookie(false); // 로컬 개발 환경에서 false로 설정, 배포 시에는 true로 설정
        return serializer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/images/**")
                .addResourceLocations("classpath:/static/images/");
    }
}