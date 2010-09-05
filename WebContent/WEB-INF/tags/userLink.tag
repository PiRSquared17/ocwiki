<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="user"
    description="Đối tượng người sử dụng" required="true"
    type="oop.data.User" rtexprvalue="true"%>
<%@ include file="/includes/common.jsp"%>

<jsp:doBody var="customBody"></jsp:doBody>
<a href="${ocw:actionUrl('user.profile')}?user=${user.id}">
    
</a>