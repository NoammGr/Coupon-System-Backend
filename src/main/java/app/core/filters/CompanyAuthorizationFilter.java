package app.core.filters;


import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
import app.core.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class CompanyAuthorizationFilter implements Filter {
    private final JwtUtil jwtUtil;

    public CompanyAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Company filter started !");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String auth = httpServletRequest.getHeader("Authorization");
        if (auth != null) {

            StringTokenizer stringTokenizer = new StringTokenizer(auth);
            String schema = stringTokenizer.nextToken();
            String jwt = stringTokenizer.nextToken();

            UserCredentials userCredentials = jwtUtil.extractUser(jwt);

            httpServletRequest.setAttribute("company", userCredentials);

            if (userCredentials.getClientType() == ClientType.COMPANY) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "only companies are authorized !");
            }
        } else {
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "You need to login !");
        }

        System.out.println("Company filter ended !");
    }
}
