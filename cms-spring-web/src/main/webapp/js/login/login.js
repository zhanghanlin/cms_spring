$(function() {
	Base.include.css("/css/login/login.css");
	var options = {
		success : loginSuccess, // 处理完成
		resetForm : false,
		dataType : 'json'
	};
	$('#loginForm').ajaxForm(options);
	
	function loginSuccess(response, status) {
		window.location.href='/index.jsp';
	}
});
