<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="textedit" value="${empty article ? action.article : article}"></c:set>

<ocw:form action="textarticle.edit">
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
				<input id="edit_name" type="text" value="${textedit.name}">
			</td>
		</tr>
		<tr>
			<td>Nội dung:</td>
			<td >
			<font size="2">
				<textarea rows="" cols="" id="edit_context">
					${textedit.content.text}
				</textarea>
			</font>
			</td>
		</tr>
	</table>
	<br></br>
</ocw:form>
<script type="text/javascript">
<!--
	var textarticle=null;
	
	new Ajax.Request(restPath + '/texts/'+ resourceId,
	{
	  method:'get',
	  requestHeaders : {
	      Accept : 'application/json'
	  },
	  evalJSON : true,
	  onSuccess : function(transport) {
		  textarticle = transport.responseJSON.result;
	  },
	  onFailure: function(transport){
		  DefaultTemplate.onFailure(transport); 
	  }
	});

	EditAction=Class.create();

	EditAction.preview=function(){
	}
	
	EditAction.save = function(successCallback, failureCallback){
		textarticle.content={text : tinymce.get('edit_context').getContent()};
		textarticle.namespace={id : $F('namespaedit')};
		textarticle.name = $('edit_name').value;
		new Ajax.Request(restPath + '/texts/'+ resourceId,
			{
				method: 'post',
				requestHeaders:{
					Accept:'application/json'
				},
				contentType: 'application/json',
				postBody: Object.toJSON({
			          article: textarticle,
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
	};
//-->
</script>