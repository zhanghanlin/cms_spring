<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<jsp:directive.include file="/common/common.html" />
	<link href="/css/admin/blue.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.jcaptcha {
			height: 38px;
		}
		.jcaptcha-img {
			cursor: pointer;
			border: 1px solid #ccc;
		}
		.jcaptcha-refresh {
			cursor: pointer;
			padding-left:10px;
		}
	</style>
</head>
<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="javascript:;"><b>Admin&nbsp;&nbsp;</b>CMS</a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">登陆你的帐号<br/><c:out value="${error }" default=""></c:out></p>
			<form action="/login" method="POST">
				<input type="hidden" value="${UUID }" name="uuid"/>
				<div class="form-group has-feedback">
					<input type="email" class="form-control" name="username" placeholder="Email" required/>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" placeholder="Password" required/>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<c:if test="${jcaptchaEbabled }">
				<div class="form-group">
					<div class="row">
						<div class="col-xs-6">
							<input type="text" class="form-control jcaptcha" name="jcaptchaCode"
								placeholder="请输入验证码" required />
						</div>
						<div class="col-xs-6">
							<img class="jcaptcha jcaptcha-img jcaptcha-refresh" src="/jcaptcha.jpg" title="点击更换验证码">
							<i class="fa fa-refresh jcaptcha-refresh" title="点击更换验证码"></i>
						</div>
					</div>
				</div>
				</c:if>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<input type="hidden" name="rememberMe" value="true" />
							<label><input type="checkbox"/>&nbsp;&nbsp;记住我</label>
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
			
			$('.jcaptcha-refresh').click(function(){
				$('.jcaptcha-img').attr('src','/jcaptcha.jpg?'+(new Date()).getTime());
			});
		});
	</script>
</body>
</html>