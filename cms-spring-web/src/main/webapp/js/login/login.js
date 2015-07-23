$(function() {
	var options = {
		success : loginSuccess, // 处理完成
		resetForm : false,
		dataType : 'json'
	};
	$('#loginForm').ajaxForm(options);	
	function loginSuccess(data) {
		if (data.code) {
			
		}
	}
});
