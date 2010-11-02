<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:if test="${sessionScope.login}">
	<div>Đánh dấu bài cần làm:
	   <button onclick="markLikeTodoLevel(0)" id="todo">Bài cần làm</button>
	</div>
	<div>Độ khó:
	    <select onchange="markLikeTodoLevel(1)" id="Level">
	        <option label="Bình thường" value="-1">Bình thường</option>
	        <option label="Khó" value="0">Khó</option>
	        <option label="Dễ" value="1">Dễ</option>
	    </select>
	</div>
</c:if>
<div>Số người thích:
	<span id="LikeCount"></span>
	<c:if test="${sessionScope.login}">
	   <button onclick="markLikeTodoLevel(2)" id="Like_button">Thích</button>
	</c:if>
</div>


<script type="text/javascript">
	var resourceID  = ${action.resource.id};
	var resource = {id: resourceID};
	var resourcereport = null;
	var login = ${sessionScope.login};

	Event.observe(window, 'load', function() {
		new Ajax.Request(restPath + '/resource_reports/' + resourceID,
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
				  $('LikeCount').value = resourcereport.likeCount;
			},
			onFailure: function(transport) {
	            DefaultTemplate.onFailure(transport); 
	        }
		});
		if (login)
		new Ajax.Request(restPath + '/resource_customizations/' + resourceID,
				{
					method: 'get',
					requestHeader:{
						Accept: 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						  try{
							  resourcereport = transport.responseJSON.result;
							  var like = resourcereport.like;
							  var todo = resourcereport.todo;
							  if ((like != null)&&(like != 'NORMAL')){
								  $('Like_button').innerHTML = 'Không thích';
								  $('Like_button').value = 'Unlike';
							  }
							  else{
								  $('Like_button').innerHTML = 'Thích';
								  $('Like_button').value = 'Like';
							  }
							  if ((todo != null)&&(todo != 'NORMAL')){
								  $('todo').innerHTML = 'Không cần làm';
								  $('todo').value = 'untodo';
							  }
							  else{
								  $('todo').innerHTML = 'Bài cần làm';
								  $('todo').value = 'todo';
							  }
						  }
						  catch(ex){
							  $('Like_button').innerHTML = 'Thích';
							  $('Like_button').value = 'Like';
							  $('todo').innerHTML = 'Bài cần làm';
							  $('todo').value = 'todo';
						  }
					},
					onFailure: function(transport) {
			            DefaultTemplate.onFailure(transport); 
			        }
				});
	});
	
	function Todo(){
		alert(resourceID);
	}
	
	function markLikeTodoLevel(type){
		var userId = resourcereport.user;
		//var user = {id: userId};
		var level = $('Level').value;
		var like;
		var todo;
		if ($('Like_button').value == 'Like') like = 'LIKE';
		else like = 'NORMAL'; 
		if ($('todo').value == 'todo') todo = 'TODO';
		else todo = 'NORMAL';
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
		new Ajax.Request(restPath + '/resource_customizations/' + resourceID,
			{
				method: 'post',
				requestHeader:{
					Accept: 'application/json'
				},
				contentType: 'application/json',
			    postBody: Object.toJSON(resourcecustomization),
				evalJSON : true,
				onSuccess : function(transport) {
					var count = $('LikeCount').value;
					switch(type){
					case 0: 
							if(todo == 'TODO'){
								$('todo').innerHTML = 'Không cần làm';
								$('todo').value = 'untodo';
							}
							else{
								$('todo').innerHTML = 'Bài cần làm';
								$('todo').value = 'todo';
							}
							break;
					case 1: 
							break;
					case 2: 
							if(like == 'LIKE'){
								count++;
								$('Like_button').innerHTML = 'Không thích';
								$('Like_button').value = 'Unlike';
							}
							else{
								if (count > 0) count--;
								$('Like_button').innerHTML = 'Thích';
								$('Like_button').value = 'Like';
							}
							$('LikeCount').innerHTML = count;
							$('LikeCount').value = count;
							break;
					default:
					}
				},
				onFailure: function(transport) {
		            DefaultTemplate.onFailure(transport); 
		        }
			});
	}
	function Level(){
		alert($('Level').value);
	}
</script>