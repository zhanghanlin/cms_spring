<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<aside class="main-sidebar">
	<section class="sidebar">
		<div class="user-panel">
			<div class="pull-left image"><img src="/images/andy.jpg" class="img-circle" alt="User Image"></div>
			<div class="pull-left info">
				<p>${user.userName }</p>
				<a href="###"><i class="fa fa-circle text-success"></i>Online</a>
			</div>
		</div>
		<form action="###" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control" placeholder="Search...">
				<span class="input-group-btn">
					<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
				</span>
			</div>
		</form>
		<ul class="sidebar-menu">
			<li class="header">CMS MAIN</li>
			<li class="treeview">
				<!-- code -->
				<!-- 
				<c:forEach items="${menuTree }" var="m">
				<a href="###">
					<i class="fa ${m.node.icon }"></i>
					<span>${m.node.name }</span>
					<c:if test="${fn:length(m.childNode) > 0 }">
					<i class="fa fa-angle-left pull-right"></i>
					</c:if>
				</a>
				<c:if test="${fn:length(m.childNode) > 0 }">
				<ul class="treeview-menu">
					<c:forEach items="${m.childNode }" var="mc">
					<li><a href="${mc.node.link }"><i class="fa fa-circle-o"></i>${mc.node.name }</a></li>
					</c:forEach>
				</ul>
				</c:if>
				</c:forEach>				
				 -->
			</li>
			<li><a href="###"><i class="fa fa-book"></i><span>Documentation</span></a></li>
		</ul>
	</section>
</aside>