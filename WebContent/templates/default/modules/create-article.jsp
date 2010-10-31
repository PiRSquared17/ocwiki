<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<div class="jsmenu" id="create-article-menu">
<ul class="level1 horizontal" id="create-article-menu-root">
    <li class="level1">Tạo mới
    <ul class="level2 dropdown">
        <li><a href="#" onclick="createQuestion(); return false">Câu hỏi</a></li>
        <c:if test="${ocw:assignableFrom('oop.data.BaseQuestion', action.resource.type.name)}">
	        <li><a href="#" onclick="createSolution(); return false">Bài giải</a></li>
        </c:if>
        <li><a href="#" onclick="createTest(); return false">Đề thi</a></li>
        <li><a href="#" onclick="createTextArticle(); return false">Bài giảng</a></li>
        <li><a href="#" onclick="createTestStructure(); return false">Cấu trúc đề</a></li>
        <li><a href="#" onclick="createTopic(); return false">Chủ đề</a></li>
        <li><a href="#" onclick="createFile(); return false">Tập tin</a></li>
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
    resource = {
            articleType: 'oop.data.Solution',
            article: {
            	//question: {
            	//	id: ${article.resource.id}
            	//},
                type: 'solutionBean',
                name: 'Bài giải mới',
                namespace: {
                    id: 0
                }
            } 
        };
        sendCreateRequest(resource);
}

function createTest() {
    resource = {
        articleType: 'oop.data.Test',
        article: {
            type: 'testBean',
            name: 'Đề thi mới',
            namespace: {
                id: 4
            }
        } 
    };
    sendCreateRequest(resource);
}

function createFile() {
    resource = {
        articleType: 'oop.data.File',
        article: {
            type: 'fileBean',
            name: 'Tập tin mới',
            namespace: {
                id: 0
            }
        } 
    };
    sendCreateRequest(resource);
}

function createTextArticle() {
    resource = {
        articleType: 'oop.data.TextArticle',
        article: {
            type: 'textArticleBean',
            name: 'Bài viết mới',
            namespace: {
                id: 0
            }
        } 
    };
    sendCreateRequest(resource);
}

function createTestStructure() {
    resource = {
        articleType: 'oop.data.TestStructure',
        article: {
            type: 'testStructureBean',
            name: 'Cấu trúc đề mới',
            namespace: {
                id: 5
            }
        } 
    };
    sendCreateRequest(resource);
}

function createTopic() {
    resource = {
        articleType: 'oop.data.Topic',
        article: {
            type: 'topicBean',
            name: 'Chủ đề mới',
            namespace: {
                id: 2
            }
        } 
    };
    sendCreateRequest(resource);
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
            DefaultTemplate.onFailure(transport); 
        }
    });
}

var createArticleMenu = new Menu('create-article-menu-root', 'createArticleMenu', function() {
    this.closeDelayTime = 300;
});

//-->
</script>