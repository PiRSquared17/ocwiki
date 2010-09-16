<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

<form id="form_edit" action="${scriptPath}">
	<input type="hidden" name="action" value="levelconst.create">
	<input type="hidden" name="teststruct" value="${test.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">
	
	<c:if test="${u:size(test.sectionStructures) != 0 && !(u:size(test.sectionStructures) == 1 && (empty test.sectionStructures[0].text)) }">
		<div>
		   <label>Phần: 
		   <select name="section">
		       <c:forEach items="${test.sectionStructures}" var="section">
		           <option value="${section.id}">${section.order} - ${section.content.text}</option>
		       </c:forEach>
		   </select></label>
           <ocw:error code="section"></ocw:error>
		</div>
	    <br></br>
	</c:if>
		
	<p><label>Độ khó: 
        <select name="level">
            <option value="1" ${param.level==1 ? 'selected' : ''}>${u:levelName(1)}</option>
            <option value="2" ${param.level==2 ? 'selected' : ''}>${u:levelName(2)}</option>
            <option value="3" ${(empty param.level) || param.level==3 ? 'selected' : ''}>${u:levelName(3)}</option>
            <option value="4" ${param.level==4 ? 'selected' : ''}>${u:levelName(4)}</option>
            <option value="5" ${param.level==5 ? 'selected' : ''}>${u:levelName(5)}</option>
        </select>
	    <ocw:error code="level"></ocw:error></label>
    </p>
    <br></br>

	<p><label>Số lượng: 
		<input type="text" id="txtQuantity" name="quantity" 
			value="${(empty param.quantity) ? 1 : param.quantity}" 
	              onfocus="$('type-random').checked = 'checked'"
	              maxlength="3">
			</label>
		<ocw:error code="quantity"></ocw:error>
	</p>
    <br></br>

	<button type="submit" name="submit" value="add">Lưu</button>
	<ocw:articleButton resource="${action.resource}">Quay về cấu trúc đề</ocw:articleButton>
</form>

<script>
	filterNumericKey('txtQuantity');
</script>