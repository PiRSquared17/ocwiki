<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="textedit" value="${empty article ? action.article : article}"></c:set>

<ocw:form action="textarticle.edit">
	<table>
		<tr>
			<td>Không gian tên:</td>
			<td>	
				<select id="namespace_edit">
					<option value="0" ${textedit.namespace.id==0 ? 'selected="selected"':''} >Chinh</option>
					<option value="1" ${textedit.namespace.id==1 ? 'selected="selected"':''} >OCWIKI</option>
					<option value="2" ${textedit.namespace.id==2 ? 'selected="selected"':''} >Chủ đề</option>
					<option value="3" ${textedit.namespace.id==3 ? 'selected="selected"':''} >Câu hỏi</option>
					<option value="4" ${textedit.namespace.id==4 ? 'selected="selected"':''} >Đề thi</option>
					<option value="5" ${textedit.namespace.id==5 ? 'selected="selected"':''} >Cấu trúc đề</option>
					<option value="6" ${textedit.namespace.id==6 ? 'selected="selected"':''} >Tập tin</option>
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
	<div>
		<input type="button" onclick="saveEdit()" name="Save" value="Save">
	</div>
</ocw:form>
<script type="text/javascript">
<!--
	var textarticle=null;
	
	new Ajax.Request(restPath + '/TextArticle/'+ resourceId,
	{
	  method:'get',
	  requestHeaders : {
	      Accept : 'application/json'
	  },
	  evalJSON : true,
	  onSuccess : function(transport) {
		  textarticle = transport.responseJSON.result;
	  },
	  onFailure: function(){
		  alert('Fail'); }
	});

	function saveEdit(){
		textarticle.content={text : tinymce.get('edit_context').getContent()};
		textarticle.namespace={id : $F('namespace_edit')};
		new Ajax.Request(restPath + '/TextArticle/'+ resourceId,
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
			     onSuccess: function(transport) {
			      },
			      onFailure: function(error){
				      alert('Loi:'+error.status); }
			});
	};
//-->
</script>