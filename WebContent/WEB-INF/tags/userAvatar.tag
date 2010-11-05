<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="user" description="Đối tượng người dùng"
	required="false" type="oop.data.User"%>
<%@ include file="/includes/common.jsp" %>
	
<c:choose>
	<c:when test="${empty user.avatar}">
	    <c:set var="path" value="${tempatePath}/images/default-avatar.gif" />
	</c:when>
	<c:otherwise>
	    <c:set var="path" value="${config.uploadPath}/avatar/${user.avatar}" />
	</c:otherwise>
</c:choose>

<img src="${path}">