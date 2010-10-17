<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="search">
<input type="text" name="search_query" value="${param.search_query}"></input>
<button type="submit">Tìm kiếm</button>
</ocw:form>