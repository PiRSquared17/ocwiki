<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<p><strong>Your Current Avatar</strong></p>
<c:choose>
	<c:when test="${empty user.avatar}">
		<img src="${homeDir}/images/default.png" width="100" height="100" />
		<br>
	</c:when>
	<c:otherwise>
		<img src="${homeDir}/images/avatar/${user.avatar}" width="100"
			height="100" />
		<br>
	</c:otherwise>
</c:choose>

<p><strong>Custom Avatar</strong></p>

<form action="${ocw:actionUrl(@'user.preference')}" method="post"
    	enctype="multipart/form-data">
    Choose Your Avatar:<br>
	<input type="file" name="file1"> <br>
	<input type="submit" name="submit" value="Upload">
</form>

