<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set scope="page" var="question" value="${action.question}"></c:set>

<form id="form_edit" action="${scriptPath}" method="get">
    
    <input type="hidden" name="action" value="question.edit"></input>
    <input type="hidden" name="qe_id" value="${question.id}"></input>
    
    <div>
	    <label>Câu hỏi: 
	        <textarea style="width:100%" name="qe_content">${u:defaultIfNull(param.qe_content, question.content)}</textarea></label>
	        <span class="error-validating">${action.contentError}</span>
    </div>
    
    <br /><br />
    <p><label>Độ khó: 
        <c:set scope="page" var="level" value="${u:defaultIfNull(param.qe_level, question.level)}"></c:set>
        <select name="qe_level">
            <option value="1" ${level==1 ? 'selected' : ''}>${u:levelName(1)}</option>
            <option value="2" ${level==2 ? 'selected' : ''}>${u:levelName(2)}</option>
            <option value="3" ${level==3 ? 'selected' : ''}>${u:levelName(3)}</option>
            <option value="4" ${level==4 ? 'selected' : ''}>${u:levelName(4)}</option>
            <option value="5" ${level==5 ? 'selected' : ''}>${u:levelName(5)}</option>
        </select>
        </label>
        <span class="error-validating">${action.levelError}</span>
    </p>
    <br />
    
	<button type="submit" name="qe_submit" value="save">Lưu</button>
	<button type="button" onclick="location.href='${scriptPath}?action=question.view&qv_id=${question.id}'">Thôi</button>
</form>
