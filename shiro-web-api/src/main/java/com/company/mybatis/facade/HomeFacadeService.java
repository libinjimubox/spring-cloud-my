package com.company.mybatis.facade;

import com.company.mybatis.dto.result.RolePermission;
import com.company.mybatis.pojo.Applications;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.service.ApplicationsService;
import com.company.mybatis.service.AuthService;
import com.company.mybatis.service.RoleAuthService;
import com.company.mybatis.service.RoleService;
import com.company.mybatis.service.UserRoleService;
import com.company.mybatis.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bin.li
 * @date 2020/10/9
 */
@Service
public class HomeFacadeService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthService roleAuthService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ApplicationsService applicationsService;

    public List<String> getAuthByRoleId(Integer roleId) {
        Role role = roleService.selectByPrimaryKey(roleId);
        List<String> authValueList = roleAuthService.findAuthByRoleId(role.getRoleId());
        return authValueList;
    }

    public List<RolePermission> getAuthWithoutRole() {
        List<RolePermission> rolePermissions = Lists.newArrayList();
        List<Applications> applications = applicationsService.selectAll();
        for (Applications application : applications) {
            Integer appId = application.getAppId();
            String appName = application.getAppName();
            String appDesc = application.getAppDesc();
            RolePermission rp = RolePermission.builder().id(appName).label(appDesc).disabled(Boolean.FALSE).build();
            List<Auth> auths = authService.findAuthByAppId(appId);
            if (CollectionUtils.isNotEmpty(auths)) {
                List<RolePermission> childPermission = auths.stream().map(auth -> RolePermission.builder().label(auth.getAuthName()).id(auth.getAuthValue()).build()).collect(Collectors.toList());
                rp.setChildren(childPermission);
            }
            rolePermissions.add(rp);
        }
        return rolePermissions;
    }

    public List<String> selectUserAuthValue(Integer userId) {
        Integer roleId = userRoleService.findRoleByUserId(userId);
        Role role = roleService.selectByPrimaryKey(roleId);
        List<String> roleAuthValues = roleAuthService.findAuthByRoleId(role.getRoleId());
        return roleAuthValues;
    }
}
