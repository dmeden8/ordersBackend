package hr.ddcode.cafford.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

//@Component
//@Order(-1000) 
public class CORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain)
            throws ServletException, IOException {
    	
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	response.addHeader("Access-Control-Expose-Headers", "x-auth-token");
    	if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,x-auth-token");
            response.setStatus(HttpServletResponse.SC_OK);              
        } 
    	else {
        	filterChain.doFilter(request, response);
        }
    }
    
}
