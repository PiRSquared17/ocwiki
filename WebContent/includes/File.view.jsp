<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="file" value="${empty article ? action.article : article}"></c:set>

<p><b>Tên File :</b> ${file.filename}</p>
        <c:if test="${file.image}">
        	<img alt="${file.filename}" src="${template}/uploads/${file.filename}"/>
	    </c:if>
<table class="toccolours mbox-inside" style="width: 100%;" cellpadding="2">
<tr>
<th style="background: #ccf; text-align: right; vertical-align: top; padding-right: 0.4em; width: 15%;" id="fileinfotpl_desc">Tên tệp tin</th>
<td>
<p>${file.filename}</p>
</td>
</tr>
<tr>
<th style="background: #ccf; text-align: right; vertical-align: top; padding-right: 0.4em; width: 15%;" id="fileinfotpl_desc">Tác giả</th>
<td>
<p>${file.author}</p>
</td>
</tr>
<tr valign="top">
<th style="background: #ccf; text-align: right; padding-right: 0.4em;" id="fileinfotpl_src">Nguồn</th>
<td>
<p>${file.originalSource}</p>
</td>
</tr>
<tr valign="top">
<th style="background: #ccf; text-align: right; padding-right: 0.4em; white-space: nowrap;" id="fileinfotpl_date">Ngày tạo</th>
<td>
<p>${file.dateOfWork}</p>
</td>
</tr>
<tr valign="top">
<th style="background: #ccf; text-align: right; padding-right: 0.4em;" id="fileinfotpl_aut">Thông tin thêm</th>
<td>
<p>${file.additionalInfo}</p>
</td>
</tr>
</table>
