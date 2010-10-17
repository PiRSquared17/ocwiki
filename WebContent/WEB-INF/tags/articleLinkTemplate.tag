<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="id"
    description="ID của bài viết" required="true" 
    rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="title"
    description="Trường title của thẻ a" required="false" 
    rtexprvalue="true" type="java.lang.String"%>
    
<a href="${config.articlePath}/${id}" title="${title}"><jsp:doBody/></a>