package com.alkemy.ong.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class ControllerFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("ONG-API", "OT-278");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
    
}
