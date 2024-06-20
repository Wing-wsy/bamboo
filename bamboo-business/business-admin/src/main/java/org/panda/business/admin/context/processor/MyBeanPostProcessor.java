package org.panda.business.admin.context.processor;

import org.panda.business.admin.context.aware.MyBeanApplicationContextAware;
import org.panda.business.admin.controller.TestLogController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean后处理器：一般在Bean实例化之后，填充到单例池singletonObjects之前执行
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 判断到指定的bean加载，才执行特定操作
        if (bean instanceof TestLogController) {
            System.out.println("【Start-Up】【BeanPostProcessor】TestLogController的Before方法执行");
        }
        // 判断到指定的bean加载，才执行特定操作
        if (bean instanceof MyBeanApplicationContextAware) {
            System.out.println("【Start-Up】【BeanPostProcessor】MyBeanApplicationContextAware的Before方法执行");
        }
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestLogController) {
            System.out.println("【Start-Up】【BeanPostProcessor】TestLogController的After方法执行");
        }
        if (bean instanceof MyBeanApplicationContextAware) {
            System.out.println("【Start-Up】【BeanPostProcessor】MyBeanApplicationContextAware的After方法执行");
        }
        return bean;
    }
}
