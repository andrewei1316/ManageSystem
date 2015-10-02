package com.manageSystem.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manageSystem.dao.UserDAO;
import com.manageSystem.po.User;
import com.manageSystem.service.MapService;
import com.manageSystem.service.UserService;
import com.manageSystem.service.impl.MapServiceImpl;
import com.manageSystem.service.impl.UserServiceImpl;

@Controller
public class UserAbout {
	
	@Resource(name = "UserService")
	UserService userService;
	
	@Resource(name = "MapService")
	MapService mapService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		//if(request.getSession().getAttribute("currUser") == null){
			return "login";
		//}else return "main";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("LogInCheck")
	public String LogInCheck(HttpServletRequest request, String userLogId, String userPws){ 
		// µÇÂ¼·½·¨ÖØÐ´
		if(true){
	  		User currUser = userService.getUserByUserLogId("1");
	  		//User currUser = userService.getUserByUserId(1);
	  		HashMap<Integer, String> attriMap = mapService.queryMap();
	  		request.getSession().setAttribute("currUser", currUser);
	  		request.getSession().setAttribute("attriMap", attriMap);
			return Main(request);
		}else return "login";
		
	}
	
	public String Main(HttpServletRequest request){
		return "main";
	}

}
