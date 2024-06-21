package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationEnvironmentPreparedEvent
 */
public class MyListenerByEnvironmentPreparedEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
//        System.out.println("【Start-Up】【指定事件】监听 EnvironmentPrepared 事件...执行了");
    }
}
