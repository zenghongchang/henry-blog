<#-- 公共顶部 -->
<#macro header title="曾洪昌博客" keywords="默认文字" description="默认文字" canonical="">
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>${title}</title>
	<meta name="author" content="曾洪昌">
	<meta name="keywords" content="${keywords}">
	<meta name="description" content="${description}" id="meta_description">
	
	<#-- 公共样式 -->
	<link href="static/image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/jquery-confirm/2.5.1/jquery-confirm.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.1/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    <#nested>
</head>
<body>
    <#include "/layout/header.ftl"/>
</#macro>

<#-- 公共底部 -->
<#macro footer>
    <#include "/layout/footer.ftl"/>
    <#nested>
    </body>
</html>
</#macro>
<#-- 分页组件 -->
<#macro pageBar>
    <#if page?exists && (page.pages > 1)>
    <nav>
        <ul class="pager page-btn" data-url="${config.siteUrl}/${url?if_exists}" data-search="${(model.keywords == null || model.keywords == '')?string('false', 'true')}">
            <#if page.hasPreviousPage>
            <li><a class="pointer" data-page="${page.prePage}"><i class="fa fa-angle-double-left"></i></a></li>
            </#if>
            <#list 1..page.pages as item>
            <li><a ${(page.pageNum == item)?string('class="pointer active"','class="pointer" data-page="${item?c}"')}>${item?c}</a></li>
            </#list>
            <#if page.hasNextPage>
            <li><a class="pointer" data-page="${page.nextPage}"><i class="fa fa-angle-double-right"></i></a></li>
            </#if>
        </ul>
    </nav>
    </#if>
</#macro>


<#-- blog-header -->
<#macro blogHeader title="Header">
    <div class="col-sm-12 blog-main">
        <div class="blog-header">
            <h1 class="blog-title">${title}</h1>
            <p class="blog-description" id="hitokoto"></p>
            <div class="info">
                <a href="javascript:void(0);" target="_blank" title="点击QQ联系我"onclick="window.open('tencent://message/?uin=843977358&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow"><i class="fa fa fa-qq fa-fw"></i>QQ联系</a>
                |
                <a href="mailto:yadong.zhang0415@gmail.com" target="_blank" title="点击给我发邮件" rel="external nofollow"><i class="fa fa fa-envelope fa-fw"></i>邮箱联系</a>
                |
                <a href="http://weibo.com/211230415" target="_blank" title="点击查看我的微博" rel="external nofollow"><i class="fa fa fa-weibo fa-fw"></i>@七彩狼丿</a>
            </div>
        </div>
    </div>
</#macro>