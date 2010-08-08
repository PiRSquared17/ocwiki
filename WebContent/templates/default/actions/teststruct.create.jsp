<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<form id="form_edit">
	<input type="hidden" name="action" value="teststruct.create" />
	<p>Tên: 
		<input type="text" name="tsc_name" value="${param.tsc_name}"></input> 
		<c:if test="${!(empty nameErr)}">
			<span style="">${nameErr}</span>
		</c:if>
	</p>
	<br></br>
	
	<p>Mô tả: <textarea style="width: 100%" name="tsc_description">${param.tsc_description}</textarea>
		<c:if test="${!(empty descriptionErr)}">
			<span style="">${descriptionErr}</span>
		</c:if>
	</p>
    <br></br>

    <p><label>Chủ đề:
	       <input type="text" id="txtTopicName" name="tsc_topicname" 
	               value="${param.tsc_topicname}"></input>
       </label>
       <input type="hidden" id="txtTopicId" name="tsc_topic" 
                value="${param.tsc_topicname}"></input>
       <span style="color:red">${topicErr}</span>
    </p>
    <br></br>
    
	<p>
		<button type="submit" name="tsc_submit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scriptPath}?action=teststruct.list'">Quay về danh sách</button>
	</p>
</form>

<script type="text/javascript">
<!--
new Autocomplete('txtTopicName', {
    serviceUrl : '${scriptPath}?action=testtopic.ajaxsearch',
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