<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="file" value="${empty article ? action.article : article}"></c:set>

<fieldset> 
<legend><b>Tải tệp tin</b></legend>

<table class="" id="uploadFile">
	<tbody>
	<tr>
	<td><label>Tệp tin trên máy :</label></td>
	<td><input id="uploadFile" name="uploadFile" size="60" value="" type="file" />
	</td></tr>
	<tr>
	<td colspan="2" class="htmlform-tip">Kích thước tối đa: 10 MB</td>
	</tr>
	<tr>
	<td><label>Các định dạng cho phép:</label></td>
	<td><div id="uploadPermitted"><p>png, gif, jpg, jpeg, svg.</p></div></td>
	</tr>
	</tbody>
	<tr>
	<td><input type="submit" name="submit" value="Tải tệp tin lên"></td>
	<td><ocw:error code="file"></ocw:error></td>
	</tr>
</table>
</fieldset>

<fieldset> 
<legend><b>File description</b></legend> 
<table>
	<tbody>
		<tr>
		<td><label>Nguồn gốc tệp tin:</label></td>
		<td><textarea id="fileSource" name="fileSource" cols="60" rows="1">${file.originalSource}</textarea></td>
		</tr>
		
		<tr>
		<td><label>Tác giả:</label></td>
		<td><textarea id="author" name="author" cols="60" rows="1">${file.author}</textarea></td>
		</tr>
		
		<tr>
		<td><label>Thông tin thêm:</label></td>
		<td><textarea id="additionalInfo" name="additionalInfo" cols="60" rows="1">${file.additionalInfo}</textarea></td>
		</tr>
		
		<tr>
		<td><label>Bản quyền:</label></td>
		<td>
			<select name="license" id="license">
			<option selected="selected" value="UNKNOWN">Không chọn</option> 
			<option value="CC3">CC3</option>  
			<option value="CC_SA3">CC_SA3</option> 
			<option value="GFPL">GFPL</option> 
			<option value="CC_SA3_GFDL">CC_SA3_GFDL</option> 
			<option value="PUBLIC_DOMAIN">PUBLIC_DOMAIN</option> 
			<option value="CREATIVE_COMMONS_ZERO_WAIVER">CREATIVE_COMMONS_ZERO_WAIVER</option> 
			</select>
		</td>
		</tr>
	</tbody>
</table> 
</fieldset>

<script type="text/javascript">
<!--
	var file;
	Event.observe(window, 'load', function() {
		new Ajax.Request(restPath + '/files/' + resourceId,{
			method: 'get',
			requestHeaders : {
		       Accept : 'application/json'
				},
		    evalJSON : true,
		    onSuccess : function(transport) {
				file = transport.responseJSON.result;
		    },
		    onFailure: function(transport){ 
		    	DefaultTemplate.onFailure(transport); 
			}
		})
	});	
	EditAction=Class.create();

	EditAction.preview=function(){
	}
	
	EditAction.save = function(){
		// gửi dữ liệu lên server
		file.originalSource = tinymce.get('fileSource').getContent();
		file.author = tinymce.get('author').getContent();
		file.additionalInfo = tinymce.get('additionalInfo').getContent();
		file.license = $F('license');
		file.dateOfWork = new Date();

		new Ajax.Request(restPath + '/files/' + resourceId,
				  {
				    method:'post',
				    contentType: 'application/json',
				    postBody: Object.toJSON({
				          article: file,
				          summary: $F('articleEdit-summary'),
				          minor: $('articleEdit-minor').checked
				      }),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) 
					{
						file = transport.responseJSON.result;
						location.href = articlePath + '/' + resourceId;
					},
				    onFailure: function(transport)
	                {
	                    var code = transport.responseJSON.code;
	                    if (code == 'old version') {
	                          openInfoDialog("Có người đã sửa file này trước bạn, hãy tải lại trang!");
	                    } else {
	                        DefaultTemplate.onFailure(transport); 
	                    }
					}
				   });
		   return;
	};
//-->
</script>