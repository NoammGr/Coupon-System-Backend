package app.core.configuration;

import app.core.auth.JwtUtil;
import app.core.filters.AdminAuthorizationFilter;
import app.core.filters.CompanyAuthorizationFilter;
import app.core.filters.CustomerAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationBean {
    @Bean
    org.springframework.boot.web.servlet.FilterRegistrationBean<AdminAuthorizationFilter> adminAuthFilter(JwtUtil jwtUtil) {
        org.springframework.boot.web.servlet.FilterRegistrationBean<AdminAuthorizationFilter> regBean = new org.springframework.boot.web.servlet.FilterRegistrationBean<>();
        regBean.setFilter(new AdminAuthorizationFilter(jwtUtil));
        regBean.addUrlPatterns("/admin/api/*");
        return regBean;
    }

    @Bean
    org.springframework.boot.web.servlet.FilterRegistrationBean<CompanyAuthorizationFilter> companyAuthFilter(JwtUtil jwtUtil) {
        org.springframework.boot.web.servlet.FilterRegistrationBean<CompanyAuthorizationFilter> regBean = new org.springframework.boot.web.servlet.FilterRegistrationBean<>();
        regBean.setFilter(new CompanyAuthorizationFilter(jwtUtil));
        regBean.addUrlPatterns("/company/api/*");
        return regBean;
    }

    @Bean
    org.springframework.boot.web.servlet.FilterRegistrationBean<CustomerAuthorizationFilter> CustomerAuthFilter(JwtUtil jwtUtil) {
        org.springframework.boot.web.servlet.FilterRegistrationBean<CustomerAuthorizationFilter> regBean = new org.springframework.boot.web.servlet.FilterRegistrationBean<>();
        regBean.setFilter(new CustomerAuthorizationFilter(jwtUtil));
        regBean.addUrlPatterns("/customer/api/*");
        return regBean;
    }
}
