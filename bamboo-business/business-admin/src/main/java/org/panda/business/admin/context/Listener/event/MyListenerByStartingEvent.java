package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationStartingEvent
 */
public class MyListenerByStartingEvent implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
//        System.out.println("【Start-Up】【指定事件】监听 Starting 事件...执行了");
    }
}
