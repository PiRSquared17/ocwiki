<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<script type="text/javascript">
<!--
var resourceId = ${action.resource.id};
var articleId = ${action.article.id};

function getResourceId() {
	return resourceId;
}

function getArticleId() {
	return articleId;
}

//-->
</script>

<h1>Sửa ${action.resource.qualifiedName}</h1>
<hr />

<form>
    <jsp:include page="/includes/${type.simpleName}.edit.jsp"></jsp:include>

    <div>
	    <label>Tóm lược: <input type="text" name="summary" value=""></label>
	    <label><input type="checkbox" name="minor" value="true"> Sửa đổi nhỏ</label>
    </div>
    <div>
	    <button name="preview" type="submit" onclick="alert('preview'); return false;">Xem thử</button>
	    <button name="save" type="submit" onclick="${type.simpleName}.save(); return false;">Lưu</button>
	    <button name="back" type="submit" onclick="alert('back'); return false;">Quay lại bài viết</button>
    </div>
</form>