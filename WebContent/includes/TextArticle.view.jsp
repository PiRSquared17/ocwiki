<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="article" value="${empty article ? action.article : article}"></c:set>

<h3>bắt đầu TextArticle</h3>
${article.content.text}
<h3>kết thúc TextArticle</h3>