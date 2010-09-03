<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
		<button type="button" name="btLock" value="lock" onclick="confirmLock()">Khóa Người Dùng</button>
		<button type="button" name="btWarning" value="warming" onclick="confirmWarning()">Cảnh Báo Người Dùng</button>
    </c:if>
    <c:set var="userID" value="${action.displayedUser.id}"></c:set>
</div>

<script language="javascript">

	function comfirmLock()
	{
		if(confirm("Bạn có muốn khóa user này không ? ")){
			lockUser(${action.displayedUser.id});
			alert("Hành động thực hiện thành công.");
		}
		else
			return;
	}
	
	function confirmWarning()
	{
		if(confirm("Bạn có muốn cảnh cáo user này không ?")){
			var message=prompt("Lí do cảnh cáo","Không hiển thị lí do");
			warningUser(document.getElementByID("userID").value, message);
		}
		else
			return;
	}
	
	function lockUser(userID)
	{
		
	}

	function warningUser(userID,message)
	{
		
	}
	
</script>