<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
    <c:if test="${sessionScope.login}">
		<button type="button" onclick="location.href='${scriptPath}?action=revision.compare'">So Sánh 2 Phiên Bản</button>
    </c:if>
</div>
