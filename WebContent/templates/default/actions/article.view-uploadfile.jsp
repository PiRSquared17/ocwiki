<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<fieldset>
    <legend>File Upload</legend>
    <div id="divChooseFile" style="text-align:center;vertical-align:middle;borderstyle:none;margin:0px;width:100%;height:55px" >
			<form id="fileUpload" enctype="multipart/form-data" >
    			<div>
        			<input id="file" type="file"/>
    			</div>
    			<div id="divUpload" style="padding-top:4px">
        			<input id="btnUpload" type="button" value="Upload File" />
    			</div>
			</form>			
    </div>
    <div id="divUploadMessage" style="padding-top:4px;display:none"></div>
    <div id="divUploadProgress" style="padding-top:4px;display:none">
        <span style="font-size:smaller">Uploading photo...</span>
        <div>
            <table border="0" cellpadding="0" cellspacing="2" style="width:100%">
                <tbody>
                    <tr>
                        <td id="tdProgress1">&nbsp; &nbsp;</td>
                        <td id="tdProgress2">&nbsp; &nbsp;</td>
                        <td id="tdProgress3">&nbsp; &nbsp;</td>
                        <td id="tdProgress4">&nbsp; &nbsp;</td>
                        <td id="tdProgress5">&nbsp; &nbsp;</td>
                        <td id="tdProgress6">&nbsp; &nbsp;</td>
                        <td id="tdProgress7">&nbsp; &nbsp;</td>
                        <td id="tdProgress8">&nbsp; &nbsp;</td>
                        <td id="tdProgress9">&nbsp; &nbsp;</td>
                        <td id="tdProgress10">&nbsp; &nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</fieldset>

<script language="javascript">
	var divChooseFile = $('divChooseFile').innerHTML;
	var divUploadMessage = $('divUploadMessage').innerHTML;
	var divUploadProgress = $('divUploadProgress').innerHTML;
</script>
