<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Xem Danh Sách Phiên Bản</title>
</head>
<body>
<h2>Đối với các phiên bản dưới đây, click vào ngày tạo để xem !</h2>

<div><input type="submit" value="So sánh 2 phiên bản được chọn"
	title="Xem điểm khác nhau giữa 2 phiên bản" /></div>

<c:choose>
	<c:when test="${pageCount > 0}">
		<ol>
			<c:forEach items="${revisions}" var="revision">
				<li><ocw:articleLink resource="${revision.article}">${revision.timestamp}</ocw:articleLink>
				<ocw:userLink user="${revision.author}">${revision.author.fullname}</ocw:userLink>
				<em>${revision.summary}</em></li>
			</c:forEach>
		</ol>
	</c:when>
	<c:otherwise>
		<div class="empty-notif">Chưa có dữ liệu</div>
	</c:otherwise>
</c:choose>

</body>
</html>