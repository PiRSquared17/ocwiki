<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="fileuploader.css" rel="stylesheet" type="text/css">	
    <style>    	
		body {font-size:13px; font-family:arial, sans-serif; width:700px; margin:100px auto;}
    </style>	
</head>
<body>		
	
	<div id="file-uploader-demo1">		
		<noscript>			
			<p>Please enable JavaScript to use file uploader.</p>
			<!-- or put a simple form for upload here -->
		</noscript>         
	</div>
	
	<div id="file-uploader-demo1">
		<div class="qq-uploader">
			<div class="qq-upload-drop-area" style="display: none;">
				<span>Drop files here to upload</span>
			</div>
			<div class="qq-upload-button" style="position: relative; overflow: hidden; direction: ltr;">Upload a file
				<input type="file" multiple="multiple" name="file" style="position: absolute; right: 0pt; top: 0pt; font-family: Arial; font-size: 118px; margin: 0pt; padding: 0pt; cursor: pointer; opacity: 0;">
			</div>
			<ul class="qq-upload-list"></ul>
		</div>
	</div>
    
    <script src="fileuploader.js" type="text/javascript"></script>
    <script>        
        function createUploader(){            
            var uploader = new qq.FileUploader({
                element: document.getElementById('file-uploader-demo1'),
                action: 'do-nothing.htm'
            });           
        }
        
        // in your app create uploader as soon as the DOM is ready
        // don't wait for the window to load  
        window.onload = createUploader;     
    </script>    
</body>
</html>