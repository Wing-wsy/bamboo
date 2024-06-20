package org.panda.business.admin.context.contextInitializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * SpringApplication类 applyInitializers方法中回调执行所有实现了 ApplicationContextInitializer 接口的 initialize 方法
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("【Start-Up】【ApplicationContextInitializer】MyApplicationContextInitializer执行了...");
    }
}
