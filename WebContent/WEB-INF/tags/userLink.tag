<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="user"
    description="Đối tượng người sử dụng" required="true"
    type="oop.data.User" rtexprvalue="true"%>
<%@ include file="/includes/common.jsp"%>

<jsp:doBody var="customBody"></jsp:doBody>
<!-- 
<c:choose>
    <c:when test="${empty user.name}"> -->
    <!-- 
    </c:when>
    <c:otherwise>
        <c:set var="url">${config.userPath}/${u:urlEncode(user.name)}</c:set>
    </c:otherwise>
</c:choose>-->
        <c:set var="url">${config.actionPath}/user.profile?user=${user.id}</c:set>

<c:set var="linkBody">
    <c:choose>
        <c:when test="${empty fn:trim(customBody)}">
            ${user.fullname}
        </c:when>
        <c:otherwise>
            ${customBody}
        </c:otherwise>
    </c:choose>
</c:set>
<c:set var="linkBody" value="${fn:trim(linkBody)}"></c:set>

<a href="${url}">${linkBody}</a>