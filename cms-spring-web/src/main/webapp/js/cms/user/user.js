$(function() {
	var user = {
		list : function() {
			$('#userTable').ajaxTable(
							{
								url : '/user/list',
								pageSize : 1,
								columns : [ 'id', 'userName', 'email',
										'status', 'createdAt' ],
								opera : [
										'<button type="button" class="btn btn-default btn-xs">查看</button>',
										'<button type="button" class="btn btn-default btn-xs">删除</button>',
										'<button type="button" class="btn btn-default btn-xs">禁用</button>' ]
							});
		}
	};
	user.list();
});