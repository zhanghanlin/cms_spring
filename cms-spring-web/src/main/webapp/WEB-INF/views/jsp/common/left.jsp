<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<aside class="main-sidebar">
	<section class="sidebar">
		<div class="user-panel">
			<div class="pull-left image"><img src="/images/andy.jpg" class="img-circle" alt="User Image"></div>
			<div class="pull-left info">
				<p><shiro:principal/></p>
				<a href="javascript:;"><i class="fa fa-circle text-success"></i>Online</a>
			</div>
		</div>
		<form action="javascript:;" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control" placeholder="Search...">
				<span class="input-group-btn">
					<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
				</span>
			</div>
		</form>
		<ul class="sidebar-menu">
			<li class="header">CMS MAIN</li>
		</ul>
	</section>
</aside>