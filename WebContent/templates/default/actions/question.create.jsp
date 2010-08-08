<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
<!--
function validate() {
	
}

//-->
</script>
<form id="form_edit">
	<input type="hidden" name="action" value="question.create" />
	
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
        <div class="error-validating">${action.levelError}</div>
	</p>
	<br />
    <!--<p>Điểm: 
        <input type="text" id="txtMark" name="qc_mark" 
                value="${empty param.qc_mark ? 1 : param.qc_mark}"
                maxlength="2" onblur="" style="width:300px;"></input>
        <div class="error-validating">${action.markError}</div>
    </p>
	<br />
	--><!-- 
	<label>
	   <input type="checkbox" name="more" value="true" 
	           ${param.more ? 'checked' : ''} style="float: none"></input>
	   Thêm nữa
	</label>
	 -->
	<p>
		<button type="submit" name="qc_submit" value="create">Tạo</button>
		<button type="button" onclick="location.href='${scripPath}?action=question.list'">Quay về danh sách</button>
	</p>
</form>
