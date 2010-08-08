<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
<!--

function clearParent() {
	$('txtParentName').value = '<không chọn>';
	$('txtParentId').value = 0;
}

//-->
</script>

<form action="${scriptPath}" method="GET">
	<input type="hidden" name="action" value="topic.edit"></input> 
	<input type="hidden" name="ce_id" value="${topic.id}"></input>
	<p>Id: ${topic.id}</p>
	<p>
		<label>Tên: <input type="text" id="txtName" 
		      name="ce_name" value="${topic.name}"></input></label>
		${nameErr}
	</p>
	<p><label>Chủ đề cha:
        <input type="text" id="txtParentName" name="ce_parentname" 
                value="${u:defaultIfNull(param.ce_parentname, topic.parent.name)}"></input>
        </label>
        <input type="hidden" id="txtParentId" name="ce_parent" 
                 value="${u:defaultIfNull(param.ce_parent, topic.parent.id)}"></input>
        <a href="javascript:clearParent();"><img src="${templatePath}/images/wrong.png" alt="remove" title="remove" width="12" height="12" /></a>
        <span style="color:red">${parentErr}</span>
	</p>
	<button type="submit" name="ce_submit" value="update">Cập nhật</button>
	<button type="button" onclick="location.href='${scriptPath}?action=topic.list'">Quay về danh sách</button>
</form>

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