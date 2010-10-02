<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
    <c:forEach items="${module.topics}" var="topic">
       <li><a href="${scriptPath}?action=test.list&topic=${topic.id}">${topic.name}</a></li>
    </c:forEach>
	<li><a href="${scriptPath}?action=topic.list">Danh sách chủ đề</a></li>
	<li><a href="${scriptPath}?action=test.list">Tất cả</a></li>
</ul>
