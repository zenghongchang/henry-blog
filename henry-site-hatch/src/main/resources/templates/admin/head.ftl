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
	<script src="${jsPath}app/login/app.js" type="text/javascript"></script>
	<script src="${jsPath}app/login/login-soft.js" type="text/javascript"></script>      
</head>