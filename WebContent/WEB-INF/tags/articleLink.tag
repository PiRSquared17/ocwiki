<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="resource"
    description="Đối tượng bài viết (lớp Resource)" required="false" 
    rtexprvalue="true" type="org.ocwiki.data.Resource"%>
<%@ attribute name="revision"
    description="Đối tượng phiên bản bài viết (lớp Revision)" required="false" 
    rtexprvalue="true" type="org.ocwiki.data.Revision"%>
<%@ attribute name="onclick" description="Mã xử lí sự kiện nhấn chuột"
	required="false" type="java.lang.String"%>
<%@ attribute name="id" description="id"
	required="false" type="java.lang.String"%>
<%@ include file="/includes/common.jsp"%>

<jsp:doBody var="body" />
<c:choose>
    <c:when test="${not empty revision}">
        <c:choose>
            <c:when test="${config.usePrettyUrl}">
                <c:set var="url" value="${config.revisionPath}/${revision.id}"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="url" value="${config.actionPath}/article.viewold?id=${revision.id}"></c:set>
            </c:otherwise>
        </c:choose>
        <c:if test="${empty fn:trim(body)}">
            <c:set var="body" value="${revision.resource.name}"></c:set>
        </c:if>
    </c:when>
    <c:when test="${not empty resource}">
        <c:choose>
            <c:when test="${config.usePrettyUrl}">
	           <c:set var="url" value="${config.articlePath}/${resource.id}"></c:set>
	           <c:if test="${not empty resource.article.name}">
	               <c:set var="url" value="${url}-${resource.urlFriendlyName}"></c:set>
	           </c:if>
	           <c:set var="url" value="${url}.html"></c:set>
            </c:when>
            <c:otherwise>
               <c:set var="url" value="${config.actionPath}/article.view?id=${resource.id}"></c:set>
            </c:otherwise>
        </c:choose>
		<c:if test="${empty fn:trim(body)}">
		    <c:set var="body" value="${resource.name}"></c:set>
		</c:if>
    </c:when>
    <c:otherwise>
        <code>error! no resource or revision specified.</code>
    </c:otherwise>
</c:choose>

<a href="${url}" onclick="${onclick}" id="${id}">${fn:trim(body)}</a>