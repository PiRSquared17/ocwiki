<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/META-INF/ocwiki.tld" prefix="ocw" %>

<c:if test="${empty config}">
    <jsp:forward page="/dont-access-jsp-directly.html"></jsp:forward>
</c:if>