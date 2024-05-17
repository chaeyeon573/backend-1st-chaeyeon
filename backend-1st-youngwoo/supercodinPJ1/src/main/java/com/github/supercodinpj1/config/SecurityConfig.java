package com.github.supercodinpj1.config;

import com.github.supercodinpj1.dto.Jwt.JwtAccessDeniedHandler;
import com.github.supercodinpj1.dto.Jwt.JwtAuthenticationFilter;
import com.github.supercodinpj1.dto.Jwt.JwtExceptionFilter;
import com.github.supercodinpj1.dto.Jwt.JwtProvider;
import com.github.supercodinpj1.service.Jwt.JwtService;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final JwtService jwtService;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .disable()
                .csrf().disable()   //rest api이므로 basic auth 및 csrf 보안을 사용하지 않음.
                .formLogin().disable()
                .logout().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    //session 사용 하지 않음

        http    //특정 URL에 대한 권한 설정
                .authorizeRequests()
                .requestMatchers("/api").permitAll()    //모든 권한 허용
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/api/signup").permitAll()
                .requestMatchers(String.valueOf(HttpMethod.POST),"/api/post/**").permitAll()
                .requestMatchers(String.valueOf(HttpMethod.PUT),"/api/post/**").permitAll()
                .requestMatchers(String.valueOf(HttpMethod.DELETE),"/api/post/**").permitAll()
                .requestMatchers(String.valueOf(HttpMethod.POST),"/api/comment/**").permitAll()
                .requestMatchers(String.valueOf(HttpMethod.PUT),"/api/comment/**").permitAll()
                .requestMatchers(String.valueOf(HttpMethod.DELETE),"/api/comment/**").permitAll();
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, jwtService), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtExceptionFilter,JwtAuthenticationFilter.class);

        http
                .exceptionHandling()
                .accessDeniedHandler(new JwtAccessDeniedHandler());

        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

}