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

	function confirm_pass(pass1,pass2){
		if (pass1.value == pass2.value) {
			if (pass1.value=="" || pass1.value == null || pass1.value.length==0)
				pass1.style.background="#FF99AF";
			else {
				pass1.style.background="#FFFFFF";
				pass2.style.background="#FFFFFF";
			}
		} else {
			pass2.style.background="#FF99AF";
		}
	}

	function showEditField(value){
		if (value==5){
			$('lastname-edit-field').show();
			$('lastname-edit-button-show').hide();
			$('lastname-edit-button-hide').show();
		}else if (value==9){
			$('about-edit-field').show();
			$('about-edit-button-show').hide();
			$('about-edit-button-hide').show();
		}else{
		}
	}
	
	function hideEditField(value){
		if (value==5){
			$('lastname-edit-field').hide();
			$('lastname-edit-button-show').show();
			$('lastname-edit-button-hide').hide();			
			$('lastname-edit-input').value='';
		}else if (value==9){
			$('about-edit-field').hide();
			$('about-edit-button-show').show();
			$('about-edit-button-hide').hide();	
			tinyMCE.getInstanceById('about-edit-input').getBody().innerHTML='';
		}else{
		}
	}
</script>
	
<h2>Thay đổi thông tin của ${action.displayedUser.fullname}</h2>
<ocw:form action="user.profileedit" id="form1">
<input type="hidden" name="action" value="Sign Up" >
<c:if test="${(action.success == true)}"><div class="notification"><font color ="green">  Thay đổi thành công! </font></div><br /></c:if>
  <br />
  
<fieldset>
<legend>Thông tin</legend>
<div>
Hình đại diện: <b>:P</b> 
</div>
<div>
Tên đầy đủ: <b>${action.displayedUser.fullname}</b> 
</div>
<div>
Email: <b><a href="mailto:${action.displayedUser.email}">${action.displayedUser.email}</a></b>
</div>
<div>
Ngày đăng kí: <b>${u:formatDateTime(action.displayedUser.registerDate)}</b>
</div>
<div>
Nhóm: <b>${action.displayedUser.group}</b>
</div>
</fieldset>

<fieldset>
<legend>Thay đổi thông tin người dùng</legend>
<div>
Hình đại diện: <b>:P</b> 
<div></div>
</div>
<div>
Tên người dùng: <b>${action.displayedUser.name}</b> 
<div></div>
</div>
<div>
Mật khẩu: <b>******</b>
<div></div>
</div>
</fieldset>

<fieldset>
<legend>Thay đổi thông tin cá nhân</legend>
<div>
Tên đầy đủ: <b>${action.displayedUser.fullname}</b> 
</div>
<div>
Tên: <b>${action.displayedUser.lastName}</b> <a href="#" id="lastname-edit-button-show" style="display: inline" onclick="showEditField(5);return false;">sửa</a><a href="#" id="lastname-edit-button-hide" style="display: none" onclick="hideEditField(5);return false;">thôi</a>
<div id=lastname-edit-field style="display: none">
	<input name="lastname-edit-input" id="lastname-edit-input" value="${action.displayedUser.lastName}"/>	
</div> 
</div>
<div>
Họ: <b>${action.displayedUser.lastName}</b> 
</div>
<div>
Giới tính: <b>${action.displayedUser.gender}</b>
</div>
<div>
Ngày sinh: <b>${action.displayedUser.birthday}</b>
</div>
</fieldset>

<fieldset>
<legend>Thay đổi chi tiết</legend>
<div>
Tự giới thiệu: <b>${action.displayedUser.about}</b>  <a href="#" id="about-edit-button-show" style="display: inline" onclick="showEditField(9);return false;">sửa</a><a href="#" id="about-edit-button-hide" style="display: none" onclick="hideEditField(9);return false;">thôi</a>
<div id=about-edit-field style="display: none">
	<textarea name="about-edit-input" id="about-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.about)}</textarea>
</div> 
</div>
<div>
Quê quán: <b>${action.displayedUser.firstName}</b> 
</div>
<div>
Địa chỉ: <b>${action.displayedUser.lastName}</b> 
</div>
<div>
Lý lịch: <b>${action.displayedUser.gender}</b>
</div>
</fieldset>

<fieldset>
<legend>Thay đổi liên hệ</legend>
<div>
Email: <b><a href="mailto:${action.displayedUser.email}">${action.displayedUser.email}</a></b>
</div>
<div>
Website: <b>${action.displayedUser.website}</b> 
</div>
</fieldset>

<fieldset>
<legend>Thay đổi thông tin khác</legend>
<div>
Múi giờ: <b>${action.displayedUser.timezone}</b>
</div>
</fieldset>

  <div id="form_edit">
   <p>
    <label>Old Password 
    <input style="width: 250px;" type="password" name="password" id="password" />
    </label>
  </p>
  <br />
  <p>
    <label>Change Password
    <input style="width: 250px;" type="password" name="changedPassword" id="changedPassword" onblur="is_empty(document.getElementById('changedPassword'));confirm_pass(document.getElementById('changedPassword'),document.getElementById('confirmPass'));" />
    </label>
  </p>
  <br />
  <p>
    <label>Confirm Password *
    <input style="width: 250px;" type="password" name="confirmPass" id="confirmPass" onblur="is_empty(document.getElementById('confirmPass'));confirm_pass(document.getElementById('changedPassword'),document.getElementById('confirmPass'));" />
    </label>
</p>
<br />
  <p>
    <label>Change E-mail
    <input style="width: 250px;" type="text" name="userEmail" id="userEmail"/>
    </label>
  </p>
  <p>
    <input style="margin-left:200px; float:none;" type="submit" name="change" id="change" value="Change" />
    <input style="margin-left:0px; float:none;" type="reset" name="resetAll" id="resetAll" value="Reset All" />
    
  </p>
  </div>
</ocw:form>
