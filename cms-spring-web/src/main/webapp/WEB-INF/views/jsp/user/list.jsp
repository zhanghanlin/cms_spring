<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:include page="../common/head.jsp" />
			<jsp:include page="../common/left.jsp" />
			<div class="content-wrapper">
				<section class="content-header">
					<h1>用户列表</h1>
					<ol class="breadcrumb">
						<li><a href="###"><i class="fa fa-dashboard"></i>CMS</a></li>
						<li><a href="###">用户管理</a></li>
						<li class="active">用户列表</li>
					</ol>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<h3 class="box-title"></h3>
									<div class="box-tools">
										<div class="input-group" style="width: 150px;">
											<input type="text" name="table_search" class="form-control input-sm pull-right" placeholder="Search" />
											<div class="input-group-btn">
												<button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
											</div>
										</div>
									</div>
								</div>
								<div class="box-body table-responsive no-padding">
									<table class="table table-hover" id="userTable">
										<thead>
											<tr>
												<th>ID</th>
												<th>用户名</th>
												<th>邮箱</th>
												<th>状态</th>
												<th>创建时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
								<div class="box-footer clearfix">
									<ul class="pagination pagination-sm no-margin pull-right"></ul>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
		<script src="/js/cms/user/user.js"></script>
	</body>
</html>