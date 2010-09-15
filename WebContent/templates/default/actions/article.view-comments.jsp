<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<br/><br/>
<p>--Các nhận xét--</p>
<p><jsp:include page="article.view-commentstoolbar.jsp"></jsp:include></p>
<p>------------</p>
<div id="commentslist"> ... đang tải... </div>

<p><jsp:include page="article.view-comment.create.jsp"></jsp:include></p>

<script type="text/javascript">
	var articleID = ${action.resource.id};
	var curPage = 0;
	var pageCount = 0;
	loadLatest();

	function loadLatest(){
		var comments;
		var commentslisthtml = '';
		new Ajax.Request(
				restPath + '/comment_reports/resource/' + articleID + '/latest',
				{
					method: 'get',
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						//alert(transport.responseText);
						var listResult = transport.responseJSON;
						pageCount = getPageCount(listResult.count);
						curPage = pageCount;
						comments = listResult.result;
						if (comments.length>0){
							for (i=0;i<comments.length;i++){
								commentslisthtml+=showComments(comments[i].comment);					
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
	}

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
		commenthtml+=('<a id="commentlike'+comment.id+'" href="#" onclick = "like('+comment.id+'); return false;" >'+'like</a>');
		commenthtml+=('.<a id="commenthide'+comment.id+'" href="#" onclick = "hideC('+comment.id+'); return false;" >'+'hide</a>');
		commenthtml+=('.<a id="commentdel'+comment.id+'" href="#" onclick = "del('+comment.id+'); return false;" >'+'del</a>');
		commenthtml+=('<br/>------------<br/>');
		commenthtml+='</div>';
		
		return commenthtml;
	}
	
	function like(id){
		alert($('commentlike'+id).innerHTML);
		$('commentlike'+id).innerHTML='unlike';
	}

	function del(id){
	}

	function hideC(id){
		alert($('commenthide'+id).innerHTML);
		$('commenthide'+id).innerHTML='unhide';
	}

	function pagination(){
		
	}

	function getPageCount(count){
		var MAX_COMMENTS_ON_PAGE = 10;
		var pCount = 0;
		if (count%MAX_COMMENTS_ON_PAGE>0)
			pCount=((count-(count%MAX_COMMENTS_ON_PAGE))/MAX_COMMENTS_ON_PAGE)+1;
		else pCount=count/MAX_COMMENTS_ON_PAGE;
		return pCount;
	}


	
	function loadPage(start){
		var comments;
		var commentslisthtml = '';
		new Ajax.Request(
				restPath + '/comment_reports/resource/' + articleID + '?start='+start,
				{
					method: 'get',
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						//alert(transport.responseText);
						comments = transport.responseJSON.result;
						if (comments.length>0){
							for (i=0;i<comments.length;i++){
								commentslisthtml+=showComments(comments[i].comment);					
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
	}

	function buttonHTML(text,value,onClickFunction){
		return '<button type="button" name="btn-'+value+'" value="'+value+'" onclick="'+onClickFunction+'">'+text+'</button>';
	}


</script>