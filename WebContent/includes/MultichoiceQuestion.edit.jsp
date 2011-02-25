<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<h3>Sửa ${question.namespace.name}:<jsp:include page="/includes/article.edit.name.jsp"></jsp:include></h3>

<div>
    <div id="question-content">
        <textarea id="question-content-textarea" rows="10" cols="40" style="width: 100%">${question.content}</textarea>
        <script type="text/javascript">
		<!--
		Editor.edit('question-content');
		//-->
		</script>
    </div>
    <div class="choice-list-wrapper" id="choiceList">
    <c:set var="choiceIndex" value="0"></c:set>
    <c:forEach items="${question.choices}" var="choice">
        <div id="choice${choiceIndex}" class="choice-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
	                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
			<div class="buttons">
			  <img src="${templatePath}/images/wrong.png" onclick="deleteAnswer(${choiceIndex});"
				     alt="edit" title="remove" width="16px" height="16px" />
			</div>
			<div class="check-wrapper">
	                <c:set var="correctId" value="choice${choiceIndex}-correct"></c:set>
	                <input type="checkbox" name="${question.id}-choices" value="${choice.id}" id="${correctId}"
	                        ${choice.correct ? 'checked="checked"' : ''}>
            </div>
            <div style="margin-right: 60px">
                <c:set var="contentId" value="choice${choiceIndex}-content"></c:set>
                <div id="${contentId}">
	                <textarea id="choice${choiceIndex}-textarea" rows="10" cols="80" style="width: 100%">${fn:escapeXml(choice.content)}</textarea>
                </div>
			    <script type="text/javascript">
			    <!--
			    Editor.preview('${contentId}');
			    //-->
			    </script>
            </div>
        </div>
        <c:set var="choiceIndex" value="${choiceIndex+1}"></c:set>
    </c:forEach>
    </div>
    <button type="button" onclick="createNewAnswer()">Tạo lựa chọn mới</button>
</div>

<ocw:setJs templateVar="newAnswerTemplate">
    <div id="choice\#{index}" class="choice-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
        <div class="check-wrapper">
            <input type="checkbox" id="choice\#{index}-correct">
        </div>
        <div style="margin-right: 60px">
            <div id="choice\#{index}-content">
                <textarea id="choice\#{index}-textarea" rows="10" cols="80" style="width: 100%">Lựa chọn mới</textarea>
            </div>
        </div>
    </div>
</ocw:setJs>

<ocw:setJs templateVar="deletedTemplate">
    <div id="choice\#{index}-deleted" style="text-align: center;">
        Lựa chọn đã bị xoá. 
        <a href="#" onclick="undeleteAnswer(\#{index}); return false;">Phục hồi</a>
    </div>
</ocw:setJs>

<script type="text/javascript">
<!--

question = resource.article;
if (!question.choices) {
	question.choices = new Array();
}

function getQuestionContent() {
	return tinymce.get('question-content-textarea').getContent();
}

function getAnswerContent(i) {
	return tinymce.get('choice' + i + '-textarea').getContent();
}

EditAction = Class.create();

EditAction.preview = function() {
};

EditAction.save = function(successCallback, failureCallback) {
	// sửa nội dung câu hỏi
	question.name = $F('articleEdit-nameInput');
	question.content = { text: getQuestionContent() };
	if (question.choices) {
		if (question.choices.length == 0) {
			delete question.choices;
		} else {
			var newAnswers = new Array();
			for (i = 0, j=0; i < question.choices.length; i++) {
				if (!question.choices[i].deleted) {
					correct = $('choice' + i + '-correct').checked; 
					contentStr = getAnswerContent(i);
					if (question.choices[i].correct != correct ||
							question.choices[i].content.text != contentStr) {
						question.choices[i] = { 
							"content": { "text": contentStr }, 
							"correct": correct 
						};
					}
					newAnswers[j++] = question.choices[i];
				}
			}
			question.choices = newAnswers;
		}
	}
	// gửi lên server
    WebService.post('/questions/' + resourceId, {
      data: {
          article: question,
          summary: $F('articleEdit-summary'),
          minor: $('articleEdit-minor').checked
      },
      onSuccess: successCallback,
      onFailure: function(transport) {
    	  var code = transport.responseJSON.code;
          if (code == 'old version') {
              alert('old version');
          } else if (code == 'question content is blank') {
              $('articleEdit-error').innerHTML = 'Hãy điền nội dung câu hỏi';
          } else if (code == 'too little choices') {
              $('articleEdit-error').innerHTML = 'Quá ít lựa chọn, tối thiểu là 2.';
          } else if (code == 'too many choices') {
              $('articleEdit-error').innerHTML = 'Quá nhiều lựa chọn, tối đa là 9.';
          } else if (code == 'choice content is blank') {
              $('articleEdit-error').innerHTML = 'Hãy điền nội dung tất cả các lựa chọn.';
          } else if (code == 'no correct choice') {
              $('articleEdit-error').innerHTML = 'Hãy chọn ít nhất một lựa chọn đúng.';
          } else {
        	  template.onFailure(transport); 
          }
          failureCallback();
      }
    });
};

function createNewAnswer() {
	if (!question.choices) {
		question.choices = new Array();
	}
	var index = question.choices.length;
	var indexPrev = index - 1;
    var data = { "index": index };
    question.choices[index] = { };
    $('choiceList').insert(newAnswerTemplate.evaluate(data));
    Editor.edit('choice' + index +'-content');
}

function deleteAnswer(index) {
	var div = $('choice' + index);
	var data = { "index": index };
	div.insert({ after: deletedTemplate.evaluate(data) });
	div.hide();
	question.choices[index].deleted = true;
}

function undeleteAnswer(index) {
    var div = $('choice' + index);
    var deleted = $('choice' + index + '-deleted');
    div.show();
    deleted.remove();
    question.choices[index].deleted = false;
}

//-->
</script>
