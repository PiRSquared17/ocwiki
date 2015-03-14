## use case ##

### Khoá người dùng ###
  1. Mở profile của một người dùng
  1. Nhấn nút khoá
  1. Chọn thời hạn khoá và nhấn xác nhận
  1. Người dùng bị khoá đến hết thời hạn đã chọn.

### Mở khoá người dùng ###
  1. Mở profile của một người dùng
  1. Nhấn nút mở khoá
  1. Người dùng được mở khoá

### Cảnh cáo người dùng ###
  1. Mở profile của một người dùng
  1. Nhấn nút cảnh cáo
  1. Nhập nội dung cảnh cáo, chọn thời hạn khoá và nhấn xác nhận
  1. Nội dung cảnh cáo xuất hiện trên giao diện của người dùng đến hết thời hạn

### Bỏ cảnh cáo người dùng ###
  1. Mở profile của một người dùng
  1. Nhấn nút huỷ bỏ cảnh cáo
  1. Người dùng không còn bị cảnh cáo nữa

## Yêu cầu ##
  * Thực hiện chức năng trong cùng 1 trang (AJAX)
    * Hiện form xác nhận, nhập nội dung dưới dang dialog ngay trong trang profile
    * Dùng AJAX để gửi yêu cầu lên server
    * Dùng web service để lưu nội dung user
  * Validate dữ liệu trong webservice
  * Hiện thông báo lỗi (nếu có) trên giao diện