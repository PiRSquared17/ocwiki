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
		if (value==2){
			$('name-edit-field').show();
			$('name-edit-button-show').hide();
			$('name-edit-button-hide').show();
		}else if (value==3){
			$('pass-edit-field').show();
			$('pass-edit-button-show').hide();
			$('pass-edit-button-hide').show();
		}else if (value==4){
			$('fullname-edit-field').show();
			$('fullname-edit-button-show').hide();
			$('fullname-edit-button-hide').show();
		}else if (value==5){
			$('lastname-edit-field').show();
			$('lastname-edit-button-show').hide();
			$('lastname-edit-button-hide').show();
		}else if (value==6){
			$('firstname-edit-field').show();
			$('firstname-edit-button-show').hide();
			$('firstname-edit-button-hide').show();
		}else if (value==7){
			$('gender-edit-field').show();
			$('gender-edit-button-show').hide();
			$('gender-edit-button-hide').show();
		}else if (value==8){
			$('birthday-edit-field').show();
			$('birthday-edit-button-show').hide();
			$('birthday-edit-button-hide').show();
		}else if (value==9){
			$('about-edit-field').show();
			$('about-edit-button-show').hide();
			$('about-edit-button-hide').show();
		}else if (value==10){
			$('hometown-edit-field').show();
			$('hometown-edit-button-show').hide();
			$('hometown-edit-button-hide').show();
		}else if (value==11){
			$('location-edit-field').show();
			$('location-edit-button-show').hide();
			$('location-edit-button-hide').show();
		}else if (value==12){
			$('bio-edit-field').show();
			$('bio-edit-button-show').hide();
			$('bio-edit-button-hide').show();
		}else if (value==13){
			$('email-edit-field').show();
			$('email-edit-button-show').hide();
			$('email-edit-button-hide').show();
		}else if (value==14){
			$('website-edit-field').show();
			$('website-edit-button-show').hide();
			$('website-edit-button-hide').show();
		}else if (value==15){
			$('timezone-edit-field').show();
			$('timezone-edit-button-show').hide();
			$('timezone-edit-button-hide').show();
		}else{
		}
	}
	
	function hideEditField(value){
		if (value==2){
			$('name-edit-field').hide();
			$('name-edit-button-show').show();
			$('name-edit-button-hide').hide();			
			$('name-edit-input').value='';
		}else if (value==3){
			$('pass-edit-field').hide();
			$('pass-edit-button-show').show();
			$('pass-edit-button-hide').hide();	
			$('pass-edit-old').value='';		
			$('pass-edit-input').value='';
			$('pass-edit-confirm').value='';
		}else if (value==4){
			$('fullname-edit-field').hide();
			$('fullname-edit-button-show').show();
			$('fullname-edit-button-hide').hide();	
			$('fullname-edit-input-1').checked=$('fullname-edit-input-1').defaultChecked;		
			$('fullname-edit-input-2').checked=$('fullname-edit-input-2').defaultChecked;
		}else if (value==5){
			$('lastname-edit-field').hide();
			$('lastname-edit-button-show').show();
			$('lastname-edit-button-hide').hide();			
			$('lastname-edit-input').value=$('lastname-edit-input').defaultValue;
		}else if (value==6){
			$('firstname-edit-field').hide();
			$('firstname-edit-button-show').show();
			$('firstname-edit-button-hide').hide();			
			$('firstname-edit-input').value=$('firstname-edit-input').defaultValue;
		}else if (value==7){
			$('gender-edit-field').hide();
			$('gender-edit-button-show').show();
			$('gender-edit-button-hide').hide();
			$('gender-edit-input-male').checked=$('gender-edit-input-male').defaultChecked;			
			$('gender-edit-input-female').checked=$('gender-edit-input-female').defaultChecked;
			$('gender-edit-input-unknown').checked=$('gender-edit-input-unknown').defaultChecked;
		}else if (value==8){
			$('birthday-edit-field').hide();
			$('birthday-edit-button-show').show();
			$('birthday-edit-button-hide').hide();	
			$('birthday-edit-day').value=$('birthday-edit-day').defaultValue;		
			$('birthday-edit-month').value=$('birthday-edit-month').defaultValue;
			$('birthday-edit-year').value=$('birthday-edit-year').defaultValue;
		}else if (value==9){
			$('about-edit-field').hide();
			$('about-edit-button-show').show();
			$('about-edit-button-hide').hide();	
			tinyMCE.get('about-edit-input').load($('about-edit-input'));
		}else if (value==10){
			$('hometown-edit-field').hide();
			$('hometown-edit-button-show').show();
			$('hometown-edit-button-hide').hide();	
			tinyMCE.get('hometown-edit-input').load($('hometown-edit-input'));
		}else if (value==11){
			$('location-edit-field').hide();
			$('location-edit-button-show').show();
			$('location-edit-button-hide').hide();	
			tinyMCE.get('location-edit-input').load($('location-edit-input'));
		}else if (value==12){
			$('bio-edit-field').hide();
			$('bio-edit-button-show').show();
			$('bio-edit-button-hide').hide();	
			tinyMCE.get('bio-edit-input').load($('bio-edit-input'));
		}else if (value==13){
			$('email-edit-field').hide();
			$('email-edit-button-show').show();
			$('email-edit-button-hide').hide();			
			$('email-edit-input').value=$('email-edit-input').defaultValue;
		}else if (value==14){
			$('website-edit-field').hide();
			$('website-edit-button-show').show();
			$('website-edit-button-hide').hide();			
			$('website-edit-input').value=$('website-edit-input').defaultValue;
		}else if (value==15){
			$('timezone-edit-field').hide();
			$('timezone-edit-button-show').show();
			$('timezone-edit-button-hide').hide();			
			$('timezone-edit-input').selectedIndex=defaultSelected;
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
<legend>Thay đổi</legend>
<fieldset>
<legend>Thay đổi thông tin người dùng</legend>
<div>
Hình đại diện: <b>:P</b> 
<div></div>
</div>
<div>
Tên người dùng: <b>${action.displayedUser.name}</b>  
<c:if test="${action.displayedUser.name}"> <a href="#" id="name-edit-button-show" style="display: inline" onclick="showEditField(2);return false;">sửa</a><a href="#" id="name-edit-button-hide" style="display: none" onclick="hideEditField(2);return false;">thôi</a>
<div id=name-edit-field style="display: none">
	<input name="name-edit-input" id="name-edit-input" value=""/>	
	<div class="notification">Lưu ý, bạn chỉ được thay đổi tên người dùng 1 lần duy nhất.</div>
</div> 
</c:if>
</div>
<div>
Mật khẩu: <b>******</b> <a href="#" id="pass-edit-button-show" style="display: inline" onclick="showEditField(3);return false;">sửa</a><a href="#" id="pass-edit-button-hide" style="display: none" onclick="hideEditField(3);return false;">thôi</a>
<div id=pass-edit-field style="display: none">
	<label>Mật khẩu cũ           : <input type="password" name="pass-edit-old" id="pass-edit-old"/></label> <br/>
	<label>Mật khẩu mới          : <input type="password" name="pass-edit-input" id="pass-edit-input"/></label> <br/>
	<label>Xác nhận mật khẩu mới : <input type="password" name="pass-edit-confirm" id="pass-edit-confirm"/></label> 
</div> 
</div>
</fieldset>

<fieldset>
<legend>Thay đổi thông tin cá nhân</legend>
<div>
Tên đầy đủ: <b>${action.displayedUser.fullname}</b> <a href="#" id="fullname-edit-button-show" style="display: inline" onclick="showEditField(4);return false;">sửa</a><a href="#" id="fullname-edit-button-hide" style="display: none" onclick="hideEditField(4);return false;">thôi</a>
<div id=fullname-edit-field style="display: none">
	<div><input type="radio" name="fullname-edit-input" id="fullname-edit-input-1" value="firstLast" <c:if test="${action.displayedUser.nameOdering=='FIRST_LAST'}">checked="checked"</c:if>/>Tên-Họ (ví dụ: ${action.displayedUser.firstName} ${action.displayedUser.lastName})</div>
	<div><input type="radio" name="fullname-edit-input" id="fullname-edit-input-2" value="lastFirst" <c:if test="${action.displayedUser.nameOdering=='LAST_FIRST'}">checked="checked"</c:if>/>Họ-Tên (ví dụ: ${action.displayedUser.lastName} ${action.displayedUser.firstName})</div>
</div> 
</div>
<div>
Họ: <b>${action.displayedUser.lastName}</b> <a href="#" id="lastname-edit-button-show" style="display: inline" onclick="showEditField(5);return false;">sửa</a><a href="#" id="lastname-edit-button-hide" style="display: none" onclick="hideEditField(5);return false;">thôi</a>
<div id=lastname-edit-field style="display: none">
	<input name="lastname-edit-input" id="lastname-edit-input" value="${fn:escapeXml(action.displayedUser.lastName)}"/>	
</div> 
</div>
<div>
Tên: <b>${action.displayedUser.firstName}</b> <a href="#" id="firstname-edit-button-show" style="display: inline" onclick="showEditField(6);return false;">sửa</a><a href="#" id="firstname-edit-button-hide" style="display: none" onclick="hideEditField(6);return false;">thôi</a>
<div id=firstname-edit-field style="display: none">
	<input name="firstname-edit-input" id="firstname-edit-input" value="${fn:escapeXml(action.displayedUser.firstName)}"/>	
</div> 
</div>
<div>
Giới tính: <b>${action.displayedUser.gender}</b> <a href="#" id="gender-edit-button-show" style="display: inline" onclick="showEditField(7);return false;">sửa</a><a href="#" id="gender-edit-button-hide" style="display: none" onclick="hideEditField(7);return false;">thôi</a>
<div id=gender-edit-field style="display: none">
    	<div><input type="radio" name="gender-edit-input" id="gender-edit-input-male" value="male" <c:if test="${action.displayedUser.gender=='MALE'}">checked="checked"</c:if>>Nam</div> 
    	<div><input type="radio" name="gender-edit-input" id="gender-edit-input-female" value="female" <c:if test="${action.displayedUser.gender=='FEMALE'}">checked="checked"</c:if>>Nữ</div> 
    	<div><input type="radio" name="gender-edit-input" id="gender-edit-input-unknown" value="unknown" <c:if test="${action.displayedUser.gender=='UNKNOWN'}">checked="checked"</c:if>>Không rõ</div> 
</div>
</div>
<div>
Ngày sinh: <b>${action.displayedUser.birthday}</b> <a href="#" id="birthday-edit-button-show" style="display: inline" onclick="showEditField(8);return false;">sửa</a><a href="#" id="birthday-edit-button-hide" style="display: none" onclick="hideEditField(8);return false;">thôi</a>
<div id=birthday-edit-field style="display: none">
ngày-tháng-năm: <input name="birthday-edit-day" id="birthday-edit-day" size="2px" maxlength="2" value="${action.displayedUser.birthday.day}"/>-
<input name="birthday-edit-month" id="birthday-edit-month" size="2px" maxlength="2" value="${action.displayedUser.birthday.month}"/>-
<input name="birthday-edit-year" id="birthday-edit-year" size="4px" maxlength="4" value="${action.displayedUser.birthday.year}"/>	
</div> 
</div>
</fieldset>

<fieldset>
<legend>Thay đổi chi tiết</legend>
<div>
Tự giới thiệu: <b>${action.displayedUser.about}</b> <a href="#" id="about-edit-button-show" style="display: inline" onclick="showEditField(9);return false;">sửa</a><a href="#" id="about-edit-button-hide" style="display: none" onclick="hideEditField(9);return false;">thôi</a>
<div id=about-edit-field style="display: none">
	<textarea name="about-edit-input" id="about-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.about)}</textarea>
</div> 
</div>
<div>
Quê quán: <b>${action.displayedUser.hometown}</b> <a href="#" id="hometown-edit-button-show" style="display: inline" onclick="showEditField(10);return false;">sửa</a><a href="#" id="hometown-edit-button-hide" style="display: none" onclick="hideEditField(10);return false;">thôi</a>
<div id=hometown-edit-field style="display: none">
	<textarea name="hometown-edit-input" id="hometown-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.hometown)}</textarea>
</div> 
</div>
<div>
Địa chỉ: <b>${action.displayedUser.location}</b> <a href="#" id="location-edit-button-show" style="display: inline" onclick="showEditField(11);return false;">sửa</a><a href="#" id="location-edit-button-hide" style="display: none" onclick="hideEditField(11);return false;">thôi</a>
<div id=location-edit-field style="display: none">
	<textarea name="location-edit-input" id="location-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.location)}</textarea>
</div> 
</div>
<div>
Lý lịch: <b>${action.displayedUser.bio}</b> <a href="#" id="bio-edit-button-show" style="display: inline" onclick="showEditField(12);return false;">sửa</a><a href="#" id="bio-edit-button-hide" style="display: none" onclick="hideEditField(12);return false;">thôi</a>
<div id=bio-edit-field style="display: none">
	<textarea name="bio-edit-input" id="bio-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.bio)}</textarea>
</div> 
</div>
</fieldset>

<fieldset>
<legend>Thay đổi liên hệ</legend>
<div>
Email: <b><a href="mailto:${action.displayedUser.email}">${action.displayedUser.email}</a></b> <a href="#" id="email-edit-button-show" style="display: inline" onclick="showEditField(13);return false;">sửa</a><a href="#" id="email-edit-button-hide" style="display: none" onclick="hideEditField(13);return false;">thôi</a>
<div id=email-edit-field style="display: none">
	<input name="email-edit-input" id="email-edit-input" value="${fn:escapeXml(action.displayedUser.email)}"/>
</div> 
</div>
<div>
Website: <b>${action.displayedUser.website}</b> <a href="#" id="website-edit-button-show" style="display: inline" onclick="showEditField(14);return false;">sửa</a><a href="#" id="website-edit-button-hide" style="display: none" onclick="hideEditField(14);return false;">thôi</a>
<div id=website-edit-field style="display: none">
	<input name="website-edit-input" id="website-edit-input" value="${fn:escapeXml(action.displayedUser.website)}"/>
</div> 
</div>
</fieldset>

<fieldset>
<legend>Thay đổi thông tin khác</legend>
<div>
Múi giờ: <b>${action.displayedUser.timezone}</b> <a href="#" id="timezone-edit-button-show" style="display: inline" onclick="showEditField(15);return false;">sửa</a><a href="#" id="timezone-edit-button-hide" style="display: none" onclick="hideEditField(15);return false;">thôi</a>
<div id=timezone-edit-field style="display: none">
	<select name="timezone-edit-input" id="timezone-edit-input">
    	<c:forEach begin = "0" end= "${action.timezone.size-1}" var="current">
	  		<option value="${action.timezone.shortTimezones[current]}" <c:if test="${action.timezone.shortTimezones[current]==action.timezone.shortTimezone}">selected="selected"</c:if>>${action.timezone.timezones[current]}</option>
 		</c:forEach>
    </select>
    <script type="text/javascript">
    var defaultSelected = $('timezone-edit-input').selectedIndex;
    </script>
</div> 
</div>
</fieldset>

  <div id="form_edit"><!--
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
  --><p>
    <input style="margin-left:0%; float:none;" type="submit" name="change-abc" id="change-abc" value="Lưu" />
    <input style="margin-left:0%; float:none;" type="reset" name="resetAll" id="resetAll" value="Hủy bỏ" />
    
  </p>
  </div>
  </fieldset>
</ocw:form>
