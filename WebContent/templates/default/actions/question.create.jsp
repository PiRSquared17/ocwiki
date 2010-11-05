<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
<!--
function validate() {
	
}

//-->
</script>
<ocw:form action="question.create" id="form_edit">
	<p><label>Câu hỏi: 
		<textarea name="qc_content">${param.qc_content}</textarea> 
		</label>
	</p>
	<br /><br />
	<p><label>Độ khó: 
		<select name="qc_level">
            <option value="1" ${param.qc_level==1 ? 'selected' : ''}>${u:levelName(1)}</option>
            <option value="2" ${param.qc_level==2 ? 'selected' : ''}>${u:levelName(2)}</option>
            <option value="3" ${(empty param.qc_level) || param.qc_level==3 ? 'selected' : ''}>${u:levelName(3)}</option>
            <option value="4" ${param.qc_level==4 ? 'selected' : ''}>${u:levelName(4)}</option>
            <option value="5" ${param.qc_level==5 ? 'selected' : ''}>${u:levelName(5)}</option>
		</select>
		</label>
		<ocw:error code="level"></ocw:error>
	</p>
	<br />
	<p>
		<button type="submit" name="qc_submit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scripPath}?action=question.list'">Quay về danh sách</button>
	</p>
</ocw:form>
