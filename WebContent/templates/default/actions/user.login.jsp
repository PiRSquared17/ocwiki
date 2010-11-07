<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="user.login" method="post">
  <h3>Đăng nhập với tài khoản OCWiki</h3>

  <br />
  <p>
  <table>
  <tr>
    <td><label for="userName">Người dùng:</label></td>
    <td>
        <input type="text" name="userName" id="userName" value="${param.userName}"/>
        <ocw:error code="name"></ocw:error>
    </td>
  </tr>
  <tr>
    <td><label for="password">Mật khẩu:</label></td>
    <td>
        <input type="password" name="password" id="password" />
        <ocw:error code="pass"></ocw:error>
    </td>
  </tr>
  <tr>
    <td><button type="submit" name="logIn" value="Log In" id="logIn">Đăng nhập</button></td>
  </tr>
</table>
</ocw:form>

<br />
<br />

<h3>Hoặc đăng nhập bằng OpenID</h3>
<a href="javascript:openOpenIDLoginDialog()">Đăng nhập bằng OpenID</a>