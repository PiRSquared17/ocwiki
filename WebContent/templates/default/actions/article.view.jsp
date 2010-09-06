<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

<c:choose>
    <c:when test="${ocw:assignableFrom('oop.data.BaseQuestion', type.name)}">
        <!-- include bài giải -->
    </c:when>
    <c:when test="${ocw:assignableFrom('oop.data.Test', type.name)}">
        <!-- include bài liên quan -->
    </c:when>
</c:choose>
