<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var = "topics" value = "${test.topics}"></c:set>

<div>
	<c:forEach var="topic" items = "topics">
		<div>
			${topic.name}
		</div>
		<br></br>
	</c:forEach>
</div>