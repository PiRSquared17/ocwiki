<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="user.login" method="post">
  <p>
  <label>Người dùng:
  <input type="text" name="userName" id="userName" value="${param.userName}"/>
  </label>
  <ocw:error code="name"></ocw:error>
  </p>
  <p></p>
  <br />
  <p>
    <label>Mật khẩu:
    <input type="password" name="password" id="password" />
    </label>
  <ocw:error code="pass"></ocw:error>
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
</ocw:form>