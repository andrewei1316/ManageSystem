package com.manageSystem.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  

public class LoginInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request,   
	        HttpServletResponse response, Object handler) throws Exception {  
	    Object obj = request.getSession().getAttribute("currUser");  
	    if(obj==null){  
	        request.getRequestDispatcher("index").forward(request, response);   
	        return false;  
	    }else{  
	        return super.preHandle(request, response, handler);  
	    }  
	}  
}
