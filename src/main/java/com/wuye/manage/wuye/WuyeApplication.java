package com.wuye.manage.wuye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableTransactionManagement
@MapperScan({"com.wuye.manage.wuye.*.mapper"})
public class WuyeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuyeApplication.class, args);
    }

}
