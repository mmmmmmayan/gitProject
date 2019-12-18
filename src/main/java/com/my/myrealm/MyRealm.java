package com.my.myrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {

    //根据传进来的token去数据库中查询并返回 不做对比
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //拿到身份信息
        String principal = (String) authenticationToken.getPrincipal();

        //用身份信息去数据库中查询 返回一个对象
        AuthenticationInfo authenticationInfo = null;
        if (principal.equals("zhangsan")) {
            authenticationInfo = new SimpleAuthenticationInfo("zhangsan", "894b3913a4a13b25dc6186d11835c209", ByteSource.Util.bytes("abc"), this.getName());
        }
        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzz");
        return authenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //拿到主要身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        if ("zhangsan".equals(primaryPrincipal)) {
            //如果用户名正确 赋予角色并授权
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("admin");
            simpleAuthorizationInfo.addStringPermission("user:*");
            return simpleAuthorizationInfo;
        }

        return null;
    }
}
