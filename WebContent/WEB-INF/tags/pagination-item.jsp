<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:actionLink name="${actionName}">
    <ocw:param name="start" value="${(i-1)*pageSize}"/>
    ${i}
</ocw:actionLink>