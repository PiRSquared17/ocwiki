<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
    	<button type="button" name="btWarning" value="warning" onclick="warningUser()">Cảnh Cáo Người Dùng</button>
    	<c:choose>
    		<c:when test="${action.displayedUser.blocked == 'true'}">
    			<button type="button" name="btUnlock" value="unlock" onclick="unlockUser()">Bỏ Khóa Người Dùng</button>
    		</c:when>
    		<c:otherwise>
    			<button type="button" name="btLock" value="lock" onclick="lockUser()">Khóa Người Dùng</button>
    		</c:otherwise>
		</c:choose>		
    </c:if>
</div>

<div style="display: none" id="warning_dialog">
    <form>
		<p>
	   	<label>Lí do cảnh cáo:
       		<input type="text" name="name" id="message"/>
		</label>
		</p><br>
		<p>
	   	<label>Thời Hạn Cảnh Cáo User:
			<select name="warning_time" id="warning_time">
				<option value="1">1 Ngày</option>
				<option selected="selected" value="3">3 Ngày</option>
				<option value="7">1 Tuần</option>
				<option value="30">1 Tháng</option>
				<option value="365">1 Năm</option>
				<option value="0">Vĩnh Viễn</option>
			</select>
		</label>
		</p>
    </form>
</div>

<div style="display: none" id="lock_dialog">
    <form>
		<p>
	   	<label>Thời Hạn Khóa User:
			<select name="lock_time" id="lock_time">
				<option value="1">1 Ngày</option>
				<option selected="selected" value="3">3 Ngày</option>
				<option value="7">1 Tuần</option>
				<option value="30">1 Tháng</option>
				<option value="365">1 Năm</option>
				<option value="0">Vĩnh Viễn</option>
			</select>
		</label>
		</p>
    </form>
</div>


<script language="javascript">
	var warning_dialog = $('warning_dialog').innerHTML;
	var lock_dialog = $('lock_dialog').innerHTML;
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
			    onFailure: function()
			    { 
					Dialog.alert("UserID không chính xác!", 
						{width:300, height:100, 
						okLabel: "Close", 
						ok:function(win){}
					}); 
			    }
			  });

	function unlockUser()
	{
		user.blocked = false;
		user.blockExpiredDate = "";
		
		new Ajax.Request(restPath + '/users/'+ userID,
				  {
				    method:'post',
				    contentType: 'application/json',
				    postBody: Object.toJSON(user),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) 
					{
						user = transport.responseJSON.result;
						location.reload(true);
					},
				    onFailure: function()
				    {
					    window.setTimeout("Có lỗi xảy ra trong quá trình xử lý)", 1000);
					}
				   });
		return ;
	}
	
	function lockUser()
	{
		var currentTime = new Date();
		var time = currentTime.getTime();
		var diffTime;
		var expiredTime;
		var expiredDate;
		
		Dialog.confirm(lock_dialog, {
			width:300, 
			okLabel: "Ok",
			cancelLabel: "Cancel", 
			buttonClass: "session-button",
			className: "alphacube", 
			cancel:function(win){}, 
			ok: function(win) 
			{ 
				diffTime = $F('lock_time') * 24 * 3600 * 1000 ;
				expiredTime = time + diffTime
				expiredDate = new Date(expiredTime);
				user.blocked = true;
				user.blockExpiredDate = expiredDate;
				
				new Ajax.Request(restPath + '/users/'+ userID,
				  {
				    method:'post',
				    contentType: 'application/json',
				    postBody: Object.toJSON(user),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) 
					{
						user = transport.responseJSON.result;
						location.reload(true);
					},
				    onFailure: function()
				    {
						window.setTimeout("Có lỗi xảy ra trong quá trình xử lý)", 1000);
					}
				   });
				return ;				
			}
		});		
		return;
	}

	function warningUser()
	{	
		var currentTime = new Date();
		var time = currentTime.getTime();
		var diffTime;
		var expiredTime;
		var expiredDate;
		
		Dialog.confirm(warning_dialog, {
			width:300, 
			okLabel: "Ok",
			cancelLabel: "Cancel", 
			buttonClass: "session-button",
			className: "alphacube", 
			cancel:function(win){}, 
			ok: function(win) 
			{
				diffTime = $F('warning_time') * 24 * 3600 * 1000 ; 
				expiredTime = time + diffTime;
				expiredDate = new Date(expiredTime);
				user.warningMessage = $F('message');
				user.warningExpiredDate = expiredDate;
				new Ajax.Request(restPath + '/users/'+ userID,
				  {
				    method:'post',
				    contentType: 'application/json',
				    postBody: Object.toJSON(user),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) 
					{
						user = transport.responseJSON.result;
						location.reload(true);
					},
				    onFailure: function()
				    {
						window.setTimeout("Có lỗi xảy ra trong quá trình xử lý)", 1000);
					}
				   });
				return ;
			}
		});
		return;
	}	
</script>