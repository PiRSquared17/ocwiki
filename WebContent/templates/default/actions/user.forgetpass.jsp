<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="user.forgetpass">
<p>
<c:if test="${userNotExist}"><div class="notification"><font color ="red">  You have not signed up our system! </font></div><br /></c:if>
<c:if test="${invalidEmail}"><div class="notification"><font color ="red">  You have to fill the field with a valid email address! </font></div><br /></c:if>
<c:if test="${passSent}"><div class="notification"><font color ="green">  An Email contains your information has been sent to your email address! </font></div><br /></c:if>
<c:if test="${sqlError}"><div class="notification"><font color ="red">  Database Error! </font></div><br /></c:if>
</p>
<br />
<p>
  <label>Your Email:
  <input type="text" name="userEmail" id="userEmail" />
  </label>
  
    <label>
    <input style="margin-left:200px; float:none;" type="submit" name="sendPassword"" id="sendPassword" value="Send Password" />
    </label>
  </p><br />
  <p><a href="/tracnghiem/" title="home" target="_self">Quay về trang chủ</a></p>
</ocw:form>