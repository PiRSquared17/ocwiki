<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<div>
    <div id="questionEdit-content">
        <textarea id="questionEdit-content-textarea">${question.content}</textarea>
        <script type="text/javascript">
		<!--
		Editor.edit('questionEdit-content');
		//-->
		</script>
    </div>
    <div class="answer-list-wrapper">
    <c:set var="answerIndex" value="0"></c:set>
    <c:forEach items="${question.answers}" var="answer">
        <div class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
            <div class="check-wrapper">
                <c:set var="correctId" value="questionEdit-answer${answerIndex}-correct"></c:set>
                <input type="radio" name="${question.id}-answers" value="${answer.id}" id="${correctId}"
                        ${answer.correct ? 'checked="checked"' : ''}>
            </div>
            <div style="margin-right: 60px">
                <c:set var="contentId" value="questionEdit-answer${answerIndex}-content"></c:set>
                <div id="${contentId}">
	                <textarea rows="" cols="">${fn:escapeXml(answer.content)}</textarea>
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
</div>

<script type="text/javascript">
<!--

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
          checked: $F('articleEdit-minor')
      }),
      evalJSON: true,
      onSuccess: function(transport) {
          location.href = articlePath + '/' + resourceId;
      },
      onFailure: function(){ }
    });
};

//-->
</script>
