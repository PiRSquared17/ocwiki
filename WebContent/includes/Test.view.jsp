<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="test" value="${empty article ? action.article : article}"></c:set>

<p><b>Thời gian làm bài:</b> ${test.time} phút</p>

<div class="test-description mouse-highlight">
    ${test.content}
</div>

<c:set var="type" value="${test.type=='radio' ? 'radio' : 'checkbox'}"></c:set>
<c:set var="i" value="1"></c:set>
<c:set var="sectionIndex" value="0"></c:set>

<c:forEach items="${test.sections}" var="section">
<div class="section">
    <div class="section-text-wrapper mouse-highlight">
        <c:if test="${not empty section.content.text}">
		  <div class="section-text">${section.content.text}</div>
	    </c:if>
    </div>

    <c:forEach items="${section.questions}" var="question">
        <div class="question-wrapper mouse-highlight">

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
