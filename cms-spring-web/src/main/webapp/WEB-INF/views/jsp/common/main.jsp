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
				<div id="content">
					<section class="content-header">
						<h1>Version.</h1>
						<ol class="breadcrumb">
							<li><a href="/"><i class="fa fa-dashboard"></i>CMS</a></li>
						</ol>
					</section>
					<section class="content">
						<!-- version  -->
						<div class="row">
							<div class="col-md-12">
								<div class="box box-solid">
									<div class="box-header with-border">
										<i class="fa fa-text-width"></i>
										<h3 class="box-title">版本信息</h3>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
									<dl>
										<dt>系统监控信息</dt>
										<dd>
											<ul>
												<li>系统监控</li>
											</ul>
										</dd>
									</dl>
									<dl>
										<dt>系统基础信息</dt>
										<dd>
											<ul>
												<li>菜单管理</li>
												<li>用户管理</li>
												<li>角色管理</li>
											</ul>
										</dd>
									</dl>
									<dl>
										<dt>日志管理</dt>
										<dd>
											<ul>
												<li>登陆日志</li>
												<li>操作日志</li>
											</ul>
										</dd>
									</dl>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- ./col -->
						</div>
					</section>
				</div>
			</div>
			<aside class="control-sidebar control-sidebar-dark" id="right-sidebar">
				<ul class="nav nav-tabs nav-justified control-sidebar-tabs"></ul>
				<div class="tab-content"></div>
			</aside>
			<jsp:directive.include file="/common/footer.html" />
		</div>
	</body>
</html>