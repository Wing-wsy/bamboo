package org.panda.business.admin.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author wing
 * @create 2024/6/20
 */

@Configuration
//@ConditionalOnProperty(prefix = "elastic.job.zk", name = {"namespace", "serverLists"}, matchIfMissing = false)
public class TestAutoConfig {
    public TestAutoConfig() {
        System.out.println("【Start-Up】TestAutoConfig构造函数调用");
    }
}
