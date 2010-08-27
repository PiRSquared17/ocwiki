<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
    
<ul>
 <c:choose>
    <c:when test="${sessionScope.login}">
        <li>Xin chào <a href="${scriptPath}?action=user.edituser" title="Sửa thông tin người dung" target="_self">${sessionScope.user.fullname}</a></li>
        <li><a href="${scriptPath}?action=user.logout" title="Đăng xuất khỏi hệ thống" target="_self">Đăng xuất</a> </li>
        <li><ocw:actionLink name="user.preference">Tuỳ chọn</ocw:actionLink></li>
    </c:when>
    <c:otherwise>
        <li><a href="${scriptPath}?action=user.login" title="Đăng nhập" target="_self">Đăng nhập</a> </li>
        <li><a href="?action=user.signup" title="Đăng kí thành viên trang web" target="_self">Đăng kí</a></li>
        <li><a href="?action=user.forgetpass" title="Khôi phục lại mật khẩu" target="_self">Quên mật khẩu</a></li>
    </c:otherwise>
</c:choose>
</ul>