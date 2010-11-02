<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<h3>
Người dùng: ${action.displayedUser.name}
</h3> 

<jsp:include page="user.profile-admintools.jsp"></jsp:include>

<div class="clear"></div>

<c:if test="${not empty action.displayedUser.warningMessage}">
	<div class="notification">Tài khoản bị cảnh cáo. 
	   Lí do: ${action.displayedUser.warningMessage}  
	</div>
</c:if>

<c:if test="${action.displayedUser.blocked}">
	<div class="notification">Tài khoản bị khoá
	   <c:choose>
	       <c:when test="${empty action.displayedUser.blockExpiredDate}">vĩnh viễn</c:when>
	       <c:otherwise>đến ${u:formatDateTime(action.displayedUser.blockExpiredDate)}</c:otherwise>
	   </c:choose> 
	</div>
</c:if>

<fieldset>
<legend>Thông tin</legend>
<p>
Tên đầy đủ: <b>${action.displayedUser.fullname}</b> 
</p>
<p>
Ngày đăng kí: <b>${u:formatDateTime(action.displayedUser.registerDate)}</b>
</p>
<p>
Email: <b><a href="mailto:${action.displayedUser.email}">${action.displayedUser.email}</a></b>
</p>
<p>
Nhóm: <b>${action.displayedUser.group}</b>
<c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
    <i><a href="${scriptPath}?action=user.editgroup&user=${action.displayedUser.id}">sửa</a></i>
</c:if>
</p>
</fieldset>

<fieldset>
<legend>Đề tạo gần đây</legend>
	<c:choose>
	<c:when test="${u:size(action.tests) > 0}">
		<ul>
		    <c:forEach items="${action.tests}" var="test">
		        <li><ocw:articleLink resource="${test}">${test.name}</ocw:articleLink>; 
		            ${u:formatDateTime(test.createDate)}
		        </li>
			</c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
	    <i>&lt;Không có dữ liệu&gt;</i>
	</c:otherwise>
	</c:choose>
</fieldset>

<fieldset>
<legend>Đề làm gần đây</legend>
	<c:choose>
	<c:when test="${u:size(action.histories) > 0}">
		<ul>
		    <c:forEach items="${action.histories}" var="history">
		        <li><a href="${scriptPath}?action=history.view&id=${history.id}">${history.test.name}</a>; 
		            ${u:formatDateTime(history.takenDate)})
		        </li>
		    </c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
	    <i>&lt;Không có dữ liệu&gt;</i>
	</c:otherwise>
	</c:choose>
</fieldset>

<fieldset>
<legend>Thống kê thảo luận</legend>
	<div>Số lượng nhận xét đã đăng: <b>${action.postedComments}</b></div>
	<div>Số lượt nhận xét được thích: <b>${action.likedComments}</b></div>
	<div>Số lượt nhận xét bị ẩn: <b>${action.hiddenComments}</b></div>
</fieldset>
