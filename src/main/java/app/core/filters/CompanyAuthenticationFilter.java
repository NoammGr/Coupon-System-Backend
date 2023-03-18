package app.core.filters;

import app.core.auth.AdminJwtUtil;
import app.core.auth.CompanyJwtUtil;
import app.core.entities.Admin;
import app.core.entities.Company;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

@Component
public class CompanyAuthenticationFilter implements Filter {

    private CompanyJwtUtil companyJwtUtil;

    public CompanyAuthenticationFilter(CompanyJwtUtil companyJwtUtil) {
        super();
        this.companyJwtUtil = companyJwtUtil;

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String auth = httpServletRequest.getHeader("Authorization");

        if (auth != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(auth);
            String schema = stringTokenizer.nextToken();
            String jwt = stringTokenizer.nextToken();
            try {
                Company company = companyJwtUtil.extractUser(jwt);
                httpServletRequest.setAttribute("company", company);
                chain.doFilter(httpServletRequest, response);
            } catch (Exception e) {
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://127.0.0.1:5500");
                httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "you need to login: " + e.getMessage());
            }
        } else {
            httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://127.0.0.1:5500");
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "you need to login ! ");
        }
    }
}
