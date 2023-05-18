package app.core.filters;

import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyAuthorizationFilter implements Filter {
    private final JwtUtil jwtUtil;

    public CompanyAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        if (httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
        } else {
            String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);
                UserCredentials userCredentials = jwtUtil.extractUser(jwt);
                httpServletRequest.setAttribute("company", userCredentials);
                if (userCredentials.getClientType() == ClientType.COMPANY) {
                    chain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                    httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Only companies are authorized!");
                }
            } else {
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "You need to login!");
            }
        }
    }
}
