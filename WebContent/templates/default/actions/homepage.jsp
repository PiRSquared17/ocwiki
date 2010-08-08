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
	    <a href="${scriptPath}?action=question.view&qv_id=${question.id}">
	       ${empty question.content ? '&lt;rỗng&gt;' : 
	               u:ellipsize(u:stripHTML(question.content), 45)}<!--  
        --></a>;
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
	    <a href="${scriptPath}?action=test.view&tv_id=${test.id}">
	        ${test.name}<!--  
	    --></a>;
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
	    <a href="${scriptPath}?action=teststruct.view&tsv_id=${struct.id}">
	        ${struct.name}<!--  
        --></a>;
        ${u:formatDateTime(struct.createDate)} . . 
        <a href="${scriptPath}?action=user.profile&user=${struct.author.id}"> 
           ${struct.author.fullname}
        </a>
    </li>
</c:forEach>
</ul>
