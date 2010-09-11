<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script>

function validateName() {
	var txtName = $('te_name');
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

<ocw:form action="test.edit">
	<input type="hidden" name="te_id" value="${test.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">
	
	<p>
	   <label>Tên: <input type="text" name="te_name" 
			value="${(empty param.te_name) ? test.name : param.te_name}"></label>
		<ocw:error code="name"></ocw:error>
	</p>
	<br></br>
	
	<p>
	   <label>Mô tả: <textarea name="te_description">
	       ${(empty param.te_name) ? test.content : param.te_description}
	   </textarea></label>
	</p>
    <br></br>

    <p><label>Chủ đề:
            <input type="text" id="txtTopicName" name="te_topicname" 
                    value="${empty param.te_topicname ? test.topic.name : param.te_topicname}">
            </label>
            <input type="hidden" id="txtTopicId" name="te_topic" 
                     value="${empty param.te_topic ? test.topic.id : param.te_topic}">
            <ocw:error code="topic"></ocw:error>
    </p>
    <br></br>

    <p>Kiểu:
        <p><label>
            <input type="radio" name="te_type" value="radio"
                    ${(empty param.te_type && test.type=='radio') || param.te_type=='radio' ? "checked":""}>
            Một câu đúng 
        </label>
        </p>
        <p><label>
            <input type="radio" name="te_type" value="check"
                    ${((empty param.te_type && test.type=='check') || param.te_type=='check') ? "checked":""}>
            Nhiều câu đúng 
        </label>
        </p>
        <ocw:error code="type"></ocw:error>
    </p>
    <br></br>
    
    <p><label>Thời gian
        <input type="text" id="txtTime" name="te_time" 
                value="${(empty param.te_time)? test.time : param.te_time}"
                maxlength="3">
        </label>
        <ocw:error code="time"></ocw:error>
    </p>
    <br></br>
	
	<p>
	   <button type="submit" name="te_submit" value="update">Lưu</button>
	   <ocw:articleButton resource="${action.resource}">Thôi</ocw:articleButton>
	</p>
</ocw:form>

<script type="text/javascript">
<!--
filterNumericKey('txtTime');

new Autocomplete('txtTopicName', {
    serviceUrl : '${scriptPath}?action=testtopic.ajaxsearch',
    minChars : 2,
    maxHeight : 400,
    width : 300,
    deferRequestBy : 100,
    autoSelect: true,
    // callback function:
    onSelect : function(value, data) {
        $('txtTopicId').value = data;
    }
});
//-->
</script>