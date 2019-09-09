package cn.sunline.tiny.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import cn.sunline.tiny.web.Context;
import cn.sunline.tiny.web.filter.BaseFilter;
import cn.sunline.tiny.web.filter.FilterResult;

@Component("corsFilter")
public class CorsFilter extends BaseFilter implements Filter{

    @Override
    public FilterResult doControl(Context ct, HttpServletRequest servletRequest) {
	// TODO Auto-generated method stub
	//HttpServletResponse response = (HttpServletResponse) servletResponse;
	HttpServletRequest request = (HttpServletRequest)servletRequest;
	//FilterChain filterChain = null;
	String origin = request.getHeader("Origin");
	
	
//	try {
//	    doFilter(servletRequest, ct.getResponse(), filterChain);
//	} catch (IOException | ServletException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//	ct.getRequest().getSession().setMaxInactiveInterval(Integer.parseInt("1800"));
//	ct.getResponse().setHeader("Access-Control-Allow-Origin",origin);
//	ct.getResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//	ct.getResponse().setHeader("Access-Control-Max-Age", "3600");
//	ct.getResponse().setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
//	ct.getResponse().setHeader("X-Content-Type-Options", "nosniff");
////	ct.getResponse().setHeader("Content-Security-Policy", "default-src 'self' 'unsafe-inline'; object-src 'none'");
//	ct.getResponse().setHeader("Access-Control-Allow-Credentials", "true");
//	ct.getResponse().setHeader("Pragma", "no-cache");
	
//	String method = request.getMethod();
//	if(method.equalsIgnoreCase("OPTIONS")){
//             //servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
//         }else{
//             //filterChain.doFilter(servletRequest, servletResponse);
//         }
//	doControlFilter(ct, servletRequest, null, null);
	return FilterResult.SUCCESS;
    }
    
//    public FilterResult doControlFilter(Context ct, HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) {
//	
//	return null;
//	
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	    throws IOException, ServletException {
	// TODO Auto-generated method stub
	HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
 
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
        if(method.equalsIgnoreCase("OPTIONS")){
            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub
	
    }

}
