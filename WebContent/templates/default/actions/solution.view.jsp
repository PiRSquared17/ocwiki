<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="resource" value="${action.resource}"></c:set>
<c:set var="solution" value="${action.solution}"></c:set>
<c:set var="question" value="${solution.question.article}"></c:set>


<div>Câu hỏi:
	<button onclick="Show()" id="show_hide" value="show">Hiện/Ẩn</button>
	<div id="content_question">
		${question.content}
	</div>
	<h2>Bài giải:</h2>
	<div>
		${solution.content}
	</div>
</div>
<script type="text/javascript">
	function Show(){
		var show=$("show_hide");
		if(show.value=='show'){
			show.value='hide';
			$("content_question").hide();
		}
		else{
			show.value='show';
			$("content_question").show();
		}
	}
</script>