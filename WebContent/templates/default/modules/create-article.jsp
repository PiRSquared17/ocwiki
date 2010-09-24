<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<div class="jsmenu" id="create-article-menu">
<ul class="level1 horizontal" id="create-article-menu-root">
    <li class="level1">Tạo mới
    <ul class="level2 dropdown">
        <li><a href="#" onclick="createQuestion()">Câu hỏi</a></li>
        <li><a href="#" onclick="createSolution()">Bài giải</a></li>
        <li><a href="#" onclick="createTest()">Đề thi</a></li>
        <li><a href="#" onclick="createTextArticle()">Bài giảng</a></li>
        <li><a href="#" onclick="createTestStructure()">Cấu trúc đề</a></li>
    </ul>
    </li>
</ul>
</div>

<script type="text/javascript">
<!--
function createQuestion() {
	
}

function createSolution() {
	
}

function createTest() {
	
}

function createTextArticle() {
	
}

function createTestStructure() {
	
}

var createArticleMenu = new Menu('create-article-menu-root', 'createArticleMenu', function() {
    this.closeDelayTime = 300;
});

//-->
</script>