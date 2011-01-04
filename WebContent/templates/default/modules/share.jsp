<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %> 
   
<!-- Twitter & FB buttons --> 
<span class="shareList">
	<span class="st_twitter_hcount" displayText="Tweet"></span>
	<span class="st_facebook_hcount" displayText="Share"></span>
</span>

<script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
<script type="text/javascript">stLight.options({publisher:'b52d3378-3f03-434c-a81b-eeac4052b94c'});</script> 

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