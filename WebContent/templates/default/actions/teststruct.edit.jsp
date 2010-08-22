<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
    var txtName = $('tname');
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

    <input type="hidden" name="action" value="teststruct.edit">
    <input type="hidden" name="tid" value="${testStruct.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">
    
    <p>
       <label>Tên: <input type="text" name="tname" 
            value="${(empty param.tname) ? testStruct.name : param.tname}"></label>
        <ocw:error code="name"></ocw:error>
    </p>
    
    <p>
       <label>Mô tả: <textarea name="tdescription">
           ${(empty param.tname) ? testStruct.description : param.tdescription}
       </textarea></label>
    </p>

    <p>
       <button type="submit" name="tsubmit" value="update">Lưu</button>
       <ocw:articleButton resource="${action.resource}">Thôi</ocw:articleButton>
    </p>
    
</form>
    