<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:choose>
    <c:when test="${empty module.children}">
        Không có
    </c:when>
    <c:otherwise>
		<ul>
		<c:forEach items="${module.children}" var="child">
		    <li><ocw:articleLink resource="${child}"></ocw:articleLink></li>
		</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>