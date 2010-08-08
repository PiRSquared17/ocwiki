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
</script>
	
<p>Change User Information</p>
<form class="user" id="form1" name="form1" method="post" action="?action=user.edituser">
<input type="hidden" name="action" value="Sign Up" ></input>
<p>
<c:if test="${error}"><div class="notification"><font color ="red"> Some error need to be fixed: </font><br />
	<c:if test="${invalidUserName}"><font color ="red">  o Invalid User Name! </font><br /></c:if>
	<c:if test="${invalidEmail}"><font color ="red">  o Invalid Email! </font><br /></c:if>
	<c:if test="${wrongPass}"><font color ="red">  o Wrong Password! </font><br /></c:if>
	<c:if test="${invalidPass}"><font color ="red">  o Invalid Password! </font><br /></c:if>
	<c:if test="${userExist}"><font color ="red">  o User Name is not available! </font><br /></c:if>
	<c:if test="${emailUnavailable}"><font color ="red">  o Email is already taken! </font><br /></c:if>
	<c:if test="${dbError}"><font color ="red">  o Database Error! </font><br /></c:if>
	<c:if test="${sqlError}"><font color ="red">  o Database Error! </font><br /></c:if>
</div></c:if>
<c:if test="${success}"><div class="notification"><font color ="green">  Thay đổi thành công! </font></div><br /></c:if>
</p>
  <br />
  <div id="form_edit">
  <p>
   User: ${userName }<br />
   Email: ${sessionScope.user.email} <br />
   Full Name: ${userFullName }<br />
   </p>
 <br />
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
  <br />
  <p>&nbsp;</p>
  <p>
    <input style="margin-left:200px; float:none;" type="submit" name="change" id="change" value="Change" />
    <input style="margin-left:0px; float:none;" type="reset" name="resetAll" id="resetAll" value="Reset All" />
    
  </p>
  <p>
    <label></label>
  </p>
  </div>
</form>
<p>&nbsp; </p>
