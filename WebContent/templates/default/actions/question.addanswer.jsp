<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>   
    
<form action="${scriptPath}">
	<input type="hidden" name="action" value="question.addanswer">
	<input type="hidden" name="id" value="${question.id}">
	<p>${question.content}</p>
	<ol>
	
		<li>
			<input type="checkbox" name="correct" value="true" ${empty param.correct ? '' : 'checked'}>
			<textarea name="answer">${param.answer}</textarea>
		</li>
	
	</ol>
	<button type="submit" name="submit" value="add">Lưu</button>
	<button type="submit" name="submit" value="addAndMore">Lưu và thêm nữa</button>
	<ocw:articleButton resource="${action.resource}">Quay về câu hỏi</ocw:articleButton>
</form>