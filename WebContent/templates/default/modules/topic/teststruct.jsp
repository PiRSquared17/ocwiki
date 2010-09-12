<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ul>
<c:forEach items="${module.testStructures}" var="testStructure">
    <li><ocw:articleLink resource="${testStructure}"></ocw:articleLink></li>
</c:forEach>
</ul>