/**
 * 
 */
package by.malinouski.hrace.filter;

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

import by.malinouski.hrace.constant.PathConsts;

/**
 * The Class LogoutFilter.
 *
 * @author makarymalinouski
 */
@WebFilter(filterName = "logout")
public class LogoutFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath() + PathConsts.INDEX);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}


	
}
