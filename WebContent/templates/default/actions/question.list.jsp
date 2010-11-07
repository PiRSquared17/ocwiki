<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>


<c:choose>

<c:when test="${action.count > 0}">
	
	<script>
	
	var correct = {
		<c:forEach items="${questions}" var="resource">
		<c:set var="question" value="${resource.article}" />
	 	q${question.id} :{
			<c:forEach items="${question.answers}" var="answer">
				a${answer.id} : ${answer.correct},
			</c:forEach>},
		</c:forEach>
		};
	
		function checkanswer(question_id) {
			answer = document.getElementsByName(question_id+'-answers');
			
			for (i=0;i<4;i++) {
				rightanswer = document.getElementById(question_id+'a' + answer[i].value + '-rightanswer');
				wronganswer = document.getElementById(question_id+'a' + answer[i].value + '-wronganswer');
				rightanswer.style.display = 'none';
				wronganswer.style.display = 'none';
				if(answer[i].checked == eval('correct.q'+question_id+'.a' + answer[i].value))
					{
						if (answer[i].checked == true )
							rightanswer.style.display='inline';
					} else if (answer[i].checked == true )
						wronganswer.style.display='inline';
					
			}
		}
	</script>
	
	<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>
	
    <ocw:pagination count="${action.count}" currentStart="${action.start}"></ocw:pagination>
	
	<table>
	<tr>
        <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
		  <th width="20px">&nbsp;</th>
		</c:if>
		<th>Nội dung</th>
		<th width="100px">Tác&nbsp;giả</th>
	</tr>
    <c:forEach items="${questions}" var="resource">
    <c:set var="question" value="${resource.article}" />
	<tr>
        <c:if test="${sessionScope.login && sessionScope.user.group == 'teacher'}">
		  <td><input type="checkbox" name="ql_questions" value="${question.id}"></td>
		</c:if>
		<td>
		   <div style="float:left; margin-right: 5px">
		      <i><ocw:articleLink resource="${resource}">#${resource.id}</ocw:articleLink></i> 
		   </div>
		   ${question.content}
			<div class="answer-list-wrapper">
			<c:forEach items="${question.answers}" var="answer">
				<div class="answer-wrapper">
				    <div class="check-wrapper">
					   <input type="radio" name="${question.id}-answers" value="${answer.id}" id="${question.id}-answer-${answer.id}">
					</div>
                    <div class="marker-wrapper"> 
                        <span id="${question.id}a${answer.id}-rightanswer" style="display:none;"><img src="${templatePath}/images/right.png" alt="right answer" width="12" height="12" /></span>
                        <span id="${question.id}a${answer.id}-wronganswer" style="display:none;"><img src="${templatePath}/images/wrong.png" alt="wrong answer" width="12" height="12" /></span>
                    </div>
					<label for="${question.id}-answer-${answer.id}">${answer.content}</label>
				</div>
			</c:forEach>
			</div>
			<button type="button" onclick="checkanswer(${question.id})">Trả lời</button>
		</td>
		<td valign="top" align="center">
		    <ocw:actionLink name="search">
		      <ocw:param name="search_query" value="type:question author:#${resource.author.id}"></ocw:param>
		      ${resource.author.fullname}
		    </ocw:actionLink>
		</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
    
</c:when>

<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>
