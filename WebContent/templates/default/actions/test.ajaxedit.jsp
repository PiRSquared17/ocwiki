<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<script type="text/javascript">
<!--

function saveSectionCreate() {
	
}

//-->
</script>

<c:set var="test" value="${action.test}"></c:set>

<div class="test-description mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
    <c:if test="${sessionScope.login && user.group=='teacher'}">
        <div class="buttons">
            <a href='${scriptPath}?action=test.edit&te_id=${test.id}'><img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="16px" height="16px" /></a>
        </div>
    </c:if>
    <h3>${test.name}</h3>
    <p><b>Mô tả:</b> ${test.content}</p>
    <p><b>Thời gian:</b> ${test.time} phút</p>
</div>

<c:set var="type" value="${test.type=='radio' ? 'radio' : 'checkbox'}"></c:set>
<c:set var="i" value="1"></c:set>

<c:forEach items="${test.sections}" var="section">
<div class="section">
    <div class="section-text-wrapper mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
        <c:choose>
            <c:when test="${sessionScope.login && user.group=='teacher'}">
                <div class="buttons">
                    <a href="${scriptPath}?action=section.edit&id=${section.id}"><img src="${templatePath}/images/edit.png" alt="remove" title="Sửa" width="16px" height="16px" /></a> 
                    <a href="${scriptPath}?action=section.delete&sd_id=${section.id}&test=${test.id}"><img src="${templatePath}/images/wrong.png" 
                           onclick="return confirm('Bạn có chắc muốn xoá phần này?');"
                           alt="remove" title="remove" width="16px" height="16px" /></a> 
                    <a href="${scriptPath}?action=section.moveup&id=${section.id}&testid=${test.id}"><img src="${templatePath}/images/up.png" alt="move up" title="Move up" width="16px" height="16px" /></a> 
                    <a href="${scriptPath}?action=section.movedown&id=${section.id}&testid=${test.id}"><img src="${templatePath}/images/down.png" alt="move down" title="Move down" width="16px" height="16px" /></a>
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

    <c:forEach items="${section.questions}" var="questionContainer">
        <c:set var="question" value="${questionContainer.article}"></c:set>
        <div class="question-wrapper mouse-out" 
              onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
              onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
            <c:if test="${sessionScope.login && user.group=='teacher'}">
                <div class="buttons">
                    <a href="${scriptPath}?action=test.editquestion&test=${test.id}&section=${section.id}&question=${question.id}"><img src="${templatePath}/images/edit.png" alt="Sửa" title="Sửa" width="16px" height="16px" /></a>
                    <a href="${scriptPath}?action=test.deletequestion&tdq_sectid=${section.id}&tdq_question=${question.id}"
                           onclick="return confirm('Bạn có chắc muốn xoá câu hỏi này?');"
                           ><img src="${templatePath}/images/wrong.png" alt="Xoá" title="Xoá" width="16px" height="16px" /></a>
                </div>
            </c:if>

            <div class="question">
                <div class="question-number-wrapper">
                    <b><ocw:articleLink resource="${questionContainer}">Câu ${i}</ocw:articleLink></b> (${question.mark} điểm):
                </div>
                <div class="question-content-wrapper">${question.content}</div>
                <div>
                    <c:set var="j" value="0" />
                    <div class="answer-list-wrapper">
                    <c:forEach items="${question.usedAnswers}" var="answer">
                       <div class="answer-wrapper">
                            <div class="answer-number-wrapper">
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
</c:forEach>

<div class="section-create-wrapper" style="display:none">
    <textarea rows="15" cols="80" id="section-create-text"></textarea>
    <button type="button" onclick="saveSectionCreate()">Lưu</button>
    <button type="button" onclick="cancelSectionCreate()">Huỷ</button>
</div>