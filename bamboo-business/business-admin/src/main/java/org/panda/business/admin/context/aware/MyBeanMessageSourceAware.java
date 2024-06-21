package org.panda.business.admin.context.aware;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

/**
 * MessageSourceAware接口可以让Bean获取到MessageSource对象，通过这个对象，我们可以方便地进行国际化操作。
 * 常见的应用场景包括：
 *  1）获取国际化信息
 */
@Component
public class MyBeanMessageSourceAware implements MessageSourceAware {
    private MessageSource messageSource;


    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
//        System.out.println("【Start-Up】【Aware】我的 MessageSourceAware 执行了...");
    }

    public void doSomething() {
        // 获取国际化信息
        // String message = messageSource.getMessage("key", null, Locale.getDefault());
        // ...
    }
}
