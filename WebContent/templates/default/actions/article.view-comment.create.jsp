<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div id="creat-comment">
<form id="creat-comment" name="creat-comment">

<label>Đăng nhận xét của bạn<br />
<textarea name="comment-input" id="comment-input" cols="45" rows="5"></textarea>
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
</form>
</div>
<script language="javascript">
	$('cannot-post').hide();
	function postComment(){
		var newComment = {message: tinyMCE.getInstanceById('comment-input').getContent()};
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
						var newpostcomment = transport.responseJSON.result;
						$('commentslist').innerHTML+=showComments(newpostcomment);
						tinyMCE.getInstanceById('comment-input').getBody().innerHTML='';						
					},
				    onFailure: function()
				    { 
						$('cannot-post').innerHTML = 'Không thể đăng comment!';
						$('cannot-post').show();
				    }		
				}
			);
	}
</script>