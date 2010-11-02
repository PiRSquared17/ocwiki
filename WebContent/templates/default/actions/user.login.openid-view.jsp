<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div id="openID-login-url" style="display:block">
	OpenID url: <input type="text" id="openIDUrl" name="openIDUrl"></input>
	<div>
	<table>
	<tr>
		<td width="150px">
		<a href="#" 
			onmouseover="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-up.png';" 
			onmouseout="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-no.png';"
			onmousedown="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-do.png';" 
			onmouseup="$('google-btn').src='${templatePath}/images/openid-buttons/large/google-up.png';"
			onclick="return false;">
	
			<img src="${templatePath}/images/openid-buttons/large/google-no.png" name="google-btn" id="google-btn" alt="Google"/>
	
		</a>
		</td><td>
		<a href="#" 
			onmouseover="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-up.png';" 
			onmouseout="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-no.png';"
			onmousedown="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-do.png';" 
			onmouseup="$('yahoo-btn').src='${templatePath}/images/openid-buttons/large/yahoo-up.png';"
			onclick="return false;">
	
			<img src="${templatePath}/images/openid-buttons/large/yahoo-no.png" name="yahoo-btn" id="yahoo-btn" alt="Yahoo!"/>
			
		</a>
		</td>	
	</tr>
	</table>
	<table>
	<tr>
		<td  width="75px">
			<a href="#" 
				onmouseover="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-up.png';" 
				onmouseout="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-no.png';"
				onmousedown="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-do.png';" 
				onmouseup="$('aol-btn').src='${templatePath}/images/openid-buttons/small/aol-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/aol-no.png" name="aol-btn" id="aol-btn" alt="Aol."/>
		
			</a>
		</td><td  width="75px">
			<a href="#" 
				onmouseover="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-up.png';" 
				onmouseout="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-no.png';"
				onmousedown="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-do.png';" 
				onmouseup="$('blogger-btn').src='${templatePath}/images/openid-buttons/small/blogger-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/blogger-no.png" name="blogger-btn" id="blogger-btn" alt="Blogger"/>
		
			</a>
		</td><td   width="75px">
			<a href="#" 
				onmouseover="$('claimID-btn').src='${templatePath}/images/openid-buttons/small/claimID-up.png';" 
				onmouseout="$('claimID-btn').src='${templatePath}/images/openid-buttons/small/claimID-no.png';"
				onmousedown="$('claimID-btn').src='${templatePath}/images/openid-buttons/small/claimID-do.png';" 
				onmouseup="$('claimID-btn').src='${templatePath}/images/openid-buttons/small/claimID-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/claimID-no.png" name="claimID-btn" id="claimID-btn" alt="claimID"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-up.png';" 
				onmouseout="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-no.png';"
				onmousedown="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-do.png';" 
				onmouseup="$('flickr-btn').src='${templatePath}/images/openid-buttons/small/flickr-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/flickr-no.png" name="flickr-btn" id="flickr-btn" alt="flickr"/>
		
			</a>
		</td>
	</tr><tr>
		<td>
			<a href="#" 
				onmouseover="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-up.png';" 
				onmouseout="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-no.png';"
				onmousedown="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-do.png';" 
				onmouseup="$('hyves-btn').src='${templatePath}/images/openid-buttons/small/hyves-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/hyves-no.png" name="hyves-btn" id="hyves-btn" alt="Hyves"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-up.png';" 
				onmouseout="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-no.png';"
				onmousedown="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-do.png';" 
				onmouseup="$('livejournal-btn').src='${templatePath}/images/openid-buttons/small/livejournal-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/livejournal-no.png" name="livejournal-btn" id="livejournal-btn" alt="LiVEJOURNAL"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('microsoft-btn').src='${templatePath}/images/openid-buttons/small/microsoft-up.png';" 
				onmouseout="$('microsoft-btn').src='${templatePath}/images/openid-buttons/small/microsoft-no.png';"
				onmousedown="$('microsoft-btn').src='${templatePath}/images/openid-buttons/small/microsoft-do.png';" 
				onmouseup="$('microsoft-btn').src='${templatePath}/images/openid-buttons/small/microsoft-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/microsoft-no.png" name="microsoft-btn" id="microsoft-btn" alt="Microsoft"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-up.png';" 
				onmouseout="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-no.png';"
				onmousedown="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-do.png';" 
				onmouseup="$('myIDnet-btn').src='${templatePath}/images/openid-buttons/small/myIDnet-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/myIDnet-no.png" name="myIDnet-btn" id="myIDnet-btn" alt="myID.net"/>
		
			</a>
		</td>
	</tr><tr>
		<td>
			<a href="#" 
				onmouseover="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-up.png';" 
				onmouseout="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-no.png';"
				onmousedown="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-do.png';" 
				onmouseup="$('myOpenID-btn').src='${templatePath}/images/openid-buttons/small/myOpenID-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/myOpenID-no.png" name="myOpenID-btn" id="myOpenID-btn" alt="myOpenID"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-up.png';" 
				onmouseout="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-no.png';"
				onmousedown="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-do.png';" 
				onmouseup="$('myspace-btn').src='${templatePath}/images/openid-buttons/small/myspace-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/myspace-no.png" name="myspace-btn" id="myspace-btn" alt="myspace"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-up.png';" 
				onmouseout="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-no.png';"
				onmousedown="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-do.png';" 
				onmouseup="$('steam-btn').src='${templatePath}/images/openid-buttons/small/steam-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/steam-no.png" name="steam-btn" id="steam-btn" alt="STEAM"/>
		
			</a>
		</td><td>
			<a href="#" 
				onmouseover="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-up.png';" 
				onmouseout="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-no.png';"
				onmousedown="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-do.png';" 
				onmouseup="$('wordpress-btn').src='${templatePath}/images/openid-buttons/small/wordpress-up.png';" 
				onclick="return false;">
		
				<img src="${templatePath}/images/openid-buttons/small/wordpress-no.png" name="wordpress-btn" id="wordpress-btn" alt="WORDPRESS"/>
		
			</a>
		</td>
	</tr>
	</table>
	</div>
</div>
