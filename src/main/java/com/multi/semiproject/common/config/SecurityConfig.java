package com.multi.semiproject.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableWebSecurity(debug = true) //URL이 어떤 Security Filter를 거치는지 console에 보여줌
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    InMemoryUserDetailsManger:
                            - UserDetailsService(interface)의 구현체
                            - 테스트 환경에서 쓰고 싶을 때 사용
                            - 애플리케이션 종료 시 사라짐
                            - username/password를 메모리에 저장해서 authentication 객체에 저장
    UserDetails:
                - 사용자 정보 저장
    * */

    //test용
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user1234")) // InMemory에 1234를 암호화해서 저장, DaoAuthenticationProvider.class의 90 line에서 matches emthod로 비교
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin1234")) // InMemory에 1234를 암호화해서 저장, DaoAuthenticationProvider.class의 90 line에서 matches emthod로 비교
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    //test용
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/test").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").authenticated()
                        .anyRequest().permitAll()
                )

                .formLogin(form->form
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/",true)
                        .failureForwardUrl("/error/error"))
                .logout(logout->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/"))
                .exceptionHandling(exception->exception.accessDeniedPage("/error/error")


                );
        return httpSecurity.build();
    }

}
