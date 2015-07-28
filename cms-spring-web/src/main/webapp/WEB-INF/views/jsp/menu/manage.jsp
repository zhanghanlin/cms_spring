<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
		<link href="/css/treeview/treeview.css" rel="stylesheet" type="text/css">
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:include page="../common/head.jsp" />
			<jsp:include page="../common/left.jsp" />
			<div class="content-wrapper">
				<section class="content-header">
					<h1>Menu Tree</h1>
					<ol class="breadcrumb">
						<li><a href="###"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Menu Tree</li>
					</ol>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-md-3">
						<div id="menutree_manage">
							<div id="treecontrol">
								<a title="Collapse the entire tree below" href="#"><img
									src="/images/treeview/minus.gif" /> Collapse All</a> <a
									title="Expand the entire tree below" href="#"><img
									src="/images/treeview/plus.gif" /> Expand All</a> <a
									title="Toggle the tree below, opening closed branches, closing open branches"
									href="#">Toggle All</a>
							</div>
							<ul id="menuManageTree"></ul>
						</div>
						</div>
						<div class="col-md-8"></div>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
		<script src="/js/jquery/jquery.cookie.js"></script>
		<script src="/js/jquery/jquery.treeview.js"></script>
		<script src="/js/cms/menu/menu.js"></script>
	</body>
</html>