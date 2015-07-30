<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<jsp:directive.include file="/common/common.html" />
	<link href="/css/admin/blue.css" rel="stylesheet" type="text/css">
</head>
<body class="register-page">
	<div class="register-box">
		<div class="register-logo">
			<a href="###"><b>Admin&nbsp;&nbsp;</b>CMS</a>
		</div>
		<div class="register-box-body">
			<p class="login-box-msg">新会员注册${msg }</p>
			<form action="/doRegister" method="POST" id="registerForm">
				<input type="hidden" value="${UUID }" name="UUID"/>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="userName" placeholder="userName" pattern="[A-Za-z][\w]{6,}" title="以字母开头,长度6位以上" min="6" required autofocus/>
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="email" class="form-control" name="email" placeholder="Email" required/>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" placeholder="Password" required/>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="rePassword" placeholder="Retype password" required/>
					<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input type="checkbox" />&nbsp;&nbsp;我同意这项条款</label>
						</div>
					</div>
					<div class="col-xs-4"><button type="submit" id="regSubmit" class="btn btn-primary btn-block btn-flat">Register</button></div>
				</div>
			</form>
			<div class="social-auth-links text-center"></div>
			<a href="/login" class="text-center">已有帐号</a>
		</div>
	</div>
	<script src="/js/jquery/jquery.min.js"></script>
	<script src="/js/bootstrap/bootstrap.min.js"></script>
	<script src="/js/common/register.js"></script>
</body>
</html>