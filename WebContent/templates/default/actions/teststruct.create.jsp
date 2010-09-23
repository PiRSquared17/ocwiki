<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="teststruct.create">
	<p>Tên: 
		<input type="text" name="tname" value="${param.tname}"> 
		<ocw:error code="name"></ocw:error>
	</p>
	<br></br>
	
	<p>Mô tả: <textarea style="width: 100%" name="tdescription">${param.tdescription}</textarea>
		<ocw:error code="description"></ocw:error>
	</p>
    <br></br>

    <p><label>Chủ đề:
	       <input type="text" id="txtTopicName" name="ttopicname" 
	               value="${param.ttopicname}">
       </label>
       <input type="hidden" id="txtTopicId" name="ttopic" 
                value="${param.ttopicname}">
       <ocw:error code="topic"></ocw:error>
    </p>
    <br></br>
    
	<p>
		<button type="submit" name="tsubmit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scriptPath}?action=teststruct.list'">Quay về danh sách</button>
	</p>
</ocw:form>

<script type="text/javascript">
<!--
new Autocomplete('txtTopicName', {
    serviceUrl : apiPath + '/topic.search',
    minChars : 2,
    maxHeight : 400,
    width : 300,
    deferRequestBy : 100,
    // callback function:
    onSelect : function(value, data) {
        $('txtTopicId').value = data;
    }
});
//-->
</script>