package app.core;

import app.core.auth.AdminJwtUtil;
import app.core.auth.CompanyJwtUtil;
import app.core.auth.CustomerJwtUtil;
import app.core.filters.*;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSystemProjectNoamApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponSystemProjectNoamApplication.class, args);
    }

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("title").version("version").description("description")).addSecurityItem(new SecurityRequirement().addList("my security")).components(new Components().addSecuritySchemes("my security", new SecurityScheme().name("my security").type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }

//    @Bean
//    FilterRegistrationBean<AdminAuthenticationFilter> AdminAuthenticationFilter(AdminJwtUtil adminJwtUtil) {
//        FilterRegistrationBean<AdminAuthenticationFilter> regBean = new FilterRegistrationBean<>();
//        regBean.setFilter(new AdminAuthenticationFilter(adminJwtUtil));
//        regBean.addUrlPatterns("/admin/*");
//        return regBean;
//    }

//    @Bean
//    FilterRegistrationBean<CompanyAuthenticationFilter> CompanyAuthenticationFilter(CompanyJwtUtil companyJwtUtil) {
//        FilterRegistrationBean<CompanyAuthenticationFilter> regBean = new FilterRegistrationBean<>();
//        regBean.setFilter(new CompanyAuthenticationFilter(companyJwtUtil));
//        regBean.addUrlPatterns("/company/*");
//        return regBean;
//    }

//    @Bean
//    FilterRegistrationBean<CustomerAuthenticationFilter> CustomerAuthenticationFilter(CustomerJwtUtil customerJwtUtil) {
//        FilterRegistrationBean<CustomerAuthenticationFilter> regBean = new FilterRegistrationBean<>();
//        regBean.setFilter(new CustomerAuthenticationFilter(customerJwtUtil));
//        regBean.addUrlPatterns("/customer/*");
//        return regBean;
//    }

    @Bean
    FilterRegistrationBean<AdminAuthorizationFilter> adminAuthFilter(AdminJwtUtil adminJwtUtil) {
        FilterRegistrationBean<AdminAuthorizationFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new AdminAuthorizationFilter());
        regBean.addUrlPatterns("/admin/api/*");
        return regBean;
    }

    @Bean
    FilterRegistrationBean<CompanyAuthorizationFilter> companyAuthFilter(CompanyJwtUtil companyJwtUtil) {
        FilterRegistrationBean<CompanyAuthorizationFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new CompanyAuthorizationFilter());
        regBean.addUrlPatterns("/company/api/*");
        return regBean;
    }

    @Bean
    FilterRegistrationBean<CustomerAuthorizationFilter> CustomerAuthFilter(CustomerJwtUtil customerJwtUtil) {
        FilterRegistrationBean<CustomerAuthorizationFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new CustomerAuthorizationFilter());
        regBean.addUrlPatterns("/customer/api/*");
        return regBean;
    }

}
