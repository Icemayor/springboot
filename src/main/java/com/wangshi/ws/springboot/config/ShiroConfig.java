package com.wangshi.ws.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.wangshi.ws.springboot.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器,可以实现权限相关的拦截器
        /**常用的过滤器：
         *  anon: 无需认证登录可以访问
         *  authc: 必需认证才可以访问
         *  user: 使用rememberMe的功能可以访问
         *  perms: 该资源必需得到权限认证才可以访问
         *  role: 该资源必需得到角色认证才可以访问
         */
        Map<String, String> shiroMap = new LinkedHashMap<String, String>();

        //无需过滤的页面(在拦截之前就需要把放行的页面先过滤，顺序很重要）
        shiroMap.put("/test", "anon");
        //把login也放行
        shiroMap.put("/login", "anon");
        shiroMap.put("/gifCode", "anon");

        //授权过滤器
        shiroMap.put("/alterUsername", "perms[user:alterUsername]");
        shiroMap.put("/alterPassword", "perms[user:alterPassword]");
        //setLoginUrl("方法名")
//        shiroMap.put("/alterUsername", "authc");
//        shiroMap.put("/alterPassword", "authc");
        //需要过滤的页面
        shiroMap.put("/*", "authc");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //添加未授权的默认页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        //rememberMe



        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联UserRealm,否则报错，找不到Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建UserRealm
     * @return
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
