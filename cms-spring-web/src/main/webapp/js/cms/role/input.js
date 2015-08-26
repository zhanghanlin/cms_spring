$(function() {
	var roleInput = {
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
	roleInput.ajaxForm();
});