<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set scope="page" var="question" value="${action.question}"></c:set>

<form id="form_edit" action="${scriptPath}" method="get">
    
    <input type="hidden" name="action" value="question.edit">
    <input type="hidden" name="qe_id" value="${question.id}">
    
    <div>
	    <label>Câu hỏi: 
	        <textarea style="width:100%" name="qe_content">${u:defaultIfNull(param.qe_content, question.content)}</textarea></label>
	        <ocw:error code="content"></ocw:error>
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
        <ocw:error code="level"></ocw:error>
    </p>
    <br />
    
	<button type="submit" name="qe_submit" value="save">Lưu</button>
	<ocw:articleButton resource="${action.resource}">Thôi</ocw:articleButton>
</form>
