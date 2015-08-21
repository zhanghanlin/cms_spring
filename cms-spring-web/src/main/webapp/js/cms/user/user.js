$(function() {
	var user = {
		list : function() {
			$('#userTable')
					.ajaxTable(
							{
								url : '/user/list',
								pageSize : 1,
								columns : [ 'id', 'userName', 'email',
										'status', 'createdAt' ],
								opera : [
										'<a role="button" href="/user/get/{id}" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">查看</a>',
										'<a role="button" class="btn btn-default btn-xs">删除</a>',
										'<a role="button" class="btn btn-default btn-xs">禁用</a>' ]
							});
		},
		closeModal : function() {
			$('#modal').on('hidden.bs.modal', function() {
				$(this).removeData('bs.modal');
			});
		}
	};
	user.list();
	user.closeModal();
});