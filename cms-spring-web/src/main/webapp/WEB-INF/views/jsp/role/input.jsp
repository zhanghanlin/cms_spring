<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<body>
	<form action="${action }" method="post" class="form-horizontal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="roleLableTitle">权限Info</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-md-12">
					<c:set var="disabled" value=""></c:set>
					<c:if test="${empty submit }">
						<c:set var="disabled" value="disabled"></c:set>
					</c:if>
					<input type="hidden" value="${UUID }" name="UUID" /> <input
						type="hidden" value="${role.id }" name="id" id="id" />
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">角色名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="name"
											value="${role.name }" name="name" placeholder="角色名"
											${disabled } required />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-2 control-label">角色说明</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="link"
											value="${role.note }" name="note" placeholder="角色说明"
											${disabled } required />
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
				<button type="submit" class="btn btn-primary pull-left">${submit}</button>
			</c:if>
			<button type="button" class="btn btn-default pull-right"
				data-dismiss="modal">关闭</button>
		</div>
	</form>
	<script type="text/javascript" src="/js/tools/select2.full.min.js"></script>
	<script type="text/javascript" src="/js/cms/menu/input.js"></script>
</body>
</html>