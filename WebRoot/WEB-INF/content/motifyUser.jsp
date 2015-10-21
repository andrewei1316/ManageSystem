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
    
    <title>更新用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript">
  	function Confirm(){
  		var a = document.getElementsByTagName("input");
    for(var i = 0; i < a.length; i++){
        if(a[i].type != "password" && a[i].value == ""){
            alert("表单中含有未填写字段, 请确保填写了所有字段!");
            return false;
        }
    }
    with(document.all){
        if(oldUserPsw.value != "" && oldUserPsw.value != ${currUser.userPsw}){
            alert("您输入的原始密码不正确！");
            return false;
        }
        if(oldUserPsw.value != "" && newUserPsw.value == ""){
        	alert("密码不能为空，请重新输入!");
        	return false;
        }
        if(newUserPsw.value != reNewUserPsw.value){
            alert("您两次输入的密码不同，请重新输入！");
            return false;
        }
    }
    return confirm("确认修改?");
  }
  </script>
  </head>
  
  <body>
  		<form id = "updateUserForm" name = "updateUserForm" action = "UpdateUser" method = "post">
  			登录账号<input type = "text" id = "userLogId" name = "userLogId" value = "${currUser.userLogId }" readonly/><br/>
  			用 户 名<input type = "text" id = "userName" name = "userName" value = "${currUser.userName }"/><br/>
  			旧 密 码<input type = "password" id = "oldUserPsw" name = "oldUserPsw" placeholder="需要更改时填写"/><br/>
  			新 密 码<input type = "password" id = "newUserPsw" name = "newUserPsw" placeholder="需要更改时填写"/><br/>	
  			确认新密码<input type = "password" id = "reNewUserPsw" name = "reNewUserPsw" placeholder="需要更改时填写"/><br/>
  			<input type = "submit" value = "提交" onclick = "return Confirm()"/> 			
  		</form>
  </body>
</html>
