package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationReadyEvent
 */
public class MyListenerByReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("【Start-Up】【指定事件】监听 Ready 事件...执行了");
    }
}
