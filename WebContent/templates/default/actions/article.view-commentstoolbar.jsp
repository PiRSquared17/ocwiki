<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>


<div id="comments-tool">
		<button type="button" name="btn-oldfirst" value="oldfirst" onclick="loadPage(0);">Cũ nhất</button>
<!--		<div id="comment-pages"></div>-->
		<button type="button" name="btn-newfirsr" value="newfirst" onclick="loadLatest();">Mới nhất</button>
</div>