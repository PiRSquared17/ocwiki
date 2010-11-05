<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<p><strong>Your Current Avatar</strong></p>
<c:choose>
	<c:when test="${empty user.avatar}">
		<img src="${homeDir}/images/default.png" width="30%" height="30%" />
		<br>
	</c:when>
	<c:otherwise>
		<img src="${config.uploadPath}/avatar/${user.avatar}" width="30%"
			height="30%" />
		<br>
	</c:otherwise>
</c:choose>

<p><strong>Custom Avatar</strong></p>

<ocw:form action="user.preference" enctype="multipart/form-data" method="post">
    Choose Your Avatar:<br>
	<input type="file" name="file1"> <br>
	<input type="submit" name="submit" value="Upload">
	<ocw:error code="File Error"></ocw:error>
</ocw:form>
