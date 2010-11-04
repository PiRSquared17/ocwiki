<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %> 
   
<!-- Twitter & FB buttons --> 
<ul class="shareList">
	<li class="st_twitter_hcount" displayText="Tweet"></li>
	<li class="st_facebook_hcount" displayText="Share"></li>
	<!-- 
	<li class="st_email_hcount" displayText="Email"></li>
	<li class="st_sharethis_hcount" displayText="Share"></li> 
	 -->
</ul>

<!-- linkhay button -->
<script type="text/javascript">
<!--
var linkhay_title = '${u:escapeJs(pageTitle)}';
var linkhay_url = location.href;
var linkhay_desc = '${u:escapeJs(pageTitle)}';
var linkhay_style = '2';
//-->
</script>
<script type="text/javascript" src="http://linkhay.com/widgets/linkhay.js"></script>