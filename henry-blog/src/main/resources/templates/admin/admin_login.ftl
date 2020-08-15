<#compress>
<!DOCTYPE html>
<html lang="zh">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	    <title>${options.blog_title!} | <@spring.message code='login.page.title' /></title>
	    <link rel="stylesheet" href="/static/halo-backend/plugins/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" href="/static/halo-backend/css/login.css">	
	    <link rel="stylesheet" href="/static/halo-backend/css/style.min.css">
	</head>
	<body>
		<section class="signin">
			<div class="container">
				<div class="sign-content">
					<div class="row">
						<div class="col-sm-12">
							<div class="signin-form">
								<form action="">
									<div class="form-group">
										<input type="email" class="form-control" name="loginName" id="login-name" placeholder="<@spring.message code='login.form.loginName' />">
									</div>	
									<div class="form-group">
										<input type="password" class="form-control" name="loginPwd" id="login-pwd" placeholder="<@spring.message code='login.form.loginPwd' />" autocomplete="current-password">
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="signin-footer">				
								<button type="button" id="btn-login" data-loading-text="<@spring.message code='login.btn.logining' />" class="btn signin_btn signin_btn_two" onclick="btn_login()"><@spring.message code='login.btn.login' /></button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
	<script src="/static/halo-common/jquery/jquery.min.js"></script>
	<script src="/static/halo-backend/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="/static/halo-backend/plugins/bootstrap/js/qiao.js"></script>
	<script src="/static/halo-backend/plugins/toast/js/jquery.toast.min.js"></script>
	<script src="/static/halo-backend/js/halo.js"></script>
	<script src="/static/halo-backend/js/login.js"></script>
</html>
</#compress>