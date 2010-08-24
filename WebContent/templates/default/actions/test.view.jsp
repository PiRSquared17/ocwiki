<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="test" value="${action.test}"></c:set>

<div>
<jsp:include page="test.view-toolbar.jsp"></jsp:include>

<div class="test-description mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
	<c:if test="${sessionScope.login && user.group=='teacher'}">
	    <div class="buttons">
	       <ocw:actionLink name="test.edit">
	           <ocw:param name="test" value="${action.resource.id}"></ocw:param>
	           <img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="16px" height="16px" />
	       </ocw:actionLink>
	  	</div>
	</c:if>
	<h3>${test.name}</h3>
	<p><b>Mô tả:</b> ${test.content}</p>
	<p><b>Thời gian:</b> ${test.time} phút</p>
</div>

<c:set var="type" value="${test.type=='radio' ? 'radio' : 'checkbox'}"></c:set>
<c:set var="i" value="1"></c:set>
<c:set var="sectionIndex" value="0"></c:set>

<c:forEach items="${test.sections}" var="section">
<div class="section">
    <div class="section-text-wrapper mouse-out"
	        onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
	        onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
		<c:choose>
			<c:when test="${sessionScope.login && user.group=='teacher'}">
				<div class="buttons">
				    <ocw:actionLink name="section.edit">
				        <ocw:param name="test" value="${action.resource.id}"></ocw:param>
				        <ocw:param name="section" value="${sectionIndex}"></ocw:param>
                        <img src="${templatePath}/images/edit.png" alt="Sửa" title="Sửa" width="16px" height="16px" />
				    </ocw:actionLink>
				    <ocw:actionLink name="section.delete" confirm="confirm('Bạn có chắc muốn xoá phần này?')">
				        <ocw:param name="test" value="${action.resource.id}"></ocw:param>
				        <ocw:param name="section" value="${sectionIndex}"></ocw:param>
				        <img src="${templatePath}/images/wrong.png" alt="Xoá" title="Xoá" width="16px" height="16px" />
				    </ocw:actionLink>
				    <ocw:actionLink name="section.moveup">
				        <ocw:param name="test" value="${action.resource.id}"></ocw:param>
				        <ocw:param name="section" value="${sectionIndex}"></ocw:param>
				        <img src="${templatePath}/images/up.png" alt="move up" title="Move up" width="16px" height="16px" />
				    </ocw:actionLink>
				    <ocw:actionLink name="section.movedown">
				        <ocw:param name="test" value="${action.resource.id}"></ocw:param>
				        <ocw:param name="section" value="${sectionIndex}"></ocw:param>
				        <img src="${templatePath}/images/down.png" alt="move down" title="Move down" width="16px" height="16px" />
				    </ocw:actionLink>
				</div>
				<div class="section-text">
				    ${(empty section.content.text) ? "&lt;Mục mặc định&gt;" : section.content.text}
				</div>
			</c:when>
			<c:otherwise>
				<div class="section-text">
    				${section.content.text}
    			</div>
			</c:otherwise>
		</c:choose>
	</div>

	<c:forEach items="${section.questions}" var="question">
		<div class="question-wrapper mouse-out" 
		      onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
		      onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
			<c:if test="${sessionScope.login && user.group=='teacher'}">
				<div class="buttons">
				    <ocw:actionLink name="test.editquestion">
					    <ocw:param name="test" value="${action.resource.id}"></ocw:param>
					    <img src="${templatePath}/images/edit.png" alt="Sửa" title="Sửa" width="16px" height="16px" />
					</ocw:actionLink>
					<ocw:actionLink name="test.deletequestion" confirm="confirm('Bạn có chắc muốn xoá câu hỏi này?')">
					    <ocw:param name="test" value="${action.resource.id}"></ocw:param>
					    <ocw:param name="section" value="${sectionIndex}"></ocw:param>
					    <ocw:param name="question" value="${questionIndex}"></ocw:param>
					    <img src="${templatePath}/images/wrong.png" alt="Xoá" title="Xoá" width="16px" height="16px" />
					</ocw:actionLink>
				</div>
			</c:if>

            <div class="question">
				<div class="question-number-wrapper">
					<b><ocw:articleLink resource="${question.baseContainer}">Câu ${i}</ocw:articleLink></b> (${question.mark} điểm):
				</div>
				<div class="question-content-wrapper">${question.content}</div>
				<div>
					<c:set var="j" value="0" />
                    <div class="answer-list-wrapper">
					<c:forEach items="${question.answers}" var="answer">
					   <div class="answer-wrapper">
						    <div class="number-wrapper">
						       <b>${u:alpha(j)}</b>.
		                    </div>
		                    <div>${answer.content}</div>
				            <c:set var="j" value="${j+1}" />
			            </div>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<c:set var="i" value="${i+1}"></c:set>
	</c:forEach>
</div>
<c:set var="sectionIndex" value="${sectionIndex+1}"></c:set>
</c:forEach>

<ocw:topics article="${test}"></ocw:topics>

<jsp:include page="test.view-toolbar.jsp"></jsp:include>
</div>