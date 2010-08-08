<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
    var txtName = $('tse_name');
    var msgName = $('msgName');

    if (txtName.value == '') {
        msgName.innerText = 'Bạn cần nhập tên.';
        msgName.style.display = 'block';
        return false;
    }
    
    msgName.style.display = 'none';
    return true;
}

</script>

<form action="${scriptPath}" method="get">

    <input type="hidden" name="action" value="teststruct.edit"></input>
    <input type="hidden" name="tse_id" value="${testStruct.id}"></input>
    
    <p>
       <label>Tên: <input type="text" name="tse_name" 
            value="${(empty param.tse_name) ? testStruct.name : param.tse_name}"></input></label>
        <span id="msgName" style='${(empty nameErr) ? "none" : "block"}'>${nameErr}</span>
    </p>
    
    <p>
       <label>Mô tả: <textarea name="tse_description">
           ${(empty param.tse_name) ? testStruct.description : param.tse_description}
       </textarea></label>
    </p>

    <p>
       <button type="submit" name="tse_submit" value="update">Lưu</button>
       <button type="button" onclick="location.href='${scriptPath}?action=teststruct.view&tsv_id=${testStruct.id}'">Thôi</button>
    </p>
    
</form>
    