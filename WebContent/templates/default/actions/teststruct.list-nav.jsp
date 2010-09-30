<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="navigator">
<c:choose>
	<c:when test="${page > 1}">
		<a href="${scriptPath}?action=teststruct.list&page=1"><img src="${templatePath}/images/2leftarrow.png" alt="first" border="0" /></a>
		<a href="${scriptPath}?action=teststruct.list&page=${page-1}"><img src="${templatePath}/images/1leftarrow.png" alt="previous" border="0" /></a>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>

Trang ${page} / ${pageCount}

<c:choose>
	<c:when test="${page < pageCount}">
		<a href="${scriptPath}?action=teststruct.list&page=${page+1}"><img src="${templatePath}/images/1rightarrow.png" alt="next" border="0" /></a>
		<a href="${scriptPath}?action=teststruct.list&page=${pageCount}"><img src="${templatePath}/images/2rightarrow.png" alt="last" border="0" /></a>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>
</div>
