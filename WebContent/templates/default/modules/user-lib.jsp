<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ul>
	<li><ocw:actionLink name="history/list"
		title="Xem lịch sử các bài kiểm tra đã làm">Lịch sử làm đề</ocw:actionLink>
	</li>
	<li>
	<ocw:actionLink name="search">
	    <ocw:param name="search_query" value="is:todo"></ocw:param>
	    Bài cần làm
	</ocw:actionLink>
	</li>
	<li>
	<ocw:actionLink name="search">
	    <ocw:param name="search_query" value="is:liked"></ocw:param>
	    Bài ưa thích
	</ocw:actionLink>
	</li>
	<li>
	<ocw:actionLink name="search">
	    <ocw:param name="search_query" value="is:hard"></ocw:param>
	    Bài khó
	</ocw:actionLink>
	</li>
	<li>
	<ocw:actionLink name="search">
	    <ocw:param name="search_query" value="is:easy"></ocw:param>
	    Bài dễ
	</ocw:actionLink>
	</li>
	<li>
	<ocw:actionLink name="search">
	    <ocw:param name="search_query" value="is:done"></ocw:param>
	    Bài đã làm
	</ocw:actionLink>
	</li>
</ul>