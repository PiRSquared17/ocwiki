<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form action="${scriptPath}" method="get">

    <input type="hidden" name="action" value="user.editgroup">
    <input type="hidden" name="user" value="${action.editedUser.id}">
    
    <h3>
	Người dùng: ${action.editedUser.name}
	</h3> 
	
	<fieldset>
	<legend>Thông tin</legend>
	<p>
	Tên đầy đủ: ${action.editedUser.fullname} 
	</p>
	<p>
	Ngày đăng kí: ${u:formatDateTime(action.editedUser.registerDate)}
	</p>
	<p>
	Email: ${action.editedUser.email}
	</p>
	<p>
	<label>Nhóm: <input type="text" name="group" value="${empty param.group ? action.editedUser.group : param.group}"></label>
	<ocw:error code="group"></ocw:error>
	</p>
	</fieldset>
	
	<button type="submit" name="submit" value="save">Lưu</button>
	<button type="button" onclick="location.href='${scriptPath}?action=user.profile&user=${action.editedUser.id}'">Quay về hồ sơ</button>

</form>