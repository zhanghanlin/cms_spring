package com.demo.java.web.shiro.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.demo.java.common.dict.Status;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.Constants;

public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @SuppressWarnings("unchecked")
    <T> List<T> getUserAttr(Long userId, String attr, Class<T> clazz) {
        Subject subject = SecurityUtils.getSubject();
        List<T> list = (List<T>) subject.getSession().getAttribute(attr);
        if ((list == null) || list.isEmpty()) {
            if (attr.equals(Constants.current_user_role_key)) {
                list = (List<T>) roleService.findByUserId(userId);
            } else if (attr.equals(Constants.current_user_menu_key)) {
                list = (List<T>) menuService.findByUserId(userId);
            }
            if ((list != null) && !list.isEmpty()) {
                subject.getSession().setAttribute(attr, list);
            }
        }
        return list;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.findByLogin(userName);
        List<Role> roles = getUserAttr(user.getId(), Constants.current_user_role_key, Role.class);
        List<Menu> menus = getUserAttr(user.getId(), Constants.current_user_menu_key, Menu.class);
        if ((user != null) && (roles != null) && (menus != null)) {
            for (Role r : roles) {
                authorizationInfo.addRole(r.getUniqueKey());
            }
            for (Menu m : menus) {
                authorizationInfo.addStringPermission(m.getUniqueKey());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.findByLogin(userName);
        if (user == null) {
            throw new UnknownAccountException(); // not found user
        }
        if (Status.DISABLE == user.getStatus()) {
            throw new LockedAccountException(); // user is disable
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}