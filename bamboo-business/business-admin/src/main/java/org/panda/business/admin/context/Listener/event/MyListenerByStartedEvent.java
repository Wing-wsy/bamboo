package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationStartedEvent
 */
public class MyListenerByStartedEvent implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("【Start-Up】【指定事件】监听 Started 事件...执行了");
    }
}
