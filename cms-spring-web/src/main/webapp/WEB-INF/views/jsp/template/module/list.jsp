<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Bootstrap</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
				<input type="hidden" id="_type" value="template_module_list"/>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<a class="btn btn-default" href="toAdd" role="button">新增碎片</a>
					</div>
					<hr size="1" color="#999999">
					<div class="row">
						<table class="table table-hover">
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
							<tbody>
	                        <c:forEach items="${list}" var="t">
								<tr>
									<th scope="row">${t.id }</th>
									<td>${t.name }</td>
									<td>${t.fileName }</td>
									<td>${t.type }</td>
									<td>${t.createdAt }</td>
									<td>
										<a class="btn btn-default btn-xs" href="edit/${t.id }" role="button">修改</a>
										<a class="btn btn-default btn-xs delete" href="###" tid="${t.id }" role="button">删除</a>
									</td>
								</tr>
	                        </c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/common/footer.html" %>
		<script src="/js/template/module/list.js"></script>
	</body>
</html>