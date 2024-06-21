package org.panda.business.admin.context.processor;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wing
 * @create 2024/6/20
 */
public class MyImportBeanDefinitionRegistrar1 implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
//        System.out.println("【Start-Up】【ImportBeanDefinitionRegistrar】MyImportBeanDefinitionRegistrar1执行了...");
        // doSomething
        // beanDefinition.setBeanClassName(OtherBean.class.getName());
        // registry.registerBeanDefinition("otherBean",beanDefinition);
    }
}
