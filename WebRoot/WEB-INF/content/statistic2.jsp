<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>统计-柱状图</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">
	<link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/xenon-core.css">
	<link rel="stylesheet" href="assets/css/xenon-forms.css">
	<link rel="stylesheet" href="assets/css/xenon-components.css">
	<link rel="stylesheet" href="assets/css/xenon-skins.css">
	<link rel="stylesheet" href="assets/css/custom.css">
	<link rel="stylesheet" href="assets/css/select2.css">	
	<script src="assets/js/jquery-1.11.1.min.js"></script>	  
	  
	<script src="resources/JS/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/JS/highcharts.js"></script>
    <script type="text/javascript" src="resources/JS/data.js"></script>
    <script type="text/javascript" src="resources/JS/exporting.js"></script>
    <script type="text/javascript" src="resources/JS/column-parsed.js"></script>
	<script type="text/javascript" src="resources/JS/3d-column.js"></script>

    
   <%String graphelement=(String)request.getAttribute("graphelement");%>
   <script>  
   var title2='<%=(String)request.getAttribute("attributeName")%>';

 $(function () {
    $('#container').highcharts({
        data: {
            table: 'datatable'
        },
        chart: {
        depth: 80,
            type: 'column',
            backgroundColor: 'rgb(235, 242, 224)',
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 0,
                
                viewDistance: 25
            }
        },
       	title: {
           text:title2
        },
        subtitle:{
        //text:'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 'Units'
            }
        },
        tooltip: {
            formatter: function () {
            	var tmp = this.point.name+"";
            	if(tmp=="undefined"){
            	   	return '<b>' + this.series.name + '</b><br/>' + this.point.y + ' ' + '0';
                }
                else{ 
                	return '<b>' + this.series.name + '</b><br/>' + this.point.y + ' ' + this.point.name;
               }
            }
        },
         plotOptions: {
             series: {
                 allowPointSelect: true,
                 cursor: 'pointer',
                 events: {
                 click:function(e)
                 {
                  	var strs=e.point.name +"";
                  	if(strs == "undefined") strs = "0"
                 	window.location.href="PreSearchEvent?value="+ title2 +"::::::" + strs;
                 }
                 },
             }
         }
        
    });
    
    
});
</script>
  
    
</head>
<body class="page-body">

	<div class="settings-pane">			
		<a href="#" data-toggle="settings-pane" data-animate="true">
			&times;
		</a>		
		<div class="settings-pane-inner">			
			<div class="row">			
				<div class="col-md-4">					
					<div class="user-info">						
						<div class="user-image">
								<img src="assets/images/user-2.png" class="img-responsive img-circle" />
						</div>
						
						<div class="user-details">
							
							<h3>
								${currUser.userName }								
								<!-- Available statuses: is-online, is-idle, is-busy and is-offline -->
								<span class="user-status is-online"></span>
							</h3>
							
							<p class="user-title">管理员</p>							
							<div class="user-links">
								<a href="MotifyUser" class="btn btn-primary">修改资料</a>
							</div>
							
						</div>
						
					</div>
					
				</div>
				
				<div class="col-md-8 link-blocks-env">
										
					<div class="links-block left-sep">
						<h4>
								<span>帮助</span>
						</h4>
						
						<ul class="list-unstyled">
							<li>
									<i class="fa-angle-right"></i>
									联系我们
							</li>						
						</ul>
					</div>
					
				</div>
				
			</div>
		
		</div>
		
	</div>
	
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->		
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others fixed">			
			<div class="sidebar-menu-inner">					
				<header class="logo-env">					
					<!-- logo -->
					<div class="logo">
						<a href="Events" class="logo-expanded">
							<img src="assets/images/logo@2x.png" width="" alt="" />
						</a>
						
						<a href="Events" class="logo-collapsed">
							<img src="assets/images/logo-collapsed@2x.png" width="40" alt="" />
						</a>
					</div>					
					<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
					<div class="settings-icon">
						<a href="#" data-toggle="settings-pane" data-animate="true">
							<i class="fa-cog"></i>
						</a>
					</div>
					
								
				</header>
						
				
						
				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
					<li>
						<a href="Events">
							<i class="fa-search"></i>
							<span class="title">搜索</span>
						</a>
					</li>
					<li>
						<a href="Statistics_column">
							<i class="fa-area-chart"></i>
							<span class="title">统计</span>
						</a>
						<ul>
							<li>
								<a href="newStatistics">
									<span class="title">饼图</span>
								</a>
							</li>
							<li>
								<a href="Statistics_column">
									<span class="title">柱状图</span>
								</a>
							</li>								
						</ul>
					</li>
					<li>
						<a href="importFile">
							<i class="fa-upload"></i>
							<span class="title">导入</span>
						</a>
					</li>
				</ul>			
			</div>
			
		</div>
		
		<div class="main-content">
					
			<!-- User Info, Notifications and Menu Bar -->
			<nav class="navbar user-info-navbar" role="navigation">
				
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">				
					<li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li>				
				</ul>
				
				
				<!-- Right links for user info navbar -->
				<ul class="user-info-menu right-links list-inline list-unstyled">										
					<li class="dropdown user-profile">
						<a href="#" data-toggle="dropdown">
							<img src="assets/images/user-4.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
							<span>
								${currUser.userName }		
								<i class="fa-angle-down"></i>
							</span>
						</a>
						
						<ul class="dropdown-menu user-profile-menu list-unstyled">
							<li>
								<a href="MotifyUser">
									<i class="fa-edit"></i>
									修改资料
								</a>
							</li>

							<li class="last">
								<a href="LogOut">
									<i class="fa-power-off"></i>
									退出
								</a>
							</li>
						</ul>
					</li>
					
				</ul>
				
			</nav>
			<div class="panel panel-default">
				<div class="panel-heading">					
				<form id="attName" name="attName" action="Statistics_column_new">
					<script type="text/javascript">
						jQuery(document).ready(function($)
										{
											$("#attributeName").select2({
												
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
						</script>
									
					<select id="attributeName" name="attributeName"  onchange="submit();" style="height: 32px;box-shadow: none;border: 1px solid #e4e4e4;">
						<option>请选择字段</option>
						<c:forEach items = "${attriList }" var ="attriList" varStatus = "status">
       					<option value="${attriList }">${attriList }</option>
       					</c:forEach>
					</select>					
				</form>
				</div>				
				<div class="panel-body">					
				<div id="container" style="min-width:800px;height:400px"></div>

  <table id="datatable" style="display:none">
	
	
	<thead>
		<tr>
			<th></th>
			<th>items</th>
			
		</tr>
	</thead>
	<tbody>
	<c:forEach items = "${chartList }" var = "chartList" varStatus = "status">
		<tr>
			<th> ${fn:split(chartList,"$")[0] }</th>
			<td> ${fn:split(chartList,"$")[1] }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	
				</div>
			</div>

			
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-2">
				
				<div class="footer-inner">			
					<div class="footer-text">
						&copy; 2015 
						<strong>Designed By</strong> 
						王子恒、罗睿、和树伟
					</div>
					
					
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
					
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>
						
					</div>
					
				</div>
				
			</footer>
		</div>
	</div>
	
	
	



	<!-- Bottom Scripts -->
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/TweenMax.min.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/xenon-api.js"></script>
	<script src="assets/js/xenon-toggles.js"></script>
	<script src="assets/js/select2.min.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="assets/js/xenon-custom.js"></script>

			
  </body>
</html>
