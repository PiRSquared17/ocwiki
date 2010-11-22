<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script language="javascript">

	//kiem tra inputbox empty
	function is_empty(element) {
		if (element.value=="" || element.value == null || element.value.length==0) {
			//bien bao loi
			element.style.background="#FF99AF";
			return true;
		} else {
			element.style.background="#FFFFFF";
			return false;
		}
	}

	//co phai e mail?
	function is_email(element){
		var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
		if(element.value.match(emailExp) && element.value!="" && element.value != null && element.value.lenth!=0){
			element.style.background="#FFFFFF";
		}else{
			//bien bao loi
			element.style.background="#FF99AF";
		}
	}
</script>
	
<h2>Cung cấp một số thông tin cá nhân của bạn</h2>
<ocw:form action="user.profile.complete" id="form" method="post">
<div><ocw:error code="newProfileError"></ocw:error></div>

<fieldset>
<legend>Đăng nhập</legend>	
	<fieldset>
	<legend>Hoàn tất thông tin cá nhân</legend>
		<c:if test="${action.usedEmail}"><div style="color: green">
			Có thể bạn đã từng sử dụng OCWiki với email ${action.newOpenIDAcc.user.email} bạn hãy liên kết với tài khoản thông qua (một trong các) cách sau
			<c:if test="${action.mergeSA}">
				<div>Xác nhận sử dụng tên người dùng và mật khẩu của ${action.simpleAccount.fullname} (Đang được xây dựng)</div>
			</c:if>
			<c:if test="${action.mergeOID}">
				<div>Xác nhận sử dụng OpenID:
				<ul>
				<c:forEach items="${action.openIDAccounts}" var="account">
					<li><ocw:actionLink name="user.login.openid">
						<ocw:param name="openIDUrl" value="${u:urlEncode(account.providerUrl)}"></ocw:param>
						<ocw:param name="connect" value="true"></ocw:param> 
						${account.providerUrl}
					</ocw:actionLink></li>
				</c:forEach> 
				</ul></div>
			</c:if>
			<c:if test="${action.mergeFB}">
				<div>Xác nhận sử dụng Facebook:(Đang được xây dựng)
				<ul>
				<c:forEach items="${action.fbAccounts}" var="account">
					<li>
						${account.uid}
					</li>
				</c:forEach> 
				</ul></div>
			</c:if>

			hoặc 
			<a href="#" onclick="$('email-edit-input').focus();$('email-edit-input').select();return false;">
				thay đổi email
			</a>.
		</div></c:if>
		<div><ocw:error code="verifyError"></ocw:error></div>
		<div><ocw:error code="fullnameError"></ocw:error></div>
		<table>
			<tr>
				<td width="100px">
					Tên đầy đủ: 
				</td><td>
					<span id="fullname-edit-field">
						<select name="fullname-edit-input" id="fullname-edit-input">
							<option value="firstLast" selected="selected">Tên-Họ</option>
							<option value="lastFirst" >Họ-Tên</option>
						</select>
					</span> 
				</td>
			</tr>
			<tr>
				<td>
					Họ: 
				</td><td>
					<span id="lastname-edit-field">
						<input name="lastname-edit-input" id="lastname-edit-input" value="${action.newOpenIDAcc.user.lastName}"/>	
					</span> 
				</td>
			</tr>
			<tr>
				<td>
					*Tên: 
				</td><td>
					<span id="firstname-edit-field">
						<input name="firstname-edit-input" id="firstname-edit-input" value="${action.newOpenIDAcc.user.firstName}"/>	
					</span> 
				</td>
			</tr>
			<tr>
				<td>
					*Email: 
				</td><td>
					<span id="email-edit-field">	
						<input name="email-edit-input" id="email-edit-input" value="${action.newOpenIDAcc.user.email}"/>
					</span> 
					<div><ocw:error code="emailError"></ocw:error></div>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<div class="notification">Bạn vui lòng điền các ô có dấu sao (*)</div>
	<div id="form-buttons">
		<p>
			<button style="margin-left:0%; float:none;" type="submit" name="change" id="change" value="change" >Tiếp tục</button>
			<button style="margin-left:0%; float:none;" type="reset" name="resetAll" id="resetAll" value="reset">Hủy bỏ mọi thay đổi</button>
		</p>
	</div>
</fieldset>
</ocw:form>
