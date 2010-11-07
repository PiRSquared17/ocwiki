<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${pageTitle}</title>
  	<link rel="stylesheet" href="${templatePath}/css/print.css" type="text/css" />
</head>
<body>
<!-- content begins -->
<div>
	<div>
		<!-- ########################################## -->
		<!--  nội dung action được đặt ở đây -->
		<!-- ########################################## -->
		<jsp:include page="actions/${action.descriptor.name}.jsp" />
	</div>
	<div id="footer">
	    ${config.copyright}
	    ·
	    <a href="https://code.google.com/p/ocwiki/" target="_blank">${app.name} v${app.version}</a>
	</div>
</div>

</body>
</html>