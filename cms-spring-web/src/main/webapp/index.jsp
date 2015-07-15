<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Bootstrap</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		    <![endif]-->
	</head>
	<body>
		<%@ include file="/common/head.html" %>
		<div class="container-fluid">
			<div class="row">
				<%@ include file="/common/left.html" %>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<h1 class="page-header">CMS</h1>
					<%com.demo.java.web.system.SystemInfo sys = com.demo.java.web.system.SystemInfo.getInstance(request);
					%>
					<table class="table table-hover">
						<thead><th>系统信息</th></thead>
						<tbody>
							<tr>
								<td>系统名称</td>
								<td><%=sys.getOs_name() %></td>
							</tr>
							<tr>
								<td>系统IP</td>
								<td><%=sys.getOs_ip() %></td>
							</tr>
							<tr>
								<td>系统Mac</td>
								<td><%=sys.getOs_mac() %></td>
							</tr>
							<tr>
								<td>CPU核数</td>
								<td><%=sys.getOs_cpus() %></td>
							</tr>
							<tr>
								<td>协议</td>
								<td><%=sys.getServer_protocol() %></td>
							</tr>
							<tr>
								<td>脚本语言</td>
								<td>Java <%=sys.getJava_version() %></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@ include file="/common/footer.html" %>
	</body>
</html>