<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="search">
<input type="text" name="search_query" value="${fn:escapeXml(param.search_query)}"></input>
<button type="submit">Tìm kiếm</button>
</ocw:form>
<hr />

<c:choose>
    <c:when test="${u:size(action.results) == 0}">
        Không tìm thấy kết quả nào.
    </c:when>
    <c:otherwise>
        <ocw:pagination actionName="search?search_query=${action.query}" 
                count="${action.count}" currentStart="${action.start}">
        </ocw:pagination>
		<c:forEach var="resource" items="${action.results}">
			<div>
			<ocw:articleLink resource="${resource}"></ocw:articleLink>
			</div>
		</c:forEach>
    </c:otherwise>
</c:choose>