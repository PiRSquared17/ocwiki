<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
    
<ul>
    <li><ocw:actionLink name="user.edituser" title="Thay đổi các thông tin cá nhân">Thông tin cá nhân</ocw:actionLink></li>
    <li><ocw:actionLink name="history.list" title="Xem lịch sử các bài kiểm tra đã làm">Lịch sử làm bài</ocw:actionLink></li>
	<c:if test="${sessionScope.login && sessionScope.user.group=='teacher'}">
	    <li><a href="${scriptPath}?action=exam.list">Lịch sử chấm bài</a></li>    
	    <li><a href="${scriptPath}?action=exam.create">Hỗ trợ chấm bài</a></li>    
	    <li><a href="${scriptPath}?action=test.create">Tạo đề mới</a></li>
	    <li><a href="${scriptPath}?action=question.create">Tạo câu hỏi mới</a></li>
	    <li><a href="${scriptPath}?action=teststruct.create">Tạo cấu trúc đề mới</a></li>
	    <li><a href="${scriptPath}?action=topic.list">Chủ đề đề thi</a></li>
	    <li><a href="${scriptPath}?action=topic.list">Chủ đề câu hỏi</a></li>
	</c:if>
</ul>
    