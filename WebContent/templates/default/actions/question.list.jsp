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
			<c:forEach items="${question.choices}" var="choice">
				a${choice.id} : ${choice.correct},
			</c:forEach>},
		</c:forEach>
		};
	
		function checkchoice(question_id) {
			choice = document.getElementsByName(question_id+'-choices');
			
			for (i=0;i<4;i++) {
				rightchoice = document.getElementById(question_id+'a' + choice[i].value + '-rightchoice');
				wrongchoice = document.getElementById(question_id+'a' + choice[i].value + '-wrongchoice');
				rightchoice.style.display = 'none';
				wrongchoice.style.display = 'none';
				if(choice[i].checked == eval('correct.q'+question_id+'.a' + choice[i].value))
					{
						if (choice[i].checked == true )
							rightchoice.style.display='inline';
					} else if (choice[i].checked == true )
						wrongchoice.style.display='inline';
					
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
			<div class="choice-list-wrapper">
			<c:forEach items="${question.choices}" var="choice">
				<div class="choice-wrapper">
				    <div class="check-wrapper">
					   <input type="radio" name="${question.id}-choices" value="${choice.id}" id="${question.id}-choice-${choice.id}">
					</div>
                    <div class="marker-wrapper"> 
                        <span id="${question.id}a${choice.id}-rightchoice" style="display:none;"><img src="${templatePath}/images/right.png" alt="right choice" width="12" height="12" /></span>
                        <span id="${question.id}a${choice.id}-wrongchoice" style="display:none;"><img src="${templatePath}/images/wrong.png" alt="wrong choice" width="12" height="12" /></span>
                    </div>
					<label for="${question.id}-choice-${choice.id}">${choice.content}</label>
				</div>
			</c:forEach>
			</div>
			<button type="button" onclick="checkchoice(${question.id})">Trả lời</button>
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
