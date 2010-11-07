<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${action.count > 0}">
    <ocw:pagination count="${action.count}" currentStart="${action.start}"></ocw:pagination>
	<c:forEach items="${action.histories}" var="history">
	    <div>
		  <ocw:actionLink name="history/view">
		      <ocw:param name="id" value="${history.id}"></ocw:param>
		      ${history.revision.name} (${u:formatDateTime(history.takenDate)})
		  </ocw:actionLink>
		</div>
	</c:forEach>
</c:when>
<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>