<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<div class="toolbar">
	<c:if test="${sessionScope.login && sessionScope.user.group == 'admin' && action.resource.status != 'DELETED'}">
		<c:choose>
    		<c:when test="${action.resource.accessibility != 'EVERYONE'}">
    			<button type="button" name="btUnlock" value="unlock" onclick="unlockArticle()">Mở Khóa Bài Viết</button>
    		</c:when>
    		<c:otherwise>
    			<button type="button" name="btLock" value="lock" onclick="lockArticle()">Khóa Bài Viết</button>
    		</c:otherwise>
		</c:choose>	
	</c:if>
</div>

<div style="display: none" id="lock_dialog">
    <form>
		<p>
	   	<label>Đối tượng cho phép truy cập:<br>
			<select name="lock_value" id="lock_value">
				<option value="EVERYONE">Tất cả</option>
				<option value="AUTHOR_ONLY">Chỉ Có Tác Giả</option>
				<option value="NO_ONE">Chỉ có Admin</option>
			</select>
		</label>
		</p>
    </form>
</div>

<script language="javascript">
	var lock_dialog = $('lock_dialog').innerHTML;
	var resource;
	var resourceID = ${action.resource.id};
	var timeout;

	function initEditTools() {
		new Ajax.Request(restPath + '/resource/'+ resourceID, {
			method:'get',
			requestHeaders : 
			{
				Accept : 'application/json'
			},
			evalJSON : true,
			onSuccess : function(transport) 
			{
				resource = transport.responseJSON.result;
			},
			onFailure: function()
			{ 
				openInfoDialog("resourceID không chính xác!");
			}
		});
	}

	function lockArticle()
	{
		Dialog.confirm(lock_dialog, {
			width:300, 
			okLabel: "Ok",
			cancelLabel: "Cancel", 
			buttonClass: "session-button",
			className: "alphacube", 
			cancel:function(win){}, 
			ok: function(win) 
			{	
				resource.accessibility = $F('lock_value');
				new Ajax.Request(restPath + '/resource/'+ resourceID,
					{
					method:'post',
					contentType: 'application/json',
					postBody: Object.toJSON(resource),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) 
						{
							resource = transport.responseJSON.result;
							location.reload(true);
						},
					onFailure: function()
						{
							openInfoDialog("Có người đã sửa tài nguyên này trước bạn, hãy tải lại trang!");
						}
				});
			}
		});
		return;
	}

	function unlockArticle()
	{
		resource = {accessibility : 'EVERYONE', status : 'NORMAL'};
		new Ajax.Request(restPath + '/resource/'+ resourceID,
			{
				method:'post',
				contentType: 'application/json',
				postBody: Object.toJSON(resource),
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) 
				{
					resource = transport.responseJSON.result;
					location.reload(true);
				},
				onFailure: function()
				{
					openInfoDialog("Có người đã sửa tài nguyên này trước bạn, hãy tải lại trang!");
				}
			});
		return ;
	}

	function openInfoDialog(info) {
		Dialog.info(info + "<br> Thông báo tự động đóng sau 2 giây ...",
	               {width:300, height:100, className: "alphacube",showProgress: true});
	  	timeout=2;
	  	setTimeout(infoTimeout, 1000);
	}

	function infoTimeout() {
	  	timeout--;
	  	if (timeout >0) {
	    	Dialog.setInfoMessage("Thông báo tự động đóng sau " + timeout + "giây ...");
	    	setTimeout(infoTimeout, 1000);
	 	}
	 	else
	  		Dialog.closeInfo();
	}

	Event.observe(window, 'load', initEditTools);
</script>