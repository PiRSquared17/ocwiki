<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form>
<div id="openID-login-dialog" style="display:none;float: center;" align="center">
	<div id="username" style="display: none">Username: <input type="text"id="username-input" name="username-input" width="100px" onkeyup="updateUsernameView();" maxlength="50"></input></div>
	<div>OpenID url: 
		<span id="default-url" style="display: none"></span>
		<span id="user-url" style="display: inline"><input width="200px" type="text" id="user-url-input" name="user-url-input"></input></span>
	</div>
	<div><a href="#" onclick="setDefaultURL(15);return false;">Tự nhập đường dẫn OpenID</a></div>
	<div>
	<table style="float: center;" >
	<tr>
		<td width="180px" align="center">
		<a href="#" 
			onmouseover="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-up.png';" 
			onmouseout="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-no.png';"
			onmousedown="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-do.png';" 
			onmouseup="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-up.png';"
			onclick="setDefaultURL(1);return false;">
	
			<img src="${templatePath}/images/openid-buttons/large/google-no.png" name="google-btn" id="google-btn" alt="Google" border="none"/>
	
		</a>
		</td><td  width="180px" align="center">
		<a href="#" 
			onmouseover="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-up.png';" 
			onmouseout="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-no.png';"
			onmousedown="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-do.png';" 
			onmouseup="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-up.png';"
			onclick="setDefaultURL(2);return false;">
	
			<img src="${templatePath}/images/openid-buttons/large/yahoo-no.png" name="yahoo-btn" id="yahoo-btn" alt="Yahoo!" border="none"/>
			
		</a>
		</td>	
	</tr>
	</table>
	<table>
	<tr>
		<td  width="89px" align="center">
			<a href="#" 
				onmouseover="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-up.png';" 
				onmouseout="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-no.png';"
				onmousedown="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-do.png';" 
				onmouseup="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-up.png';" 
				onclick="setDefaultURL(3);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/aol-no.png" name="aol-btn" id="aol-btn" alt="Aol." border="none"/>
		
			</a>
		</td><td  width="89px" align="center">
			<a href="#" 
				onmouseover="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-up.png';" 
				onmouseout="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-no.png';"
				onmousedown="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-do.png';" 
				onmouseup="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-up.png';" 
				onclick="setDefaultURL(4);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/blogger-no.png" name="blogger-btn" id="blogger-btn" alt="Blogger" border="none"/>
		
			</a>
		</td><td   width="89px" align="center">
			<a href="#" 
				onmouseover="$('chimp-btn').src='${templatePath}/images/openid-buttons/small/chimp-up.png';" 
				onmouseout="$('chimp-btn').src='${templatePath}/images/openid-buttons/small/chimp-no.png';"
				onmousedown="$('chimp-btn').src='${templatePath}/images/openid-buttons/small/chimp-do.png';" 
				onmouseup="$('chimp-btn').src='${templatePath}/images/openid-buttons/small/chimp-up.png';" 
				onclick="setDefaultURL(5);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/chimp-no.png" name="chimp-btn" id="chimp-btn" alt="chi.mp" border="none"/>
		
			</a>
		</td><td width="89px" align="center">
			<a href="#" 
				onmouseover="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-up.png';" 
				onmouseout="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-no.png';"
				onmousedown="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-do.png';" 
				onmouseup="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-up.png';" 
				onclick="setDefaultURL(6);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/flickr-no.png" name="flickr-btn" id="flickr-btn" alt="flickr" border="none"/>
		
			</a>
		</td>
	</tr><tr>
		<td align="center">
			<a href="#" 
				onmouseover="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-up.png';" 
				onmouseout="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-no.png';"
				onmousedown="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-do.png';" 
				onmouseup="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-up.png';" 
				onclick="setDefaultURL(7);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/hyves-no.png" name="hyves-btn" id="hyves-btn" alt="Hyves" border="none"/>
		
			</a>
		</td><td align="center">
			<a href="#" 
				onmouseover="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-up.png';" 
				onmouseout="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-no.png';"
				onmousedown="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-do.png';" 
				onmouseup="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-up.png';" 
				onclick="setDefaultURL(8);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/livejournal-no.png" name="livejournal-btn" id="livejournal-btn" alt="LiVEJOURNAL" border="none"/>
		
			</a>
		</td><td align="center">
			<a href="#" 
				onmouseover="$('verisign-btn').src='${templatePath}/images/openid-buttons/small/verisign-up.png';" 
				onmouseout="$('verisign-btn').src='${templatePath}/images/openid-buttons/small/verisign-no.png';"
				onmousedown="$('verisign-btn').src='${templatePath}/images/openid-buttons/small/verisign-do.png';" 
				onmouseup="$('verisign-btn').src='${templatePath}/images/openid-buttons/small/verisign-up.png';" 
				onclick="setDefaultURL(9);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/verisign-no.png" name="verisign-btn" id="verisign-btn" alt="VeriSign" border="none"/>
		
			</a>
		</td><td align="center">
			<a href="#" 
				onmouseover="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-up.png';" 
				onmouseout="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-no.png';"
				onmousedown="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-do.png';" 
				onmouseup="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-up.png';" 
				onclick="setDefaultURL(10);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/myIDnet-no.png" name="myIDnet-btn" id="myIDnet-btn" alt="myID.net" border="none"/>
		
			</a>
		</td>
	</tr><tr>
		<td align="center">
			<a href="#" 
				onmouseover="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-up.png';" 
				onmouseout="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-no.png';"
				onmousedown="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-do.png';" 
				onmouseup="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-up.png';" 
				onclick="setDefaultURL(11);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/myOpenID-no.png" name="myOpenID-btn" id="myOpenID-btn" alt="myOpenID" border="none"/>
		
			</a>
		</td><td align="center">
			<a href="#" 
				onmouseover="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-up.png';" 
				onmouseout="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-no.png';"
				onmousedown="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-do.png';" 
				onmouseup="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-up.png';" 
				onclick="setDefaultURL(12);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/myspace-no.png" name="myspace-btn" id="myspace-btn" alt="myspace" border="none"/>
		
			</a>
		</td><td align="center">
			<a href="#" 
				onmouseover="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-up.png';" 
				onmouseout="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-no.png';"
				onmousedown="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-do.png';" 
				onmouseup="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-up.png';" 
				onclick="setDefaultURL(13);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/steam-no.png" name="steam-btn" id="steam-btn" alt="STEAM" border="none"/>
		
			</a>
		</td><td align="center">
			<a href="#" 
				onmouseover="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-up.png';" 
				onmouseout="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-no.png';"
				onmousedown="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-do.png';" 
				onmouseup="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-up.png';" 
				onclick="setDefaultURL(14);return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/wordpress-no.png" name="wordpress-btn" id="wordpress-btn" alt="WORDPRESS" border="none"/>
		
			</a>
		</td>
	</tr>
	</table>
	</div>
	<div>Tìm hiểu thêm về OpenID tại <a href="http://openid.net" target="_blank">OpenID.net</a> hoặc <a href="http://vi.wikipedia.org/wiki/OpenID" target="_blank">Wikipedia.org</a></div>
	<span id="openID_loginError" style="color: red"></span>
