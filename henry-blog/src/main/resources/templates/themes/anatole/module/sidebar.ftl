<div class="sidebar animated fadeInDown">
    <div class="logo-title">
        <div class="title">
        	<#if options.anatole_style_avatar_circle?default('false')=='true'></#if>
            <img src="${options.blog_logo?default("/anatole/source/images/9f61728e5cac70480bbe663ab4c40ae.jpg")}" style="width:127px;border-radius:50%;" />
            <h3 title="">
                <a href="/">${options.blog_title?default("ANATOLE")}</a>
            </h3>
            <div class="description">
                <#if options.anatole_style_hitokoto?default("false")=="true">
                    <p id="yiyan">获取中...</p>
                <#else >
                    <p>${user.userDesc?default("好记性不如烂笔头")}</p>
                </#if>
            </div>
        </div>
    </div>
    <#include "social-list.ftl">
    <#--
    <div class="footer">
        <a target="_blank" href="#">
            <span>Designed by </span>
            <a href="https://www.caicai.me">CaiCai</a>
            <div class="by_halo">
                <a href="https://github.com/ruibaby/halo" target="_blank">Proudly published with Halo&#65281;</a>
            </div>
            <div class="footer_text">
                <@footer_info></@footer_info>
            </div>
        </a>
    </div>
    -->
</div>