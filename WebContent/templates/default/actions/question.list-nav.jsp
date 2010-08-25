<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty param.author}">
	<c:set var="filter" value="&author=${param.author}"></c:set>
</c:if>
<c:if test="${not empty param.topic}">
	<c:set var="filter" value="&topic=${param.topic}"></c:set>
</c:if>


<div class="navigator">
<c:choose>
    <c:when test="${page > 1}">
        <a href="${scriptPath}?action=question.list&page=1${filter}"><img src="${templatePath}/images/2leftarrow.png" alt="first" /></a>
        <a href="${scriptPath}?action=question.list&page=${page-1}${filter}"><img src="${templatePath}/images/1leftarrow.png" alt="previous" /></a>
    </c:when>
    <c:otherwise>
         
    </c:otherwise>
</c:choose>

Trang ${page} / ${pageCount}

<c:choose>
    <c:when test="${page < pageCount}">
        <a href="${scriptPath}?action=question.list&page=${page+1}${filter}"><img src="${templatePath}/images/1rightarrow.png" alt="next" /></a>
        <a href="${scriptPath}?action=question.list&page=${pageCount}${filter}"><img src="${templatePath}/images/2rightarrow.png" alt="last" /></a>
    </c:when>
    <c:otherwise>
        
    </c:otherwise>
</c:choose>
</div>
    