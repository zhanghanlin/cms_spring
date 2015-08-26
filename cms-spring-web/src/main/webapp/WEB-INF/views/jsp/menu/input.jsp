<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ol.breadcrumb {
	margin-bottom: 0px;
	padding-left: 0px;
	background-color: #fff;
}
</style>
</head>
<body>
	<form action="${action }" method="post" class="form-horizontal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title">菜单Info</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-md-12">
					<c:set var="disabled" value=""></c:set>
					<c:if test="${empty submit }">
						<c:set var="disabled" value="disabled"></c:set>
					</c:if>
					<input type="hidden" value="${UUID }" name="UUID" />
					<input type="hidden" value="${menu.id }" name="id" id="id" />
					<input type="hidden" value="${menu.code }" name="code" />
					<input type="hidden" id="parentCode" value='<c:out value="${parentCode }" default="0"></c:out>' name="parentCode" />
					<input type="hidden" id="parentId" value='<c:out value="${parentId }" default="0"></c:out>' name="parentId" />
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
											value="${menu.name }" name="name" placeholder="分类名称"
											${disabled } required />
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
											${disabled } required />
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
											value="${menu.note }" name="note" placeholder="分类说明"
											${disabled } required />
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
											value="${menu.icon }" name="icon" placeholder="分类图标"
											${disabled } required />
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
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<c:if test="${not empty submit }">
				<button type="submit" class="btn btn-info pull-left">${submit}</button>
			</c:if>
			<button type="button" class="btn btn-default pull-right"
				data-dismiss="modal">关闭</button>
		</div>
	</form>
	<script type="text/javascript" src="/js/tools/select2.full.min.js"></script>
	<script type="text/javascript" src="/js/cms/menu/input.js"></script>
</body>
</html>