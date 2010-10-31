<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var = "topics" value = "${test.topics}"></c:set>

<div>
	<c:forEach var="topic" items = "${topics}">
		<div id="topic-${topic.id}">
			${topic.name}
			<a href="" onclick = "Delete(${topic.id}); return false" id="Delete-${topic.id}"> Xóa</a>
		</div>
	</c:forEach>
	<span id = "add-topic-content"></span>
	<form>
		<button type="submit" id = "add-topic-button" onclick = "Add(); return false;">Thêm chủ đề mới</button>
		<input type="text" id="add-content-topic">
		<span id = "Message-topic"></span>
	</form>
</div>

<ocw:setJs var="PhuchoiTemplate">
	<a href="" onclick = "Undelete(\#{idTopic}); return false" id="Undelete-\#{idTopic}"> Phục hồi</a>
</ocw:setJs>

<ocw:setJs var="topicTemplate">
	<div id="topic-\#{idTopic}">
		\#{nameTopic}
		<a href="" onclick = "Delete(\#{idTopic}); return false" id="Delete-\#{idTopic}"> Xóa</a>
	</div>
</ocw:setJs>

<script type="text/javascript">
	Phuchoi = new Template('${PhuchoiTemplate}');
	topicTempl = new Template('${topicTemplate}');
	
	function Add(){
		var idTopic = $('add-content-topic').value;
		var length_topic = test.topics.length;
		var data, topicElement;
		if (isExits(idTopic)){
			$('Message-topic').innerHTML = 'Chủ đề này đã tồn tại!';
			return;
		}
		new Ajax.Request(restPath + '/topics/' + idTopic,{
			method: 'get',
			requestHeaders : {
		       Accept : 'application/json'
	  		},
		    evalJSON : true,
		    onSuccess : function(transport) {
	  			topicElement = transport.responseJSON.result;
	  			var tpName = topicElement.name;
		       	data = {"idTopic": idTopic,"nameTopic":tpName};
		       	test.topics[length_topic] = {"id":topicElement.id};
		       	$('add-topic-content').insert({before: topicTempl.evaluate(data)});
		    },
		    onFailure: function(transport){ 
		    	DefaultTemplate.onFailure(transport); 
			}
		});
	}
	
	function Delete(id){
		var element = $('Delete-' + id);
		var data = {"idTopic":id};
		//var
		element.insert({after: Phuchoi.evaluate(data)});
		element.hide();
		checkTopic(id, true);
	}

	function Undelete(id){
		var element = $('Delete-' + id);
		var Undelete = $('Undelete-' + id);
		//var
		Undelete.remove();
		element.show();
		checkTopic(id, false);
	}

	function checkTopic(id, value){
		var length_topic = test.topics.length;
		var i;
		for (i = 0; i<length_topic; i++)
			if (test.topics[i].id == id){
				test.topics[i].deleted = value;
				break;
			}		
	}

	function isExits(id){
		var length_topic = test.topics.length;
		var i;
		for (i = 0; i<length_topic; i++)
			if (test.topics[i].id == id){
				return true;
			}
		return false;	
	}

	new Autocomplete('add-content-topic', {
        serviceUrl : apiPath + '/topic.search?format=qcount',
        minChars : 2,
        maxHeight : 400,
        width : 300,
        deferRequestBy : 100,
        // callback function:
        onSelect : function(value, data,id) {
			$('add-content-topic').value = data;
        }
    });
</script>