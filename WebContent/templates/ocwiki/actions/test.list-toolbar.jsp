<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
		<button type="button" onclick="location.href='${scriptPath}?action=test.create'">Thêm</button>
		<button name="tl_submit" value="delete" type="submit" onclick="return confirmDelete('tl_tests', 'đề thi');">Xóa</button>
    </c:if>
</div>
