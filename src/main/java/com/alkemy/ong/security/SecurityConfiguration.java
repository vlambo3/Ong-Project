package com.alkemy.ong.security;

import com.alkemy.ong.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/auth/*",
                        "/v2/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/api/docs"
                ).permitAll()
                .antMatchers("/users*").hasRole("ADMIN")
                .antMatchers("/slides").hasRole("ADMIN")
                .antMatchers("/activities").hasRole("ADMIN")
                .antMatchers("/categories").hasRole("ADMIN")
                .antMatchers("/slides").hasRole("ADMIN")
                .antMatchers("/news").hasRole("ADMIN")
                .antMatchers("/categories/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST ,"/testimonials").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/testimonials/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/organization/public").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/contacts").permitAll()
                .antMatchers(HttpMethod.GET,"/contacts").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/news").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
