package com.zhaozm.shirodemo;

import com.zhaozm.shirodemo.pojo.Permission;
import com.zhaozm.shirodemo.pojo.Role;
import com.zhaozm.shirodemo.pojo.User;
import com.zhaozm.shirodemo.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user =(User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList=new ArrayList<String>();
        List<String> roleNameList=new ArrayList<>();
        Set<Role> roles=user.getRoles();
        if(CollectionUtils.isNotEmpty(roles)){
          for(Role role :roles){
              Set<Permission> permissionSet=role.getPermissions();
              roleNameList.add(role.getRname());
              if(CollectionUtils.isNotEmpty(permissionSet)){
                  for(Permission permission:permissionSet){
                      permissionList.add(permission.getName());
                  }
              }
          }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    //认证

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
        String username=usernamePasswordToken.getUsername();
        User user=userService.findByUsername(username);

        return new SimpleAuthenticationInfo(user ,user.getPassword(),this.getClass().getName());
    }
}
