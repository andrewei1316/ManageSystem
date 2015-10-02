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
    
    <title>个性化设置</title>
    
    <script src="resources/JS/jquery.js"></script>  
    <script src="resources/JS/jquery.tablednd.js"></script>  
    <style>  
    .tDnD_whileDrag,table.tablednd tbody tr:hover {  
        background-color: #eee;  
    }  
    </style>  
     
    <script language="javascript" type="text/javascript">
		window.onload = function(){
    		var oTable = document.getElementById("bbsTab");
    		for(var i=0;i<oTable.rows.length;i++){
        		oTable.rows[i].cells[0].innerHTML = (i+1);
    		}
		};
	</script>
	
	<script language="javascript" type="text/javascript">
		$(document).ready(function() {

            $("#table-1").tableDnD({
                onDrop: function() {
                    var oTable = document.getElementById("bbsTab");
    				for(var i=0;i<oTable.rows.length;i++){
        				oTable.rows[i].cells[0].innerHTML = (i+1);
    				}
               }
           });
        });
	</script>
	
	<script type="text/javascript">
		function Confirm(num){
           var table = document.getElementById("bbsTab");
           var tr = table.getElementsByTagName("tr");
           var str = "";
           for (var i = 0; i < tr.length; i++) {
               var rowid = tr[i].getAttribute("id");
               str = str + rowid;
               if(i != tr.length - 1) str = str + ",";
           }
           if(settingForm.Num.value > 0 && settingForm.Num.value < num){
           		settingForm.newSettings.value = str;
            	return true;
           }else{
           		alert("您输入的显示数目不合法，请重新输入！");
           		return false;
           }
        }
	</script>
    
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <form id = "settingForm" name = "settingForm" action = "UpdateQuery" method = "post">
      	 要显示的数目：<input type = "text" id = "Num" name = "Num" value = "${showNum }"/><br/>
      	 设置显示顺序：
      	 <table id="table-1" >
       		<tr>
       			<td>序号</td>
       			<td>字段名称</td>
       		</tr>
       		<tbody id="bbsTab">
       		<c:forEach items = "${attriList }" var ="attriList" varStatus = "status">
       			<tr id = "${opSeq[status.count] }"> <td></td>
       			<td>${attriList }</td></tr>
       		</c:forEach>
       		</tbody>
      	</table>
      	<input id = "newSettings" name = "newSettings" type = "hidden"/>
      	<input id = "subSetting" name ="subSetting" type ="submit" value ="提交" onclick = "return Confirm(${length})"/>
      </form>
  </body>
</html>
