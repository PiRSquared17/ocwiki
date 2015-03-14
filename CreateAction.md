**Mục lục**


[Action](Action.md) là hành động chính được thực hiện khi truy cập một URL, trên giao diện nó tương ứng với phần nằm chính giữa trang.

## Tạo lớp xử lý nghiệp vụ ##
Các thao tác nghiệp vụ cần được thực hiện bởi lớp riêng, extends từ [lớp AbstractAction](ClassAbstractAction.md).

Cài đặt đoạn mã xử lý nghiệp vụ và chuẩn bị dữ liệu trong hàm performImpl (override của [lớp AbstractAction](ClassAbstractAction.md)).

Các trang JSP không được phép tự truy cập dữ liệu nên trong lớp nghiệp vụ cần lấy dữ liệu từ CSDL và tạo sẵn getter cho JSP gọi. Lưu ý hàm truy cập có thể bị gọi nhiều lần nên không để lệnh lấy dữ liệu từ CSDL trong đó mà để trong hàm performImpl.

Trước khi kết thúc hàm performImpl, hãy nhớ đặt tiêu đề cho trang bằng cách gọi hàm title.

Ta được `HelloWorldAction` như sau:

```
public class HelloWorldAction extends AbstractAction {

	private Date currentTime;

	@Override
	protected void performImpl() throws IOException, ServletException {
		currentTime = new Date();
		title("Hello world");
	}
	
	public Date getCurrentTime() {
		return currentTime;
	}

}
```

## Tạo trang JSP trình bày ##

Các trang JSP có trách nhiệm chuyển các dữ liệu đã được chuẩn bị bởi lớp nghiệp vụ thành mã HTML.

Trang JSP được đặt trong thư mục `WebContent/templates/default/actions` và có tên là tên action + đuôi .jsp.

Trong ngữ cảnh của trang JSP, đối tượng của lớp nghiệp vụ vừa thực hiện được truyền vào biến có tên là "action". JSP có thể gọi hàm "getFoo()" của đối tượng này bằng cách viết `${action.foo}`.

Ví dụ ta có file hello-world.jsp như sau:

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<h3>Xin chào!!!</h3>
<h3>Thời điểm hiện tại là ${action.currentTime}</h3>
```

## Cấu hình ##
Tệp `/WebContent/WEB-INF/conf/default.xml` chứa cấu hình các action. Mỗi action được định nghĩa bằng một thẻ `<action></action>` trong đó có các thẻ con:

  * name: tên action (phải phù hợp với tên trang JSP)
  * class: tên đầy đủ của lớp nghiệp vụ
  * [và một số thẻ khác](ConfigFile#Th%E1%BA%BB_action.md)

Với action Hello world, chúng ta thêm các dòng sau vào tệp cấu hình:

```
    <action>
        <name>hello-world</name>
        <class>org.ocwiki.controller.action.HelloWorldAction</class>
    </action>
```

## Chạy thử ##
Khởi động hoặc khởi động lại server để action sẵn sàng hoạt động.

Sau khi server khởi động thành công (console không có exception), action vừa tạo có thể truy cập từ địa chỉ http://localhost:8080/ocwiki/action/hello-world (có thể cần thay "ocwiki" bằng tên project của bạn, hello-world bằng tên action của bạn). Ta có kết quả như sau:

![http://images.ocwiki.googlecode.com/hg/hello-world-action.png](http://images.ocwiki.googlecode.com/hg/hello-world-action.png)

## Mã nguồn ví dụ ##
Mã nguồn của ví dụ trên được đặt trong branch "helloworld" của code base. Bạn có thể phải chuột vào project > Team > Switch to... và chọn branch "helloworld" hoặc xem trực tiếp mã nguồn theo các đường dẫn sau:

  * [HelloWorldAction.java](http://code.google.com/p/ocwiki/source/browse/src/org/ocwiki/controller/action/HelloWorldAction.java?r=helloworld)
  * [hello-world.jsp](http://code.google.com/p/ocwiki/source/browse/WebContent/templates/default/actions/hello-world.jsp?r=helloworld)
  * [default.xml](http://code.google.com/p/ocwiki/source/browse/WebContent/WEB-INF/conf/default.xml?r=helloworld)