<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cmsFn" uri="/WEB-INF/tld/cms.tld" %>
<!DOCTYPE html>
<html>
	<body>
		<section class="content-header">
			<h1>Session管理</h1>
			<ol class="breadcrumb">
				<li><a href="javascript:;"><i class="fa fa-dashboard"></i>CMS</a></li>
				<li><a href="javascript:;">系统监控管理</a></li>
				<li class="active">Session管理</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"></h3>
							<div class="box-tools">
								<div class="input-group" style="width: 150px;">
									<input type="text" name="table_search"
										class="form-control input-sm pull-right" placeholder="Search" />
									<div class="input-group-btn">
										<button class="btn btn-sm btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover" id="userTable">
								<thead>
									<tr>
										<th>会话ID</th>
										<th>用户名</th>
										<th>主机地址</th>
										<th>最后访问时间</th>
										<th>已强制退出</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${sessions}" var="session">
										<tr>
											<td>${session.id}</td>
											<td>${cmsFn:principal(session)}</td>
											<td>${session.host}</td>
											<td><fmt:formatDate value="${session.lastAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
											<td>${cmsFn:isForceLogout(session) ? '是' : '否'}</td>
											<td>
												<c:if test="${not cmsFn:isForceLogout(session)}">
													<a class="forceLogout" sessionId="${session.id }" href="javascript:;">强制退出</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="box-footer clearfix">
							<ul class="pagination pagination-sm no-margin pull-right"></ul>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
	<script src="/js/cms/session/session.js"></script>
</html>