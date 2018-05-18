<#include "../page/vars.ftl"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="utf-8" xmlns="http://www.w3.org/1999/xhtml" lang="utf-8">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>loginForm</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="Henry'site" name="description" />
	<meta content="Henry" name="author" />
	<link rel="shortcut icon" href="${imagesPath}favicon.ico" />
	<#-- CSS -->
	<link href="${cssPath}plugin/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/bootstrap/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/font-awesome/font-awesome.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/style/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/style/style.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}plugin/style/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}app/other/default.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}app/other/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link href="${cssPath}app/login/login-soft.css" rel="stylesheet" type="text/css"/>
	<#-- JS -->
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
	<script src="${plugPath}jquery/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${plugPath}jquery/jquery.backstretch.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${jsPath}app/app.js" type="text/javascript"></script>
	<script src="${jsPath}app/login.js" type="text/javascript"></script>      
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
	<#-- LOGO -->
	<div class="logo">
		<img src="${imagesPath}logo-big.png" alt="" /> 
	</div>
	<#-- LOGO -->
	<div class="content">
		<#-- 开始登陆框 -->
		<form class="form-vertical login-form" action="/admin/login" method="post">
			<h3 class="form-title">欢迎登陆老曾网站后台</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>请输入账号和密码</span>
			</div>
			<#if error??>
				<div class="alert alert-error show">
					<button class="close" data-dismiss="alert"></button>
					<span>${error}</span>
				</div>
			</#if>
			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" autocomplete="off" type="text" placeholder="Username" name="username"/>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input class="m-wrap placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password"/>
					</div>
				</div>
			</div>
			<div class="form-actions form-action-login">
				<button type="submit" class="btn blue">
					Login <i class="m-icon-swapright m-icon-white"></i>
				</button>       
			</div>
			<div class="forget-password">
				<h4>Forgot your password ?</h4>
				<p>
					no worries, click <a href="javascript:;" class="" id="forget-password">here</a>
					to reset your password.
				</p>
			</div>
		</form>
		<#-- 结束登陆框 -->   
		<#-- 开始忘记密码 -->
		<form class="form-vertical forget-form" action="index.html" method="post">
			<h3 class="">Forget Password ?</h3>
			<p>Enter your e-mail address below to reset your password.</p>
			<div class="control-group">
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-envelope"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				<button type="button" id="back-btn" class="btn">
				<i class="m-icon-swapleft"></i> Back
				</button>
				<button type="submit" class="btn blue pull-right">
					Submit <i class="m-icon-swapright m-icon-white"></i>
				</button>           
			</div>
		</form>
		<#-- 忘记密码结束 -->		
	</div>
	<!-- END LOGIN -->
	<#-- copyright -->
	<div class="copyright">
		Copyright © 2017-${copyRight} By Henry.
	</div>
	<script>
		$(document).ready(function() {   
			App.init();
		  	Login.init();
		});
	</script>
</body>
<!-- END BODY -->
</html>