<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="question" value="${action.question}"></c:set>

<script language="javascript">

var correct = { 
<c:forEach items="${question.answers}" var="answer">
	a${answer.id} : ${answer.correct},
</c:forEach>};

function checkanswer() {
	answer = document.getElementsByName('${question.id}-answers');
	
	for (i=0;i<4;i++) {
		rightanswer = document.getElementById('a' + answer[i].value + '-rightanswer');
		wronganswer = document.getElementById('a' + answer[i].value + '-wronganswer');
		rightanswer.style.display = 'none';
		wronganswer.style.display = 'none';
		if(answer[i].checked == eval('correct.a' + answer[i].value))
			{
				if (answer[i].checked == true )
					rightanswer.style.display='inline';
			} else if (answer[i].checked == true )
				wronganswer.style.display='inline';
			
	}
}
</script>

<c:if test="${sessionScope.user.group=='teacher'}">
    <div>
        <button onclick="location.href='${scriptPath}?action=question.edit&qe_id=${question.id}'">Sửa</button>
        <button onclick="location.href='${scriptPath}?action=answer.create&question=${question.id}'">Thêm phương án trả lời</button>
        <button onclick="location.href='${scriptPath}?action=question.create'">Thêm câu hỏi</button>
        <button onclick="location.href='${scriptPath}?action=question.list'">Quay về danh sách</button>
    </div>
</c:if>

<p>Tác giả: <b><ocw:userLink user="${action.resource.author}">${action.resource.author.fullname}</ocw:userLink></b></p>
<p>Thời điểm tạo: <b>${u:formatDateTime(action.resource.createDate)}</b></p>

<div>
	${question.content}
	<div class="answer-list-wrapper">
	<c:forEach items="${question.answers}" var="answer">
		<div class="answer-wrapper mouse-out"
	            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
	            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
		    <div class="check-wrapper">
				<input type="radio" name="${question.id}-answers" value="${answer.id}" id="answer-${answer.id}">
			</div>
			<div class="marker-wrapper">
				<span id="a${answer.id}-rightanswer" style="display:none;"><img src="${templatePath}/images/right.png" alt="right answer" width="16px" height="16px" /></span>
				<span id="a${answer.id}-wronganswer" style="display:none;"><img src="${templatePath}/images/wrong.png" alt="wrong answer" width="16px" height="16px" /></span>
		    </div>
            <c:if test="${sessionScope.user.group=='teacher'}">
	            <div class="buttons">
	                <a href="${scriptPath}?action=answer.edit&question=${question.id}&answer=${answer.id}"><img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="16px" height="16px" /></a>
	                <a href="${scriptPath}?action=answer.delete&question=${question.id}&answer=${answer.id}"
	                       onclick="return confirm('Bạn có chắc muốn xoá phương án trả lời này?');"
                            ><img src="${templatePath}/images/wrong.png" alt="edit" title="remove" width="16px" height="16px" /></a>
	            </div>
            </c:if>

            <div style="margin-right: 60px">
			     <label for="answer-${answer.id}">${answer.content}</label>
			</div>
		</div>
	</c:forEach>
	</div>
</div>

<ocw:topics article="${question}"/>

<p>
	<button onclick="checkanswer()">Trả lời</button>
</p>

<a href="${scriptPath}?action=article.changelog&article=${action.resource.id}">change log</a>

