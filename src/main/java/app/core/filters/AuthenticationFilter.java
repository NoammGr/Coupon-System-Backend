package app.core.filters;

import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class AuthenticationFilter implements Filter {
    private final JwtUtil jwtUtil;

    public AuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter started !");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            System.out.println("filter caught a pre-flight request and pass it on");
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String auth = httpServletRequest.getHeader("Authorization");

        if (auth != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(auth);
            String jwt = stringTokenizer.nextToken();
            try {
                UserCredentials userCredentials = jwtUtil.extractUser(jwt);
                httpServletRequest.setAttribute("user", userCredentials);
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
        System.out.println("Filter ended !");
    }
}
