<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
		<style type="text/css">
		table.treetable tr.collapsed span.indenter a:BEFORE {
			content: "展开"
		}
		table.treetable tr.expanded span.indenter a:BEFORE {
			content: "闭合"
		}
		</style>
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
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<h3 class="box-title">Menu</h3>
								</div>
								<div class="box-body table-responsive no-padding">
									<table class="table table-hover" id="menuTable"></table>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
		<script src="/js/jquery/jquery.treetable.js"></script>
		<script src="/js/cms/menu/menu.js"></script>
	</body>
</html>