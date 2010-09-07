<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<p>Create comment---</p>
<form id="creat-comment" name="creat-comment" method="" action="">
<label>Nhận xét<br />
<textarea name="comment-input" id="comment-input" cols="45" rows="5"></textarea>
</label>
<p>
  <label>
  <input type="button" name="btn-post" id="btn-post" value="Đăng nhận xét" />
  </label>
  <label>
  <input type="reset" name="btn-reset" id="btn-reset" value="Hủy bỏ" />
  </label>
</p>
</form>