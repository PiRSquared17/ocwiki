<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${action.count > 0}">
    <ocw:pagination count="${action.count}" currentStart="${action.start}"></ocw:pagination>
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
		<td>
            <ocw:actionLink name="search">
              <ocw:param name="search_query" value="type:test author:#${resource.author.id}"></ocw:param>
              ${resource.author.fullname}
            </ocw:actionLink>
		</td>
		<td>${u:formatDateTime(resource.createDate)}</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
</c:when>
<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>