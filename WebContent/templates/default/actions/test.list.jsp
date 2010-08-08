<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${pageCount > 0}">
	
	<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>
	
	<form action="${scriptPath}" id="listForm">
	<input type="hidden" name="action" value="test.list" />
	
	<jsp:include page="test.list-toolbar.jsp"></jsp:include>
	<jsp:include page="test.list-nav.jsp"></jsp:include>
	
	<table>
	<tr>
        <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
    		<th width="20px">&nbsp;</th>
    	</c:if>
		<th>Tên</th>
		<th width="140px">Tác giả</th>
		<th width="120px">Thời điểm tạo</th>
		
	</tr>
	<c:forEach items="${tests}" var="test">
	<tr>
        <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
		   <td>
	            <input type="checkbox" name="tl_tests" value="${test.id}"></input>
	       </td>
        </c:if>
		<td><a href="${scriptPath}?action=test.view&tv_id=${test.id}">${test.name}</a></td>
		<td><a href="${scriptPath}?action=test.list&author=${test.author.id}">
			${test.author.fullname}
		</a></td>
		<td>${u:formatDateTime(test.createDate)}</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
	
	<jsp:include page="test.list-nav.jsp"></jsp:include>
	<jsp:include page="test.list-toolbar.jsp"></jsp:include>
	
	</form>
    
</c:when>

<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>
    