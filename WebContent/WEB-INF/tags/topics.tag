<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="article"
    description="Đối tượng bài viết (lớp BaseArticle)" required="true" 
    rtexprvalue="true" type="oop.data.BaseArticle"%>
<%@ attribute name="editable"
    description="Có hiện các nút sửa đổi hay không" required="false"%>
<%@ include file="/includes/common.jsp"%>

<c:if test="${u:size(article.topics) > 0}">
	<div class="topic-list">
	<div class="topic-list-title">Chủ đề</div>
	<c:forEach items="${article.topics}" var="topic">
	    <div class="topic-list-item">
	        <ocw:articleLink resource="topic" />
	        <c:if test="${editable}">
		        <div class="topic-list-delete-button" onclick="alert('add')">&nbsp;</div>
	        </c:if>
	    </div>
	    <c:if test="${editable}">
	        <div class="topic-list-add-button" onclick="alert('add')">&nbsp;</div>
	    </c:if>
	</c:forEach>
	</div>
</c:if>
