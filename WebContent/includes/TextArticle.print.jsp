<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="article" value="${empty article ? action.article : article}"></c:set>

<div>
	<ocw:parse resource="${resource}">${article.content.text}</ocw:parse>
</div>