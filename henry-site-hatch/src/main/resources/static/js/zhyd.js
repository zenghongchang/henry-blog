function automaticAlignmentHeigt() {
    if ($('.blog-sidebar') && $('.blog-main')) {
        var blog_sidebar_height = $('.blog-sidebar').height();
        alert(blog_sidebar_height);
        var blog_main_height = $('.blog-main').height();
        alert(blog_main_height);
        if (blog_sidebar_height > blog_main_height) {
            $('.blog-main').height(blog_sidebar_height);
        } else {
            $('.blog-sidebar').height(blog_main_height);
        }
    }
}

function initNavbar() {
	$(".navbar .navbar-nav li").each(function() {
		var $this = $(this);
		if($this.hasClass("dropdown")) {
			$this.on("mouseover", function() {
				$this.addClass("open").find("a:first-child").attr("aria-expanded", "true");
			}).on("mouseout", function() {
				$this.removeClass("open").find("a:first-child").attr("aria-expanded", "false");
			});
		}
		$this.find("a").each(function() {
			var $this = $(this);
			var $parent = $this.parent();
			$parent.removeClass("active");
			if($this.attr("href") === $.tool.currentPath()) {
				$parent.toggleClass("active");
			}
		});
	});
}

function initArticeMenu() {
	$(function() {
		if($('.blog-info-body') && $('.blog-info-body')[0]) {
			// console.log("生成文章目录");
			var padding = [0, 10, 20, 30, 40];
			var liDom, aDom, spanDom;
			var dNum = 0;
			$('.blog-info-body').find('h2,h3').each(function(index, item) {
				var $this = $(this);
				$this.before($('<span id="menu_' + index + '" class="menu-point"></span>'));
				$this.addClass("menu-title");
				var tagText = $this.text();
				var tagName = $this[0].tagName.toLowerCase();
				var tagIndex = parseInt(tagName.charAt(1)) - 1;
				spanDom = '<i class="fa fa-angle-right"></i>';
				aDom = '<a href="#menu_' + index + '" style="display:inline-block;">' + tagText + '</a>';
				liDom = '<li style="padding-left:' + padding[tagIndex] + 'px;line-height: 2;">' + spanDom + aDom + '</li>';
				$("#article-menu ul").append(liDom);
				dNum++;
			});
			if(dNum > 0) {
				$("#article-menu").show();
				$('.article-module').removeClass('hide');
				var sc = $(document); //得到document文档对象。
				var am = $(".article-module"); // 文章目录对象
				var win = $(window); //得到窗口对象
				win.scroll(function() {
					bindMenuScroll();
				});
				bindMenuScroll();

				function bindMenuScroll() {
					if($.tool.currentPath().indexOf('/article/') !== -1) {
						if(sc.scrollTop() >= 200) {
							if(!am.hasClass("fixed")) {
								var top = win.width() > 768 ? '85px' : '55px';
								am.addClass('fixed').css({
									width: '21.7%',
									right: 0,
									border: '1px solid rgba(0, 0, 0, 0.1)'
								}).animate({
									top: top
								}, 100);
								$('.close-article-menu').removeClass('hide');
							}
						} else {
							am.removeClass('fixed').removeAttr('style');
							$('.close-article-menu').addClass('hide');
						}
					}
				}
				$('.close-article-menu').click(function() {
					am.addClass('hide');
				});
			}
		}
	});
}

