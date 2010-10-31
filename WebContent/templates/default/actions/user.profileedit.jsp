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
			$('name-view').hide();
		}else if (value==3){
			$('pass-edit-field').show();
			$('pass-edit-button-show').hide();
			$('pass-edit-button-hide').show();
			$('pass-view').hide();
		}else if (value==4){
			$('fullname-edit-field').show();
			$('fullname-edit-button-show').hide();
			$('fullname-edit-button-hide').show();
			$('fullname-view').hide();
		}else if (value==5){
			$('lastname-edit-field').show();
			$('lastname-edit-button-show').hide();
			$('lastname-edit-button-hide').show();
			$('lastname-view').hide();
		}else if (value==6){
			$('firstname-edit-field').show();
			$('firstname-edit-button-show').hide();
			$('firstname-edit-button-hide').show();
			$('firstname-view').hide();
		}else if (value==7){
			$('gender-edit-field').show();
			$('gender-edit-button-show').hide();
			$('gender-edit-button-hide').show();
			$('gender-view').hide();
		}else if (value==8){
			$('birthday-edit-field').show();
			$('birthday-edit-button-show').hide();
			$('birthday-edit-button-hide').show();
			$('birthday-view').hide();
		}else if (value==9){
			$('about-edit-field').show();
			$('about-edit-button-show').hide();
			$('about-edit-button-hide').show();
			$('about-view').hide();
		}else if (value==10){
			$('hometown-edit-field').show();
			$('hometown-edit-button-show').hide();
			$('hometown-edit-button-hide').show();
			$('hometown-view').hide();
		}else if (value==11){
			$('location-edit-field').show();
			$('location-edit-button-show').hide();
			$('location-edit-button-hide').show();
			$('location-view').hide();
		}else if (value==12){
			$('bio-edit-field').show();
			$('bio-edit-button-show').hide();
			$('bio-edit-button-hide').show();
			$('bio-view').hide();
		}else if (value==13){
			$('email-edit-field').show();
			$('email-edit-button-show').hide();
			$('email-edit-button-hide').show();
			$('email-view').hide();
		}else if (value==14){
			$('website-edit-field').show();
			$('website-edit-button-show').hide();
			$('website-edit-button-hide').show();
			$('website-view').hide();
		}else if (value==15){
			$('timezone-edit-field').show();
			$('timezone-edit-button-show').hide();
			$('timezone-edit-button-hide').show();
			$('timezone-view').hide();
		}else{
		}
	}
	
	function hideEditField(value){
		if (value==2){
			$('name-edit-field').hide();
			$('name-edit-button-show').show();
			$('name-edit-button-hide').hide();			
			$('name-edit-input').value='';
			$('name-view').show();
		}else if (value==3){
			$('pass-edit-field').hide();
			$('pass-edit-button-show').show();
			$('pass-edit-button-hide').hide();	
			$('pass-edit-old').value='';		
			$('pass-edit-input').value='';
			$('pass-edit-confirm').value='';
			$('pass-view').show();
		}else if (value==4){
			$('fullname-edit-field').hide();
			$('fullname-edit-button-show').show();
			$('fullname-edit-button-hide').hide();	
			$('fullname-edit-input').selectedIndex=defaultNameOrderSelected;
			$('fullname-view').show();
		}else if (value==5){
			$('lastname-edit-field').hide();
			$('lastname-edit-button-show').show();
			$('lastname-edit-button-hide').hide();			
			$('lastname-edit-input').value=$('lastname-edit-input').defaultValue;
			$('lastname-view').show();
		}else if (value==6){
			$('firstname-edit-field').hide();
			$('firstname-edit-button-show').show();
			$('firstname-edit-button-hide').hide();			
			$('firstname-edit-input').value=$('firstname-edit-input').defaultValue;
			$('firstname-view').show();
		}else if (value==7){
			$('gender-edit-field').hide();
			$('gender-edit-button-show').show();
			$('gender-edit-button-hide').hide();
			$('gender-edit-input').selectedIndex=defaultGenderSelected;	
			$('gender-view').show();		
		}else if (value==8){
			$('birthday-edit-field').hide();
			$('birthday-edit-button-show').show();
			$('birthday-edit-button-hide').hide();	
			$('birthday-edit-day').value=$('birthday-edit-day').defaultValue;		
			$('birthday-edit-month').value=$('birthday-edit-month').defaultValue;
			$('birthday-edit-year').value=$('birthday-edit-year').defaultValue;
			$('birthday-view').show();
		}else if (value==9){
			$('about-edit-field').hide();
			$('about-edit-button-show').show();
			$('about-edit-button-hide').hide();	
			tinyMCE.get('about-edit-input').load($('about-edit-input'));
			$('about-view').show();
		}else if (value==10){
			$('hometown-edit-field').hide();
			$('hometown-edit-button-show').show();
			$('hometown-edit-button-hide').hide();	
			tinyMCE.get('hometown-edit-input').load($('hometown-edit-input'));
			$('hometown-view').show();
		}else if (value==11){
			$('location-edit-field').hide();
			$('location-edit-button-show').show();
			$('location-edit-button-hide').hide();	
			tinyMCE.get('location-edit-input').load($('location-edit-input'));
			$('location-view').show();
		}else if (value==12){
			$('bio-edit-field').hide();
			$('bio-edit-button-show').show();
			$('bio-edit-button-hide').hide();	
			tinyMCE.get('bio-edit-input').load($('bio-edit-input'));
			$('bio-view').show();
		}else if (value==13){
			$('email-edit-field').hide();
			$('email-edit-button-show').show();
			$('email-edit-button-hide').hide();			
			$('email-edit-input').value=$('email-edit-input').defaultValue;
			$('email-view').show();
		}else if (value==14){
			$('website-edit-field').hide();
			$('website-edit-button-show').show();
			$('website-edit-button-hide').hide();			
			$('website-edit-input').value=$('website-edit-input').defaultValue;
			$('website-view').show();
		}else if (value==15){
			$('timezone-edit-field').hide();
			$('timezone-edit-button-show').show();
			$('timezone-edit-button-hide').hide();			
			$('timezone-edit-input').selectedIndex=defaultTimezoneSelected;
			$('timezone-view').show();
		}else{
		}
	}
