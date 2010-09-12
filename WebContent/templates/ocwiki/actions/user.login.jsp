<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form id="form_edit" name="form1" method="post" action="${ocw:actionUrl('user.login')}">
	<c:if test="${not empty message}"><div class="notification"><font color ="red">${message}</font></div></c:if>
	<c:if test="${error}"><div class="notification"><font color ="red"> Some field[s] is[are] missing: </font><br />
		<c:if test="${userNameMissing}"><font color ="red">  o User Name </font><br /></c:if>
		<c:if test="${passMissing}"><font color ="red">  o Password </font><br /></c:if>
	</div></c:if>
	<c:if test="${wrongPass}"><div class="notification"><font color="red"> Sai mật khẩu!</font></div><br /></c:if>
	<c:if test="${wrongUser}"><div class="notification"><font color="red"> Người dùng không tồn tại!</font></div><br /></c:if>
	<c:if test="${sqlError}"><div class="notification"><font color ="red">  Database Error! </font></div><br /></c:if>
	
	<input type="hidden" name="action" value="user.login">
	<input type="hidden" name="returnAction" value="${param.returnAction}">
	
  <br />
  <p>
  <label>Người dùng:
  <input type="text" name="userName" id="userName" value="${param.userName}"/>
  </label>
  </p>
  <p></p>
  <br />
  <p>
    <label>Mật khẩu:
    <input type="password" name="password" id="password" />
    </label>
   </p>
    <br />
  <p>
    <label>
    <input type="submit" name="logIn" value="Log In" id="logIn" value="Đăng nhập" />
    </label>
  </p>
  <br />
  <p>
    <ocw:actionLink name="user.forgetpass">Quên mật khẩu?</ocw:actionLink>
    <ocw:actionLink name="user.signup">Đăng kí</ocw:actionLink>
  </p>
  <p><a href="/tracnghiem/" title="home" target="_self">Quay về trang chủ</a></p>
</form>
