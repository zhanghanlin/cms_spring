$(function() {
	var role = {
		list : function() {
			$('#roleTable')
					.ajaxTable(
							{
								url : '/role/list',
								pageSize : 1,
								columns : [ 'id', 'name', 'note', 'status',
										'createdAt' ],
								opera : [
										'<a role="button" href="/role/get/{id}" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">查看</a>',
										'<a role="button" href="/role/edit/{id}" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">编辑</a>',
										'<a role="button" class="btn btn-default btn-xs">删除</a>',
										'<a role="button" class="btn btn-default btn-xs">禁用</a>',
										'<a role="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#role_modal">分配权限</a>' ]
							});
		},
		closeModal : function() {
			$('#modal').on('hidden.bs.modal', function() {
				$(this).removeData('bs.modal');
			});
		},
		menuList : function() {
			$('#role_modal').on('show.bs.modal', function() {
				$.getJSON('/menu/_all', function(data) {
					console.info(data);
					var json = '';
//					$('#treeview').treeview({
//						data : json
//					});
				})
			});
		},
		parseJSON : function(data) {
			var json = '[{}]';
			$.each(data,function(i,o){
			});
		}
	};
	role.list();
	role.closeModal();
	role.menuList();
});