<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:if test="${fn:length(m.childNode) > 0 }">
<li class="treeview">
	<c:forEach items="${menuTree }" var="m">
	<a href="###"> <i class="fa ${m.node.icon }"></i> <span>${m.node.name
				}</span> <c:if test="${fn:length(m.childNode) > 0 }">
				<i class="fa fa-angle-left pull-right"></i>
			</c:if>
		</a>
		<c:if test="${fn:length(m.childNode) > 0 }">
			<ul class="treeview-menu">
				<c:forEach items="${m.childNode }" var="mc">
					<li><a href="${mc.node.link }"><i class="fa fa-circle-o"></i>${mc.node.name
							}</a></li>
				</c:forEach>
			</ul>
		</c:if>
	</c:forEach>
</li>
</c:if>