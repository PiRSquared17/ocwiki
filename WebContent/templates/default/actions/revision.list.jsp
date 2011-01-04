<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<h3>Đối với các phiên bản dưới đây, click vào ngày tạo để xem.</h3>
<strong>Chọn số phiên bản hiển thị trên một trang :</strong><br/>
	<ocw:actionLink name="revision.list">
		<ocw:param name="r" value="${action.resourceId}"></ocw:param>
		<ocw:param name="s" value="0"></ocw:param>
		<ocw:param name="z" value="10"></ocw:param>
		10
	</ocw:actionLink>
		<ocw:actionLink name="revision.list">
		<ocw:param name="r" value="${action.resourceId}"></ocw:param>
		<ocw:param name="s" value="0"></ocw:param>
		<ocw:param name="z" value="20"></ocw:param>
		20
	</ocw:actionLink>
		<ocw:actionLink name="revision.list">
		<ocw:param name="r" value="${action.resourceId}"></ocw:param>
		<ocw:param name="s" value="0"></ocw:param>
		<ocw:param name="z" value="30"></ocw:param>
		30
	</ocw:actionLink>
		<ocw:actionLink name="revision.list">
		<ocw:param name="r" value="${action.resourceId}"></ocw:param>
		<ocw:param name="s" value="0"></ocw:param>
		<ocw:param name="z" value="40"></ocw:param>
		40
	</ocw:actionLink>
		<ocw:actionLink name="revision.list">
		<ocw:param name="r" value="${action.resourceId}"></ocw:param>
		<ocw:param name="s" value="0"></ocw:param>
		<ocw:param name="z" value="50"></ocw:param>
		50
	</ocw:actionLink>
		<ocw:actionLink name="revision.list">
		<ocw:param name="r" value="${action.resourceId}"></ocw:param>
		<ocw:param name="s" value="0"></ocw:param>
		<ocw:param name="z" value="100"></ocw:param>
		100
	</ocw:actionLink>

<ocw:pagination actionName="${action.descriptor.name}"
        count="${action.count}" currentStart="${action.start}" pageSize="${action.size}"
        additionalParams="resourceID=${action.resourceId}"></ocw:pagination>
        
<ul>
    <c:forEach items="${action.revisions}" var="revision">
		<li>
		    (<u:trim><c:choose>
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
		     </c:choose></u:trim>
		     | 
		     <u:trim><c:choose>
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
		     </c:choose></u:trim>) 
		    <input type="checkbox" name="revid" value="${revision.id}">
	        <ocw:articleLink revision="${revision}">${u:formatDateTime(revision.timestamp)}</ocw:articleLink>
			<ocw:userLink user="${revision.author}" />
			<c:if test="${not empty revision.summary}">
				(${revision.summary})
			</c:if>
			<ocw:actionLink name="revision.restore">
				<ocw:param name="revisionId" value="${revision.id}"></ocw:param>
				Lùi
			</ocw:actionLink>
		</li>
	</c:forEach>
</ul>
