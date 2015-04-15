package lockc.websocket.example;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})
public class MyWebFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hsr = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String appKey = hsr.getHeader("X-Application");
		if(appKey == null) {
			httpResponse.setStatus(401);
			httpResponse.getWriter().write("NO_APP_KEY");
			httpResponse.getWriter().flush();
			return;
		}
		
		String sessionToken = hsr.getHeader("X-Authentication");
		if(sessionToken == null) {
			httpResponse.setStatus(401);
			httpResponse.getWriter().write("NO_SESSION_INFORMATION");
			httpResponse.getWriter().flush();
			return;
		}
		
		// All fine pass down the chain
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
