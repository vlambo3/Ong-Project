package com.alkemy.ong.security;


import com.alkemy.ong.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpMethod.*;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtRequestFilter jwtFilter;

    private final String[] developerRole = {"/v2/api-docs/**", "/swagger-ui/**", "/swagger-resources/**",
            "/configuration/**", "/api/docs"};
    private final String[] userRolePost = {"/comments", "/contacts", "/members"};
    private final String[] userRoleGet = {"/auth/me", "/categories", "/members", "/news", "/organization/public",
            "/posts/*/comments", "/testimonials", "/users/me"};
    private final String[] userRolePut = {"/comments/*", "/members/*"};
    private final String[] userRoleDelete = {"/comments/*", "/users/*"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/auth/*").permitAll()

                .antMatchers(developerRole).permitAll()

                .antMatchers(POST, userRolePost).hasAnyRole("ADMIN", "USER")
                .antMatchers(GET, userRoleGet).hasAnyRole("ADMIN", "USER")
                .antMatchers(PUT, userRolePut).hasAnyRole("ADMIN", "USER")
                .antMatchers(DELETE, userRoleDelete).hasAnyRole("ADMIN", "USER")

                .antMatchers(POST, "/**").hasRole("ADMIN")
                .antMatchers(GET, "/**").hasRole("ADMIN")
                .antMatchers(PUT, "/**").hasRole("ADMIN")
                .antMatchers(DELETE, "/**").hasRole("ADMIN")

                /*
                .antMatchers("/users",
                        "/slides",
                        "/activities",
                        "/categories",
                        "/news",
                        "/categories/{id}",
                        "/testimonials/{id}").hasRole("ADMIN")
                .antMatchers(
                        "/v2/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/api/docs"
                ).permitAll()
                .hasRole("DEVELOPER")
                .antMatchers(
                        "/users*",
                        "/members/**",
                        "/slides",
                        "/activities",
                        "/categories/**",
                        "/news",
                        "/testimonials/{id}"
                ).hasRole("ADMIN")
                .antMatchers(POST, "/testimonials").hasRole("ADMIN")
                .antMatchers(PUT, "/testimonials/*").hasRole("ADMIN")
                .antMatchers(POST, "/organization/public").hasRole("ADMIN")
                .antMatchers(POST, "/contacts").permitAll()
                .antMatchers(GET, "/contacts").hasRole("ADMIN")
                .antMatchers(GET, "/news").hasRole("ADMIN")
                .antMatchers(PUT,"/slides/{id}").hasRole("ADMIN")
                .antMatchers(DELETE,"/slides/{id}").hasRole("ADMIN")
                .antMatchers(GET,"/users/me").hasAnyRole("ADMIN","USER")
                 */

                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
