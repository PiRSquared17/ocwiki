<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<br/><br/>
<p>--Các nhận xét--</p>
<p><jsp:include page="article.view-commentstoolbar.jsp"></jsp:include></p>
<br/>
<br/>
<p>------------</p>
<div id="commentslist"> ... đang tải... </div>

<p><jsp:include page="article.view-comment.create.jsp"></jsp:include></p>

<script type="text/javascript"><!--
	var articleID = ${action.resource.id};
	var commentslisthtml = '';
	var commentslist;
	new Ajax.Request(
			restPath + '/comment_reports/resource/' + articleID + '/latest',
			{
				method: 'get',
				requestHeaders : {
					Accept : 'application/json'
				},
				evalJSON : true,
				onSuccess : function(transport) {
					commentslist = transport.responseJSON.result;
					if (commentslist.length>0){
						for (i=0;i<commentslist.length;i++){
							commentslisthtml+=showComments(commentslist[i].comment);					
						}
						$('commentslist').innerHTML = commentslisthtml;
					} else {
						$('commentslist').innerHTML = 'Chưa có nhận xét';
					}
				},
			    onFailure: function()
			    { 
					$('commentslist').innerHTML = 'Gặp lỗi khi tải nhận xét!';
			    }		
			}
		);

	function showComments(comment){
		var commenthtml='';
		commenthtml+='<div id=comment';
		commenthtml+=comment.id;
		commenthtml+='>';
		commenthtml+='vào ngày: ';
		commenthtml+=comment.timestamp.toString();
		commenthtml+=' <a href="${scriptPath}?action=user.profile&user=';
		commenthtml+=comment.user.id;
		commenthtml+='">';
		commenthtml+=comment.user.name;
		commenthtml+='</a> cho rằng:';
		commenthtml+=comment.message;
		commenthtml+='<a id=commentlike';
		commenthtml+=comment.id;
		commenthtml+=' href="#" onclick = "like(';
		commenthtml+=comment.id;
		commenthtml+='); return false;" >';
		commenthtml+='like</a>';
		commenthtml+='.<a href="#" onclick = "del(';
		commenthtml+=comment.id;
		commenthtml+='); return false;" >';
		commenthtml+='del</a>';
		commenthtml+=('<br/>------------<br/>');
		commenthtml+='</div>';
		
		return commenthtml;
	}
	
	function like(id){
		$('commentlike'+id).innerHTML='unlike';
	}

	function del(id){
	}



--></script>