<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<p><jsp:include page="article.view-commentstoolbar.jsp"></jsp:include></p>
<br/>
<br/>
<p>--start comments list here--</p>
<p><jsp:include page="article.view-comment.view.jsp"></jsp:include></p>
<br/>
<br/>
<div id="commentslist"> ... đang tải... </div>
<p>--end comments list here--</p>

<p><jsp:include page="article.view-comment.create.jsp"></jsp:include></p>

<script type="text/javascript">
	var articleID = ${action.resource.id};
	var commentslisthtml = '';
	var commentslist;
	new Ajax.Request(
			restPath + '/comments/resource/' + articleID + '/latest',
			{
				method: 'get',
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					commentslist = transport.responseJSON.result;
					for (var i=0;i<commentslist.size();i++){
						commentslisthtml+=commentslist[i].message;
						commentslisthtml+=('<br/>------------<br/>');
					}
					$('commentslist').innerHTML = commentslisthtml;
				},
			    onFailure: function()
			    { 
					$('commentslist').innerHTML = 'Gặp lỗi khi tải nhận xét!';
			    }		
			}
		);
	

</script>