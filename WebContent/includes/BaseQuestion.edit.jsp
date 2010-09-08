<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<script type="text/javascript">
<!--

var question = null;

new Ajax.Request(restPath + '/questions/' + getArticleId(),
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

//-->
</script>


<div>
    <div id="questionEdit-content">
        <textarea id="questionEdit-content-textarea">${question.content}</textarea>
    </div>
    <div class="answer-list-wrapper">
    <c:forEach items="${question.answers}" var="answer">
        <div class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
            <div class="check-wrapper">
                <c:set var="correctId" value="questionEdit-answer${answer.id}-correct"></c:set>
                <input type="radio" name="${question.id}-answers" value="${answer.id}" id="${correctId}"
                        ${answer.correct ? 'checked="checked"' : ''}>
            </div>
            <div style="margin-right: 60px">
                <c:set var="contentId" value="questionEdit-answer${answer.id}-content"></c:set>
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
    </c:forEach>
    </div>
</div>

<ocw:topics article="${question}"/>

