package com.my;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class GitApp {

    public static void main(String[] args) {

        //从工厂获取安全管理器
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:token.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();

        //给安全管理工具 设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //拿到主体
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken("zhangsan", "123");
        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //主体是否被认证
        boolean authenticated = subject.isAuthenticated();
        //如果已经被认证 进行授权
        if (authenticated) {
            //基于角色
//            boolean b = subject.hasRole("admin"); //判断当前主体是具有当前角色

            /*判断当前主体是否具有下面指定角色
            List<String> list = new ArrayList<>();
            list.add("admin");
            list.add("super");
            boolean b = subject.hasAllRoles(list);
            */

            //基于资源
//            boolean b = subject.isPermitted("user:add:001");    //判断当前主体是否具有当前权限
//            boolean[] booleans = subject.isPermitted("user:add:001", "book:del"); //判读当前主体是否具有某个

            List<String> list = Arrays.asList("user:add", "book:del");
            boolean b = subject.isPermittedAll("user:add", "book:del");
            System.out.println(b);
            System.out.println("拉取成功  修改后重新推上去");
        }


    }
}
