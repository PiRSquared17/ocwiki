<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<input type="hidden" name="userID" id="userID" value="${action.displayedUser.id}" >
<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
    	<c:set></c:set>
		<button type="button" name="btLock" value="lock" onclick="confirmLock()">Khóa Người Dùng</button>
		<button type="button" name="btWarning" value="warming" onclick="confirmWarning()">Cảnh Báo Người Dùng</button>
    </c:if>
</div>

<script language="javascript">

	function comfirmLock()
	{
		if(confirm("Ban co muon khoa user nay khong")){
			lockUser(123);
			alert("Hanh dong da duoc thuc hien");
		}
		else
			return;
	}

	function confirmWarning()
	{
		if(confirm("Ban co muon canh cao user nay khong")){
			var message=prompt("Li do canh cao","Khong li do");
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