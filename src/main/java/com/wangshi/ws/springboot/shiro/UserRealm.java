package com.wangshi.ws.springboot.shiro;

import com.wangshi.ws.springboot.model.User;
import com.wangshi.ws.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        String shiro = userService.findPerms(user.getUsername());
        info.addStringPermission(shiro);
        return info;
    }

    /**
     * 执行认证的逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证的逻辑");


        //编写shiro的判断逻辑，判断用户名和密码（这里其实可以用户名和密码一次性查出来）
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = new User();

        //在数据库查找用户名和密码
        Long i = userService.findUsername(token.getUsername());
        String password;
        if(i <= 0){
            return null;//如果用户名不存在的话，只要返回Null，shiro底层会抛出unknowAccountException
        }else {
            password = userService.findPassword(token.getUsername());
            user.setUsername(token.getUsername());
        }
        //判断密码
        return new SimpleAuthenticationInfo(user, password, "");
    }
}
