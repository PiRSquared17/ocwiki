<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="answer.edit">
    <input type="hidden" name="question" value="${action.resource.id}">
    <input type="hidden" name="answer" value="${param.answer}">
    <input type="hidden" name="basever" value="${action.resource.version}">

    ${question.content}
	<div>
    <c:set var="i" value="0"></c:set>
        <div class="answer-list-wrapper">
		<c:forEach items="${question.answers}" var="answer">
			<div class="answer-wrapper">
		   <c:choose>
		      <c:when test="${i == param.answer}">
		          <div class="check-wrapper">
		              <label><b>${u:alpha(i)}</b>. 
		              <input type="checkbox" name="correct" value="true"
		                  ${empty param.correct ? (answer.correct ? 'checked' : '') : 'checked' }>
		             </label> 
		          </div>
		          <textarea style="width:80%" name="content">${answer.content}</textarea>
		          <ocw:error code="content"></ocw:error>
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
	<ocw:articleButton resource="${action.resource}">Quay về câu hỏi</ocw:articleButton>
</ocw:form>