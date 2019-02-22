package com.linchtech.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: 107
 * @date: 2019/2/20 10:41
 * @description: 访问权限配置（URL的认证。可配置拦截什么URL，设置什么权限等安全限制）
 * @Review:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected UserDetailsService userDetailsService() {
        // 自定义用户信息类
        return this.userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder(){
            @Override
            public String encode(CharSequence charSequence) {
                // 加密
                return charSequence.toString();
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                // 密码校验
                return charSequence.toString().equals(s);
            }
        }) ;
    }
}
