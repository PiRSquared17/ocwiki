<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<h3>
Người dùng: ${action.displayedUser.name}
</h3> 

<fieldset>
<legend>Thông tin</legend>
<p>
Tên đầy đủ: <b>${action.displayedUser.fullname}</b> 
</p>
<p>
Ngày đăng kí: <b>${u:formatDateTime(action.displayedUser.registerDate)}</b>
</p>
<p>
Email: <b><a href="mailto:${action.displayedUser.email}">${action.displayedUser.email}</a></b>
</p>
<p>
Nhóm: <b>${action.displayedUser.group}</b>
<c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
    <i><a href="${scriptPath}?action=user.editgroup&user=${action.displayedUser.id}">sửa</a></i>
</c:if>
</p>
</fieldset>

<fieldset>
<legend>Đề tạo gần đây</legend>
	<c:choose>
	<c:when test="${u:size(action.tests) > 0}">
		<ul>
		    <c:forEach items="${action.tests}" var="test">
		        <li><a href="${scriptPath}?action=test.view&tv_id=${test.id}">${test.name}</a>; 
		            ${u:formatDateTime(test.createDate)}
		        </li>
			</c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
	    <i>&lt;Không có dữ liệu&gt;</i>
	</c:otherwise>
	</c:choose>
</fieldset>

<fieldset>
<legend>Đề làm gần đây</legend>
	<c:choose>
	<c:when test="${u:size(action.histories) > 0}">
		<ul>
		    <c:forEach items="${action.histories}" var="history">
		        <li><a href="${scriptPath}?action=history.view&id=${history.id}">${history.test.name}</a>; 
		            ${u:formatDateTime(history.takenDate)})
		        </li>
		    </c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
	    <i>&lt;Không có dữ liệu&gt;</i>
	</c:otherwise>
	</c:choose>
</fieldset>