<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
	<c:if test="${sessionScope.login}">
		<c:if test="${sessionScope.user.group=='teacher'}">
			<label>Nhập mã phiên bản mới:</label><input type="text" name="code" id="code">
			<input type="hidden" name="testid" id="testid" value="${test.id}">
			<button type="submit" name="tl_submit" id="tl_submit" value="create"'">Thêm phiên bản</button>
			<button type="submit" name="tl_submit" id="tl_submit" value="delete"
			     onclick="return confirmDelete('tl_testversions', 'phiên bản');">Xóa</button>
		</c:if>
	</c:if>
	<ocw:articleButton resource="${action.resource}">Trở lại đề</ocw:articleButton>
</div>
