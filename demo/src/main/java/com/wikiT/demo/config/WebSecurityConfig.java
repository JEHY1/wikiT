package com.wikiT.demo.config;

import com.wikiT.demo.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userDetailService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(http -> http
                .requestMatchers("/login", "/signup", "/user").permitAll()
                .anyRequest().authenticated()
        ).formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/home")
        ).logout(logout -> logout
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
        ).csrf(csrf -> csrf
                .disable()
        ).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }
}
