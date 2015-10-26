package com.manageSystem.service.out;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manageSystem.po.*;
import com.manageSystem.util.HibernateSessionFactory;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.FileWriter;
import java.io.IOException; 
import java.util.Iterator; 
import java.util.List; 
import org.dom4j.Document;  
import org.dom4j.DocumentException; 
import org.dom4j.DocumentHelper; 
import org.dom4j.Element;  
import org.dom4j.io.OutputFormat; 
import org.dom4j.io.SAXReader; 
import org.dom4j.io.XMLWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class out extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public out() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String hql="";
        List<Event> allTickit=new ArrayList<Event>();
        Query query = null;
        int count=0;
        //------------------------------------
        
        String filetype=request.getParameter("filetype");
        String fileURL=request.getRealPath("/xml/"+filetype);        
        File file=new File(fileURL);
        Element event_list=null;
        Document doc=null;
        if(file.exists()){file.delete();}//删除上一次剩下的文件
        //-----------------------------------------创建文档对象并且设置根节点
         
        doc=DocumentHelper.createDocument();
        //doc.getRootElement().asXML();
        event_list=DocumentHelper.createElement("event_list");
        doc.setRootElement(event_list);
        
        
        //-----------------------------------
		if(request.getSession().getAttribute("hqlfinal")!=null){
			hql=(String)request.getSession().getAttribute("hqlfinal");
			HibernateSessionFactory getSession;
			getSession=new HibernateSessionFactory();
			 Session session=getSession.getSession();
			//int count = (int) session.createSQLQuery("select count(*) from worktickit").uniqueResult();
			 
			 query=session.createQuery(hql);
			 allTickit=query.list();
			 count=allTickit.size();
			
			
		}
		//--------------------------------for循环添加节点设置内容
		for(int i=0;i<count;i++)
		{
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
		RequestDispatcher rd=request.getRequestDispatcher("Download");    
        rd.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
