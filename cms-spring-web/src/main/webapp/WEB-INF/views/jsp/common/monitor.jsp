<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
	</head>
	<!-- https://almsaeedstudio.com/themes/AdminLTE/index2.html -->
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:directive.include file="head.jsp" />
			<jsp:directive.include file="left.jsp" />
			<div class="content-wrapper">
				<section class="content-header">
					<h1>CMS <small>index</small></h1>
				</section>
				<section class="content">
					<div class="row">
						<section class="col-xs-12">
							<div class="box box-primary">
								<div class="box-header with-border">
									<i class="fa fa-bar-chart-o"></i>
									<h3 class="box-title">实时监控</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-default btn-sm" data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
										<button class="btn btn-default btn-sm" data-widget="remove">
											<i class="fa fa-times"></i>
										</button>
									</div>
								</div>
								<div class="box-body">
									<div class="row">
										<div class="col-md-4 col-sm-6 col-xs-6 text-center">
											<div id="cpu" class="cpu" style="height: 200px;"></div>
										</div>
										<div class="col-md-4 col-sm-6 col-xs-6 text-center">
											<div id="jvm" class="jvm" style="height: 200px;"></div>
										</div>
										<div class="col-md-4 col-sm-6 col-xs-6 text-center">
											<div id="mem" class="mem" style="height: 200px;"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="box box-primary">
								<div class="box-header with-border">
									<i class="fa fa-bar-chart-o"></i>
									<h3 class="box-title">实时图表</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-default btn-sm" data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
										<button class="btn btn-default btn-sm" data-widget="remove">
											<i class="fa fa-times"></i>
										</button>
									</div>
								</div>
								<div class="box-body">
									<div id="line" class="line" style="height: 300px;"></div>
								</div>
							</div>
						</section>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
		<script src="/js/tools/echarts-all.js"></script>
		<script src="/js/cms/common/monitor.js"></script>
	</body>
</html>