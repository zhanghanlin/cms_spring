<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CMS Admin</title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
		<!--[if lt IE 9]>
			<script src="/js/ie/html5shiv.min.js"></script>
			<script src="/js/ie/respond.min.js"></script>
		<![endif]-->
		<link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="/css/admin/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="/css/admin/ionicons.min.css" rel="stylesheet" type="text/css">
		<link href="/css/admin/morris.css" rel="stylesheet" type="text/css">
		<link href="/css/admin/_all-skins.min.css" rel="stylesheet" type="text/css">
		<link href="/css/admin/admin.min.css" rel="stylesheet" type="text/css">
		<link href="/css/common.css" rel="stylesheet" type="text/css">
	</head>
	<!-- https://almsaeedstudio.com/themes/AdminLTE/index2.html -->
	<body class="skin-blue sidebar-mini">
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
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>Version</b> 2.2.0
				</div>
				<strong>Copyright © 2014-2015 <a
					href="###">Almsaeed Studio</a>.
				</strong> All rights reserved.
			</footer>
			<div class="control-sidebar-bg"></div>
			<script src="/js/jquery/jquery.min.js"></script>
			<script src="/js/jquery/jquery.form.js"></script>
			<script src="/js/jquery/jquery-ui.min.js"></script>
			<script src="/js/jquery/jquery.slimscroll.min.js"></script>
			<script src="/js/bootstrap/bootstrap.min.js"></script>
			<script src="/js/admin/app.js"></script>
			<script src="/js/admin/admin.js"></script>
			<script src="/js/tools/icheck.min.js"></script>
			<script src="/js/base.js"></script>
			<script src="/js/cms/common/common.js"></script>
			<script src="/js/tools/ajaxTable.js"></script>
		</div>
	</body>
</html>