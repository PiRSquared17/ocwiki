<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:choose>

<c:when test="${u:size(topLevels) > 0}">
	    
	<script type="text/javascript">
	<!--
	
	function validateDelete() {
		var topics = $('listForm').topics;
		for (i = 0; i < cl_topics.length; i++) {
			if (topics[i].checked) {
				return confirm('Bạn có chắc muốn xóa (các) chủ đề này?');
			}
		}
		alert('Bạn cần chọn ít nhất 1 chủ đề.');
		return false;
	}
	
	//-->
	</script>
	    
	${message}
	    
	<form action="${scriptPath}" id="listForm">
	<input type="hidden" name="action" value="topic.list" />
	
	<jsp:include page="topic.list-toolbar.jsp"></jsp:include>
	
	<div class="clear"></div>
	<div class="content-wrapper">
        <ul style="list-style: none;">
		<c:forEach items="${topLevels}" var="topLevel" >
			<c:set var="topic" value="${topLevel}" scope="request" />
			<jsp:include page="topic.list-item.jsp"/>
		</c:forEach>
		</ul>
	</div>
	
	<jsp:include page="topic.list-toolbar.jsp"></jsp:include>
	
	</form>
	
</c:when>

<c:otherwise>
    <div class="empty-notif">
        Chưa có dữ liệu
    </div>
</c:otherwise>

</c:choose>