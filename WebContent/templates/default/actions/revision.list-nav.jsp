<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="navigator">
<c:choose>
	<c:when test="${action.page > 1}">
				
		<ocw:actionLink name="revision.list">
			<ocw:param name="resourceID" value="${action.resourceID}"></ocw:param>
			<ocw:param name="page" value="1"></ocw:param>
			<ocw:param name="size" value="${action.size}"></ocw:param>
			<img src="${templatePath}/images/2leftarrow.png" alt="first" />
		</ocw:actionLink>
		
		<ocw:actionLink name="revision.list">
			<ocw:param name="resourceID" value="${action.resourceID}"></ocw:param>
			<ocw:param name="page" value="${action.page-1}"></ocw:param>
			<ocw:param name="size" value="${action.size}"></ocw:param>
			<img src="${templatePath}/images/1leftarrow.png" alt="previous" />
		</ocw:actionLink>

	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>

Trang ${action.page} / ${action.pageCount}

<c:choose>
	<c:when test="${action.page < action.pageCount}">
				
		<ocw:actionLink name="revision.list">
			<ocw:param name="resourceID" value="${action.resourceID}"></ocw:param>
			<ocw:param name="page" value="${action.page+1}"></ocw:param>
			<ocw:param name="size" value="${action.size}"></ocw:param>
			<img src="${templatePath}/images/1rightarrow.png" alt="next" />
		</ocw:actionLink>
		
		<ocw:actionLink name="revision.list">
			<ocw:param name="resourceID" value="${action.resourceID}"></ocw:param>
			<ocw:param name="page" value="${action.pageCount}"></ocw:param>
			<ocw:param name="size" value="${action.size}"></ocw:param>
			<img src="${templatePath}/images/2rightarrow.png" alt="last" />
		</ocw:actionLink>

	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>
</div>
