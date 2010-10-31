<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="navigator">
<c:choose>
    <c:when test="${page > 1}">
        <a href="${scriptPath}?action=user.list&page=1"><img src="${templatePath}/images/2leftarrow.png" alt="first" /></a>
        <a href="${scriptPath}?action=user.list&page=${page-1}"><img src="${templatePath}/images/1leftarrow.png" alt="previous" /></a>
    </c:when>
    <c:otherwise>
         
    </c:otherwise>
</c:choose>

Trang ${page} / ${pageCount}

<c:choose>
    <c:when test="${page < pageCount}">
        <a href="${scriptPath}?action=user.list&page=${page+1}"><img src="${templatePath}/images/1rightarrow.png" alt="next" /></a>
        <a href="${scriptPath}?action=user.list&page=${pageCount}"><img src="${templatePath}/images/2rightarrow.png" alt="last" /></a>
    </c:when>
    <c:otherwise>
        
    </c:otherwise>
</c:choose>
</div>
    