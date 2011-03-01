<%@ page contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<jsp:include page="article.view-${fn:toLowerCase(action.resource.status)}.jsp"></jsp:include>