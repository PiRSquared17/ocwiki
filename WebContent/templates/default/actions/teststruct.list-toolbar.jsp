<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
    <c:if test="${sessionScope.login}">
		<button type="button" onclick="location.href='${scriptPath}?action=teststruct.create'">Thêm</button>
		<button name="tsl_submit" value="delete" type="submit" onclick="return confirmDelete('selected', 'cấu trúc đề');">Xóa</button>
    </c:if>
</div>
