package app.core.filters;


import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
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

public class AdminAuthorizationFilter implements Filter {
    private final JwtUtil jwtUtil;

    public AdminAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
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

            UserCredentials userCredentials = jwtUtil.extractUser(jwt);

            httpServletRequest.setAttribute("admin", userCredentials);

            if (userCredentials.getClientType() == ClientType.ADMIN) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "only admins are authorized !");
            }
        } else {
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "You need to login !");
        }
    }

}
