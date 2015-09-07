<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
		table.treetable tr span a {
			font-family: "fontAwesome";
			padding-right: 5px;
		}
		table.treetable tr.collapsed span.indenter a:BEFORE {
			content: "\f067";
		}
		table.treetable tr.expanded span.indenter a:BEFORE {
			content: "\f068";
		}
		</style>
	</head>
	<body>
		<section class="content-header">
			<h1>菜单管理</h1>
			<ol class="breadcrumb">
				<li><a href="javascript:;"><i class="fa fa-dashboard"></i>CMS</a></li>
				<li><a href="javascript:;">系统管理</a></li>
				<li class="active">菜单管理</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"></h3>
							<div class="box-tools">
								<a role="button" href="/menu/toAdd/0"
									class="btn btn-default btn-sm" data-toggle="modal"
									data-target="#modal" style="margin-right: 10px;">新增</a>
								<ul class="pagination pagination-sm no-margin pull-right">
									<li><a href="javascript:;" class="expandAll">展开全部</a></li>
									<li><a href="javascript:;" class="collapseAll">闭合全部</a></li>
								</ul>
							</div>
						</div>
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover" id="menuTable">
								<thead>
									<tr>
										<th style="width:15%;">菜单名称</th>
										<th style="width:10%;">菜单类型</th>
										<th style="width:15%;">菜单说明</th>
										<th style="width:15%;">菜单唯一Key</th>
										<th style="width:10%;">菜单图标</th>
										<th style="width:10%;">菜单状态</th>
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
		</section>
		<script src="/js/jquery/jquery.treetable.js"></script>
		<script src="/js/cms/menu/menu.js"></script>
	</body>
</html>