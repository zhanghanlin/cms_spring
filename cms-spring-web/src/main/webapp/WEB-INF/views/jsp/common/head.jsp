<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<header class="main-header">
	<a href="/" class="logo">
		<span class="logo-mini"><b></b>CMS</span>
		<span class="logo-lg"><b>ADMIN&nbsp;</b>CMS</span>
	</a>
	<nav class="navbar navbar-static-top" role="navigation">
		<a class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown notifications-menu">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-bell-o"></i>
						<span class="label label-warning">1</span>
					</a>
					<ul class="dropdown-menu">
						<li class="header">你有1条消息</li>
						<li>
							<ul class="menu">
								<li>
									<a href="javascript:;"><i class="fa fa-users text-aqua"></i>5 new members joined today</a>
								</li>
								<li class="footer"><a href="#">View all</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="dropdown user user-menu">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
						<img src="/images/andy.jpg" class="user-image" alt="User Image" />
						<span class="hidden-xs"><shiro:principal/></span>
					</a>
					<ul class="dropdown-menu">
						<li class="user-header">
							<img src="/images/andy.jpg" class="user-image" alt="User Image" />
							<p><shiro:principal/> - Web Developer<small>Member since Nov. 2015</small></p>
						</li>
						<li class="user-body">
							<div class="col-xs-4 text-center"><a href="javascript:;">A</a></div>
							<div class="col-xs-4 text-center"><a href="javascript:;">B</a></div>
							<div class="col-xs-4 text-center"><a href="javascript:;">C</a></div>
						</li>
						<li class="user-footer">
							<div class="pull-left"><a href="javascript:;" class="btn btn-default btn-flat">设置</a></div>
                    		<div class="pull-right"><a href="/logout" class="btn btn-default btn-flat">退出</a></div>
						</li>
					</ul>
				</li>
				<li><a href="javascript:;" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a></li>
			</ul>
		</div>
	</nav>
</header>