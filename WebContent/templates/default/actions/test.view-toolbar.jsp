<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
	<c:if test="${sessionScope.login}">
	   <c:if test="${test.questionCount > 0}">
    		<button onclick="location.href='${scriptPath}?action=test.solve&testId=${test.id}'">Làm</button>
	   </c:if>
		<c:if test="${sessionScope.user.group=='teacher'}">
		      <button onclick="location.href='${scriptPath}?action=test.ajaxedit&testId=${test.id}'">new edit page</button>
			<button onclick="location.href='${scriptPath}?action=section.create&sc_testid=${test.id}'">Thêm mục</button>
			<button onclick="location.href='${scriptPath}?action=test.addquestion&taq_test=${test.id}'">Thêm câu hỏi</button>
			<button onclick="return confirm('Bạn có chắc muốn xoá đề thi này?') && (location.href='${scriptPath}?action=test.list&tl_submit=delete&tl_tests=${test.id}')">Xóa</button>
		</c:if>
	</c:if>
	<button onclick="location.href='${scriptPath}?action=testversion.list&testid=${test.id}'">Các phiên bản</button>
	<button onclick="location.href='${scriptPath}?action=test.savedocx&testid=${test.id}&verid=${version.id}'">Xuất</button>
	<button onclick="location.href='${scriptPath}?action=test.list'">Quay về danh sách</button>
</div>
