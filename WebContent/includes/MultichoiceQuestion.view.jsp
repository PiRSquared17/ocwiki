<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

<form id="question-form" method="post" 
        action="${config.restPath}/q_attempts/${resource.id}">
<div>
    <ocw:parse resource="${resource}">${question.content}</ocw:parse>
    <div class="choice-list-wrapper">
    <c:set var="choiceIndex" value="0"></c:set>
    <c:forEach items="${question.choices}" var="choice">
        <div class="choice-wrapper mouse-highlight">
            <div class="check-wrapper">
                <input type="radio" name="choices" value="${choice.id}" id="choice-${choice.id}">
            </div>
            <div style="margin-right: 60px">
                 <label for="choice-${choice.id}">
                     <ocw:parse resource="${resource}">${choice.content}</ocw:parse>
                 </label>
            </div>
        </div>
        <c:set var="choiceIndex" value="${choiceIndex+1}"></c:set>
    </c:forEach>
    </div>
</div>
<button type="button" onclick="getResult('question-form', 'question-choice-result')">Trả lời</button>
<span id="question-choice-result"></span>
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
            template.onFailure(transport);
        }
    });
}

//-->
</script>
