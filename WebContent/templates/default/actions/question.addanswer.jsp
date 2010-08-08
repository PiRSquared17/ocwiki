<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>   
    
<form action="${scriptPath}">
	<input type="hidden" name="action" value="question.addanswer"></input>
	<input type="hidden" name="id" value="${question.id}"></input>
	<p>${question.content}</p>
	<ol>
	
		<li>
			<input type="checkbox" name="correct" value="true" ${empty param.correct ? '' : 'checked'}></input>
			<textarea name="answer">${param.answer}</textarea>
		</li>
	
	</ol>
	<button type="submit" name="qa_submit" value="add">Lưu</button>
	<button type="submit" name="qa_submit" value="addAndMore">Lưu và thêm nữa</button>
	<button type="button" onclick="location.href='${scriptPath}?action=question.view&qv_id=${question.id}'">Quay về câu hỏi</button>
</form>