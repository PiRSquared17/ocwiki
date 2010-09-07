<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<p><jsp:include page="article.view-commentstoolbar.jsp"></jsp:include></p>
<br/>
<br/>
<p>--start comments list here--</p>
<p><jsp:include page="article.view-comment.view.jsp"></jsp:include></p>
<br/>
<br/>
<p>--end comments list here--</p>

<p><jsp:include page="article.view-comment.create.jsp"></jsp:include></p>
