<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="user"
    description="Đối tượng người sử dụng" required="true"
    type="oop.data.User"%>
<%@ include file="/includes/common.jsp"%>

<a href="${ocw:actionUrl('user.profile')}?user=${user.id}">${user.fullname}</a>