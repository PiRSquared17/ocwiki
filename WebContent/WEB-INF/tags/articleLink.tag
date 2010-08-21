<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="resource"
    description="Đối tượng bài viết (lớp Resource)" required="true" 
    rtexprvalue="true" type="oop.data.Resource"%>

<a href="${homeDir}/article/${resource.id}"><jsp:doBody /></a>