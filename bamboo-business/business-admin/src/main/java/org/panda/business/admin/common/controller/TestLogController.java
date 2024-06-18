package org.panda.business.admin.common.controller;

import org.panda.bamboo.common.util.LogUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wing
 * @create 2024/6/18
 */
@RestController
@RequestMapping("/books")
public class TestLogController {

    @GetMapping("/not")
    public void isFollow() {

        LogUtil.trace(getClass(), "这是trace日志");
        LogUtil.debug(getClass(), "这是debug日志");
        LogUtil.info(getClass(), "这是info日志");
        LogUtil.warn(getClass(), "这是warn日志");
        LogUtil.error(getClass(), "这是error日志");

//        for (int i = 0; i < 10; i++) {
//            LogUtil.trace(getClass(), "这是trace日志-{}", i);
//            LogUtil.debug(getClass(), "这是debug日志-{}", i);
//            LogUtil.info(getClass(), "这是info日志-{}", i);
//            LogUtil.warn(getClass(), "这是warn日志-{}", i);
//            LogUtil.error(getClass(), "这是error日志-{}", i);
//        }

    }
}
