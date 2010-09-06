<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<h1>${action.revision.qualifiedName}</h1>
<hr />
Phiên bản lúc ${u:formatDateTime(action.revision.timestamp)},
<ocw:userLink user="${action.revision.author}" />

<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

