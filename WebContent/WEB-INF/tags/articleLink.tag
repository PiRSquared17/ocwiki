<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="resource"
    description="Đối tượng bài viết (lớp Resource)" required="false" 
    rtexprvalue="true" type="oop.data.Resource"%>
<%@ attribute name="revision"
    description="Đối tượng phiên bản bài viết (lớp Revision)" required="false" 
    rtexprvalue="true" type="oop.data.Revision"%>
<%@ attribute name="onclick" description="Mã xử lí sự kiện nhấn chuột"
	required="false" type="java.lang.String"%>
<%@ include file="/includes/common.jsp"%>

<jsp:doBody var="body" />
<c:choose>
    <c:when test="${empty revision}">
        <c:set var="url" value="${homeDir}/article/${resource.id}"></c:set>
		<c:if test="${empty fn:trim(body)}">
		    <c:set var="body" value="${resource.article.name}"></c:set>
		</c:if>
    </c:when>
    <c:otherwise>
        <c:set var="url" value="${homeDir}/article/revision/${revision.id}"></c:set>
        <c:if test="${empty fn:trim(body)}">
            <c:set var="body" value="${revision.article.name}"></c:set>
        </c:if>
    </c:otherwise>
</c:choose>
<a href="${url}" onclick="${onclick}">${body}</a>