# Test 01 #
  * Các vấn đề phát hiện được khi kiểm thử được cập nhật ở đây.
  * Vấn đề phức tạp sẽ được đưa vào issuse

  * Ngày bắt đầu: 101105
  * Ngày kết thúc: 101106

  * Các lỗi này sẽ được ưu tiên test trong test02

# Chi tiết #

## 101105 ##
  * Merge các branch + setup lại mySQL -> một số lỗi hiển thị
    * ~~Thay đổi tên người dùng, tạo người dùng mới -> lỗi tiếng việt~~
    * ~~Lỗi tiếng việt 1 lúc dẫn đến tên người dùng dài quá -> lỗi ở ảnh 101105\_001.pg~~
  * Test chung - homepage, chưa login
    * ~~Search cần hiển thị không tìm thấy hoặc thông báo từ khóa quá chung chung.~~
    * ~~kết quả tìm kiếm cần có 1 phần nội dung chứa từ khóa tìm kiếm.~~ cái này rất phức tạp, không kịp làm
    * ~~1 nút chủ đề cạnh ô search 1 cái bên menu bên trái → bối rối?~~
    * ~~xem qua 1 bài viết → bài liên quan nên có phần nội dung hiển thị ra chứ nhìn id thì :(~~
    * _Yêu cầu đăng nhập được gửi đến action đăng nhập bằng username&pass → không có ý nghĩa j`, cũng hok có nút cancel. >~~<~~_

  * các tính năng dùng ajax nên hiện chữ đang xử lý hok thì ng` dùng chẳng bít đằng nào mà lần
    * cụ thể là tính năng nào :-S

  * Test chung - đã đăng nhập - mức người dùng
    * ~~lịch sử làm bài hok hoạt động~~
    * ~~nháp hok lưu bằng tay đc cũng hok lưu khi chuyển trang~~
    * ~~tạo mới đề thi lỗi jsp~~
    * ~~tạo mới > cấu trúc đề hok hoạt động~~ bỏ roài
    * ~~đôi lúc Nháp tự nhiên xuất hiện hok làm sao đóng đc~~
  * Test tính năng - Hoạt động của từng tính năng đã được xây dựng
    * Tính năng liên quan đến bài viết:
      * ~~Xem danh sách bài viết: chưa có xem danh sách bài viết~~
      * Tạo bài viết:
        * ~~Khi bấm tạo mới bài viết thì bài viết bị tạo lun và riêng bài giảng thì hiện lên trang chủ tất cả đều là nội dung chẳng có j` cả?~~
        * ~~Lỗi gặp phải khi tạo câu hỏi mới và bấm lưu: org.apache.jasper.JasperException: javax.servlet.ServletException: File "/includes/.edit.jsp" not found + lỗi tương tự khi sửa 1 câu hỏi cũ~~
        * ~~Tạo chủ đề: chọn chủ đề cha là text field thì người ta chọn = mắt ah. :))~~
        * chưa preview đc
        * ~~Không tạo đc file -  lỗi kì lạ nào đó~~
        * ~~các trang tạo mới đều thiếu title~~
        * ~~Tạo câu hỏi mới hok thêm lựa chọn mới đc~~
        * ~~Tạo file mới cũng lỗi và hok thực hiện đc~~

  * Tính năng nháp:
    * ~~Hok lưu đc đặc biệt cần khi chuyển trang.~~
    * ~~Có lỗi khi mới đăng nhập xong, ô nháp nhảy ra đóng hok nổi~~

  * Các tính năng liên quan đến người dùng:
    * tính năng upload ava
      * ~~chưa có title~~
      * ~~Không biết giới hạn kích thước và kiểu file ava đc up -> up file 3M lên thì xịt~~
    * tính năng đăng nhập
      * ~~Dùng user and pass khi một trường user lỗi gõ sai tên ng` dùng, gõ lại đúng rồi, sai pass, vẫn hiện thông báo lỗi người dùng hok tồn tại~~
      * ~~Tính năng đăng kí và quên mật khẩu hok có thì nên bỏ đi~~
      * ~~"Trở về trang chủ" trong action bị sai~~
      * ~~đang ở action đăng nhập bằng openID mà đăng nhập dùng username, pass = ajax thì đăng nhập xong vẫn ở lại trang đăng nhập đó sau đó đăng nhập bằng OpenID vẫn ngon~~
      * ~~Đăng nhập bằng OpenID bị mất bắt lỗi hok truy cập đc đến nhà cung cấp~~
    * admin:
      * chưa có edit group
      * Hok có panel riêng cho admin

  * Thống kê:
    * Chủ đề chưa đc phân loại sao lại có cả bài viết mới và câu hỏi mới?

# Test01 đã kết thúc, các bạn xúc tiến việc sửa các lỗi trên và edit vào đây nhé #


