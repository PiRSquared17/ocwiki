<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form action="${scriptPath}" method="get">

    <input type="hidden" name="action" value="answer.edit"></input>
    <input type="hidden" name="question" value="${question.id}"></input>
    <input type="hidden" name="answer" value="${param.answer}"></input>

    ${question.content}
	<div>
    <c:set var="i" value="0"></c:set>
        <div class="answer-list-wrapper">
		<c:forEach items="${question.answers}" var="answer">
			<div class="answer-wrapper">
		   <c:choose>
		      <c:when test="${answer.id == editing}">
		          <div class="check-wrapper">
		              <label><b>${u:alpha(i)}</b>. 
		              <input type="checkbox" name="correct" value="true"
		                  ${empty param.correct ? (answer.correct ? 'checked' : '') : 'checked' }></input>
		             </label> 
		          </div>
		          <textarea style="width:80%" name="content">${empty param.content ? answer.content : param.content}</textarea>
		          <div class="error-validating">${action.contentError}</div>
		      </c:when>
		      <c:otherwise>
		          <div class="answer-number-wrapper">
			         <b>${u:alpha(i)}</b>. 
			      </div>
			      <div ${answer.correct ? 'style="color:red"' : '' }>${answer.content}</div>
		      </c:otherwise>
		   </c:choose>
	        </div>
	       <c:set var="i" value="${i+1}"></c:set>
		</c:forEach>
		</div>
    </div>
	
	<button type="submit" name="submit" value="save">Lưu</button>
	<button type="button" onclick="location.href='${scriptPath}?action=question.view&qv_id=${question.id}'">Quay về câu hỏi</button>
</form>