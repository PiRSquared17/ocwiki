<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp"%>
<fieldset> 
<legend>Upload Your File</legend> 
<label>Choose File:</label>
<input id="UploadFile" name="UploadFile" value="" type="file" /><br/>
<label>Kích thước file tối đa: 10 MB</label><br/> 
<label>Các dạng file cho phép: png, gif, jpg, jpeg, pdf, svg.</label> 
</fieldset> 

<fieldset> 
<legend>File description</legend> 
<label>Nguồn gốc của File:</label>
<textarea id="fileSource" name="fileSource" cols="80" rows="1"></textarea> <br/>

<label>Tác giả           :</label>
<textarea id="author" name="author" cols="80" rows="1"></textarea> <br/>

<label>Thông tin thêm    :</label>
<textarea id="additionalInfo" name="additionalInfo" cols="80" rows="1"></textarea> <br/>

<label>Bản quyền         </label>
<select name="license" id="license">
<option selected="selected" value="">Không chọn</option> 
<option value="CC3">CC3</option>  
<option value="CC_SA3">CC_SA3</option> 
<option value="GFPL">GFPL</option> 
<option value="CC_SA3_GFDL">CC_SA3_GFDL</option> 
<option value="PUBLIC_DOMAIN">PUBLIC_DOMAIN</option> 
<option value="CREATIVE_COMMONS_ZERO_WAIVER">CREATIVE_COMMONS_ZERO_WAIVER</option> 
</select> 
</fieldset>

<input type="submit" name="submit" value="Upload">
<ocw:error code="file"></ocw:error>