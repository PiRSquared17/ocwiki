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
	var commentCount = 0;
	loadLatest();
	

	//load comments
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
						commentCount = listResult.count;
						pageCount = getPageCount(listResult.count);
						curPage = pageCount-1;
						
						comments = listResult.result;
						if (comments==null){
							$('commentslist').innerHTML = 'Chưa có nhận xét';
							pagination();
						} else {
							if (comments.length>0){
								for (i=0;i<comments.length;i++){
									commentslisthtml+=showComments(comments[i].comment);					
								}
								$('commentslist').innerHTML = commentslisthtml;
								pagination();
							} else {
								$('commentslist').innerHTML = 'Chưa có nhận xét';
								pagination();
							}
						}
					},
				    onFailure: function()
				    { 
						$('commentslist').innerHTML = 'Gặp lỗi khi tải nhận xét!';
				    }		
				}
			);
	}

	function loadPage(page){
		var start = page*10;
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
						var listResult = transport.responseJSON;
						comments = listResult.result;
						if (comments==null){
							$('commentslist').innerHTML = 'Chưa có nhận xét';
						} else {
							if (comments.length>0){
								for (i=0;i<comments.length;i++){
									commentslisthtml+=showComments(comments[i].comment);					
								}
								$('commentslist').innerHTML = commentslisthtml;
								curPage = page;
								pagination();
							} else {
								$('commentslist').innerHTML = 'Chưa có nhận xét';
							}
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
		commenthtml+=('<div id=comment'+comment.id+'>');
		commenthtml+=('vào ngày: '+comment.timestamp);
		commenthtml+=(' <a href="${scriptPath}?action=user.profile&user='+comment.user.id+'">'+comment.user.name+'</a> cho rằng:');
		commenthtml+=('<div id=commentmessage'+comment.id+'>'+comment.message+'</div>');
		commenthtml+=('<a id="commentlike'+comment.id+'" href="#" onclick = "like('+comment.id+'); return false;" >'+'like</a>');
		commenthtml+=('.<a id="commenthide'+comment.id+'" href="#" onclick = "hideC('+comment.id+'); return false;" >'+'hide</a>');
		commenthtml+=('.<a id="commentdel'+comment.id+'" href="#" onclick = "del('+comment.id+'); return false;" >'+'del</a>');
		commenthtml+=('<br/>------------<br/>');
		commenthtml+='</div>';
		
		return commenthtml;
	}
	
	function like(id){
		$('commentlike'+id).innerHTML='unlike';
	}

	function del(id){
	}

	function hideC(id){
		$('commenthide'+id).innerHTML='unhide';
	}

	//Phan trang
	function pagination(){
		var pageButtons = '';
		if (pageCount>12) {
			if (curPage<=5){
				for (var i=0; i<=curPage+2; i++){
					pageButtons+=buttonHTML(i+1,i,('loadPage('+i+')'));				
				}
				pageButtons+='...';
				for (var j=pageCount-3; j<=pageCount-1; j++){
					pageButtons+=buttonHTML(j+1,j,('loadPage('+j+')'));	
				}
			} else if (curPage>=pageCount-6) {
				for (var i=0; i<=2; i++){
					pageButtons+=buttonHTML(i+1,i,('loadPage('+i+')'));				
				}
				pageButtons+='...';
				for (var j=curPage-2; j<=pageCount-1; j++){
					pageButtons+=buttonHTML(j+1,j,('loadPage('+j+')'));	
				}
			} else {
				for (var i=0; i<=2; i++){
					pageButtons+=buttonHTML(i+1,i,('loadPage('+i+')'));				
				}
				pageButtons+='...';
				for (var k=curPage-2; k<=curPage+2; k++){
					pageButtons+=buttonHTML(k+1,k,('loadPage('+k+')'));	
				}
				pageButtons+='...';
				for (var j=pageCount-3; j<=pageCount-1; j++){
					pageButtons+=buttonHTML(j+1,j,('loadPage('+j+')'));	
				}
			}
			$('comment-pages').innerHTML = pageButtons;
		} else if (pageCount>0){
			for (var i=0; i<pageCount; i++){
				pageButtons+=buttonHTML(i+1,i,('loadPage('+i+')'));				
			}
			$('comment-pages').innerHTML = pageButtons;
			
		} else {
			alert('else');
		}

		
		disableButtons();
	}

	function disableButtons(){
		//alert('disbut');
		$('btn-'+(curPage)).innerHTML = ' '+(curPage+1)+' ';
		if (curPage<=0) {
			$('btn-prev').hide();
			$('btn-oldfirst').hide();
		} else {
			$('btn-prev').show();
			$('btn-oldfirst').show();
		}
		if (curPage>=pageCount-1){
			$('btn-next').hide();
			$('btn-newfirst').hide();
		} else {
			$('btn-next').show();
			$('btn-newfirst').show();
		}
	}
	
	function getPageCount(count){
		var MAX_COMMENTS_ON_PAGE = 10;
		var pCount = 0;
		if (count%MAX_COMMENTS_ON_PAGE>0){
			pCount=((count-(count%MAX_COMMENTS_ON_PAGE))/MAX_COMMENTS_ON_PAGE)+1;
		}
		else pCount=count/MAX_COMMENTS_ON_PAGE;
		return pCount;
	}

	function buttonHTML(text,value,onClickFunction){
		return '<span id="btn-'+value+'" > <a id="link-'+value+'" href="#" onclick="'+onClickFunction+'; return false;" >'+text+'</a> </span>';
	}
	
	function rawButtonHTML(text,value,onClickFunction){
		return '<button type="button" id="btn-'+value+'" name="btn-'+value+'" value="'+value+'" onclick="'+onClickFunction+'">'+text+'</button>';
	}


</script>