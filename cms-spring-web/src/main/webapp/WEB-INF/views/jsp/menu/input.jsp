<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link href="/css/tools/select2.min.css" rel="stylesheet" type="text/css">
		<jsp:directive.include file="/common/common.html" />
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
												<div class="form-group">
													<label class="col-sm-2 control-label">分类</label>
													<div class="col-sm-3">
														<select class="form-control" id="menu1">
															<option selected="selected">Alabama</option>
															<option>Alaska</option>
															<option>California</option>
															<option>Delaware</option>
															<option>Tennessee</option>
															<option>Texas</option>
															<option>Washington</option>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="box-footer">
										<button type="submit" class="btn btn-default">Cancel</button>
										<button type="submit" class="btn btn-info pull-right">Sign in</button>
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
		<script type="text/javascript">
		$('#menu1').select2();
		</script>
	</body>
</html>