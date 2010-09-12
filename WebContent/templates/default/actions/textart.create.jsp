<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<ocw:form action="textart.create">
<table>
  <tr>
    <td>Tên:</td>
    <td><input type="text" name="name" value="${param.name}"></input></td>
  </tr>
  <tr>
    <td>Không gian tên:</td>
    <td>
	    <select name="namespace">
	        <option value="0" ${param.namespace==0 ? 'selected="selected"' : ''}>Chính</option>
	        <option value="1" ${param.namespace==1 ? 'selected="selected"' : ''}>OCWiki</option>
	    </select>
    </td>
  </tr>
  <tr>
    <td>Nội dung:</td>
    <td><textarea name="content">${param.content}</textarea></td>
  </tr>
  <tr>
    <td colspan="2"><button name="submit" type="submit" value="create">Tạo</button></td>
  </tr>
</table>
</ocw:form>