/**
 * 描述：jQuery插件-手机式分页查询
 * 作者：lt
 */
$(function($){
	$.ltpage = {
		//初始化
		init : function(options){
			loading();
			var opts = $.extend({},$.ltpage.defaults,options);
			opts.getData();
			sbottom(opts);
		},
		//鼠标滚动到底部时加载
		sbottom : function(opts){
			$(window).scroll(function(){
				var scrollBottom =  $(document).height() - $(window).height() - $(window).scrollTop();
				if(scrollBottom < 1){
					opts.pageNo ++;
					opts.getData();
				}
			});
		},
		loading : function(){
			$("body").append("<div style='width:100%;height:60px;background:url('"+basePath/images/load.gif+"') no-repeat center;position:fixed;bottom:0;left:0;'></div>");
		}
	};
	//默认参数
	$.ltpage.defaults = {
		pageNo : 1,
		pageSize : 10,
		getData:null//获取数据
	};
})(jQuery);
