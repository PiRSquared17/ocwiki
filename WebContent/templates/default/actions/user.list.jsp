<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${pageCount > 0}">

    <c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

	<form action="${scriptPath}" id="listForm">
		<input type="hidden" name="action" value="user.list" />
		
		<jsp:include page="user.list-nav.jsp"></jsp:include>
		
		<table>
		<tr>
			<th>Tài khoản</th>
			<th>Nhóm</th>
			<th>Ngày gia nhập</th>
		</tr>
		<c:forEach items="${users}" var="user">
		<tr>
			<td>linh tinh <ocw:userLink user="${user}"></ocw:userLink></td>
			<td>${user.group}</td>
			<td>${u:formatDateTime(user.registerDate)}</td>
		</tr>
		</c:forEach>
		<tr>
		</tr>
		</table>
		
		<jsp:include page="user.list-nav.jsp"></jsp:include>
	</form>
	
</c:when>

<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>
