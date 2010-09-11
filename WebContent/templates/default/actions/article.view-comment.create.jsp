<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div>
<p>Create comment---</p>
<form id="creat-comment" name="creat-comment">
<span id = "cannot-post" style="color: red; display:none;" ></span>
<label>Nhận xét<br />
<textarea name="comment-input" id="comment-input" cols="45" rows="5"></textarea>
</label>
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
		var newComment = {message: $F('comment-input')};
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
						newpostcomment = transport.responseJSON.result;
						$('commentslist').innerHTML+=newpostcomment.message;
						$('commentslist').innerHTML+=('<br/>------------<br/>');
						//tinyMCE.getInstanceById('comment-input').getBody().innerHTML='';						
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