<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ul>
<c:forEach items="${module.children}" var="child">
    <li><ocw:articleLink resource="${child}"></ocw:articleLink></li>
</c:forEach>
</ul>