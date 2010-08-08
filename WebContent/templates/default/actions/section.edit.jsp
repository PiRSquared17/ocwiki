<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('se_name');
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

    <input type="hidden" name="action" value="section.edit"></input>
    <input type="hidden" name="se_id" value="${section.id}"></input>
	    
	<p><label>Nội dung: <textarea style="width:100%" name="se_text">${(empty param.se_text) ? section.text : param.se_text}</textarea></label>
		<span id="msgName" style='${(empty nameErr) ? "none" : "block"}'>${nameErr}</span>
	</p>
    <br></br>

	<p><label>Chèn vào trước: 
		<select name="se_order" >
			<c:forEach items="${test.sections}" var="item">
				<c:if test="${item != section}">
					<option value="${item.order}" 
					       ${item.order == (empty param.se_order ? section.order : param.se_order) ? 'selected' : '' }>
						${item.order} - ${u:ellipsize(u:stripHTML(item.text), 50)}
					</option>
				</c:if>
			</c:forEach>
	        <option value="last">-- cuối cùng --</option>
		</select>
	</label></p>
    <br></br>

	<p>
		<button type="submit" name="se_submit" value="update">Lưu</button>
		<button type="button" onclick="location.href='${scriptPath}?action=test.view&tv_id=${test.id}'">Quay về đề thi</button>
	</p>

</form>