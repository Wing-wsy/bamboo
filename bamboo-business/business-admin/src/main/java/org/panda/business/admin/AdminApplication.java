package org.panda.business.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application启动类
 *
 * @author fangen
 */
@MapperScan("org.panda.business.admin.modules.*.service.repository")
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);

    }
}
