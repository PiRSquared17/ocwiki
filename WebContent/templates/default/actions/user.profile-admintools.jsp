<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<div class="toolbar">
    <c:if test="${sessionScope.login && sessionScope.user.group == 'admin'&& action.displayedUser.group != 'admin'}">
    	<c:choose>
    		<c:when test="${not empty action.displayedUser.warningMessage}">
    			<button type="button" name="btRemoveWarning" value="removeWarning" onclick="removeWarningUser()">Bỏ Cảnh Cáo</button>
    		</c:when>
    		<c:otherwise>
    			<button type="button" name="btWarning" value="warning" onclick="warningUser()">Cảnh Cáo</button>
    		</c:otherwise>
    	</c:choose>
    	   	
    	<c:choose>
    		<c:when test="${action.displayedUser.blocked == 'true'}">
    			<button type="button" name="btUnlock" value="unlock" onclick="unlockUser()">Bỏ Khóa</button>
    		</c:when>
    		<c:otherwise>
    			<button type="button" name="btLock" value="lock" onclick="lockUser()">Khóa</button>
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
		<br>
		<span class="error-validating" id="message_error"></span>
		</p>
		<br>	
		<p>
	   	<label>Thời Hạn Cảnh Cáo:
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
	   	<label>Thời Hạn Khóa:
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
	var timeout;
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
					openInfoDialog("UserID không chính xác!");
			    }
			  });

	
	function openInfoDialog(info) {
		Dialog.info(info + "<br> Thông báo tự động đóng sau 2 giây ...",
	               {width:300, height:100, className: "alphacube",showProgress: true});
	  	timeout=2;
	  	setTimeout(infoTimeout, 1000);
	}

	function infoTimeout() {
	  	timeout--;
	  	if (timeout >0) {
	    	Dialog.setInfoMessage(info + "<br> Thông báo tự động đóng sau " + timeout + "giây ...");
	    	setTimeout(infoTimeout, 1000);
	 	}
	 	else
	  		Dialog.closeInfo();
	}
		
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
						openInfoDialog("Có người đã sửa tài khoản này trước bạn, hãy tải lại trang!");
					}
				   });
		return true;
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
				if($F('lock_time') != 0)
				{ 
					diffTime = $F('lock_time') * 24 * 3600 * 1000 ;
					expiredTime = time + diffTime
					expiredDate = new Date(expiredTime);					
					user.blockExpiredDate = expiredDate;
				}
				else
					user.blockExpiredDate = '';
				user.blocked = true;
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
						openInfoDialog("Có người đã sửa tài khoản này trước bạn, hãy tải lại trang!");
					}
				   });
			}
		});		
		return true;
	}

	function removeWarningUser()
	{
		user.warningMessage = "";
		user.warningExpiredDate = "";
		
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
						openInfoDialog("Có người đã sửa tài khoản này trước bạn, hãy tải lại trang!");
					}
				   });
		return true;
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
			ok: function(win) {
				if ($F('message') == '')
				{	
					$('message_error').innerHTML = 'Bạn cần nhập lí do cảnh cáo!';				
					return false;
				}
				else
				{	
					if($F('warning_time') != 0)
					{
						diffTime = $F('warning_time') * 24 * 3600 * 1000 ; 
						expiredTime = time + diffTime;
						expiredDate = new Date(expiredTime);						
						user.warningExpiredDate = expiredDate;
					}
					else
						user.warningExpiredDate = '';
					user.warningMessage = $F('message');
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
							openInfoDialog("Có người đã sửa tài khoản này trước bạn, hãy tải lại trang!");
						}
					});
				}	
			}
		});
		return true;
	}	
</script>