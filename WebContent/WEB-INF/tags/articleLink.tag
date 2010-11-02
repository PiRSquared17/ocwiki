<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="resource"
    description="Đối tượng bài viết (lớp Resource)" required="false" 
    rtexprvalue="true" type="oop.data.Resource"%>
<%@ attribute name="revision"
    description="Đối tượng phiên bản bài viết (lớp Revision)" required="false" 
    rtexprvalue="true" type="oop.data.Revision"%>
<%@ attribute name="onclick" description="Mã xử lí sự kiện nhấn chuột"
	required="false" type="java.lang.String"%>
<%@ attribute name="id" description="id"
	required="false" type="java.lang.String"%>
<%@ include file="/includes/common.jsp"%>

<jsp:doBody var="body" />
<c:choose>
    <c:when test="${not empty revision}">
        <c:set var="url" value="${homeDir}/article/revision/${revision.id}"></c:set>
        <c:if test="${empty fn:trim(body)}">
            <c:set var="body" value="${revision.resource.name}"></c:set>
        </c:if>
    </c:when>
    <c:when test="${not empty resource}">
        <c:set var="url" value="${homeDir}/article/${resource.id}"></c:set>
		<c:if test="${empty fn:trim(body)}">
		    <c:set var="body" value="${resource.name}"></c:set>
		</c:if>
    </c:when>
    <c:otherwise>
        error! no resource or revision specified.
    </c:otherwise>
</c:choose>

<a href="${url}" onclick="${onclick}" id="${id}">${fn:trim(body)}</a>