<#include "module/macro.ftl">
<@head title="${options.blog_title?default('old曾架构师之路')}" keywords="${options.seo_keywords?default('old曾架构师之路')}" description="${options.seo_desc?default('old曾架构师之路')}"></@head>
<#include "module/sidebar.ftl">
<div class="main">
    <#include "module/page-top.ftl">
    <div class="autopagerize_page_element">
        <div class="content">
            <#include "module/post_entry.ftl">
            <#if posts.totalPages gt 1>
                <div class="pagination">
                    <ul class="clearfix">
                        <#if posts.hasPrevious()>
                            <#if posts.number == 1>
                                <li class="pre pagbuttons">
                                    <a class="btn" role="navigation" href="/">上一页</a>
                                </li>
                            <#else>
                                <li class="pre pagbuttons">
                                    <a class="btn" role="navigation" href="/page/${posts.number}">上一页</a>
                                </li>
                            </#if>
                        </#if>
                        <#if posts.hasNext()>
	                        <li class="next pagbuttons">
	                            <a class="btn" role="navigation" href="/page/${posts.number+2}">下一页</a>
	                        </li>
                        </#if>
                    </ul>
                </div>
            </#if>
        </div>
    </div>
</div>
<@footer></@footer>