<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="resource"
    description="Đối tượng bài viết (lớp Resource)" required="false" 
    rtexprvalue="true" type="oop.data.Resource"%>
<%@ attribute name="revision"
    description="Đối tượng phiên bản bài viết (lớp Revision)" required="false" 
    rtexprvalue="true" type="oop.data.Revision"%>
<%@ include file="/includes/common.jsp" %>

<jsp:doBody var="customBody" />
<c:choose>
    <c:when test="${empty revision}"></c:when>
</c:choose>
<a href="${homeDir}/article/${resource.id}">
</a>