<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<form id="question-form" method="post" 
        action="${config.restPath}/user_answers/${resource.id}">
<div>
    <ocw:parse resource="${resource}">${question.content}</ocw:parse>
    <div class="answer-list-wrapper">
    <c:set var="answerIndex" value="0"></c:set>
    <c:forEach items="${question.answers}" var="answer">
        <div class="answer-wrapper mouse-out"
                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
            <div class="check-wrapper">
                <input type="radio" name="${question.id}-answers" value="${answer.id}" id="answer-${answer.id}">
            </div>
            <div class="marker-wrapper">
                <span id="a${answer.id}-rightanswer" style="display:none;"><img src="${templatePath}/images/right.png" alt="right answer" width="16px" height="16px" /></span>
                <span id="a${answer.id}-wronganswer" style="display:none;"><img src="${templatePath}/images/wrong.png" alt="wrong answer" width="16px" height="16px" /></span>
            </div>

            <div style="margin-right: 60px">
                 <label for="answer-${answer.id}">
                     <ocw:parse resource="${resource}">${answer.content}</ocw:parse>
                 </label>
            </div>
        </div>
        <c:set var="answerIndex" value="${answerIndex+1}"></c:set>
    </c:forEach>
    </div>
</div>
<button type="button" onclick="getResult('question-form', 'question-answer-result')">Trả lời</button>
<span id="question-answer-result"></span>
</form>

<ocw:topics article="${question}"/>

<script type="text/javascript">
<!--

function getResult(form, result) {
    form = $(form);
    result = $(result);
    form.request({
        requestHeaders : 
        {
            Accept : 'application/json'
        },
        evalJSON : true,
        onSuccess : function(transport) 
        {
            if (transport.result) {
                result.innerHTML = 'Bạn đã làm chính xác, chúc mừng!!!';
            } else {
            	result.innerHTML = 'Sai rồi :-( Hãy thử lại.';
            }                
        },
        onFailure: function(transport)
        { 
            if (transport.responseJSON.code == 'login required') {
                OcwikiDefaultTemplate.promptLogin();
            } else {
                alert('Có lỗi xảy ra, xin lỗi vì sự bất tiện!');
            }  
        }
    });
}

//-->
</script>
