<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div>
<h3>Chủ đề chưa được sử dụng:</h3>
<c:choose>

<c:when test="${u:size(action.unusedTopics) > 0}">
	<div align="center"><ocw:pagination actionName="${action.descriptor.name}" count="${action.count}" currentStart="${action.curStart}"/></div>
	<div class="content-wrapper">
        <ul style="list-style: none;">
		<c:forEach items="${action.unusedTopics}" var="topic" >
			<li>
			     <ocw:articleLink resource="${topic}">${topic.name}</ocw:articleLink>
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