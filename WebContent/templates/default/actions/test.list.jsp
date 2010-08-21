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
		<th>Tên</th>
		<th width="140px">Tác giả</th>
		<th width="120px">Thời điểm tạo</th>
	</tr>
	<c:forEach items="${tests}" var="resource">
	<c:set var="test" value="${resource.article}"></c:set>
	<tr>
		<td><ocw:articleLink resource="${resource}">${test.name}</ocw:articleLink></td>
		<td><ocw:userLink user="${resource.author}">${resource.author.fullname}</ocw:userLink></td>
		<td>${u:formatDateTime(resource.createDate)}</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
	
	<jsp:include page="test.list-nav.jsp"></jsp:include>
	</form>
    
</c:when>
<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>
    
<jsp:include page="test.list-toolbar.jsp"></jsp:include>