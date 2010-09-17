<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="article"
    description="Đối tượng bài viết (lớp BaseArticle)" required="true" 
    rtexprvalue="true" type="oop.data.BaseArticle"%>
<%@ attribute name="editable"
    description="Có hiện các nút sửa đổi hay không" required="false"%>
<%@ include file="/includes/common.jsp"%>

<c:if test="${u:size(article.topics) > 0}">
	<div class="topic-list">
	<span class="topic-list-title">Chủ đề</span>
	<c:forEach items="${article.topics}" var="topic">
	    <span class="topic-list-item">
	        <ocw:articleLink resource="${topic}" />
	    </span>
	</c:forEach>
	</div>
</c:if>
