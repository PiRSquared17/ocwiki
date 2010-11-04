<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<div class="session">
<div class="jsmenu" id="user-toolbar"
	style="display: ${sessionScope.login ? 'block' : 'none'};">
<ul class="level1 horizontal" id="user-toolbar-root">
	<li class="level1">${sessionScope.user.fullname}
	<ul class="level2 dropdown">
		<li><ocw:actionLink name="user.profileedit"
			title="Sửa thông tin người dùng">Sửa thông tin</ocw:actionLink></li>
		<li><ocw:actionLink name="user.preference">Tuỳ chọn</ocw:actionLink>
		</li>
		<li><ocw:actionLink name="user.logout"
			title="Đăng xuất khỏi hệ thống">Đăng xuất</ocw:actionLink></li>
	</ul>
	</li>
</ul>
</div>
<div class="jsmenu" id="guest-toolbar"
    style="display: ${sessionScope.login ? 'none' : 'block'};">
<ul class="level1 horizontal" id="guest-toolbar-root">
	<li class="level1" id="login-dropdown-menu">Đăng nhập
        <ul class="level2 dropdown">
            <c:if test="${not empty config.facebookAppId}">
	            <li><a href="#" onclick="fblogin(); return false;">FaceBook</a></li>
	            <script type="text/javascript">
				<!--
				Event.observe(window, 'load', function() {
				    FB.init({
				        appId: '${config.facebookAppId}', 
				        status: true, 
				        cookie: true, 
				        xfbml: true
				    });
				}
				//-->
				</script>
            </c:if>
            <li><ocw:actionLink name="user.login.openid"><ocw:param name="openIDUrl" value="https://www.google.com/accounts/o8/id"></ocw:param>Google</ocw:actionLink></li>
            <li><ocw:actionLink name="user.login.openid"><ocw:param name="openIDUrl" value="https://me.yahoo.com/"></ocw:param>Yahoo</ocw:actionLink></li>
            <li><a href="#" onclick="openOpenIDLoginDialog(); return false;">OpenID</a></li>
            <li><a href="#" onclick="openLoginDialog(); return false;">Nội bộ</a></li>
        </ul>
	</li>
</ul>
</div>
</div>

<ocw:setJs var="dialogContent">
<form>
<p><label>Tên: <input type="text" name="name"
	id="session_name" /> </label> <span class="error-validating"
	id="session_nameError"></span></p>
<p><label>Mật khẩu: <input type="password" name="password"
	id="session_password" /> <span class="error-validating"
	id="session_passwordError"></span> </label></p>
</form>
<ocw:actionLink name="user.signup">Đăng kí</ocw:actionLink> 
<ocw:actionLink name="user.forgetpass">Quên mật khẩu?</ocw:actionLink>
</ocw:setJs>

<c:if test="${(not empty config.facebookAppId) and (not sessionScope.login)}">
	<script src="http://connect.facebook.net/en_US/all.js"></script>
	<script type="text/javascript">
	<!--
	
	function fblogin() {
	  FB.login(function(response) {
	      if (response.session) {
	          new Ajax.Request(
	                  restPath + '/login/facebook',
	                  {
	                      method : 'get',
	                      requestHeaders : {
	                          Accept : 'application/json'
	                      },
	                      evalJSON : true,
	                      onSuccess : function(transport) {
	                          location.reload(true);
	                      },
	                      onFailure : function(transport) {
	                          alert(transport.responseText);
	                          var code = transport.responseJSON.code;
	                          if (code == 'account blocked') {
	                              alert('Tài khoản của bạn đã bị khoá.');
	                          } else {
	                              DefaultTemplate.onFailure(transport); 
	                          }
	                      }
	                  });
	      }
	    });
	}

//-->
</script>
    
</c:if>
<script type="text/javascript">

var userToolbar;
var guessToolbar;

Element.observe(window, 'load', function() {
   	userToolbar = new Menu('user-toolbar-root', 'userToolbar', function() {
        this.closeDelayTime = 300;
    });
   	guessToolbar = new Menu('guest-toolbar-root', 'guessToolbar', function() {
        this.closeDelayTime = 300;
    });
});

function openLoginDialog() {
	Dialog.confirm('${dialogContent}', {
		width : 300,
		okLabel : "Đăng nhập",
		cancelLabel : "Thôi",
		buttonClass : "session-button",
		className : "alphacube",
		id : "session-loginDialog",
		cancel : function(win) { return true; },
		ok : session_login
	});
	$('session_name').focus();
}

function session_login() {
	if ($F('session_name') == '') {
		$('session_nameError').innerHTML = 'Bạn cần nhập tên người dùng';
		return false;
	}
	if ($F('session_password') == '') {
		$('session_passwordError').innerHTML = 'Bạn cần nhập mật khẩu';
		return false;
	}
	new Ajax.Request(
		restPath + '/login',
		{
			method : 'post',
			parameters : {
				name : $F('session_name'),
				password : $F('session_password')
			},
			requestHeaders : {
				Accept : 'application/json'
			},
			evalJSON : true,
			onSuccess : function(transport) {
				location.reload(true);
			},
			onFailure : function(transport) {
				var code = transport.responseJSON.code;
				if (code == 'invalid password') {
					$('session_passwordError').innerHTML = 'Sai mật khẩu';
				} else if (code == 'invalid name') {
					$('session_nameError').innerHTML = 'Người dùng không tồn tại';
				} else if (code == 'account blocked') {
					$('session_nameError').innerHTML = 'Tài khoản đã bị khoá';
				} else {
				    DefaultTemplate.onFailure(transport); 
				}
			}
		});
	return false;
}

function openOpenIDLoginDialog() {
	Dialog.confirm($('openID-login-dialog').innerHTML, 
			{
				className :"alphacube", 
				width : 400, 
				height : 250, 
				okLabel : "Đăng nhập", 
				cancelLabel : "Thôi", 
				buttonClass : "session-button",
				id : "openID-loginDialog",
				onOk :
					function(win){ 
						getURL();
						openID_login();
					}
			}
	); 
}

function openID_login(){
	if ($F('userSuppliedOpenIDUrl') == '') {
		$('openID_loginError').innerHTML = 'Bạn cần nhập đường dẫn OpenID';
		return false;
	}
	new Ajax.Request(
			restPath + '/login/openid',
			{
				method : 'get',
				parameters : {
					userSuppliedOpenIDUrl : $F(encodeURI('userSuppliedOpenIDUrl'))
				},
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					alert(transport.responseJSON.result.text);
					location.href=transport.responseJSON.result.text;
				},
				onFailure : function(transport) {
					var code = transport.responseJSON.code;
					if (code == 'empty url') {
						$('openID_loginError').innerHTML = 'Bạn cần nhập đường dẫn OpenID';
					} else if (code == 'connection error') {
						$('openID_loginError').innerHTML = 'Không thể kết nối tới nhà cung cấp OpenID';
					} else {
					    DefaultTemplate.onFailure(transport); 
					}
				}
			});
		return false;
}

	//-->
</script>
<%-- openID login Dialog --%>
<jsp:include page="session.openid.dialog-view.jsp" flush="true"></jsp:include>
