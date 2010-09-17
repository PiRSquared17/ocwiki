<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<li>
	<c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
	   <input type="checkbox" name="cl_topics" value="${topic.id}">
	</c:if>
	<c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
	   <i><a href="${scriptPath}?action=topic.edit&id=${topic.id}"><img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="12" height="12" /></a></i>
	</c:if>
    <a href="${scriptPath}?action=question.list&topic=${topic.id}">${topic.name}</a>
</li>

<!-- recursive display items -->
<c:set var="curr" value="${topic}" scope="page"></c:set>
<c:forEach items="${curr.article.children}" var="child">
	<ul style="list-style: none;">
		<c:set var="topic" value="${child}" scope="request"></c:set>
		<jsp:include page="topic.list-item.jsp"/>
	</ul>
</c:forEach>