</div>

<script type="text/javascript">
var OIDP;
function setDefaultURL(value){
	OIDP=value;
	if (value==1){
		$('username').hide();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='https://www.google.com/accounts/o8/id';
	}else if (value==2){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<span id="provider-view">me.yahoo.com</span><b><span id="username-view">/username</span></b>';
	}else if (value==3){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<span id="provider-view">openid.aol.com</span><b><span id="username-view">/username</span></b>';
	}else if (value==4){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">blogspot.com</span>';
	}else if (value==5){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">mp</span>';
	}else if (value==6){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<span id="provider-view">flickr.com</span><b><span id="username-view">/username</span></b>';
	}else if (value==7){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">hyves.nl</span>';
	}else if (value==8){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">livejournal.com</span>';
	}else if (value==9){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">pip.verisignlabs.com</span>';
	}else if (value==10){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">myid.net</span>';
	}else if (value==11){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">myopenid.com</span>';
	}else if (value==12){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<span id="provider-view">myspace.com</span><b><span id="username-view">/username</span></b>';
	}else if (value==13){
		$('username').hide();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://steamcommunity.com/openid';
	}else if (value==14){
		$('username').show();
		$('user-url').hide();
		$('default-url').show();
		$('default-url').innerHTML='http://<b><span id="username-view">username.</span></b><span id="provider-view">wordpress.com</span>';
	
	}else{
		$('user-url').show();
		$('username').hide();
		$('default-url').hide();
	}
}

function updateUsernameView(){
	if ((OIDP==2)||(OIDP==3)||(OIDP==6)||(OIDP==12)){
		$('username-view').innerHTML="/"+$('username-input').value;;
	}else if ((OIDP==4)||(OIDP==5)||(OIDP==7)||(OIDP==8)||(OIDP==11)||(OIDP==14)||(OIDP==9)||(OIDP==10)){
		$('username-view').innerHTML=$('username-input').value+".";		
	}else{
		$('username-view').innerHTML=$('username-input').value;
	}
	$('username').focus;
}

function getURL(){
	var result;
	if ((OIDP==1)||(OIDP==13)){
		result = ($('default-url').innerHTML);
	}else if ((OIDP==2)||(OIDP==3)||(OIDP==6)||(OIDP==12)){
		if (($('username-input').value==null) || ($('username-input').value=="")){
			result = ('http://'+($('provider-view').innerHTML));
		}else{
			result = ('http://'+($('provider-view').innerHTML)+"/"+($('username-input').value));
		}
	}else if ((OIDP==4)||(OIDP==5)||(OIDP==7)||(OIDP==8)||(OIDP==11)||(OIDP==14)||(OIDP==9)||(OIDP==10)){
		if (($('username-input').value==null) || ($('username-input').value=="")){
			if ((OIDP==4)||(OIDP==5)){
				result=null
			}else{
				result = ('http://'+($('provider-view').innerHTML));
			}			
		}else{
			result = ('http://'+($('username-input').value)+"."+($('provider-view').innerHTML));
		}		
	}else{
		result = ($('user-url-input').value);
	}	
	$('userSuppliedOpenIDUrl').value=result;
	alert(result);
	return result;
}
</script>
<input id="userSuppliedOpenIDUrl" name="userSuppliedOpenIDUrl" type="hidden" value=""/>
</form>

<%-- hyves/myopenID co the hok can id --%>