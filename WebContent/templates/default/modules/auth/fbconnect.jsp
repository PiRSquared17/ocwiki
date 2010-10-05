<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty config.facebookAppId and not sessionScope.login}">
	<div id="fb-root"></div>
	<script src="http://connect.facebook.net/en_US/all.js"></script>
	<script>
	  FB.init({appId: '${config.facebookAppId}', status: true, cookie: true, xfbml: true});
	  FB.Event.subscribe('auth.sessionChange', function(response) {
	    if (response.session) {
		  alert('login!');
	      returnUrl = encodeURI(location.href);
	      location.href = actionPath + '/user.fblogin?returnUrl=' + returnUrl;
	    }
	  });
	</script>
	<fb:login-button></fb:login-button>
</c:if>
