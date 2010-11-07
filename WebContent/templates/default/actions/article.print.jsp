<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<h1>${action.resource.qualifiedName}</h1>
<hr />
<ocw:userLink user="${action.resource.author}" /> tạo lúc 
${u:formatDateTime(action.resource.createDate)}

<div class="clear"></div>
<br>
<c:set var="type" value="${action.resource.type}"></c:set>
<jsp:include page="/includes/${type.simpleName}.print.jsp"></jsp:include>
