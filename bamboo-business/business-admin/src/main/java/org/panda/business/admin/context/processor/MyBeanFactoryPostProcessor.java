package org.panda.business.admin.context.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Bean工厂后处理器：在BeanDefinitionMap填充完毕，Bean实例化之前执行
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("【Start-Up】【BeanFactoryPostProcessor】MyBeanFactoryPostProcessor执行了...");
    }
}
