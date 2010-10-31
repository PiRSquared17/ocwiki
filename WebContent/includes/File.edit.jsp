<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="file" value="${empty article ? action.article : article}"></c:set>

<fieldset> 
<legend><b>Tải tệp tin</b></legend>
<table class="" id="uploadFile">
	<tbody>
	<tr>
	<td>
		<div id="file-uploader-demo1">		
		<noscript>			
			<p>Please enable JavaScript to use file uploader.</p>
			<!-- or put a simple form for upload here -->
		</noscript>         
		</div>
	</td>
	<td><span id="progress"></span></td>
	</tr>
	<tr>
	<td>Kích thước tối đa: <b>10 MB</b></td>
	</tr>
	<tr>
	<td>Các định dạng cho phép: <b>png, gif, jpg, jpeg, svg.</b></td>
	</tr>
	</tbody>
	<tr>
	<td><ocw:error code="file"></ocw:error></td>
	</tr>
</table>
</fieldset>


<script src="${templatePath}/js/fileuploader.js" type="text/javascript"></script>
<script>        
    function createUploader(){            
        var uploader = new qq.FileUploader({
            element: document.getElementById('file-uploader-demo1'),
            action: '${ocw:restUrl("upload/123")}',
            onProgress: function(id, fileName, loaded, total){$('progress').innerHTML = loaded;},
            onComplete: function(id, fileName, responseJSON){},
            onCancel: function(id, fileName){},
            showMessage: function(message) {}
        });           
    }
    
    // in your app create uploader as soon as the DOM is ready
    // don't wait for the window to load  
    window.onload = createUploader;     
</script>    

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