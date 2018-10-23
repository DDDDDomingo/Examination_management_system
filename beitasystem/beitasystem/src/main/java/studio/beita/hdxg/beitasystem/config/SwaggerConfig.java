package studio.beita.hdxg.beitasystem.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: SwaggerConfig
 * @package: studio.beita.hdxg.beitasystem.config
 * @description: Swagger配置文件
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter{
    private ApiInfo initApiInfo() {
        ApiInfo apiInfo = new ApiInfo("贝塔考试系统管理 Platform API",
                initContextInfo(),
                "版本：1.0.0",
                "team",
                "服务条款",
                "The Apache License, Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"
        );
        return apiInfo;
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars*")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。");
        return sb.toString();
    }

    @Bean
    public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("RestfulApi")
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .paths(doFilteringRules())
                .build()
                .apiInfo(initApiInfo());
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                /*regex("/front.*"),
                regex("/category.*"),
                regex("/attribute.*"),
                regex("/attributeValue.*"),
                regex("/product.*"),
                regex("/admin.*"),*/
                regex("/user.*")

        );
    }
}
