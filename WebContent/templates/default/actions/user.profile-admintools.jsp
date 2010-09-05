<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
		<button type="button" name="btLock" value="lock" onclick="confirmLock()">Khóa Người Dùng</button>
		<button type="button" name="btWarning" value="warming" onclick="confirmWarning()">Cảnh Cáo Người Dùng</button>
    </c:if>
</div>

<script language="javascript">
	var message;
	var time;
	var userID  = ${action.displayedUser.id} ;
	var user;
	new Ajax.Request(restPath + '/users/'+ userID,
			  {
			    method:'get',
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					user = transport.responseJSON.result;
				},
			    onFailure: function(){ 
				    alert(" UserID không chính xác");}
			  });

	function confirmLock()
	{
		if(confirm("Bạn có muốn khóa user này không ? ")){
			time = prompt("Thời hạn khóa user",'2010-10-25T00:47:13+07:00');
			lockUser(time);
		}
		else
			return ;
	}
	
	function confirmWarning()
	{
		if(confirm("Bạn có muốn cảnh cáo user này không ?")){
			message=prompt("Lí do cảnh cáo","Không hiển thị lí do");
			time=prompt("Thời hạn cảnh cáo user",'2010-10-25T00:47:13+07:00');
			warningUser(message,time);
		}
		else
			return ;
	}
	
	function lockUser(time)
	{
		user.blocked = true;
		user.blockExpiredDate = time;
		
		new Ajax.Request(restPath + '/users/'+ userID,
		  {
		    method:'post',
		    postBody: Object.toJSON(user),
			requestHeaders : {
				Accept : 'application/json'
			},
			evalJSON : true,
			onSuccess : function(transport) {
				alert("Đã thực hiện thành công");
			},
		    onFailure: function(){ 
			    alert("Có lỗi xảy ra trong quá trình xử lý!");}
		   });
		return ;
	}

	function warningUser(message,time)
	{	
		user.warningMessage = message;
		user.warningExpiredDate = time
		new Ajax.Request(restPath + '/users/'+ userID,
		  {
		    method:'post',
		    postBody: Object.toJSON(user),
			requestHeaders : {
				Accept : 'application/json'
			},
			evalJSON : true,
			onSuccess : function(transport) {
				alert("Đã thực hiện thành công");
			},
		    onFailure: function(){ 
			    alert("Có lỗi xảy ra trong quá trình xử lý!");}
		   });
		return ;
	}	
</script>