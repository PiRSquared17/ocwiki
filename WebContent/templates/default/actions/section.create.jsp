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

<ocw:form action="section.create">

	<input type="hidden" name="test" value="${param.test}">
	
	<p><label>Nội dung: <textarea style="width:100%" name="text" rows="15" cols="80" style="width: 80%">${param.name}</textarea></label>
		<ocw:error code="name"></ocw:error>
	</p>
    <br></br>
	
	<p><label>Vị trí: 
		<select name="index">
		    <c:set var="sectionIndex" value="0" />
			<c:forEach items="${test.sections}" var="section">
				<option value="${sectionIndex}">
					${sectionIndex}: ${u:ellipsize(u:stripHTML(section.content.text), 50)}
				</option>
			    <c:set var="sectionIndex" value="${sectionIndex+1}" />
			</c:forEach>
			<option value="${sectionIndex}">
			     -- cuối cùng --
			</option>
		</select>
	</label></p>
    <br></br>

	<p>
		<button type="submit" name="submit" value="create">Tạo</button>
		<ocw:actionButton name="test.view">
		  <ocw:param name="id" value="${action.resource.id}"></ocw:param>
		  Quay về đề thi
		</ocw:actionButton>
	</p>
</ocw:form>