<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div id="form_edit">
<form>
	<input type="hidden" name="action" value="test.create" />
	
	<p><label>Tên: 
		<input type="text" name="tc_name" value="${param.tc_name}"></input> 
		<c:if test="${!(empty nameErr)}">
			<span class="error-validating">${nameErr}</span>
		</c:if></label>
	</p>
	<br />
	<p>Mô tả: <textarea name="tc_description">${param.tc_description}</textarea>
		<c:if test="${!(empty descriptionErr)}">
			<span class="error-validating">${descriptionErr}</span>
		</c:if>
	</p>
	<br />
	<p>Kiểu:
	   <select name="tc_type">
	       <option value="radio">Một câu đúng</option>
	       <option value="check">Nhiều câu đúng</option>
	   </select>
		<span class="error-validating">${action.typeError}</span>
	</p>
	<br />
	<p><label>Thời gian
		<input type="text" id="txtTime" name="tc_time" 
				value='${(empty param.tc_time)?"90":param.tc_time}'
				maxlength="3"></input> phút
    	</label>
		<span class="error-validating">${action.timeError}</span>
	</p>
	<br />
	
	<fieldset>
	   <legend>
	       <label><input type="checkbox" name="tc_usestruct" value="true" style="float: none"
                    ${param.tc_usestruct ? 'checked' : ''}></input>
	       Sinh tự động</label>
	   </legend>

		<p>
		    <label>
		        Chọn cấu trúc đề:
			    <input type="text" id="txtTestStructName" 
			            name="tc_structname" value="${param.tc_structname}"></input>
			    <input type="hidden" id="txtTestStructId" 
			            name="tc_struct" value="${param.tc_struct}"></input>
			</label>
			<span class="error-validating">${action.structError}</span>
		</p>
	</fieldset>
	
    <br />
	<p>
		<button type="submit" name="tc_submit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scriptPath}?action=test.list'">Quay về danh sách đề</button>
	</p>
</form>
</div>
<script type="text/javascript">
<!--
filterNumericKey('txtTime');

new Autocomplete('txtTestStructName', {
    serviceUrl : '${scriptPath}?action=teststruct.ajaxsearch',
    minChars : 2,
    maxHeight : 400,
    width : 300,
    deferRequestBy : 100,
    autoSelect: true,
    // callback function:
    onSelect : function(value, data) {
        $('txtTestStructId').value = data;
    }
});
//-->
</script>