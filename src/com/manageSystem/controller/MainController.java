package com.manageSystem.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manageSystem.po.Event;
import com.manageSystem.po.User;
import com.manageSystem.service.EventService;
import com.manageSystem.service.MapService;
import com.manageSystem.service.UserService;
import com.manageSystem.util.HibernateSessionFactory;

@Controller
public class MainController{
	
	@Resource(name = "EventService")
    private EventService eventService;
    
    @Resource(name ="UserService")
    private UserService userService;
    
    @Resource(name = "MapService")
	MapService mapService;

    private User user;
    private String[] opSeq;
    private Map<Integer, String> map;
    private Map<String, Integer> sta=new HashMap();
    private List<Event> allEventList;
    private List<String> attriList;
    private int showNum;
    private int length;
    
    /*************************** UserAbout    **************************************/
    @RequestMapping("LogInCheck")
	public String LogInCheck(HttpServletRequest request, String userLogId, String userPws){ 
		User currUser = userService.getUserByUserLogId(userLogId);
		if(currUser != null && currUser.getUserPsw().compareTo(userPws) == 0){
	  		HashMap<Integer, String> attriMap = mapService.queryMap();
	  		request.getSession().setAttribute("currUser", currUser);
	  		request.getSession().setAttribute("attriMap", attriMap);
			return queryEvent(request, "", "", "");
		}else return "login";
	}
	
	@RequestMapping("MotifyUser")
	public String MotifyUser(HttpServletRequest request){
		if(request.getSession().getAttribute("currUser") == null){
			return "login";
		}
		request.setAttribute("currUser", request.getSession().getAttribute("currUser"));
		return "motifyUser";
	}
	
	@RequestMapping("UpdateUser")
	public String UpdateUser(HttpServletRequest request, String userLogId, String userName, String oldUserPsw, String newUserPsw){
		if(request.getSession().getAttribute("currUser") == null){
			return "login";
		}
		User currUser = (User)request.getSession().getAttribute("currUser");
		currUser.setUserName(userName);
		if(oldUserPsw.compareTo(currUser.getUserPsw()) == 0){
			currUser.setUserPsw(newUserPsw);
		}
		request.getSession().setAttribute("currUser", currUser);
		userService.updateUser(currUser);
		return queryEvent(request, "", "", "");
	}
	
	@RequestMapping("ShowUser")
	public String ShowUser(HttpServletRequest request){
		if(request.getSession().getAttribute("currUser") == null){
			return "login";
		}
		List<User> allUserList = userService.queryAllUser();
		request.setAttribute("allUserList", allUserList);
		request.setAttribute("currUser", request.getSession().getAttribute("currUser"));
		return "showUser";
	}
	
