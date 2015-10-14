package com.manageSystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manageSystem.po.Event;
import com.manageSystem.po.User;
import com.manageSystem.service.EventService;
import com.manageSystem.service.UserService;

@Controller
public class SearchAbout {

    @Resource(name = "EventService")
    private EventService eventService;
    
    @Resource(name ="UserService")
    private UserService userService;

    private User user;
    private String[] opSeq;
    private Map<Integer, String> map;
    private List<Event> allEventList;
    private List<String> attriList;
    private int showNum;
    private int length;
    private boolean isPreSearch = false;

    @SuppressWarnings({ "unchecked" })
    @RequestMapping("Events")
    public String queryEvent(HttpServletRequest request){
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        String settings = user.getUserSettings();
        opSeq = settings.split(",");
        showNum = Integer.valueOf(opSeq[0]);
        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
    	if(!isPreSearch){
    		allEventList = eventService.queryAllEvent();
        }
    	isPreSearch = false;
        request.setAttribute("showNum", showNum);
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        return "search";
    }
    
    @RequestMapping("Setting")
    public String Setting(HttpServletRequest request){
    	request.setAttribute("opSeq", opSeq);
    	request.setAttribute("length", length);
    	request.setAttribute("showNum", showNum);
        request.setAttribute("attriList", attriList);
        return "setting";
    }
    
    @RequestMapping("UpdateQuery")
    public String UpdateQuery(HttpServletRequest request, Integer Num, String newSettings){
    	String userSettings = Num.toString() + "," + newSettings;
		user.setUserSettings(userSettings);
		showNum = Num;
		userService.updateUser(user);
		return "close";
    }
    
    @RequestMapping("FuzSearchEvent")
    public String FuzSearchEvent(HttpServletRequest request, String FuzzySearchKeys){
    	if(!isPreSearch){
    		allEventList = eventService.FuzzyQueryEvent(FuzzySearchKeys);
    	}
    	isPreSearch = false;
    	request.setAttribute("showNum", showNum);
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        return "search";
    }
    
    @RequestMapping("PreSearchEvent")
    public String PreSearchEvent(HttpServletRequest request, String preSearchKeys){
    	HashMap<String, String> searchMap = new HashMap<String, String>();
    	if(preSearchKeys != ""){
    		String[] Keys = preSearchKeys.split(",");
        	for(int i= 0; i < Keys.length; i++){
        		String[] value = Keys[i].split(":");
        		searchMap.put(value[0], value[1]);
        	}
    	}
    	allEventList = eventService.PreciseQueryEvent(searchMap);
    	isPreSearch = true;
        return "close";
    }
    
    @RequestMapping("InputKeys")
    public String InputKeys(HttpServletRequest request){
        request.setAttribute("attriList", attriList);
        return "preSearch";
    }
    
    /**
     *  用于饼图查看某字段为某个值的工作票的详细信息
     *  函数需要传入一个字符串，这个字符串的格式为 字段名:值,字段名:值,..,字段名:值
     *  未测试， 有 bug 请联系开发人员.
     */
    @RequestMapping("ShowDetail")
    public String ShowDetail(HttpServletRequest request, String preSearchKeys){
    	HashMap<String, String> searchMap = new HashMap<String, String>();
    	if(preSearchKeys != ""){
    		String[] Keys = preSearchKeys.split(",");
        	for(int i= 0; i < Keys.length; i++){
        		String[] value = Keys[i].split(":");
        		searchMap.put(value[0], value[1]);
        	}
    	}
    	allEventList = eventService.PreciseQueryEvent(searchMap);
    	isPreSearch = true;
        return queryEvent(request);
    }
}
