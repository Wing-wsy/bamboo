package org.panda.business.admin.modules.test;


import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.panda.bamboo.common.util.LogUtil;
import org.panda.tech.core.web.restful.RestfulResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * @author wing
 * @create 2024/6/18
 */
@Api(tags = "测试控制器")
@ApiSupport(order = 2,author = "wing1") // order：当前控制器排序，author：当前控制器作者
@RestController
@RequestMapping("/test")
public class TestController implements InitializingBean, ServletContextAware, ApplicationContextAware,
        BeanFactoryAware, BeanNameAware {

    @ApiOperationSupport(order = 1,author = "wing")  // order：当前接口排序，author：当前接口作者（如果没写，默认使用控制器的作者）
    @ApiOperation(value = "打印日志")
    @GetMapping("/log")
    public RestfulResult isFollow() {

        LogUtil.trace(getClass(), "这是trace日志");
        LogUtil.debug(getClass(), "这是debug日志");
        LogUtil.info(getClass(), "这是info日志");
        LogUtil.warn(getClass(), "这是warn日志");
        LogUtil.error(getClass(), "这是error日志");
        return RestfulResult.success();
    }

    @ApiOperationSupport(order = 2)   //没有当前接口作者，默认使用控制器的作者（如果有写的话）
    @ApiImplicitParam(name = "name",value = "姓名",required = false)
    @ApiOperation(value = "示例接口【开发中】")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name",defaultValue = "小王")String name){
        return ResponseEntity.ok("Hi:"+name);
    }

    /**
     * 测试方法
     *
     * @param who 测试参数
     * @return {@link Dict}
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "测试aop日志打印")
    @GetMapping("/test")
    public Dict test(String who) {
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }

    /**
     *  测试post json方法
     * @param map 请求的json参数
     * @return {@link Dict}
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "测试aop日志打印-json")
    @PostMapping("/testJson")
    public Dict testJson(@RequestBody Map<String, Object> map) {
        return Dict.create().set("json", map);
    }


    /*@PostMapping("/createOrder")
    @ApiImplicitParam(name = "map",value = "集合",required = false)
    @ApiOperation(value = "响应描述")
    @DynamicResponseParameters(name = "CreateOrderHashMapModel",properties = {
            @DynamicParameter(name = "",value = "注解id",example = "X000111",required = true,dataTypeClass = Integer.class),
            @DynamicParameter(name = "name3",value = "订单编号-gson"),
            @DynamicParameter(name = "name1",value = "订单编号1-gson"),
    })
    public ResponseEntity<HashMap> createOrder1235332(@RequestBody HashMap map){
        return ResponseEntity.ok(map);
    }*/


    /** spring初始化bean的时候，如果bean实现了InitializingBean接口，会自动调用afterPropertiesSet方法 */
    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("【Start-Up】【afterPropertiesSet】TestLogController-afterPropertiesSet方法调用");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        System.out.println("【Start-Up】【Aware】这是beanFactory:" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
//        System.out.println("【Start-Up】【Aware】这是name:" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("【Start-Up】【Aware】这是applicationContext:" + applicationContext);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
//        System.out.println("【Start-Up】【Aware】这是servletContext:" + servletContext);
    }
}
