package com.linchtech.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

    // @Bean
    // public static NoOpPasswordEncoder passwordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

}
