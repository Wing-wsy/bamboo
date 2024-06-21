package org.panda.business.admin.context.Listener.event;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听具体的某一个事件的监听器 这里举例：ApplicationPreparedEvent
 */
public class MyListenerByPreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
//        System.out.println("【Start-Up】【指定事件】监听 Prepared 事件...执行了");
    }
}
