# Test 02 #
  * Các vấn đề phát hiện được khi kiểm thử được cập nhật ở đây.
  * Vấn đề phức tạp sẽ được đưa vào issuse

  * Ngày bắt đầu: 101107
  * Ngày kết thúc:



# Chi tiết #

## 101107 ##
  * Bài viết:
    * _Xóa (admin): Chưa hỗ trợ_
    * Lịch sử:
      * ~~hiển thị hơi lỗi tí~~
      * ~~Lỗi lùi phiên bản - wrong edit token~~
      * ~~Lỗi khi view phiên bản và chuyển phiên bản khác - unsupported type - cái này do phiên bản đó view bị lỗi~~
    * In:
      * ~~Chưa in được - File &quot;/includes/Solution.print.jsp&quot; not found~~
    * coi:
      * cần làm:
      * mức độ:
      * thik:
      * comment:
        * list:
          * like:
          * ẩn:
            * ~~cạnh nút ẩn có 1 dấu chấm kì quái~~
        * tạo:
    * list:
    * Riêng 1 dạng cụ thể:
      * chủ đề:
        * Tạo:
          * tạo mới + chủ đề cha: Toán học, + ND: logic, bấm lưu, chẳng có j` xảy ra?
          * Tên chủ đề có cần phải text area hok?
        * Sửa:
        * coi:
          * xem tree có thể hiện các topic root đc hem?
      * bài giảng:
        * Tạo:
          * Ô nội dung hơi hẹp và ngắn
        * Sửa:
      * test:
        * Tạo Sửa:
          * mới tạo thì nên để ô sửa nd mặc định là hiện.
          * nút "thêm" làm ng` dùng bối rối, bên cạnh đó chỉ điền mã thì chịu sao bít là cái nào mà thêm?
          * thêm 1 câu hỏi hok có thì báo là câu hỏi đã tồn tại
          * ~~**sửa điểm có lỗi?**~~
          * Mất tên section khi lưu?
        * làm:
          * ~~gửi kết quả báo lỗi? could not insert: [oop.data.ChoiceAnswer] - Table 'ocwiki.historyanswer' doesn't exist~~
      * câu hỏi:
        * Tạo:
          * **Nội dung khi bấm sang sửa lựa chọn khác thì biến mất** (sửa thì hok bị nhưng tạo thì bị?)
        * Sửa:
          * ~~Hok hiện giá trị mặc định (nội dung hiện tại câu hỏi) -> sửa mà gõ lai thì..~~
          * **Lựa chọn mới khi đã sửa và bấm sang sửa lựa chọn khác vẫn hiển thị là lựa chọn mới**
        * làm:
        * nên có nút viết bài giải và xem các bài giải ngay trong view
      * file:
        * Tạo Sửa:
> > > > > Có thông báo thực hiện khi bấm lưu nhưng có vẻ bị treo ở 1 đoạn nào đó?
        * upload: Hok test đc
  * Thống kê: OK
  * Tìm kiếm:
    * nếu từ khóa chung chung thì phải trả về là từ khóa quá chung
  * Nháp: OK
  * Soạn kí tự toán: OK
  * Người dùng:
    * chung:
      * Đăng nhập:
        * username và pass:
          * Layout lại tí thì đẹp
        * OpenID: Tạm OK với tất cả các nhà cung cấp được liệt kê
          * ~~chưa xử lý khi user bị block. :D~~
        * Facebook: Hok test đc
        * Liên kết tài khoản:
      * Đăng xuất: OK
      * Sửa thông tin: Có vẻ OK
      * Danh sách người dùng: OK
      * Xem thông tin: OK
    * teacher:
    * admin:
      * khóa/mở:
        * người dùng: OK
        * bài viết: Không hoạt động
      * cảnh báo/bỏ cảnh báo người dùng: OK

## ~~Hiện tại có nhiều hiện tượng rất lạ khi merge, Minh siêu nhân cần check lại~~ ##

Để xử lý hiện tượng này, theo tớ trc hết là phải clean triệt để.
Bên cạnh đó mỗi khi các bạn sửa hoàn thiện 1 lỗi, hãy commit lun là chú thích tên lỗi. Sau đó nếu có vấn đề j` tớ có thể dùng phiên bản của các file đã sửa trong lần commit đó để test.