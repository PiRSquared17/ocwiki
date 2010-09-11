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
        <ocw:actionButton name="question.edit">
		    <ocw:param name="id" value="${action.resource.id}"></ocw:param>
		    Sửa
		</ocw:actionButton>
        <button onclick="location.href='${scriptPath}?action=answer.create&question=${action.resource.id}'">Thêm phương án trả lời</button>
        <button onclick="location.href='${scriptPath}?action=question.create'">Thêm câu hỏi</button>
        <button onclick="location.href='${scriptPath}?action=question.list'">Quay về danh sách</button>
    </div>
</c:if>

<p>Tác giả: <b><ocw:userLink user="${action.resource.author}">${action.resource.author.fullname}</ocw:userLink></b></p>
<p>Thời điểm tạo: <b>${u:formatDateTime(action.resource.createDate)}</b></p>

<div>
    <ocw:parse resource="${action.resource}">${question.content}</ocw:parse>
	<div class="answer-list-wrapper">
	<c:set var="answerIndex" value="0"></c:set>
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
                   <ocw:actionLink name="answer.edit">
                        <ocw:param name="question" value="${action.resource.id}"></ocw:param>
                        <ocw:param name="answer" value="${answerIndex}"></ocw:param>
                        <img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="16px" height="16px" />
                    </ocw:actionLink>
                   <ocw:actionLink name="answer.delete" confirm="confirm('Bạn có chắc muốn xoá phương án trả lời này?')">
                        <ocw:param name="question" value="${action.resource.id}"></ocw:param>
                        <ocw:param name="answer" value="${answerIndex}"></ocw:param>
                        <img src="${templatePath}/images/wrong.png" alt="edit" title="remove" width="16px" height="16px" />
                    </ocw:actionLink>
	            </div>
            </c:if>

            <div style="margin-right: 60px">
			     <label for="answer-${answer.id}">
			         <ocw:parse resource="${action.resource}">${answer.content}</ocw:parse>
			     </label>
			</div>
		</div>
        <c:set var="answerIndex" value="${answerIndex+1}"></c:set>
	</c:forEach>
	</div>
</div>

<ocw:topics article="${question}"/>

<p>
	<button onclick="checkanswer()">Trả lời</button>
</p>

<ocw:actionLink name="revision.list">
    <ocw:param name="resourceID" value="${action.resource.id}"></ocw:param>
    revision list
</ocw:actionLink>

