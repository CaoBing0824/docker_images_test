package com.xy.boot.open;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 上午 10:23
 */
@Slf4j
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.xy.boot.open","com.xy.boot.common.exception"})
@ServletComponentScan
@MapperScan(value={"com.xy.boot.open.mapper"})
public class OpenApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpenApiApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("PortalApplication is success!");
    }

}
