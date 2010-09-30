<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>

<c:set var="teststruct" value="${empty article ? action.article : article}"></c:set>

<div class="test-description mouse-out"
            onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
            onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
    <p>Tên: <b>${teststruct.name}</b></p>
    <c:if test="${not empty u:stripHTML(teststruct.description)}">
        <div><div style="float: left">Mô tả:&nbsp;</div> 
           <div><b>${teststruct.description}</b></div>
        </div>
    </c:if>
    <p>Tác giả: <ocw:userLink user="${action.resource.author}">${action.resource.author.fullname}</ocw:userLink></p>
</div>

<c:set var="sectionIndex" value="0"></c:set>
<c:forEach items="${teststruct.sectionStructures}" var="sectstruct">
<div class="section">
    <div class="section-text-wrapper mouse-out"
        onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
        onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
    <c:if test="${not empty sectstruct.content.text}">
        <div class="section-text">
            ${sectstruct.content.text}
        </div>
    </c:if>
    </div>

    <table cellspacing="0">
        <c:forEach items="${sectstruct.topicConstraints}" var="topicConstraint">
            <tr class="row-constraint mouse-out"
                    onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                    onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
               <td>Chủ đề:
                   <c:set var="notFirstTopic" value="${false}"></c:set>
                   <c:forEach items="${topicConstraint.topics}" var="topic">
                       <c:if test="${notFirstTopic}">,</c:if>
		               <ocw:articleLink resource="topic" />
	                   <c:set var="isFirstTopic" value="${true}"></c:set>
                   </c:forEach> 
               </td>
               <td class="column-count">Số câu: <b>${topicConstraint.count}</b></td>
            </tr>
        </c:forEach>
        <c:forEach items="${sectstruct.levelConstraints}" var="levelConstraint">
            <tr class="row-constraint mouse-out"
                    onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                    onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
               <td>Độ khó: <b>${u:levelName(levelConstraint.level)}</b></td>
               <td class="column-count">Số câu: <b>${levelConstraint.count}</b></td>
            </tr>
        </c:forEach>
    </table>

</div>
<c:set var="sectionIndex" value="${sectionIndex+1}"></c:set>
</c:forEach>

<ocw:topics article="${test}"></ocw:topics>