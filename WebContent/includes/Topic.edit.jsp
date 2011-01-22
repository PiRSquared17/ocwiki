<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="topic" value="${empty article ? action.article : article}"></c:set>

<form>
	<div id = "Parent_topic">
			Chọn chủ đề cha:
			<input id = "Parent-topic-edit" value = "${topic.parent.name}">
	</div>
	<div id = "Topic-content">
		Nội dung:
		<textarea rows="" cols="80" id = "Topic-content-edit">${topic.content.text}</textarea>
	</div>
</form>
<script type="text/javascript">
	var topic = null;

	
	$('Parent-topic-edit').select();
	new Autocomplete('Parent-topic-edit', {
	    serviceUrl : apiPath + '/topic.search',
	    minChars : 2,
	    maxHeight : 400,
	    width : 300,
	    deferRequestBy : 100,
	    // callback function:
	    onSelect : function(value, data) {
	        $('Parent-topic-edit').value = data;
	    }
	});

	// Lay doi tuong topic ve
	WebService.get('/topics/' + resourceId,{
	    onSuccess : function(transport) {
	       topic = transport.responseJSON.result;
	    },
	    onFailure: function(transport){ 
	    	template.onFailure(transport); 
		}
	});
	
	EditAction = Class.create();
	EditAction.preview = function(){
	}

	EditAction.save = function(successCallback, failureCallback){
		var content = tinymce.get('Topic-content-edit').getContent();
		var parentId = $('Parent-topic-edit').value;
		topic.content.text = content;
		topic.parent = {"id": parentId};
		WebService.post('/topics/' + resourceId,{
		    postBody: Object.toJSON({
		          article: topic,
		          summary: $F('articleEdit-summary'),
		          minor: $('articleEdit-minor').checked
		    }),
		    onSuccess : successCallback,
		    onFailure: function(transport){ 
		    	template.onFailure(transport); 
		    	failureCallback();
			}
		});
	}
</script>