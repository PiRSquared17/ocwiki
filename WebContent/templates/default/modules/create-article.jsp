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
	resource = {
		articleType: 'oop.data.BaseQuestion',
		article: {
		    type: 'baseQuestionBean',
		    name: 'Câu hỏi mới',
		    namespace: {
		        id: 3
		    }
		} 
	};
	sendCreateRequest(resource);
}

function createSolution() {
	
}

function createTest() {
	
}

function createTextArticle() {
	
}

function createTestStructure() {
	
}

function sendCreateRequest(resource) {
    new Ajax.Request(restPath + '/resource',
    {
        method:'post',
        contentType: 'application/json',
        postBody: Object.toJSON(resource),
        requestHeaders : 
        {
            Accept : 'application/json'
        },
        evalJSON : true,
        onSuccess : function(transport) {
            var id = transport.responseJSON.result.id;
            location.href = actionPath + '/article.edit?id=' + id;
        },
        onFailure: function(transport) {
            var code = transport.responseJSON.code;
            if (code == 'login required') {
                mess = '<p>Bạn chưa đăng nhập hoặc phiên làm việc của bạn đã hết hạn.</p>' + 
                       '<p>Hãy <a href="' + actionPath + '/user.login' + '">đăng nhập</a>.</p>';
            	Dialog.info(mess, {
                	width:300, 
                	height:100, 
                	className: "alphacube"
                });
            }
        }
    });
}

var createArticleMenu = new Menu('create-article-menu-root', 'createArticleMenu', function() {
    this.closeDelayTime = 300;
});

//-->
</script>