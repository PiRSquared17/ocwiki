<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %> 
    
<p>Hiện tại chúng ta có <b>${action.userCount}</b> thành viên, đóng góp 
<b>${action.questionCount}</b> câu hỏi, <b>${action.testCount}</b> đề thi và 
<b>${action.structCount}</b> cấu trúc đề.</p>

<div>
<h3>10 câu hỏi mới nhất</h3>
<ul class="list-new">
<c:forEach items="${action.questions}" var="question">
    <li>
        <ocw:articleLink resource="${question}">${empty question.article.content ? '&lt;rỗng&gt;' : 
                   u:ellipsize(u:stripHTML(question.article.content), 45)}</ocw:articleLink>
        ${u:formatDateTime(question.createDate)} . . 
	    <a href="${scriptPath}?action=user.profile&user=${question.author.id}"> 
	       ${question.author.fullname}
	    </a>
    </li>
</c:forEach>
</ul>
</div>

<h3>10 đề thi mới nhất</h3>
<ul class="list-new">
<c:forEach items="${action.tests}" var="test">
    <li>
        <ocw:articleLink resource="${test}"></ocw:articleLink>
        ${u:formatDateTime(test.createDate)} . . 
        <a href="${scriptPath}?action=user.profile&user=${test.author.id}"> 
           ${test.author.fullname}
        </a>
    </li>
</c:forEach>
</ul>

<h3>10 cấu trúc đề mới nhất</h3>
<ul class="list-new">
<c:forEach items="${action.testStructures}" var="struct">
    <li>
        <ocw:articleLink resource="${struct}"></ocw:articleLink>
        ${u:formatDateTime(struct.createDate)} . . 
        <a href="${scriptPath}?action=user.profile&user=${struct.author.id}"> 
           ${struct.author.fullname}
        </a>
    </li>
</c:forEach>
</ul>
