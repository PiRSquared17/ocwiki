<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="/includes/common.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Không tìm thấy tài nguyên - ${config.siteName}</title>
	<link rel="stylesheet" href="${config.homeDir}/css/error.css" type="text/css" />
	<script type="text/javascript" src="${config.homeDir}/js/prototype.js"></script>
	<script type="text/javascript" src="${config.homeDir}/js/error.js"></script>
</head>
<body>
<div id="content">
<table>
    <tr>
        <td id="messageBoard" valign="middle"">
        <h1>Rất tiếc, hệ thống không tìm thấy tài nguyên mà bạn cần.</h1>
        <p><label for="searchInputText">Hãy thử dùng công cụ tìm kiếm của chúng tôi:</label></p>
        <ocw:form action="search" id="navSearch">
		<div id="searchWrapper" class="wrapper">
		    <span class="searchInput">
		        <span>
		            <input id="searchInputText" type="text" name="search_query" 
		                  value="${fn:escapeXml(param.search_query)}"></input>
		            <button type="submit"></button>
		        </span>
		    </span>
		</div>
		<p>Hoặc <a href="mailto:${config.contactEmail}" class="contactLink">liên
		hệ với ban quản trị</a> để được trợ giúp.</p>
	</ocw:form>
        </td>
    </tr>
</table>
</div>

<script type="text/javascript">
<!--
new TextboxDefaultHandler('searchInputText', 'Tìm kiếm');
//-->
</script>
</body>
</html>