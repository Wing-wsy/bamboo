package org.panda.business.admin.context.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryAware接口可以让Bean获取到BeanFactory对象，通过这个对象，我们可以获取Spring容器中的Bean实例或一些组件。
 * 常见的应用场景包括：
 *  1）获取Spring容器中的Bean实例
 *  2）手动注册BeanDefinition
 */
@Component
public class MyBeanBeanFactoryAware implements BeanFactoryAware {
    private BeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("【Start-Up】【Aware】我的 BeanFactoryAware 执行了...");
    }

    public void doSomething() {
        // 获取其他的Bean实例或其他的组件
        // OtherBean otherBean = applicationContext.getBean("otherBean", OtherBean.class);
        // ...
    }
}
