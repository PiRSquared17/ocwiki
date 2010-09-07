<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="resource"
    description="Đối tượng bài viết (lớp Resource)" required="true" 
    rtexprvalue="true" type="oop.data.Resource"%>

<button type="button" onclick="location.href='${homeDir}/article/${resource.id}'"><jsp:doBody /></button>