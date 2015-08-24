<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<body>
		<section class="content-header">
			<h1>角色列表</h1>
			<ol class="breadcrumb">
				<li><a href="###"><i class="fa fa-dashboard"></i>CMS</a></li>
				<li><a href="###">系统设置</a></li>
				<li class="active">角色管理</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"></h3>
							<div class="box-tools">
								<div class="input-group" style="width: 250px;">
									<a role="button" href="/role/toAdd"
										class="btn btn-default btn-sm pull-left" data-toggle="modal"
										data-target="#modal" style="margin-right: 10px;">新增</a> <input
										type="text" name="table_search"
										class="form-control input-sm pull-right" placeholder="Search"
										style="width: 150px;" />
									<div class="input-group-btn">
										<button class="btn btn-sm btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover" id="roleTable">
								<thead>
									<tr>
										<th>ID</th>
										<th>角色名</th>
										<th>角色说明</th>
										<th>状态</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="box-footer clearfix">
							<ul class="pagination pagination-sm no-margin pull-right"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" data-backdrop="static" id="modal">
				<div class="modal-dialog">
					<div class="modal-content"></div>
				</div>
			</div>
			<div class="modal fade" data-backdrop="static" id="role_modal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<h4 class="modal-title">分配权限</h4>
						</div>
						<div class="modal-body">
							<div id="treeview"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary pull-left">保存</button>
							<button type="button" class="btn btn-default pull-right"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		<script src="/js/cms/role/role.js"></script>
	</body>
</html>