<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="answer.create">
    <input type="hidden" name="question" value="${action.resource.id}">

    ${action.question.content}
	<div>
        <c:set var="i" value="0"></c:set>
        <div class="answer-list-wrapper">        
			<c:forEach items="${action.question.answers}" var="answer">
		      <div class="answer-wrapper">
			      <div class="answer-number-wrapper">
			           <b>${u:alpha(i)}</b>.
			       </div>
			       <div ${answer.correct ? 'style="color:red"' : '' }>${answer.content}</div>
			       <c:set var="i" value="${i+1}"></c:set>
			   </div>
			</c:forEach>
			<div class="answer-wrapper">
			    <div class="check-wrapper">
		            <label><b>${u:alpha(i)}</b>. 
		            <input type="checkbox" name="correct" value="true" ${empty param.correct ? '' : 'checked' }> </label>
	            </div>
	            <textarea style="width:80%" name="content">${param.content}</textarea>
	            <div class="error-validating">${action.errors.content}</div>
	        </div>
		</div>
    </div>
	<button type="submit" name="submit" value="more">Thêm nữa</button>
	<button type="submit" name="submit" value="create">Lưu</button>
	<ocw:articleButton resource="${action.resource}">Quay về câu hỏi</ocw:articleButton>
</ocw:form>