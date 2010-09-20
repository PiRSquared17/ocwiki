<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>


<%@page import="oop.data.Test"%><script type="text/javascript">
<!--

var allowedTime = ${action.test.time} * 60;
var timeLeft = allowedTime;
var questions = [<c:forEach items="${action.test.sections}" var="section">
	   <c:forEach items="${section.questions}" var="question">
	        ${question.id}, 
	   </c:forEach>
	</c:forEach>];

function validateDone() {
	return $('chkDone').checked;
}

function updateTime() {
	if (timeLeft <= 0) {
		$('chkDone').checked = true;
		$('btnSubmit').click();
		return;
	}

	$('lblMinute').innerHTML = Math.floor(timeLeft / 60);
	$('lblSecond').innerHTML = timeLeft % 60;
	$('txtTime').value = new String(allowedTime - timeLeft);

	timeLeft--;
	setTimeout('updateTime()', 1000);
}

function updateCount() {
	var count = questions.length;
	var form = $('solve-form');
	for (i = 0; i < questions.length; i++) {
		try {
			var elem = eval('form.q' + questions[i]);
			for (j = 0; j < elem.length; j++) {
				if (elem[j].checked) {
					count--;
					break;
				}
			}
		} catch (e) {
		}
	}
	$('lblQuestionCount').innerHTML = new String(count);
}

//-->
</script>

<form method="get" action="${scriptPath}" id="solve-form">
	<h3>${action.test.name}</h3>
	<p><b>Mô tả:</b> ${action.test.content}</p>
	<p><b>Thời gian:</b> ${action.test.time} phút</p>


	<input type="hidden" name="action" value="test.solve">
	<input type="hidden" name="testId" value="${action.test.id}">
	<input type="hidden" name="versionId" value="${action.version.id}">
	<input type="hidden" name="time" value="0" id="txtTime">
	
	<c:set var="i" value="0"></c:set>
	<c:set var="type" value='${action.test.type=="radio" ? "radio" : "checkbox"}'></c:set>
	
	<c:forEach items="${action.test.sections}" var="section">
	   ${section.content.text}
	   
	   <c:set var="questionIds" value="${action.version.data.questionsOrder[section.id]}"></c:set>
	   
	   <c:forEach items="${questionIds}" var="questionId">
            
            <c:set var="question" value="${section.questionById[questionId]}"></c:set>
		    <div>
		        <div class="question-number-wrapper">
		          <b>Câu ${i+1}</b>:
		        </div>
		        
		        <div class="question-content-wrapper">${question.content}</div>
		        
		        <div class="answer-list-wrapper">
			        <c:set var="answerIds" value="${action.version.data.answersOrder[section.id][questionId]}"></c:set>
			        <c:set var="j" value="0"></c:set>
			        <c:forEach items="${answerIds}" var="answerId">
			            <c:set var="answer" value="${question.answerById[answerId]}"></c:set>
			            <div class="answer-wrapper">
			                <div class="check-wrapper">
			                    <input type="${type}" onchange="updateCount()"
			                            name="q${question.id}" value="${answer.id}"
			                            id="q${question.id}-a${answer.id}">
			                </div>
			                <div class="answer-number-wrapper">
			                    <label for="q${question.id}-a${answer.id}"><b>${u:alpha(j)}</b>.</label>
			                </div>
			                <label for="q${question.id}-a${answer.id}">${answer.content}</label>
			            </div>
				        <c:set var="j" value="${j+1}"></c:set>
			        </c:forEach>
		        </div>
		    </div>
		    <c:set var="i" value="${i+1}"></c:set>
            	   
	   </c:forEach>
	
	</c:forEach>
	
	<label><input type="checkbox" id="chkDone">Xong</label>
	<button id="btnSubmit" name="submit" value="done" 
			onclick="return validateDone();">Gửi</button>
</form>

<div class="solve-status">
Số câu còn lại: <span class="value" id="lblQuestionCount"></span>
Thời gian còn lại: <span class="value"><span id="lblMinute"></span>:<span id="lblSecond"></span></span>
</div>


<script type="text/javascript">
<!--
   updateTime();
   updateCount();
//-->
</script>