</script>
	
<h2>Thay đổi thông tin của ${action.displayedUser.fullname}</h2>
<ocw:form action="user.profileedit" id="form1" method="post">
<input type="hidden" name="action" value="Sign Up" >
<c:if test="${(action.success == true)}">
	<div class="notification"><font color ="green">  Thay đổi thành công! </font></div>
</c:if>
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
		<table>
			<tr>
				<td width="100px">
					Hình đại diện:  
				</td>
				<td>
					<img alt="Hình đại diện" src="${action.displayedUser.avatar}"/>
				</td>
			</tr>
			<tr>
				<td>
					Tên người dùng: 
				</td> 
				<td> 
					<span id=name-view style="display: inline"><b>${action.displayedUser.name}</b></span>
					<c:if test="${action.displayedUser.name}"> 
						<span id=name-edit-field style="display: none">
							<input name="name-edit-input" id="name-edit-input" value=""/>			
							<a href="#" id="name-edit-button-hide" style="display: none" onclick="hideEditField(2);return false;">thôi</a>
							<span class="notification">Lưu ý, bạn chỉ được thay đổi tên người dùng 1 lần duy nhất.</span>					
						</span>
						<a href="#" id="name-edit-button-show" style="display: inline" onclick="showEditField(2);return false;">sửa</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					Mật khẩu:
				</td><td>
					<span id=pass-view style="display: inline"><b>******</b></span>
					<span id=pass-edit-field style="display: none">
						<table>
							<tr>
								<td width="200px">
									Mật khẩu mới: 
								</td><td>
									<input type="password" name="pass-edit-input" id="pass-edit-input"/>
									<a href="#" id="pass-edit-button-hide" style="display: none" onclick="hideEditField(3);return false;">thôi</a>
								</td>
							</tr>
							<tr>
								<td>
									Xác nhận mật khẩu: 
								</td><td>
									<input type="password" name="pass-edit-confirm" id="pass-edit-confirm"/>
								</td>
							</tr>
							<tr>
								<td>
									Mật khẩu cũ:         
								</td><td>
									<input type="password" name="pass-edit-old" id="pass-edit-old"/>
								</td>
							</tr>
						</table>
					</span>
					<a href="#" id="pass-edit-button-show" style="display: inline" onclick="showEditField(3);return false;">sửa</a>
				</td> 
			</tr>
		</table>
	</fieldset>
	
	<fieldset>
	<legend>Thay đổi thông tin cá nhân</legend>
		<table>
			<tr>
				<td width="100px">
					Tên đầy đủ: 
				</td><td>
					<span id="fullname-view" style="display: inline"><b>${action.displayedUser.fullname}</b></span> 
					<span id=fullname-edit-field style="display: none">
						<select name="fullname-edit-input" id="fullname-edit-input">
							<option value="firstLast" <c:if test="${action.displayedUser.nameOrdering=='FIRST_LAST'}">selected="selected"</c:if>>Tên-Họ (${action.displayedUser.firstName} ${action.displayedUser.lastName})</option>
							<option value="lastFirst" <c:if test="${action.displayedUser.nameOrdering=='LAST_FIRST'}">selected="selected"</c:if>>Họ-Tên (${action.displayedUser.lastName} ${action.displayedUser.firstName})</option>
						</select>
						<script type="text/javascript">
					    	var defaultNameOrderSelected = $('fullname-edit-input').selectedIndex;
					    </script>
					</span> 
					<a href="#" id="fullname-edit-button-show" style="display: inline" onclick="showEditField(4);return false;">sửa</a><a href="#" id="fullname-edit-button-hide" style="display: none" onclick="hideEditField(4);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Họ: 
				</td><td>
					<span id="lastname-view" style="display: inline"><b>${action.displayedUser.lastName}</b></span> 
					<span id=lastname-edit-field style="display: none">
						<input name="lastname-edit-input" id="lastname-edit-input" value="${fn:escapeXml(action.displayedUser.lastName)}"/>	
					</span> 
					<a href="#" id="lastname-edit-button-show" style="display: inline" onclick="showEditField(5);return false;">sửa</a><a href="#" id="lastname-edit-button-hide" style="display: none" onclick="hideEditField(5);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Tên: 
				</td><td>
					<span id="firstname-view" style="display: inline"><b>${action.displayedUser.firstName}</b></span> 
					<span id=firstname-edit-field style="display: none">
						<input name="firstname-edit-input" id="firstname-edit-input" value="${fn:escapeXml(action.displayedUser.firstName)}"/>	
					</span> <a href="#" id="firstname-edit-button-show" style="display: inline" onclick="showEditField(6);return false;">sửa</a><a href="#" id="firstname-edit-button-hide" style="display: none" onclick="hideEditField(6);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Giới tính: 
				</td><td>
					<span id="gender-view" style="display: inline">
						<b>
							<c:if test="${action.displayedUser.gender=='MALE'}">Nam</c:if>
							<c:if test="${action.displayedUser.gender=='FEMALE'}">Nữ</c:if>
							<c:if test="${action.displayedUser.gender=='UNKNOWN'}">Không rõ</c:if>
						</b>
					</span> 
					<span id=gender-edit-field style="display: none">
						<select name="gender-edit-input" id="gender-edit-input">
							<option value="male" <c:if test="${action.displayedUser.gender=='MALE'}">selected="selected"</c:if>>Nam</option>
							<option value="female" <c:if test="${action.displayedUser.gender=='FEMALE'}">selected="selected"</c:if>>Nữ</option>
							<option value="unknown" <c:if test="${action.displayedUser.gender=='UNKNOWN'}">selected="selected"</c:if>>Không rõ</option>
						</select>
						<script type="text/javascript">
					    	var defaultGenderSelected = $('gender-edit-input').selectedIndex;
					    </script>
					</span>
					<a href="#" id="gender-edit-button-show" style="display: inline" onclick="showEditField(7);return false;">sửa</a><a href="#" id="gender-edit-button-hide" style="display: none" onclick="hideEditField(7);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Ngày sinh: 
				</td><td>
					<span id="birthday-view" style="display: inline"><b>${u:formatDate(action.displayedUser.birthday)}</b></span> 
					<span id=birthday-edit-field style="display: none">
						<input name="birthday-edit-day" id="birthday-edit-day" size="2px" maxlength="2" value="${action.displayedUser.birthday.date}"/>-
						<input name="birthday-edit-month" id="birthday-edit-month" size="2px" maxlength="2" value="${action.displayedUser.birthday.month+1}"/>-
						<input name="birthday-edit-year" id="birthday-edit-year" size="4px" maxlength="4" value="${action.displayedUser.birthday.year+1900}"/>	
						(ngày-tháng-năm)
					</span> <a href="#" id="birthday-edit-button-show" style="display: inline" onclick="showEditField(8);return false;">sửa</a><a href="#" id="birthday-edit-button-hide" style="display: none" onclick="hideEditField(8);return false;">thôi</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset>
	<legend>Thay đổi chi tiết</legend>
		<table>
			<tr>
				<td width="100px">
					Tự giới thiệu: 
				</td><td>
					<span id=about-view style="display: inline">${action.displayedUser.about}</span> 
					<span id=about-edit-field style="display: none">
						<textarea name="about-edit-input" id="about-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.about)}</textarea>
					</span> 
					<a href="#" id="about-edit-button-show" style="display: inline" onclick="showEditField(9);return false;">sửa</a><a href="#" id="about-edit-button-hide" style="display: none" onclick="hideEditField(9);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Quê quán: 
				</td><td>
					<span id=hometown-view style="display: inline">${action.displayedUser.hometown}</span> 
					<span id=hometown-edit-field style="display: none">
						<textarea name="hometown-edit-input" id="hometown-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.hometown)}</textarea>
					</span> 
					<a href="#" id="hometown-edit-button-show" style="display: inline" onclick="showEditField(10);return false;">sửa</a><a href="#" id="hometown-edit-button-hide" style="display: none" onclick="hideEditField(10);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Địa chỉ: 
				</td><td>
					<span id=location-view style="display: inline">${action.displayedUser.location}</span> 
					<span id=location-edit-field style="display: none">
						<textarea name="location-edit-input" id="location-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.location)}</textarea>
					</span> 
					<a href="#" id="location-edit-button-show" style="display: inline" onclick="showEditField(11);return false;">sửa</a><a href="#" id="location-edit-button-hide" style="display: none" onclick="hideEditField(11);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Lý lịch: 
				</td><td>
					<span id=bio-edit-field style="display: inline">${action.displayedUser.bio}</span> 
					<span id=bio-edit-field style="display: none">
						<textarea name="bio-edit-input" id="bio-edit-input" cols="45" rows="5">${fn:escapeXml(action.displayedUser.bio)}</textarea>
					</span> 
					<a href="#" id="bio-edit-button-show" style="display: inline" onclick="showEditField(12);return false;">sửa</a><a href="#" id="bio-edit-button-hide" style="display: none" onclick="hideEditField(12);return false;">thôi</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset>
	<legend>Thay đổi liên hệ</legend>
		<table>
			<tr>
				<td width="100px">
					Email: 
				</td><td>
					<span id=email-view style="display: inline"><a href="mailto:${action.displayedUser.email}">${action.displayedUser.email}</a></span>
					<span id=email-edit-field style="display: none">
						<input name="email-edit-input" id="email-edit-input" value="${fn:escapeXml(action.displayedUser.email)}"/>
					</span> 
					<a href="#" id="email-edit-button-show" style="display: inline" onclick="showEditField(13);return false;">sửa</a><a href="#" id="email-edit-button-hide" style="display: none" onclick="hideEditField(13);return false;">thôi</a>
				</td>
			</tr>
			<tr>
				<td>
					Website:
				</td><td>
					<span id=website-view style="display: inline">${action.displayedUser.website}</span>
					<span id=website-edit-field style="display: none">
						<input name="website-edit-input" id="website-edit-input" value="${fn:escapeXml(action.displayedUser.website)}"/>
					</span> 
					<a href="#" id="website-edit-button-show" style="display: inline" onclick="showEditField(14);return false;">sửa</a><a href="#" id="website-edit-button-hide" style="display: none" onclick="hideEditField(14);return false;">thôi</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset>
	<legend>Thay đổi thông tin khác</legend>
		<table>
			<tr>
				<td width="100px">
					Múi giờ: 
				</td><td>
					<span id=timezone-view style="display: inline">${action.timezone.timezone}</span>
					<span id=timezone-edit-field style="display: none">
						<select name="timezone-edit-input" id="timezone-edit-input">
					    	<c:forEach begin = "0" end= "${action.timezone.size-1}" var="current">
						  		<option value="${action.timezone.shortTimezones[current]}" <c:if test="${action.timezone.shortTimezones[current]==action.timezone.shortTimezone}">selected="selected"</c:if>>${action.timezone.timezones[current]}</option>
					 		</c:forEach>
					    </select>
					    <script type="text/javascript">
					    	var defaultTimezoneSelected = $('timezone-edit-input').selectedIndex;
					    </script>
				    </span>
				    <a href="#" id="timezone-edit-button-show" style="display: inline" onclick="showEditField(15);return false;">sửa</a><a href="#" id="timezone-edit-button-hide" style="display: none" onclick="hideEditField(15);return false;">thôi</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<div id="form-buttons">
		<p>
			<button style="margin-left:0%; float:none;" type="submit" name="change" id="change" value="change" >Lưu</button>
			<button style="margin-left:0%; float:none;" type="reset" name="resetAll" id="resetAll" value="reset">Hủy bỏ</button>
		</p>
	</div>
</fieldset>
</ocw:form>
