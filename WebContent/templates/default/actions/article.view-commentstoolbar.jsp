<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>


<div id="comments-tool">
		<button type="button" id="btn-oldfirst" name="btn-oldfirst" value="oldfirst" onclick="loadPage(0);">Cũ nhất</button>
		<button type="button" id="btn-prev" value="prev" onclick="loadPage(curPage-1);">prev</button>
		<span id="comment-pages"></span>
		<button type="button" id="btn-next" value="next" onclick="loadPage(curPage+1);">next</button>
		<button type="button" id="btn-newfirsr" name="btn-newfirsr" value="newfirst" onclick="loadLatest();">Mới nhất</button>
</div>