<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="file" value="${empty article ? action.article : article}"></c:set>

<fieldset> 
<legend>File description</legend> 
<label>Nguồn gốc của File:</label>
<textarea id="fileSource" name="fileSource" cols="80" rows="1">${file.originalSource}</textarea> <br/>

<label>Tác giả           :</label>
<textarea id="author" name="author" cols="80" rows="1">${file.author}</textarea> <br/>

<label>Thông tin thêm    :</label>
<textarea id="additionalInfo" name="additionalInfo" cols="80" rows="1">${file.additionalInfo}</textarea> <br/>

<label>Bản quyền         </label>
<select name="license" id="license" value="${file.license}">
<option selected="selected" value="UNKNOWN">Không chọn</option> 
<option value="CC3">CC3</option>  
<option value="CC_SA3">CC_SA3</option> 
<option value="GFPL">GFPL</option> 
<option value="CC_SA3_GFDL">CC_SA3_GFDL</option> 
<option value="PUBLIC_DOMAIN">PUBLIC_DOMAIN</option> 
<option value="CREATIVE_COMMONS_ZERO_WAIVER">CREATIVE_COMMONS_ZERO_WAIVER</option> 
</select> 
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
						location.reload(true);
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