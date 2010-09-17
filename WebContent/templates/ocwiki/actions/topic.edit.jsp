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

<ocw:form action="topic.edit"> 
	<input type="hidden" name="id" value="${topic.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">

	<p>Id: ${topic.id}</p>
	<p>
		<label>Tên: <input type="text" id="txtName" 
		      name="name" value="${topic.name}"></label>
		<ocw:error code="name"></ocw:error>
	</p>
	<p><label>Chủ đề cha:
        <input type="text" id="txtParentName" name="parentname" 
                value="${u:defaultIfNull(param.parentname, topic.parent.name)}">
        </label>
        <input type="hidden" id="txtParentId" name="parent" 
                 value="${u:defaultIfNull(param.parent, topic.parent.id)}">
        <a href="javascript:clearParent();"><img src="${templatePath}/images/wrong.png" alt="remove" title="remove" width="12" height="12" /></a>
        <ocw:error code="parent"></ocw:error>
	</p>
	<button type="submit" name="submit" value="update">Cập nhật</button>
	<button type="button" onclick="location.href='${scriptPath}?action=topic.list'">Quay về danh sách</button>
</ocw:form>

<script>
    $('txtName').focus();

    new Autocomplete('txtParentName', {
        serviceUrl : apiPath + '/topic.search',
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