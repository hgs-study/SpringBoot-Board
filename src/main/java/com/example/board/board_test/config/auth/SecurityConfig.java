package com.example.board.board_test.config.auth;

import com.example.board.board_test.domain.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable합니다.
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() //URL별 권한관리 설정하는 옵션의 시작점입니다.
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll() //권한 관리 대상을 지정하는 옵션
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
