<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<br/><br/>
<!-- <p align="center"><b>::Các nhận xét::</b></p> -->
<div><jsp:include page="comment-toolbar.jsp"></jsp:include></div>
<div id="commentslist"> ... đang tải... </div>

<p><jsp:include page="comment-create.jsp"></jsp:include></p>

<script type="text/javascript" src="${templatePath}/js/datetime.js"></script>
<script type="text/javascript">
	var articleID = ${action.resource.id};
	var curPage = 0;
	var pageCount = 0;
	var commentCount = 0;
	var curPageCommCount = 0;

	Event.observe(window, 'load', loadLatest);	

	//load comments
	function loadLatest(){
		curPageCommCount = 0
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
						commentCount = listResult.totalCount;
						pageCount = getPageCount(listResult.totalCount);
						curPage = pageCount-1;
						
						comments = listResult.result;
						if (comments==null){
							$('commentslist').innerHTML = 'Chưa có nhận xét';
							pagination();
						} else {
							if (comments.length>0){
								for (i=0;i<comments.length;i++){
									commentslisthtml+=showComments(comments[i]);					
								}
								$('commentslist').innerHTML = commentslisthtml;
								pagination();
							} else {
								$('commentslist').innerHTML = 'Chưa có nhận xét';
								pagination();
							}
						}
					},
				    onFailure: function(transport)
				    { 
						DefaultTemplate.onFailure(transport); 
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
									commentslisthtml+=showComments(comments[i]);					
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

	function showComments(commentPreview){
		var commenthtml='';
		commenthtml+=('<div class="comment');
		if ((curPageCommCount%2)==0){
			commenthtml+=(' even');
		}
		commenthtml+=('" id=comment'+commentPreview.comment.id+'>');
		var avatarUrl = '${templatePath}/images/default-avatar-thumbnail.gif'; 
		if (commentPreview.comment.user.avatar) {
			avatarUrl = '${config.uploadPath}/avatar/thumbnail/' + commentPreview.comment.user.avatar; 
		}
		commenthtml+='<div class="avatar"><img src="' + avatarUrl + '"></div>';
		commenthtml+=('<div class="comment-body"><div style="float:left; margin-right: 5px"><a href="${scriptPath}?action=user.profile&user='+commentPreview.comment.user.id+'">'+commentPreview.comment.user.fullname+'</a></div>');
		commenthtml+=('<div style="display:' + (commentPreview.status == 'HIDDEN' ? 'none' : 'block') + '" id=commentmessage'+commentPreview.comment.id+'>'+commentPreview.comment.message+'</div>');
		commenthtml+=('<div>');
		commenthtml+=dateToString(commentPreview.comment.timestamp);
		commenthtml+=(' <span style="display:' + (commentPreview.likeCount == 0 ? 'none' : 'inline') + '" id="commentlikecountpreview'+commentPreview.comment.id+'"><span id="commentlikecount'+commentPreview.comment.id+'">'+commentPreview.likeCount+'</span> người thích.</span>');
		if (login){
			commenthtml+=('<a style="display:' + (commentPreview.status == 'LIKE' ? 'none' : 'inline') + '" id="commentlike'+commentPreview.comment.id+'" href="#" onclick = "likeComment('+commentPreview.comment.id+'); return false;" >'+'thích</a>');
			commenthtml+=('<a style="display:' + (commentPreview.status == 'LIKE' ? 'inline' : 'none') + '" id="commentunlike'+commentPreview.comment.id+'" href="#" onclick = "unlihiComment('+commentPreview.comment.id+'); return false;" >'+'bỏ thích</a>');
			commenthtml+=('.<a style="display:' + (commentPreview.status == 'HIDDEN' ? 'none' : 'inline') + '" id="commenthide'+commentPreview.comment.id+'" href="#" onclick = "hideComment('+commentPreview.comment.id+'); return false;" >'+'ẩn</a>');
			commenthtml+=('<a style="display:' + (commentPreview.status == 'HIDDEN' ? 'inline' : 'none') + '" id="commentunhide'+commentPreview.comment.id+'" href="#" onclick = "unlihiComment('+commentPreview.comment.id+'); return false;" >'+'hiện</a>');
			//commenthtml+=('.<a id="commentdel'+comment.id+'" href="#" onclick = "del('+comment.id+'); return false;" >'+'del</a>');
		}
		commenthtml+=('</div></div>');
		commenthtml+='</div><div class="clear"></div>';
		curPageCommCount++;
		return commenthtml;
	}

	function likeComment(lid){
		
		var newCommentCustomization = {comment:{id: lid}, status:'LIKE'};
		new Ajax.Request(				
				restPath + '/comment_customizations',
				{
					method: 'post',
					contentType: 'application/json',
				    postBody: Object.toJSON(newCommentCustomization),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						var newCommentReport = transport.responseJSON.result;
						$('commentlikecount'+lid).innerHTML=newCommentReport.likeCount;
						if (newCommentReport.likeCount<=0) {
							$('commentlikecountpreview'+lid).hide();
						} else {
							$('commentlikecountpreview'+lid).show();
						}
						$('commentmessage'+lid).show();
						
						$('commentlike'+lid).hide();
						$('commentunlike'+lid).show();
						$('commenthide'+lid).show();
						$('commentunhide'+lid).hide();
					},
				    onFailure: function(transport){ 
						DefaultTemplate.onFailure(transport); 
				    }		
				}				
			);
	}

	function unlihiComment(lhid){
		
		var newCommentCustomization = {comment:{id: lhid}, status:'NORMAL'};
		new Ajax.Request(				
				restPath + '/comment_customizations',
				{
					method: 'post',
					contentType: 'application/json',
				    postBody: Object.toJSON(newCommentCustomization),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						var newCommentReport = transport.responseJSON.result;
						$('commentlikecount'+lhid).innerHTML=newCommentReport.likeCount;
						if (newCommentReport.likeCount<=0) {
							$('commentlikecountpreview'+lhid).hide();
						} else {
							$('commentlikecountpreview'+lhid).show();
						}
						$('commentmessage'+lhid).show();
						
						$('commentlike'+lhid).show();
						$('commentunlike'+lhid).hide();
						$('commenthide'+lhid).show();
						$('commentunhide'+lhid).hide();
					},
				    onFailure: function(transport){ 
						DefaultTemplate.onFailure(transport); 
				    }		
				}				
			);
	}
	
	function hideComment(hid){
		
		var newCommentCustomization = {comment:{id: hid}, status:'HIDDEN'};
		new Ajax.Request(				
				restPath + '/comment_customizations',
				{
					method: 'post',
					contentType: 'application/json',
				    postBody: Object.toJSON(newCommentCustomization),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						var newCommentReport = transport.responseJSON.result;
						$('commentlikecount'+hid).innerHTML=newCommentReport.likeCount;
						if (newCommentReport.likeCount<=0) {
							$('commentlikecountpreview'+hid).hide();
						} else {
							$('commentlikecountpreview'+hid).show();
						}
						$('commentmessage'+hid).hide();
						
						$('commenthide'+hid).hide();
						$('commentunhide'+hid).show();
						$('commentlike'+hid).show();
						$('commentunlike'+hid).hide();
					},
				    onFailure: function(transport){ 
						DefaultTemplate.onFailure(transport); 
				    }		
				}				
			);
	}
	
	function del(id){
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
			$('btn-prev').hide();
			$('btn-oldfirst').hide();
			$('btn-next').hide();
			$('btn-newfirst').hide();						
		}		
		disableButtons();
	}

	function disableButtons(){
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