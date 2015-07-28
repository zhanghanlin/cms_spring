$(function() {
	var options = {
		success : loginSuccess, // 处理完成
		resetForm : false,
		dataType : 'json'
	};
	$('#loginForm').ajaxForm(options);
	function loginSuccess(data) {
		console.info(data);
		if (data.code == 200) {
			window.location.href = '/index.jsp';
		}
	}
});