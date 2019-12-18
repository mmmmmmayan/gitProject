package com.my.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class TestShiro {

    public static void main(String[] args) {

        //从工厂拿到 安全管理器
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        //给安全管理工具  设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //拿到主体需要的 用户名和密码 进行认证
        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "123");
        //拿到主体
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //主体是否被认证
        boolean b = subject.isAuthenticated();
        System.out.println(b);
    }
}
