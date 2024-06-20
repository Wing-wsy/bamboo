package org.panda.business.admin.context.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextAware接口可以让Bean获取到ApplicationContext对象，通过这个对象，我们可以获取Spring容器中的其他Bean实例或一些组件。
 * 常见的应用场景包括：
 *  1）在Web项目中获取ServletContext
 *  2）获取国际化信息
 *  3）获取Scheduler等定时任务
 */
@Component
public class MyBeanApplicationContextAware implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("【Start-Up】【Aware】我的 MyBeanApplicationContextAware 执行了...");
    }

    public void doSomething() {
        // 获取其他的Bean实例或其他的组件
        // OtherBean otherBean = applicationContext.getBean("otherBean", OtherBean.class);
        // ...
    }

    /** spring初始化bean的时候，如果bean实现了InitializingBean接口，会自动调用afterPropertiesSet方法 */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【Start-Up】【afterPropertiesSet】MyBeanApplicationContextAware-afterPropertiesSet方法调用");
    }

}
