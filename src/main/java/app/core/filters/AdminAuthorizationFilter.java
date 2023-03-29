package app.core.filters;

import app.core.auth.AdminJwtUtil;
import app.core.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

@Component
public class AdminAuthorizationFilter implements Filter {
    @Autowired
    AdminJwtUtil adminJwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Admin filter started !");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String auth = httpServletRequest.getHeader("Authorization");
        StringTokenizer stringTokenizer = new StringTokenizer(auth);
        String jwt = stringTokenizer.nextToken();

        Admin admin = adminJwtUtil.extractUser(jwt);

        httpServletRequest.setAttribute("admin", admin);

        if (admin.getEmail().equals("admin@admin.com")) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "only admins are authorized !");
        }

        // pass the request on
        System.out.println("Admin filter ended !");
    }

}
