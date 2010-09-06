<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>


Đối với các phiên bản dưới đây, click vào ngày tạo để xem.

<div><label><strong>Chọn số phiên bản hiển thị
trên một trang :</strong> <select id="selectItemsPerPage" name="Items per page">
	<option value='10' selected="selected">10</option>
	<option value='20'>20</option>
	<option value='30'>30</option>
	<option value='40'>40</option>
	<option value='50'>50</option>
	<option value='100'>100</option>
</select> </label></div>
<form action="${scriptPath}" id="listForm"><input type="hidden"
	name="action" value="revision.list" /> <jsp:include
	page="revision.list-toolbar.jsp"></jsp:include> <jsp:include
	page="revision.list-nav.jsp"></jsp:include>
</form>
<ul>
    <c:forEach items="${action.revisions}" var="revision">
		<li>
		    (
		    <c:choose>
		      <c:when test="${revision.id==action.latestRevision.id}">
		          h.tại
		      </c:when>
		      <c:otherwise>
			    <ocw:actionLink name="article.diff">
				    <ocw:param name="from" value="${revision.id}"></ocw:param>
				    <ocw:param name="to" value="curr"></ocw:param>
				    h.tại
				</ocw:actionLink> 
		      </c:otherwise>
		    </c:choose>
		     | 
		     <c:choose>
		          <c:when test="${revision.id == action.earliestRevision.id}">
		              trước
		          </c:when>
		          <c:otherwise>
		            <ocw:actionLink name="article.diff">
		                <ocw:param name="from" value="prev"></ocw:param>
		                <ocw:param name="to" value="${revision.id}"></ocw:param>
		                trước
		            </ocw:actionLink>
		          </c:otherwise>
		     </c:choose>
		    )
		    <input type="checkbox" name="revid" value="${revision.id}">
	        <ocw:articleLink revision="${revision}">${u:formatDateTime(revision.timestamp)}</ocw:articleLink>
			<ocw:userLink user="${revision.author}" />
			(${revision.summary})
		</li>
	</c:forEach>
</ul>
<jsp:include page="revision.list-nav.jsp"></jsp:include>

<jsp:include page="revision.list-toolbar.jsp"></jsp:include>
