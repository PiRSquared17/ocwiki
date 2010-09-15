<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<div>
    <div id="questionEdit-content">
        <textarea id="questionEdit-content-textarea" rows="10" cols="40" style="width: 100%">${question.content}</textarea>
        <script type="text/javascript">
		<!--
		Editor.edit('questionEdit-content');
		//-->
		</script>
    </div>
    <div class="answer-list-wrapper" id="questionEdit-answerList">
    <c:set var="answerIndex" value="0"></c:set>
    <c:forEach items="${question.answers}" var="answer">
        <div class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
            <div class="check-wrapper">
                <c:set var="correctId" value="questionEdit-answer${answerIndex}-correct"></c:set>
                <input type="checkbox" name="${question.id}-answers" value="${answer.id}" id="${correctId}"
                        ${answer.correct ? 'checked="checked"' : ''}>
            </div>
            <div style="margin-right: 60px">
                <c:set var="contentId" value="questionEdit-answer${answerIndex}-content"></c:set>
                <div id="${contentId}">
	                <textarea id="questionEdit-answer${answerIndex}-textarea" rows="10" cols="80" style="width: 100%">${fn:escapeXml(answer.content)}</textarea>
                </div>
			    <script type="text/javascript">
			    <!--
			    Editor.preview('${contentId}');
			    //-->
			    </script>
            </div>
        </div>
        <c:set var="answerIndex" value="${answerIndex+1}"></c:set>
    </c:forEach>
    </div>
    <button type="button" onclick="createNewAnswer()">Tạo lựa chọn mới</button>
</div>

<ocw:setJs var="template">
    <div id="questionEdit-answer\#{index}" class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
        <div class="check-wrapper">
            <input type="checkbox" id="questionEdit-answer\#{index}-correct">
        </div>
        <div style="margin-right: 60px">
            <div id="questionEdit-answer\#{index}-content">
                <textarea id="questionEdit-answer\#{index}-textarea" rows="10" cols="80" style="width: 100%">Lựa chọn mới</textarea>
            </div>
        </div>
    </div>
</ocw:setJs>

<script type="text/javascript">
<!--

newAnswerTemplate = new Template('${template}');

question = null;

new Ajax.Request(restPath + '/questions/' + resourceId,
{
  method:'get',
  requestHeaders : {
      Accept : 'application/json'
  },
  evalJSON : true,
  onSuccess : function(transport) {
      question = transport.responseJSON.result;
  },
  onFailure: function(){ }
});


EditAction = Class.create();

EditAction.preview = function() {
};
        
EditAction.save = function() {
	question.content = { text: tinymce.get('questionEdit-content-textarea').getContent() };
	for (i = 0; i < question.answers.length; i++) {
		correct = $('questionEdit-answer' + i + '-correct').checked; 
		contentStr = tinymce.get('questionEdit-answer' + i + '-textarea').getContent();
		if (question.answers[i].correct != correct ||
				question.answers[i].content.text != contentStr) {
			question.answers[i] = { 
				"content": { "text": contentStr }, 
				"correct": correct 
			};
		}
	}
	//alert(question.content);
    new Ajax.Request(restPath + '/questions/' + resourceId,
    {
      method:'post',
      requestHeaders : {
          Accept : 'application/json'
      },
      contentType: 'application/json',
      postBody: Object.toJSON({
          article: question,
          summary: $F('articleEdit-summary'),
          minor: $('articleEdit-minor').checked
      }),
      evalJSON: true,
      onSuccess: function(transport) {
          location.href = articlePath + '/' + resourceId;
      },
      onFailure: function(){ }
    });
    //alert('!');
};

function createNewAnswer() {
	var index = question.answers.length;
    var data = { "index": index };
    question.answers[index] = { };
    $('questionEdit-answerList').insert(newAnswerTemplate.evaluate(data));
    Editor.edit('questionEdit-answer' + index +'-content');
}

//-->
</script>
