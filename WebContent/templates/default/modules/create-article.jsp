<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<div class="jsmenu" id="create-article-menu">
<ul class="level1 horizontal" id="create-article-menu-root">
    <li class="level1">Tạo mới
    <ul class="level2 dropdown">
        <li><a href="#" onclick="createQuestion(); return false">Câu hỏi</a></li>
        <c:if test="${u:assignableFrom('org.ocwiki.data.BaseQuestion', action.resource.type)}">
	        <li><a href="#" onclick="createSolution(resource.id); return false">Bài giải</a></li>
        </c:if>
        <li><a href="#" onclick="createTest(); return false">Đề thi</a></li>
        <li><a href="#" onclick="createTextArticle(); return false">Bài viết</a></li>
        <!-- <li><a href="#" onclick="createTestStructure(); return false">Cấu trúc đề</a></li> -->
        <li><a href="#" onclick="createTopic(); return false">Chủ đề</a></li>
        <li><a href="#" onclick="createFile(); return false">Tập tin</a></li>
    </ul>
    </li>
</ul>
</div>

<script type="text/javascript">
<!--

var createArticleMenu = new Menu('create-article-menu-root', 'createArticleMenu', function() {
    this.closeDelayTime = 300;
});

//-->
</script>