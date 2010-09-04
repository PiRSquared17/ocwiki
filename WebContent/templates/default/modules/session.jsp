<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>

<div class="session">
<div class="jsmenu" id="user-toolbar"
	style="display: ${sessionScope.login ? 'block' : 'none'};">
<ul class="level1 horizontal" id="user-toolbar-root">
	<li class="level1">${sessionScope.user.fullname}
	<ul class="level2 dropdown">
		<li><ocw:actionLink name="user.edituser"
			title="Sửa thông tin người dùng">Sửa thông tin</ocw:actionLink></li>
		<li><ocw:actionLink name="user.preference">Tuỳ chọn</ocw:actionLink>
		</li>
		<li><ocw:actionLink name="user.logout"
			title="Đăng xuất khỏi hệ thống">Đăng xuất</ocw:actionLink></li>
	</ul>
	</li>
</ul>
</div>
<div style="display: ${sessionScope.login ? 'none' : 'block'};"
	id="guest-toolbar">
<ul>
	<li><ocw:actionLink name="user.signup">Đăng kí</ocw:actionLink></li>
	<li><ocw:actionLink name="user.login" title="Đăng nhập"
		onclick="openLoginDialog(); return false;">Đăng nhập</ocw:actionLink></li>
</ul>
</div>
</div>

<div style="display: none" id="session-loginDialog-content">
<form>
<p><label>Tên: <input type="text" name="name"
	id="session_name" /> </label> <span class="error-validating"
	id="session_nameError"></span></p>
<p><label>Mật khẩu: <input type="password" name="password"
	id="session_password" /> <span class="error-validating"
	id="session_passwordError"></span> </label></p>
</form>
<ocw:actionLink name="user.signup">Đăng kí</ocw:actionLink> <ocw:actionLink
	name="user.forgetpass">Quên mật khẩu?</ocw:actionLink></div>

<script type="text/javascript">

	var sessionLoginDialogContent = $('session-loginDialog-content').innerHTML;
	$('session-loginDialog-content').remove();

	function openLoginDialog() {
		Dialog.confirm(sessionLoginDialogContent, {
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
						}
					}
				});
		return false;
	}

    var userToolbar = new Menu('user-toolbar-root', 'userToolbar', function() {
        this.closeDelayTime = 300;
    });

	//-->
</script>
