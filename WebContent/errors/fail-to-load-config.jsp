<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Có lỗi trong tệp cấu hình</title>
	<link rel="stylesheet" href="<c:url value="/css/error.css"/>" type="text/css" />
</head>
<body>
<div id="content">
<table>
    <tr>
        <td id="messageBoard" valign="middle"">
	        <h1>Hệ thống không khởi động được do có lỗi trong tệp cấu hình.</h1>
	        <p>Xin hãy thông báo với ban quản trị để sửa lỗi sớm nhất có thể.</p>
	        <div class="errorDetail">Chi tiết lỗi: 
	           <pre>${requestScope['javax.servlet.error.exception'].cause}</pre>
	        </div>
	        <p>Xin lỗi về sự bất tiện!</p>
        </td>
    </tr>
</table>
</div>
</body>
</html>