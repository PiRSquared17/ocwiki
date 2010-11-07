<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lỗi nội bộ - ${config.siteName}</title>
	<link rel="stylesheet" href="${config.homeDir}/css/error.css" type="text/css" />
</head>
<body>
<div id="content">
<table>
    <tr>
        <td id="messageBoard" valign="middle"">
		   <h1>Hệ thống gặp lỗi khi xử lí yêu cầu của bạn.</h1>
		   <p>Hãy <a href="mailto:${config.contactEmail}?subject=${u:urlEncode('[Thông báo lỗi] Lỗi nội bộ')}&body=${u:urlEncode(requestScope['javax.servlet.error.exception'])}" 
		          class="contactLink">liên hệ với ban quản trị</a> để được trợ giúp.</p>
		   <p class="errorDetail">Chi tiết lỗi:
	       <code>${requestScope['javax.servlet.error.exception']}</code></p>
	       <p>Xin lỗi về sự bất tiện!</p>
           <p><ocw:actionLink name="homepage">Trở về trang chủ</ocw:actionLink></p>
        </td>
    </tr>
</table>
</div>
</body>
</html>