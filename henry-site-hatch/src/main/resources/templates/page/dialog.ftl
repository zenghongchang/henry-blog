<#--搜索框Dialog-->
<div class="modal fade nav-search-box" tabindex="-1" role="dialog" aria-labelledby="navSearchModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header" style="padding: 5px 15px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: 10px;">
					<span aria-hidden="true">×</span>
				</button>
				<h4>
					<i class="fa fa-search"></i>搜索文章
				</h4>
			</div>
			<div class="modal-body">
				<form action="#" method="post" class="form-horizontal searchForm" id="searchForm">
					<input type="hidden" name="pageNumber" value="1">
					<div class="input-group bottom-line">
						<input type="text" class="form-control br-none" name="keywords" value="" required="required" placeholder="输入搜索内容">
						<span class="input-group-btn">
							<button class="btn btn-default br-none nav-search-btn pointer" type="submit"><i class="fa fa-search"></i> 搜索</button>
						</span>
					</div>
					<div class="clear"></div>
					<ul class="list-unstyled list-inline search-hot">
						<li><strong style="position: relative;top: 2px;color: #999999;">热门搜索：</strong></li>
						<li>
							<a class="pointer" rel="external nofollow"><span class="label label-default">Java</span></a>
						</li>
						<li>
							<a class="pointer" rel="external nofollow"><span class="label label-primary">Springboot</span></a>
						</li>
						<li>
							<a class="pointer" rel="external nofollow"><span class="label label-success">Linux</span></a>
						</li>
						<li>
							<a class="pointer" rel="external nofollow"><span class="label label-info">Maven</span></a>
						</li>
						<li>
							<a class="pointer" rel="external nofollow"><span class="label label-warning">Bootstrap</span></a>
						</li>
						<li>
							<a class="pointer" rel="external nofollow"><span class="label label-danger">阿里云</span></a>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
</div>	

<div class="modal fade bs-example-modal-sm" id="comment-detail-modal" tabindex="-1" role="dialog" aria-labelledby="comment-detail-modal-label">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				<h4 class="modal-title" id="comment-detail-modal-label">评论信息框</h4>
				<small><i class="fa fa-lightbulb-o fa-fw"></i>可以通过QQ号实时获取昵称和头像</small>
			</div>
			<div class="modal-body">
				<form id="detail-form">
					<input type="hidden" name="avatar">
					<div class="form-group input-logo">
						<input type="text" class="form-control" name="qq" placeholder="选填" value="">
						<img class="pull-left hide" alt="">
						<span class="fa fa-qq pull-left" aria-hidden="true">QQ</span>
					</div>
					<div class="form-group input-logo">
						<input type="text" class="form-control" name="nickname" placeholder="必填" value="匿名">
						<span class="fa fa-user pull-left" aria-hidden="true">昵称</span>
					</div>
					<div class="form-group input-logo">
						<input type="text" class="form-control" name="email" placeholder="选填">
						<span class="fa fa-envelope pull-left" aria-hidden="true">邮箱</span>
					</div>
					<div class="form-group input-logo">
						<input type="text" class="form-control" name="url" placeholder="选填">
						<span class="fa fa-globe pull-left" aria-hidden="true">网址</span>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-default btn-sm" id="detail-form-btn"><i class="fa fa-smile-o"></i>提交评论</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="reward" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				<h4 class="modal-title">山无棱江水为竭，冬雷震震夏雨雪，才敢请君舍</h4>
			</div>
			<div class="modal-body">
				<div class="rewardType" align="center">
					<ul class="list-unstyle list-inline">
						<li style="margin-right: 20px;">
							<div class="iradio_square-green checked" style="position: relative;"><input type="radio" name="type" id="alipay" onclick="PaymentUtils.change(0)" data-index="0" checked="checked" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div><span style="margin-left: 5px;">支付宝</span>
						</li>
						<li style="margin-right: 20px;">
							<div class="iradio_square-green" style="position: relative;"><input type="radio" name="type" id="wechat" onclick="PaymentUtils.change(1)" data-index="1" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div><span style="margin-left: 5px;">微信</span>
						</li>
					</ul>
				</div>
				<div id="qrcode-container" align="center" style="margin-top: 10px;"></div>
				<div style="width: 100%;color: #a3a3a3;font-size: 16px;font-family: 'Microsoft YaHei';text-align: center;">
					转账时请备注“<strong>博客赞助</strong>”
				</div>
			</div>
		</div>
		<small class="font-bold"></small>
	</div>
	<small class="font-bold"></small>
</div>
<#-- loading弹窗 -->
<div id="loading">
	<div class="filter"></div>
	<div class="loader">
		<div class="loading-1"></div>
		<div class="loading-2">Loading...</div>
	</div>
</div>