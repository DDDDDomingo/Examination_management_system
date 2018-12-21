package studio.beita.hdxg.beitasystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 用户头像映射位置
     */
    @Value("${USER_AVATAR_PATH_PATTERN}")
    private String USER_AVATAR_PATH_PATTERN;
    /**
     * 用户头像存储位置
     */
    @Value("${USER_AVATAR_FILE_REPOSITORY}")
    private String USER_AVATAR_FILE_REPOSITORY;

    /**
     * 缴费凭证映射位置
     */
    @Value("${USER_SIGNUPPIC_PATTERN}")
    private String USER_SIGNUPPIC_PATTERN;
    /**
     * 缴费凭证存储位置
     */
    @Value("${USER_SIGNUPPIC_FILE_REPOSITORY}")
    private String USER_SIGNUPPIC_FILE_REPOSITORY;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * 用户头像映射
         */
        if (!USER_AVATAR_FILE_REPOSITORY.endsWith(File.separator)) {
            USER_AVATAR_FILE_REPOSITORY += File.separator;
            registry.addResourceHandler(USER_AVATAR_PATH_PATTERN + "/**").addResourceLocations("file:" + USER_AVATAR_FILE_REPOSITORY);
        }
        if (!USER_SIGNUPPIC_FILE_REPOSITORY.endsWith(File.separator)) {
            USER_SIGNUPPIC_FILE_REPOSITORY += File.separator;
            registry.addResourceHandler(USER_SIGNUPPIC_PATTERN + "/**").addResourceLocations("file:" + USER_SIGNUPPIC_FILE_REPOSITORY);
        }

        super.addResourceHandlers(registry);
    }

}
