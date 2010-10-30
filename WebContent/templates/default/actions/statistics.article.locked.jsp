<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div>
<h3>Bài viết bị khóa:</h3>
<c:choose>

<c:when test="${u:size(action.lockedArticles) > 0}">
	<div><ocw:pagination actionName="${action.descriptor.name}" count="${action.count}" currentStart="${action.curStart}"/></div>
	<div class="clear"></div>
	<div class="content-wrapper">
        <ul style="list-style: none;">
		<c:forEach items="${action.lockedArticles}" var="article" >
			<li>
			     <ocw:articleLink resource="${article}">${article.name}</ocw:articleLink>
			</li>
		</c:forEach>
		</ul>
	</div>
</c:when>

<c:otherwise>
    <div class="empty-notif">
        Không có
    </div>
</c:otherwise>

</c:choose>
</div>