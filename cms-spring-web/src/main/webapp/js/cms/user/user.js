$(function() {
	var user = {
		list : function() {
			$('#userTable')
					.ajaxTable(
							{
								url : '/user/list',
								pageSize : 15,
								columns : [ 'userName', 'email', 'roles',
										'status', 'createdAt' ],
								opera : [
										'<a role="button" href="/user/detail/{id}" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal">查看</a>',
										'<a role="button" class="btn btn-default btn-xs">删除</a>',
										'<a role="button" class="btn btn-default btn-xs">禁用</a>',
										'<a role="button" user-id="{id}" class="btn btn-default btn-xs role2user">分配角色</a>' ]
							});
		},
		closeModal : function() {
			$('.modal').on('hidden.bs.modal', function() {
				$(this).removeData('bs.modal');
			});
		},
		roles : function() {
			$('#role_modal').on('show.bs.modal', function() {
				$.ajax({
					url : '/user/roles',
					data : {
						userId : $('#saveRole2User').attr('user-id')
					},
					dataType : 'JSON',
					success : function(arr) {
						var str = [];
						$.each(arr, function(i, o) {
							str.push('<tr><td><input class="role_cbx" ');
							if (o.check) {
								str.push('checked="checked"');
							}
							str.push(' value="')
							str.push(o.id);
							str.push('" type="checkbox"/></td>');
							str.push('<td>');
							str.push(o.name)
							str.push('</td>');
							str.push('<td>');
							str.push(o.note)
							str.push('</td></tr>');
						});
						$('#roleTable tbody').html(str.join(''));
					}
				});
			});
		},
		role2User : function(userId) {
			var roleIds = [];
			$(".role_cbx:checked").each(function() {
				roleIds.push($(this).val());
			})
			$.ajax({
				url : '/user/role2user',
				type : 'POST',
				data : {
					id : userId,
					roleIds : roleIds.toString()
				},
				processData : true,
				dataType : 'JSON',
				success : function(data) {
					$('#role_modal').modal('hide');
				},
				error : function(err) {
					alert("表单提交异常！" + err.msg);
				}
			});
		}
	};
	user.roles();
	user.list();
	user.closeModal();

	$('#userTable').delegate('a.role2user', 'click', function() {
		$('#saveRole2User').attr('user-id', $(this).attr('user-id'));
		$('#role_modal').modal('show');
	});
	$('#saveRole2User').click(function() {
		user.role2User($(this).attr('user-id'));
	})
});