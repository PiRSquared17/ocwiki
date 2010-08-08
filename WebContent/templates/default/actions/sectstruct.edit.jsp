<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('sse_name');
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

    <input type="hidden" name="action" value="sectstruct.edit"></input>
    <input type="hidden" name="sse_id" value="${section.id}"></input>
	    
	<p><label>Nội dung: <textarea name="sse_text" style="width: 100%">${(empty param.sse_text) ? section.text : param.sse_text}</textarea></label>
		<span id="msgName" style='${(empty nameErr) ? "none" : "block"}'>${nameErr}</span>
	</p>
    <br></br>

	<p><label>Chèn vào trước: 
		<select name="sse_order" >
			<c:forEach items="${test.sectionStructures}" var="item">
				<c:if test="${item != section}">
					<option value="${item.order}" 
					       ${item.order == (empty param.sse_order ? section.order : param.sse_order) ? 'selected="selected"' : '' }>
						${item.order} - ${u:ellipsize(item.text, 45)}
					</option>
				</c:if>
                <c:set var="last" value="${item.order+1}"></c:set>
			</c:forEach>
	        <option value="${last}" ${'last' == (empty param.sse_order ? section.order : param.sse_order) ? 'selected="selected"' : '' }>-- cuối cùng --</option>
		</select>
	</label></p>
	<br></br>
	
	<p>
		<button type="submit" name="sse_submit" value="update">Lưu</button>
		<button type="button" onclick="location.href='${scriptPath}?action=teststruct.view&tsv_id=${test.id}'">Quay về cấu trúc đề</button>
	</p>

</form>