<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}" scope="request"></c:set>
<c:set var="solution" value="${empty article ? action.article : article}" scope="request"></c:set>
<c:set var="question" value="${solution.question.article}"></c:set>

<ocw:form action="textarticle.edit">
	<div id="content_question">
		Câu hỏi:
		${question.content}
	</div>
	<table>
		<tr>
			<td>Không gian tên:</td>
			<td>	
				<select id="namespaedit">
					<option value="0" ${textedit.namespace.id==0 ? 'selected="selected"':''} >Chinh</option>
					<option value="1" ${textedit.namespace.id==1 ? 'selected="selected"':''} >OCWIKI</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Tên:</td>
			<td>
				<input id="edit_name" type="text" value="${solution.name}">
			</td>
		</tr>
		<tr>
			<td>Bài giải:</td>
			<td >
			<font size="2">
				<textarea rows="" cols="60" id="edit_context">
					${solution.content}
				</textarea>
			</font>
			</td>
		</tr>
	</table>
	<br></br>
</ocw:form>
<script type="text/javascript">
	var solution = null;
	new Ajax.Request(restPath + '/solutions/' + resourceId,{
		method: 'get',
		requestHeaders : {
	       Accept : 'application/json'
  		},
	    evalJSON : true,
	    onSuccess : function(transport) {
	       solution = transport.responseJSON.result;
	       template();
	    },
	    onFailure: function(transport){ 
	    	DefaultTemplate.onFailure(transport); 
		}
	});
	
	EditAction = Class.create();

	EditAction.preview = function(){
	}
	
	EditAction.save = function(successCallback, failureCallback){
		var content = tinymce.get('edit_context').getContent();	
		var name = $('edit_name').value;
		solution.namespace = {id: $F('namespaedit')};
		solution.name = name;
		solution.content={text : content};
		new Ajax.Request(restPath + '/solutions/'+ resourceId,
				{
					method: 'post',
					requestHeaders:{
						Accept:'application/json'
					},
					contentType: 'application/json',
					postBody: Object.toJSON({
				          article: solution,
				          summary: $F('articleEdit-summary'),
				          checked: $F('articleEdit-minor')
				     }),
				     evalJSON: true,
				     onSuccess: successCallback,
				     onFailure: function(transport){
				    	  DefaultTemplate.onFailure(transport); 
				    	  failureCallback();
				 	 }
				});
	}
</script>