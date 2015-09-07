<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
	<body>
		<section class="content-header">
			<h1>用户列表</h1>
			<ol class="breadcrumb">
				<li><a href="javascript:;"><i class="fa fa-dashboard"></i>CMS</a></li>
				<li><a href="javascript:;">用户管理</a></li>
				<li class="active">用户列表</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"></h3>
						</div>
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover" id="userTable">
								<thead>
									<tr>
										<th style="width:15%;">用户名</th>
										<th style="width:19%;">邮箱</th>
										<th style="width:19%;">角色</th>
										<th style="width:10%;">状态</th>
										<th style="width:20%;">创建时间</th>
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
			<shiro:hasRole name="admin">
			<div class="modal fade" data-backdrop="static" id="role_modal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">x</span>
							</button>
							<h4 class="modal-title">分配角色</h4>
						</div>
						<div class="modal-body">
							<table class="table table-hover" id="roleTable">
								<thead>
									<tr>
										<th>选择</th>
										<th>角色名称</th>
										<th>角色说明</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" id="saveRole2User" class="btn btn-primary pull-left">保存</button>
							<button type="button" class="btn btn-default pull-right"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
			</shiro:hasRole>
		</section>
		<script src="/js/cms/user/user.js"></script>
	</body>
</html>