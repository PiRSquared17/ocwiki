<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty config.facebookAppId}">
	<div id="fb-root"></div>
	<script src="http://connect.facebook.net/en_US/all.js"></script>
	<script>
	  FB.init({appId: '${config.facebookAppId}', status: true, cookie: true, xfbml: true});
	  FB.Event.subscribe('auth.sessionChange', function(response) {
	    if (response.session) {
	      alert('logged in');
	    } else {
	      alert('logged out');
	    }
	  });
	</script>
	<fb:login-button></fb:login-button>
</c:if>
