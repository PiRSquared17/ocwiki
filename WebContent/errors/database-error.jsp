<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lỗi cơ sở dữ liệu - ${config.siteName}</title>
    <link rel="stylesheet" href="${config.homeDir}/css/error.css" type="text/css" />
</head>
<body>
<div id="content">
<table>
    <tr>
        <td id="messageBoard" valign="middle"">
           <h1>Hệ thống gặp lỗi khi tương tác với cơ sở dữ liệu</h1>
           <p>Xin hãy <a href="mailto:${config.contactEmail}?subject=${u:urlEncode('Lỗi cơ sở dữ liệu')}&body=${u:urlEncode(requestScope['javax.servlet.error.exception'])}" 
                class="contactLink">
                thông báo ban quản trị</a> để lỗi được khắc phục sớm nhất.</p>
           <p class="errorDetail">Chi tiết lỗi:
           <code>${requestScope['javax.servlet.error.exception']}</code></p>
           <p>Xin lỗi về sự bất tiện!</p>
        </td>
    </tr>
</table>
</div>
</body>
</html>