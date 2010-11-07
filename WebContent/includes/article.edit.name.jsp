<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<span id="articleEdit-name">
    <input id="articleEdit-nameInput" type="text" name="name" value="${fn:escapeXml(article.name)}"></input>
</span>

<script type="text/javascript">
<!--
Editor.preview('articleEdit-name');
//-->
</script>