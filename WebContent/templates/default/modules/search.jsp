<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="search" id="navSearch">
<div id="searchWrapper" class="wrapper">
	<span class="searchInput">
	    <span>
			<input id="searchInputText" type="text" name="search_query" value="${fn:escapeXml(param.search_query)}"></input>
			<button type="submit"></button>
		</span>
	</span>
</div>
</ocw:form>

<script type="text/javascript">
<!--
new TextboxDefaultHandler('searchInputText', 'Tìm kiếm');
//-->
</script>