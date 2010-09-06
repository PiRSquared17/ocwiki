<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="test" value="${empty article ? action.article : article}"></c:set>

<div class="test-description mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
    <h3>${test.name}</h3>
    <p><b>Mô tả:</b> ${test.content}</p>
    <p><b>Thời gian:</b> ${test.time} phút</p>
</div>

<c:set var="type" value="${test.type=='radio' ? 'radio' : 'checkbox'}"></c:set>
<c:set var="i" value="1"></c:set>
<c:set var="sectionIndex" value="0"></c:set>

<c:forEach items="${test.sections}" var="section">
<div class="section">
    <div class="section-text-wrapper mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
        <c:if test="${not empty section.content.text}">
		  <div class="section-text">${section.content.text}</div>
	    </c:if>
    </div>

    <c:forEach items="${section.questions}" var="question">
        <div class="question-wrapper mouse-out" 
              onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
              onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">

            <div class="question">
                <div class="question-number-wrapper">
                    <b><ocw:articleLink resource="${question.baseContainer}">Câu ${i}</ocw:articleLink></b> (${question.mark} điểm):
                </div>
                <div class="question-content-wrapper">${question.content}</div>
                <div>
                    <c:set var="j" value="0" />
                    <div class="answer-list-wrapper">
                    <c:forEach items="${question.answers}" var="answer">
                       <div class="answer-wrapper">
                            <div class="number-wrapper">
                               <b>${u:alpha(j)}</b>.
                            </div>
                            <div>${answer.content}</div>
                            <c:set var="j" value="${j+1}" />
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <c:set var="i" value="${i+1}"></c:set>
    </c:forEach>
</div>
<c:set var="sectionIndex" value="${sectionIndex+1}"></c:set>
</c:forEach>

<ocw:topics article="${test}"></ocw:topics>