	@RequestMapping("LogOut")
	public String LogOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("currUser") != null)
			session.removeAttribute("currUser");
		return "login";
	}

    /***************************  SearchAbout  *************************************/
    @RequestMapping("/")
    /**
     * 用来做默认页面跳转
     * @param request
     * @return
     */
    public String Index(HttpServletRequest request, HttpServletResponse response){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	return queryEvent(request, "", "", ""); 
    }
    
    @SuppressWarnings({ "unchecked" })
    @RequestMapping("Events")
    public String queryEvent(HttpServletRequest request, String FuzzySearchKeys, String preSearchKeys, String isRefresh){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        String settings = user.getUserSettings();
        opSeq = settings.split(",");
        showNum = Integer.valueOf(opSeq[0]);
        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
        if(isRefresh == null || isRefresh.compareTo("") == 0){
        	System.out.println("isRefresh = " + isRefresh);
        	if(FuzzySearchKeys != null && FuzzySearchKeys.compareTo("") != 0){
        		System.out.println("FuzzySearchKeys'' = " + FuzzySearchKeys);
        		allEventList = eventService.FuzzyQueryEvent(FuzzySearchKeys);
        	}else if(preSearchKeys != null && preSearchKeys.compareTo("") != 0){
        		System.out.println("preSearchKeys = " + preSearchKeys);
        		HashMap<String, String> searchMap = new HashMap<String, String>();
            	if(preSearchKeys != ""){
            		String[] Keys = preSearchKeys.split(",,,,,,");
                	for(int i= 0; i < Keys.length; i++){
                		String[] value = Keys[i].split("::::::");
                		System.out.println("value[0] = " + value[0] + ", value[1] = " + value[1] );
                		searchMap.put(value[0], value[1]);
                	}
            	}
            	allEventList = eventService.PreciseQueryEvent(searchMap);
        	}else{
        		allEventList = eventService.queryAllEvent();
        		System.out.println("*******************************");
        	}
        }
        request.setAttribute("showNum", showNum);
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
    	request.setAttribute("opSeq", opSeq);
    	request.setAttribute("length", length);
    	request.setAttribute("showNum", showNum);
        return "search";
    }
    
    @RequestMapping("Setting")
    public String Setting(HttpServletRequest request){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	request.setAttribute("opSeq", opSeq);
    	request.setAttribute("length", length);
    	request.setAttribute("showNum", showNum);
        request.setAttribute("attriList", attriList);
        return "setting";
    }
    
    @RequestMapping("UpdateQuery")
    public String UpdateQuery(HttpServletRequest request, Integer Num, String newSettings){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	String userSettings = Num.toString() + "," + newSettings;
		user.setUserSettings(userSettings);
		showNum = Num;
		userService.updateUser(user);
		return queryEvent(request, "", "", "true");
    }
    
    @RequestMapping("FuzSearchEvent")
    public String FuzSearchEvent(HttpServletRequest request, String FuzzySearchKeys){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	System.out.println("FuzzySearchKeys = " + FuzzySearchKeys);
    	return queryEvent(request, FuzzySearchKeys, "", "");
    }
    
    @RequestMapping("PreSearchEvent")
    public String PreSearchEvent(HttpServletRequest request, String preSearchKeys){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	System.out.println("preSearchKeys = " + preSearchKeys);
    	if(preSearchKeys == null){
    		preSearchKeys = (String)request.getParameter("value");
    		System.out.println("preSearchKeys11 = " + preSearchKeys);
    	}
    	return queryEvent(request, "", preSearchKeys, "");
    }
    
    @RequestMapping("InputKeys")
    public String InputKeys(HttpServletRequest request){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
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
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
        return queryEvent(request, "", preSearchKeys, "");
    }
  
    //-----------------------------StatisticsAbout---------------------------------
   
    @RequestMapping("newStatistics")
    public String newStatistics(HttpServletRequest request){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        allEventList = eventService.queryAllEvent();
        String settings = user.getUserSettings();
        opSeq = settings.split(",");

        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
        request.setAttribute("attriList", attriList);
        
        String attributeName="cause";
        if (!sta.isEmpty())
        	sta.clear();
        int len=allEventList.size();
        for (int i=0;i<len;i++){
        	//System.out.println("************"+i+"times"+"***************");
        	String tmp=allEventList.get(i).toString();
        	//System.out.println(tmp);
        	
        	String key="";
        	String[] keylist=tmp.split(",,,,,, ");
        	for (int j=0;j<keylist.length;j++){
        		//System.out.println(keylist[j]);
        		String[] tmplist=keylist[j].split("::::::");
        		//System.out.println(tmplist[0]+" ");
        		if (tmplist[0].equals(attributeName)){
        			if (tmplist.length<=1) key="";
        			else key=tmplist[1];
        			System.out.println("*****"+key);
        		}
        	}
        	//System.out.println(key);
        	if (key.equals("")) continue;
        	if (!sta.containsKey(key)){
        		sta.put(key,1);
        	}
        	else {
        		int val=sta.get(key);
        		sta.put(key, val+1);
        	}
        }
        Set<String> nameset=sta.keySet();
        Iterator<String> it=nameset.iterator();
        List<String> chartList =new ArrayList<String>();
        while (it.hasNext()){
        	String str=it.next();
        	String string = str + "$" + String.valueOf(sta.get(str));
        	chartList.add(string);
        }
        
        request.setAttribute("chartList", chartList);
        request.setAttribute("attributeName", attributeName);
    	return "statistic1";
    }
    
    @RequestMapping("Statistics_new")
    public String statistics_new(HttpServletRequest request,String attributeName){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	System.out.println(attributeName);
    	attributeName=attributeName.toLowerCase();
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        allEventList = eventService.queryAllEvent();
        String settings = user.getUserSettings();
        opSeq = settings.split(",");

        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        //System.out.println(222);
        
        if (!sta.isEmpty())
        	sta.clear();
        int len=allEventList.size();
        for (int i=0;i<len;i++){
        	//System.out.println("************"+i+"times"+"***************");
        	String tmp=allEventList.get(i).toString();
        	//System.out.println(tmp);
        	
        	String key="";
        	String[] keylist=tmp.split(",,,,,, ");
        	for (int j=0;j<keylist.length;j++){
        		//System.out.println(keylist[j]);
        		String[] tmplist=keylist[j].split("::::::");
        		//System.out.println(tmplist[0]+" ");
        		if (tmplist[0].equals(attributeName)){
        			if (tmplist.length<=1) key="";
        			else key=tmplist[1];
        			System.out.println("*****"+key);
        		}
        	}
        	//System.out.println(key);
        	if (key.equals("")) continue;
        	if (!sta.containsKey(key)){
        		sta.put(key,1);
        	}
        	else {
        		int val=sta.get(key);
        		sta.put(key, val+1);
        	}
        }
        Set<String> nameset=sta.keySet();
        Iterator<String> it=nameset.iterator();
        List<String> chartList =new ArrayList<String>();
        while (it.hasNext()){
        	String str=it.next();
        	String string = str + "$" + String.valueOf(sta.get(str));
        	chartList.add(string);
        }
        
        request.setAttribute("chartList", chartList);
        request.setAttribute("attributeName", attributeName);
        
    	return "statistic1";
    }
    
    @RequestMapping("Statistics_column_new")
    public String statistics_colum_new(HttpServletRequest request,String attributeName){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	System.out.println(attributeName);    	
    	attributeName=attributeName.toLowerCase();
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        allEventList = eventService.queryAllEvent();
        String settings = user.getUserSettings();
        opSeq = settings.split(",");
        showNum = Integer.valueOf(opSeq[0]);

        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
        request.setAttribute("showNum", showNum);
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        //System.out.println(222);
        
        if (!sta.isEmpty())
        	sta.clear();
        int len=allEventList.size();
        for (int i=0;i<len;i++){
        	String tmp=allEventList.get(i).toString();
        	
        	String key="";
        	String[] keylist=tmp.split(",,,,,, ");
        	for (int j=0;j<keylist.length;j++){
        		String[] tmplist=keylist[j].split("::::::");
        		if (tmplist[0].equals(attributeName)){
        			if (tmplist.length<=1) key="";
        			else key=tmplist[1];
        		}
        	}
        	if (key.equals("")) continue;
        	if (!sta.containsKey(key)){
        		sta.put(key,1);
        	}
        	else {
        		int val=sta.get(key);
        		sta.put(key, val+1);
        	}
        }
        Set<String> nameset=sta.keySet();
        Iterator<String> it=nameset.iterator();
        List<String> chartList =new ArrayList<String>();
        while (it.hasNext()){
        	String str=it.next();
        	String string = str + "$" + String.valueOf(sta.get(str));
        	chartList.add(string);
        	System.out.println("string = " + string);
        }
        
        request.setAttribute("chartList", chartList);
        request.setAttribute("attributeName", attributeName);
    	return "statistic2";
    }
    
    @RequestMapping("Statistics_column")
    public String Statistics_column(HttpServletRequest request){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        allEventList = eventService.queryAllEvent();
        String settings = user.getUserSettings();
        opSeq = settings.split(",");

        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        
        String attributeName="cause";
        if (!sta.isEmpty())
        	sta.clear();
        int len=allEventList.size();
        for (int i=0;i<len;i++){
        	//System.out.println("************"+i+"times"+"***************");
        	String tmp=allEventList.get(i).toString();
        	//System.out.println(tmp);
        	
        	String key="";
        	String[] keylist=tmp.split(",,,,,, ");
        	for (int j=0;j<keylist.length;j++){
        		//System.out.println(keylist[j]);
        		String[] tmplist=keylist[j].split("::::::");
        		//System.out.println(tmplist[0]+" ");
        		if (tmplist[0].equals(attributeName)){
        			if (tmplist.length<=1) key="";
        			else key=tmplist[1];
        			System.out.println("*****"+key);
        		}
        	}
        	//System.out.println(key);
        	if (key.equals("")) continue;
        	if (!sta.containsKey(key)){
        		sta.put(key,1);
        	}
        	else {
        		int val=sta.get(key);
        		sta.put(key, val+1);
        	}
        }
        
        Set<String> nameset=sta.keySet();
        Iterator<String> it=nameset.iterator();
        List<String> chartList =new ArrayList<String>();
        while (it.hasNext()){
        	String str=it.next();
        	String string = str + "$" + String.valueOf(sta.get(str));
        	chartList.add(string);
        }
        
        request.setAttribute("chartList", chartList);
        request.setAttribute("attributeName", attributeName);
    	return "statistic2";
    }
  
    //---------------------------------------------------------------------
    @RequestMapping("Export")
	public String Export(HttpServletRequest request,HttpServletResponse response,String fileType) throws IOException{
		//System.out.println(fileType);
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
		DoExport(request,response,fileType);
		
		//------------------------------------------------------------------
        request.setAttribute("showNum", showNum);
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        
        //--------------------------------set----------------------------
        
		return "search";
	}
    
    public void DoExport(HttpServletRequest request,HttpServletResponse response,String filetype) throws IOException{
        List<Event> allTickit=allEventList;
        //------------------------------------

        String fileURL=request.getRealPath("/xml/"+filetype);        
        File file=new File(fileURL);
        Element event_list=null;
        Document doc=null;
        if(file.exists()){file.delete();}//删除上一次剩下的文件
        //-----------------------------------------创建文档对象并且设置根节点
         
        doc=DocumentHelper.createDocument();
        //doc.getRootElement().asXML();
        event_list=DocumentHelper.createElement("allEvents");
        doc.setRootElement(event_list);
        
        
        //-----------------------------------
		int cnt;
        cnt=allTickit.size();
		//--------------------------------for循环添加节点设置内容
		for(int i=0;i<cnt;i++){
			Element event=event_list.addElement("event");
			event.addElement("IPCCUSTOMER").setText(allTickit.get(i).getIpccustomer());
			event.addElement("ACTIONABLE").setText(allTickit.get(i).getActionable());
			event.addElement("CUSTOMERCODE").setText(allTickit.get(i).getCustomercode());
			event.addElement("ZPROCESSSTATE").setText(allTickit.get(i).getZprocessstate());
			event.addElement("CAUSE").setText(allTickit.get(i).getCause());
			event.addElement("RESOLUTIONCODE").setText(allTickit.get(i).getResolutioncode());
			event.addElement("CLASS").setText(allTickit.get(i).getClass_());
			event.addElement("ZBNOTIFYSTATE").setText(allTickit.get(i).getZbnotifystate());
			event.addElement("RESOURCETYPE").setText(allTickit.get(i).getResourcetype());
			event.addElement("ZGENERICACTIONSTATE").setText(allTickit.get(i).getZgenericactionstate());
			event.addElement("IBMMANAGED").setText(allTickit.get(i).getIbmmanaged());
			event.addElement("ZHNOTIFYSTATE").setText(allTickit.get(i).getZhnotifystate());
			event.addElement("LASTUPDATE").setText(allTickit.get(i).getLastupdate());
			event.addElement("SUMMARY").setText(allTickit.get(i).getSummary());
			event.addElement("COMPONENTTYPE").setText(allTickit.get(i).getComponenttype());
			event.addElement("CUSTOMER").setText(allTickit.get(i).getCustomer());
			event.addElement("OSTYPE").setText(allTickit.get(i).getOstype());
			event.addElement("POLL").setText(allTickit.get(i).getPoll());
			event.addElement("EXPIRETIME").setText(allTickit.get(i).getExpiretime());
			event.addElement("PROCESSREQ").setText(allTickit.get(i).getProcessreq());
			event.addElement("TYPE").setText(allTickit.get(i).getType());
			event.addElement("TASKLIST").setText(allTickit.get(i).getTasklist());
			event.addElement("TICKETSTATUS").setText(allTickit.get(i).getTicketstatus());
			event.addElement("SERIAL").setText(allTickit.get(i).getSerial());
			event.addElement("LASTOCCURRENCE").setText(allTickit.get(i).getLastoccurrence());
			event.addElement("ACKNOWLEDGED").setText(allTickit.get(i).getAcknowledged());
			event.addElement("ZTICKETSEVERITY").setText(allTickit.get(i).getZticketseverity());
			event.addElement("ZTICKETSTATE").setText(allTickit.get(i).getZticketstate());
			event.addElement("NODE").setText(allTickit.get(i).getNode());
			event.addElement("RESOLUTION").setText(allTickit.get(i).getResolution());
			event.addElement("OWNERGID").setText(allTickit.get(i).getOwnergid());
			event.addElement("TARGETIPMS").setText(allTickit.get(i).getTargetipms());
			event.addElement("ALERTKEY").setText(allTickit.get(i).getAlertkey());
			event.addElement("SUPPRESSESCL").setText(allTickit.get(i).getSuppressescl());
			event.addElement("FLASH").setText(allTickit.get(i).getFlash());
			event.addElement("SERVERNAME").setText(allTickit.get(i).getServername());
			event.addElement("ALERTGROUP").setText(allTickit.get(i).getAlertgroup());
			event.addElement("TALLY").setText(allTickit.get(i).getTally());
			event.addElement("SERVERSERIAL").setText(allTickit.get(i).getServerserial());
			event.addElement("GRADE").setText(allTickit.get(i).getGrade());
			event.addElement("OWNERUID").setText(allTickit.get(i).getOwneruid());
			event.addElement("COMPONENT").setText(allTickit.get(i).getComponent());
			event.addElement("TICKETNUMBER").setText(allTickit.get(i).getTicketnumber());
			event.addElement("FIRSTOCCURRENCE").setText(allTickit.get(i).getFirstoccurrence());
			event.addElement("SEVERITY").setText(allTickit.get(i).getSeverity());
			event.addElement("ORIGINALSEVERITY").setText(allTickit.get(i).getOriginalseverity());
		}
		
		//---------------------保存文件
				
		
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		format.setSuppressDeclaration(true); //去掉版本信息
		try{
			XMLWriter writer=new XMLWriter(new FileWriter(fileURL),format);
			
			writer.write(doc);
			writer.close();
			
		}
		catch(IOException e){e.printStackTrace();}		
		
		
		
		//----
		request.setAttribute("filename",filetype);
		request.setAttribute("filepath", fileURL);
		
		//System.out.println(fileURL+","+filetype);
		//System.out.println(request);
		
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+filetype);  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
        
        //读取文件  
        InputStream iStream = new FileInputStream(fileURL);  
        OutputStream oStream = response.getOutputStream();  
          
        //写文件  
        int tmp;  
        while((tmp=iStream.read())!= -1)  
        {  
            oStream.write(tmp);  
        }  
          
        iStream.close();  
        oStream.close(); 	
    }
    
  //----------------------------------ImportAbout----------------------------------------
    @RequestMapping("importFile")
    public String importFile(HttpServletRequest request){
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	return "import";
    }
    
    //@RequestMapping("upload")
    public String Import(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	if(request.getSession().getAttribute("currUser") == null){
    		return "login";
    	}
    	response.setContentType("text/html;charset=gbk");
		response.setCharacterEncoding("utf-8");
	
		PrintWriter out = response.getWriter();
		 
		
		String fileName = null;
		//定义上载文件的最大字节
		int MAX_SIZE = 102400 * 102400;
		// 创建根路径的保存变量
		String rootPath;
		//声明文件读入类
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		//取得客户端的网络地址
		String remoteAddr = request.getRemoteAddr();
		//获得服务器的名字
		String serverName = request.getServerName();
		System.out.println(serverName);
		//取得互联网程序的绝对地址
		String realPath = request.getRealPath("/")+serverName;
		System.out.println(realPath);
		realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
		System.out.println(realPath);
		//创建文件的保存目录
		rootPath = realPath + "\\upload\\";
		//取得客户端上传的数据类型
		String contentType = request.getContentType();
		System.out.println(1);
		try{
			if(contentType.indexOf("multipart/form-data") >= 0){
			//读入上传的数据
			in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength();
			if(formDataLength > MAX_SIZE){
			out.println("<P>上传的文件字节数不可以超过" + MAX_SIZE + "</p>");
			return "input file is too large";
		}
		//保存上传文件的数据
		byte dataBytes[] = new byte[formDataLength];
		int byteRead = 0;
		int totalBytesRead = 0;
		//上传的数据保存在byte数组
		
		System.out.println(2);
		
		while(totalBytesRead < formDataLength){
		byteRead = in.read(dataBytes,totalBytesRead,formDataLength);
		totalBytesRead += byteRead;
		}
		//根据byte数组创建字符串
		String file = new String(dataBytes);
		//out.println(file);
		//取得上传的数据的文件名
		
		System.out.println(3);
		//---------------------------------------------------------------------
		String saveFile = file.substring(file.indexOf("filename=\"") + 10);
		saveFile = saveFile.substring(0,saveFile.indexOf("\n"));
		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
		int lastIndex = contentType.lastIndexOf("=");
		//取得数据的分隔字符串
		String boundary = contentType.substring(lastIndex + 1,contentType.length());
		//创建保存路径的文件名
		 fileName = rootPath + saveFile;
		//out.print(fileName);
		 
		 //error!!!!!!!!!
		//---------------------------------------------------------------------
		 System.out.println(4);
		 
		int pos;
		pos = file.indexOf("filename=\"");
		pos = file.indexOf("\n",pos) + 1;
		pos = file.indexOf("\n",pos) + 1;
		pos = file.indexOf("\n",pos) + 1;
		int boundaryLocation = file.indexOf(boundary,pos) - 4;
		//out.println(boundaryLocation);
		//取得文件数据的开始的位置
		int startPos = ((file.substring(0,pos)).getBytes()).length;
		//out.println(startPos);
		//取得文件数据的结束的位置
		int endPos = ((file.substring(0,boundaryLocation)).getBytes()).length;
		//out.println(endPos);
		//检查上载文件是否存在
		
		System.out.println(5);
		
		File checkFile = new File(fileName);
		if(checkFile.exists()){
		out.println("<p>"+ "文件已经存在.</p>");
		}
		//检查上载文件的目录是否存在
		File fileDir = new File(rootPath);
		if(!fileDir.exists()){
		fileDir.mkdirs();
		}
		//创建文件的写出类
		fileOut = new FileOutputStream(fileName);
		//保存文件的数据
		fileOut.write(dataBytes,startPos,(endPos - startPos));
		fileOut.close();
		out.println( "文件成功上载.</p>");
		}else{
		out.println("<p>上传的数据类型不是multipart/form-data</p>");
		}
		//---------------------
		/*
		request.setAttribute("filepath", fileName);
		RequestDispatcher rd=request.getRequestDispatcher("Analysis1");    
		rd.forward(request, response);
		*/
			
			//Analysis
     //-----------------------
		
		
		
		
		}catch(Exception ex){
		throw new ServletException(ex.getMessage());
		}
		return "Success!";
    }

    /**
        flagcode:
        1,娌℃湁鏍瑰睘鎬�
        2,鏂囦欢绫诲瀷鎴栨牸寮忛敊璇�
        3,鎴愬姛
        4,鍏朵粬鏈煡閿欒
        5,涔熻鎯冲鍏ュ鏉℃暟鎹紝涔熷彲鑳芥槸閿欑殑
    **/
    
    public int Analysis(File file){
        /*
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;chartset=UTF-8");
        String filepath=(String)request.getAttribute("filepath");//浠庝笂涓�釜servlet鑾峰緱鏂囦欢璺緞
        File file=new File(filepath);
        */
    	
        Document document=null;
        Element event=null;
        Element root=null;
        HibernateSessionFactory getSession;//鑾峰彇鏁版嵁搴撹繛鎺�
        getSession=new HibernateSessionFactory();
        Session session=getSession.getSession();
        
        System.out.println("init over");
    
        if(file.exists()){
            SAXReader reader=new SAXReader();
            try{
                document=reader.read(file);
                //if(document==null)out.print("no doc");else out.print("you1");
                root=document.getRootElement();//鑾峰彇鏂囨。鏍瑰厓绱�
                //if(root==null)throw new Exception();//濡傛灉鍙栦笉鍒版牴鍏冪礌锛屽垯宸ヤ綔绁ㄥ唴瀹规牸寮忎笉瑙勮寖锛屾墜鍔ㄦ姏鍑哄紓甯�
                if (root==null) return 1;
            }
            catch(Exception e){//濡傛灉鏂囦欢绫诲瀷涓嶆纭垨鍐呭鏍煎紡涓嶅锛岃鍙栦笉浜嗗垯鎶撲綇寮傚父鍚庤浆鍥炲墠鍙帮紝骞朵笖鍋滄servlet
                file.delete();//鍒犻櫎鏂囦欢
                //request.setAttribute("message", "鎮ㄧ殑宸ヤ綔绁ㄦ枃浠剁被鍨嬫垨鍐呭鏍煎紡涓嶆纭�");
                //RequestDispatcher rd=request.getRequestDispatcher("/import.jsp");    
                //rd.forward(request, response); return; //鍋滄servlet
                return 2;
            }//-------end of try/catch
                
            if(root.getName().equals("event"))//濡傛灉鏄崟寮犲伐浣滅エ鍦ㄤ竴涓枃浠跺唴鍒欐牴鍏冪礌涓篹vent
            {
                event=root;//鏍瑰厓绱犱负event 
               
                Event tickit=new Event();
                //鍒ゆ柇杩欎釜鏍囩鏄惁涓虹┖锛屽鏍囩涓嶅瓨鍦ㄧ殑鎯呭喌杩涜鍏煎锛岃祴鍊间竴涓┖鐨勫瓧绗︿覆锛堝苟闈瀗ull锛夛紝涓嶄负绌虹洿鎺ュ彇鍊�
                if(event.element("ACKNOWLEDGED")!=null)tickit.setAcknowledged(event.element("ACKNOWLEDGED").getText());
                else tickit.setAcknowledged("");
                
                
                if(event.element("ACTIONABLE")!=null)tickit.setActionable(event.element("ACTIONABLE").getText());
                else tickit.setActionable("");
                if(event.element("ALERTGROUP")!=null)tickit.setAlertgroup(event.element("ALERTGROUP").getText());
                else tickit.setAlertgroup("");
                if(event.element("ALERTKEY")!=null)tickit.setAlertkey(event.element("ALERTKEY").getText());
                else tickit.setAlertkey("");
                if(event.element("CAUSE")!=null)tickit.setCause(event.element("CAUSE").getText());
                else tickit.setCause("");
                if(event.element("CLASS")!=null)tickit.setClass_(event.element("CLASS").getText());
                else tickit.setClass_("");
                if(event.element("COMPONENT")!=null)tickit.setComponent(event.element("COMPONENT").getText());
                else tickit.setComponent("");
                if(event.element("COMPONENTTYPE")!=null)tickit.setComponenttype(event.element("COMPONENTTYPE").getText());
                else tickit.setComponenttype("");
                if(event.element("CUSTOMER")!=null)tickit.setCustomer(event.element("CUSTOMER").getText());
                else tickit.setCustomer("");
                if(event.element("CUSTOMERCODE")!=null)tickit.setCustomercode(event.element("CUSTOMERCODE").getText());
                else tickit.setCustomercode("");
                if(event.element("EXPIRETIME")!=null)tickit.setExpiretime(event.element("EXPIRETIME").getText());
                else tickit.setExpiretime("");
                if(event.element("FIRSTOCCURRENCE")!=null)tickit.setFirstoccurrence(event.element("FIRSTOCCURRENCE").getText());
                else tickit.setFirstoccurrence("");
                if(event.element("FLASH")!=null)tickit.setFlash(event.element("FLASH").getText());
                else tickit.setFlash("");
                if(event.element("GRADE")!=null)tickit.setGrade(event.element("GRADE").getText());
                else tickit.setGrade("");
                if(event.element("IBMMANAGED")!=null)tickit.setIbmmanaged(event.element("IBMMANAGED").getText());
                else tickit.setIbmmanaged("");
                if(event.element("IPCCUSTOMER")!=null)tickit.setIpccustomer(event.element("IPCCUSTOMER").getText());
                else tickit.setIpccustomer("");
                if(event.element("LASTOCCURRENCE")!=null)tickit.setLastoccurrence(event.element("LASTOCCURRENCE").getText());
                else tickit.setLastoccurrence("");
                if(event.element("LASTUPDATE")!=null)tickit.setLastupdate(event.element("LASTUPDATE").getText());
                else tickit.setLastupdate("");
                if(event.element("NODE")!=null)tickit.setNode(event.element("NODE").getText());
                else tickit.setNode("");
                if(event.element("ORIGINALSEVERITY")!=null)tickit.setOriginalseverity(event.element("ORIGINALSEVERITY").getText());
                else tickit.setOriginalseverity("");
                if(event.element("OSTYPE")!=null)tickit.setOstype(event.element("OSTYPE").getText());
                else tickit.setOstype("");
                if(event.element("OWNERGID")!=null)tickit.setOwnergid(event.element("OWNERGID").getText());
                else tickit.setOwnergid("");
                if(event.element("OWNERUID")!=null)tickit.setOwneruid(event.element("OWNERUID").getText());
                else tickit.setOwneruid("");
                if(event.element("POLL")!=null)tickit.setPoll(event.element("POLL").getText());
                else tickit.setPoll("");
                if(event.element("PROCESSREQ")!=null)tickit.setProcessreq(event.element("PROCESSREQ").getText());
                else tickit.setProcessreq("");
                if(event.element("RESOLUTION")!=null)tickit.setResolution(event.element("RESOLUTION").getText());
                else tickit.setResolution("");
                if(event.element("RESOLUTIONCODE")!=null)tickit.setResolutioncode(event.element("RESOLUTIONCODE").getText());
                else tickit.setResolutioncode("");
                if(event.element("RESOURCETYPE")!=null)tickit.setResourcetype(event.element("RESOURCETYPE").getText());
                else tickit.setResourcetype("");
                /*
                
                if(tickit.setSerial(event.element("SERIAL")!=null)tickit.setResourcetype(event.element("SERIAL")).getText());
                else tickit.setResourcetype("");
               */
                
                if(event.element("SERVERNAME")!=null)tickit.setServername(event.element("SERVERNAME").getText());
                else tickit.setServername("");
                if(event.element("SERVERSERIAL")!=null)tickit.setServerserial(event.element("SERVERSERIAL").getText());
                else tickit.setServerserial("");
                if(event.element("SEVERITY")!=null)tickit.setSeverity(event.element("SEVERITY").getText());
                else tickit.setSeverity("");
                if(event.element("SUMMARY")!=null)tickit.setSummary(event.element("SUMMARY").getText());
                else tickit.setSummary("");
                if(event.element("SUPPRESSESCL")!=null)tickit.setSuppressescl(event.element("SUPPRESSESCL").getText());
                else tickit.setSuppressescl("");
                if(event.element("TALLY")!=null)tickit.setTally(event.element("TALLY").getText());
                else tickit.setTally("");
                if(event.element("TARGETIPMS")!=null)tickit.setTargetipms(event.element("TARGETIPMS").getText());
                else tickit.setTargetipms("");
                if(event.element("TASKLIST")!=null)tickit.setTasklist(event.element("TASKLIST").getText());
                else tickit.setTasklist("");
                if(event.element("TICKETNUMBER")!=null)tickit.setTicketnumber(event.element("TICKETNUMBER").getText());
                else tickit.setTicketnumber("");
                if(event.element("TICKETSTATUS")!=null)tickit.setTicketstatus(event.element("TICKETSTATUS").getText());
                else tickit.setTicketstatus("");
                if(event.element("TYPE")!=null)tickit.setType(event.element("TYPE").getText());
                else tickit.setType("");
                if(event.element("ZBNOTIFYSTATE")!=null)tickit.setZbnotifystate(event.element("ZBNOTIFYSTATE").getText());
                else tickit.setZbnotifystate("");
                if(event.element("ZGENERICACTIONSTATE")!=null)tickit.setZgenericactionstate(event.element("ZGENERICACTIONSTATE").getText());
                else tickit.setZgenericactionstate("");
                if(event.element("ZHNOTIFYSTATE")!=null)tickit.setZhnotifystate(event.element("ZHNOTIFYSTATE").getText());
                else tickit.setZhnotifystate("");
                if(event.element("ZPROCESSSTATE")!=null)tickit.setZprocessstate(event.element("ZPROCESSSTATE").getText()); 
                else tickit.setZprocessstate("");
                if(event.element("ZTICKETSEVERITY")!=null)tickit.setZticketseverity(event.element("ZTICKETSEVERITY").getText());
                else tickit.setZticketseverity("");
                if(event.element("ZTICKETSTATE")!=null)tickit.setZticketstate(event.element("ZTICKETSTATE").getText());
                else tickit.setZticketstate("");
                Transaction tx=session.beginTransaction();
                session.save(tickit);
                tx.commit();
                session.close();
                file.delete();
                /*
                request.setAttribute("message", "宸叉垚鍔熷鍏ュ伐浣滅エ锛�);
                RequestDispatcher rd=request.getRequestDispatcher("/inner.jsp");    
                 rd.forward(request, response);
                 */
                 return 3;
                 
                //}//end of else
             }//end of try
            /*
            catch(Exception e){//濡傛灉鍦ㄨ鍙栬繖寮犲伐浣滅エ杩囩▼涓嚭浜嗕弗閲嶉棶棰橈紝璋冨洖鍓嶅彴锛屽弽棣堜俊鎭�
                
                request.setAttribute("message", "鎮ㄥ鍏ョ殑鏂囦欢绫诲瀷鎴栬�鏍煎紡涓嶈鑼冿紒");
                RequestDispatcher rd=request.getRequestDispatcher("/import.jsp");    
                rd.forward(request, response);
                
                return 4;
            
            }//----//end of try/catch
             */
        }//end of if root.equals "event"
        else{//濡傛灉鏍瑰厓绱犱笉鏄痚vent 鍒欏彲鑳戒负澶氬紶宸ヤ綔绁�
            return 5;    
        }//end of else 涔熷氨鏄牴涓嶆槸event 鍙兘鏈夊寮犲伐浣滅エ
		return 0;

    }
    
    /******************************uploadAbout ******************************/
    @RequestMapping(value = "/upload")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
    	if(request.getSession().getAttribute("currUser") == null){
			return "login";
		}
    	user = (User)request.getSession().getAttribute("currUser");
        map = (Map<Integer, String>)request.getSession().getAttribute("attriMap");
        allEventList = eventService.queryAllEvent();
        String settings = user.getUserSettings();
        opSeq = settings.split(",");
        showNum = Integer.valueOf(opSeq[0]);
        

        attriList = new ArrayList<String>();
        length = opSeq.length;
        for(int i = 1; i < length; i++)
            attriList.add(map.get(Integer.valueOf(opSeq[i])));
        request.setAttribute("showNum", showNum);
        request.setAttribute("allEventList", allEventList);
        request.setAttribute("attriList", attriList);
        
        Event show=allEventList.get(0);
        String str=show.toString();
        String[] strlist=str.split(",,,,,, ");
        List<String> showList = new ArrayList<String>();
        
        for (int i=0;i<strlist.length;i++){
        	System.out.println(strlist[i]);
        }
        
        //showList.add(show.ge);
        for (int i=0;i<strlist.length;i++){
        	System.out.println(i+"times");
        	showList.add(strlist[i]);
        }
        
        request.setAttribute("showList",showList);
        
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        int beginIndex=fileName.indexOf(".");
        String suffix=fileName.substring(beginIndex);
        System.out.println(suffix);
        if (suffix.equals(".xml") || suffix.equals(".XML")){
        	work();
        	return "importSuccess";
        }
        else {
        	return "importFailed";
        }
    }
    
    public void work(){
    	HibernateSessionFactory getSession;//閼惧嘲褰囬弫鐗堝祦鎼存捁绻涢幒锟�
        getSession=new HibernateSessionFactory();
        Session session=getSession.getSession();
    	Event tickit=new Event();
        tickit.setAcknowledged("0");  
        
        tickit.setActionable("Non-Actionable");
       
        tickit.setAlertgroup("ITM_MS_SQL_Database_Detail");
        
        tickit.setAlertkey("afi_sppctud_3oqw_sql_dev");
       
        tickit.setCause("Capacity");
       
        tickit.setClass_("0");
    
        tickit.setComponent("SQL");
       
        tickit.setComponenttype("Database");
        
        tickit.setCustomer("Ameriprise Financial");
        
        tickit.setCustomercode("afi");
      
        tickit.setExpiretime("0");
       
        tickit.setFirstoccurrence("1393675144000");
        
        tickit.setFlash("0");
        
        tickit.setGrade("0");
        
        tickit.setIbmmanaged("0");
       
        tickit.setIpccustomer("Ameriprise Financial");
        
        tickit.setLastoccurrence("1393675144000");
       
        tickit.setLastupdate("1398827961094");
        
        tickit.setNode("afi_us97udb213ampwb");
        
        tickit.setOriginalseverity("2");
       
        tickit.setOstype("");
       
        tickit.setOwnergid("0");
        
        tickit.setOwneruid("0");
        
        tickit.setPoll("0");
        
        tickit.setProcessreq("0");
        
        tickit.setResolution("The database IAD_MAIN_ARCHIVAL is set to autogrowth. No action is required since there is enough free space on disk for database growth. name fileid AllocatedSizeinMB SpacedUsedinMB SpacedUsedPCT Usage MaxSizeInMB IAD_MAIN_Data 1 500.00 29.19 (5.84%) 5.84 Data 3173 IAD_MAIN_Log 2 249.88 1.66 (0.67%) 0.67 Log 2097152 IAD_MAIN_USR_DATA 3 4000.00 113.13 (2.83%) 2.83 Data 25384 IAD_MAIN_LOOKUP_DATA 4 500.00 0.19 (0.04%) 0.04 Data 3173 IAD_MAIN_INDEX_DATA 5 1000.00 0.06 (0.01%) 0.01 Data 6346");
        
        tickit.setResolutioncode("Solved (Full restoration)");
        
        tickit.setResourcetype("0");
        
        tickit.setSerial("901230");
        
        tickit.setServername("USS10P0AFIP");
       
        tickit.setServerserial("901230");
        
        tickit.setSeverity("0");
       
        tickit.setSummary("MS SQL issue: high space used in database: IAD_MAIN_ARCHIVAL. Used: 77.34%");
       
        tickit.setSuppressescl("0");
        
        tickit.setTally("1");
       
        tickit.setTargetipms("ISM Afi");
        
        tickit.setTasklist("0");
        
        tickit.setTicketnumber("INC0770210");
        
        tickit.setTicketstatus("Closed");
        
        tickit.setType("0");
       
        tickit.setZbnotifystate("0");
        
        tickit.setZgenericactionstate("0");
       
        tickit.setZhnotifystate("0");
       
        tickit.setZprocessstate("0"); 
        
        tickit.setZticketseverity("0");
        
        tickit.setZticketstate("0");
       
        Transaction tx=session.beginTransaction();
        session.save(tickit);
        tx.commit();
        session.close();
        //file.delete();
    }
}
