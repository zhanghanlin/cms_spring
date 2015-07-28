<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:directive.include file="/common/head.html" />
			<jsp:directive.include file="left.jsp" />
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
						<div class="col-md-3">
							<div class="box box-solid">
								<c:forEach items="${menuTree.childNode }" var="m">
								<div class="box-header with-border">
									<h3 class="box-title">${m.node.name }</h3>
									<div class="box-tools">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div>								
								<c:forEach items="${tree.childNode }" var="mc">
								<div class="box-body no-padding">
									<ul class="nav nav-pills nav-stacked">
										<li><a href="###"><i class="fa fa-envelope-o"></i>${mc.node.name }</a></li>
									</ul>
								</div>
								</c:forEach>
								</c:forEach>
							</div>
						</div>
						<div class="col-md-8"></div>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
	</body>
</html>