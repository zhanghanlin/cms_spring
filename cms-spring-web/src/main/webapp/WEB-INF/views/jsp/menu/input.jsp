<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link href="/css/tools/select2.min.css" rel="stylesheet" type="text/css">
		<jsp:directive.include file="/common/common.html" />
		<style type="text/css">
		from.form-horizontal i {
			font-family: "fontAwesome";
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
						<li><a href="###"><i class="fa fa-dashboard"></i>CMS</a></li>
						<li><a href="###">系统设置</a></li>
						<li><a href="###">菜单管理</a></li>
						<li class="active">新增菜单</li>
					</ol>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-md-12">
							<div class="box box-info">
								<div class="box-header with-border">
									<h3 class="box-title">新增</h3>
								</div>
								<form action="" class="form-horizontal">
									<div class="box-body">
										<div class="row">
											<div class="col-md-10">
												<div class="form-group" id="menuDirectory">
													<label class="col-sm-2 control-label">等级</label>
													<div class="col-sm-2">
														<select class="form-control" id="menuLevel"></select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-10">
												<div class="form-group">
													<label class="col-sm-2 control-label">名称</label>
													<div class="col-sm-3">
														<input type="text" class="form-control" id="name" name="name" placeholder="分类名称">
													</div>
												</div>
											</div>
										</div><div class="row">
											<div class="col-md-10">
												<div class="form-group">
													<label class="col-sm-2 control-label">链接</label>
													<div class="col-sm-5">
														<input type="text" class="form-control" id="note" name="note" placeholder="分类链接,默认###">
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-10">
												<div class="form-group">
													<label class="col-sm-2 control-label">说明</label>
													<div class="col-sm-5">
														<input type="text" class="form-control" id="note" name="note" placeholder="分类说明">
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-10">
												<div class="form-group">
													<label class="col-sm-2 control-label">图标</label>
													<div class="col-sm-2">
														<input type="text" class="form-control" id="icon" name="icon" placeholder="分类图标">
													</div>
													<label class="col-sm-1 control-label"><i></i></label>
													<div class="col-sm-1">
														<a role="button" href="/icons" target="_blank" class="btn btn-default">Icon</a>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="box-footer">
										<div class="row">
											<div class="col-sm-3">
												<button type="submit" class="btn btn-info pull-right">Sign in</button>
											</div>
											<div class="col-sm-3">
												<button type="reset" class="btn btn-default">Cancel</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
		<script type="text/javascript" src="/js/tools/select2.full.min.js"></script>
		<script type="text/javascript" src="/js/cms/menu/input.js"></script>
	</body>
</html>