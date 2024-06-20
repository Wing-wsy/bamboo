package org.panda.business.admin.controller;

import org.panda.bamboo.common.util.LogUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author wing
 * @create 2024/6/18
 */
@RestController
@RequestMapping("/books")
public class TestLogController implements InitializingBean, ServletContextAware, ApplicationContextAware,
        BeanFactoryAware, BeanNameAware {

    @GetMapping("/not")
    public void isFollow() {


        LogUtil.error(getClass(), "这是实例：{}", this);
        LogUtil.trace(getClass(), "这是trace日志");
        LogUtil.debug(getClass(), "这是debug日志");
        LogUtil.info(getClass(), "这是info日志");
        LogUtil.warn(getClass(), "这是warn日志");
        LogUtil.error(getClass(), "这是error日志");
    }

    /** spring初始化bean的时候，如果bean实现了InitializingBean接口，会自动调用afterPropertiesSet方法 */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【Start-Up】【afterPropertiesSet】TestLogController-afterPropertiesSet方法调用");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【Start-Up】【Aware】这是beanFactory:" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("【Start-Up】【Aware】这是name:" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【Start-Up】【Aware】这是applicationContext:" + applicationContext);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("【Start-Up】【Aware】这是servletContext:" + servletContext);
    }
}
