<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript" src="${templatePath}/js/fieldcheck.js"></script>

<p>Sign Up</p>
<form id="form_edit" name="form1" method="post" action="?action=user.signup">
<c:if test="${error}"><div class="notification"><font color ="red"> Some error need to be fixed: </font><br />
	<c:if test="${invalidUserName}"><font color ="red">  o Invalid User Name! </font><br /></c:if>
	<c:if test="${invalidEmail}"><font color ="red">  o Invalid Email! </font><br /></c:if>
	<c:if test="${invalidPass}"><font color ="red">  o Invalid Password! </font><br /></c:if>
	<c:if test="${userExist}"><font color ="red">  o User Name is not available! </font><br /></c:if>
	<c:if test="${emailUnavailable}"><font color ="red">  o Email is already taken! </font><br /></c:if>
	<c:if test="${sqlError}"><font color ="red">  o Database Error! </font><br /></c:if>
</div></c:if>
  <br />
  <p>
  <label>User Name *
  <input style="width: 250px;" type="text" name="userName" id="userName" value = "${param.userName}" onblur="is_user(document.getElementById('userName'));"  />
  </label>
  </p>
  <br />
  <p>
    <label>Password *
    <input style="width: 250px;" type="password" name="password" id="password" onblur="is_empty(document.getElementById('password'));confirm_pass(document.getElementById('password'),document.getElementById('confirmPass'));" />
    </label>
  </p>
  <br />
  <p>
    <label>Confirm Password *
    <input style="width: 250px;" type="password" name="confirmPass" id="confirmPass" onblur="is_empty(document.getElementById('confirmPass'));confirm_pass(document.getElementById('password'),document.getElementById('confirmPass'));" />
    </label>
</p>
<br />
  <p>
    <label>Full Name *
    <input style="width: 250px;" type="text" name="fullName" id="fullName" value = "${param.fullName}" onblur="is_empty(document.getElementById('fullName'));"/>
    </label>
  </p>
  <br />
  <p>
    <label>E-mail *
    <input style="width: 250px;" type="text" name="userEmail" id="userEmail" value = "${param.userEmail}" onblur="is_email(document.getElementById('userEmail'));"/>
    </label>
  </p>
  <p>&nbsp;</p>
  <p>
    <input style="margin-left:200px; float:none;" type="submit" name="signUp" id="signUp" value="Sign Up" />
    <input style="margin-left:0px; float:none;" type="reset" name="resetAll" id="resetAll" value="Reset All" />
  </p>
  <p><a href="/tracnghiem/" title="home" target="_self">Quay về trang chủ</a></p>
</form>
