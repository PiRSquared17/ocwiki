## Kịch bản ##
  * Người dùng kích hoạt hộp thoại bằng cách nào đó (từ trang sửa bài viết, chọn câu hỏi, thêm chủ đề, v.v...)
  * Hệ thống hiển thị hộp thoại cùng với lời gợi ý nhập từ khoá tìm kiếm
  * Người dùng nhập từ khoá tìm kiếm và nhấn enter hoặc nhấn vào nút tìm
  * Hệ thống hiển thị các kết quả
  * Người dùng chọn một kết quả và nhấn đồng ý
  * Hệ thống đóng hộp thoại và trả quyền điều khiển về cho trang chứa hộp thoại

## Định dạng kết quả ##
Kết quả bao gồm tên tài nguyên và tóm tắt nội dung tài nguyên.

Tên tài nguyên có thể rỗng, trong trường hợp đó mã số của tài nguyên được hiển thị dưới dạng #id.

Tóm tắt nội dung hiển thị phần văn bản của tài nguyên chứa từ khoá tìm kiếm, với các từ khoá xuất hiện được in đậm.

Độ dài của tóm tắt không quá 160 ký tự hiển thị (độ dài phần tóm tắt của Google khoảng này). Nếu dài hơn thì được cắt bớt và thêm dấu "...".

## Gọi hộp thoại ##
  * Người gọi có thể định ra một số ràng buộc đối với tài nguyên
    * Loại tài nguyên (câu hỏi, đề thi,...)
    * Trạng thái
  * Người gọi hộp hội thoại cung cấp callback để nó gọi sau khi đóng