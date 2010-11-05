<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<p><strong> Warning During the progress of a MassIndexer the
content of the index is undefined, make sure that nobody will try to
make some query during index rebuilding! If somebody should query the
index it will not corrupt but most results will likely be missing. </strong></p>

<p>${action.run}</p>