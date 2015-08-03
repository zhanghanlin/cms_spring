$(function() {
	var user = {
		tableObj : $('#userTable'),
		Status : {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
		},
		tableHead : function() {
			return '<tr>\
						<td>ID</td>\
						<td>用户名</td>\
						<td>邮箱</td>\
						<td>状态</td>\
						<td>创建时间</td>\
					</tr>';
		},
		parseObj : function(o) {
			return '<tr>\
						<td>' + o.id + '</td>\
						<td>'
					+ o.userName + '</td>\
						<td>' + o.email
					+ '</td>\
						<td>' + user.userStatus(o.status)
					+ '</td>\
						<td>' + Base.date.unixToDate(o.createdAt) + '</td>\
						<td>'
					+ user.userOpera(o) + '</td>\
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
			var page = '<li><a href="###" class="'+preCss+'">&laquo;</a></li>';
			for ( var i = 1; i <= obj.totalPage; i++) {
				page += '<li><a href="###">'+i+'</a></li>';
			}
			page += '<li><a href="###" class="'+nextCss+'">&raquo;</a></li>';
			$('.box-footer ul').html(page);
		},
		list : function() {
			var param = {
				curPage : 1,
				pageSize : 2
			};
			$.getJSON('/user/list', param, function(obj) {
				var html = '';
				$.each(obj.result, function(i, o) {
					html += user.parseObj(o);
				})
				user.tableObj.html(user.tableHead() + html);
				user.parsePage(obj);
			});
		},
		userOpera : function(o) {
			return '<button type="button" class="btn btn-default btn-xs">查看</button>\
						<a role="button" href="/user/edit/'
					+ o.id
					+ '" class="btn btn-default btn-xs">编辑</a>\
						<button type="button" class="btn btn-default btn-xs">删除</button>\
						<button type="button" class="btn btn-default btn-xs">禁用</button>';
		},
		userStatus : function(status) {
			status = parseInt(status);
			if (status == user.Status.NORMAL) {
				return '<span class="label label-success">正常</span>';
			}
			if (status == user.Status.DISABLE) {
				return '<span class="label label-warning">禁用</span>';
			}
			if (status == user.Status.DELETE) {
				return '<span class="label label-danger">删除</span>';
			}
		}
	};
	user.list();
});