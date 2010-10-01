<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="question" value="${empty article ? action.article : article}"></c:set>

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

<ocw:topics article="${question}"/>
