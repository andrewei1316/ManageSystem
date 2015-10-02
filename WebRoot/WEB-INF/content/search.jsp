<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'search.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type = "text/javascript" src="resources/JS/jquery.js"></script>  
	<script type = "text/javascript" src="resources/JS/sortTable.js"></script>
    <script type = "text/javascript" src="resources/JS/jquery.tablednd.js"></script>  
    
	<script language = "javascript">
 		 function showPreSearch(){
  		 	if(document.getElementById("setHidden").style.display == "none") $("#setHidden").show();
  			else $("#setHidden").hide();
  		}
  	</script>
  
  	<script language = "javascript">
  		function Custom(){
  			window.open ('Setting', '自定义显示设置', 'height=600, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=no');
  		}
  	</script>
  
  	<script language="javascript" type="text/javascript">
		window.onload = function(){
    		var oTable = document.getElementById("bbsTab");
    		for(var i=0;i<oTable.rows.length;i++){
        		oTable.rows[i].cells[0].innerHTML = (i+1);
    		}
		};
	</script>
	
	<script type="text/javascript">
		function spliPreSearchKeys(){
			 var texts=document.getElementById("PreSearchForm").getElementsByTagName("input");
			 str = "";
             for(var i=0;i<texts.length;i++){
                if(texts[i].type=="text"){
                	if(texts[i].value != ""){
                    	str = str + texts[i].name + ":" + texts[i].value +",";
                	}
                }
            }
            PreSearchForm.preSearchKeys.value = str;
            return true;
         }
	</script>
  	
  	<style>  
    	.tDnD_whileDrag,table.tablednd tbody tr:hover {  
        	background-color: #eee;  
    	}	  
    </style> 
  </head>
  
  <body>
     <button type = "button" id= "Custom" name = "Custom" onclick = "Custom()">自定义显示</button>
	<form id = "FuzzySearchForm" name = "FuzzySearchForm" action = "FuzSearchEvent" method = "get">
		<input type = "text" id = "FuzzySearchKeys" name = "FuzzySearchKeys"  placeholder="关键字可以用空格隔开"/><input type = "submit" id = "subFuzzySearch" name = "subFuzzySearch" value = "模糊搜索"/>	
	</form>
	<button type = "button" id= "PreSearch" name = "PreSearch" onclick = "showPreSearch()">精确搜索</button>
	<div id = "setHidden" style = "display: none">
	<form id = "PreSearchForm" name = "PreSearchForm" action = "PreSearchEvent" method = "post">
		<table border='1'> <tr> <td>字段</td><td>值</td> <td>字段</td><td>值</td> <td>字段</td><td>值</td> </tr>
		<c:forEach items = "${attriList }" var = "attriList" varStatus = "status">
			<c:if test = "${status.index == 0 }"> <tr> </c:if>
			<c:if test = "${status.index != 0 && status.index % 3 == 0 }"> </tr><tr></c:if>
			<td>${attriList }</td> <td><input name = "${attriList}" id = "${attriList }" type = "text"/> </td>
			<c:if test = "${status.index == 44 }"> </tr> </c:if>
		</c:forEach>
		</table>
		<input id = "preSearchKeys" name = "preSearchKeys" type = "hidden"/>
		<input type = "submit" value = "提交" onclick = "return spliPreSearchKeys()"/>
	</form>
	</div>
	
	<table id = 'EventData' border='1'> 
	<thead>
		<tr> <td>行号</td>
		<c:forEach items = "${attriList }" var = "attriList" varStatus = "status" end = "${showNum - 1 }">
			<th onclick = "$.sortTable.sort('EventData', ${status.index} )" > ${attriList } </th>
		</c:forEach>
		</tr>
	</thead>
	<tbody id = "bbsTab">
		<c:forEach items = "${allEventList }" var = "event">
			<tr> <td></td>
			<c:forEach items = "${attriList }" var = "attriList" end = "${showNum - 1 }">
				<td>${event[fn:toLowerCase(attriList)]}</td>
			</c:forEach>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>
