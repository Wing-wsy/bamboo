package org.panda.business.admin.modules.system.api.controller;

import io.swagger.annotations.Api;
import org.panda.bamboo.common.constant.Commons;
import org.panda.business.admin.modules.system.service.SysRoleService;
import org.panda.business.admin.modules.system.service.dto.SysRoleDto;
import org.panda.business.admin.modules.system.service.entity.SysRole;
import org.panda.tech.core.exception.business.BusinessException;
import org.panda.tech.core.web.restful.RestfulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色权限管理
 *
 * @author fangen
 */
@Api(tags = "系统角色管理")
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @GetMapping("/getRoles")
    public RestfulResult<?> getRoles() {
        List<SysRoleDto> roles = roleService.getRoles();
        return RestfulResult.success(roles);
    }

    @PostMapping("/add")
    public RestfulResult<?> add(@RequestBody SysRole role) {
        String result = roleService.addRole(role);
        if (Commons.RESULT_SUCCESS.equals(result)) {
            return RestfulResult.success();
        }
        return RestfulResult.failure(result);
    }

    @PutMapping("/updateRoleMenu/{roleId}")
    public RestfulResult<?> updateRoleMenu(@PathVariable Integer roleId, @RequestBody SysRoleDto roleDTO) {
        roleService.updateRoleMenu(roleId, roleDTO);
        return RestfulResult.success();
    }

    @DeleteMapping("/del/{roleId}")
    public RestfulResult<?> del(@PathVariable Integer roleId) {
        try {
            int result = roleService.deleteRole(roleId);
            if (result < 1) {
                return RestfulResult.failure();
            }
        }catch (BusinessException e){
            return RestfulResult.failure(e.getMessage());
        }
        return RestfulResult.success();
    }
}
