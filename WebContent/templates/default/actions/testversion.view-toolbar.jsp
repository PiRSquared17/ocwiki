<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
	<button type="button" onclick="location.href='${scriptPath}?action=test.savedocx&testid=${test.id}&verid=${version.id}'">Xuất</button>
	<button type="button" onclick="location.href='${scriptPath}?action=testversion.list&testid=${test.id}'">Trở lại các phiên bản</button>
</div>
