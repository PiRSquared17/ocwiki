<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ul>
<c:forEach items="${module.questions}" var="question">
    <li><ocw:articleLink resource="${question}"></ocw:articleLink></li>
</c:forEach>
</ul>