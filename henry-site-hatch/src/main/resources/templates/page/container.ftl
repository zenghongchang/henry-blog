<div class="container custome-container">
	<div class="row">
		<#-- 左侧导航栏部分 <#include "left-sidebar.ftl" /> -->
		<div class="col-xs-6 col-md-4 blog-sidebar">
			 <#include "left-sidebar.ftl" />
		</div>
		<#--正文部分 <#include "content.ftl" />-->
		<div class="col-xs-12 col-md-8 blog-main">
			<#include "content.ftl" />
		</div>
	</div>
</div>