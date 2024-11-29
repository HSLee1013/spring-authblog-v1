package com.metacoding.autoblog._core.config;

import com.metacoding.autoblog.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // csrf 규칙을 안지키면 막으므로 해제해야한다.
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(r ->
                        r.requestMatchers("/s/**").authenticated().anyRequest().permitAll())
                .formLogin(f ->
                        f.loginPage("/login-form").permitAll()
                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/")
                                .successHandler((request, response, authentication) -> {
                                    User user = (User) authentication.getPrincipal();
                                    HttpSession session = request.getSession();
                                    session.setAttribute("sessionUser", user);

                                    response.sendRedirect("/");
                                }));

//        http.authorizeHttpRequests(r ->
//                        r.requestMatchers("/s/**").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                                .anyRequest().permitAll())
//                .formLogin(f ->
//                        f.loginPage("/login-form")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/"));
        return http.build();
    }
}
