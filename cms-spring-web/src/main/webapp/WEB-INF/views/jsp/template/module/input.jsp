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
					<form class="form-horizontal" method="post" id="inputPage">
						<div class="form-group">
							<label class="col-sm-2 control-label" style="text-align:left;" for="name">页面名称</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="name" name="name" placeholder="页面名称" value="${t.name }"/>
							</div>
							<div class="col-sm-2">
								<select class="form-control" id="moduleType" name="moduleType">
									<option value="0" <c:if test="${t.type == 0 }">selected</c:if>>自动类型</option>									
									<option value="1" <c:if test="${t.type == 1 }">selected</c:if>>手动类型</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="text-align:left;" for="path">碎片刷新类型</label>
							<div class="col-sm-3">
								<select class="form-control">
									<option>Type A</option>
									<option>Type B</option>
								</select>
							</div>
							<div class="col-sm-6">
								<button type="button" id="cancel" class="btn btn-primary" style="float: right;">返回</button>
							</div>
							<div class="col-sm-1">
								<button type="button" id="submit" class="btn btn-primary" style="float: right;">确定</button>
							</div>
						</div>
						<input type="hidden" id="data" name="data"/>
						<input type="hidden" id="id" value="${t.id }"/>
						<textarea rows="20" class="form-control" id="moduelData" style="resize:none"></textarea>
						<div id="controlEditor"></div>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="/common/footer.html" %>
		<script src="/js/template/module/input.js"></script>
	</body>
</html>