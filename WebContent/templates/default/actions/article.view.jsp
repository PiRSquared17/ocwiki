<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<div style="float: right;">
    [<ocw:actionLink name="revision.list">
	    <ocw:param name="resourceID" value="${action.resource.id}"></ocw:param>
	    lịch sử
	</ocw:actionLink>]
	[<ocw:actionLink name="article.edit">
	    <ocw:param name="id" value="${action.resource.id}"></ocw:param>
	    sửa
	</ocw:actionLink>]
    <c:if test="${ocw:assignableFrom('oop.data.BaseQuestion', type.name)}">
        [<a href="#">bài giải</a>]
    </c:if>
</div>

<h1>${action.resource.qualifiedName}</h1>
<hr />
<ocw:userLink user="${action.resource.author}" /> tạo lúc 
${u:formatDateTime(action.resource.createDate)}

<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

<c:choose>
    <c:when test="${ocw:assignableFrom('oop.data.Test', type.name)}">
        <!-- include bài liên quan -->
    </c:when>
</c:choose>
