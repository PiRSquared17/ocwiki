<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="toolbar">
	<c:if test="${sessionScope.login}">
	   <c:if test="${test.questionCount > 0}">
	       <ocw:actionButton name="test.solve">
	           <ocw:param name="test" value="${action.resource.id}"></ocw:param>
	           Làm
	       </ocw:actionButton>
	   </c:if>
		<c:if test="${sessionScope.user.group=='teacher'}">
           <ocw:actionButton name="test.ajaxedit">
               <ocw:param name="test" value="${action.resource.id}"></ocw:param>
               new edit page
           </ocw:actionButton>
           <ocw:actionButton name="section.create">
               <ocw:param name="test" value="${action.resource.id}"></ocw:param>
               Thêm mục
           </ocw:actionButton>
           <ocw:actionButton name="test.addquestion">
               <ocw:param name="test" value="${action.resource.id}"></ocw:param>
               Thêm câu hỏi
           </ocw:actionButton>
           <ocw:actionButton name="test.list" confirm="confirm('Bạn có chắc muốn xoá đề thi này?')">
               <ocw:param name="test" value="${action.resource.id}"></ocw:param>
               Xóa
           </ocw:actionButton>
		</c:if>
	</c:if>
    <ocw:actionButton name="test.savedocx">
        <ocw:param name="test" value="${action.resource.id}"></ocw:param>
        Xuất DOCX
    </ocw:actionButton>
    <ocw:actionButton name="test.list">
        Quay về danh sách
    </ocw:actionButton>
</div>
