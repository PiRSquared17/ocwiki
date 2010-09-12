<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:forEach items="${module.children}" var="child">
    <ocw:articleLink resource="${child}"></ocw:articleLink>
</c:forEach>