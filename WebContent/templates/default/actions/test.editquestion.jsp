<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form action="${scriptPath}" method="get">
    <input type="hidden" name="action" value="test.editquestion">
    <input type="hidden" name="test" value="${param.test}">
    <input type="hidden" name="section" value="${param.section}">
    <input type="hidden" name="question" value="${question.id}">
  
    <c:if test="${u:size(test.sections) != 0 && !(u:size(test.sections) == 1 && (empty test.sections[0].text)) }">
        <div>
           <label>Phần: 
           <c:set scope="page" var="index" value="${u:defaultIfNull(param.section_new, section.id)}"></c:set>
           <select name="section_new">
               <c:forEach items="${test.sections}" var="section">
                   <option value="${section.id}" ${section.id==index ? 'selected="selected"' : ''}>
                          ${section.order} - ${u:ellipsize(u:stripHTML(section.text), 50)}</option>
               </c:forEach>
           </select></label>
        </div>
    </c:if>
    <ocw:error code="section"></ocw:error>
    
    <div>
    <div style="float: left">Nội dung:</div> ${question.content}
    </div>
     
    Danh sách câu trả lời:
	<div class="answer-list-wrapper">
	<c:forEach items="${question.answers}" var="answer">
	    <div class="answer-wrapper">
	       <div class="check-wrapper">
				<input type="checkbox" name="answers" value="${answer.id}"
				     ${empty question.usedAnswerById[answer.id] ? '' : 'checked'}>
		   </div>
            <div class="answer-content-wrapper" ${answer.correct ? 'style="color:red"' : ''}>
	       		 ${answer.content}
            </div>  
		</div>
	</c:forEach>
	</div>
    <ocw:error code="answers"></ocw:error>
	
	<div>
		<button type="submit" name="submit" value="save">Lưu</button>
		<ocw:articleButton resource="${action.resource}">Quay về đề thi</ocw:articleButton>
	</div>
</form>