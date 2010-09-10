<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div>
<p>Create comment---</p>
<form id="creat-comment" name="creat-comment">
<span id = "cannot-post" style="color: red; visibility: hidden;" ></span>
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

	var articleID = ${action.resource.id};
	function postComment(){
		var newComment = { resource: { id: articleID }, message: $F('comment-input') };
		new Ajax.Request(
				restPath + '/comments',
				{
					method: 'post',
					contentType: 'application/json',
				    postBody: Object.toJSON(newComment),
					requestHeaders : {
						Accept : 'application/json'
					},
					evalJSON : true,
					onSuccess : function(transport) {
						$('cannot-post').innerHTML = 'Có thể đăng comment!';
						$('cannot-post').show();
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