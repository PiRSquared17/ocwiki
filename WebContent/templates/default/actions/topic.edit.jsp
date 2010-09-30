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

<ocw:form action="topic.edit" method="post"> 
	<input type="hidden" name="id" value="${action.resource.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">

    <table>
	  <tr>
	    <td class="ocw-label"><label>Tên:</label></td>
	    <td class="ocw-input"><input type="text" id="txtName" name="name" value="${action.topic.name}">
	       <ocw:error code="name"></ocw:error>
	    </td>
	  </tr>
	  <tr>
	    <td class="ocw-label"><label>Tên chủ đề cha:</label></td>
	    <td class="ocw-input">
	       <input type="text" id="txtParentName" name="parentname" value="${action.topic.parent.name}">
	       <ocw:error code="parent-name"></ocw:error>
        </td>
	  </tr>
	  <tr>
	    <td class="ocw-label">hoặc ID:</td>
	    <td class="ocw-input">
	       <input type="text" id="txtParentId" name="parent" value="${action.topic.parent.id}">
	       <ocw:error code="parent-id"></ocw:error>
        </td>
	  </tr>
	  <tr>
	    <td>Nội dung:</td>
	    <td>
	       <textarea name="content" rows="20" cols="80">${action.topic.content.text}</textarea>
	       <ocw:error code="content"></ocw:error>
	    </td>
	  </tr>
	</table>
        
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