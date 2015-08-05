$(function() {
	var menu = {
		tableObj : $('#menuTable'),
		Status : {
			NORMAL : 0,
			DISABLE : 1,
			DELETE : 2
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
			var str = [];
			str.push('<tr data-tt-id="');
			str.push(dataId);
			str.push('" data-tt-parent-id="');
			str.push(parentId);
			str.push('"><td title="')
			str.push(o.note);
			str.push('">')
			for ( var i = 1; i < (o.code.length / 3).toFixed(0); i++) {
				str.push('|——');
			}
			str.push(o.name);
			str.push('</td><td><i class="fa ')
			str.push(o.icon);
			str.push('"></i></td><td>')
			str.push(menu.menuStatus(o.status));
			str.push('</td><td i="')
			str.push(o.id);
			str.push('">')
			str.push(menu.opera(o));
			str.push('</td></tr>')
			return str.join('');
		},
		list : function() {
			$.getJSON('/menu/_all', function(obj) {
				if (obj.hasChild) {
					var html = '';
					$.each(obj.childNode, function(i, o) {
						html += menu.parseObj(o.node);
						html += menu.child(o);
					});
				}

				menu.tableObj.find('tbody').html(html);
				menu.tableObj.treetable({
					expandable : true
				});
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
			var str = [];
			str.push('<a role="button" href="/menu/get/');
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