<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="test" value="${action.history.test}"></c:set>

<div>
<div>Thời điểm thực hiện: <strong>${u:formatDateTime(action.history.takenDate)}s</strong></div>
<div>Thời gian thực hiện: <strong>${action.history.time}s</strong></div>
<div>Điểm số: <strong>${action.history.mark}</strong></div>
</div>

<hr />

<div>
<div class="test-description mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
	<c:if test="${sessionScope.login && user.group=='teacher'}">
	    <div class="buttons">
	       <ocw:actionLink name="test.edit">
	           <ocw:param name="test" value="${action.resource.id}"></ocw:param>
	           <img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="16px" height="16px" />
	       </ocw:actionLink>
	  	</div>
	</c:if>
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
		<div class="section-text">
			${section.content.text}
		</div>
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
                    <c:set var="choices" value="${action.history.answerByQuestion[question.base].choices}"></c:set>
					<c:forEach items="${question.answers}" var="answer">
					   <div class="answer-wrapper">
                            <div class="check-wrapper">
					           <input type="checkbox" disabled="disabled" 
					                   ${u:contains(choices, answer) ? 'checked="checked"' : ''}>
					        </div> 
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

</div>