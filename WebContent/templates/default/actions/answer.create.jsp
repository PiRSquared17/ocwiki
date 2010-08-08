<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form action="${ocw:actionUrl('answer.create')}" method="get">

    <input type="hidden" name="question" value="${question.id}"></input>

    ${question.content}
	<div>
        <c:set var="i" value="0"></c:set>
        <div class="answer-list-wrapper">        
			<c:forEach items="${question.answers}" var="answer">
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
		            <input type="checkbox" name="correct" value="true" ${empty param.correct ? '' : 'checked' }></input> </label>
	            </div>
	            <textarea style="width:80%" name="content">${param.content}</textarea>
	            <div class="error-validating">${action.contentError}</div>
	        </div>
		</div>
    </div>
	<button type="submit" name="submit" value="more">Thêm nữa</button>
	<button type="submit" name="submit" value="create">Lưu</button>
	<button type="button" onclick="location.href='${ocw:actionUrl('question.view')}?qv_id=${question.id}'">Quay về câu hỏi</button>
</form>