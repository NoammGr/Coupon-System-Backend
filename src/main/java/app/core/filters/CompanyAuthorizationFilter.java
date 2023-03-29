package app.core.filters;

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
public class CompanyAuthorizationFilter implements Filter {
    CompanyJwtUtil companyJwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Company filter started !");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String auth = httpServletRequest.getHeader("Authorization");
        StringTokenizer stringTokenizer = new StringTokenizer(auth);
        System.out.println(auth);
        String jwt = stringTokenizer.nextToken();

        Company company = companyJwtUtil.extractUser(jwt);

        httpServletRequest.setAttribute("company", company);

        if (company != null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "only companies are authorized !");
        }

        System.out.println("Company filter ended !");
    }
}
