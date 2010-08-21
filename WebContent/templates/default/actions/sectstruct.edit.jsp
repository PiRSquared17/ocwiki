<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('sname');
	var msgName = $('msgName');

	if (txtName.value == '') {
		msgName.innerText = 'Bạn cần nhập tên.';
		msgName.style.display = 'block';
		return false;
	}
	
	msgName.style.display = 'none';
	return true;
}

</script>

<form id="form_edit" action="${scriptPath}" method="get">

    <input type="hidden" name="action" value="sectstruct.edit">
    <input type="hidden" name="sid" value="${section.id}">
	    
	<p><label>Nội dung: <textarea name="stext" style="width: 100%">${(empty param.stext) ? section.text : param.stext}</textarea></label>
		<ocw:error code="name"></ocw:error>
	</p>
    <br></br>

	<p><label>Chèn vào trước: 
		<select name="sorder" >
			<c:forEach items="${test.sectionStructures}" var="item">
				<c:if test="${item != section}">
					<option value="${item.order}" 
					       ${item.order == (empty param.sorder ? section.order : param.sorder) ? 'selected="selected"' : '' }>
						${item.order} - ${u:ellipsize(item.text, 45)}
					</option>
				</c:if>
                <c:set var="last" value="${item.order+1}"></c:set>
			</c:forEach>
	        <option value="${last}" ${'last' == (empty param.sorder ? section.order : param.sorder) ? 'selected="selected"' : '' }>-- cuối cùng --</option>
		</select>
	</label></p>
	<br></br>
	
	<p>
		<button type="submit" name="ssubmit" value="update">Lưu</button>
		<ocw:articleButton resource="${action.resource}">Quay về cấu trúc đề</ocw:articleButton>
	</p>

</form>