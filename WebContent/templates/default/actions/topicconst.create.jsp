<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

<form id="form_edit" action="${scriptPath}">

	<input type="hidden" name="action" value="topicconst.create"></input>
	<input type="hidden" name="teststruct" value="${test.id}"></input>
	
	<c:if test="${u:size(test.sectionStructures) != 0 && !(u:size(test.sectionStructures) == 1 && (empty test.sectionStructures[0].text)) }">
		<div>
		   <label>Phần: 
		   <select name="section">
		       <c:forEach items="${test.sectionStructures}" var="section">
		           <option value="${section.id}">${section.order} - ${section.text}</option>
		       </c:forEach>
		   </select></label>
		   <span class="error-validating">${action.sectionError}</span>
		</div>
        <br></br>
	</c:if>
	
	<p><label>Chủ đề:
	          <input type="text" id="txtTopicName" name="topicname" 
	                  value="${param.topicname}"
	                  onfocus="$('type-random').checked = 'checked'"></input>
		</label>
		<input type="hidden" id="txtTopicId" name="topicid" 
		         value="${param.topicid}"></input>
		<span class="error-validating">${action.topicError}</span>
	</p>
    <br></br>
	
	<p><label>Số lượng: 
			<input type="text" id="txtQuantity" name="quantity" 
				value="${(empty param.quantity) ? 1 : param.quantity}" 
				onfocus="$('type-random').checked = 'checked'"
				maxlength="3">
			</input></label>
        <span class="error-validating">${action.quantityError}</span>
	</p>
    <br></br>

	<button type="submit" name="submit" value="add">Lưu</button>
	<button type="button" onclick="location.href='${scriptPath}?action=teststruct.view&tsv_id=${test.id}'">Quay về cấu trúc đề</button>
	
</form>

<script>
	filterNumericKey('txtQuantity');

    new Autocomplete('txtTopicName', {
        serviceUrl : '${scriptPath}?action=topic.ajaxsearch&format=qcount',
        minChars : 2,
        maxHeight : 400,
        width : 300,
        deferRequestBy : 100,
        // callback function:
        onSelect : function(value, data) {
            $('txtTopicId').value = data;
        }
    });
</script>