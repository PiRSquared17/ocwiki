<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>

<script type="text/javascript">
<!--
resourceId = ${action.resource.id};
//-->
</script>

<form>
    <%-- <h3>/includes/${type.simpleName}.edit.jsp</h3> --%>
    <jsp:include page="/includes/${type.simpleName}.edit.jsp"></jsp:include>  

    <div>
	    <label>Tóm lược: <input id="articleEdit-summary" type="text" name="summary" value=""></label>
	    <label><input id="articleEdit-minor" type="checkbox" name="minor" value="true"> Sửa đổi nhỏ</label>
    </div>
    <div>
        <!--  
	    <button id="articleEdit-preview" name="preview" type="submit" onclick="alert('preview'); return false;">Xem thử</button>
	    -->
	    <button id="articleEdit-save" name="save" type="submit" onclick="saveArticle(); return false;">Lưu</button>
	    <button id="articleEdit-back" name="back" type="submit" onclick="backArticle(); return false;">Quay lại bài viết</button>
	    <span class="error-validating" id="articleEdit-error"></span>
    </div>
</form>

<script type="text/javascript">
<!--
function backArticle() {
	location.href= '${config.articlePath}/${action.resource.id}';
}

function failureCallback() {
    $('articleEdit-save').disabled = false;
    $('articleEdit-back').disabled = false;
}

function saveArticle() {
	$('articleEdit-save').disabled = true;
    $('articleEdit-back').disabled = true;
	EditAction.save(backArticle, failureCallback);
}
//-->
</script>