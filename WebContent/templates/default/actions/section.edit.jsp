<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('name');
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

<ocw:form action="section.edit">
    <input type="hidden" name="id" value="${section.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">
	    
	<p><label>Nội dung: <textarea style="width:100%" name="text">${(empty param.text) ? section.content.text : param.text}</textarea></label>
		<ocw:error code="name"></ocw:error>
	</p>
    <br></br>

	<p><label>Chèn vào trước: 
		<select name="order" >
			<c:forEach items="${test.sections}" var="item">
				<c:if test="${item != section}">
					<option value="${item.order}" 
					       ${item.order == (empty param.order ? section.order : param.order) ? 'selected' : '' }>
						${item.order} - ${u:ellipsize(u:stripHTML(item.text), 50)}
					</option>
				</c:if>
			</c:forEach>
	        <option value="last">-- cuối cùng --</option>
		</select>
	</label></p>
    <br></br>

	<p>
		<button type="submit" name="submit" value="update">Lưu</button>
		<ocw:articleButton resource="${action.resource}">Quay về đề thi</ocw:articleButton>
	</p>
</ocw:form>