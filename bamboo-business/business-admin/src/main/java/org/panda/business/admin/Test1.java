package org.panda.business.admin;

import org.panda.bamboo.common.util.LogUtil;
import org.panda.bamboo.core.beans.ContextInitializedBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wing
 * @create 2024/6/18
 */
@Component
public class Test1 implements ContextInitializedBean {
    @Override
    public void afterInitialized(ApplicationContext context) throws Exception {

    }
}
