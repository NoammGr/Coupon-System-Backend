package app.core.filters;


import app.core.auth.UserCredentials;
import app.core.connectionsystem.ClientType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyAuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Company filter started !");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        UserCredentials userCredentials = (UserCredentials) httpServletRequest.getAttribute("user");
        if (userCredentials.getClientType() == ClientType.COMPANY) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "only admins are authorized !");
        }

        System.out.println("Company filter ended !");
    }
}
