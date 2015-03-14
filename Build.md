![http://images.ocwiki.googlecode.com/hg/resource-utf8.png](http://images.ocwiki.googlecode.com/hg/resource-utf8.png)

  * Cài đặt Vietnamese collation cho MySQL theo hướng dẫn: http://vietunicode.sourceforge.net/howto/vietcollationmysql.html.

  * Mở tệp server.xml trong project có tên Servers, thêm thuộc tính `URIEncoding="UTF-8"` vào tất cả các thẻ `<Connector>`.

  * Copy file WebContent/WEB-INF/conf/myconfig.xml.sample thành file _any-name_.xml, thay đổi các thông số bên trong cho phù hợp (đảm bảo rằng người sử dụng MySQL có quyền tạo CSDL).

  * Phải chuột vào file vừa tạo, chọn Team > Ignore... > Only this file > OK.

  * Khởi động server và truy cập đường dẫn http://localhost:8080/ocwiki/setup (thay ocwiki bằng tên project).