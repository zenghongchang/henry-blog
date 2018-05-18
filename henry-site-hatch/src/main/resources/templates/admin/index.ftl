<#include "../page/vars.ftl"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="utf-8" xmlns="http://www.w3.org/1999/xhtml" lang="utf-8">
<head>
	<meta charset="utf-8" />
	<title>后台管理首页</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="Henry'site" name="description" />
	<meta content="Henry" name="author" />
	<#-- CSS -->
	<link href="${cssPath}plugin/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/bootstrap/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/font-awesome/font-awesome.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/style/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/style/style.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/style/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/jquery/jquery.gritter.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/jquery/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${cssPath}plugin/jquery/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${cssPath}plugin/date/daterangepicker.css" rel="stylesheet" type="text/css" />
	<link href="${cssPath}plugin/other/fullcalendar.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}app/other/default.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}app/other/uniform.default.css" rel="stylesheet" type="text/css"/>
	<#-- JavaScript -->
	<script src="${plugPath}jquery/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${plugPath}jquery/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${plugPath}bootstrap/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="${plugPath}other/excanvas.min.js"></script>
	<script src="${plugPath}other/respond.min.js"></script>
	<![endif]-->   
	<script src="${plugPath}jquery/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${plugPath}jquery/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${plugPath}jquery/jquery.vmap.js" type="text/javascript"></script>  
	<script src="${plugPath}jquery/jquery.vmap.russia.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.vmap.world.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.vmap.europe.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.vmap.germany.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.vmap.usa.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.vmap.sampledata.js" type="text/javascript"></script> 
	<script src="${plugPath}jquery/jquery.flot.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.flot.resize.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.pulsate.min.js" type="text/javascript"></script>
	<script src="${plugPath}date/date.js" type="text/javascript"></script>
	<script src="${plugPath}date/daterangepicker.js" type="text/javascript"></script>   
	<script src="${plugPath}jquery/jquery.gritter.js" type="text/javascript"></script>
	<script src="${plugPath}fullcalendar.min.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.easy-pie-chart.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.sparkline.min.js" type="text/javascript"></script> 
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${jsPath}app/app.js" type="text/javascript"></script>
	<script src="${jsPath}app/index.js" type="text/javascript"></script>      
</head>
<body class="page-header-fixed">
    <#-- 头部导航栏 -->
    <div class="header navbar navbar-inverse navbar-fixed-top">
        <!-- BEGIN TOP NAVIGATION BAR -->
        <div class="navbar-inner">
            <div class="container-fluid">
                <!-- BEGIN LOGO -->
                <a class="brand" href="index.html">
                    <img src="${imagesPath}logo.png" alt="logo" />
                </a>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                    <img src="${imagesPath}menu-toggler.png" alt="" />
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <#-- 头部导航栏开始 -->
                <ul class="nav pull-right">
                    <#--通知消息开始-->
                    <li class="dropdown" id="header_notification_bar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-warning-sign"></i>
                            <span class="badge">100</span>
                        </a>
                        <ul class="dropdown-menu extended notification">
                        </ul>
                    </li>
                    <#--通知消息结束-->
                    <#--收件箱开始-->
                    <li class="dropdown" id="header_inbox_bar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-envelope"></i>
                            <span class="badge">5</span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                        	<li>
								<p>You have 14 new notifications</p>
							</li>
							<li>
								<a href="#">
									<span class="label label-success"><i class="icon-plus"></i></span>	
									New user registered. 	
									<span class="time">Just now</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-important"><i class="icon-bolt"></i></span>
									Server #12 overloaded. 
									<span class="time">15 mins</span>
								</a>
							</li>
                        </ul>
                    </li>
                    <#--收件箱结束-->
                    <#-- 待办事项开始 -->
                    <li class="dropdown" id="header_task_bar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-tasks"></i>
                            <span class="badge">5</span>
                        </a>
                        <ul class="dropdown-menu extended tasks">
                        </ul>
                    </li>
                    <#-- 待办事项结束 -->
                    <#-- 登陆信息开始  -->
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img alt="" src="${imagesPath}avatar1_small.jpg" />
                            <span class="username">
                            	<#if userDomain??>
                            		${userDomain.username}
                            	</#if>
                            </span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">                        	
							<li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a></li>
							<li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>
							<li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox(3)</a></li>
							<li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>
							<li class="divider"></li>
							<li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>
                        </ul>
                    </li>
                    <#-- 登陆信息结束  -->
                </ul>
                <#-- 头部导航栏结束 -->
            </div>
        </div>
        <!-- END TOP NAVIGATION BAR -->
    </div>
    <div class="page-container">
    	<div class="page-sidebar nav-collapse collapse">
    		<ul class="page-sidebar-menu">
    			<li>
					<div class="sidebar-toggler hidden-phone"></div>
				</li>
				<li>
					<form class="sidebar-search">
						<div class="input-box">
							<a href="javascript:;" class="remove"></a>
							<input type="text" placeholder="Search..." />
							<input type="button" class="submit" value=" " />
						</div>
					</form>
				</li>
				<li class="start active">
					<a href="javascript:;">
						<i class="icon-dashboard"></i>
						<span class="title">仪表盘</span>
						<span class="arrow"></span>
					</a>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-github-alt"></i>
						<span class="title">发布文章</span>
						<span class="arrow"></span>
					</a>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-comment"></i>
						<span class="title">评论管理</span>
						<span class="arrow"></span>
					</a>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-file-text"></i>
						<span class="title">文件管理</span>
						<span class="arrow"></span>
					</a>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-external-link"></i>
						<span class="title">友情链接</span>
						<span class="arrow"></span>
					</a>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-cogs" aria-hidden="true"></i>
						<span class="title">系统设置</span>
						<span class="arrow"></span>
					</a>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-bookmark-empty"></i>
						<span class="title">分类标签</span>
						<span class="arrow"></span>
					</a>
				</li>
    		</ul>
    	</div>
    	<div class="page-content">
    		<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div id="portlet-config" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3>Widget Settings</h3>
				</div>
				<div class="modal-body">
					Widget settings form goes here
				</div>
			</div>
			<div class="container-fluid">
				<div class="span12">
				</div>
			</div>
    	</div>
    </div>
    <div class="footer">
		<div class="footer-inner">
			Copyright © 2017-${copyRight} By Henry.
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
	</div>
	<script>
		jQuery(document).ready(function() {   
		   App.init(); // initlayout and core plugins	
		});
	</script>	
</body>
</html>