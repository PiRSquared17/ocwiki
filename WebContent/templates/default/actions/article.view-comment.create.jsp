<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div id="creat-comment">
<form id="creat-comment" name="creat-comment" >
<div align="center">
<label><b>::Đăng nhận xét của bạn::</b><br />
<textarea name="comment-input" id="comment-input" style="width: 100%" cols="45" rows="5"></textarea>
</label>
<p><span id = "cannot-post" style="color: red; display:none;" ></span></p>
<p>

  <label>
  <input type="button" name="btn-post" id="btn-post" value="Đăng nhận xét" onclick="postComment()"/>
  </label>
  <label>
  <input type="reset" name="btn-reset" id="btn-reset" value="Hủy bỏ" />
  </label>
</p>
</div>
</form>
</div>
<script language="javascript">
	$('cannot-post').hide();
	function postComment(){
		
		var newMessage = tinyMCE.getInstanceById('comment-input').getContent();
		if (login!=true){
			var callLogin = 'đăng nhập';//phải gọi đến login menu ở đây.//'<a href="#" onclick="$(\'login-dropdown-menu\').style.background=\"red\";">đăng nhập</a>';
			$('cannot-post').innerHTML = 'Bạn cần '+callLogin+' để thảo luận!';
			$('cannot-post').show();
			return;
		}
		if (newMessage.empty()==true){
			$('cannot-post').innerHTML = 'Bạn chưa nhận xét gì!';
			$('cannot-post').show();
			return;
		}

		var newComment = {message: tinyMCE.getInstanceById('comment-input').getContent()};

		//for (i=68;i<200;i++){ //batch add comments
		//var newComment = {message: i};
		
		new Ajax.Request(
				restPath + '/comments/resource/' + articleID,
				{
					method: 'post',
					contentType: 'application/json',
				    postBody: Object.toJSON(newComment),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						var newPostComment = transport.responseJSON.result;
						var newPostCommentReport = {comment:newPostComment, /*user:getUser(),*/ status:'NORMAL' , likeCount: 0};
						if (commentCount == 0){
							$('commentslist').innerHTML=showComments(newPostCommentReport,0);
						} else {
							$('commentslist').innerHTML+=showComments(newPostCommentReport);
						}
						tinyMCE.getInstanceById('comment-input').getBody().innerHTML='';	
						$('cannot-post').hide();
						commentCount++;
						pageCount = getPageCount(commentCount);
						//can có curPage?
						pagination();			
					},
				    onFailure: function(transport)
				    { 
						DefaultTemplate.onFailure(transport); 
				    }		
				}				
			);
		//}
	}
</script>