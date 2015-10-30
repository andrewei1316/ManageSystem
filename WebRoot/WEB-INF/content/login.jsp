<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/xenon-core.css">
	<link rel="stylesheet" href="assets/css/xenon-forms.css">
	<link rel="stylesheet" href="assets/css/xenon-components.css">
	<link rel="stylesheet" href="assets/css/xenon-skins.css">
	<link rel="stylesheet" href="assets/css/custom.css">

	<script src="assets/js/jquery-1.11.1.min.js"></script>
</head>
<body class="page-body login-page">

	
	<div class="login-container">
	
		<div class="row">
		
			<div style="width:35%;margin: auto;position: absolute;top: 50;left: 0;bottom: 0;right: 0;">			
				<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);												
						$("form#logInForm").validate({
						
							rules: {
								userLogId: {
									required: true
								},
								
								userPws: {
									required: true
								}
							},
							
							messages: {
								userLogId: {
									required: '请输入用户名'
								},
								
								userPws: {
									required: '请输入密码'
								}
							},
														
						});
						$("form#logInForm .form-group:has(.form-control):first .form-control").focus();
					});
				</script>

				<div class="errors-container">													
				</div>
				
				<!-- Add class "fade-in-effect" for login form effect -->
				<form method="post" role="form" class="login-form fade-in-effect" id = "logInForm" name = "logInForm" action = "LogInCheck">
					
					<div class="login-header">
						<a class="logo">
							<img src="assets/images/logo@2x.png" />			
						</a>	
					</div>
	
					
					<div class="form-group">
						<label class="control-label" for="userLogId">用户名</label>
						<input type="text" class="form-control input-dark" name="userLogId" id="userLogId" autocomplete="off" />
					</div>
					
					<div class="form-group">
						<label class="control-label" for="userPws">密码</label>
						<input type="password" class="form-control input-dark" name="userPws" id="userPws" autocomplete="off" />
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-dark  btn-block text-left">
							<i class="fa-lock"></i>
							登录
						</button>
						

						</div>											
				</form>										
			</div>			
		</div>		
	</div>



	<!-- Bottom Scripts -->
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/TweenMax.min.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/xenon-api.js"></script>
	<script src="assets/js/xenon-toggles.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="assets/js/toastr/toastr.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="assets/js/xenon-custom.js"></script>

</body>
</html>