package org.panda.business.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
//import org.panda.bamboo.common.annotation.helper.EnumValueHelper;
import org.panda.bamboo.common.constant.Commons;
import org.panda.bamboo.common.constant.basic.Strings;
import org.panda.bamboo.common.util.LogUtil;
import org.panda.business.admin.application.resolver.MessageSourceResolver;
import org.panda.business.admin.common.constant.AuthConstants;
import org.panda.business.admin.modules.system.api.vo.MenuVO;
import org.panda.business.admin.modules.system.api.vo.UserVO;
import org.panda.business.admin.modules.system.service.SysMenuService;
import org.panda.business.admin.modules.system.service.SysUserService;
import org.panda.business.admin.modules.system.service.dto.SysUserDto;
import org.panda.business.admin.modules.system.service.entity.SysRole;
import org.panda.business.admin.modules.system.service.entity.SysUser;
import org.panda.business.admin.modules.system.service.repository.SysRoleMapper;
import org.panda.business.admin.modules.system.service.repository.SysUserMapper;
import org.panda.tech.core.exception.business.BusinessException;
import org.panda.tech.data.model.query.QueryResult;
import org.panda.tech.data.mybatis.util.QueryPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.panda.business.admin.modules.system.api.param.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author bamboo-code-generator
 * @since 2023-06-20
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private MessageSourceResolver messageSourceResolver;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuService menuService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private SysUserTokenService userTokenService;
//    @Autowired
//    private SettingsManager settingsManager;

    @Override
    public SysUserDto getUserAndRoles(String username) {
        SysUser userParam = new SysUser();
        userParam.setUsername(username);
        SysUserDto sysUserDto = this.baseMapper.findUserAndRoles(userParam);
        if (sysUserDto != null) {
            List<SysRole> roles = sysUserDto.getRoles();
            if(CollectionUtils.isNotEmpty(roles)) {
                if (roles.get(0).getId() != null) {
                    Set<String> roleCodes = roles.stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
                    sysUserDto.setRoleCodes(roleCodes);
                } else { // 未绑定角色
                    sysUserDto.setRoles(null);
                }
            }
        }
        return sysUserDto;
    }

    @Override
    public QueryResult<UserVO> getUserByPage(UserQueryParam queryParam) {
        Page<SysUser> page = new Page<>(queryParam.getPageNo(), queryParam.getPageSize());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(queryParam.getKeyword())) {
            queryWrapper.like(SysUser::getUsername, queryParam.getKeyword()).or()
                        .like(SysUser::getNickname, queryParam.getKeyword());
        }
        queryWrapper.orderByDesc(SysUser::getCreateTime);
        IPage<SysUser> userPage = this.page(page, queryWrapper);

        List<UserVO> userVOList = new ArrayList<>();
        if (userPage.getTotal() > 0) {
            List<SysUser> users = userPage.getRecords();
            users.forEach(user -> {
                UserVO userVO = new UserVO(user);
                List<SysRole> roles = sysRoleMapper.findRolesByUserId(user.getId());
                if(CollectionUtils.isNotEmpty(roles)) {
                    Set<String> roleCodes = roles.stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
                    userVO.setRoleCodes(roleCodes);
                    userVO.setRoles(roles);
                }
                Set<String> roleCodeAndIds = new HashSet<>();
                for (SysRole role : roles) {
                    roleCodeAndIds.add(role.getRoleCode() + Strings.VERTICAL_BAR + role.getId());
                }
                userVO.setRoleCodeAndIds(roleCodeAndIds);
                userVOList.add(userVO);
            });
        }
        return QueryPageHelper.convertQueryResult(userVOList, userPage);
    }

    @Override
    public UserVO getUserByToken(String token) {
        String username = token;
        SysUserDto sysUserDto = this.getUserAndRoles(username);

        UserVO userVO = new UserVO(sysUserDto.getUser());
        userVO.setRoleCodes(sysUserDto.getRoleCodes());
        List<MenuVO> routes = menuService.getRoutes();
        userVO.setRoutes(routes);
        return userVO;
    }

    @Override
    public SysUser getUserInfo(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        return this.getOne(queryWrapper);
    }

    @Override
    public String addUser(AddUserParam userParam) {
        // 校验username不能重复
        String username = userParam.getUsername();
        SysUser userPO = this.getUserInfo(username);
        if (userPO != null) {
            String errorMessage = messageSourceResolver.findI18nMessage("admin.system.user.error_add");
            LogUtil.error(getClass(), errorMessage);
            return errorMessage;
        }
        String password = userParam.getPassword();
        if (StringUtils.isEmpty(password)) {
            Optional<String> initPassword = null;
            if (initPassword.isPresent()) {
                password = initPassword.get();
            } else {
                return null;
            }
        }
        String encodePassword = null;
        SysUser user = new SysUser();
        if (StringUtils.isEmpty(userParam.getUserType())) {
//            userParam.setUserType(EnumValueHelper.getValue(UserType.CUSTOMER));
        }
        user.setUserType(userParam.getUserType());
        user.setPassword(encodePassword);
        user.setUsername(userParam.getUsername());
        user.setPhone(userParam.getPhone());
        user.setNickname(userParam.getNickname());
        user.setEmail(userParam.getEmail());
        // 为空时填入2-未知
        user.setSex(StringUtils.isEmpty(userParam.getSex()) ? "2" : userParam.getSex());
        if (this.save(user)) {
            return Commons.RESULT_SUCCESS;
        } else {
            return Commons.RESULT_FAILURE;
        }
    }

    @Override
    public boolean updateUser(SysUser user) {
        return this.updateById(user);
    }

    @Override
    public boolean updateAccount(UpdateAccountParam updateAccountParam) {
//        UserSpecificDetails userSpecificDetails = SecurityUtil.getAuthorizedUserDetails();
        String principalUsername = null;
        Integer id = updateAccountParam.getId();
        SysUser sysUser = this.getById(id);
        // 用户本人才可以修改自己的账户信息
        if (principalUsername.equals(sysUser.getUsername())) {
            SysUser updateUser = new SysUser();
            updateUser.setId(id);
            updateUser.setNickname(updateAccountParam.getNickname());
            updateUser.setPhone(updateAccountParam.getPhone());
            updateUser.setEmail(updateAccountParam.getEmail());
            updateUser.setSex(updateAccountParam.getSex());
            return this.updateById(updateUser);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) throws BusinessException {
        if (!this.checkTopRoles()) {
            return false;
        }
//        UserSpecificDetails userSpecificDetails = SecurityUtil.getAuthorizedUserDetails();
        // 删除操作排除自己
        String principalUsername =null;
        if (username.equals(principalUsername)) {
            String errorMessage = messageSourceResolver.findI18nMessage("admin.system.user.error_del");
            throw new BusinessException(errorMessage);
        }

        // 校验是否绑定的有角色
        SysUserDto useDto = this.getUserAndRoles(username);
        List<SysRole> roles = useDto.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            String errorMessage = messageSourceResolver.findI18nMessage("admin.system.user.error_del.1");
            throw new BusinessException(errorMessage);
        }

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        return this.remove(queryWrapper);
    }

    @Override
    public void updateUserRole(UpdateUserRoleParam userRoleParam) {
        Set<String> roleCodeAndIds = userRoleParam.getRoleCodeAndIds();
        Set<Integer> roleIds = new HashSet<>();
        for (String roleCodeAndId : roleCodeAndIds) {
            if (roleCodeAndId.contains(Strings.VERTICAL_BAR)) {
                String roleId = roleCodeAndId.split("\\|")[1];
                if (StringUtils.isNotEmpty(roleId)) {
                    roleIds.add(Integer.parseInt(roleId));
                }
            }
        }
        this.baseMapper.updateUserRole(userRoleParam.getId(), roleIds);
    }

    @Override
    public String resetPassword(ResetPassParam resetPassParam) {
        Integer id = resetPassParam.getId();
        String principalUsername = null;
        SysUser sysUser = this.getById(id);
        // 具有相应角色权限的管理员才可以重置, 本人可以重置自己的密码,无需验证
        if (!sysUser.getUsername().equals(principalUsername)) {
            // 顶级用户角色才可以更新
            if (!this.checkTopRoles()) {
                return AuthConstants.ROLE_NOT_CHANGE_PASS;
            }
        }
        if (StringUtils.isEmpty(resetPassParam.getNewPassword())) {
            // 新密码为空，自动重置默认密码
            Optional<String> initPassword = null;
            if (initPassword.isPresent()) {
                resetPassParam.setNewPassword(initPassword.get());
            } else {
                return null;
            }
        }
        String newPasswordEncrypt = null;
        SysUser updateUser = new SysUser();
        updateUser.setId(id);
        updateUser.setPassword(newPasswordEncrypt);
        if (this.updateById(updateUser)) {
            return Commons.RESULT_SUCCESS;
        }
        return null;
    }

    @Override
    public String updatePassword(UpdatePassParam updatePassParam) {
        String principalUsername = null;
        Integer id = updatePassParam.getId();
        SysUser sysUser = this.getById(id);
        // 用户本人才可以修改自己的密码
        if (!principalUsername.equals(sysUser.getUsername())) {
            return AuthConstants.ROLE_NOT_CHANGE_PASS;
        }
        // 判断旧密码是否正确
//        if (!passwordEncoder.matches(updatePassParam.getOldPassword(), sysUser.getPassword())) {
//            return AuthConstants.PWD_WRONG;
//        }
        // 校验通过更新密码
        SysUser updateUser = new SysUser();
        String newPasswordEncrypt = null;
        updateUser.setId(id);
        updateUser.setPassword(newPasswordEncrypt);
        if (this.updateById(updateUser)) {
            // 踢出已在线的用户
//            userTokenService.kickOutOnlineUsers(id, sysUser.getUsername());
            return Commons.RESULT_SUCCESS;
        }
        return null;
    }

    public boolean checkTopRoles() {
//        UserSpecificDetails userSpecificDetails = SecurityUtil.getAuthorizedUserDetails();
        // 删除操作排除自己
        String principalUsername = null;

        SysUserDto useDto = this.getUserAndRoles(principalUsername);
        List<SysRole> roles = useDto.getRoles();
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }

        List<String> topRoles = null;
        List<SysRole> result = roles.stream()
                .filter(role -> topRoles.contains(role.getRoleCode()))
                .collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(result);
    }

}
