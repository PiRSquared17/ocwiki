<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
<!--

function clearParent() {
    $('txtParentName').value = '<không chọn>';
    $('txtParentId').value = 0;
}

function validate() {
	
}

//-->
</script>

<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

<ocw:form action="topic.create"> 
	<p>
		<label>Tên: <input type="text" id="txtName"
		      name="cc_name" value="${topic.name}"></label>
		<ocw:error code="name"></ocw:error>
	</p>
    <p>
        <label>Nội dung:
        <textarea name="content" rows="20" cols="80">${param.content}</textarea></label>
    </p>
	<p><label>Chủ đề cha:
        <input type="text" id="txtParentName" name="cc_parentname" 
                value="${param.cc_parentname}">
        </label>
        <input type="hidden" id="txtParentId" name="cc_parent" 
                 value="${param.cc_parent}">
        <a href="javascript:clearParent();"><img src="${templatePath}/images/wrong.png" alt="remove" title="remove" width="12" height="12" /></a>
        <ocw:error code="parent"></ocw:error>
	</p>
	<button type="submit" name="cc_submit" value="create">Tạo</button>
	<button type="button" onclick="location.href='${scriptPath}?action=topic.list'">Quay về danh sách</button>
</ocw:form>

<script>
    $('txtName').focus();

    new Autocomplete('txtParentName', {
        serviceUrl : '${scriptPath}?action=topic.ajaxsearch',
        minChars : 2,
        maxHeight : 400,
        width : 300,
        deferRequestBy : 100,
        // callback function:
        onSelect : function(value, data) {
            $('txtParentId').value = data;
        }
    });
</script>