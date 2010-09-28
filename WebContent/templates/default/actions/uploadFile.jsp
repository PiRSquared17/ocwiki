<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<p><strong>Upload Your File</strong></p>

<ocw:form action="uploadFile">
    Choose Your File:<br>
	<input type="file" name="file1" size="10*1024*1024"> <br>
	<input type="submit" name="submit" value="Upload">
	<ocw:error code="File Error"></ocw:error>
</ocw:form>


