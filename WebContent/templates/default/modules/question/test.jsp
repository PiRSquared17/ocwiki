<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:choose>
    <c:when test="${empty module.tests}">
        Không có
    </c:when>
    <c:otherwise>
		<ul>
		    <c:forEach items="${module.tests}" var="test">
		        <li><ocw:articleLink resource="${test}"></ocw:articleLink></li>
		    </c:forEach>
		</ul>
	</c:otherwise>
</c:choose>
