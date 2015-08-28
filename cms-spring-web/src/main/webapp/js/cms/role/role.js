$(function() {
	var role = {
		list : function() {
			$('#roleTable')
					.ajaxTable(
							{
								url : '/role/list',
								pageSize : 15,
								columns : [ 'id', 'name', 'note', 'uniqueKey',
										'status', 'createdAt' ],
								opera : [
										'<a role="button" href="/role/detail/{id}" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">查看</a>',
										'<a role="button" href="/role/edit/{id}" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">编辑</a>',
										'<a role="button" class="btn btn-default btn-xs">删除</a>',
										'<a role="button" class="btn btn-default btn-xs">禁用</a>',
										'<a role="button" class="btn btn-default btn-xs menu2role" role-id="{id}">分配权限</a>' ]
							});
		},
		closeModal : function() {
			$('#modal').on('hidden.bs.modal', function() {
				$(this).removeData('bs.modal');
			});
		},
		menus : function() {
			$('#menu_modal').on('show.bs.modal', function() {
				$.getJSON('/role/menus', {
					roleId : $('#saveMenu2Role').attr('role-id')
				}, function(data) {
					var setting = {
						check : {
							enable : true,
							chkboxType : {
								"Y" : "s",
								"N" : "ps"
							}
						},
						data : {
							simpleData : {
								enable : true
							}
						}
					};
					$.fn.zTree.init($("#ztree"), setting, data);
				});
			});
		},
		ajaxForm : function() {
			var options = {
				type : "POST",
				dataType : "json",
				success : function(json) {// 表单提交成功回调函数
					$('#modal').modal('hide');
					$('.sidebar-menu li.active').not('.treeview').find('a')
							.trigger('click');
				},
				error : function(err) {
					alert("表单提交异常！" + err.msg);
				}
			};
			$(".form-horizontal").ajaxForm(options);
		},
		menu2Role : function(roleId) {
			var menuIds = [];
			var treeObj = $.fn.zTree.getZTreeObj("ztree"), nodes = treeObj
					.getCheckedNodes(true);
			for ( var i = 0; i < nodes.length; i++) {
				menuIds.push(nodes[i].id);
			}
			$.ajax({
				url : '/role/menu2role',
				type : 'POST',
				data : {
					id : roleId,
					menuIds : menuIds.toString()
				},
				processData : true,
				dataType : 'JSON',
				success : function(data) {
					$('#menu_modal').modal('hide');
				},
				error : function(err) {
					alert("表单提交异常！" + err.msg);
				}
			});
		}
	};
	role.list();
	role.closeModal();
	role.menus();
	$('#roleTable').delegate('a.menu2role', 'click', function() {
		$('#saveMenu2Role').attr('role-id', $(this).attr('role-id'));
		$('#menu_modal').modal('show');
	});
	$('#saveMenu2Role').click(function() {
		role.menu2Role($(this).attr('role-id'));
	})
});