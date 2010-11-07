<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div>
<ocw:actionLink name="homepage"></ocw:actionLink>
</div>

<script language="javascript">
	Event.observe(window, 'load', redirectToHome);	
	function redirectToHome(){
		location.href='${config.homeDir}';
	}
</script>
