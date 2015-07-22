<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@ include file="/common/head.html" %>
			<%@ include file="/common/left.html" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>碎片列表</h1>
					<ol class="breadcrumb">
						<li><a href="###"><i class="fa fa-dashboard"></i>首页</a></li>
						<li><a href="###">模板管理</a></li>
						<li class="active">碎片管理</li>
					</ol>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header"><h3 class="box-title">碎片列表</h3></div>
								<div class="box-body">
									<table id="list" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>#</th>
												<th>碎片名称</th>
												<th>文件名称</th>
												<th>碎片类型</th>
												<th>创建时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody></tbody>
										<tfoot>
											<tr>
												<th>#</th>
												<th>碎片名称</th>
												<th>文件名称</th>
												<th>碎片类型</th>
												<th>创建时间</th>
												<th>操作</th>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<%@ include file="/common/footer.html" %>
			<%@ include file="/common/right.html" %>
			<div class="control-sidebar-bg"></div>
		</div>
		<script type="text/javascript" src="/js/jquery/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="/js/bootstrap/dataTables.bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function () {
			Base.include.css("/css/bootstrap/dataTables.bootstrap.css");
			$('#list').DataTable({
				"ajaxSource" : '/template/module/api/list',
				"columns" : [ {
					data : "id"
				}, {
					data : "name"
				}, {
					data : "fileName"
				}, {
					data : "type"
				}, {
					data : "createdAt"
				}, {
					data : "status"
				} ],
				"autoWidth" : false,
			});
		});
		</script>
	</body>
</html>