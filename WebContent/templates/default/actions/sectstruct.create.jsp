<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('ssc_name');
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

	<input type="hidden" name="action" value="sectstruct.create"></input>
	<input type="hidden" name="ssc_testid" value="${test.id}"></input>
	
	<p><label>Nội dung: <textarea style="width:100%" name="ssc_text">${param.ssc_name}</textarea></label>
		<span id="msgName" style='${"block"}'>${nameErr}</span>
	</p>
    <br></br>
	
	<p><label>Thứ tự: 
		<c:set var="last" value="0"></c:set>e
		<select name="ssc_order">
			<c:forEach items="${test.sectionStructures}" var="section">
				<option value="${section.order}">
					${section.order}: ${isection.text}
				</option>
			    <c:set var="last" value="${item.order+1}"></c:set>
			</c:forEach>
			<option value="${last}"
		      	     ${(empty param.ssc_order) || param.ssc_order=='last' ? 'selected="selected"' : ''}>
		         -- cuối cùng --
			</option>
		</select>
	</label></p>
    <br></br>

	<p>
		<button type="submit" name="ssc_submit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scriptPath}?action=teststruct.view&tsv_id=${test.id}'">Quay về cấu trúc đề</button>
	</p>

</form>