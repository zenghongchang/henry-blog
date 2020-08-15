<#list posts.content as post>
    <div class="post animated fadeInDown">
        <div class="post-title">
            <h3>
                <a href="/archives/${post.postUrl}">${post.postTitle}</a>
            </h3>
        </div>
        <div class="post-content">
            <div class="p_part">
                <p>${post.postSummary?if_exists}...</p>
            </div>
            <div class="p_part">
                <p></p>
            </div>
        </div>
        <div class="post-footer">
            <div class="meta">
                <div class="info">
                    <i class="fa fa-calendar"></i> 
                    <span class="date"><#if post.postDate??>${post.postDate?string("yyyy-MM-dd")}</#if></span>
                    <i class="fa fa-comment-o"></i>
                    <a href="/archives/${post.postUrl}#comment_widget">评论</a>
                    <#if post.tags??>
                   		<#if post.tags?size gt 0>
	                    	<i class="fa fa-tag"></i>
	                        <#list post.tags as tag>
	                        	<a href="/tags/${tag.tagUrl}" class="tag">&nbsp;${tag.tagName}</a>
	                        </#list>
	                    </#if>
                    </#if>
                </div>
            </div>
    	</div>
	</div>
</#list>