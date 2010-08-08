<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
	<c:if test="${sessionScope.login && sessionScope.user.group=='teacher'}">
		<button onclick="location.href='${scriptPath}?action=sectstruct.create&ssc_testid=${teststruct.id}'">Thêm mục</button>
		<button onclick="location.href='${scriptPath}?action=topicconst.create&teststruct=${teststruct.id}'">Thêm chủ đề</button>
		<button onclick="location.href='${scriptPath}?action=levelconst.create&teststruct=${teststruct.id}'">Thêm độ khó</button>
		<button onclick="location.href='${scriptPath}?action=test.create&tc_usestruct=true&tc_structname=${teststruct.name}&tc_struct=${teststruct.id}'">Tạo đề</button>
		<button onclick="return confirm('Bạn có chắc muốn xoá cấu trúc đề này?') && (location.href='${scriptPath}?action=teststruct.list&tsl_submit=delete&tsl_tests=${teststruct.id}')">Xóa</button>
	</c:if>
	<button onclick="location.href='${scriptPath}?action=teststruct.list'">Quay về danh sách</button>
</div>
