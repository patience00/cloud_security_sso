package com.linchtech.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author: 107
 * @date: 2019/2/21 16:58
 * @description:
 * @Review:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/login"
                )
                // 访问上面路径无需登录认证权限
                .permitAll()
                //.antMatchers("/**")
                //.(SystemRoleEnum.ROLE_SYSTEM_ADMIN.getName())
                .anyRequest()
                .authenticated()
                .and()
                // 暂时禁用CSRF，否则无法提交表单
                .csrf().disable();
    }

}
