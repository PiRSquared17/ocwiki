<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>


<h2>Đối với các phiên bản dưới đây, click vào ngày tạo để xem !</h2>

<div><label><strong>Chọn số phiên bản hiển thị
trên một trang :</strong> <select id="selectItemsPerPage" name="Items per page">
	<option value='10' selected="selected">10</option>
	<option value='20'>20</option>
	<option value='30'>30</option>
	<option value='40'>40</option>
	<option value='50'>50</option>
	<option value='100'>100</option>
</select> </label></div>
<c:choose>
	<c:when test="${action.pageCount > 0}">
		<c:if test="${not empty message}">
			<div class="notification">${message}</div>
		</c:if>

		<form action="${scriptPath}" id="listForm"><input type="hidden"
			name="action" value="revision.list" /> <jsp:include
			page="revision.list-toolbar.jsp"></jsp:include> <jsp:include
			page="revision.list-nav.jsp"></jsp:include>

		<table>
			<tr>
				<th width="120px">Thời điểm tạo</th>
				<th width="140px">Tác giả</th>
				<th width="200px">Tóm tắt</th>

			</tr>
			<c:forEach items="${action.revisions}" var="revision">

				<tr>
					<td><ocw:articleLink resource="${revision.resource}">${u:formatDateTime(revision.timestamp)}</ocw:articleLink></td>
					<td><ocw:userLink user="${revision.author}">${revision.author.fullname}</ocw:userLink></td>
					<td>${revision.summary}</td>
				</tr>
			</c:forEach>
			<tr>
			</tr>
		</table>

		<jsp:include page="revision.list-nav.jsp"></jsp:include></form>
	</c:when>

	<c:otherwise>
		<div class="empty-notif">Chưa có dữ liệu</div>
	</c:otherwise>
</c:choose>

<jsp:include page="revision.list-toolbar.jsp"></jsp:include>
