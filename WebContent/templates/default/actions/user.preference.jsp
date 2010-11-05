<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<p><strong>Your Current Avatar</strong></p>
<ocw:userAvatar user="${user}" />

<p><strong>Custom Avatar</strong></p>

<ocw:form action="user.preference" enctype="multipart/form-data" method="post">
    Choose Your Avatar:<br>
	<input type="file" name="file1"> <br>
	<input type="submit" name="submit" value="Upload">
	<ocw:error code="File Error"></ocw:error>
</ocw:form>
