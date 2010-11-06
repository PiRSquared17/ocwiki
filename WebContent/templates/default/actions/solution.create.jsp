<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="resource" value="${action.resource}"></c:set>
<c:set var="question" value="${resource.article}"></c:set>

<div>Câu hỏi:
		<button onclick="show_hide()" value="Show" id="show_hide" name="Hide">Hiện/Ẩn</button>
			<div id="content_question">
					${question.content}
		</div>
		<ocw:form action="solution.create">
			<input type="hidden" value="${resource.id}" name="id">
			<table>
				<tr>
					<td>Tên:</td>
					<td>
						<input type="text" name="name">
					</td>
				</tr>
				<tr>
					<td>Bài giải:</td>
					<td>
						<textarea rows="" cols="" id="bai_giai" name="bai_giai">
						</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" name="submit" value="create">Lưu</button>
					</td>
				</tr>
			</table>
		</ocw:form>	
</div>
<script type="text/javascript">
	
	function show_hide(){
		var value=$('show_hide').value;
		if(value=='Show'){
			$('content_question').hide();
			$('show_hide').value='Hide';
		}
		else{
			$('content_question').show();
			$('show_hide').value='Show';
		}
	}
	
</script>