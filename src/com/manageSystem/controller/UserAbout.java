package com.manageSystem.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		if(request.getSession().getAttribute("currUser") == null){
			return "login";
		}else return "main";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("LogInCheck")
	public String LogInCheck(HttpServletRequest request, String userLogId, String userPws){ 
		User currUser = userService.getUserByUserLogId(userLogId);
		if(currUser != null && currUser.getUserPsw().compareTo(userPws) == 0){
			HashMap<Integer, String> attriMap = mapService.queryMap();
	  		request.getSession().setAttribute("currUser", currUser);
	  		request.getSession().setAttribute("attriMap", attriMap);
			return Main(request);
		}else return "login";
	}
	
	public String Main(HttpServletRequest request){
		return "main";
	}
	
	@RequestMapping("MotifyUser")
	public String MotifyUser(HttpServletRequest request){
		request.setAttribute("currUser", request.getSession().getAttribute("currUser"));
		return "motifyUser";
	}
	
	@RequestMapping("UpdateUser")
	public String UpdateUser(HttpServletRequest request, String userLogId, String userName, String oldUserPsw, String newUserPsw){
		User currUser = (User)request.getSession().getAttribute("currUser");
		currUser.setUserName(userName);
		if(oldUserPsw.compareTo(currUser.getUserPsw()) == 0){
			currUser.setUserPsw(newUserPsw);
		}
		request.getSession().setAttribute("currUser", currUser);
		userService.updateUser(currUser);
		return "main";
	}
	
	@RequestMapping("ShowUser")
	public String ShowUser(HttpServletRequest request){
		List<User> allUserList = userService.queryAllUser();
		request.setAttribute("allUserList", allUserList);
		request.setAttribute("currUser", request.getSession().getAttribute("currUser"));
		return "showUser";
	}
	
	@RequestMapping("AddUser")
	public String AddUser(HttpServletRequest request){
		List<User> allUserList = userService.queryAllUser();
		
		
		return "addUser";
	}
	
	@RequestMapping("LogOut")
	public String LogOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("currUser") != null)
			session.removeAttribute("currUser");
		return "login";
	}
}
