package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationFailedEvent
 */
public class MyListenerByFailedEvent implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        System.out.println("【Start-Up】【指定事件】监听 Failed 事件...执行了");
    }
}
