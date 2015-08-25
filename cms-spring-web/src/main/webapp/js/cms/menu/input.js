$(function() {
	var menuInput = {
		initSelect : function() {
			$.getJSON('/menu/tree', function(obj) {
				if ($('#menu').length == 0) {
					return false;
				}
				menuInput.selectHtml(obj);
			});
		},
		selectHtml : function(obj) {
			var html = '<option value="0">CMS</option>';
			$.each(obj.children, function(i, o) {
				if (o.node) {
					if (o.hasChild) {
						html += '<optgroup label=' + o.node.name
								+ '><option value="' + o.code + '">【'
								+ o.node.name + '】</option>';
						html += menuInput.subSelectHtml(o);
						html += '</optgroup>';
					} else {
						html += '<option value="' + o.code + '">' + o.node.name
								+ '</option>';
					}
				}
			});
			$('#menu').html(html).select2({
				placeholder : "请选择",
				width : '100%'
			});
		},
		subSelectHtml : function(obj) {
			var html = '';
			if (obj.hasChild) {
				$.each(obj.children, function(i, o) {
					if (o.hasChild) {
						html += '<optgroup label=' + o.node.name
								+ '><option value="' + o.code + '">【'
								+ o.node.name + '】</option>';
						html += menuInput.subSelectHtml(o);
						html += '</optgroup>';
					} else {
						html += '<option value="' + o.code + '">' + o.node.name
								+ '</option>';
					}
				});
			}
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
					alert("表单操作完成！操作结果：" + json.msg);
					$('#modal').modal('toggle');
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