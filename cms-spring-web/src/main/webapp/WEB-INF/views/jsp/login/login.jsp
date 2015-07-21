<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>登陆CMS</title>
</head>
<body>
	<div class="container">
		<form id="loginForm" class="form-signin" action="/api/login" method="post">
			<h4 class="form-signin-heading">&nbsp;</h4>
			<label for="userName" class="sr-only">用户名</label>
				<input type="text" id="userName" name="userName" class="form-control" placeholder="UserName" required autofocus />
				<label for="password" class="sr-only">密码</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required />
			<div class="checkbox">
				<label>
					<input type="checkbox" value="remember-me" />记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" id="submit" type="submit">登陆</button>
		</form>
	</div>
	<%@ include file="/common/footer.html" %>
	<script src="/js/login/login.js"></script>
</body>
</html>