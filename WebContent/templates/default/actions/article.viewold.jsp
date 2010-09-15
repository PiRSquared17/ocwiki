<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<h1>${action.revision.qualifiedName}</h1>
<hr />
<table width="100%">
  <tr>
    <td width="150px" align="left">
        <c:choose>
            <c:when test="${empty action.previousRevision}">
	           &lt; phiên bản trước
	        </c:when>
	        <c:otherwise>
			    <ocw:articleLink revision="${action.previousRevision}">
			        &lt; phiên bản trước
			    </ocw:articleLink>
	        </c:otherwise>
        </c:choose>
    </td>
    <td align="center">
        Phiên bản lúc 
        <ocw:articleLink revision="${action.revision}">
	        ${u:formatDateTime(action.revision.timestamp)}
        </ocw:articleLink>
        do <ocw:userLink user="${action.revision.author}" /> sửa.
    </td>
    <td width="150px" align="right">
        <c:choose>
            <c:when test="${empty action.nextRevision}">
	            phiên bản sau &gt;
            </c:when>
            <c:otherwise>
		        <ocw:articleLink revision="${action.nextRevision}">
		            phiên bản sau &gt;
		        </ocw:articleLink>            
            </c:otherwise>
        </c:choose>
    </td>
  </tr>
  <tr>
    <td align="left">
        <c:choose>
            <c:when test="${empty action.previousRevision}">
			    (so sánh)
            </c:when>
            <c:otherwise>
			    (<ocw:actionLink name="article.diff">
				    <ocw:param name="from" value="${action.previousRevision.id}"></ocw:param>
				    <ocw:param name="to" value="${action.revision.id}"></ocw:param>
				    so sánh
				</ocw:actionLink>)
			</c:otherwise>
		</c:choose>
    </td>
    <td align="center">
        <ocw:articleLink resource="${action.resource}">đến phiên bản hiện tại</ocw:articleLink>
    </td>
    <td align="right">
        <c:choose>
            <c:when test="${empty action.nextRevision}">
	            (so sánh)
            </c:when>
            <c:otherwise>
		        (<ocw:actionLink name="article.diff">
		            <ocw:param name="from" value="${action.revision.id}"></ocw:param>
		            <ocw:param name="to" value="${action.nextRevision.id}"></ocw:param>
		            so sánh
		        </ocw:actionLink>)
		    </c:otherwise>
		</c:choose>
    </td>
  </tr>
</table>

<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

