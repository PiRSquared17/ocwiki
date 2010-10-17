<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<form id="question-form" method="post" 
        action="${config.restPath}/answer_attempts/${resource.id}">
<div>
    <ocw:parse resource="${resource}">${question.content}</ocw:parse>
    <div class="answer-list-wrapper">
    <c:set var="answerIndex" value="0"></c:set>
    <c:forEach items="${question.answers}" var="answer">
        <div class="answer-wrapper mouse-highlight">
            <div class="check-wrapper">
                <input type="radio" name="answers" value="${answer.id}" id="answer-${answer.id}">
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

function getResult(form, info) {
    form = $(form);
    info = $(info);
    form.request({
        requestHeaders : 
        {
            Accept : 'application/json'
        },
        evalJSON : true,
        onSuccess : function(transport) 
        {
            var result = transport.responseJSON.result;
            if (result.correct) {
                mess = 'Bạn đã làm chính xác';
                if (result.count == 1) {
                    mess += ' ngay lần trả lời đầu tiên.';
                } else {
                    mess += ' ở lần trả lời thứ ' + result.count + '.';
                }
                info.innerHTML = mess + ' Xin chúc mừng!!!';
            } else {
            	info.innerHTML = 'Sai rồi :-( Hãy thử lại.';
            }
        },
        onFailure: function(transport)
        { 
            DefaultTemplate.onFailure(transport);
        }
    });
}

//-->
</script>
