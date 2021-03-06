<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<link href="/css/ztree/ztree.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	.selectMenuCont {
		display: none;
		position: absolute;
		background-color: #CFB;
		z-index: 999;
		width: 88%;
		border: 1px solid #ccc;
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
			<h4 class="modal-title">菜单信息</h4>
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
					<input type="hidden" id="parentId" value='<c:out value="${parentId }" default="0"></c:out>'/>
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" id="menuDirectory">
									<label class="col-sm-2 control-label">父级分类</label>
									<div class="col-sm-5">
										<input id="selectMenu" type="text" class="form-control" readonly="readonly" ${disabled }/>
										<div id="selectMenuCont" class="selectMenuCont">
											<ul id="menuTree" class="ztree "></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" id="menuDirectory">
									<label class="col-sm-2 control-label">菜单类型</label>
									<div class="col-sm-5">
										<label class="radio-inline"> <input type="radio"
											name="type" id="type_menu" value="0"
											<c:if test="${menu.type == 0 || empty menu}">
											checked				
											</c:if>>菜单
										</label> <label class="radio-inline"> <input
											type="radio" name="type" id="type_button" value="1"
											<c:if test="${menu.type == 1}">
											checked				
											</c:if>>按钮
										</label>
									</div>
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
											value="${menu.link }" name="link" placeholder="分类链接,默认空"
											${disabled } />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">唯一Key</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="uniqueKey"
											value="${menu.uniqueKey }" name="uniqueKey" placeholder="唯一Key"
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
	<script type="text/javascript" src="/js/jquery/jquery.ztree.all.min.js"></script>
	<script type="text/javascript" src="/js/cms/menu/input.js"></script>
</body>
</html>