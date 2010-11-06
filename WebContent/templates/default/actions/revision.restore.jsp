<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<c:set var="revision" value="${action.oldRevision}"></c:set>

<ocw:form action="revision.restore">
	<input type="hidden" name="revisionId" value="${revision.id}"></input>
	<input type="hidden" name="basever" value="${revision.resource.version}"></input>
	Bạn có muốn khôi phục lại phiên bản này trở về phiên bản của
	<br></br>
	<b>Tac giả:</b>${revision.author.name} 
	<br></br>
	<b>Ngày:</b> ${revision.timestamp} 
	<p></p>
	Sửa đổi nhỏ:<input type="text" name="sumary"></input>
	<input type="checkbox" name="minor"></input>
	<br></br>	
	<input type="submit" name="cofirm" value="Xác nhận"></input>
	
</ocw:form>