<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${pageCount > 0}">
	
	<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>
	
	<form action="${scriptPath}" id="listForm">
	<input type="hidden" name="action" value="teststruct.list" />
	
	<jsp:include page="teststruct.list-toolbar.jsp"></jsp:include>
	<jsp:include page="teststruct.list-nav.jsp"></jsp:include>
	
	<table>
	<tr>
        <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
		  <th width="20px">&nbsp;</th>
		</c:if>
		<th>Tên</th>
		<th width="140px">Chủ đề</th>
		<th width="140px">Tác giả</th>
        <th width="120px">Thời điểm tạo</th>
	</tr>
	<c:forEach items="${structs}" var="struct">
	<tr>
	   <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
	   	   <td>
			   <input type="checkbox" name="selected" value="${struct.id}"></input>
    		</td>
	   </c:if>
		<td><a href="${scriptPath}?action=teststruct.view&tsv_id=${struct.id}">${struct.name}</a></td>
		<td><a href="${scriptPath}?action=teststruct.list&topic=${struct.topic.id}">
			${struct.topic.name}
		</a></td>
		<td><a href="${scriptPath}?action=teststruct.list&author=${struct.author.id}">
			${struct.author.fullname}
		</a></td>
		<td>${u:formatDateTime(struct.createDate)}</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
	
	<jsp:include page="teststruct.list-nav.jsp"></jsp:include>
	<jsp:include page="teststruct.list-toolbar.jsp"></jsp:include>
	
	</form>
	
</c:when>

<c:otherwise>
   <div class="empty-notif">
       Chưa có dữ liệu
   </div>
</c:otherwise>

</c:choose>

	