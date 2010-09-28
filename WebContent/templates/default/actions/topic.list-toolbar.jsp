<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
    
<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
		<button type="button" onclick="location.href='${scriptPath}?action=topic.create'">Thêm</button>
		<button name="cl_submit" value="delete" type="submit" onclick="return validateDelete();">Xóa</button>
    </c:if>
</div>