<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('sc_name');
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

	<input type="hidden" name="action" value="section.create"></input>
	<input type="hidden" name="sc_testid" value="${test.id}"></input>
	
	<p><label>Nội dung: <textarea style="width:100%" name="sc_text" rows="15" cols="80" style="width: 80%">${param.sc_name}</textarea></label>
		<span id="msgName" style='${"block"}'>${nameErr}</span>
	</p>
    <br></br>
	
	<p><label>Chèn vào trước: 
		<select name="sc_order">
			<c:forEach items="${test.sections}" var="item">
				<option value="${item.order}" 
				        ${item.order==param.sc_order ? 'selected="selected"' : ''}>
					${item.order}: ${u:ellipsize(u:stripHTML(item.text), 50)}
				</option>
			</c:forEach>
			<option value="last"
			         ${(empty param.sc_order) || param.sc_order=='last' ? 'selected="selected"' : ''}>
			     -- cuối cùng --
			</option>
		</select>
	</label></p>
    <br></br>

	<p>
		<button type="submit" name="sc_submit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scriptPath}?action=test.view&tv_id=${test.id}'">Quay về đề thi</button>
	</p>

</form>