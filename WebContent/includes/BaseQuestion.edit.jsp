<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<div>
    <div id="question-content">
        <textarea id="question-content-textarea" rows="10" cols="40" style="width: 100%">${question.content}</textarea>
        <script type="text/javascript">
		<!--
		Editor.edit('question-content');
		//-->
		</script>
    </div>
    <div class="answer-list-wrapper" id="answerList">
    <c:set var="answerIndex" value="0"></c:set>
    <c:forEach items="${question.answers}" var="answer">
        <div id="answer${answerIndex}" class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
	                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
			<div class="buttons">
			  <img src="${templatePath}/images/wrong.png" onclick="deleteAnswer(${answerIndex});"
				     alt="edit" title="remove" width="16px" height="16px" />
			</div>
			<div class="check-wrapper">
	                <c:set var="correctId" value="answer${answerIndex}-correct"></c:set>
	                <input type="checkbox" name="${question.id}-answers" value="${answer.id}" id="${correctId}"
	                        ${answer.correct ? 'checked="checked"' : ''}>
            </div>
            <div style="margin-right: 60px">
                <c:set var="contentId" value="answer${answerIndex}-content"></c:set>
                <div id="${contentId}">
	                <textarea id="answer${answerIndex}-textarea" rows="10" cols="80" style="width: 100%">${fn:escapeXml(answer.content)}</textarea>
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

<ocw:setJs var="newTemplate">
    <div id="answer\#{index}" class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
        <div class="check-wrapper">
            <input type="checkbox" id="answer\#{index}-correct">
        </div>
        <div style="margin-right: 60px">
            <div id="answer\#{index}-content">
                <textarea id="answer\#{index}-textarea" rows="10" cols="80" style="width: 100%">Lựa chọn mới</textarea>
            </div>
        </div>
    </div>
</ocw:setJs>

<ocw:setJs var="deletedTemplate">
    <div id="answer\#{index}-deleted" style="text-align: center;">
        Lựa chọn đã bị xoá. 
        <a href="#" onclick="undeleteAnswer(\#{index}); return false;">Phục hồi</a>
    </div>
</ocw:setJs>

<script type="text/javascript">
<!--

newAnswerTemplate = new Template('${newTemplate}');
deletedTemplate = new Template('${deletedTemplate}');

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

function getQuestionContent() {
	return tinymce.get('question-content-textarea').getContent();
}

function getAnswerContent(i) {
	return tinymce.get('answer' + i + '-textarea').getContent();
}

EditAction = Class.create();

EditAction.preview = function() {
};

EditAction.save = function() {
	// sửa nội dung câu hỏi
	question.content = { text: getQuestionContent() };
	var newAnswers = new Array();
	for (i = 0, j=0; i < question.answers.length; i++) {
		if (!question.answers[i].deleted) {
			correct = $('answer' + i + '-correct').checked; 
			contentStr = getAnswerContent(i);
			if (question.answers[i].correct != correct ||
					question.answers[i].content.text != contentStr) {
				question.answers[i] = { 
					"content": { "text": contentStr }, 
					"correct": correct 
				};
			}
			newAnswers[j++] = question.answers[i];
		}
	}
	question.answers = newAnswers;
	// gửi lên server
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
      onFailure: function(transport) {
    	  var code = transport.responseJSON.code;
          if (code == 'old version') {
              alert('old version');
          } else if (code == 'question content is blank') {
              $('articleEdit-error').innerHTML = 'Hãy điền nội dung câu hỏi';
          } else if (code == 'too little answers') {
              $('articleEdit-error').innerHTML = 'Quá ít lựa chọn, tối thiểu là 2.';
          } else if (code == 'too many answers') {
              $('articleEdit-error').innerHTML = 'Quá nhiều lựa chọn, tối đa là 9.';
          } else if (code == 'answer content is blank') {
              $('articleEdit-error').innerHTML = 'Hãy điền nội dung tất cả các lựa chọn.';
          } else if (code == 'no correct answer') {
              $('articleEdit-error').innerHTML = 'Hãy chọn ít nhất một lựa chọn đúng.';
          } else {
        	  $('articleEdit-error').innerHTML = 'Lỗi không rõ: ' + code;
          }
      }
    });
};

function createNewAnswer() {
	var index = question.answers.length;
    var data = { "index": index };
    question.answers[index] = { };
    $('answerList').insert(newAnswerTemplate.evaluate(data));
    Editor.edit('answer' + index +'-content');
}

function deleteAnswer(index) {
	var div = $('answer' + index);
	var data = { "index": index };
	div.insert({ after: deletedTemplate.evaluate(data) });
	div.hide();
	question.answers[index].deleted = true;
}

function undeleteAnswer(index) {
    var div = $('answer' + index);
    var deleted = $('answer' + index + '-deleted');
    div.show();
    deleted.remove();
    question.answers[index].deleted = false;
}

//-->
</script>
