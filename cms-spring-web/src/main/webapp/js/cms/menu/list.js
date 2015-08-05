$(function() {
	var menu = {
		tableObj : $('#menuTable'),
		Status : {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
		},
		tableHead : function() {
			return '<tr>\
						<td>菜单名称</td>\
						<td>菜单图标</td>\
						<td>菜单状态</td>\
						<td>操作</td>\
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
						<td title="' + o.note + '">'
					+ o.name + '</td>\
						<td><i class="fa ' + o.icon
					+ '"></i></td>\
						<td>' + menu.menuStatus(o.status)
					+ '</td>\
						<td i="' + o.id + '">' + menu.opera(o)
					+ '</td>\
					</tr>';
		},
		list : function() {
			$.getJSON('/menu/_all', function(obj) {
				if (obj.hasChild) {
					var html = '';
					$.each(obj.childNode, function(i, o) {
						html += menu.parseObj(o.node);
						html += menu.child(o);
					});
					menu.tableObj.html(menu.tableHead() + html);
					menu.tableObj.treetable({
						expandable : true
					});
				}
			});
		},
		child : function(obj) {
			var html = '';
			if (obj.hasChild) {
				$.each(obj.childNode, function(j, o) {
					html += menu.parseObj(o.node);
					html += menu.child(o);
				});
			}
			return html;
		},
		tools : function() {
			$('.box-tools ul li a').click(function() {
				if ($(this).hasClass('expandAll')) {
					menu.tableObj.treetable('expandAll');
					return false;
				}
				if ($(this).hasClass('collapseAll')) {
					menu.tableObj.treetable('collapseAll');
					return false;
				}
			})
		},
		opera : function(o) {
			var html = '<a role="button" href="/menu/edit/'
					+ o.id
					+ '" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">查看</a>&nbsp;';
			var status = o.status;
			if (status != menu.Status.DELETE) {
				html += '<a role="button" href="/menu/edit/'
						+ o.id
						+ '" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">编辑</a>\
						<a role="button" href="/menu/toAdd/'
						+ o.id
						+ '" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">新增子菜单</a>\
						<a role="button" class="btn btn-default btn-xs delete" status="'
						+ menu.Status.DELETE + '">删除</a>&nbsp;';
				if (status == menu.Status.NORMAL) {
					html += '<a role="button" class="btn btn-default btn-xs disable" status="'
							+ menu.Status.DISABLE + '">禁用</a>';
				} else {
					html += '<a role="button" class="btn btn-default btn-xs normal" status="'
							+ menu.Status.NORMAL + '">启用</a>';
				}
			}
			return html;
		},
		menuStatus : function(status) {
			status = parseInt(status);
			if (status == menu.Status.NORMAL) {
				return '<span class="label label-success">正常</span>';
			}
			if (status == menu.Status.DISABLE) {
				return '<span class="label label-warning">禁用</span>';
			}
			if (status == menu.Status.DELETE) {
				return '<span class="label label-danger">删除</span>';
			}
		},
		updateStatus : function() {
			menu.tableObj.delegate('a.delete,a.disable,a.normal', 'click',
					function() {
						var _this = $(this);
						var id = _this.parent().attr('i');
						var status = _this.attr('status');
						var param = {
							id : id,
							status : status
						};
						$.post('/menu/update/status', param, function(obj) {
							if (obj.code == 200) {
								_this.parents('tr').find('td:eq(2)').html(
										menu.menuStatus(obj.data.status));
								_this.parent().html(menu.opera(obj.data));
							}
						}, 'JSON');
					});
		},
		closeModal : function() {
			$('#modal').on('hidden.bs.modal', function() {
				$(this).removeData('bs.modal');
			})
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
	menu.list();
	menu.tools();
	menu.updateStatus();
	menu.closeModal();
});