function initScrollMenu() {
	var topmenu = $("#topmenu"); //得到导航对象
	var mainmenu = $("#mainmenu"); //得到导航对象
	var win = $(window); //得到窗口对象
	var sc = $(document); //得到document文档对象。
	var am = $(".article-module"); // 文章目录对象
	bindScroll();
	win.scroll(function() {
		bindScroll();
	});

	function bindScroll() {
		if(sc.scrollTop() >= 100) {
			if(!mainmenu.hasClass("transparent")) {
				topmenu.animate({
					opacity: '0'
				}, 0);
				mainmenu.addClass('transparent');
				if(win.width() > 768) {
					mainmenu.animate({
						top: '0',
						'z-index': 1000
					}, 1);
				}
			}
		} else {
			topmenu.animate({
				opacity: '1'
			}, 0);
			mainmenu.removeClass('transparent');
			if(win.width() > 768) {
				mainmenu.animate({
					top: '35',
					'z-index': 998
				}, 1);
			}
		}
	}
}
var PaymentUtils = window.payment || {
	config: [{
		url: '/image/alipay_nb.jpg',
		desc: '支付宝转账'
	}, {
		url: '/image/wechat_nb.jpg',
		desc: '微信转账'
	}],
	show: function() {
		$("#reward").modal('show');
		this.change(0);
		$("#reward input").on('ifChecked', function(event) {
			var index = $(this).data("index");
			PaymentUtils.change(index);
		});
	},
	hide: function() {
		$("#reward").modal('hide');
	},
	change: function(index) {
		var config = this.config[index];
		$("#qrcode-container").empty();
		$('<img  src="' + config.url + '" style="width: 250px;height: 250px;" alt="' + config.desc + '">').appendTo($("#qrcode-container"));
	}
};
$(function() {
	// automaticAlignmentHeigt();
	initNavbar();
	initArticeMenu();
	initScrollMenu();
	$('.to-top').toTop({
		autohide: true, //返回顶部按钮是否自动隐藏。可以设置true或false。默认为true
		offset: 100, //页面滚动到距离顶部多少距离时隐藏返回顶部按钮。默认值为420
		speed: 500, //滚动和渐隐的持续时间，默认值为500
		right: 25, //返回顶部按钮距离屏幕右边的距离，默认值为15
		bottom: 50 //返回顶部按钮距离屏幕顶部的距离，默认值为30
	});

	$("[data-toggle='tooltip']").tooltip();
	$('[data-toggle="popover"]').popover();

	// 图片预览
	$(".showImage").fancybox();

	$(document).ajaxStart(function() {
		$("#loading").show();
	}).ajaxStop(function() {
		$("#loading").hide();
	});

	if($.rating) {
		$.rating.init(5);
	}

	if($.bubble) {
		$.bubble.init();
	}

	getCurrentDate();

	setInterval(function() {
		getCurrentDate();
	}, 1000);

	/*
	 * 获取当前时间 
	 */
	function getCurrentDate() {
		var now = new Date();
		var weekArr = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
		$("#currentTime").html(now.format('yyyy年MM月dd日 hh时mm分ss秒') + " " + weekArr[now.getDay()]);
	}

	/*
	 * 显示取链的表格
	 */
	$(".showContent").click(function() {
		$(this).toggleClass('fa-plus-square fa-minus-square');
		$(".disable-content").slideToggle(400);
	});

	if(/iphone|ipod|ipad|ipad|mobile/i.test(navigator.userAgent.toLowerCase())) {
		$('.share-sd').click(function() {
			$('#share').animate({
					opacity: 'toggle',
					top: '-80px'
				},
				500).animate({
					top: '-60px'
				},
				'fast');
			return false;
		});
	} else {
		$(".share-sd").mouseover(function() {
			$(this).children("#share").show();
		});
		$(".share-sd").mouseout(function() {
			$(this).children("#share").hide();
		});
	}

	$("img.lazy-img").lazyload({
		placeholder: "/image/loading.gif",
		effect: "fadeIn",
		threshold: 100
	});
	$(window).bind("load", function() {
		var timeout = setTimeout(function() {
			$("img.lazy-img").trigger("sporty");
		}, 3000);
	});

	/* 热门搜索标签点击事件 */
	$(".search-hot li").click(function() {
		var $this = $(this);
		var text = $this.find("a span").text();
		$this.parents(".searchForm").find("input[name=keywords]").val(text);
		$this.parents(".searchForm").find(".nav-search-btn").click();
	});

	/* 分页按钮点击事件 */
	$(".page-btn li a").click(function() {
		var $this = $(this);
		var $parents = $this.parents(".page-btn");
		var search = $parents.data("search");
		var url = $parents.data("url");
		var pageNum = $this.data("page") || 1;
		if(!pageNum) {
			return;
		}
		var action = url + "/" + pageNum;
		if(search) {
			$("#searchForm").find("input[name=pageNumber]").val(pageNum);
			$(".nav-search-btn").click();
		} else {
			window.location.href = action;
		}
	});
});