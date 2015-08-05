$(function() {
	var role = {
		pageSize : 1,
		tableObj : $('#roleTable'),
		Status : {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
		},
		tableHead : function() {
			return '<tr>\
						<td>ID</td>\
						<td>权限名</td>\
						<td>权限说明</td>\
						<td>状态</td>\
						<td>创建时间</td>\
					</tr>';
		},
		parseObj : function(o) {
			return '<tr>\
						<td>' + o.id + '</td>\
						<td>'
					+ o.name + '</td>\
						<td>' + o.note
					+ '</td>\
						<td>' + role.roleStatus(o.status)
					+ '</td>\
						<td>' + Base.date.unixToDate(o.createdAt)
					+ '</td>\
						<td>' + role.roleOpera(o)
					+ '</td>\
					</tr>';
		},
		parsePage : function(obj) {
			if (obj.totalPage <= 0) {
				return false;
			}
			var preCss = '';
			var nextCss = '';
			if (obj.curPage == 1) {
				preCss = 'not-allowed';
			}
			if (obj.curPage == obj.totalPage) {
				nextCss = 'not-allowed';
			}
			var page = '<li><a href="###" n="1" class="' + preCss
					+ '">&laquo;</a></li>';
			for ( var i = 1; i <= obj.totalPage; i++) {
				if (i == obj.curPage) {
					page += '<li class="active"><a href="###" n="' + i + '">'
							+ i + '</a></li>';
				} else {
					page += '<li><a href="###" n="' + i + '">' + i
							+ '</a></li>';
				}
			}
			page += '<li><a href="###"  n="' + obj.totalPage + '" class="'
					+ nextCss + '">&raquo;</a></li>';
			$('.box-footer ul').html(page);
			role.pageOpera();
		},
		list : function(curPage) {
			var param = {
				curPage : curPage,
				pageSize : role.pageSize
			};
			$.getJSON('/role/list', param, function(obj) {
				var html = '';
				$.each(obj.result, function(i, o) {
					html += role.parseObj(o);
				})
				role.tableObj.html(role.tableHead() + html);
				role.parsePage(obj);
			});
		},
		roleOpera : function(o) {
			return '<button type="button" class="btn btn-default btn-xs">查看</button>\
						<button type="button" class="btn btn-default btn-xs">删除</button>\
						<button type="button" class="btn btn-default btn-xs">禁用</button>';
		},
		roleStatus : function(status) {
			status = parseInt(status);
			if (status == role.Status.NORMAL) {
				return '<span class="label label-success">正常</span>';
			}
			if (status == role.Status.DISABLE) {
				return '<span class="label label-warning">禁用</span>';
			}
			if (status == role.Status.DELETE) {
				return '<span class="label label-danger">删除</span>';
			}
		},
		pageOpera : function() {
			$('.pagination').delegate(
					'li a',
					'click',
					function() {
						if ($(this).hasClass('not-allowed')
								|| $(this).parent().hasClass('active')) {
							return false;
						}
						role.list($(this).attr('n'));
					});
		}
	};
	role.list(1);
});