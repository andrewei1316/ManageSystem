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
    
    <title>查找</title>
    
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
    
	<script language = "javascript">
 		 function showPreSearch(){
 			window.open ('InputKeys', '自定义显示设置', 'height=600, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=no');
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
  	</head>
  
  <body>
     <button type = "button" id= "Custom" name = "Custom" onclick = "Custom()">自定义显示</button>
	<form id = "FuzzySearchForm" name = "FuzzySearchForm" action = "FuzSearchEvent" method = "post">
		<input type = "text" id = "FuzzySearchKeys" name = "FuzzySearchKeys"  placeholder="关键字可以用空格隔开"/><input type = "submit" id = "subFuzzySearch" name = "subFuzzySearch" value = "模糊搜索"/>	
	</form>
	<button type = "button" id= "PreSearch" name = "PreSearch" onclick = "showPreSearch()">精确搜索</button>
	<table class = "table table-hover" id = "EventData">
	<thead>
		<tr> <th>行号</th>
		<c:forEach items = "${attriList }" var = "attriList" varStatus = "status" end = "${showNum - 1 }">
			<th onclick = "$.sortTable.sort('EventData', ${status.count} )" > ${attriList } </th>
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
	</tbody>
	</table>
	<script>
    $(document).ready( function () {
        $('#EventData').bdt();
    });
</script>
  </body>
</html>
