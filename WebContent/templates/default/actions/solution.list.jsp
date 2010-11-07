<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${action.count > 0}">
    <c:forEach var="solution" items="${action.solutions}">
        <ocw:articleLink resource="${solution}"></ocw:articleLink>
    </c:forEach>
</c:when>
<c:otherwise>
    <div class="empty-notif">
        Câu hỏi này chưa có ai giải. Hãy 
        <a href="javascript:createSolution(${action.question.id})">trở thành người đầu tiên</a>!
    </div>
</c:otherwise>

</c:choose>