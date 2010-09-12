<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
var alphabetArr = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
</script>

<form method="get" action="${scriptPath}">
    <jsp:include page="testversion.view-toolbar.jsp"></jsp:include>
	
	<div style="clear:both">
		<h3>${test.name}</h3>
		<p><b>Mô tả:</b> ${test.content}</p>
		<p><b>Thời gian:</b> ${test.time} phút</p>
		<p><b>Phiên bản:</b> ${version.code}</p>
	</div>


	<input type="hidden" name="action" value="test.solve">
	<input type="hidden" name="testId" value="${test.id}">
	<input type="hidden" name="versionId" value="${version.id}">
	
	<c:set var="i" value="0"></c:set>

	
	<c:forEach items="${test.sections}" var="section">
	   ${section.content.text}
	   
	   <c:set var="questionIds" value="${version.data.questionsOrder[section.id]}"></c:set>
	   
	   <c:forEach items="${questionIds}" var="questionId">
            
            <c:set var="j" value="0" />
            <c:set var="question" value="${section.questionById[questionId]}"></c:set>
		    <div>
		    	<div class="question-number-wrapper">
		    	     <b>Câu ${i+1}: </b>
		    	</div>
		        ${question.content}	        
		        
		        <c:set var="answerIds" value="${version.data.answersOrder[section.id][questionId]}"></c:set>
                <div class="answer-list-wrapper">
		        <c:forEach items="${answerIds}" var="answerId">
		            <c:set var="answer" value="${question.answerById[answerId]}"></c:set>
                    <div class="answer-wrapper">
                        <div class="answer-number-wrapper">
		                	<b>${u:alpha(j)}</b>. 
	                	</div>
		                ${answer.content}
		             </div>
					<c:set var="j" value="${j+1}" />
		        </c:forEach>
		        </div>
		    </div>
		    <c:set var="i" value="${i+1}"></c:set>
            	   
	   </c:forEach>
	
	</c:forEach>
<jsp:include page="testversion.view-toolbar.jsp"></jsp:include>	
</form>
