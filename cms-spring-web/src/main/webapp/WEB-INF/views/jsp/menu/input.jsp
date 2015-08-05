<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
from.form-horizontal i {
	font-family: "fontAwesome";
}

ol.breadcrumb {
	margin-bottom: 0px;
	padding-left: 0px;
	background-color: #fff;
}
option span.sub {
	margin-left: 10px;
}
</style>
</head>
<body class="sidebar">
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box-header">
					<button aria-hidden="true" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span></button>
				</div>
				<form action="${action }" method="post" class="form-horizontal">
					<input type="hidden" value="${UUID }" name="UUID" /> <input
						type="hidden" value="${menu.id }" name="id" id="id" /> <input
						type="hidden" value="${menu.code }" name="code" /> <input
						type="hidden" id="parentCode"
						value='<c:out value="${parentCode }" default="0"></c:out>'
						name="parentCode" />
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" id="menuDirectory">
									<label class="col-sm-2 control-label">等级</label>
									<c:choose>
										<c:when test="${fn:length(menuNames) > 0 }">
											<div class="col-sm-7">
												<ol class="breadcrumb">
													<c:forEach items="${menuNames }" var="m">
														<li>${m }</li>
													</c:forEach>
												</ol>
											</div>
										</c:when>
										<c:otherwise>
											<div class="col-sm-5">
												<select class="form-control" id="menu"><option></option></select>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">名称</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="name"
											value="${menu.name }" name="name" placeholder="分类名称" required />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">链接</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="link"
											value="${menu.link }" name="link" placeholder="分类链接,默认###"
											required />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">说明</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="note"
											value="${menu.note }" name="note" placeholder="分类说明" required />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">图标</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="icon"
											value="${menu.icon }" name="icon" placeholder="分类图标" required />
									</div>
									<label class="col-sm-1 control-label"><i></i></label>
									<div class="col-sm-1">
										<a role="button" href="/icons" target="_blank"
											class="btn btn-default">Icon</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="box-footer">
						<div class="row">
							<div class="col-sm-6"></div>
							<div class="col-sm-3">
								<button type="submit" class="btn btn-info pull-left">${submit}</button>
								<button type="button" class="btn btn-default pull-right" data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="/js/tools/select2.full.min.js"></script>
	<script type="text/javascript" src="/js/cms/menu/input.js"></script>
</body>
</html>