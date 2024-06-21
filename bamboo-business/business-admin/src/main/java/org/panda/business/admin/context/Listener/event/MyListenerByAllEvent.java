package org.panda.business.admin.context.Listener.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听 ApplicationEvent 事件的监听器
 * 只要是属于事件ApplicationEvent的都会进行回调
 */
public class MyListenerByAllEvent implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        System.out.println("【Start-Up】【全部事件】MyListenerByAllEvent监听全部事件执行了...");
    }
}
