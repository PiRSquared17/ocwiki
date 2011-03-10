<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %> 
    
<p>Hiện tại chúng ta có <b>${action.userCount}</b> thành viên, đóng góp 
<b>${action.textArticleCount}</b> bài viết, 
<b>${action.questionCount}</b> câu hỏi, 
<b>${action.testCount}</b> đề thi.</p>

<div>
<h3>Bài viết mới</h3>
<ul class="list-new">
<c:forEach items="${action.textArticles}" var="article">
    <li>
        <ocw:articleLink resource="${article}" />
        ${u:formatDateTime(article.createDate)} . .
        <ocw:userLink user="${article.author}"></ocw:userLink> 
    </li>
</c:forEach>
</ul>
</div>

<div>
<h3>Câu hỏi mới</h3>
<ul class="list-new">
<c:forEach items="${action.questions}" var="question">
    <li>
        <ocw:articleLink resource="${question}">${empty question.article.content ? '&lt;rỗng&gt;' : 
                   u:ellipsize(u:stripHTML(question.article.content), 45)}</ocw:articleLink>
        ${u:formatDateTime(question.createDate)} . .
        <ocw:userLink user="${question.author}"></ocw:userLink> 
    </li>
</c:forEach>
</ul>
</div>

<h3>Đề thi mới</h3>
<ul class="list-new">
<c:forEach items="${action.tests}" var="test">
    <li>
        <ocw:articleLink resource="${test}">${test.name}</ocw:articleLink>
        ${u:formatDateTime(test.createDate)} . . 
        <ocw:userLink user="${test.author}"></ocw:userLink> 
    </li>
</c:forEach>
</ul>