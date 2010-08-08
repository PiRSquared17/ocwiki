<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form action="${scriptPath}" method="get">
    <input type="hidden" name="action" value="test.editquestion"></input>
    <input type="hidden" name="teq_test" value="${param.teq_test}"></input>
    <input type="hidden" name="teq_section" value="${param.teq_section}"></input>
    <input type="hidden" name="teq_question" value="${question.id}"></input>
  
    <c:if test="${u:size(test.sections) != 0 && !(u:size(test.sections) == 1 && (empty test.sections[0].text)) }">
        <div>
           <label>Phần: 
           <c:set scope="page" var="index" value="${u:defaultIfNull(param.teq_section_new, section.id)}"></c:set>
           <select name="teq_section_new">
               <c:forEach items="${test.sections}" var="section">
                   <option value="${section.id}" ${section.id==index ? 'selected="selected"' : ''}>
                          ${section.order} - ${u:ellipsize(u:stripHTML(section.text), 50)}</option>
               </c:forEach>
           </select></label>
        </div>
    </c:if>
    <span class="error-validating">${action.sectionError}</span>
    
    <div>
    <div style="float: left">Nội dung:</div> ${question.content}
    </div>
     
    Danh sách câu trả lời:
	<div class="answer-list-wrapper">
	<c:forEach items="${question.answers}" var="answer">
	    <div class="answer-wrapper">
	       <div class="check-wrapper">
				<input type="checkbox" name="teq_answers" value="${answer.id}"
				     ${empty question.usedAnswerById[answer.id] ? '' : 'checked'}></input>
		   </div>
            <div class="answer-content-wrapper" ${answer.correct ? 'style="color:red"' : ''}>
	       		 ${answer.content}
            </div>  
		</div>
	</c:forEach>
	</div>
    <span class="error-validating">${action.answersError}</span>
	
	<div>
		<button type="submit" name="teq_submit" value="save">Lưu</button>
		<button type="button" onclick="location.href='${scriptPath}?action=test.view&tv_id=${param.teq_test}'">Quay về đề thi</button>
	</div>
</form>