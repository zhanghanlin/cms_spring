<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Bootstrap</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		    <![endif]-->
	</head>
	<body>
		<%@ include file="/common/head.html" %>
		<div class="container-fluid">
			<div class="row">
				<%@ include file="/common/left.html" %>
				<input type="hidden" id="_type" value="template_page_list"/>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<a class="btn btn-default" href="toAdd" role="button">新增页面</a>
					</div>
					<hr size="1" color="#999999">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>页面名称</th>
								<th>文件名称</th>
								<th>访问路径</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
		<%@ include file="/common/footer.html" %>
		<script src="/js/template/page/list.js"></script>
	</body>
</html>