package com.multi.semiproject.common.config;


import com.multi.semiproject.authentication.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return(web -> web.ignoring().requestMatchers("/static/**"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Map<String, List<String>> permitMap = customUserDetailService.getPermitListMap()         ;
        List<String> adminList = permitMap.get("adminPermitList");
        List<String> memberList = permitMap.get("memberPermitList");

        http.csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests((authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(adminList.toArray(new String[adminList.size()])).hasRole("ADMIN")
                        .requestMatchers(memberList.toArray(new String[memberList.size()])).hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                ))
                .formLogin(form -> form
                        .loginPage(("/member/login"))
                        .defaultSuccessUrl("/",true)
                        .failureForwardUrl("/error/login"))
                .logout(logout->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/")
                )
                .exceptionHandling(exception-> exception
                        .accessDeniedPage("/error/denied"));
        return http.build();
    }


}
