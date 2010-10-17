<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

<form action="${scriptPath}" id="listForm">
<input type="hidden" name="action" value="testversion.list" />
<jsp:include page="testversion.list-toolbar.jsp"></jsp:include>

<table>
<tr>
	<th width="20px">&nbsp;</th>
	<th>Mã phiên bản</th>
</tr>
<c:forEach items="${testVersions}" var="testversion">
<tr>
	<td><input type="checkbox" name="tl_testversions" value="${testversion.id}"></td>
	<td><a href="${scriptPath}?action=testversion.view&testid=${test.id}&verid=${testversion.id}"> 
	       ${empty testversion.code ? '&lt;không tên&gt;' : testversion.code}</a>
	</td>
</tr>
</c:forEach>
<tr>
</tr>
</table>

</form>
