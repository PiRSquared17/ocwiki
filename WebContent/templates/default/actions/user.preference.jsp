<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<fieldset>
<legend><strong>Your Current Avatar</strong></legend>
<ocw:userAvatar user="${user}" />
</fieldset>
<fieldset>
<legend><strong>Custom Avatar</strong></legend>
<ocw:form action="user.preference" enctype="multipart/form-data" method="post">
    Choose Your Avatar:<br>
	<input type="file" name="file1"> <br>
	<input type="submit" name="submit" value="Upload"><br>
	Dung lượng tối đa : 2MB<br>
	<ocw:error code="File Error"></ocw:error>
</ocw:form>
</fieldset>