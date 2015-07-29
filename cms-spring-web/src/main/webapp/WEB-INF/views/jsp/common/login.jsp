<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<jsp:directive.include file="/common/common.html" />
	<link href="/css/admin/blue.css" rel="stylesheet" type="text/css">
</head>
<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="###"><b>Admin&nbsp;&nbsp;</b>CMS</a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">登陆你的帐号</p>
			<form action="/doLogin" method="POST">
				<input type="hidden" value="${UUID }" name="UUID"/>
				<div class="form-group has-feedback">
					<input type="email" class="form-control" name="userName" placeholder="Email" required/>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" placeholder="Password" required/>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input type="checkbox" />&nbsp;&nbsp;记住我</label>
						</div>
					</div>
					<div class="col-xs-4"><button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button></div>
				</div>
			</form>
			<div class="social-auth-links text-center"></div>
			<a href="/register" class="text-center">没有帐号?</a>
		</div>
	</div>
	<script src="/js/jquery/jquery.min.js"></script>
	<script src="/js/bootstrap/bootstrap.min.js"></script>
	<script src="/js/tools/icheck.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('input').iCheck({
				checkboxClass:'icheckbox_square-blue',
				radioClass: 'iradio_square-blue',
				increaseArea: '20%' // optional
			});
		});
	</script>
</body>
</html>