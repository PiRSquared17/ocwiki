OCWiki chia sẻ tri thức với tất cả mọi người. Không chỉ người dùng cuối sử dụng hệ thống mà OCWiki mong muốn các bên thứ ba phát triển ứng dụng dựa trên nền tảng dữ liệu của OCWiki, khai thác và đóng góp dữ liệu cho cộng đồng.

OCWiki đã phát triển hệ thống web service dựa theo kiến trúc [REST](http://en.wikipedia.org/wiki/Representational_State_Transfer) với định dạng dữ liệu [JSON](http://en.wikipedia.org/wiki/JSON).

# Mục lục #


# Quy ước #

Dưới đây `restPath` là đường dẫn gốc của tất cả các dịch vụ REST. Trên máy của developer `restPath`=`http://localhost:8080/ocwiki/rest`, đối với ocwiki.org, `restPath`=`http://ocwiki.org/rest`.

# Định dạng dữ liệu #

Dữ liệu được trả về dưới dạng chuỗi JSON mã hoá một đối tượng JavaScript. Các kết quả luôn chứa trong thuộc tính `result` của đối tượng, ngoài ra có thể có thêm một số thuộc tính khác cung cấp thông tin bổ sung.

Có hai loại kết quả:
  * đối tượng đơn lẻ: thuộc tính `result` chứa đối tượng đó
  * danh sách các đối tượng:
    * thuộc tính `result` chứa một mảng các đối tượng
    * thuộc tính `totalCount` chứa tổng số các đối tượng có trong CSDL (có thể nhiều hơn số phẩn tử của mảng trên tuỳ vào cách phân trang và số lượng dữ liệu)
    * thuộc tính `next` chứa URI đến trang tiếp theo (nếu có)

# Danh mục tài nguyên và dịch vụ #
  * [Câu hỏi](RestQuestion.md)
  * [Đề thi](RestTest.md)
  * [Bài giải](RestSolution.md)
  * [Chủ đề](RestTopic.md)
  * [Bài giảng](RestArticle.md)
  * [Tập tin](RestFile.md)
  * [Thảo luận](RestComment.md)
  * [Đăng nhập](RestLogin.md)