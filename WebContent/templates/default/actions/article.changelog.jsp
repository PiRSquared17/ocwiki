<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ul>
<c:forEach items="${action.changes}" var="change">
    <li>${u:formatDateTime(change.datetime)} ${change.user.name} ${change.summary}
        <c:if test="${change.reversible}"><a href="#">đảo ngược</a></c:if>
    </li>
</c:forEach>
</ul>