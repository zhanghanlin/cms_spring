<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:include page="../WEB-INF/views/jsp/common/head.jsp" />
			<jsp:include page="../WEB-INF/views/jsp/common/left.jsp" />
			<div class="content-wrapper">
				<section class="content-header">
					<h1>404&nbsp;<small>Error Page</small></h1>
				</section>
				<section class="content">
					<div class="error-page">
						<h2 class="headline text-yellow">404</h2>
						<div class="error-content">
							<h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>
							<p>
								点击返回<a href="/">首页</a>!
							</p>
						</div>
					</div>
				</section>
			</div>
			<aside class="control-sidebar control-sidebar-dark" id="right-sidebar">
				<ul class="nav nav-tabs nav-justified control-sidebar-tabs"></ul>
				<div class="tab-content"></div>
			</aside>
			<jsp:directive.include file="/common/footer.html" />
		</div>
	</body>
</html>