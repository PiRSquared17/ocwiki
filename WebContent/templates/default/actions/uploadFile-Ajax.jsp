<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<div id="file-uploader-demo1">		
	<noscript>			
		<p>Please enable JavaScript to use file uploader.</p>
		<!-- or put a simple form for upload here -->
	</noscript>         
</div>
<span id="progress"></span>

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
