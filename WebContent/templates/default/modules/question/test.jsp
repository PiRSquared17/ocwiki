<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ul>
    <c:forEach items="${module.tests}" var="test">
        <li><ocw:articleLink resource="${test}"></ocw:articleLink></li>
    </c:forEach>
</ul>