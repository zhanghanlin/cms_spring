<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			#loginLogTable thead tr th {
				width: 25%;
			}
		</style>
	</head>
	<body>
		<section class="content-header">
			<h1>登陆日志管理</h1>
			<ol class="breadcrumb">
				<li><a href="javascript:;"><i class="fa fa-dashboard"></i>CMS</a></li>
				<li><a href="javascript:;">日志管理</a></li>
				<li class="active">登陆日志</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover" id="loginLogTable">
								<thead>
									<tr>
										<th>登陆帐号</th>
										<th>登陆时用户名</th>
										<th>登陆IP</th>
										<th>登陆时间</th>
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
		</section>
		<script src="/js/cms/log/loginLog.js"></script>
	</body>
</html>