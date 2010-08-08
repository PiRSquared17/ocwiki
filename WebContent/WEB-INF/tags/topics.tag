<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="article"
    description="Đối tượng bài viết (lớp BaseArticle)" required="true"
    type="oop.data.BaseArticle"%>
<%@ attribute name="editable"
    description="Có hiện các nút sửa đổi hay không" required="false"
    type="java.lang.Boolean"%>
<%@ include file="/includes/common.jsp"%>

<div class="topic-list">
<div class="topic-list-title">Chủ đề</div>
<c:forEach items="${article.topics}" var="topic">
    <div class="topic-list-item">
        ${topic.name}
        <c:if test="${editable}">
	        <div class="topic-list-delete-button" onclick="alert('add')">&nbsp;</div>
        </c:if>
    </div>
    <c:if test="${editable}">
        <div class="topic-list-add-button" onclick="alert('add')">&nbsp;</div>
    </c:if>
</c:forEach>
</div>