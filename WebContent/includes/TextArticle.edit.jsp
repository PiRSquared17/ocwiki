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
					<option value="0" ${textedit.namespace.id==0 ? 'selected="selected"':''} >Chính</option>
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
	
	WebService.get('/texts/'+ resourceId,
	{
	  onSuccess : function(transport) {
		  textarticle = transport.responseJSON.result;
	  },
	  onFailure: function(transport){
		  template.onFailure(transport); 
	  }
	});

	EditAction=Class.create();

	EditAction.preview=function(){
	}
	
	EditAction.save = function(successCallback, failureCallback){
		textarticle.content={text : tinymce.get('edit_context').getContent()};
		textarticle.namespace={id : $F('namespaedit')};
		textarticle.name = $('edit_name').value;
		WebService.post('/texts/'+ resourceId,
			{
				postBody: Object.toJSON({
			          article: textarticle,
			          summary: $F('articleEdit-summary'),
			          checked: $F('articleEdit-minor')
			     }),
			     onSuccess: successCallback,
			     onFailure: function(transport){
			    	  template.onFailure(transport); 
			    	  failureCallback();
			     }
			});
	};
//-->
</script>