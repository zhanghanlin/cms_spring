<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
	</head>
	<!-- https://almsaeedstudio.com/themes/AdminLTE/index2.html -->
	<body class="skin-blue sidebar-mini fixed">
		<div class="wrapper">
			<jsp:directive.include file="head.jsp" />
			<jsp:directive.include file="left.jsp" />
			<div class="content-wrapper">
				<div id="content"></div>
			</div>
			<aside class="control-sidebar control-sidebar-dark" id="right-sidebar">
				<ul class="nav nav-tabs nav-justified control-sidebar-tabs"></ul>
				<div class="tab-content"></div>
			</aside>
			<jsp:directive.include file="/common/footer.html" />
		</div>
	</body>
</html>