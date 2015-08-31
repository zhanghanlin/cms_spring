$(function() {
	var menuInput = {
		initSelect : function() {
			$.getJSON('/menu/tree', function(data) {
				var html = '<option value="0">顶级分类</option>'
						+ menuInput.selectHtml(data[0], data);
				$('#select_menu').html(html).selectpicker();
				$('#select_menu').selectpicker('val', $('#parentId').val());
			});
		},
		selectHtml : function(arr, data) {
			var html = '';
			$.each(arr, function(i, o) {
				var sp = '';
				for ( var i = 1; i < o.level; i++) {
					sp += '&nbsp;&nbsp;&nbsp;&nbsp;';
				}
				html += '<option value="' + o.id + '">' + sp + o.name
						+ '</option>';
				if (data[o.id]) {
					html += menuInput.selectHtml(data[o.id], data);
				}
			});
			return html;
		},
		iconBlur : function() {
			if ($('#icon').val()) {
				$('#icon').parent().next().find('i').addClass('fa');
				$('#icon').parent().next().find('i').addClass($('#icon').val());
			}
			$('#icon').blur(function() {
				$(this).parent().next().find('i').removeClass();
				$('#icon').parent().next().find('i').addClass('fa');
				$(this).parent().next().find('i').addClass($(this).val());
			})
		},
		ajaxForm : function() {
			var options = {
				type : "POST",
				dataType : "json",
				success : function(json) {// 表单提交成功回调函数
					$('#modal').modal('hide');
				},
				error : function(err) {
					alert("表单提交异常！" + err.msg);
				}
			};
			$(".form-horizontal").ajaxForm(options);
		}
	};
	menuInput.ajaxForm();
	menuInput.iconBlur();
	menuInput.initSelect();
});