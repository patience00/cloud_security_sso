package com.linchtech.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.sql.DataSource;

/**
 * @author: 107
 * @date: 2019/2/22 9:30
 * @description:
 * @Review:
 */
@Configuration
@EnableAuthorizationServer
public class ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置token获取和验证时的策略
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // secret和密码从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置tokenStore，保存到redis缓存中
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .userDetailsService(userDetailsService);
    }

}
