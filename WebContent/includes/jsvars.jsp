<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
<!-- // initialize global variables
   articlePath = '${config.articlePath}';
   actionPath = '${config.actionPath}';
   apiPath = '${config.apiPath}';
   uploadPath = '${config.uploadPath}';
   restPath = '${config.restPath}';
   templatePath = '${templatePath}';
   recaptchaPublicKey = '${config.recaptchaPublicKey}';

   resource = ${u:toJson(action.resourceBean)};

   login = ${sessionScope.login ? true : false};

<c:if test="${not empty sessionScope.user}">
	user = {
	    id: ${sessionScope.user.id},
	    name: '${sessionScope.user.name}',
	    group: '${sessionScope.user.group}',
	    email: '${sessionScope.user.email}',
	    fullname: '${sessionScope.user.fullname}',
	    timezone: '${sessionScope.user.timezone}',
	    blocked: ${sessionScope.user.blocked},
	    blockExpiredDate: '${sessionScope.user.blockExpiredDate}',
	    warningMessage: '${sessionScope.user.warningMessage}',
	    warningExpiredDate: '${sessionScope.user.warningExpiredDate}'
	};
</c:if>

//-->
</script>