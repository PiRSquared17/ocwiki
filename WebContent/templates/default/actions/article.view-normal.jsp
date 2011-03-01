<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="type" value="${action.resource.type}"></c:set>
<div style="float: right;">
    [<ocw:actionLink name="revision.list">
        <ocw:param name="r" value="${action.resource.id}"></ocw:param>
        lịch sử
    </ocw:actionLink>]
    [<ocw:actionLink name="article.edit">
        <ocw:param name="id" value="${action.resource.id}"></ocw:param>
        sửa
    </ocw:actionLink>]
    <c:if test="${sessionScope.login && sessionScope.user.group == 'admin'}">
        <c:if test="${action.resource.status != 'DELETED'}">
            [<a href="#" onclick="lockArticle(); return false;">khoá</a>]
        </c:if> 
       [<ocw:actionLink name="article.delete" onclick="markResourceDeleted(); return false;">
           <ocw:param name="id" value="${action.resource.id}"></ocw:param>
           xoá
       </ocw:actionLink>]
    </c:if>
    <c:choose>
        <c:when test="${u:assignableFrom('org.ocwiki.data.MultichoiceQuestion', type)}">
            [<ocw:actionLink name="solution.list">
               <ocw:param name="id" value="${action.resource.id}"></ocw:param>
               bài giải
            </ocw:actionLink>]
        </c:when>
        <c:when test="${u:assignableFrom('org.ocwiki.data.Test', type)}">
            [<ocw:actionLink name="test.solve">
                <ocw:param name="testId" value="${action.resource.id}"></ocw:param>
                làm
            </ocw:actionLink>]
        </c:when>
    </c:choose>
    [<ocw:actionLink name="article.print" target="_blank">
        <ocw:param name="id" value="${action.resource.id}"></ocw:param>
        in
    </ocw:actionLink>]
</div>

<h1>${action.resource.qualifiedName}</h1>

<div class="clear"></div>
<br>
<jsp:include page="/includes/${type.simpleName}.view.jsp"></jsp:include>

<div class="clear"></div>

<div class="editors">
    Tạo lúc ${u:formatDateTime(action.resource.createDate)}.
    Tác giả:
    <c:set var="editorCount" value="${u:size(action.editors)}"></c:set>
    <c:forEach var="i" begin="0" end="${editorCount-1}">
        <c:if test="${i > 0}">,</c:if>
        <ocw:userLink user="${action.editors[i]}"></ocw:userLink><%--  
    --%></c:forEach>.
</div>

<script type="text/javascript">
<!--
function markResourceDeleted() {
    resource.status = 'DELETED';
    WebService.post('/resources/' + resource.id, {
        data: resource,
        onSuccess: function (transport) {
        	location.reload(true);
        },
        onFailure: function(transport){ 
            template.onFailure(transport); 
        }
    });
}
//-->
</script>

<ocw:setJs jsVar="lock_dialog">
    <form>
        <p>
        <label>Đối tượng cho phép truy cập:<br>
            <select name="lock_value" id="lock_value">
                <option value="EVERYONE">Tất cả</option>
                <option value="AUTHOR_ONLY">Chỉ Có Tác Giả</option>
                <option value="NO_ONE">Chỉ có Admin</option>
            </select>
        </label>
        </p>
    </form>
</ocw:setJs>


<script language="javascript">
    var resourceID = ${action.resource.id};
    var timeout;

    function lockArticle()
    {
        Dialog.confirm(lock_dialog, {
            width:300, 
            okLabel: "Ok",
            cancelLabel: "Cancel", 
            buttonClass: "session-button",
            className: "alphacube", 
            cancel:function(win){}, 
            ok: function(win) 
            {   
                resource.accessibility = $F('lock_value');
                WebService.post('/resources/'+ resourceID,
                    {
                    postBody: Object.toJSON(resource),
                    onSuccess : function(transport) 
                    {
                        resource = transport.responseJSON.result;
                        location.reload(true);
                    },
                    onFailure: function(transport)
                    {
                        var code = transport.responseJSON.code;
                        if (code == 'old version') {
                              openInfoDialog("Có người đã sửa tài nguyên này trước bạn, hãy tải lại trang!");
                        } else {
                            template.onFailure(transport);
                        }
                    }
                });
            }
        });
        return;
    }

    function unlockArticle()
    {
        resource = {accessibility : 'EVERYONE', status : 'NORMAL'};
        WebService.post('/resources/'+ resourceID, {
            postBody: Object.toJSON(resource),
            onSuccess : function(transport) 
            {
                resource = transport.responseJSON.result;
                location.reload(true);
            },
            onFailure: function(transport)
            {
                var code = transport.responseJSON.code;
                if (code == 'old version') {
                      openInfoDialog("Có người đã sửa tài nguyên này trước bạn, hãy tải lại trang!");
                } else {
                    template.onFailure(transport); 
                }
            }
        });
        return;
    }
</script>