<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="topic" value="${empty article ? action.article : article}"></c:set>

${topic.content.text}

<c:if test="${not empty topic.parent}">
	Chủ đề cha: <ocw:articleLink resource="${topic.parent}"></ocw:articleLink>
</c:if>