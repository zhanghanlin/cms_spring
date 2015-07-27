<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:directive.include file="/common/common.html" />
	</head>
	<body class="skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:directive.include file="/common/head.html" />
			<jsp:directive.include file="/common/left.html" />
			<div class="content-wrapper">
				<section class="content-header">
					<h1>CMS <small>index</small></h1>
				</section>
				<section class="content">
					<div class="row">
						<section class="col-lg-12 connectedSortable">
							<jsp:directive.include file="/common/tools/Calendar.html" />
							<jsp:directive.include file="/common/tools/Visitors.html" />
						</section>
					</div>
				</section>
			</div>
			<jsp:directive.include file="/common/right.html" />
			<jsp:directive.include file="/common/footer.html" />
		</div>
	</body>
</html>