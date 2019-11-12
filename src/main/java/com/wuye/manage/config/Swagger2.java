package com.wuye.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {

    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                //.apis(RequestHandlerSelectors.basePackage("com.wuye.manage.wuye.**.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * @Description: 构建api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("物业接口文档")
                // 设置联系人
                .contact(
                        new Contact("Leafqun", null, null))
                // 描述
                .description("接口采用restful设计")
                // 定义版本号
                .version("v1").build();
    }
}
