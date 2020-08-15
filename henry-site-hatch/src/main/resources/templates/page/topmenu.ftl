<#--顶部导航栏部分-->
<nav id="topmenu" class="navbar navbar-default navbar-fixed-top" style="opacity: 1;">
	<div class="menu-box">
		<#--站点通知LOGO-->
		<div class="pull-left notify">
			<i class="fa fa-volume-up">
				<span>站内公告</span>
			</i>
		</div>
		
		<div class="pull-left">
			<ul class="list-unstyled list-inline">
				<li><span id="currentTime"></span></li>
			</ul>
			<div class="clear"></div>
		</div>

		<div class="menu-topmenu-container pull-right">
			<ul class="list-unstyled list-inline pull-left">
				<li>
					<a href="#" class="menu_a" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="关于博客"><i class="fa fa-info fa-fw"></i>关于</a>
				</li>
				<li>
					<a href="#" class="menu_a" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="友情链接"><i class="fa fa-link fa-fw"></i>友情链接</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
<nav id="mainmenu" class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="menu-box">
		<#-- 折叠收起部分样式 -->
		<div class="navbar-header">
			<span class="pull-right nav-search toggle-search" data-toggle="modal" data-target=".nav-search-box"><i class="fa fa-search"></i></span>
			<a type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="navbar-brand logo" href="#"></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<#--LOGO说明部分-->
			<div class="pull-left site-desc">
				<h1 style="font-size: 20px;font-weight: 700;" class="auto-shake">
					<a href="#" data-original-title="用文字记录成长" data-toggle="tooltip" data-placement="auto">曾洪昌博客</a>
				</h1>
				<p class="site-description">用文字记录成长</p>
			</div>
			<#--导航栏部分-->
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="index.html" class="menu_a"><i class="fa fa-home"></i>首页</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-linux"></i>运维天地<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<li>
							<a aria-expanded="false" href="#" title="Linux运维">Linux</a>
						</li>
						<li>
							<a aria-expanded="false" href="#" title="DBA数据库">DBA</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-code"></i>编程语言<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<li>
							<a aria-expanded="false" href="#" title="点击查看《后端编程》的文章">后端编程</a>
						</li>
						<li>
							<a aria-expanded="false" href="#" title="点击查看《前端编程》的文章">前端编程</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-th-list"></i>其他技术<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<li>
							<a aria-expanded="false" href="#" title="点击查看《闲言碎语》的文章">闲言碎语</a>
						</li>
						<li>
							<a aria-expanded="false" href="#" title="点击查看《开发工具》的文章">开发工具</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="guestbook.html" class="menu_a"><i class="fa fa-comments-o"></i>留言板</a>
				</li>
				<li>
					<span class="pull-right nav-search main-search" data-toggle="modal" data-target=".nav-search-box">
						<i class="fa fa-search"></i>
					</span>
				</li>
			</ul>
		</div>
	</div>
</nav>