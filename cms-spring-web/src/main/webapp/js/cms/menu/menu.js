$(function() {
	var tree = {
		tableObj : $('#menuTable'),
		status : {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
		},
		tableHead : function() {
			return '<tr>\
						<td>Name</td>\
						<td>Icon</td>\
						<td>Status</td>\
						<td>Operation</td>\
					</tr>';
		},
		parseObj : function(o) {
			var code = o.code;
			var dataId = '';
			while (code.length / 3 >= 1) {
				dataId += '-' + parseInt(code.substr(0, 3));
				code = code.substr(3);
			}
			dataId = dataId.substr(1);
			var parentId = dataId.substr(0, dataId.lastIndexOf('-'));
			return '<tr data-tt-id="' + dataId + '" data-tt-parent-id="'
					+ parentId + '">\
						<td title="'
					+ o.note + '">' + o.name
					+ '</td>\
						<td><i class="fa ' + o.icon
					+ '"></i></td>\
						<td>' + tree.menuStatus(o.status)
					+ '</td>\
						<td>' + tree.menuOpera(o)
					+ '</td>\
					</tr>';
		},
		list : function() {
			$.getJSON('/menu/_all', function(obj) {
				if (obj.hasChild) {
					var html = '';
					$.each(obj.childNode, function(i, o) {
						html += tree.parseObj(o.node);
						html += tree.child(o);
					});
					tree.tableObj.html(tree.tableHead() + html);
					tree.tableObj.treetable({
						expandable : true,
						
					});
				}
			});
		},
		child : function(obj) {
			var html = '';
			if (obj.hasChild) {
				$.each(obj.childNode, function(j, o) {
					html += tree.parseObj(o.node);
					html += tree.child(o);
				});
			}
			return html;
		},
		menuTools : function() {
			$('.box-tools ul li a').click(function() {
				if ($(this).hasClass('expandAll')) {
					tree.tableObj.treetable('expandAll');
					return false;
				}
				if ($(this).hasClass('collapseAll')) {
					tree.tableObj.treetable('collapseAll');
					return false;
				}
			})
		},
		menuOpera : function(o) {
			return '<button type="button" class="btn btn-default btn-xs">查看</button>\
					<a role="button" href="/menu/edit/'+o.id+'" class="btn btn-default btn-xs">编辑</button>\
					<a role="button" href="/menu/toAdd/'+o.code+'" class="btn btn-default btn-xs">新增子菜单</a>\
					<button type="button" class="btn btn-default btn-xs">删除</button>\
					<button type="button" class="btn btn-default btn-xs">禁用</button>';
		},
		menuStatus : function(status) {
			status = parseInt(status);
			if (status == tree.status.NORMAL) {
				return '<span class="label label-success">正常</span>';
			}
			if (status == tree.status.DISABLE) {
				return '<span class="label label-warning">禁用</span>';
			}
			if (status == tree.status.DELETE) {
				return '<span class="label label-danger">删除</span>';
			}
		},
		drag : function() {
			$("#menuTable tr").draggable({
				revert : 'invalid',
				helper : 'clone', // original 拖拽源控件,clone托转控制体
				scroll : true,
				cursor : "move", // 光标样式
				delay : 500, // 按下鼠标，等待500毫秒，启动拖动
				start : function(event, ui) {
					console.info("start" + this);
				},
				drag : function(event, ui) {
				},
				stop : function(event, ui) {
					console.info("stop" + this);
				}
			});
		}
	};
	tree.list();
	tree.menuTools();
});