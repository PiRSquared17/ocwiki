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

<ocw:form action="sectstruct.create">
	<input type="hidden" name="tstr" value="${test.id}">
	
	<p><label>Nội dung: <textarea style="width:100%" name="stext">${param.sname}</textarea></label>
		<ocw:error code="name"></ocw:error>
	</p>
    <br></br>
	
	<p><label>Thứ tự: 
		<c:set var="last" value="0"></c:set>e
		<select name="sindex">
			<c:forEach items="${test.sectionStructures}" var="section">
				<option value="${section.order}">
					${section.order}: ${isection.text}
				</option>
			    <c:set var="last" value="${item.order+1}"></c:set>
			</c:forEach>
			<option value="${last}"
		      	     ${(empty param.sindex) || param.sindex=='last' ? 'selected="selected"' : ''}>
		         -- cuối cùng --
			</option>
		</select>
	</label></p>
    <br></br>

	<p>
		<button type="submit" name="ssubmit" value="create">Tạo</button>
		<ocw:articleButton resource="${action.resource}">Quay về cấu trúc đề</ocw:articleButton>
	</p>
</ocw:form>