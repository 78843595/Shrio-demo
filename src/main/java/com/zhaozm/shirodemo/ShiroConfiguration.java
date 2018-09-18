package com.zhaozm.shirodemo;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager manager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");
        LinkedHashMap<String,String> filterChainDefInitionMap=new LinkedHashMap<>();
        filterChainDefInitionMap.put("/index", "authc");
        filterChainDefInitionMap.put("/login", "anon");
        filterChainDefInitionMap.put("/loginUser", "anon");
        filterChainDefInitionMap.put("/admin", "roles[admin]");
        filterChainDefInitionMap.put("/edit", "perms[edit]");

        filterChainDefInitionMap.put("/druid/**", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefInitionMap);
        return bean;

    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authReam(@Qualifier("credentialMatcher") CredentialMatcher matcher){
        AuthRealm authRealm= new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        return authRealm;
    }
    @Bean("credentialMatcher")
   public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return  advisor;

    }
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
