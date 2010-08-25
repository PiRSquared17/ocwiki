<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<ul>
    <li><a href="?action=user.edituser" title="Thay đổi các thông tin cá nhân" target="_self">Thông tin cá nhân</a></li>
    <li><a href="?action=history.list" title="Xem lịch sử các bài kiểm tra đã làm" target="_self">Lịch sử làm bài</a></li>
	<c:if test="${sessionScope.login && sessionScope.user.group=='teacher'}">
	    <li><a href="${scriptPath}?action=exam.list">Lịch sử chấm bài</a></li>    
	    <li><a href="${scriptPath}?action=exam.create">Hỗ trợ chấm bài</a></li>    
	    <li><a href="${scriptPath}?action=test.create">Tạo đề mới</a></li>
	    <li><a href="${scriptPath}?action=question.create">Tạo câu hỏi mới</a></li>
	    <li><a href="${scriptPath}?action=teststruct.create">Tạo cấu trúc đề mới</a></li>
	    <li><a href="${scriptPath}?action=testtopic.list">Chủ đề đề thi</a></li>
	    <li><a href="${scriptPath}?action=topic.list">Chủ đề câu hỏi</a></li>
	</c:if>
</ul>
    