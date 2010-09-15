<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<script type="text/javascript">
<!--
resourceId = ${action.resource.id};
//-->
</script>

<h1>Sửa ${action.resource.qualifiedName}</h1>
<hr />

<form>
    <jsp:include page="/includes/${type.simpleName}.edit.jsp"></jsp:include>

    <div>
	    <label>Tóm lược: <input id="articleEdit-summary" type="text" name="summary" value=""></label>
	    <label><input id="articleEdit-minor" type="checkbox" name="minor" value="true"> Sửa đổi nhỏ</label>
    </div>
    <div>
	    <button id="articleEdit-preview" name="preview" type="submit" onclick="alert('preview'); return false;">Xem thử</button>
	    <button id="articleEdit-save" name="save" type="submit" onclick="EditAction.save(); return false;">Lưu</button>
	    <button id="articleEdit-back" name="back" type="submit" onclick="alert('back'); return false;">Quay lại bài viết</button>
	    <span class="error-validating" id="articleEdit-error"></span>
    </div>
</form>