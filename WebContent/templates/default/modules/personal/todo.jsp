<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:if test="${sessionScope.login}">
	<div>Đánh dấu bài cần làm:
	<button onclick="Li_To_Le(0)" id="todo">Bài cần làm</button>
	</div>
	<div>Độ khó:
		<select onchange="Li_To_Le(1)" id="Level">
			<option label="Khó" value="0">Khó</option>
			<option label="Dễ" value="1">Dễ</option>
		</select>
	</div>
	<div>Số người thích:
		<span id="LikeCount"></span>
		<button onclick="Li_To_Le(2)" id="Like_button">Thích</button>
	</div>
</c:if>
<script type="text/javascript">
	var resourceID  = ${action.resource.id};
	var resource = {id: resourceID};
	var resourcereport = null;
	var login = ${sessionScope.login};
	new Ajax.Request(restPath + '/ResourceReport/' + resourceID,
	{
		method: 'get',
		requestHeader:{
			Accept: 'application/json'
		},
		evalJSON : true,
		onSuccess : function(transport) {
			  resourcereport = transport.responseJSON.result;
			  var userId = resourcereport.user;
			  $('LikeCount').innerHTML = resourcereport.likeCount;
		},
		onFailure: function(){
		  alert('Fail'); }
	});
	if (login)
	new Ajax.Request(restPath + '/LikeArticle/' + resourceID,
			{
				method: 'get',
				requestHeader:{
					Accept: 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					  resourcereport = transport.responseJSON.result;
					  var like = resourcereport.like;
					  var todo = resourcereport.todo;
					  if (like != null){
						  $('Like_button').hide();
					  }
					  else{
						  $('Like_button').show();
					  }
					  if (todo == null){
						  $('todo').innerHTML = 'Bài cần làm';
					  }
					  else{
						  $('todo').innerHTML = 'Đã làm xong';
					  }
				},
				onFailure: function(transport){
				  alert('Fail'); }
			});
	function Todo(){
		alert(resourceID);
	}
	function Li_To_Le(type){
		var userId = resourcereport.user;
		//var user = {id: userId};
		var level = $('Level').value;
		var like = 'LIKE';
		var todo = 'TODO';
		//var resourcecustomization={'user':{id: userId},'resource':{id: resourceID},'level':level,'like':like,'todo': todo};
		var resourcecustomization;
		switch(type){
			case 0: 
					resourcecustomization={'user':{id: userId},'resource':{id: resourceID},'todo': todo};
					break;
			case 1: 
					//level = $('Level').value;
					resourcecustomization={'user':{id: userId},'resource':{id: resourceID},'level':level};
					break;
			case 2: 
					resourcecustomization={'user':{id: userId},'resource':{id: resourceID},'like':like};
					break;
			default:
		}
		new Ajax.Request(restPath + '/LikeArticle/' + resourceID,
				{
			method: 'post',
			requestHeader:{
				Accept: 'application/json'
			},
			contentType: 'application/json',
		    postBody: Object.toJSON(resourcecustomization),
			evalJSON : true,
			onSuccess : function(transport) {
				  aler('Ok!');
			},
			onFailure: function(){
			  alert('Fail'); }
		});
	}
	function Level(){
		alert($('Level').value);
	}
</script>