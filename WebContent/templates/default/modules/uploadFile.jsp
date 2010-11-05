<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<fieldset> 
    <legend>Upload Yout File</legend> 
    
    <div id="divFrame"> 
        <iframe id="ifrPhoto" scrolling="no" frameborder="0"   
				style="text-align:center;vertical-align:middle;border-style:none;margin:0px;width:100%;height:55px" >				
			<ocw:form action="uploadFile" id="uploadFile" enctype="multipart/form-data" method="post">
				<div><input id="file" type="file" name="file" size="10*1024*1024"></div>
				<div><input id="divUpload" type="submit" name="submit" value="Upload"></div>
				<ocw:error code="file"></ocw:error>
			</ocw:form>		
		</iframe> 		

    </div> 
    <div id="divUploadMessage" style="padding-top:4px;display:none"></div> 
    <div id="divUploadProgress" style="padding-top:4px;display:none"> 
        <span style="font-size:smaller">Uploading file...</span> 
        <div> 
        </div> 
    </div> 
</fieldset> 


<script type="text/javascript">
	
</script>
