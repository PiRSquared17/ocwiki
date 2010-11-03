<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>
<div style="float: right;">
    [<ocw:actionLink name="revision.list">
	    <ocw:param name="resourceID" value="${action.resource.id}"></ocw:param>
	    lịch sử
	</ocw:actionLink>]
	[<ocw:actionLink name="article.edit">
	    <ocw:param name="id" value="${action.resource.id}"></ocw:param>
	    sửa
	</ocw:actionLink>]
	<c:if test="${sessionScope.user.group == 'admin'}">
	   [<ocw:actionLink name="article.delete" onclick="markResourceDeleted(); return false;">
	       <ocw:param name="id" value="${action.resource.id}"></ocw:param>
	       xoá
	   </ocw:actionLink>]
	</c:if>
	<c:choose>
	    <c:when test="${ocw:assignableFrom('oop.data.BaseQuestion', type.name)}">
	        [<a href="#">bài giải</a>]
	    </c:when>
	    <c:when test="${ocw:assignableFrom('oop.data.Test', type.name)}">
	        [<ocw:actionLink name="test.solve">
			    <ocw:param name="testId" value="${action.resource.id}"></ocw:param>
			    làm
			</ocw:actionLink>]
	    </c:when>
    </c:choose>
    [<ocw:actionLink name="article.print" target="_blank">
	    <ocw:param name="id" value="${action.resource.id}"></ocw:param>
	    in
	</ocw:actionLink>]
</div>

<h1>${action.resource.qualifiedName}</h1>
<hr />
<br>
<p><jsp:include page="article.view-edittool.jsp"></jsp:include></p>
<br>
<ocw:userLink user="${action.resource.author}" /> tạo lúc 
${u:formatDateTime(action.resource.createDate)}

<div class="clear"></div>
<br>
<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

<div class="clear"></div>

<p><jsp:include page="article.view-comments.jsp"></jsp:include></p>

<script type="text/javascript">
<!--
function markResourceDeleted() {
	
}
//-->
</script>
