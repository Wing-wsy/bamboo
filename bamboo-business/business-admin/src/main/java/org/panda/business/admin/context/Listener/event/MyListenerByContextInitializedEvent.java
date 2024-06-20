package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationContextInitializedEvent
 */
public class MyListenerByContextInitializedEvent implements ApplicationListener<ApplicationContextInitializedEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        System.out.println("【Start-Up】【指定事件】监听 ContextInitialized 事件...执行了");
    }
}
