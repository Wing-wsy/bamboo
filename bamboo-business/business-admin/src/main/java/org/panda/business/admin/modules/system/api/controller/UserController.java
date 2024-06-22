package org.panda.business.admin.modules.system.api.controller;

import io.swagger.annotations.Api;
import org.panda.bamboo.common.constant.Commons;
import org.panda.business.admin.common.constant.AuthConstants;
import org.panda.business.admin.modules.system.api.param.*;
import org.panda.business.admin.modules.system.api.vo.UserVO;
import org.panda.business.admin.modules.system.service.SysUserService;
import org.panda.business.admin.modules.system.service.entity.SysUser;
import org.panda.tech.core.exception.business.BusinessException;
import org.panda.tech.core.web.config.WebConstants;
import org.panda.tech.core.web.restful.RestfulResult;
import org.panda.tech.data.model.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

/**
 * 用户权限管理
 *
 * @author fangen
 **/
@Api(tags = "系统用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/info")
    public RestfulResult<?> info(HttpServletRequest request) {
        String token = request.getHeader(WebConstants.HEADER_AUTH_JWT);
        UserVO userInfo = userService.getUserByToken(token);
        if (userInfo == null) {
            return RestfulResult.failure(AuthConstants.USER_NOT_EXIST_CODE, AuthConstants.USERNAME_NOT_EXIST);
        }
        return RestfulResult.success(userInfo);
    }

    @PostMapping("/page")
    public RestfulResult<?> page(@RequestBody UserQueryParam queryParam) {
        QueryResult<UserVO> userPage = userService.getUserByPage(queryParam);
        return RestfulResult.success(userPage);
    }

    @PostMapping("/add")
    public RestfulResult<?> add(@RequestBody AddUserParam userParam) {
        String result = userService.addUser(userParam);
        if (Commons.RESULT_SUCCESS.equals(result)) {
            return RestfulResult.success();
        }
        return RestfulResult.failure(result);
    }

    @PutMapping("/edit")
    public RestfulResult<?> edit(@RequestBody SysUser user) {
        if (userService.updateUser(user)) {
            return RestfulResult.success();
        }
        return RestfulResult.failure();
    }

    @PutMapping("/updateAccount")
    public RestfulResult<?> updateAccount(@RequestBody UpdateAccountParam updateAccountParam) {
        boolean result = userService.updateAccount(updateAccountParam);
        if (result) {
            return RestfulResult.success();
        }
        return RestfulResult.failure();
    }

    @PostMapping("/updatePassword")
    public RestfulResult<?> updatePassword(@RequestBody UpdatePassParam updatePassParam) {
        String result = userService.updatePassword(updatePassParam);
        if (Commons.RESULT_SUCCESS.equals(result)) {
            return RestfulResult.success();
        } else {
            return RestfulResult.failure(result);
        }
    }

    @PostMapping("/resetPassword")
    public RestfulResult<?> resetPassword(@RequestBody ResetPassParam resetPassParam) {
        String result = userService.resetPassword(resetPassParam);
        if (Commons.RESULT_SUCCESS.equals(result)) {
            return RestfulResult.success();
        } else {
            return RestfulResult.failure(result);
        }
    }

    @DeleteMapping("/del/{username}")
    public RestfulResult<?> del(@PathVariable String username) {
        try {
            if (userService.deleteUser(username)) {
                return RestfulResult.success();
            }
        } catch (BusinessException e){
            return RestfulResult.failure(e.getMessage());
        }
        return RestfulResult.failure();
    }

    @PostMapping("/updateUserRole")
    public RestfulResult<?> updateUserRole(@RequestBody UpdateUserRoleParam userRoleParam) {
        userService.updateUserRole(userRoleParam);
        return RestfulResult.success();
    }

}
