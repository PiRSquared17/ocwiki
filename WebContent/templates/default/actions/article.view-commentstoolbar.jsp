<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>


<div id="comments-tool" align="center">
<!--		<button type="button" id="btn-oldfirst" name="btn-oldfirst" value="oldfirst" onclick="loadPage(0);">C? nh?t</button>-->
<!--		<button type="button" id="btn-prev" value="prev" onclick="loadPage(curPage-1);">prev</button>-->
		<span id="btn-oldfirst"> <a id="link-oldfirst" href="#" onclick="loadPage(0); return false;" >C? nh?t</a> </span>
		<span id="btn-prev"> <a id="link-prev" href="#" onclick="loadPage(curPage-1); return false;" >Tr??c</a> </span>
		<span id="comment-pages"></span>
		<span id="btn-next"> <a id="link-next" href="#" onclick="loadPage(curPage+1); return false;" >Sau</a> </span>
		<span id="btn-newfirst"> <a id="link-newfirst" href="#" onclick="loadLatest(); return false;" >M?i nh?t</a> </span>
<!--		<button type="button" id="btn-next" value="next" onclick="loadPage(curPage+1);">next</button>-->
<!--		<button type="button" id="btn-newfirsr" name="btn-newfirsr" value="newfirst" onclick="loadLatest();">M?i nh?t</button>-->
</div>