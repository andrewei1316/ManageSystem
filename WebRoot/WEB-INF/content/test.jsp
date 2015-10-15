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
    
    <title>輸入查找的字段值</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type = "text/css" href="resources/CSS/bootstrap-table.css">
	<link rel="stylesheet" type = "text/css" href="resources/CSS/bootstrap.min.css">
	<link rel="stylesheet" type = "text/css" href="resources/CSS/jquery.bdt.min.css">
	<link rel="stylesheet" type = "text/css" href="resources/CSS/jquery.bdt.css">
	<link rel="stylesheet" type = "text/css" href="resources/CSS/font-awesome.min.css">
	
	<script type = "text/javascript" src="resources/JS/jquery.js"></script>  
	<script type = "text/javascript" src="resources/JS/sortTable.js"></script>
    <script type = "text/javascript" src="resources/JS/jquery.tablednd.js"></script>  
    <script type = "text/javascript" src="resources/JS/bootstrap-table.js"></script>  
    <script type = "text/javascript" src="resources/JS/bootstrap.min.js"></script>
    <script type = "text/javascript" src="resources/JS/jquery.bdt.js"></script>
    <script type = "text/javascript" src="resources/JS/jquery.sortelements.js"></script>
    
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

<table class = "table table-hover" id = "EventData">
	<thead>
		<tr> <th>行号</th><th>字段</th><th>值</th></tr>
	</thead>
	<tbody id = "bbsTab">
		<c:forEach items = "${attriList }" var = "attriList" varStatus = "status">
			<tr><td> ${attriList } </td> <td> <input type = "text" id = "${attriList }" name = "${attriList }"/></td></tr>
		</c:forEach>
	</tbody>
	</table>
		<input id = "preSearchKeys" name = "preSearchKeys" type = "hidden"/>
		<input type = "submit" value = "提交" onclick = "return spliPreSearchKeys()"/>
	</form>
	<script>
    $(document).ready( function () {
        $('#EventData').bdt();
    });
</script>
  </body>
</html>
