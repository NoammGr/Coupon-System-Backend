package app.core.filters;

import app.core.auth.CustomerJwtUtil;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.repositories.CustomerRepository;
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
public class CustomerAuthorizationFilter implements Filter {
    @Autowired
    CustomerJwtUtil customerJwtUtil;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Customer filter started !");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String auth = httpServletRequest.getHeader("Authorization");
        StringTokenizer stringTokenizer = new StringTokenizer(auth);
        String jwt = stringTokenizer.nextToken();

        Customer customer = customerJwtUtil.extractUser(jwt);

        httpServletRequest.setAttribute("customer", customer);

        if (customerRepository.existsByEmail(customer.getEmail())) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer \"general api\"");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "only customers are authorized !");
        }

        System.out.println("Customer filter ended !");
    }

}
