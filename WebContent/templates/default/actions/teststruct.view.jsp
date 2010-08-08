<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<jsp:include page="teststruct.view-toolbar.jsp"></jsp:include>

<div class="test-description mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
	<c:if test="${sessionScope.login && user.group=='teacher'}">
	    <div class="buttons">
	        <a href='${scriptPath}?action=teststruct.edit&tse_id=${teststruct.id}'><img src="${templatePath}/images/edit.png" alt="edit" title="edit" width="16px" height="16px"/></a>
	    </div>
	</c:if>
	<p>Tên: <b>${teststruct.name}</b></p>
	<c:if test="${not empty u:stripHTML(teststruct.description)}">
		<div><div style="float: left">Mô tả:&nbsp;</div> 
		   <div><b>${teststruct.description}</b></div>
		</div>
	</c:if>
	<p>Tác giả: <b><a href="${scriptPath}?action=user.profile&id=${teststruct.author.id}">${teststruct.author.fullname}</a></b></p>
</div>

<c:forEach items="${teststruct.sectionStructures}" var="sectstruct">
<div class="section">
    <div class="section-text-wrapper mouse-out"
        onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
        onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
    <c:choose>
        <c:when test="${sessionScope.login && sessionScope.user.group=='teacher'}">
            <div class="buttons">
                <a href="${scriptPath}?action=sectstruct.edit&sse_id=${sectstruct.id}"><img src="${templatePath}/images/edit.png" alt="remove" title="Sửa" width="16px" height="16px" /></a>
                <a href="${scriptPath}?action=sectstruct.delete&ssd_id=${sectstruct.id}&ssd_testid=${teststruct.id}"
                        onclick="return confirm('Bạn có chắc muốn xoá phần này?')"
                        ><img src="${templatePath}/images/wrong.png" alt="remove" title="remove" width="16px" height="16px" /></a>
                <a href="${scriptPath}?action=sectstruct.moveup&ssu_id=${sectstruct.id}&ssu_testid=${teststruct.id}"><img src="${templatePath}/images/up.png" alt="move up" title="Move up" width="16px" height="16px" /></a>
                <a href="${scriptPath}?action=sectstruct.movedown&ssw_id=${sectstruct.id}&ssw_testid=${teststruct.id}"><img src="${templatePath}/images/down.png" alt="move down" title="Move down" width="16px" height="16px" /></a>
            </div>
            <div class="section-text">
                ${(empty sectstruct.text) ? "&lt;Mục mặc định&gt;" : sectstruct.text}
            </div>
        </c:when>
        <c:otherwise>
            <div class="section-text">
                ${sectstruct.text}
            </div>
        </c:otherwise>
    </c:choose>
    </div>

    <table cellspacing="0">
        <c:forEach items="${sectstruct.topicConstraints}" var="topicConstraint">
            <tr class="row-constraint mouse-out"
                    onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                    onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
               <td>Chủ đề: <b>${topicConstraint.topic.name}</b></td>
               <td class="column-count">Số câu: <b>${topicConstraint.count}</b></td>
               <td class="column-buttons">
                    <div class="buttons">
	                    <a href="${scriptPath}?action=topicconst.delete&test=${teststruct.id}&section=${sectstruct.id}&topic=${topicConstraint.topic.id}"
	                           onclick="return confirm('Bạn có chắc muốn xoá ràng buộc này?')"
	                           >
	                        <img src="${templatePath}/images/wrong.png" alt="remove" title="remove" width="16px" height="16px" />
	                    </a>
                    </div>
               </td>
            </tr>
        </c:forEach>
        <c:forEach items="${sectstruct.levelConstraints}" var="levelConstraint">
            <tr class="row-constraint mouse-out"
                    onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                    onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
               <td>Độ khó: <b>${u:levelName(levelConstraint.level)}</b></td>
               <td class="column-count">Số câu: <b>${levelConstraint.count}</b></td>
               <td class="column-buttons">
                    <div class="buttons">
	                    <a href="${scriptPath}?action=levelconst.delete&test=${teststruct.id}&section=${sectstruct.id}&level=${levelConstraint.level}"
	                           onclick="return confirm('Bạn có chắc muốn xoá ràng buộc này?')"
	                           ><img src="${templatePath}/images/wrong.png" alt="remove" title="remove" width="16px" height="16px" />
	                    </a>
                    </div>
               </td>
            </tr>
        </c:forEach>
    </table>

</div>
</c:forEach>
