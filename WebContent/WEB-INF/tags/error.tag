<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="code" description="Mã lỗi" required="true"%>
<%@ include file="/includes/common.jsp"%>

<c:if test="${not empty action.errors[code]}">
    <span class="error-validating">${action.errors[code]}</span>
</c:if>