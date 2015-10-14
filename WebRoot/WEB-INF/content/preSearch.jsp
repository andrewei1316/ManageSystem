<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>填写查找字段</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  	
  </head>
  
  <body>
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
  </body>
</html>
