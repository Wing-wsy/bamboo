package org.panda.business.admin.modules.system.api.controller;

import io.swagger.annotations.Api;
import org.panda.business.admin.modules.system.api.vo.MenuVO;
import org.panda.business.admin.modules.system.service.SysMenuService;
import org.panda.business.admin.modules.system.service.dto.SysMenuDto;
import org.panda.business.admin.modules.system.service.entity.SysMenu;
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

import java.util.Collections;
import java.util.List;

/**
 * 菜单权限管理
 *
 * @author fangen
 */
@Api(tags = "系统菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private SysMenuService menuService;

    @GetMapping("/getRoutes")
    public RestfulResult<?> getRoutes() {
        List<MenuVO> routes = menuService.getRoutes();
        return RestfulResult.success(routes);
    }

    @GetMapping("/getMenus")
    public RestfulResult<?> getMenus(){
        List<SysMenuDto> routes = menuService.getMenus();
        return RestfulResult.success(routes);
    }

    @GetMapping("/getChildKeys")
    public RestfulResult<?> getChildKeys(Integer menuId){
        List<Integer> childKeys = menuService.getChildKeys(menuId);
        Collections.reverse(childKeys);
        return RestfulResult.success(childKeys);
    }

    @PostMapping("/add")
    public RestfulResult<?> add(@RequestBody SysMenu menu){
        menuService.addMenu(menu);
        return RestfulResult.success();
    }

    @PutMapping("/update")
    public RestfulResult<?> update(@RequestBody SysMenu menu){
        menuService.updateMenu(menu);
        return RestfulResult.success();
    }

    @DeleteMapping("/del/{menuId}")
    public RestfulResult<?> del(@PathVariable Integer menuId){
        try {
            int result = menuService.deleteMenu(menuId);
            if (result < 1) {
                return RestfulResult.failure();
            }
        }catch (BusinessException e){
            return RestfulResult.failure(e.getMessage());
        }
        return RestfulResult.success();
    }
}
