<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<h1>${action.resource.qualifiedName}</h1>
<hr />

<div class="clear"></div>
<br>
<c:set var="type" value="${action.resource.type}"></c:set>
<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

<hr />
<ocw:userLink user="${action.resource.author}" /> tạo lúc 
${u:formatDateTime(action.resource.createDate)}
