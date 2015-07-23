<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CMS Admin</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!--[if lt IE 9]>
        <script src="/js/ie/html5shiv.min.js"></script>
        <script src="/js/ie/respond.min.js"></script>
        <![endif]-->
		<link href="/css/admin/admin.min.css" rel="stylesheet" type="text/css">
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@ include file="/common/head.html" %>
			<%@ include file="/common/left.html" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>CMS <small>index</small></h1>
				</section>
				<section class="content">
					<div class="row">
						<section class="col-lg-12 connectedSortable">
							<%@ include file="/common/tools/Calendar.html" %>
							<%@ include file="/common/tools/Visitors.html" %>
						</section>
					</div>
				</section>
			</div>
			<%@ include file="/common/right.html" %>
			<%@ include file="/common/footer.html" %>
			<div class="control-sidebar-bg"></div>
		</div>
	</body>
</html>