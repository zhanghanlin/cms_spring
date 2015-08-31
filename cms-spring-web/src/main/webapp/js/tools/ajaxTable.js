(function($) {
	$.fn.ajaxTable = function(options) {
		var settings = {
			url : '',
			pageDom : '.pagination',
			paging : true,
			curPage : 1,
			pageSize : 20,
			columns : [],
			opera : []
		}
		var opts = $.extend(settings, options);
		this.each(function() {
			ajax(opts.curPage);
		});
		var _this = $(this);
		var Status = {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
		}

		function ajax(curPage) {
			$.ajax({
				url : opts.url,
				data : {
					curPage : curPage,
					pageSize : opts.pageSize
				},
				dataType : 'JSON',
				success : function(data) {
					if (!data)
						return;
					var str = [];
					$.each(data.result, function(i, o) {
						str.push('<tr>');
						$.each(opts.columns, function(i, c) {
							str.push('<td>');
							if (c == 'status') {
								str.push(status(o[c]))
							} else if (c == 'createdAt') {
								str.push(new Date(parseInt(o[c]))
										.toLocaleString().replace(/:\d{1,2}$/,
												' '))
							} else {
								if (o[c] instanceof Array) {
									$.each(o[c],function(_i,_o){
										str.push(_o);
										str.push('&nbsp;&nbsp;');
									});
								} else {
									str.push(o[c]);
								}
							}
							str.push('</td>');
						});
						if (opts.opera) {
							str.push('<td>');
							var operaStr = opts.opera.join('&nbsp;');
							if (/{[A-Za-z0-9]+}/.test(operaStr)) {
								var label = operaStr.match(/{[A-Za-z0-9]+}/g);
								$.each(label, function(i, lab) {
									var t = lab.replace(/[{}]+/g, '');
									operaStr = operaStr.replace(lab, o[t]);
								});
							}
							str.push(operaStr);
							str.push('</td>');
						}
						str.push('</tr>');
					})
					_this.find('tbody').html(str.join(''));
					if (opts.paging) {
						pages(data);
					}
				}
			});
		}

		function status(status) {
			status = parseInt(status);
			if (status == Status.NORMAL) {
				return '<span class="label label-success">正常</span>';
			}
			if (status == Status.DISABLE) {
				return '<span class="label label-warning">禁用</span>';
			}
			if (status == Status.DELETE) {
				return '<span class="label label-danger">删除</span>';
			}
		}

		function pages(obj) {
			if (obj.totalPage <= 1) {
				return false;
			}
			var str = [];
			var curPage = obj.curPage;
			var totalPage = obj.totalPage;
			str.push('<li><a href="###" n="1"');
			if (curPage == 1) {
				str.push(' class="not-allowed" ');
			}
			str.push('>&laquo;</a></li>');
			for ( var i = 1; i <= totalPage; i++) {
				str.push('<li');
				if (i == curPage) {
					str.push(' class="active" ');
				}
				str.push('><a href="###" n="');
				str.push(i);
				str.push('">');
				str.push(i);
				str.push('</a></li>');
			}
			str.push('<li><a href="###"  n="' + obj.totalPage + '"');
			if (curPage == totalPage) {
				str.push(' class="not-allowed" ');
			}
			str.push('>&raquo;</a></li>');
			$(opts.pageDom).html(str.join(''));

			$(opts.pageDom).delegate(
					'li a',
					'click',
					function() {
						if ($(this).hasClass('not-allowed')
								|| $(this).parent().hasClass('active')) {
							return false;
						}
						ajax($(this).attr('n'));
					});
		}
	}
})(jQuery);