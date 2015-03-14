File này định nghĩa các taglib mà hầu hết các trang JSP đều cần đến. Tên viết tắt cho các taglib được quy định trong file này như sau:

  * c: [core](http://download.oracle.com/docs/cd/E17802_01/products/products/jsp/jstl/1.1/docs/tlddocs/c/tld-summary.html)
  * fn: [function](http://download.oracle.com/docs/cd/E17802_01/products/products/jsp/jstl/1.1/docs/tlddocs/fn/tld-summary.html)
  * u: [utils.tld](TaglibUtils.md)
  * ocw: [ocwiki.tld](TaglibOcwiki.md)

Hai dòng đầu tiên của mọi file JSP trong project đều phải như sau:
```jsp

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common.jsp" %>
```