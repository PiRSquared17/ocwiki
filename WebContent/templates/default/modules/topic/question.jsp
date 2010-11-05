<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>


<c:choose>
    <c:when test="${empty module.questions}">
        Không có
    </c:when>
    <c:otherwise>
		<ul>
		<c:forEach items="${module.questions}" var="question">
		    <li><ocw:articleLink resource="${question}"></ocw:articleLink></li>
		</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>