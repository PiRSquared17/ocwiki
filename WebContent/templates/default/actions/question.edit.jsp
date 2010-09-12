<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set scope="page" var="question" value="${action.question}"></c:set>

<ocw:form action="question.edit">
    <input type="hidden" name="id" value="${action.resource.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">
    
    <div>
	    <label>Câu hỏi: 
	        <textarea style="width:100%" name="content">${u:defaultIfNull(param.content, question.content)}</textarea></label>
	        <ocw:error code="content"></ocw:error>
    </div>
    
    <br /><br />
    <p><label>Độ khó: 
        <c:set scope="page" var="level" value="${u:defaultIfNull(param.level, question.level)}"></c:set>
        <select name="level">
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
    
	<button type="submit" name="submit" value="save">Lưu</button>
	<ocw:articleButton resource="${action.resource}">Thôi</ocw:articleButton>
</ocw:form>