package org.panda.business.admin.context.Listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 这个是自定义创建执行监听器的类 6个时机自己想干点什么就干
 * 不过要注意每个时机的容器内容初始化情况
 */
public class MySpringFactoryApplicationRunListener implements SpringApplicationRunListener {
    /**
     * !!! 构造方法必须这么写
     * 下边创建对象的时候会说
     * @param application
     * @param args
     */
    public MySpringFactoryApplicationRunListener(SpringApplication application, String[] args) {
    }

    /** 对应 ApplicationStartingEvent 事件 */
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【starting】");
    }

    /** 对应 ApplicationEnvironmentPreparedEvent 事件 */
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
                                    ConfigurableEnvironment environment) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【environmentPrepared】");
    }

    /** 对应 ApplicationContextInitializedEvent 事件 */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【contextPrepared】");
    }

    /** 对应 ApplicationPreparedEvent 事件 */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【contextLoaded】");
    }

    /** 对应 ApplicationStartedEvent 事件 */
    @Override
    public void started(ConfigurableApplicationContext context) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【started】");
    }

    /** 对应 ApplicationReadyEvent 事件 */
    @Override
    public void running(ConfigurableApplicationContext context) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【running】");
    }

    /** 对应 ApplicationFailedEvent 事件 */
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
//        System.out.println("【Start-Up】自定义创建执行监听器的类...【failed】");
    }
}
