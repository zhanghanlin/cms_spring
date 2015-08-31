$(function() {
	var menu = {
		tableObj : $('#menuTable'),
		Status : {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
		},
		menuHtml : function(arr, data) {
			if (!arr)
				return false;
			var str = [];
			$.each(arr, function(i, o) {
				str.push('<tr data-tt-id="');
				str.push(o.id);
				str.push('" data-tt-parent-id="');
				str.push(o.parentId);
				str.push('"><td title="')
				str.push(o.note);
				str.push('">')
				for ( var i = 1; i < o.level; i++) {
					str.push('|——');
				}
				str.push(o.name);
				str.push('</td>');
				str.push('<td>');
				str.push(o.note);
				str.push('</td><td>');
				str.push(o.uniqueKey);
				str.push('</td><td><i class="fa ')
				str.push(o.icon);
				str.push('"></i></td><td>')
				str.push(menu.menuStatus(o.status));
				str.push('</td><td i="')
				str.push(o.id);
				str.push('">')
				str.push(menu.opera(o));
				str.push('</td></tr>')
				str.push(menu.menuHtml(data[o.id], data));
			});
			return str.join('');
		},
		list : function() {
			$.getJSON('/menu/all', function(data) {
				var html = menu.menuHtml(data[0], data);
				menu.tableObj.find('tbody').html(html);
				menu.tableObj.treetable({
					expandable : true
				});
			});
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
			var str = [];
			str.push('<a role="button" href="/menu/detail/');
			str.push(o.id);
			str
					.push('" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">查看</a>&nbsp;');
			var status = o.status;
			if (status != menu.Status.DELETE) {
				str.push('<a role="button" href="/menu/edit/')
				str.push(o.id);
				str
						.push('" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">编辑</a>&nbsp;<a role="button" href="/menu/toAdd/');
				str.push(o.id);
				str
						.push('" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">新增子菜单</a>&nbsp;<a role="button" class="btn btn-default btn-xs delete" status="');
				str.push(menu.Status.DELETE);
				str.push('">删除</a>&nbsp;');
				if (status == menu.Status.NORMAL) {
					str
							.push('<a role="button" class="btn btn-default btn-xs disable" status="');
					str.push(menu.Status.DISABLE);
					str.push('">禁用</a>');
				} else {
					str
							.push('<a role="button" class="btn btn-default btn-xs normal" status="');
					str.push(menu.Status.NORMAL);
					str.push('">启用</a>');
				}
			}
			return str.join('');
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
								_this.parents('tr').find('td:eq(4)').html(
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