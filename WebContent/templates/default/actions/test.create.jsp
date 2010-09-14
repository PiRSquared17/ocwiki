<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div id="form_edit">
<ocw:form action="test.create">
    <p><label>Tên: 
        <input type="text" name="name" value="${param.name}"> 
        <ocw:error code="name"></ocw:error></label>
    </p>
    <br />
    <p>Nội dung: <textarea name="description">${param.content}</textarea>
        <ocw:error code="content"></ocw:error>
    </p>
    <br />
    <p>Kiểu:
       <select name="type">
           <option value="radio">Một câu đúng</option>
           <option value="check">Nhiều câu đúng</option>
       </select>
        <ocw:error code="type"></ocw:error>
    </p>
    <br />
    <p><label>Thời gian
        <input type="text" id="txtTime" name="time" 
                value='${(empty param.time)?"90":param.time}'
                maxlength="3"> phút
        </label>
        <ocw:error code="time"></ocw:error>
    </p>
    <br />
    
    <fieldset>
       <legend>
           <label><input type="checkbox" name="usestruct" value="true" style="float: none"
                    ${param.usestruct ? 'checked' : ''}>
           Sinh tự động</label>
       </legend>

        <p>
            <label>
                Chọn cấu trúc đề:
                <input type="text" id="txtTestStructName" 
                        name="structname" value="${param.structname}">
                <input type="hidden" id="txtTestStructId" 
                        name="struct" value="${param.struct}">
            </label>
            <ocw:error code="struct"></ocw:error>
        </p>
    </fieldset>
    
    <br />
    <p>
        <button type="submit" name="submit" value="create">Tạo</button>
        <ocw:actionButton name="test.list">Quay về danh sách đề</ocw:actionButton>
    </p>
</ocw:form>
</div>
<script type="text/javascript">
<!--
filterNumericKey('txtTime');

new Autocomplete('txtTestStructName', {
    serviceUrl : apiPath + '/teststruct.search',
    minChars : 2,
    maxHeight : 400,
    width : 300,
    deferRequestBy : 100,
    autoSelect: true,
    // callback function:
    onSelect : function(value, data) {
        $('txtTestStructId').value = data;
    }
});
//-->
</script>